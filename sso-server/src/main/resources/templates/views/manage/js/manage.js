formValidator();

function initFunction(){
	laydate.render({
        //event: 'focus' //响应事件。如果没有传入event，则按照默认的click
 	   elem: '#effectTime', //需显示日期的元素选择器
 	   event: 'click', //触发事件
 	   format: 'yyyy-MM-dd', //日期格式
 	   showBottom: false, //显示底部栏
 	   istime: false, //是否开启时间选择
 	   isclear: false, //是否显示清空
 	   istoday: false, //是否显示今天
 	   issure: false,  //是否显示确认
 	   festival: false, //是否显示节日,
 	   calendar: true,//显示公历节日
 	   min: '2010-01-01 00:00:00', //最小日期
 	   max: '2099-12-31 23:59:59', //最大日期
 	   start: '2014-6-15 23:00:00',    //开始日期
 	   value: new Date(), //初始值
 	   isInitValue: true ,//是否允许填充初始值，默认为 true
 	   fixed: true, //是否固定在可视区域
 	   zIndex: 99999999 //css z-index
  });
	
	$("#docDateTable").bootstrapTable({
		method: 'post',
	    contentType: "application/x-www-form-urlencoded",//当请求方法为post的时候,必须要有！！！！
		url: 'manage/selectByExample.do',   
	    search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	    showRefresh: false,				
	    showToggle: false,				//是否显示详细视图和列表视图的切换按钮
	    showColumns: false,				//是否显示所有的列
	    //toolbar: '#add',                //工具按钮用哪个容器
	    pagination: true,				//是否显示分页（*）
	    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
	    pageNumber:1,                       //初始化加载第一页，默认第一页
	    pageSize: 10,                       //每页的记录行数（*）
	    pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
	    sortable: true,                     //是否启用排序
	    sortOrder: "asc",                   //排序方式
	    clickToSelect: true,                //是否启用点击选中行
	    uniqueId: "adminId",
	    //showpaginationswitch:true,//是否显示 数据条数选择框
	    height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	  //得到查询的参数
	    queryParams : function (params) {
	        //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	        var temp = {   
	            rows: params.limit,                         //页面大小
	            page: (params.offset / params.limit) + 1,   //页码
	            sort: params.sort,      //排序列名  
	            sortOrder: params.order //排位命令（desc，asc） 
	        };
	        return $("#cxtj #searchForm").serialize()+"&"+jQuery.param(temp);
	    },
	    responseHandler: function(res) {
	    	//console.log(res);
	        return {
	            "total": res.extend.date.total,//总页数
	            "rows": res.extend.date.list   //数据
	         };
	    },
	    columns: [
	    	 {title : '序号',field : 'did',align : 'center',checkbox : true, width : 10},
	    	 {title : '主键',field : 'adminId',align : 'center'},
	    	 {title : '账号名称',field : 'name',align : 'center'},
	    	 {title : '性别',field : 'gender',align : 'center',formatter:function (value, row, index) {return setGender(value, row, index)} },
	    	 {title : '手机号',field : 'phone',align : 'center'},
	    	 {title : 'E-mail',field : 'email',align : 'center'},
	    	 {title : '是否生效',field : 'isValid',align : 'center',formatter:function (value, row, index) {return setIsValid(value, row, index)} },
	    	 {title : '生效时间',field : 'effectTime',align : 'center',formatter:setDate()},
	    	 {title : "单位" , field : "department",align : "center",visible:false },
	    	 {title : "失效时间" , field : "failTime",align : "center" ,formatter:setDate()}
	    	// events: operateEvents,
            // formatter: operateFormatter
        ]
	}); 
	
}

//查询按钮事件
$('#searchBtn').click(function(){refreshTable()})

function refreshTable(){
	$('#docDateTable').bootstrapTable('refresh', {url: 'manage/selectByExample.do'});
}

