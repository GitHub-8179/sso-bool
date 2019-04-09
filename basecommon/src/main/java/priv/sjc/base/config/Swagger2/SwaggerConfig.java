package priv.sjc.base.config.Swagger2;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.DispatcherServlet;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 访问地址：http://localhost:端口号/项目名称/swagger-ui.html 
 * 引入第三方地址
 *  <dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>swagger-bootstrap-ui</artifactId>
    <version>1.6</version>
</dependency>
http://localhost:5680/zxmall/doc.html

 * @author 20162
 *
 */
@Configuration
@EnableSwagger2
@Slf4j
@ComponentScan(basePackages = {"com.jsc"})
public class SwaggerConfig {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	 @Value("${swagger.show}")
	 private boolean swaggerShow;


	 @Bean
	    public Docket createRestApi() {
		 

            logger.info("任务准备执行，任务名称：{}", "Swagger2 API");

            
		 //自定义异常信息
	        ArrayList<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>() {{
	               add(new ResponseMessageBuilder().code(200).message("成功").build());
	               add(new ResponseMessageBuilder().code(400).message("请求参数错误").responseModel(new ModelRef("Error")).build());
	               add(new ResponseMessageBuilder().code(401).message("权限认证失败").responseModel(new ModelRef("Error")).build());
	               add(new ResponseMessageBuilder().code(403).message("请求资源不可用").responseModel(new ModelRef("Error")).build());
	               add(new ResponseMessageBuilder().code(404).message("请求资源不存在").responseModel(new ModelRef("Error")).build());
	               add(new ResponseMessageBuilder().code(409).message("请求资源冲突").responseModel(new ModelRef("Error")).build());
	               add(new ResponseMessageBuilder().code(415).message("请求格式错误").responseModel(new ModelRef("Error")).build());
	               add(new ResponseMessageBuilder().code(423).message("请求资源被锁定").responseModel(new ModelRef("Error")).build());
	               add(new ResponseMessageBuilder().code(500).message("服务器内部错误").responseModel(new ModelRef("Error")).build());
	               add(new ResponseMessageBuilder().code(501).message("请求方法不存在").responseModel(new ModelRef("Error")).build());
	               add(new ResponseMessageBuilder().code(503).message("服务暂时不可用").responseModel(new ModelRef("Error")).build());
	               add(new ResponseMessageBuilder().code(-1).message("未知异常").responseModel(new ModelRef("Error")).build());
	           }};
	        return new Docket(DocumentationType.SWAGGER_2)
	        		.enable(swaggerShow)
	                .apiInfo(apiInfo())
	                .select()
	                //为当前包路径
	                .apis(RequestHandlerSelectors.basePackage("com.sjc"))
//	                .apis(RequestHandlerSelectors.basePackage("com"))
	                .paths(PathSelectors.any())
	                .build()
//	                .useDefaultResponseMessages(false)
	                .globalResponseMessage(RequestMethod.GET, responseMessages)
	                .globalResponseMessage(RequestMethod.POST, responseMessages)
	                .globalResponseMessage(RequestMethod.PUT, responseMessages)
	                .globalResponseMessage(RequestMethod.DELETE, responseMessages);
	    }
	    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                //页面标题
	                .title("Spring Boot 测试使用 Swagger2 构建RESTful API")
	                //创建人
	                //.contact(new Contact("帅呆了", "url", "email"))
	                .contact(new Contact("MarryFeng", "http://www.baidu.com", "http://localhost/swagger-ui.html"))
	                //版本号
	                .version("1.0")
	                //服务条款网址
	                //.termsOfServiceUrl("http://blog.csdn.net/forezp")
	                //描述
	                .description("API 描述")
	                .build();
	    }
	    
}
