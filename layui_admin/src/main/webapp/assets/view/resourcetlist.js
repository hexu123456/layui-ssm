
layui.use(['table','layer','form'], function(){
	var table =layui.table;
	var layer=layui.layer;
	var form = layui.form;
	  //第一个实例
	 table.render({
	    elem: '#resourceTable'
	    ,id:'rightinfo'
	   // ,height: 312
	    ,url: 'getresourceList' //数据接口
	    ,page: true //开启分页
	    ,limit: 10 //每页默认显示的数量
	    ,limits:[5,10,20,30]
	    ,cols: [[ //表头
	       {field: 'rightid', title: '权限编号',  sort: true, fixed: 'left',hide:true}
	      ,{field: 'resourid', title: '资源编号',  sort: true, fixed: 'left',hide:true}
	      ,{field: 'rightname', title: '权限名称'}
	      ,{field: 'resourname', title: '资源名称'}
	      ,{field: 'url', title: '地址',  sort: true}
	      ,{fixed: 'right', title:'操作',width: 165, align:'center', toolbar: '#ResourceDemo'}
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
        	addResource(table,form);
        }
	 }
	 //添加按钮绑定事件
	 $('.bangdingBtn .layui-btn').on('click', function () {
	        var type = $(this).data('type');
	        active[type] ? active[type].call(this) : '';
	  });
	 /*表格 行内操作(编辑  以及  删除 按钮操作)  */
	  table.on('tool(resourcelist)', function(obj){
	      var data = obj.data; //获得当前行数据
	      var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	      if(layEvent === 'del'){ //删除
	    	  delResourceById(table,data);
	      } else if(layEvent === 'edit'){ //编辑
	    	  editResource(table,data);
	      }
   
	 });
/**
 * 删除
 */	 
});

function delResourceById(table,data){
	 layer.confirm('确定删除吗？',{title:'删除'}, function(index){
   	  var resourid=data.resourid;
   	  $.post("delResourceById",{resourid:resourid},function(result){
   		  if(result.status=='0'){
   			  layer.msg('删除成功', {icon: 1});
                 table.reload('rightinfo', {//重载表格
                     page: {
                         curr: 1
                         // 重新从第 1 页开始
                     }
                 })
   		  }else{
   			  layer.msg('删除失败', {icon: 2});
   		  }
   	  })
   	  layer.close(index);//关闭弹窗
   	  
     });
}
/**
 * 修改
 */
function editResource(table,data){
	layer.open({
		  title:"修改资源信息",
		  type: 2,//0：信息框，默认  1：页面层  2：iframe层  3：加载层  4：tips层
		  shadeClose: true,
          shade: 0, //遮罩透明度
          maxmin: true, //允许全屏最小化
          skin: 'layui-layer-molv',
          btn: ['确定', '取消'] ,
		  content: 'openEditResource',
		   area:['400px','300px']//设置高度宽度:宽度，高度
		  ,success:function(layero,index){
			  //成功打开窗口后回调
			  var body = layer.getChildFrame('body', index);
			  var iframeWindow = layero.find('iframe')[0].contentWindow;
			  body.find('#resourname').val(data.resourname);
			  body.find('#url').val(data.url);
			  //处理权限
			  queryRightList(body,iframeWindow,data);
			  
		   }
		  ,yes: function(index, layero){
			  var body = layer.getChildFrame('body', index);
			  var resourname=  body.find('#resourname').val();
			  var url= body.find('#url').val();
			  if(resourname==null||resourname==''){
				  body.find('#resourname').focus();
				  layer.msg('名称不能为空', {icon: 0});
				  return false;
			  }
			  if(url==null||url==''){
				  body.find('#url').focus();
				  layer.msg('路径不能为空', {url: 0});
				  return false;
			  }
	          $.ajax({
	        	  url:"editResource",
	        	  post:"post",
	        	  data:{resourid:data.resourid,resourname:resourname,url:url},
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
	        			  layer.alert("修改失败", {icon: 2},function (idx) {
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
	//这里以搜索为例
	table.reload('rightinfo',{
	  where: { //设定异步数据接口的额外参数，任意设
		  resourname : $('#sel_resourcename').val(),
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
	$('#sel_resourcename').val("");
	var resourcename=$('#sel_resourcename').val();
	//这里以搜索为例
	table.reload('rightinfo',{
	  where: { //设定异步数据接口的额外参数，任意设
		  resourname :resourcename ,
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
function addResource(table,form){
	layer.open({
		  title:"添加资源",
		  type: 2,//0：信息框，默认1：页面层2：iframe层3：加载层4：tips层
		  content: 'openResource',
		   area:['400px','300px']//设置高度宽度:宽度，高度
		  ,btn: ['确定', '取消']
		  ,success:function(layero,index){
			  var body = layer.getChildFrame('body', index);
			  var iframeWindow = layero.find('iframe')[0].contentWindow;
			  var data={rightid:''};
			  queryRightList(body,iframeWindow,data);
		  }
		  ,yes: function(index, layero){
			  var body = layer.getChildFrame('body', index);
			  var resourname=  body.find('#resourname').val();
			  var rightid=  body.find('#rightid').val();
			  var url= body.find('#url').val();
			  if(resourname==null||resourname==''){
				  body.find('#resourname').focus();
				  layer.msg('名称不能为空', {resourname: 0});
				  return false;
			  }
			  if(url==null||url==''){
				  body.find('#url').focus();
				  layer.msg('路径不能为空', {url: 0});
				  return false;
			  }
			  //成功打开窗口后回调
			  $.ajax({
			  		type:"post",
						dataType:"json",//预期服务器返回的数据类型,如果不设置 的json数据接收不了
						url:"addResoure",
						data:{rightid:rightid,resourname:resourname,url:url},
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
			        			  layer.alert("添加失败", {icon: 2},function (idx) {
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
 * 得到所有权限列表
 */
function queryRightList(body,iframeWindow,data){
	 $.ajax({
	  		type:"get",
			dataType:"json",//预期服务器返回的数据类型,如果不设置 的json数据接收不了
			url:"queryRightList",
			success:function(result){
				 var option = '<option >---请选择权限---</option>';  // option进行拼接
				 $.each(result.data,function(idx,right){
					 if(right.rightid==data.rightid)
					    option += "<option value='"+right.rightid+"' selected='selected'>"+right.rightname+"</option>";
					 else
						 option += "<option value='"+right.rightid+"'>"+right.rightname+"</option>";
				 })
			     body.find('#rightid').html(option);
              iframeWindow.layui.form.render('select');//必须从新渲染
			}
	    })
}