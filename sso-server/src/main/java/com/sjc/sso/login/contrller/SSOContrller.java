package com.sjc.sso.login.contrller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjc.sso.login.entity.UserLoginEntity;
import com.sjc.sso.login.util.JwtUtil;


@Controller
@RequestMapping("/sso")
@CrossOrigin
public class SSOContrller {

	/**
	 * 判断是否有全局session，进行登录页面或访问页面重定向
	 * @param server
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String preLogin(String server,HttpServletRequest request,Model model) {
		  Cookie[] cookies = request.getCookies();
	       String token = null;
	        // 2.判断cookie数组是否为null
	        if (cookies != null) {
	            // 3.判断cookie中是否有用户名和密码
	            for (int i = 0; i < cookies.length; i++) {
	               token = cookies[i].getValue();
	               if(token != null ) {
	           			String sendUrl = "redirect:".concat(server).concat("?token=").concat(token);
	               }

	            }
	        }
	        
		HttpSession session = request.getSession(false);
		if(session == null) {
			model.addAttribute("url",server);
			return "index";
		}
		token = (String) request.getSession().getAttribute("token");
		if(token == null) {
			model.addAttribute("url",server);
			return "index";
		}else {
			String sendUrl = "redirect:".concat(server).concat("?token=").concat(token);
			return sendUrl;
		}
		
	}
	
	/**
	 * 校验用户账号密码
	 * @param userLoginEntity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	Map<String,Set> map = new HashMap<String,Set>();
	
	@RequestMapping("/verifyLgoin")
	public String verifyLgoin(UserLoginEntity userLoginEntity,HttpServletRequest request,Model model) {
		try {
			
			if("123".equals(userLoginEntity.getUser())) {
				
			}
			if("123".equals(userLoginEntity.getPass())) {
				
			}
			
			String token = JwtUtil.createJWT();
			Set<String> set = map.get(token);
			if(set == null) {
				set = new HashSet<String>();
			}
//			set.add( exitLoin);
			set.add( "http://www.ssoclient1.com:8080/exitLogin");
			map.put(token, set);
			System.out.println("生成："+token);
			
			request.getSession().setAttribute("token", token);
			
			return "redirect:".concat(userLoginEntity.getUrl()).concat("?token=").concat(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/verifyJWT")
	@ResponseBody
	public String verifyJWT(String token,String server,HttpServletRequest request,HttpServletResponse response) {

		boolean bool = JwtUtil.validJWT(token);
		if(bool) {
		
			Set<String> set = map.get(token);
			if(set != null) {
				for (String object : set) {
					object.equals("http://www.ssoclient1.com:8080/exitLogin");
				}
			}else {
				return "no";
			}
	        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";  
			System.out.println(basePath+":"+request.getRequestURI()+"校验jwt-在线用户数量："+map.size());
			
			return "ok";
		}else {
			return "no";
		}
	}
	
	
	@RequestMapping("/exitLogin")
	public String exitLogin(String token,String server,HttpServletRequest request,Model model) {

		HttpSession session = request.getSession(false);
		Set<String> set = map.get(token);
//		httpReq(token,"http://www.ssoclient1.com:8080/exitLogin");
		map.remove(token);
//		if(set != null) {
//			for (String url : set) {
//				System.out.println(url);
//				httpReq(token,url);
//			}
//			map.remove(token);
//		}
		System.out.println("在线用户数量："+map.size());

		if(session!=null) {
			session.invalidate();
		}
		model.addAttribute("server",server);
		String sendUrl = "redirect:http://www.ssoServer.com:8091/index";
		return sendUrl;
	}
	
	
	public String httpReq(String token,String server) {
    	InputStream inputStream = null;
    	OutputStream outputStream = null;
        try {
			URL url = new URL(server);
//			URL url = new URL(server+"?token="+token);
			//得到connection对象。
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//文本信息
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("Cookie", "token="+token);
			//设置urlConnection对象链接超时
			connection.setConnectTimeout(5000);
			//设置urlConnection对象获取数据超时
			connection.setReadTimeout(5000);
			connection.setDoOutput(true);   //需要输出
			connection.setDoInput(true);   //需要输入
			connection.setUseCaches(false);  //不允许缓存
//			connection.setRequestMethod("GET");

	        //设置请求方式
			connection.setRequestMethod("POST");
			String parameter=  "token="+token+"&server="+server;
			byte[] parameters =parameter.getBytes("UTF-8");
			outputStream = connection.getOutputStream();
			outputStream.write(parameters);
			//连接
			connection.connect();

			//获取本次网络请求的状态码
			int code=connection.getResponseCode();
			//如果本次返回的状态吗是200（成功）
			if (code==200) {
			     //调用urlConnection.getInputStream得到本次请求所返回的结果流
			    inputStream =connection.getInputStream();
			    //创建一个BufferedReader，去读取结果流
			    BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
			    String readLine;
			    StringBuffer buffer=new StringBuffer();
			    while ((readLine=reader.readLine())!=null) {
			        buffer.append(readLine);
			    }
			    return buffer.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(inputStream!=null)
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(outputStream!=null)
					outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		return "no";
        
    }
	
}