function delInfo(){
	var selrows=$('#docDateTable').bootstrapTable('getSelections');
	   
	if(selrows.length>0){
		swal({
            title: "您确定要删除这条信息吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "是的，我要删除！",
            cancelButtonText: "让我再考虑一下…",
            closeOnConfirm: true,
            closeOnCancel: true
        },
        function (isConfirm) {
            if (isConfirm) {
               // swal("删除成功！", "您已经永久删除了这条信息。", "success");
                var ids="";
            	for ( var i in selrows) {
            		ids+=selrows[i].adminId+"-";
            	}
            	$.ajax({
            	  	url:"manage/delManageInfoByKey/"+ids+".do",
            	  	type:"post",
            	  	data:{_method:"DELETE",ids:ids},
//            	  	type:"DELETE",
//            	  	data:{ids:ids},
            	  	dataType: "json", 
            	  	error: function(result){ //失败 
              		 swal({
                         //title: "太帅了",
                         text: "信息提交失败！",
                         type: "error"
                     });
            	  	}, 
            	  	success:function(result){
//              		 swal({
////            	             title: "太帅了",
//                         text: "信息提交成功！",type: "success"
//                     });
             		swal({title: "提示",text: "删除数据成功"});

              		refreshTable();
            	  	}
            	}); 
            } else {
            }
        });
	}else{
		swal({title: "提示",text: "请选择要删除的数据"});
	}
	
	
}
function updateInfo(){
	var selrow=$('#docDateTable').bootstrapTable('getSelections');
	if(selrow.length!=1){
		swal({
            title: "提示",
            text: "请选择一条数据进行修改"
        });
		return ;
	}
//	$('#myModal5').modal('show',selrow[0]);
	var data = selrow[0];
	$('#myModal5').modal('show');

	
	$("#myModal5 #saveOrUpdateTest" ).text("用户信息修改");

	$("#myModal5 #signupForm #fadminId" ).val(data.adminId);
	$("#myModal5 #signupForm #fname" ).val(data.name);
//	$("#myModal5 #signupForm #fgender" ).val(data.gender);
//	$("#myModal5 #signupForm #fisValid" ).val(data.effectTime);
	$("#myModal5 #signupForm #fphone" ).val(data.phone);
	var genders = $('#myModal5 #signupForm input[type=radio][name=gender]');
	for ( var i in genders) {
		if(genders[i].value==data.gender){
			genders[i].checked=true;
			break;
		}
	}
	
	var isValids = $('#myModal5 #signupForm input[type=radio][name=isValid]');
	for ( var i in isValids) {
		if(isValids[i].value==data.isValid){
			isValids[i].checked=true;
			break;
		}
	}
	
	$("#myModal5 #signupForm #femail" ).val(data.email);
	$("#myModal5 #signupForm #feffectTime" ).val(data.effectTime);
	$("#myModal5 #signupForm #ffailTime" ).val(data.failTime);
	$("#myModal5 #signupForm #fdepartment" ).val(data.department);
	 setTimeout(function(){ 
		 $("#signupForm").bootstrapValidator('validate');
//		 $('#signupForm').data('bootstrapValidator').enableFieldValidators('password', false);
//		 $('#signupForm').data('bootstrapValidator').enableFieldValidators('confirm_password', false);
//		  $('#signupForm').data('bootstrapValidator').updateStatus('password', 'NOT_VALIDATED',null)
//		  $('#signupForm').data('bootstrapValidator').updateStatus('confirm_password', 'NOT_VALIDATED',null)

	 } ,200);//开启执行一次

}

$('#myModal5').on('show.bs.modal', function (e) {
//	console.log("dsssssss");
	$("#myModal5 #signupForm").get(0).reset();
	$("#myModal5 #saveOrUpdateTest" ).text("用户信息新增");
	
	$("#signupForm").data('bootstrapValidator').destroy();
    $('#signupForm').data('bootstrapValidator', null);
    formValidator();
})
function saveUpdate(){
    $("#signupForm").bootstrapValidator('validate');//提交验证

	if(!$("#signupForm").data('bootstrapValidator').isValid()){return ;};
	$.ajax({
	  	url:"manage/saveOrUpdate.do",
	  	type:"post",
	  	data:$("#myModal5 #signupForm").serialize(),
	  	dataType: "json", 
	  	error: function(result){ //失败 
  		 swal({
             //title: "太帅了",
             text: "信息提交失败！",
             type: "error"
         });
	  	}, 
	  	success:function(result){
	  	 $('#myModal5').modal('hide');
  		 swal({
//	             title: "太帅了",
             text: "信息提交成功！",
             type: "success"
         });
  		refreshTable();
	  	}
	}); 
}


function setGender(value, row, index){
	return value=="0"?"女":"男";
}

function setIsValid(value, row, index){
	return value=="0"?"失效":"生效";
}

