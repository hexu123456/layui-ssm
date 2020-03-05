<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限列表</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css"> 
<body>
 <div class="x-nav">
     <span class="layui-breadcrumb">
       <a href="">首页</a>
       <a><cite>权限列表</cite></a>
     </span>
   </div>
 <div class="x-body">
     <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so bangdingBtn" >
           <label class="layui-form-label">权限名称:</label>
           <input type="text" name="rightname" id="sel_rightname"  placeholder="请输入权限名称" class="layui-input">
          <button type="button" class="layui-btn" style="vertical-align:top;"  data-type="search"><i class="layui-icon">&#xe615;</i>查询</button>
          <button type="button" class="layui-btn" style="vertical-align:top;"  data-type="reload"><i class="layui-icon">&#xe666;</i>重置</button>
        </form>
      </div>
      <xblock class="bangdingBtn">
        <button class="layui-btn" data-type="add" ><i class="layui-icon"></i>添加</button>
      </xblock>
	 <table id="rightTable" lay-filter="rightlist" ></table>
	<script type="text/html" id="barManager">
      <a class="layui-btn layui-btn-xs" lay-event="edit" >编辑</a>
      <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>

 </div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layui.all.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/view/flatrightlist.js" ></script>
<script>

</script>
</body>
</html>