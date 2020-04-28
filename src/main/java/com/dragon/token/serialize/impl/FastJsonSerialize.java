package com.dragon.token.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.dragon.token.serialize.ISerializable;

/**
 * @ClassName: FastJsonSerialize
 * @Description: alibaba fastjson
 * @Author: pengl
 * @Date: 2020/4/5 13:04
 * @Version V1.0
 */
public class FastJsonSerialize implements ISerializable {
    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> pvClass) {
        return JSON.parseObject(data, pvClass);
    }
}
