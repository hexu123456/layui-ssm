package com.hx.dao;

import java.util.List;
import java.util.Map;

import com.hx.entity.TblManagerInfo;
import com.hx.entity.TblResourceInfo;
import com.hx.entity.TblRightInfo;

public interface FlatRightDao {
	 //通过管理员账号获取权限菜单集合
	 public List<TblRightInfo> getMenuRightListbyAccount(String account) throws Exception;
	 //通过权限ID获取资源集合
	 public List<TblResourceInfo> getResourceListbyRightid(long rightid) throws Exception;
	 //根据账号查找管理员基本信息
	 public TblManagerInfo queryManager(String account)throws Exception;
	 //权限列表
	 public List<TblRightInfo> rightList(Map<String,Object> params)throws Exception;
	 //添加权限
	 public int addRight(TblRightInfo right)throws Exception;
	 //删除权限
	 public int delRightById(long rightid)throws Exception;
	 //查找权限
	 public TblRightInfo findRightById(long rightid)throws Exception;
	 //修改权限
	 public boolean editRight(TblRightInfo right)throws Exception;
}
