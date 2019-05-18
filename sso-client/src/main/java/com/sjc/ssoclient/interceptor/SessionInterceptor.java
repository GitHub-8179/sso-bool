package com.sjc.ssoclient.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class SessionInterceptor extends HandlerInterceptorAdapter{

	@Value("${sso.server.token}")
	private String verifyJWT;
	
	@Value("${sso.server.url}")
	private String server;
	
	@Value("${sso.client.exitLogin}")
	private String exitLogin;
	
	@Value("${sso.client.index}")
	private String index;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        StringBuffer s = request.getRequestURL() ;
//    	  request.getRequestURL().toString()
//        System.out.println(s);
//        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";  
        //登录不做拦截
//        if(request.getRequestURI().equals("/exitLogin"))
//        {
//            return true;
//        }
    	
//    	HttpSession session = request.getSession(false);
//    	if(session != null&&session.getAttribute("login")!=null&&session.getAttribute("login").equals("login")) {
//    		
//    		return true;
//    	}
        
        Cookie[] cookies = request.getCookies();
        String token = null;
        // 2.判断cookie数组是否为null
        if (cookies != null) {
            // 3.判断cookie中是否有用户名和密码
            for (int i = 0; i < cookies.length; i++) {
               token = cookies[i].getValue();
               if(token != null ) {
                   String resp = httpReq(token);
            	   if("ok".equals(resp)) {
                	   Cookie cookie = new Cookie("token",token);
                	   response.addCookie(cookie);
                    	return true;
                    }
               }

            }
        }
        //验证session是否存在
        token = request.getParameter("token");
        if(token != null)
        {
           String resp = httpReq(token);
           if("ok".equals(resp)) {
        	   Cookie cookie = new Cookie("token",token);
        	   response.addCookie(cookie);

//        	   request.getSession().setAttribute("login", "login");
            	return true;
            }
        }
        
        response.sendRedirect(server.concat("?server=").concat(index));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
    
    public String httpReq(String token) {
    	InputStream inputStream = null;
    	OutputStream outputStream = null;
        try {
			URL url = new URL(verifyJWT);
			//得到connection对象。
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//文本信息
			connection.setRequestProperty("Charset", "UTF-8");
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
			String parameter=  "token="+token+"&server="+exitLogin;
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
