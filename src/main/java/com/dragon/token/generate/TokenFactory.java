package com.dragon.token.generate;

import com.dragon.token.generate.impl.CryptoToken;
import com.dragon.token.generate.impl.JwtToken;

/**
 * @ClassName: TokenFactory
 * @Description: 根据类型获取Token实例
 * @Author: pengl
 * @Date: 2020/4/28 22:41
 * @Version V1.0
 */
public final class TokenFactory {

    private static final CryptoToken CRYPTO_TOKEN = new CryptoToken();
    private static final JwtToken JWT_TOKEN = new JwtToken();

    public static Token getToken() {
        return CRYPTO_TOKEN;
    }

    public static Token getToken(TokenType tokenType) {
        if (TokenType.CRYPTO_TOKEN == tokenType) {
            return CRYPTO_TOKEN;
        } else if (TokenType.JWT_TOKEN == tokenType) {
            return JWT_TOKEN;
        }
        return null;
    }
}
