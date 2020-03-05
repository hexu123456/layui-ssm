<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改权限</title>
</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/layui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/xadmin.css">
<body>
	<div class="x-body">
		<form class="layui-form" id="editRightForm">
			<div class="layui-form-item">
				<label for="rightname" class="layui-form-label" > 权限名称<span class="x-red">*</span>:
				</label>
				<div class="layui-input-inline">
					<input type="text" id="rightname" name="rightname" required lay-verify="rightname" class="layui-input" value=""/>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="icon" class="layui-form-label"> 图标<span class="x-red">*</span>: </label>
				<div class="layui-input-inline">
					<input type="text" id="icon" name="icon" class="layui-input" required lay-verify="icon">
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layui.all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/view/flatrightlist.js" ></script>
<script>
      
</script>
</html>