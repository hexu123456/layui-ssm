package com.hx.service;

import java.util.List;

import com.hx.entity.User;

public interface UserService {

	public List<User> selUserList()throws Exception;
	//测试事物
	public void xiugaiUser()throws Exception;
}
