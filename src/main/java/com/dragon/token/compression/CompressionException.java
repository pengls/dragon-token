package com.dragon.token.compression;

/**
 * @ClassName: CompressionException
 * @Description: CompressionException
 * @Author: pengl
 * @Date: 2020/4/1 20:15
 * @Version V1.0
 */
public class CompressionException extends RuntimeException {
    public CompressionException(String errorMsg) {
        super(errorMsg);
    }

    public CompressionException(String errorMsg, Throwable tr) {
        super(errorMsg, tr);
    }
}
