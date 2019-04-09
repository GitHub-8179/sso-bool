package priv.sjc.base.config.mvc;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	/**
	 * 配置后缀请求
	 * /basecommon/src/main/java/priv/sjc/base/config/kaptcha/KaptchaConfig.java
	 */
	@Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
//        //开启路径后缀匹配
//        configurer.setUseRegisteredSuffixPatternMatch(true);
        //setUseSuffixPatternMatch 后缀模式匹配
        configurer.setUseSuffixPatternMatch(true);
        //setUseTrailingSlashMatch 自动后缀路径模式匹配
        configurer.setUseTrailingSlashMatch(true);
    }
	
	/**
	 * 校验数据
	 * /basecommon/src/main/java/priv/sjc/base/config/kaptcha/KaptchaConfig.java
	 * @return
	 */
    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }
	

}
