
layui.use(['table','layer','form'], function(){
	var table =layui.table;
	var layer=layui.layer;
	var form = layui.form;
	  //第一个实例
	 table.render({
	    elem: '#mangerTable'
	    ,id:'managerInfo'//自定义
	   // ,height: 312
	    ,url: 'queryManagerList' //数据接口
	    ,page: true //开启分页
	    ,limit: 10 //每页默认显示的数量
	    ,limits:[5,10,20,30]
	    ,cols: [[ //表头
	       {field: 'managerid', title: 'managerid',  sort: true, fixed: 'left',hide:true}
	      ,{field: 'account', title: '登录账号'}
	      ,{field: 'linkman', title: '姓名',  sort: true}
	      ,{field: 'linkphone', title: '电话',  sort: true}
	      ,{field: 'rolename', title: '角色名称'} 
	      ,{field: 'type', title: '类型',sort:true,templet:function(d){
	    	//类型(type:x.平台超管  0.平台管理员  1.物业公司 2.小区管理员  3.财务管理员 4.经纪人5.车位分期管理员)
				if(d.type=='x')
					return "平台超管";
				if(d.type=='0')
					return "平台管理员";
				if(d.type=='1')
					return "物业公司";
				if(d.type=='2')
					return "小区管理员 ";
				if(d.type=='3')
					return "财务管理员";
	      }} 
	      ,{field: 'enabled', title: '状态',templet:function(d){
	    		////是否可用 0.禁用  1.可用
				if(d.enabled=='0')
					return "禁用";
				if(d.enabled=='1')
					return "正常";
	      }} 
	      ,{fixed: 'right', title:'操作',width: 265, align:'center', toolbar: '#barManager'}
	    ]]
	  ,page: true //是否显示分页
	  });
	//上方菜单操作栏(查询、以及  增加  按钮  )
	 
	 var $ = layui.$, active = {
	   search: function () {//查询
        	search(table);
        },reload:function(){//重置
        	reload(table);
        }, add: function () { //添加
        	openAddManager(table,form);
        }
	 }
	 //添加按钮绑定事件
	 $('.bangdingBtn .layui-btn').on('click', function () {
	        var type = $(this).data('type');
	        active[type] ? active[type].call(this) : '';
	  });
	 /*表格 行内操作(编辑  以及  删除 按钮操作) tool:对应table中lay-filter */
	  table.on('tool(managerlist)', function(obj){
	      var data = obj.data; //获得当前行数据
	      var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	      if(layEvent == 'del'){ //删除
	    	  delFlatManager(table,data);
	      } else if(layEvent == 'edit'){ //编辑
	    	  openEditManager(table,data);
	      }else if(layEvent=='resertPwd'){//重置密码
	        openResertPwd(table,data);
	      }
	      
	     
   
	 });
/**
 * 删除
 */	 
});

function delFlatManager(table,data){
	 layer.confirm('确定删除吗？',{title:'删除'}, function(index){
   	  var managerid=data.managerid;
   	  $.post("delFlatManagerById",{managerid:managerid},function(result){
   		  if(result.status=='0'){
   			  layer.msg('删除成功', {icon: 1});
   			  //刷新
	          search(table);
   		  }else{
   			  layer.msg('删除失败', {icon: 2});
   		  }
   	  })
   	  layer.close(index);//关闭弹窗
   	  
     });
}
/**
 * 修改平台管理员
 */
