package com.hx.shiro;

import java.util.LinkedHashMap;

//处理拦截和不拦截地址
public class FactoryfilterChainDefinitions {

	
	public LinkedHashMap<String, String> FilterChainDefinitionMap()
	{
		LinkedHashMap<String, String> map=new LinkedHashMap<String, String>();
		//anon:不拦截地址
		//authc:拦截地址
		map.put("/login.jsp", "anon");
		map.put("/assets/**", "anon");
		map.put("/images/**", "anon");
		map.put("/login/auth", "anon");
		map.put("/css/**", "anon");
		map.put("/js/**", "anon");
		map.put("/img/**", "anon");
		map.put("/fonts/**", "anon");
		map.put("/font/**", "anon");
		map.put("/shiro/logout", "logout");
		
		map.put("/admin/index", "anon");
		map.put("/** ", "authc");
		return map;
	}
}
