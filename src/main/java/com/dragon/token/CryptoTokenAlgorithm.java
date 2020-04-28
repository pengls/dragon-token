package com.dragon.token;

/**
 * @ClassName: JwtAlgorithm
 * @Description: 用于CryptoToken的加密/解密算法
 * @Author: pengl
 * @Date: 2020/4/28 22:40
 * @Version V1.0
 */
public enum CryptoTokenAlgorithm {
    DES3,
    AES,
    PBEWithMd5AndDes,
    PBEWithMd5AndTripleDES,
    PBEWithSHA1AndDESede,
    PBEWithSHA1AndRC2_40;
}
