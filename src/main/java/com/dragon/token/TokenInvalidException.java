package com.dragon.token;

/**
 * @ClassName: JwtInvalidException
 * @Description: 无效token
 * @Author: pengl
 * @Date: 2020/4/1 22:02
 * @Version V1.0
 */
public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException(String message) {
        super(message);
    }
}
