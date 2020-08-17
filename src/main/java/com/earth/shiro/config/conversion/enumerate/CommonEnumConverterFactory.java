package com.earth.shiro.config.conversion.enumerate;

import com.earth.shiro.config.enumeration.base.BaseCodeValueEnum;
import com.earth.shiro.config.enumeration.base.BaseKeyValueEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * Created by tidus on 2017/11/3.
 */
public class CommonEnumConverterFactory implements ConverterFactory<String, Enum> {

    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        if (BaseCodeValueEnum.class.isAssignableFrom(targetType)) {
            return new CodeValueEnumConverter(targetType);
        } else if (BaseKeyValueEnum.class.isAssignableFrom(targetType)) {
            return new KeyValueEnumConverter(targetType);
        } else {
            return new CommonEnumConverter(targetType);
        }
    }

}
