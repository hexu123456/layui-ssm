<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重置密码</title>
</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/layui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/xadmin.css">
<body>
	<div class="x-body">
		<form class="layui-form" id="addRightForm">
			<div class="layui-form-item">
				<label for="password" class="layui-form-label" > 新密码<span class="x-red">*</span>:
				</label>
				<div class="layui-input-inline">
					<input type="password" id="password" name="password" class="layui-input" required lay-verify="password">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="surepassword" class="layui-form-label">确认密码<span class="x-red">*</span>: </label>
				<div class="layui-input-inline">
					<input type="password" id="surepassword" name="surepassword" class="layui-input" required lay-verify="surepassword">
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layui.all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/view/flatrightlist.js" ></script>
</html>