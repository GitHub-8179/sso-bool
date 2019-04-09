<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>admin信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">

<link href="<%=basePath %>static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>static/bootstrap-3.3.7-dist/css/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>static/css/sweetalert.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>static/formValidator/dist/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css"/>

</head>


<body  onload="initFunction()">

	<div id="admin" class="container-fluid col-md-12 col-sm-12 col-xs-12">
		<div class="row ">
			<div class="panel panel-default " id="cxtj">
		       <div class="panel-heading">
		            <form role="form" id="searchForm" class="form-inline">
		                <div class="form-group">
		                    <label for="sname1">账号名称:</label> 
		                    <input type="text" class="form-control" id="name" name="name" placeholder="请输入账号名称">
		                </div>
		                 <div class="form-group">
		                    <label for="sname1">生效日期:</label> 
		                    <input class="form-control layer-date"  id="effectTime" name="effectTime" type="dataFormat" >
<!-- 		                    <input readonly class="form-control layer-date" id="hello1" >-->		                </div>
		                <div class="form-group">
		                    <button type="button" id="searchBtn" class="btn btn-primary">查询</button>
		                </div>
		            </form>
		       </div>
		   	</div>		
		</div>
		<div class="row">
			<table id="docDateTable" class="table table-striped table-hover table-bordered " style="color: #000000">
				<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
			        <button type="button" class="btn btn-outline btn-default" data-toggle="modal" data-target="#myModal5" >
			            <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
			        </button>
<!-- 			        data-toggle="modal" data-target="#myModal5"
 -->			        <button type="button" class="btn btn-outline btn-default"  onclick='updateInfo()' >
			            <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
			        </button>
			        <button type="button" class="btn btn-outline btn-default" onclick='delInfo()'>
			            <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
			        </button>
		        </div>
		</table>
			
		</div>
	</div>
	
  
<div class="modal inmodal fade" id="myModal5" tabindex="-1" role="dialog"  aria-hidden="true">
   <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id='saveOrUpdateTest'></h4>
                <small class="font-bold">
            </div>
            <div class="modal-body">


                  <div class="ibox-content">
                        <form class="form-horizontal m-t" id="signupForm">
                        	<div class="form-group hidden">
                                <label class="col-sm-3 control-label">主键：</label>
                                <div class="col-sm-8">
                                    <input id="fadminId" name="adminId" class="form-control" placeholder="请输入主键" type="text">
                                </div>
                            </div>
                            <div class="form-group hidden">
                                <label class="col-sm-3 control-label">单位：</label>
                                <div class="col-sm-8">
                                    <input id="fdepartment" name="department" class="form-control" placeholder="请输入单位" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">账号名称：</label>
                                <div class="col-sm-8">
                                    <input id="fname" name="name" class="form-control" type="text" aria-required="true" class="valid">
<!--                                     <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span> </span> 
 -->                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">性别：</label>
                                <div class="col-sm-8">
	                                <label class="radio-inline">
								        <input type="radio" name="gender" id="gender1" value="1" checked> 男
								    </label>
								    <label class="radio-inline">
								        <input type="radio" name="gender" id="gender0"  value="0"> 女
								    </label>
							    </div>
                            </div>
                              <div class="form-group">
                                <label class="col-sm-3 control-label">是否生效：</label>
                                <div class="col-sm-8">
	                                <label class="radio-inline">
								        <input type="radio" name="isValid" id="isValid1" value="1" checked> 生效
								    </label>
								    <label class="radio-inline">
								        <input type="radio" name="isValid" id="isValid0"  value="0"> 失效
								    </label>
							    </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手机号：</label>
                                <div class="col-sm-8">
                                    <input id="fphone" name="phone" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">密码：</label>
                                <div class="col-sm-8">
                                    <input id="fpassword" name="password" class="form-control" type="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">确认密码：</label>
                                <div class="col-sm-8">
                                    <input id="confirm_password" name="confirm_password" class="form-control" type="password">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 请再次输入您的密码</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">E-mail：</label>
                                <div class="col-sm-8">
                                    <input id="femail" name="email" class="form-control" type="email">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">生效时间：</label>
                                <div class="col-sm-8">
                                    <input id="feffectTime" name="effectTime" class="form-control">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">失效时间：</label>
                                <div class="col-sm-8">
                                <!-- type="datetime-local" -->
                                    <input id="ffailTime" name="failTime" class="form-control" >
                                </div>
                            </div>
                           <!--  <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" class="checkbox" id="agree" name="agree"> 我已经认真阅读并同意《H+使用协议》
                                        </label>
                                    </div>
                                </div>
                            </div> -->
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="button" onclick="saveUpdate()">提交</button>
                                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    
                    
            </div>
          <!--   <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="add1">保存</button>
            </div> -->
        </div>
    </div>
