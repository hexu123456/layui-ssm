package com.hx.route.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResourceRouteController {
	//资源列表页面
	@RequestMapping("/admin/flatresourcelist")
	public String flatresourcelist(){
		return "resource/resourcelist";
	}
	
	//打开添加资源页面
	@RequestMapping("/admin/openResource")
	public String openResource(){
		return "resource/addResource";
	}
	//打开修改资源页面
	@RequestMapping("/admin/openEditResource")
	public String openEditResource(){
		return "resource/editResource";
	}
}
