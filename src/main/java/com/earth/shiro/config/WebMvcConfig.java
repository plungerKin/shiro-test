package com.earth.shiro.config;

import com.earth.shiro.config.conversion.date.DateConverterFactory;
import com.earth.shiro.config.conversion.enumerate.CommonEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author tidus
 * @Date 2019-06-13
 * @Project accompanying-reading-server
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new DateConverterFactory());
        registry.addConverterFactory(new CommonEnumConverterFactory());
    }
}
