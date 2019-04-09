//tree文档帮助     https://www.cnblogs.com/zx-admin/p/7872046.html

 var defaultData = [
	 	{text: '首页',href: '/views/welcome/welcomeImage.jsp',tags: ['0']},
		{text: '管理员',href: '',tags: ['0'],
			nodes: [
                {text: '管理员信息',href: '/views/manage/manage.jsp',tags: ['2'],url:"fds"}
            ]},
        {text: '图书管理',href: '',tags: ['4'],
           nodes: [
                {text: '图书信息管理',href: '/views/bookInfo/bookInfo.jsp',tags: ['2'],url:"fds"}
            ]},
        {text: '借阅信息管理',href: '',tags: ['0'],
        	 nodes: [{text: '借阅卡管理',href: '/views/borrowcard/borrowcard.jsp',tags: ['0']},
        		 {text: '借阅信息查看',href: '/views/borrowRecord/borrowRecord.jsp',tags: ['0']}
        	 ]},
     	 	{text: '图书借阅',href: '/views/bookBorrow/bookBorrow.jsp?recordType=0',tags: ['0']},
     	 	{text: '图书归还',href: '/views/bookBorrow/bookBorrow.jsp?recordType=1',tags: ['0']}

//    	 	{text: '图书归还',href: '/views/bookReturn/bookReturn.jsp?recordType=1',tags: ['0']}

        ];

 
 $('#treeview').treeview({
     color: "#428bca",
     data: defaultData,
     selectedBackColor: "darkorange",
     selectedColor: "yellow",
     showBorder:false,//显示表格
     showTags: false,//显示后面数据
     enableLinks: false,
     levels: 0,
     highlightSelected: true,
     onNodeSelected: function (event, node) {
    	 if(node.href==""){return;}
//         $('#event_output').prepend('<p>您单击了 ' + node.text + '</p>');
//         $('#myTab').append('<li><a href="#'+nodeIdHref+'" data-toggle="tab">'+ node.text 
// 				+'&nbsp;<button type="button" class="close"  aria-label="Close" id="'+nodeIdHref+'" onclick="closeTab(this)" ><span aria-hidden="true" style="color:red">&times;</span></button>'+
//         '</a></li>');
//         $('#myTabContent').append('<div class="tab-pane fade" id="'+nodeIdHref+'"><p>您单击了 ' + node.text + '</p></div>');
         
//    	 $('#treeview').selected.Expand(True);//子节点展开
//    	 $('#treeview').selected.collapse(True)://子节点闭拢
    		 
    	 $('#myTab a[href="#ios"]').text(node.text);
//    	 $('#myTab a[href="#ios"]').html(node.text+'&nbsp;<button type="button" class="close"  aria-label="Close" onclick="closeTab(this)" ><span style="color:red">&times;</span></button>');
//    	 $('#myTabContent #ios').html("<object data='../manage/manage.jsp'></object> ");
    	 $('#myTabContent #ios ').html("<iframe SRC='.."+node.href+"' ></iframe> ");
    	 $('#myTab a[href="#ios"]').css("display","block");
    	 $("#myTabContent #ios").css("display","block");

//    	 $.ajax({
//    	         url : '../manage/manage.jsp',
//    	 	cache : false,
//    	 	async : false,
//    	 	success : function(html) {
//    	 		$('#myTabContent #ios ').html(html);
//       		 $('#myTabContent #ios ').height(document.documentElement.clientHeight - 115);
//    	 	}
//    	 });
    	 
//    	 $('#myTabContent #ios ').load('../manage/manage.jsp #admin',{data:date},function(){
//    		 $(".close").click(function(){
//    			 $(".detail").hide();
//    			 });
    		 $('#myTabContent #ios iframe').height(document.documentElement.clientHeight*0.68);
    		 $('#myTabContent #ios iframe').width(document.documentElement.clientWidth *0.83);

//    		 $('#myTabContent #ios iframe').height(document.documentElement.clientHeight - 235);
//    		 $('#myTabContent #ios iframe').width(document.documentElement.clientWidth - 215);

//    	 });
    	 
         $('#myTab a:last').tab('show');
         
     }
 });
 
 
 nodeSelected();
 function nodeSelected(){
	 $('#myTabContent #ios ').html("<iframe SRC='../views/welcome/welcomeImage.jsp' ></iframe> ");
	 $('#myTabContent #ios iframe').height(document.documentElement.clientHeight*0.68);
	 $('#myTabContent #ios iframe').width(document.documentElement.clientWidth *0.83);
     $('#myTab a:last').tab('show');
 }
 function closeTab(){
	 
	 $('#myTabContent #home iframe').height(document.documentElement.clientHeight*0.68);
	 $('#myTabContent #home iframe').width(document.documentElement.clientWidth *0.84);
	 
	$('#myTab a[href="#ios"]').css("display","none");
    $("#myTabContent #ios").css("display","none");
 }
 
 
 var changeFrameHeight = function (that) {
	 $('#myTabContent #home iframe').height(document.documentElement.clientHeight*0.68);
	 $('#myTabContent #home iframe').width(document.documentElement.clientWidth *0.84);
//	    $(that).height(document.documentElement.clientHeight - 130);

	}  
    
    
