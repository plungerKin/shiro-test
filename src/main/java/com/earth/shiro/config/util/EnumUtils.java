package com.earth.shiro.config.util;


import com.earth.shiro.config.enumeration.base.BaseCodeValueEnum;
import com.earth.shiro.config.enumeration.base.BaseKeyEnum;
import com.earth.shiro.config.enumeration.base.BaseKeyValueEnum;

/**
 * 作用是让Mysql在加载数据到entity 或者 将entity写入到数据时 会自动匹配枚举类型
 * 注意：需要在 Mybatis 的配置文件中指定每个枚举的TypeHandler
 * Created by tidus on 2018/5/28.
 */
@SuppressWarnings("Duplicates")
public class EnumUtils {

    public static <E extends Enum<?> & BaseKeyEnum> E getKeyEnumByKey(Class<E> enumClass, Integer key) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getKey().equals(key)) {
                return e;
            }
        }
        return null;
    }
    public static <E extends Enum<?> & BaseCodeValueEnum> E getCodeValueEnumByCode(Class<E> enumClass, String code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    public static <E extends Enum<?> & BaseKeyValueEnum> E getKeyValueEnumByKey(Class<E> enumClass, Integer key) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getKey().equals(key)) {
                return e;
            }
        }
        return null;
    }
}
