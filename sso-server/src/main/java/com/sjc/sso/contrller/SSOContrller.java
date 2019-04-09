package com.sjc.sso.contrller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjc.sso.entity.UserLoginEntity;

@Controller
@RequestMapping("/sso")
@CrossOrigin
public class SSOContrller {

	@RequestMapping("/lgoin")
	public String preLogin(String server,HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		System.out.println(server);
		StringBuffer s = request.getRequestURL() ;
		String login = (String) session.getAttribute("login");
		if(login == null) {
			model.addAttribute("url",server);
			return "index";
		}
		
		return server;
	}
	
	@RequestMapping("/verifyLgoin")
//	@ResponseBody
	public void verifyLgoin(UserLoginEntity userLoginEntity,HttpServletRequest request,HttpServletResponse response,Model model) {
		try {

		if("123".equals(userLoginEntity.getUser())) {
			
		}
		if("123".equals(userLoginEntity.getPass())) {
			
		}
		System.out.println("二次请求登陆验证"+userLoginEntity.getUrl());
		response.sendRedirect(userLoginEntity.getUrl()+"?token="+System.currentTimeMillis());
		} catch (IOException e) {
			e.printStackTrace();
		}
//		return userLoginEntity.getUrl();
//		return System.currentTimeMillis()+"";
	}
	
	
	
}
