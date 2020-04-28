package com.dragon.token.compression.impl;

import com.dragon.token.compression.Compression;
import com.dragon.token.compression.CompressionCodec;
import com.dragon.token.compression.CompressionException;
import com.dragon.token.utils.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: CompressionProxy
 * @Description: 代理类：统计压缩率
 * @Author: pengl
 * @Date: 2020/4/4 20:40
 * @Version V1.0
 */
public class CompressionProxy implements CompressionCodec {
    private static final Logger LOG = LoggerFactory.getLogger(CompressionProxy.class);

    private CompressionCodec compressionCodec;

    public CompressionProxy(CompressionCodec compressionCodec) {
        this.compressionCodec = compressionCodec;
    }

    @Override
    public byte[] compress(byte[] data) throws CompressionException {
        long c = data.length;
        long b = System.currentTimeMillis();
        byte[] res = compressionCodec.compress(data);
        long e = System.currentTimeMillis();
        LOG.info("Compression Type : {}, before length: {}, after length: {}, ratio: {}%, times: {}ms",
                compressionCodec.current(), c, res.length, Objects.percentage(res.length, c, 2), (e - b));
        return res;
    }

    @Override
    public byte[] decompress(byte[] compressed) throws CompressionException {
        long b = System.currentTimeMillis();
        byte[] res = compressionCodec.decompress(compressed);
        long e = System.currentTimeMillis();
        LOG.info("Compression Type : {}, decompress times: {}ms", compressionCodec.current(), (e - b));
        return res;
    }

    @Override
    public Compression current() {
        return null;
    }
}
