package com.earth.shiro.config.conversion.date;

import com.earth.shiro.config.util.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * Created by tidus on 2017/11/29.
 */
public class DateConverter<T extends Date> implements Converter<String, T> {


    @Override
    public T convert(String source) {
        Date date = DateUtils.parseDate(source);
        return (T) date;
    }
}
