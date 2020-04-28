package com.dragon.token.generate;

import com.dragon.crypto.Algorithm;
import com.dragon.crypto.Crypto;
import com.dragon.crypto.CryptoFactory;
import com.dragon.token.TokenBuilder;
import com.dragon.token.TokenParser;

import java.util.Date;

/**
 * @ClassName: AbstractToken
 * @Description: AbstractToken
 * @Author: pengl
 * @Date: 2020/4/28 22:16
 * @Version V1.0
 */
public abstract class AbstractToken implements Token{
    protected static final Crypto BASE64 = CryptoFactory.getCrypto(Algorithm.Base64);

    @Override
    public boolean verify(TokenParser parser) {
        try {
            parse(parser);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    protected boolean checkIsExpire(TokenBuilder jwtToken) {
        Date expireDate = jwtToken.getExpireDate();
        if (expireDate != null) {
            if (System.currentTimeMillis() > expireDate.getTime()) {
                return true;
            }
        }

        long expire = jwtToken.getExpire();
        if (expire != 0) {
            Date createDate = jwtToken.getCreateDate();
            if (System.currentTimeMillis() > (createDate.getTime() + expire)) {
                return true;
            }
        }
        return false;
    }
}
