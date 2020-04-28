package com.dragon.token.serialize.impl;

import com.dragon.token.serialize.SerializeException;
import com.dragon.token.serialize.ISerializable;

import java.io.*;

/**
 * @ClassName: JdkSerialize
 * @Description: JdkSerialize use object stream
 * @Author: pengl
 * @Date: 2020/4/1 21:15
 * @Version V1.0
 */
public class JdkSerialize implements ISerializable {

    @Override
    public byte[] serialize(Object obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            oos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new SerializeException(e.getMessage(), e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> pvClass) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        try (ObjectInputStream oos = new ObjectInputStream(bais)) {
            return (T) oos.readObject();
        } catch (IOException e) {
            throw new SerializeException(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new SerializeException(e.getMessage(), e);
        }
    }
}
