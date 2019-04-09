package com.sjc.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.support.json.JSONUtils;
import com.sjc.login.service.ILogin;

@CrossOrigin//跨域请求
@RestController
@RequestMapping("/login")
public class LoginController {
	
	
	@Autowired
	private ILogin loginImpl;
	
	@GetMapping("/getToken")
    public Map getData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map map = new HashMap();
		try {
			response.setHeader("Access-Control-Allow-Origin","*");  
			 response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); 
		     response.setHeader("Access-Control-Allow-Methods","GET"); //方法根据自己的方式定义
			map.put("code", 200);
			map.put("key", UUID.randomUUID());
//			Map dataList = loginImpl.getToken();
			System.out.println("dddddddddddddddddddddd");

		} catch (Exception e) {
			map.put("msg", false);
		}
    	
		return map;
	}

	
	@GetMapping("/getToken1")
    public String getData1(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map map = new HashMap();
		try {
			map.put("callback", UUID.randomUUID()+"");
			System.out.println("sssssssssssssssssssssssssss");

		} catch (Exception e) {
			map.put("msg", false);
		}
		String dd = JSONUtils.toJSONString(map);
	    return "success_jsonpCallback("+ dd +")";

	}


}
