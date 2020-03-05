package com.hx.dao;

import java.util.List;
import java.util.Map;

import com.hx.entity.User;

public interface UserDao {

	public List<User> selUserList()throws Exception;
	
	public int updateName(Map<String ,Object> map)throws Exception;
	
	public int updateDept( Map<String ,Object> map)throws Exception;
}
