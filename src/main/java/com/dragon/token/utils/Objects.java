package com.dragon.token.utils;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Map;

/**
 * @ClassName: Objects
 * @Description: Objects
 * @Author: pengl
 * @Date: 2020/4/1 20:31
 * @Version V1.0
 */
public class Objects {
    /**
     * @MethodName: nullSafeClose
     * @Description: 关闭 resource
     * @Author: pengl
     * @Date: 2020/4/1 20:32
     * @Version V1.0
     */
    public static void nullSafeClose(Closeable... closeables) {
        if (closeables == null) {
            return;
        }

        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {

                }
            }
        }
    }

    /**
     * @MethodName: percentage
     * @Description: 百分比计算
     * @Author: pengl
     * @Date: 2020/4/4 22:16
     * @Version V1.0
     */
    public static String percentage(long a, long b, int decimal){
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(decimal);
        return format.format((float) a / (float) b * 100);
    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof CharSequence) {
            return ((CharSequence)object).length() == 0;
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        } else if (object instanceof Collection) {
            return ((Collection)object).isEmpty();
        } else {
            return object instanceof Map ? ((Map)object).isEmpty() : false;
        }
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }
}
