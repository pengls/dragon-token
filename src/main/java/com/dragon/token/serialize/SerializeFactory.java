package com.dragon.token.serialize;

import com.dragon.token.serialize.impl.FastJsonSerialize;
import com.dragon.token.serialize.impl.JdkSerialize;
import com.dragon.token.serialize.impl.KryoSerialize;

/**
 * @ClassName: SerializeFactory
 * @Description: SerializeFactory
 * @Author: pengl
 * @Date: 2020/4/1 21:33
 * @Version V1.0
 */
public abstract class SerializeFactory {
    private static final ISerializable JDK_SERIALIZE = new JdkSerialize();
    private static final ISerializable KRYO_SERIALIZE = new KryoSerialize();
    private static final ISerializable FASTJSON_SERIALIZE = new FastJsonSerialize();

    public static final ISerializable getSerializable(SerializeType serializeType) {
        switch (serializeType) {
            case KRYO:
                return KRYO_SERIALIZE;
            case FAST_JSON:
                return FASTJSON_SERIALIZE;
            case JDK:
            default:
                return JDK_SERIALIZE;
        }
    }
}
