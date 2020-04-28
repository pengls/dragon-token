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
package com.dragon.token.compression;

import com.dragon.token.utils.Assert;
import com.dragon.token.utils.Objects;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @ClassName: AbstractCompressionCodec
 * @Description: abstract
 * @Author: pengl
 * @Date: 2020/4/1 20:23
 * @Version V1.0
 */
public abstract class AbstractCompressionCodec implements CompressionCodec {

    protected abstract byte[] doCompress(byte[] data) throws IOException;

    @FunctionalInterface
    public interface StreamWrapper {
        OutputStream wrap(OutputStream out) throws IOException;
    }

    protected byte[] readAndClose(InputStream input) throws IOException {
        byte[] buffer = new byte[512];
        ByteArrayOutputStream out = new ByteArrayOutputStream(buffer.length);
        int read;
        try {
            read = input.read(buffer);
            while (read != -1) {
                out.write(buffer, 0, read);
                read = input.read(buffer);
            }
        } finally {
            Objects.nullSafeClose(input);
        }
        return out.toByteArray();
    }

    protected byte[] writeAndClose(byte[] payload, StreamWrapper wrapper) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(512);
        OutputStream compressionStream = wrapper.wrap(outputStream);
        try {
            compressionStream.write(payload);
            compressionStream.flush();
        } finally {
            Objects.nullSafeClose(compressionStream);
        }
        return outputStream.toByteArray();
    }

    @Override
    public final byte[] compress(byte[] data) {
        Assert.notNull(data, "payload cannot be null.");

        try {
            return doCompress(data);
        } catch (IOException e) {
            throw new CompressionException("Unable to compress payload.", e);
        }
    }


    @Override
    public final byte[] decompress(byte[] compressed) {
        Assert.notNull(compressed, "compressed bytes cannot be null.");

        try {
            return doDecompress(compressed);
        } catch (IOException e) {
            throw new CompressionException("Unable to decompress bytes.", e);
        }
    }

    protected abstract byte[] doDecompress(byte[] compressed) throws IOException;
}
