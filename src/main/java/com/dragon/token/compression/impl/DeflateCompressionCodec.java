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
import com.dragon.token.utils.Objects;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.InflaterOutputStream;

/**
 * @ClassName: DeflateCompressionCodec
 * @Description: jdk deflate压缩算法
 * @Author: pengl
 * @Date: 2020/4/1 20:22
 * @Version V1.0
 */
public class DeflateCompressionCodec extends AbstractCompressionCodec {

    private static final StreamWrapper WRAPPER = out -> new DeflaterOutputStream(out);

    @Override
    protected byte[] doCompress(byte[] payload) throws IOException {
        return writeAndClose(payload, WRAPPER);
    }

    @Override
    protected byte[] doDecompress(final byte[] compressed) throws IOException {
        try {
            return readAndClose(new InflaterInputStream(new ByteArrayInputStream(compressed)));
        } catch (IOException e1) {
            try {
                return doDecompressBackCompat(compressed);
            } catch (IOException e2) {
                throw e1;
            }
        }
    }

    byte[] doDecompressBackCompat(byte[] compressed) throws IOException {
        InflaterOutputStream inflaterOutputStream = null;
        ByteArrayOutputStream decompressedOutputStream = null;

        try {
            decompressedOutputStream = new ByteArrayOutputStream();
            inflaterOutputStream = new InflaterOutputStream(decompressedOutputStream);
            inflaterOutputStream.write(compressed);
            inflaterOutputStream.flush();
            return decompressedOutputStream.toByteArray();
        } finally {
            Objects.nullSafeClose(decompressedOutputStream, inflaterOutputStream);
        }
    }

    @Override
    public Compression current() {
        return Compression.DEFLATE;
    }
}
