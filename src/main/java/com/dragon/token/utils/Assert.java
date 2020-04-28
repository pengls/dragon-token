package com.dragon.token.utils;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @ClassName: Assert
 * @Description: Assert
 * @Author: pengl
 * @Date: 2020/3/31 20:14
 * @Version V1.0
 */
public final class Assert {
    /**
     * @MethodName: isTrue
     * @Description: isTrue
     * @Author: pengl
     * @Date: 2020/3/31 20:18
     * @Version V1.0
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * @MethodName: isTrue
     * @Description: isTrue
     * @Author: pengl
     * @Date: 2020/3/31 20:19
     * @Version V1.0
     */
    public static void isTrue(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * @MethodName: isTrue
     * @Description: isTrue
     * @Author: pengl
     * @Date: 2020/3/31 20:18
     * @Version V1.0
     */
    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * @MethodName: isTrue
     * @Description: isTrue
     * @Author: pengl
     * @Date: 2020/3/31 20:19
     * @Version V1.0
     */
    public static void isFalse(boolean expression, Supplier<String> messageSupplier) {
        if (expression) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNull(Object object, Supplier<String> messageSupplier) {
        if (object != null) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * @MethodName: allNotBlank
     * @Description: assert all not blank
     * @Author: pengl
     * @Date: 2020/3/31 21:22
     * @Version V1.0
     */
    public static void allNotBlank(String message, CharSequence... css) {
        if (StrUtils.isAnyBlank(css)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void allNotBlank(Supplier<String> messageSupplier, CharSequence... css) {
        if (StrUtils.isAnyBlank(css)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * @MethodName: allBlank
     * @Description: assert all blank
     * @Author: pengl
     * @Date: 2020/3/31 21:22
     * @Version V1.0
     */
    public static void allBlank(String message, CharSequence... css) {
        if (!StrUtils.isAllBlank(css)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void allBlank(Supplier<String> messageSupplier, CharSequence... css) {
        if (!StrUtils.isAllBlank(css)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }


    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object, Supplier<String> messageSupplier) {
        if (object == null) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void notEmpty(String str, String message) {
        if (StrUtils.isEmpty(str)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(String str, Supplier<String> messageSupplier) {
        if (StrUtils.isEmpty(str)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void isEmpty(String str, String message) {
        if (StrUtils.isNotEmpty(str)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isEmpty(String str, Supplier<String> messageSupplier) {
        if (StrUtils.isNotEmpty(str)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void notBlank(String str, String message) {
        if (StrUtils.isBlank(str)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notBlank(String str, Supplier<String> messageSupplier) {
        if (StrUtils.isBlank(str)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void isBlank(String str, String message) {
        if (StrUtils.isNotBlank(str)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isBlank(String str, Supplier<String> messageSupplier) {
        if (StrUtils.isNotBlank(str)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void endWith(String str, String suffix, boolean ignoreCase, String message) {
        if (!StrUtils.endsWith(str, suffix, ignoreCase)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void endWith(String str, String suffix, boolean ignoreCase, Supplier<String> messageSupplier) {
        if (!StrUtils.endsWith(str, suffix, ignoreCase)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void startWith(String str, String prefix, boolean ignoreCase, String message) {
        if (!StrUtils.startsWith(str, prefix, ignoreCase)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void startWith(String str, String prefix, boolean ignoreCase, Supplier<String> messageSupplier) {
        if (!StrUtils.startsWith(str, prefix, ignoreCase)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }


    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        notNull(superType, "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new IllegalArgumentException(message);
        }

    }

    public static void isAssignable(Class<?> superType, Class<?> subType, Supplier<String> messageSupplier) {
        notNull(superType, "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, Supplier<String> messageSupplier) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void notEmpty(Object[] array, String message) {
        if (Objects.isEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(byte[] bytes, Supplier<String> messageSupplier) {
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void notEmpty(byte[] bytes, String message) {
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Object[] array, Supplier<String> messageSupplier) {
        if (Objects.isEmpty(array)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (Objects.isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Collection<?> collection, Supplier<String> messageSupplier) {
        if (Objects.isEmpty(collection)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void isEmpty(Object[] array, String message) {
        if (Objects.isNotEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isEmpty(Object[] array, Supplier<String> messageSupplier) {
        if (Objects.isNotEmpty(array)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void isEmpty(Collection<?> collection, String message) {
        if (Objects.isNotEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isEmpty(Collection<?> collection, Supplier<String> messageSupplier) {
        if (Objects.isNotEmpty(collection)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void isRangeIndex(int index, int size, String message) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isRangeIndex(int index, int size, Supplier<String> messageSupplier) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    private static String nullSafeGet(Supplier<String> messageSupplier) {
        return messageSupplier != null ? messageSupplier.get() : null;
    }
}
