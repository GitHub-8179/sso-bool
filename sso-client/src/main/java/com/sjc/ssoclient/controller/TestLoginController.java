package com.sjc.ssoclient.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.SessionScope;


@RestController
@CrossOrigin
public class TestLoginController {

	@GetMapping("/testLogin")
	public String Test() {
		
		return "SSO";
	}
	@GetMapping("/")
	@CrossOrigin//跨域请求
	public String Test1() {
		
		return UUID.randomUUID()+"";
	}
	@RequestMapping("/exitLogin")
	@ResponseBody
	public String exitLogin(String token,HttpServletRequest request,Model model, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.removeAttribute(token);
//			session.setMaxInactiveInterval(200);
			session.invalidate();
		}
		
	       Cookie[] cookies = request.getCookies();
	        // 2.判断cookie数组是否为null
	        if (cookies != null) {
	            // 3.判断cookie中是否有用户名和密码
	            for (int i = 0; i < cookies.length; i++) {
	               token = cookies[i].getValue();
	        	   Cookie cookie = new Cookie("token","");
            	   response.addCookie(cookie);
	            }
	        }
	   return "ok";
	}
	
	
}