// var activeTab = "tab1"; //当前的tab页。默认为第一个tab页。
// var previousTab;  //上一个打开的tab页。默认为空。
 
// $(function(){
//	 //实现事件响应函数，当tab页被显示时会触发
 
//	 $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
//		 if(e.target.hash=="#ios"){
//			 $("#myTabContent #home").css("display","none");
//			 $("#myTabContent #ios").css("display","block");
//
//		 }else{
//			 $("#myTabContent #ios").css("display","none");
//			 $("#myTabContent #home").css("display","block");
//		 }
//	 });
// 
// $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
//	 if(e.target.hash=="#ios"){
//		 $("#myTabContent #home").css("display","none");
//		 $("#myTabContent #ios").css("display","block");
//
//	 }else{
//		 $("#myTabContent #ios").css("display","none");
//		 $("#myTabContent #home").css("display","block");
//	 }
//	})
 
// $(function(){
//     //实现事件响应函数，当tab页被显示时会触发
//     $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
//         //获取当前被显示的tab页标签的aria-controls属性值
//         activeTab = $(e.target).attr("aria-controls");
//         //获取前一个被显示的tab页标签的aria-controls属性值
//         previousTab = $(e.relatedTarget).attr("aria-controls");
//     });
//     
//     //点击带关闭按钮tab页标签上的x后的响应事件
//     $("#closetabbtn").click(function(e){
//        // $(this).parent().parent().css("display","none"); //隐藏tab头，调用remove方法就是删除了
//         $(this).parent().parent().remove();
//         $("#closetab").css("display","none"); //隐藏tab正文信息，调用remove方法就是删除了
//         if(activeTab=="closetab"){ //判断当前tab页是否是带关闭按钮的tab页，如果是，则显示上次打开的tab页
//             $('#contentnavid a[href="#'+previousTab+'"]').tab('show'); //显示tab页
//         }        
//         return false; //一定要return false，阻止事件往上冒泡
//     });
// });
 
 
 
 
 
 
// color: "#428bca",
// //backColor: "black",背景色
// //borderColor:'green',
// collapseIcon: "glyphicon glyphicon-minus",//可收缩的节点图标
// nodeIcon: "glyphicon glyphicon-user",
// //emptyIcon: "glyphicon glyphicon-ban-circle",//设置列表树中没有子节点的节点的图标
// expandIcon: "glyphicon glyphicon-plus",  //设置列表上中有子节点的图标
// highlightSearchResults:true,//是否高亮搜索结果 默认true
// highlightSelected:true,     //是否选中高亮显示
// onhoverColor: "#f5f5f5",    //鼠标滑过的颜色
// levels: 0 ,                 //设置初始化展开几级菜单 默认为2
// selectedIcon: 'glyphicon glyphicon-tint',
//// selectedBackColor: 'black',  //设置被选中的节点背景颜色
// //selectedColor : 'red',      //设置被选择节点的字体、图标颜色
// showBorder:true,                //是否显示边框
// showCheckbox:false,              //是否显示多选框
// //uncheckedIcon:'',             //设置未选择节点的图标
// data:defaultData,
// showTags:true,//显示徽章