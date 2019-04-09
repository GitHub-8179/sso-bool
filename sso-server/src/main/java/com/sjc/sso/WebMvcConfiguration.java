package com.sjc.sso;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.Bean;

/**
 * WebMVC 配置.
 * <p>
 * 添加路径和页面的映射关系
 *
 * @author Mengday Zhang
 * @version 1.0
 * @since 2018/6/13
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");

        super.addViewControllers(registry);
    }
    
    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
    

    
//    //所有的WebMvcConfigureAdapter     设置默认启动页面，HelloController下的方法和这个二选一
//    @Bean//将组件注册到容器中
//    public WebMvcConfigurer webMvcConfigurer() {
//        WebMvcConfigurer configurer = new WebMvcConfigurer() {
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("login");
//                //它会找templates文件夹下的文件是因为模版映射的关系,templates为Spring Boot默认jar包使用嵌入式的Tomcat
//                registry.addViewController("/index.html").setViewName("login");
//                registry.addViewController("/main.html").setViewName("dashboard");
//            }
//            //注册拦截器
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                //静态资源在springboot2.0以前已经做好映射，不用管                      /**指任意范围都通过拦截
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/index.html","/","/user/login","/asserts/**","/webjars/**");
////                .excludePathPatterns代表这些请求不过滤     asserts为resources下static下的文件夹，webjars则是maven导入的一些前端框架
//            }
//        };
//        return configurer;
//    }
// 
//    /**
//     * @Description:配置国际化多语言
//     * @Param: []
//     * @return: org.springframework.web.servlet.LocaleResolver
//     * @Author: hw
//     * @Date: 2019/1/7
//     */
//    @Bean
//    public LocaleResolver localeResolver() {
//        return new MyLocaleResolver();
//    }
    
    
  //1.这个为解决中文乱码
  	@Bean
  	public HttpMessageConverter<String> responseBodyConverter() {
  	    StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
  	    return converter;
  	}
  	
  	
  	//2.1：解决中文乱码后，返回json时可能会出现No converter found for return value of type: xxxx
  	//或这个：Could not find acceptable representation
  	//解决此问题如下
  	public ObjectMapper getObjectMapper() {
  		return new ObjectMapper();
  	}
  	
  	//2.2：解决No converter found for return value of type: xxxx
  	public MappingJackson2HttpMessageConverter messageConverter() {
  		MappingJackson2HttpMessageConverter converter=new MappingJackson2HttpMessageConverter();
  		converter.setObjectMapper(getObjectMapper());
  		return converter;
  	}
  	


  	@Override
  	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
  	    super.configureMessageConverters(converters);
  	    //解决中文乱码
  	    converters.add(responseBodyConverter());
  	    
  	    //解决： 添加解决中文乱码后的配置之后，返回json数据直接报错 500：no convertter for return value of type
  	    //或这个：Could not find acceptable representation
  	    converters.add(messageConverter());
  	}

  	
}
