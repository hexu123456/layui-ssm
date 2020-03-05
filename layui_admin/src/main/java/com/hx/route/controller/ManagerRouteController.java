package com.hx.route.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerRouteController {
	//管理员列表页面
	@RequestMapping("/admin/flatadminlist")
	public String flatadminlist(){
		return "manager/flatadminlist";
	}
	//打开添加平台管理员页面
	@RequestMapping("/admin/openAddManager")
	public String openAddManager(){
		return "manager/addManager";
	}
	//打开修改平台管理员页面
	@RequestMapping("/admin/openEditManager")
	public String openEditManager(){
		return "manager/editManager";
	}
	//打开修改超管页面
	@RequestMapping("/admin/openEditAdmin")
	public String openEditAdmin(){
		return "manager/editAdmin";
	}
	//打开重置平台管理员密码页面
	@RequestMapping("/admin/openResertPwd")
	public String openResertPwd(){
		return "manager/resertpassword";
	}
}
