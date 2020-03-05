package com.hx.service;

import java.util.List;

import com.hx.entity.TblRightInfo;

public interface FlatRightService {

	//通过管理员账号获取权限菜单集合
	public List<TblRightInfo> getRightListByAccount(String account) throws Exception;
	
}
