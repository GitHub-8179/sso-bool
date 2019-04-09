//https://www.cnblogs.com/landeanfen/p/5005367.html

//初始化Table
        $('#tb_order').bootstrapTable({
            url: '/TableStyle/GetOrder',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            smartDisplay:true,//设置为 true 是程序自动判断显示分页信息和 card 视图。
            checkboxHeader:false,				//标题选择框是否显示
            //toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,					//设置为 true启用 全匹配搜索，否则为模糊搜索
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            rowStyle: function (row, index) {
                //这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
                var strclass = "";
                if (row.ORDER_STATUS == "待排产") {
                    strclass = 'success';//还有一个active
                }
                else if (row.ORDER_STATUS == "已删除") {
                    strclass = 'danger';
                }
                else {
                    return {};
                }
                return { classes: strclass }
            },
            columns: [{
                checkbox: true
            }, {
                field: 'ORDER_NO',
                title: '订单编号'
            }, {
                field: 'ORDER_TYPE',
                title: '订单类型'
            }, {
                field: 'ORDER_STATUS',
                title: '订单状态'
            }, {
                field: 'REMARK',
                title: '备注'
            }, ]
        });
        
        
        
        
        
        
        
        //隐藏
        $('#tb_departments').bootstrapTable('hideColumn', 'ORG_ID');
        
        
        $('#tb_departments').bootstrapTable({
            url: '/Editable/GetDepartment',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "/Editable/Edit",
                    data: { strJson: JSON.stringify(row) },
                    success: function (data, status) {
                        if (status == "success") {
                            alert("编辑成功");
                        }
                    },
                    error: function () {
                        alert("Error");
                    },
                    complete: function () {

                    }

                });
            }
        });
        
        
        
        
        
       //laydata的使用
      	//=========================================================================

        laydate.render({
            //event: 'focus' //响应事件。如果没有传入event，则按照默认的click
     	   elem: '#effectTime', //需显示日期的元素选择器
     	   event: 'click', //触发事件
     	   format: 'YYYY-MM-DD hh:mm:ss', //日期格式
     	   showBottom: false, //显示底部栏
     	   istime: false, //是否开启时间选择
     	   isclear: false, //是否显示清空
     	   istoday: false, //是否显示今天
     	   issure: false,  //是否显示确认
     	   festival: false, //是否显示节日,
     	   calendar: true,//显示公历节日
     	   min: '1900-01-01 00:00:00', //最小日期
     	   max: '2099-12-31 23:59:59', //最大日期
     	   start: '2014-6-15 23:00:00',    //开始日期
     	   value: '2017-09-10', //初始值
     	   isInitValue: false ,//是否允许填充初始值，默认为 true
     	   mark: { //标注重要日子
     	    '0-10-14': '生日'
     	    ,'0-12-31': '跨年' //每年12月31日
     	    ,'0-0-10': '工资' //每个月10号
     	    ,'2017-8-15': '' //具体日期
     	    ,'2017-8-20': '预发' //如果为空字符，则默认显示数字+徽章
     	    ,'2017-8-21': '发布'
     	  },
     	  ready: function(date){//控件初始打开的回调
     	    console.log(date); //得到初始的日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
     	  },
     	change: function(value, date, endDate){//日期时间被切换后的回调
     	    console.log(value); //得到日期生成的值，如：2017-08-18
     	    console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
     	    console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
     	  },
     	   fixed: true, //是否固定在可视区域
     	   btns: ['confirm'], //['clear', 'now', 'confirm']
     	   zIndex: 99999999 //css z-index
     	  // choose: function(dates){} //选择好日期的回调
      });
  	
  	//获取指定年月的最后一天，month默认为当前月，year默认为当前年。如： 
  	var endDate1 = laydate.getEndDate(10); //得到31 
  	var endDate2 = laydate.getEndDate(2, 2080); //得到29
  	//=========================================================================
        
        
        /*  laydate.render({
        //event: 'focus' //响应事件。如果没有传入event，则按照默认的click
 	   elem: '#effectTime', //需显示日期的元素选择器
 	   event: 'click', //触发事件
 	   format: 'YYYY-MM-DD hh:mm:ss', //日期格式
 	   istime: false, //是否开启时间选择
 	   isclear: true, //是否显示清空
 	   istoday: true, //是否显示今天
 	   issure: true,  //是否显示确认
 	   festival: true, //是否显示节日
 	   min: '1900-01-01 00:00:00', //最小日期
 	   max: '2099-12-31 23:59:59', //最大日期
 	   start: '2014-6-15 23:00:00',    //开始日期
 	   fixed: false, //是否固定在可视区域
 	   zIndex: 99999999, //css z-index
 	   choose: function(dates){} //选择好日期的回调
    });*/

	/*   laydate.render({
			  elem: '#endDate'
			  ,type: 'month'
			  ,format: 'yyyy-MM'
			  ,value: myDate
			  ,btns: ['confirm']
			});*/

  	
 /* 	<select id='name' class="form-control selectpicker"  data-live-search="true"> 

  	var jsonData = :[{"id": "0","text": "可用"},{"id": "1","text": "禁用"}]
  	$("mySelect2IDorClass").select2({
  	data:jsonData
  	})*/
  	$.ajax({  
  	// get请求地址  
  	    url: basePath,  
  	    dataType: "json",  
  	    success: function (data) {  
  	    var optArr = [];  
  	        for (var i = 0; i < data.length; i++) {  
  	            $('.selectpicker').append("<option value=" + data[i].userName + ">" + data[i].userName + "</option>");  
  	        }  
  	  
  	  
  	        // 缺一不可  
  	        $('#name').selectpicker('refresh');  
  	        $('#name').selectpicker('render');  
  	    }  
  	});  
  	
  	
  	
  	//===============表单校验========================
