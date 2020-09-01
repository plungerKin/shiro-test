package com.earth.shiro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@ComponentScan(basePackages={"com.earth"})
//加载特定spring配置
//@ImportResource(locations={"classpath:spring-hessian.xml"})
public class AppConfig {

    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域，这里所有域名都通过，如果需要指定域名，需要提到配置文件中配置。
        config.addAllowedOrigin(CorsConfiguration.ALL);
        //是否发送Cookie信息
        config.setAllowCredentials(true);
        //放行哪些原始域(请求方式)
        config.addAllowedMethod(CorsConfiguration.ALL);
        //放行哪些原始域(头部信息)
        config.addAllowedHeader(CorsConfiguration.ALL);
        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）

        //config.addExposedHeader(HttpHeaderConStant.X_TOTAL_COUNT);

        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();

        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }

}
