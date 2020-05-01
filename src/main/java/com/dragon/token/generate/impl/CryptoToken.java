package com.dragon.token.generate.impl;

import com.alibaba.fastjson.JSONObject;
import com.dragon.crypto.Algorithm;
import com.dragon.crypto.CryptoException;
import com.dragon.crypto.CryptoFactory;
import com.dragon.crypto.CryptoParam;
import com.dragon.token.*;
import com.dragon.token.compression.CompressionException;
import com.dragon.token.compression.CompressionFactory;
import com.dragon.token.generate.AbstractToken;
import com.dragon.token.generate.TokenType;
import com.dragon.token.serialize.SerializeException;
import com.dragon.token.serialize.SerializeFactory;
import com.dragon.token.serialize.SerializeType;
import com.dragon.token.utils.Assert;
import com.dragon.token.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

/**
 * @ClassName: JwtToken
 * @Description: 更安全的加密token
 * @Author: pengl
 * @Date: 2020/4/28 22:07
 * @Version V1.0
 */
@Slf4j
public class CryptoToken extends AbstractToken {
    private static final TokenAlgorithm DEFAULT_ALGORITHM = TokenAlgorithm.AES;

    @Override
    public String create(TokenBuilder builder) {
        Assert.notBlank(builder.getKey(), "the crypto key is required");
        TokenAlgorithm tokenAlgorithm = builder.getAlgorithm() == null ? DEFAULT_ALGORITHM : builder.getAlgorithm();
        Assert.isEffectiveAlgorithm(tokenAlgorithm, TokenType.CRYPTO_TOKEN, "please use crypto token algorithm");

        //serialize
        byte[] bytes = SerializeFactory.getSerializable(builder.getSerializeType()).serialize(builder);

        //compression
        if (builder.getCompression() != null) {
            bytes = CompressionFactory.getCompression(builder.getCompression()).compress(bytes);
        }

        //encrypt
        byte[] encrypt = CryptoFactory.getCrypto(Algorithm.valueOf(tokenAlgorithm.toString())).encrypt(CryptoParam.builder().key(builder.getKey()).data(bytes).build());

        return StrUtils.newStringUtf8(BASE64.encrypt(encrypt));
    }

    @Override
    public boolean verify(TokenParser parser) {
        return false;
    }

    @Override
    public <T> T parse(TokenParser parser) {
        Assert.notBlank(parser.getKey(), "the crypto key is required");
        String token = parser.getToken();
        Assert.notBlank(token, "the token is blank");
        TokenAlgorithm tokenAlgorithm = parser.getAlgorithm() == null ? DEFAULT_ALGORITHM : parser.getAlgorithm();
        Assert.isEffectiveAlgorithm(tokenAlgorithm, TokenType.CRYPTO_TOKEN, "please use crypto token algorithm");

        try {
            //base64 decode
            byte[] data = BASE64.decrypt(token.getBytes());

            //decrypt
            data = CryptoFactory.getCrypto(Algorithm.valueOf(tokenAlgorithm.toString())).decrypt(CryptoParam.builder().key(parser.getKey()).data(data).build());

            //decompression
            if (parser.getCompression() != null) {
                data = CompressionFactory.getCompression(parser.getCompression()).decompress(data);
            }

            //deserialize
            TokenBuilder builder = SerializeFactory.getSerializable(parser.getSerializeType()).deserialize(data, TokenBuilder.class);

            //check expired
            if (parser.isCheckExpire() && checkIsExpire(builder)) {
                log.warn("token {} is expired !", parser.getToken());
                throw new TokenExpireException("token is expire");
            }

            //TODO very low ，need change to Generics T
            if (parser.getSerializeType() == SerializeType.FAST_JSON) {
                JSONObject dataObj = (JSONObject) builder.getData();
                return JSONObject.parseObject(dataObj.toJSONString(), (Type) parser.getDataType());
            }

            return (T) builder.getData();

        } catch (CryptoException e) {
            log.warn("token parse exception, CryptoException : {}", e.getMessage(), e);
            throw new TokenInvalidException("invalid token string");
        } catch (CompressionException e) {
            log.warn("token parse exception, CompressionException : {}", e.getMessage(), e);
            throw new TokenInvalidException("invalid token string");
        } catch (SerializeException e) {
            log.warn("token parse exception, SerializeException : {}", e.getMessage(), e);
            throw new TokenInvalidException("invalid token string");
        } catch (TokenExpireException e) {
            log.warn("token {} is expired !", token);
            throw new TokenExpireException("token is expire");
        } catch (Exception e) {
            log.warn("token parse exception, Exception : {}", e.getMessage(), e);
            throw new TokenInvalidException("invalid token string");
        }
    }
}
