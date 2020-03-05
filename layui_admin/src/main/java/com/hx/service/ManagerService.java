package com.hx.service;

import java.util.Map;

import com.hx.tools.WebResult;

public interface ManagerService {
	//添加平台管理员
	public WebResult saveFlatManagerInfo(Map<String,Object> map)throws Exception;
	//修改平台管理员
	public WebResult editFlatManagerInfo(Map<String,Object> map)throws Exception;
	//删除管理员
	public void delFlatManagerById(long managerid)throws Exception;
}
