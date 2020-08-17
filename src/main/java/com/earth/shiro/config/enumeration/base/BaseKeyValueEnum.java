package com.earth.shiro.config.enumeration.base;


/**
 * Key 是 Integer
 * Value 是 String
 * 的枚举
 */
public interface BaseKeyValueEnum {

    Integer getKey();
    void setKey(Integer key);
    String getValue();
    void setValue(String value);

}
