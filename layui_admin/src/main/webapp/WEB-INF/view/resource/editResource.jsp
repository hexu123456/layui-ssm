<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加权限</title>
</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/layui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/xadmin.css">
<body>
	<div class="x-body">
		<form class="layui-form" id="editRightForm">
		    <div class="layui-form-item">
			 <label class="layui-form-label">权限名称</label>
				<div  class="layui-input-block">
					<select id="rightid"  lay-verify="required" disabled="disabled">
						<option value="">--请选择权限--</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="rightname" class="layui-form-label" > 资源名称<span class="x-red">*</span>:
				</label>
				<div class="layui-input-inline">
					<input type="text" id="resourname" name="resourname" required lay-verify="resourname" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="icon" class="layui-form-label"> 路径<span class="x-red">*</span>: </label>
				<div class="layui-input-inline">
					<input type="text" id="url" name="url" class="layui-input" required lay-verify="url">
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layui.all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/view/resourcetlist.js" ></script>
</html>