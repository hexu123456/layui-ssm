package com.hx.route.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FlatRightRouteController {
	//权限列表页面
	@RequestMapping("/admin/flatrightlist")
	public String testable(){
		return "flatadmin/flatrightlist";
	}
	//打开添加权限页面
	@RequestMapping("/admin/openAddRight")
	public String openAddRight(){
		return "flatadmin/addRight";
	}
	//打开修改权限页面
	@RequestMapping("/admin/openEditRight")
	public String openEditRight(){
		return "flatadmin/editRight";
	}
}
