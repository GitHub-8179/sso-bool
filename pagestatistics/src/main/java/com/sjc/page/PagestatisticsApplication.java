package com.sjc.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//(scanBasePackages= {"com","priv.sjc"})
@ComponentScan(basePackages = {"com","priv.sjc"})

public class PagestatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagestatisticsApplication.class, args);
	}

}
