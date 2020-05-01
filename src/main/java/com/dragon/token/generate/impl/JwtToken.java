package com.dragon.token.generate.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dragon.crypto.*;
import com.dragon.token.*;
import com.dragon.token.compression.CompressionException;
import com.dragon.token.compression.CompressionFactory;
import com.dragon.token.generate.AbstractToken;
import com.dragon.token.generate.TokenType;
import com.dragon.token.utils.Assert;
import com.dragon.token.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName: JwtToken
 * @Description: 符合jwt规范的token  //TODO 需要重构
 * @Author: pengl
 * @Date: 2020/4/28 22:07
 * @Version V1.0
 */
@Slf4j
public class JwtToken extends AbstractToken {
    private static final TokenAlgorithm DEFAULT_ALGORITHM = TokenAlgorithm.MD5;

    @Override
    public String create(TokenBuilder builder) {
        TokenAlgorithm tokenAlgorithm = builder.getAlgorithm() == null ? DEFAULT_ALGORITHM : builder.getAlgorithm();
        Assert.isEffectiveAlgorithm(tokenAlgorithm, TokenType.JWT_TOKEN, "please use jwt token algorithm");
        String header = "{\"alg\":\"" + tokenAlgorithm.toString() + "\",\"typ\":\"JWT\"}";
        String bodyData = JSON.toJSONString(builder);
        byte[] body = bodyData.getBytes(StandardCharsets.UTF_8);
        //compression
        if (builder.getCompression() != null) {
            body = CompressionFactory.getCompression(builder.getCompression()).compress(body);
        }
        String sign = header + "." + bodyData;
        return Base64.encodeBase64URLSafeString(header.getBytes(StandardCharsets.UTF_8))
                + "." + Base64.encodeBase64URLSafeString(body) + "." + sign(sign, tokenAlgorithm, builder.getKey());
    }

    private String sign(String sign, TokenAlgorithm algorithm, String key) {
        String res = Base64.encodeBase64URLSafeString(CryptoFactory.getCrypto(Algorithm.valueOf(algorithm.toString()))
                .encrypt(CryptoParam.builder().data(sign.getBytes(StandardCharsets.UTF_8)).key(key).build()));
        return res;
    }

    @Override
    public <T> T parse(TokenParser parser) {
        String token = parser.getToken();
        Assert.notBlank(token, "the token is blank");
        try {
            String[] parses = token.split("\\.", 3);
            String header = CryptoHelper.base64Decode(parses[0]);
            String body = StrUtils.newStringUtf8(parser.getCompression() == null ? Base64.decodeBase64(parses[1]) : CompressionFactory.getCompression(parser.getCompression()).decompress(Base64.decodeBase64(parses[1])));
            String inputSign = parses[2];

            if (StrUtils.isAnyBlank(header, body, inputSign)) {
                throw new TokenInvalidException("invalid token string");
            }

            String sign = header + "." + body;
            TokenAlgorithm algorithm = TokenAlgorithm.valueOf(JSONObject.parseObject(header).getString("alg"));
            if (!inputSign.equals(sign(sign, algorithm, parser.getKey()))) {
                throw new TokenInvalidException("invalid token string");
            }

            TokenBuilder builder = JSONObject.parseObject(body, TokenBuilder.class);
            //check expired
            if (parser.isCheckExpire() && checkIsExpire(builder)) {
                log.warn("token {} is expired !", parser.getToken());
                throw new TokenExpireException("token is expire");
            }

            JSONObject dataObj = (JSONObject) builder.getData();
            return JSONObject.parseObject(dataObj.toJSONString(), (Type) parser.getDataType());

        } catch (CryptoException e) {
            log.warn("token parse exception, CryptoException : {}", e.getMessage(), e);
            throw new TokenInvalidException("invalid token string");
        } catch (CompressionException e) {
            log.warn("token parse exception, CompressionException : {}", e.getMessage(), e);
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
