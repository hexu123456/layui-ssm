<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改超管权限</title>
</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/layui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/xadmin.css">
<body id="addManagers">
	<div class="x-body">
		<form class="layui-form" id="addRightForm">
		    <input type="hidden" id="managerid" />
			<div class="layui-form-item">
				<label for="rolename" class="layui-form-label" > 角色名称<span class="x-red">*</span>:
				</label>
				<div class="layui-input-inline">
					<input type="text" id="rolename" name="rolename" required lay-verify="rolename" class="layui-input">
				</div>
				<label for="linkman" class="layui-form-label" >联系人名<span class="x-red">*</span>:
				</label>
				<div class="layui-input-inline">
					<input type="text" id="linkman" name="linkman" required lay-verify="linkman" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="linkphone" class="layui-form-label">电话<span class="x-red">*</span>: </label>
				<div class="layui-input-inline">
					<input type="text" id="linkphone" name="linkphone" class="layui-input" required lay-verify="linkphone">
				</div>
				<label for="account" class="layui-form-label">账号<span class="x-red">*</span>: </label>
				<div class="layui-input-inline">
					<input type="text" id="account" name="account" class="layui-input" required lay-verify="account">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" >权限<span class="x-red">*</span>:</label>
			    <div class="layui-input-block" id="queryEditAdminrResource">
			    </div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layui.all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
</script>
</html>