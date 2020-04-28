package com.dragon.token.serialize.impl;

import com.dragon.token.serialize.ISerializable;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.Pool;

/**
 * @ClassName: KryoSerialize
 * @Description: Kryo序列化
 * @Author: pengl
 * @Date: 2020/4/4 20:06
 * @Version V1.0
 */
public class KryoSerialize implements ISerializable {
    private static Pool<Kryo> mKryoPool = new Pool<Kryo>(true, false, 8) {
        @Override
        protected Kryo create() {
            Kryo kryo = new Kryo();
            kryo.setRegistrationRequired(false);
            kryo.setReferences(false);
            // Configure the Kryo instance.
            return kryo;
        }
    };
    private static Pool<Output> mOutputPool = new Pool<Output>(true, false, 16) {
        @Override
        protected Output create() {
            return new Output(1024, -1);
        }
    };
    private static Pool<Input> mInputPool = new Pool<Input>(true, false, 16) {
        @Override
        protected Input create() {
            return new Input(1024);
        }
    };

    @Override
    public byte[] serialize(Object obj) {
        Kryo kryo = mKryoPool.obtain();
        Output output = mOutputPool.obtain();
        try {
            output.reset();
            kryo.writeObject(output, obj);
            return output.getBuffer();
        } finally {
            mKryoPool.free(kryo);
            mOutputPool.free(output);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> pvClass) {
        Kryo kryo = mKryoPool.obtain();
        Input input = mInputPool.obtain();
        try {
            input.setBuffer(data);
            return kryo.readObject(input, pvClass);
        } finally {
            mKryoPool.free(kryo);
            mInputPool.free(input);
        }
    }
}
