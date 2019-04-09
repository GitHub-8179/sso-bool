<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
    <link href="<%=basePath %>static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <link href="<%=basePath %>views/welcome/css/welcome.css" rel="stylesheet" type="text/css"/>
<body>

    <div class="container-fluid" >
		<div class="row"  >
	<div class="panel panel-primary">
    	<div class="panel-heading">
    		<div>
    		欢迎登录图书管理系统
<%-- 	        	<h3>欢迎登录图书管理系统</h3>
	        	<a href="<%=basePath%>login.jsp" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
 --%>    		</div>
    	</div>
    	
    	<div class="panel-body">
    	<!-- 树 -->
    	<div class="row">
    		<div class='col-md-2 col-sm-2 col-xs-2'>
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>用户权限</h5>
	                    <div class="ibox-tools">
	                        <a class="collapse-link">
	                            <i class="fa fa-chevron-up"></i>
	                        </a>
	                        <a class="dropdown-toggle" data-toggle="dropdown" href="buttons.html#">
	                            <i class="fa fa-wrench"></i>
	                        </a>
	                        <ul class="dropdown-menu dropdown-user">
	                            <li><a href="buttons.html#">选项1</a></li>
	                            <li><a href="buttons.html#">选项2</a></li>
	                        </ul>
	                        <a class="close-link">
	                            <i class="fa fa-times"></i>
	                        </a>
	                    </div>
	                </div>
	                <div class="ibox-content">
	                   <div id="treeview" class="test"></div>
	                   <!--  <div class="col-sm-6">
	                        <h5>事件输出：</h5>
	                        <hr>
	                        <div id="event_output"></div>
	                    </div>
	                    <div class="clearfix"></div>
	                </div> -->
	            </div>
			</div>
			</div>
    	
		    	<div class="panel-heading" id="topText">
				    <ul id="myTab" class="nav nav-tabs">
<!-- 		    			<li class="active"><a href="#home" data-toggle="tab">首页</a></li>
 -->						<li><a href="#ios" data-toggle="tab" > 首页</a></li>
				    </ul>
		    	</div> 
		    	<div>
		    	
		    	</div>
	    	<div class="panel-body" role='tablist' id="event_output"  background="../welcome/image/welcomeImage.jpg">
	 			<div id="myTabContent" class="tab-content" >
		 			<!-- <div class="tab-pane fade in active" id="home">
			 			<div class="col-sm-8">
			 				<iframe SRC='../welcome/welcomeImage.jsp' ></iframe> "
			 			</div>
					 </div> -->
		 			
					
					 <div class="tab-pane fade " id="ios"  >
					 	<div></div>
					 </div>
    
	 			</div>
	    	</div>
	
    	<!-- </div>
    		<div class="panel-footer text-center" >图书馆版权所有</div>
    	</div> -->
	</div>
	
		</div>
    </div>
    
    
    </div>
    	</div>
    		<div class="panel-footer text-center" >图书馆版权所有</div>
	
</body>

<!-- 其他使用 -->
  <!-- 全局js -->
	<script type="text/javascript" src= "<%=basePath %>static/js/jquery.min.js"></script>
	<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src= "<%=basePath %>views/welcome/js/bootstrap-treeview.js"></script>
<%-- 	<script type="text/javascript" src= "<%=basePath %>views/welcome/js/treeview-demo.js"></script>
 --%>	<script type="text/javascript" src= "<%=basePath %>views/welcome/js/welcome.js"></script>

</html>