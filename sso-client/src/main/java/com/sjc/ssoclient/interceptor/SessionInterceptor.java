package com.sjc.ssoclient.interceptor;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class SessionInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        StringBuffer s = request.getRequestURL() ;
//        System.out.println(s);
//        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";  
        //登录不做拦截
//        if(request.getRequestURI().equals("/userbg/login") || request.getRequestURI().equals("/user/login_view"))
//        {
//            return true;
//        }
        //验证session是否存在
        Object obj = request.getSession().getAttribute("_session_user");
        if(request.getParameter("token")!=null) {
        	return true;
        }
        if(obj == null)
        {
            response.sendRedirect("http://www.ssoServer.com:8091/sso/lgoin?server="+request.getRequestURL().toString());
//            URL url = new URL("http://www.ssoServer.com:8091/sso/lgoin?server="+request.getRequestURL().toString());
//            //得到connection对象。
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            //设置请求方式
//            connection.setRequestMethod("GET");
//            //连接
//            connection.connect();

            return false;
        }
        
//        String token = request.getParameter("token");
//        if(token == null) {
//        	String reqUrl = "http://www.sso.com:8090/checkToken";
//        	
//        }
        System.out.println("ssssssssssssssssssssss");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
	
}