function setDate(value, row, index){
//	var date = eval('new ' + eval("111111111111111").source)
//    return date.format("yyyy-MM-dd");
	return value;
}

$('#add1').click(function () {
	$('#myModal5').modal('hide');
});


function ajaxPost(url,data){
	 $.ajax({
		  	url:url,
		  	type:"post",
		  	data:data,
		  	dataType: "json", 
		  	error: function(result){ //失败 
	  		 swal({
	             //title: "太帅了",
	             text: "信息提交失败！",
	             type: "error"
	         });
		  	}, 
		  	success:function(result){
		  	 $('#myModal5').modal('hide');
	  		 swal({
//		             title: "太帅了",
	             text: "信息提交成功！",
	             type: "success"
	         });
	  		 refreshTable();
		  	}
		}); 
}
$(document).ready(function () {

    $('.demo1').click(function () {
        swal({
            title: "欢迎使用SweetAlert",
            text: "Sweet Alert 是一个替代传统的 JavaScript Alert 的漂亮提示效果。"
        });
    });

    $('.demo2').click(function () {
        swal({
            title: "太帅了",
            text: "小手一抖就打开了一个框",
            type: "success"
        });
    });

    $('.demo3').click(function () {
        swal({
            title: "您确定要删除这条信息吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            closeOnConfirm: false
        }, function () {
            swal("删除成功！", "您已经永久删除了这条信息。", "success");
        });
    });

    $('.demo4').click(function () {
        swal({
                title: "您确定要删除这条信息吗",
                text: "删除后将无法恢复，请谨慎操作！",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "是的，我要删除！",
                cancelButtonText: "让我再考虑一下…",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {
                if (isConfirm) {
                    swal("删除成功！", "您已经永久删除了这条信息。", "success");
                } else {
                    swal("已取消", "您取消了删除操作！", "error");
                }
            });
    });


});



var effectTime = laydate.render({
    //event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	   elem: '#feffectTime', //需显示日期的元素选择器
	   event: 'click', //触发事件
	   type: 'datetime',
	   format: 'yyyy-MM-dd HH:mm:ss', //日期格式
	   showBottom: true, //显示底部栏
	   istime: false, //是否开启时间选择
	   isclear: false, //是否显示清空
	   istoday: false, //是否显示今天
	   issure: false,  //是否显示确认
	   festival: false, //是否显示节日,
	   calendar: true,//显示公历节日
	   min: '2010-01-01 00:00:00', //最小日期
	   max: '2099-12-31 23:59:59', //最大日期
	   start: '2014-6-15 23:00:00',    //开始日期
	   value: new Date(), //初始值
	   isInitValue: true ,//是否允许填充初始值，默认为 true
	   fixed: true, //是否固定在可视区域
	   zIndex: 99999999 ,//css z-index
	   done: function(value, date, endDate){
		   failTime.config.min ={  
			        year:date.year,   
			        month:date.month-1,   
			        date: date.date
			      }; 
			 setTimeout(function(){timeValidat();} ,100);//开启执行一次
		},
		ready:function(date){
//			$("#signupForm").data('bootstrapValidator').destroy();
//		    $('#signupForm').data('bootstrapValidator', null);
	    },
		change: function(value, date, endDate){
		}
});

var failTime = laydate.render({
    //event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	   elem: '#ffailTime', //需显示日期的元素选择器
	   event: 'click', //触发事件
	   type: 'datetime',
	   format: 'yyyy-MM-dd HH:mm:ss', //日期格式
	   showBottom: true, //显示底部栏
	   istime: false, //是否开启时间选择
	   isclear: false, //是否显示清空
	   istoday: false, //是否显示今天
	   issure: false,  //是否显示确认
	   festival: false, //是否显示节日,
	   calendar: true,//显示公历节日
	   min: '2010-01-01 00:00:00', //最小日期
	   max: '2099-12-31 23:59:59', //最大日期
	   start: '2014-6-15 23:00:00',    //开始日期
	   value: new Date(), //初始值
	   isInitValue: true ,//是否允许填充初始值，默认为 true
// 	   btns: ['confirm'], //['clear', 'now', 'confirm']
	   fixed: true, //是否固定在可视区域
	   zIndex: 99999999, //css z-index
	   done: function(value, date, endDate){
		   effectTime.config.min ={  
			        year:date.year,   
			        month:date.month-1,   
			        date: date.date
			      }; 
//		   var name = setInterval(function(){alert("一直循环执行");} ,1000);
//		   clearTimeout(name);//关闭
		  setTimeout(function(){timeValidat();} ,100);//开启执行一次
//		  var setTimeoutName= setTimeout(function(){imeValidat()} ,1000);//开启
		   
//		   timeValidat();
//		   $("#signupForm").data('bootstrapValidator').validate();
		},
		change: function(value, date, endDate){
		    console.log(value); //得到日期生成的值，如：2017-08-18
		    console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
		    console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
		}
});

function timeValidat(){
	  $('#signupForm').data('bootstrapValidator').updateStatus('effectTime', 'NOT_VALIDATED',null)
      .validateField('effectTime');
	   $('#signupForm').data('bootstrapValidator').updateStatus('failTime', 'NOT_VALIDATED',null)
      .validateField('failTime');
}

function formValidator(){
$("#signupForm").bootstrapValidator({
//    live: 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
    excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
//    submitButtons: '#btn-test',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
    message: '通用的验证失败消息',//好像从来没出现过
    feedbackIcons: {//根据验证结果显示的各种图标
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	name: {
//    		threshold: 4, //有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
            validators: {
                notEmpty: {message: '账号名称必须输入'},
                remote: {url: 'manage/selectNameVerify.do',
	                    type: 'GET',
	                    delay :1000,
//                        data:{userTelephone:$('input[name="usertelephone"]').val() },
//	                    data:{userTelephone:function() {return $('input[name="usertelephone"]').val() } },
	                    message: '用户已存在，请重新输入！'
	                },
                stringLength: {min: 4,max: 16,message: '长度必须在4-16之间'}
            }
        },
        department: {
            validators: {
                notEmpty: {message: '请输入您所在单位！'},
                stringLength: {min: 0,max: 16,message: '单位长度应为0——16位'}
            }
        },
        gender: {
            validators: {
                notEmpty: {message: '性别不可为空！'},
                stringLength: {min: 0,max: 2,message: '单位长度应为0——2位'}
            }
        },
        isValid: {
            validators: {
                notEmpty: {message: '生效标着不可为空！'},
                stringLength: {min: 0,max: 2,message: '单位长度应为0——2位'}
            }
        },
        phone: {
            validators: {
                notEmpty: {message: '手机号不可为空！'},
                stringLength: {min: 11,max: 11,message: '请正确输入您的手机号！'},
//                phone:{},
                regexp:{ regexp:/^1[3|5|8]{1}[0-9]{9}$/,message:'请输入正确的手机号码'}
            }
        },
        email: {
            validators: {
                stringLength: {min: 0,max: 32,message: '邮箱长度不可大于32位！'},
                emailAddress:{message: '请正确输入您的邮箱！'}
            }
        },
        password: {
            validators: {
                notEmpty: {message: '密码不可为空！'},
                stringLength: {min: 8,max: 16,message: '请输入8——16位！'}
            }
        },
        confirm_password: {
            validators: {
                notEmpty: {message: '密码不可为空！'},
                stringLength: {min: 8,max: 16,message: '请输入8——16位！'},
                identical:{field:'password',message:"输入密码不一致"}
            }
        },
        effectTime: {
            validators: {
                notEmpty: {message: '生效日期不可为空！'},
                callback: {
                    callback:function(value, validator,$field,options){
                        var end = $('#signupForm').find("input[name='failTime']").val();
                        return parseInt(Date.parse(value.replace(/-/g,'/'))) <= parseInt( Date.parse(end.replace(/-/g,'/')) );
                    },
                message: '生效日期不能大于失效日期'
                },
                date: {format: 'YYYY-MM-DD HH:mm:dd',message: '日期格式不正确'}
            }
        },
        failTime: {
            validators: {
                notEmpty: {message: '失效日期不可为空！'},
                callback: {
                    callback:function(value, validator,$field,options){
                        var begin = $('#signupForm').find("input[name='effectTime']").val();
                        return parseInt(Date.parse(value.replace(/-/g,'/'))) >= parseInt( Date.parse(begin.replace(/-/g,'/')) );
                    },
                message: '失效日期不能小于生效日期'
                },
                date: {format: 'YYYY-MM-DD HH:mm:dd',message: '日期格式不正确'}
            }
        }
    },
    submitHandler: function (validator, form, submitButton) {
        alert("submit");
    }
});
}