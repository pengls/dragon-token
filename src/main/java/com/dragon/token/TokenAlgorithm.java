package com.dragon.token;

import com.dragon.token.generate.TokenType;

public enum TokenAlgorithm {
    //=================== CryptoToken Algorithm ===================//
    DES3(TokenType.CRYPTO_TOKEN),
    AES(TokenType.CRYPTO_TOKEN),
    PBEWithMd5AndDes(TokenType.CRYPTO_TOKEN),
    PBEWithMd5AndTripleDES(TokenType.CRYPTO_TOKEN),
    PBEWithSHA1AndDESede(TokenType.CRYPTO_TOKEN),
    PBEWithSHA1AndRC2_40(TokenType.CRYPTO_TOKEN),

    //=================== JwtToken Algorithm ===================//
    MD5(TokenType.JWT_TOKEN),
    SHA1(TokenType.JWT_TOKEN),
    SHA256(TokenType.JWT_TOKEN),
    SHA384(TokenType.JWT_TOKEN),
    SHA512(TokenType.JWT_TOKEN),
    HmacMD5(TokenType.JWT_TOKEN),
    HmacSHA1(TokenType.JWT_TOKEN),
    HmacSHA256(TokenType.JWT_TOKEN),
    HmacSHA384(TokenType.JWT_TOKEN),
    HmacSHA512(TokenType.JWT_TOKEN);


    private TokenType tokenType;

    private TokenAlgorithm(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

}
