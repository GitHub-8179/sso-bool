package com.sjc.ssoclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sjc.ssoclient.interceptor.SessionInterceptor;

//@SpringBootApplication
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})

public class SsoClientApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(SsoClientApplication.class, args);
	}

//	//拦截器
//	@Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor()).addPathPatterns("/**");
//    }
//	
//	//拦截器注入spring管理
//	@Bean
//	public SessionInterceptor authInterceptor(){
//	    return new SessionInterceptor();
//	}
}
