package com.sjc.page.kaptcha;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import priv.sjc.base.aspect.SysLog;

@Controller
@RequestMapping("/code")
@Api(value="/code", tags="验证码模块")
@Validated
//@Valid
public class CodeController {
    
	@Autowired
    private Producer captchaProducer = null;
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    
	@ApiOperation(value = "生成验证码图片", notes = "查询数据库中某个的学生信息")
    @RequestMapping("/kaptcha")
	@SysLog
    public void getKaptchaImage(@RequestParam @NotEmpty(message="ces") String uuid,HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ServletOutputStream out=null;
//		 ,BindingResult result
//		   if(result.hasErrors()){
//	            for (ObjectError error : result.getAllErrors()) {
//	                System.out.println(error.getDefaultMessage());
//	            }
//	        }
		try {
	        HttpSession session = request.getSession();
//	        response.setDateHeader("Expires", 0);
//	        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//	        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//	        response.setHeader("Pragma", "no-cache");
//	        response.setContentType("image/jpeg");
	        
	        response.setHeader("Cache-Control", "no-store, no-cache");
			response.setContentType("image/jpeg");
	        //生成验证码
	        String capText = defaultKaptcha.createText();
	//        String capText = captchaProducer.createText();
	        BufferedImage bi = captchaProducer.createImage(capText);
	
	        System.out.println(capText);
	        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
	        //向客户端写出
	//        BufferedImage bi = defaultKaptcha.createImage(capText);
	        out = response.getOutputStream();
	        ImageIO.write(bi, "jpg", out);
        
            out.flush();
        } finally {
        	if(out != null)
            out.close();
        }
    }
}
