package com.dragon.token.serialize;

/**
 * @ClassName: SerializeException
 * @Description: SerializeException
 * @Author: pengl
 * @Date: 2020/4/1 21:17
 * @Version V1.0
 */
public class SerializeException extends RuntimeException {
    public SerializeException(String message) {
        super(message);
    }

    public SerializeException(String message, Throwable cause) {
        super(message, cause);
    }
}
