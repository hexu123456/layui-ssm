<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>layuimini-单页版 v2 - 基于Layui的后台管理系统前端模板</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="icon" href="images/favicon.ico">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font-awesome.min.css"
	media="all">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/layuimini.css?v=2.0.0"
	media="all">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/public.css" media="all">
</head>
<body class="layui-layout-body layuimini-all">
	<div class="layui-layout layui-layout-admin">

		<div class="layui-header header">
			<div class="layui-logo layuimini-logo layuimini-back-home">
			   <a href="javascript:;"><img src="${pageContext.request.contextPath}/images/logo.png" alt="logo"><h1>后台管理系统</h1></a>
			</div>

			<div class="layuimini-header-content">
				<a>
					<div class="layuimini-tool">
						<i title="展开" class="fa fa-outdent" data-side-fold="1"></i>
					</div>
				</a>

				<!--电脑端头部菜单-->
				<ul
					class="layui-nav layui-layout-left layuimini-header-menu mobile layui-hide-xs layuimini-menu-header-pc">
				</ul>

				<!--手机端头部菜单-->
				<ul
					class="layui-nav layui-layout-left layuimini-header-menu mobile layui-hide-sm">
					<li class="layui-nav-item"><a href="javascript:;"><i
							class="fa fa-list-ul"></i> 选择模块</a>
						<dl class="layui-nav-child layuimini-menu-header-mobile">
						</dl></li>
				</ul>

				<ul class="layui-nav layui-layout-right">

                    <!-- 打开全屏 -->
					<li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
						<a href="javascript:;" data-check-screen="full"><i
							class="fa fa-arrows-alt"></i></a>
					</li>
					<li class="layui-nav-item layuimini-setting" ><a style="color: white;"
						href="javascript:;">${account}</a>
						<dl class="layui-nav-child">
						  <shiro:hasRole name="gzjojo">
							<dd>
								<a onclick="openEditAdmin();"
									data-title="超管权限" data-icon="fa fa-gears">超管权限<span
									class="layui-badge-dot"></span></a>
							</dd>
						 </shiro:hasRole>
							
							<dd>
								<a href="javascript:;"
									layuimini-content-href="page/user-password.html"
									data-title="修改密码" data-icon="fa fa-gears">修改密码</a>
							</dd>
							<dd>
								<hr>
							</dd>
							<dd>
								<a href="${pageContext.request.contextPath}/shiro/logout">退出</a>
						  	</dd>
						   </dl>
						</li>
					 <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs"
						lay-unselect><a href="javascript:;" data-bgcolor=""></a></li> 
				</ul>
			</div>
		</div>

		<!--无限极左侧菜单-->
		<div class="layui-side layui-bg-black layuimini-menu-left">
		</div>

		<!--初始化加载层-->
	<!-- 	<div class="layuimini-loader">
			<div class="layuimini-loader-inner"></div>
		</div> -->

		<!--手机端遮罩层-->
		<div class="layuimini-make"></div>

		<!-- 移动导航 -->
		<div class="layuimini-site-mobile">
			<i class="layui-icon"></i>
		</div>



			<div class="layui-body" >
				<iframe id="iframe" style="border:0; width:100%;height: 99%; " name="iframe"  frameborder="0" src="${pageContext.request.contextPath}/admin/index">  </iframe>
			</div>


	</div>
	<script src="${pageContext.request.contextPath}/js/layui.js" charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/js/lay-config.js?v=2.0.0" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js" ></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/view/flatadminlist.js" ></script>
	<script>
	
		layui.use([ 'jquery', 'layer', 'miniAdmin', 'miniTongji' ],
						function() {
							var $ = layui.jquery, layer = layui.layer, miniAdmin = layui.miniAdmin, miniTongji = layui.miniTongji;

							var options = {
								iniUrl : "${pageContext.request.contextPath}/fonts/init.json",  // 初始化接口
								renderPageVersion : true, // 初始化页面是否加版本号
								bgColorDefault : 0, // 主题默认配置
								multiModule : true, // 是否开启多模块
								menuChildOpen : false, // 是否默认展开菜单
								loadingTime : 0, // 初始化加载时间
								pageAnim : true, // 切换菜单动画
							};
							miniAdmin.render(options);

						});
	</script>
</body>
</html>