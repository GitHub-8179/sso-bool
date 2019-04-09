<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>图书管理主页</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">


    <link href="<%=basePath %>static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath %>views/welcome/css/welcomeImage.css" rel="stylesheet" type="text/css"/>

</head>

<body >
<%-- 	<div id="admin" class="container-fluid col-md-12 col-sm-12 col-xs-12">
			<div class="row " background="<%=basePath %>views/welcome/image/welcomeImage.jpg" ;width="90%"; height="90%"; >
	
	欢迎
	</div>
	</div> --%>

						  <!--  <div class="jumbotron">
						        <h1>欢迎登陆页面！</h1>
						        <p>这是一个超大屏幕（Jumbotron）的实例。</p>
						        <p><a class="btn btn-primary btn-lg" role="button">学习更多</a></p>
						   </div> -->
<%-- <img alt="image" src="<%=basePath %>views/welcome/image/welcomeImage.jpg" />
 --%>  
</body>

  <!-- 全局js -->
	<script type="text/javascript" src= "<%=basePath %>static/js/jquery.min.js"></script>
	<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

 
 </html>