
layui.use(['table','layer','form'], function(){
	var table =layui.table;
	var layer=layui.layer;
	var form = layui.form;
	  //第一个实例
	 table.render({
	    elem: '#rightTable'
	    ,id:'rightinfo'
	   // ,height: 312
	    ,url: 'rightList' //数据接口
	    ,page: true //开启分页
	    ,limit: 10 //每页默认显示的数量
	    ,limits:[5,10,20,30]
	    ,cols: [[ //表头
	       {field: 'rightid', title: 'rightid',  sort: true, fixed: 'left',hide:true}
	      ,{field: 'rightname', title: '权限名称'}
	      ,{field: 'icon', title: '图标',  sort: true}
	      ,{field: 'sort', title: '序号'} 
	      ,{field: 'isenabled', title: '状态',templet:function(d){
	    	  if(d.isenabled=='0')
	    		  return "禁用";
	    	  else
	    		  return "启用";
	      }}
	      ,{fixed: 'right', title:'操作',width: 165, align:'center', toolbar: '#barManager'}
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
        	addRight(table);
        }
	 }
	 //添加按钮绑定事件
	 $('.bangdingBtn .layui-btn').on('click', function () {
	        var type = $(this).data('type');
	        active[type] ? active[type].call(this) : '';
	  });
	 /*表格 行内操作(编辑  以及  删除 按钮操作)  */
	  table.on('tool(rightlist)', function(obj){
	      var data = obj.data; //获得当前行数据
	      var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	      if(layEvent === 'del'){ //删除
	    	  delRightById(table,data);
	      } else if(layEvent === 'edit'){ //编辑
	    	  editRight(table,data);
	      }
   
	 });
/**
 * 删除
 */	 
});

function delRightById(table,data){
	 layer.confirm('确定删除吗？',{title:'删除'}, function(index){
   	  var rightid=data.rightid;
   	  $.post("delRightById",{rightid:rightid},function(result){
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
function editRight(table,data){
	layer.open({
		  title:"修改权限",
		  type: 2,//0：信息框，默认  1：页面层  2：iframe层  3：加载层  4：tips层
		  shadeClose: true,
          shade: 0, //遮罩透明度
          maxmin: true, //允许全屏最小化
          skin: 'layui-layer-molv',
          btn: ['确定', '取消'] ,
		  content: 'openEditRight',
		   area:['400px','250px']//设置高度宽度:宽度，高度
		  ,success:function(layero,index){
			  //成功打开窗口后回调
			  var body = layer.getChildFrame('body', index);
			  body.find('#rightname').val(data.rightname);
			  body.find('#icon').val(data.icon);
			  
		   }
		  ,yes: function(index, layero){
			  var body = layer.getChildFrame('body', index);
			  var rightname=  body.find('#rightname').val();
			  var icon= body.find('#icon').val();
			  if(rightname==null||rightname==''){
				  body.find('#rightname').focus();
				  layer.msg('权限名称不能为空', {icon: 0});
				  return false;
			  }
			  if(icon==null||icon==''){
				  body.find('#icon').focus();
				  layer.msg('图标不能为空', {icon: 0});
				  return false;
			  }
	          $.ajax({
	        	  url:"editRight",
	        	  post:"post",
	        	  data:{rightid:data.rightid,rightname:rightname,icon:icon},
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
		  rightname : $('#sel_rightname').val(),
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
	$('#sel_rightname').val("");
	var rightname=$('#sel_rightname').val();
	//这里以搜索为例
	table.reload('rightinfo',{
	  where: { //设定异步数据接口的额外参数，任意设
		  rightname :rightname ,
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
function addRight(table){
	layer.open({
		  title:"添加权限",
		  type: 2,//0：信息框，默认1：页面层2：iframe层3：加载层4：tips层
		  content: 'openAddRight',
		   area:['400px','250px']//设置高度宽度:宽度，高度
		  ,btn: ['确定', '取消']
		  ,success:function(layero,index){
		   }
		  ,yes: function(index, layero){
			  var body = layer.getChildFrame('body', index);
			  var rightname=  body.find('#rightname').val();
			  var icon= body.find('#icon').val();
			  if(rightname==null||rightname==''){
				  body.find('#rightname').focus();
				  layer.msg('权限名称不能为空', {icon: 0});
				  return false;
			  }
			  if(icon==null||icon==''){
				  body.find('#icon').focus();
				  layer.msg('图标不能为空', {icon: 0});
				  return false;
			  }
			  //成功打开窗口后回调
			  $.ajax({
			  		type:"post",
						dataType:"json",//预期服务器返回的数据类型,如果不设置 的json数据接收不了
						url:"addRight",
						data:{rightname:rightname,icon:icon},
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