</div>

 <div class="modal inmodal fade" id="myModal6" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">窗口标题</h4>
            </div>
            <div class="modal-body">
   					<div class="ibox-content">
                        <form class="form-horizontal m-t" id="commentForm">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓名：</label>
                                <div class="col-sm-8">
                                    <input id="cname" name="name" minlength="2" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">E-mail：</label>
                                <div class="col-sm-8">
                                    <input id="cemail" type="email" class="form-control" name="email" required="" aria-required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">网站：</label>
                                <div class="col-sm-8">
                                    <input id="curl" type="url" class="form-control" name="url">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">说明：</label>
                                <div class="col-sm-8">
                                    <textarea id="ccomment" name="comment" class="form-control" required="" aria-required="true"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">提交</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    
              </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
 
</body>

<script type="text/javascript" src= "<%=basePath %>static/js/jquery.min.js"></script>
<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap-table-zh-CN.min.js"></script> 
<script type="text/javascript" src= "<%=basePath %>static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src= "<%=basePath %>static/laydate/laydate.js"></script>
<script type="text/javascript" src= "<%=basePath %>static/js/sweetalert.min.js"></script>
<script type="text/javascript" src= "<%=basePath %>static/formValidator/dist/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src= "<%=basePath %>static/formValidator/dist/js/language/zh_CN.js"></script>

<script type="text/javascript" src= "<%=basePath %>views/manage/js/manage.js"></script>
<%--<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap-table-mobile.min.js"></script>--%>
<script type="text/javascript">
$("#signupForm").validate();

/* $('#exampleTableEvents').bootstrapTable({
    url: "/bootstrap_table_test.json",
    search: true,
    pagination: true,
    showRefresh: true,
    showToggle: true,
    showColumns: true,
    iconSize: 'outline',
    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
    pageNumber:1,                       //初始化加载第一页，默认第一页
    pageSize: 10,                       //每页的记录行数（*）
    pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
    toolbar: '#exampleTableEventsToolbar'
    ,
    icons: {
      refresh: 'glyphicon-repeat',
      toggle: 'glyphicon-list-alt',
      columns: 'glyphicon-list'
    } 
  }); */
  
  
/* 
$('#tb_departments').bootstrapTable({
    url: 'manage/selectByExample.do',         //请求后台的URL（*）
    method: 'get',                      //请求方式（*）
    toolbar: '#toolbar',                //工具按钮用哪个容器
    striped: true,                      //是否显示行间隔色
    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true,                   //是否显示分页（*）
    sortable: false,                     //是否启用排序
    sortOrder: "asc",                   //排序方式
    queryParams: oTableInit.queryParams,//传递参数（*）
    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
    pageNumber:1,                       //初始化加载第一页，默认第一页
    pageSize: 10,                       //每页的记录行数（*）
    pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
    search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
    strictSearch: true,
    showColumns: true,                  //是否显示所有的列
    showRefresh: true,                  //是否显示刷新按钮
    minimumCountColumns: 2,             //最少允许的列数
    clickToSelect: true,                //是否启用点击选中行
    height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
    columns: [{
        checkbox: true
    }, {
        field: 'Name',
        title: '部门名称'
    }, {
        field: 'ParentName',
        title: '上级部门'
    }, {
        field: 'Level',
        title: '部门级别'
    }, {
        field: 'Desc',
        title: '描述'
    }, ]
}); */
</script>
</html>