<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">

<link href="<%=basePath %>static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>static/bootstrap-3.3.7-dist/css/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>

</head>


<body>
					
	<div class="panel panel-default">
    <div class="panel-heading">
        查询条件
    </div>
    <div class="panel-body form-group" style="margin-bottom:0px;">
        <label class="col-sm-1 control-label" style="text-align: right; margin-top:5px">姓名：</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="Name" id="search_name"/>
        </div>
        <label class="col-sm-1 control-label" style="text-align: right; margin-top:5px">手机号：</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="Name" id="search_tel"/>
        </div>
        <div class="col-sm-1 col-sm-offset-4">
            <button class="btn btn-primary" id="search_btn">查询</button>
        </div>
     </div>
</div>

 <table id="mytab" data-toggle="table"  data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-icon-size="outline" data-search="true">

<div id="toolbar" class="btn-group pull-right" style="margin-right: 20px;">
     <button id="btn_edit" type="button" class="btn btn-default" style="display: none; border-radius: 0">
         <span class="glyphicon glyphicon-pencil" aria-hidden="true" ></span>修改
     </button>
      <button id="btn_delete" type="button" class="btn btn-default" style="display: none;">
          <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
      </button>
      <button id="btn_add" type="button" class="btn btn-default">
          <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
      </button>
 </div>
 
 
 
</body>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>

<script type="text/javascript" src= "<%=basePath %>static/js/jquery.min.js"></script>
<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
 <script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap-table.min.js"></script>
<%--  <script type="text/javascript" src= "<%=basePath %>static/js/content.js"></script>
 --%> 
<%--<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap-table-mobile.min.js"></script>
<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap-table-zh-CN.min.js"></script> --%>
<script type="text/javascript">
$('#mytab').bootstrapTable({
    method: 'post',
    contentType: "application/x-www-form-urlencoded",//当请求方法为post的时候,必须要有！！！！
	url: 'manage/selectByExample.do',   
    striped: true, //是否显示行间隔色
    pageNumber: 1, //初始化加载第一页
    pagination:true,//是否分页
    paginationPreText:'上一页',
    paginationNextText:'下一页',
    sidePagination:'server',//server:服务器端分页|client：前端分页
    pageSize:10,//单页记录数
    pageList:[5,10,20,30],//可选择单页记录数
    showRefresh:true,//刷新按钮
    queryParams : function (params) {//上传服务器的参数
        var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
            limit : params.limit, // 每页显示数量
            offset : params.offset, // SQL语句起始索引
            //page: (params.offset / params.limit) + 1,   //当前页码
            
            Name:$('#search_name').val(),
            Tel:$('#search_tel').val()
        };
        return temp;
    },
    columns:[
        {
            title:'登录名',
            field:'loginName',
            sortable:true
        },
        {
            title:'姓名',
            field:'name',
            sortable:true
        },
        {
            title:'手机号',
            field:'tel',
        },
        {
            title:'性别',
            field:'sex',
            formatter: formatSex,//对返回的数据进行处理再显示
        }
    ]
})
 
//value代表该列的值，row代表当前对象
function formatSex(value,row,index){
	return value == 1 ? "男" : "女";
	//或者 return row.sex == 1 ? "男" : "女";
}
 
 //查询按钮事件
$('#search_btn').click(function(){
    $('#mytab').bootstrapTable('refresh', {url: ROOT+'user/getUserListPage'});
})
</script>
</html>