//  	<link href="<%=basePath %>static/formValidator/bootstrapValidator.js"" rel="stylesheet" type="text/css"/>
//  	<script type="text/javascript" src= "<%=basePath %>static/formValidator/bootstrapValidator.js""></script>

  	$("#form-test").bootstrapValidator({
  	    live: 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
  	    excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
  	    submitButtons: '#btn-test',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
  	    message: '通用的验证失败消息',//好像从来没出现过
  	    feedbackIcons: {//根据验证结果显示的各种图标
  	        valid: 'glyphicon glyphicon-ok',
  	        invalid: 'glyphicon glyphicon-remove',
  	        validating: 'glyphicon glyphicon-refresh'
  	    },
  	    fields: {
  	        text: {
  	            validators: {
  	                notEmpty: {//检测非空,radio也可用
  	                    message: '文本框必须输入'
  	                },
  	                stringLength: {//检测长度
  	                    min: 6,
  	                    max: 30,
  	                    message: '长度必须在6-30之间'
  	                },
  	                regexp: {//正则验证
  	                    regexp: /^[a-zA-Z0-9_\.]+$/,
  	                    message: '所输入的字符不符要求'
  	                },
  	                remote: {//将内容发送至指定页面验证，返回验证结果，比如查询用户名是否存在
  	                    url: '指定页面',
  	                    type: 'GET',
  	                  delay :1000,
  	                    message: 'The username is not available'
  	                },
  	                different: {//与指定文本框比较内容相同
  	                    field: '指定文本框name',
  	                    message: '不能与指定文本框内容相同'
  	                },
  	                emailAddress: {//验证email地址
  	                    message: '不是正确的email地址'
  	                },
  	                identical: {//与指定控件内容比较是否相同，比如两次密码不一致
  	                    field: 'confirmPassword',//指定控件name
  	                    message: '输入的内容不一致'
  	                },
  	                date: {//验证指定的日期格式
  	                    format: 'YYYY/MM/DD',
  	                    message: '日期格式不正确'
  	                },
  	                choice: {//check控件选择的数量
  	                    min: 2,
  	                    max: 4,
  	                    message: '必须选择2-4个选项'
  	                }
  	            }
  	        }
  	    }
  	});
//    notEmpty: {message: '账号名称必须输入'},
//    stringLength: {min: 4,max: 16,message: '长度必须在4-16之间'}
//    regexp: {regexp: /^[a-zA-Z0-9_\.]+$/,message: '所输入的字符不符要求'},
//    remote: {url: '指定页面',message: 'The username is not available'},
//    different: {field: '指定文本框name',message: '不能与指定文本框内容相同'},
//    emailAddress: {message: '不是正确的email地址'},
//    identical: {field: 'confirmPassword',message: '输入的内容不一致'},
//    date: {format: 'YYYY/MM/DD',message: '日期格式不正确'},
//    choice: { min: 2,max: 4,message: '必须选择2-4个选项'}
  	
//  	notEmpty：非空验证；
//  	stringLength：字符串长度验证；
//  	regexp：正则表达式验证；
//  	emailAddress：邮箱地址验证（都不用我们去写邮箱的正则了~~）
//  	除此之外，在文档里面我们看到它总共有46个验证类型，我们抽几个常见的出来看看：
//  	base64：64位编码验证；
//  	between：验证输入值必须在某一个范围值以内，比如大于10小于100；
//  	creditCard：身份证验证；
//  	date：日期验证；
//  	ip：IP地址验证；
//  	numeric：数值验证；
//  	phone：电话号码验证；
//  	uri：url验证；
//  	 digits: { message: '值必须是整数'},
//     greaterThan: {value: 10,message: '必须大于10'},
//  	lessThan: {value: 100,message: '必须小于100'}
  	
  	$("#btn-test").click(function () {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
  	    $("#form-test").bootstrapValidator('validate');//提交验证
  	    if ($("#form-test").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
  	        alert("yes");//验证成功后的操作，如ajax
  	    }
  	});

  	//===============表单校验  结束========================