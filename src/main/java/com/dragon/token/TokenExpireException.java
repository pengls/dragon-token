package com.dragon.token;

/**
 * @ClassName: JwtInvalidException
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/4/1 22:02
 * @Version V1.0
 */
public class TokenExpireException extends RuntimeException {
    public TokenExpireException(String message) {
        super(message);
    }
}