function openEditManager(table,data){
	layer.open({
		  title:"修改平台管理员",
		  type: 2,//0：信息框，默认  1：页面层  2：iframe层  3：加载层  4：tips层
		  shadeClose: true,
          shade: 0, //遮罩透明度
          maxmin: true, //允许全屏最小化
          skin: 'layui-layer-molv',
          btn: ['确定', '取消'] ,
		  content: 'openEditManager',
		  area:['700px','430px']//设置高度宽度:宽度，高度
		  ,success:function(layero,index){
			  //成功打开窗口后回调
			  var body = layer.getChildFrame('body', index);
			  body.find('#rolename').val(data.rolename);
			  body.find('#linkman').val(data.linkman);
			  body.find('#linkphone').val(data.linkphone);
			  body.find('#account').val(data.account);
			  //处理权限
			  var iframeWindow = layero.find('iframe')[0].contentWindow; //获得子页面的对象
			  queryEditMangerResource(body,iframeWindow,data);
			  
		   }
		  ,yes: function(index, layero){
			  var body = layer.getChildFrame('body', index);
			  var rolename=  body.find('#rolename').val();
			  var linkman= body.find('#linkman').val();
			  var linkphone= body.find('#linkphone').val();
			  var account= body.find('#account').val();
			  if(rolename==null||rolename==''){
				  body.find('#rolename').focus();
				  layer.msg('角色名称不能为空', {icon: 0});
				  return false;
			  }
			  if(linkman==null||linkman==''){
				  body.find('#linkman').focus();
				  layer.msg('联系人名不能为空', {icon: 0});
				  return false;
			  }
			  if(linkphone==null||linkphone==''){
				  body.find('#linkphone').focus();
				  layer.msg('电话不能为空', {icon: 0});
				  return false;
			  }
			  if(account==null||account==''){
				  body.find('#account').focus();
				  layer.msg('账号不能为空', {icon: 0});
				  return false;
			  }
				//获取权限ID集合
				var rightids=new Array();  
				body.find('input[name="erightid"]:checked').each(function(){  
					rightids.push($(this).val().replace('e',''));//向数组中添加元素
				});
				if(rightids.length<1)
				{
					layer.msg('请勾选权限集！', {icon: 0});
					return false;
				}
				 $.ajax({
				  		type:"post",
							dataType:"json",//预期服务器返回的数据类型,如果不设置 的json数据接收不了
							url:"editFlatManagerInfo",
							data:{
								managerid:data.managerid,
								rolename:rolename,
								linkman:linkman,
								linkphone:linkphone,
								account:account,
								rightids:rightids.toString()
							},
							success:function(result){
								if(result.status=='0'){
				        			  layer.alert("修改成功", {icon: 1},function (idx) {
				        		             //关闭当前提示框
				        		             layer.close(idx);
				        		             //刷新
				        		             search(table);
				        		        }); 
				        			  //关闭当前frame
				        	          layer.close(index);
				        		  }else{
				        			  layer.alert(result.msg, {icon: 2},function (idx) {
				        		             //关闭当前提示框
				        		             layer.close(idx);
				        		        });
				        		  }
							}
				    })
			
		  }
		});
}
/**
 * 搜索
 */
function search(table){
	//这里以搜索为例 对应table定义的id
	table.reload('managerInfo',{
	  where: { //设定异步数据接口的额外参数，任意设
		  account : $('#sel_account').val(),
	  }
	  ,page: {
	    curr: 1 //重新从第 1 页开始
	    //,limit:10
	  }
	});
}
/**
 * 重置
 */
function reload(table){
	$('#sel_account').val("");
	var account=$('#sel_account').val();
	//这里以搜索为例 对应table定义的id
	table.reload('managerInfo',{
	  where: { //设定异步数据接口的额外参数，任意设
		  account :account ,
	  }
	  ,page: {
	    curr: 1 //重新从第 1 页开始
	    //,limit:10
	  }
	});
}
/**
 * 打开添加页面
 */
