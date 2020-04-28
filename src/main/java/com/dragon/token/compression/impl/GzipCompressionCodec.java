/*
 * Copyright (C) 2015 jsonwebtoken.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dragon.token.compression.impl;

import com.dragon.token.compression.AbstractCompressionCodec;
import com.dragon.token.compression.Compression;
import com.dragon.token.compression.CompressionCodec;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @ClassName: GzipCompressionCodec
 * @Description: jdk gzip压缩算法
 * @Author: pengl
 * @Date: 2020/4/4 9:13
 * @Version V1.0
 */
public class GzipCompressionCodec extends AbstractCompressionCodec implements CompressionCodec {

    private static final StreamWrapper WRAPPER = out -> new GZIPOutputStream(out);

    @Override
    protected byte[] doCompress(byte[] payload) throws IOException {
        return writeAndClose(payload, WRAPPER);
    }

    @Override
    protected byte[] doDecompress(byte[] compressed) throws IOException {
        return readAndClose(new GZIPInputStream(new ByteArrayInputStream(compressed)));
    }

    @Override
    public Compression current() {
        return Compression.GZIP;
    }
}
