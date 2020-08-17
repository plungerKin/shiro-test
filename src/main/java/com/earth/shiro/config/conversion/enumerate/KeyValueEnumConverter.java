package com.earth.shiro.config.conversion.enumerate;

import com.earth.shiro.config.enumeration.base.BaseKeyValueEnum;
import com.earth.shiro.config.util.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * BaseKeyValueEnum 枚举的Converter
 *
 * Created by tidus on 2017/11/3.
 */
public class KeyValueEnumConverter<T extends Enum<?> & BaseKeyValueEnum> implements Converter<String, T> {

    private final Class<T> enumType;

    public KeyValueEnumConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        return EnumUtils.getKeyValueEnumByKey(this.enumType, Integer.parseInt(source));
    }
}
