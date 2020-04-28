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

    public static final ISerializable getSerializable(SerializeType serializeType){
        switch (serializeType){
            case JDK:
                return new JdkSerialize();
            case KRYO:
                return new KryoSerialize();
            case FAST_JSON:
                return new FastJsonSerialize();
            default:
                return new JdkSerialize();
        }
    }
}