function openAddManager(table,form){
	layer.open({
		  title:"添加平台管理员",
		  type: 2,//0：信息框，默认1：页面层2：iframe层3：加载层4：tips层
		  maxmin: true, //允许全屏最小化
          skin: 'layui-layer-molv',
		  content: 'openAddManager',
		   area:['700px','450px']//设置高度宽度:宽度，高度
		  ,btn: ['确定', '取消']
		  ,success:function(layero,index){
			  var body = layer.getChildFrame('body', index);
			  var iframeWindow = layero.find('iframe')[0].contentWindow; //获得子页面的对象
			  queryAddMangerResource(body,iframeWindow);
		   }
		  ,yes: function(index, layero){
			  var body = layer.getChildFrame('body', index);
			  var rolename=  body.find('#rolename').val();
			  var linkman= body.find('#linkman').val();
			  var linkphone= body.find('#linkphone').val();
			  var account= body.find('#account').val();
			  var password= body.find('#password').val();
			  var surepassword= body.find('#surepassword').val();
			  if(rolename==null||rolename==''){
				  body.find('#rolename').focus();
				  layer.msg('角色名称不能为空', {icon: 0});
				  return false;
			  }
			  if(linkman==null||linkman==''){
				  body.find('#linkman').focus();
				  layer.msg('联系人名不能为空', {icon: 0});
				  return false;
			  }
			  if(linkphone==null||linkphone==''){
				  body.find('#linkphone').focus();
				  layer.msg('电话不能为空', {icon: 0});
				  return false;
			  }
			  if(account==null||account==''){
				  body.find('#account').focus();
				  layer.msg('账号不能为空', {icon: 0});
				  return false;
			  }
			  if(password==null||password==''){
				  body.find('#password').focus();
				  layer.msg('密码不能为空', {icon: 0});
				  return false;
			  }
			  if(surepassword==null||surepassword==''){
				  body.find('#surepassword').focus();
				  layer.msg('确认密码不能为空', {icon: 0});
				  return false;
			  }
			  if(surepassword!=password){
				  body.find('#surepassword').focus();
				  layer.msg('两次输入的密码不相符', {icon: 0});
				  return false;
			  }
				//获取权限ID集合
				var rightids=new Array();  
				body.find('input[name="erightid"]:checked').each(function(){  
					rightids.push($(this).val().replace('e',''));//向数组中添加元素
				});
				if(rightids.length<1)
				{
					layer.msg('请勾选权限集！', {icon: 0});
					return false;
				}
			  //成功打开窗口后回调
			  $.ajax({
			  		type:"post",
						dataType:"json",//预期服务器返回的数据类型,如果不设置 的json数据接收不了
						url:"saveFlatManagerInfo",
						data:{
							rolename:rolename,
							linkman:linkman,
							linkphone:linkphone,
							account:account,
							password:password,
							rightids:rightids.toString()
						},
						success:function(result){
							if(result.status=='0'){
			        			  layer.alert("添加成功", {icon: 1},function (idx) {
			        		             //关闭当前提示框
			        		             layer.close(idx);
			        		             //刷新
			        		             search(table);
			        		        }); 
			        			  //关闭当前frame
			        	          layer.close(index);
			        		  }else{
			        			  layer.alert(result.msg, {icon: 2},function (idx) {
			        		             //关闭当前提示框
			        		             layer.close(idx);
			        		        });
			        		  }
						}
			    })
		  }
		});
}
/**
 * 超管修改页面
 * @param table
 * @param form
 */
function openEditAdmin(){
	layer.open({
		title:"修改超管信息",
		type: 2,//0：信息框，默认1：页面层2：iframe层3：加载层4：tips层
		maxmin: true, //允许全屏最小化
		skin: 'layui-layer-molv',
		content: 'openEditAdmin',
		area:['700px','450px']//设置高度宽度:宽度，高度
	,btn: ['确定', '取消']
	,success:function(layero,index){
		var body = layer.getChildFrame('body', index);
		var iframeWindow = layero.find('iframe')[0].contentWindow; //获得子页面的对象
		queryEditAdminrResource(body,iframeWindow);
	}
	,yes: function(index, layero){
		  var body = layer.getChildFrame('body', index);
		  var managerid=  body.find('#managerid').val();
		  var rolename=  body.find('#rolename').val();
		  var linkman= body.find('#linkman').val();
		  var linkphone= body.find('#linkphone').val();
		  var account= body.find('#account').val();
		  if(rolename==null||rolename==''){
			  body.find('#rolename').focus();
			  layer.msg('角色名称不能为空', {icon: 0});
			  return false;
		  }
		  if(linkman==null||linkman==''){
			  body.find('#linkman').focus();
			  layer.msg('联系人名不能为空', {icon: 0});
			  return false;
		  }
		  if(linkphone==null||linkphone==''){
			  body.find('#linkphone').focus();
			  layer.msg('电话不能为空', {icon: 0});
			  return false;
		  }
		  if(account==null||account==''){
			  body.find('#account').focus();
			  layer.msg('账号不能为空', {icon: 0});
			  return false;
		  }
			//获取权限ID集合
			var rightids=new Array();  
			body.find('input[name="erightid"]:checked').each(function(){  
				rightids.push($(this).val().replace('e',''));//向数组中添加元素
			});
			if(rightids.length<1)
			{
				layer.msg('请勾选权限集！', {icon: 0});
				return false;
			}
			 $.ajax({
			  		type:"post",
						dataType:"json",//预期服务器返回的数据类型,如果不设置 的json数据接收不了
						url:"editFlatManagerInfo",
						data:{
							managerid:managerid,
							rolename:rolename,
							linkman:linkman,
							linkphone:linkphone,
							account:account,
							rightids:rightids.toString()
						},
						success:function(result){
							if(result.status=='0'){
			        			  layer.alert("修改成功", {icon: 1},function (idx) {
			        		             //关闭当前提示框
			        		             layer.close(idx);
			        		             //刷新
			        		             search(table);
			        		        }); 
			        			  //关闭当前frame
			        	          layer.close(index);
			        		  }else{
			        			  layer.alert(result.msg, {icon: 2},function (idx) {
			        		             //关闭当前提示框
			        		             layer.close(idx);
			        		        });
			        		  }
						}
			    })
	     }
	});
}
/**
 * 重置密码
 */
