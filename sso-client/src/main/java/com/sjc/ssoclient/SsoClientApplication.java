package com.sjc.ssoclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sjc.ssoclient.interceptor.SessionInterceptor;

//@SpringBootApplication
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})

public class SsoClientApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(SsoClientApplication.class, args);
	}

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**");
    }
}
