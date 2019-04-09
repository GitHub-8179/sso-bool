package com.sjc.login.kaptcha.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
 
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
//        //开启路径后缀匹配
//        configurer.setUseRegisteredSuffixPatternMatch(true);
        
        //setUseSuffixPatternMatch 后缀模式匹配
        configurer.setUseSuffixPatternMatch(true);
        //setUseTrailingSlashMatch 自动后缀路径模式匹配
        configurer.setUseTrailingSlashMatch(true);

    }
}
