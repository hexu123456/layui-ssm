package com.hx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.dao.UserDao;
import com.hx.entity.User;
import com.hx.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Override
	public List<User> selUserList() throws Exception {
		return userDao.selUserList();
	}
	@Override
	public void xiugaiUser() throws Exception {
		Map<String,Object> mapUpateName=new HashMap<String, Object>();
		mapUpateName.put("name", "hexu");
		mapUpateName.put("id", 1);
		int updateName = userDao.updateName(mapUpateName);
		Map<String,Object> mapUpateDept=new HashMap<String, Object>();
		mapUpateDept.put("dept", "ccAA");
		mapUpateDept.put("id", 2);
		int updateDept = userDao.updateDept(mapUpateDept);
		System.out.println(updateName+"--"+updateDept);
		int i=1/0;
	}

}