function openResertPwd(table,data){
	layer.open({
		  title:"重置密码",
		  type: 2,//0：信息框，默认1：页面层2：iframe层3：加载层4：tips层
		  maxmin: true, //允许全屏最小化
          skin: 'layui-layer-molv',
		  content: 'openResertPwd',
		   area:['400px','250px']//设置高度宽度:宽度，高度
		  ,btn: ['确定', '取消']
		  ,success:function(layero,index){
			  
		   }
		  ,yes: function(index, layero){
			  var body = layer.getChildFrame('body', index);
			  var password= body.find('#password').val();
			  var surepassword= body.find('#surepassword').val();
			  if(password==null||password==''){
				  body.find('#password').focus();
				  layer.msg('密码不能为空', {icon: 0});
				  return false;
			  }
			  if(surepassword==null||surepassword==''){
				  body.find('#surepassword').focus();
				  layer.msg('确认密码不能为空', {icon: 0});
				  return false;
			  }
			  if(surepassword!=password){
				  body.find('#surepassword').focus();
				  layer.msg('两次输入的密码不相符', {icon: 0});
				  return false;
			  }
			  $.ajax({
				  url:"resertPwd",
				  type:"post",
				  data:{managerid:data.managerid,password:password},
				  success:function(result){
					  if(result.status=='0'){
	        			  layer.alert("重置密码成功", {icon: 1},function (idx) {
	        		             //关闭当前提示框
	        		             layer.close(idx);
	        		             //刷新
	        		             search(table);
	        		        }); 
	        			  //关闭当前frame
	        	          layer.close(index);
	        		  }else{
	        			  layer.alert("服务正忙,请稍后再试!", {icon: 2},function (idx) {
	        		             //关闭当前提示框
	        		             layer.close(idx);
	        		        });
	        		  }
				  }
				 
			  })
		  }
	});
}
/**
 * 得到添加管理员权限列表
 */
function queryAddMangerResource(body,iframeWindow){
	body.contents().find("#queryAddMangerResource").html("");
    $.ajax({
		type:"post",
	    dataType: "json",//预期服务器返回的数据类型
		url:"queryAddMangerResource",
		success:function(result){
			 body.find("#queryAddMangerResource").html(result.data.ecatehtml);
			 iframeWindow.layui.form.render();//必须从新渲染
		}
	})
}
/**
 * 得到修改管理员权限列表
 */
function queryEditMangerResource(body,iframeWindow,data){
	body.contents().find("#queryEditMangerResource").html("");
	$.ajax({
		type:"post",
		data:{managerid:data.managerid},
		dataType: "json",//预期服务器返回的数据类型
		url:"queryEditMangerResource",
		success:function(result){
			body.find("#queryEditMangerResource").html(result.data.ecatehtml);
			iframeWindow.layui.form.render();//必须从新渲染
		}
	})
}
/**
 * 得到修改超管权限列表
 */
function queryEditAdminrResource(body,iframeWindow,data){
	body.contents().find("#queryEditAdminrResource").html("");
	$.ajax({
		type:"post",
		dataType: "json",//预期服务器返回的数据类型
		url:"queryEditAdminrResource",
		success:function(result){
			body.find("#queryEditAdminrResource").html(result.data.ecatehtml);
			var managerinfo=result.data.managerinfo;
			  body.find('#managerid').val(managerinfo.managerid);
			  body.find('#rolename').val(managerinfo.rolename);
			  body.find('#linkman').val(managerinfo.linkman);
			  body.find('#linkphone').val(managerinfo.linkphone);
			  body.find('#account').val(managerinfo.account);
			iframeWindow.layui.form.render();//必须从新渲染
		}
	})
}