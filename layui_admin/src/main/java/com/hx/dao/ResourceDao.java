package com.hx.dao;

import java.util.List;
import java.util.Map;

import com.hx.entity.TblResourceInfo;
import com.hx.entity.TblRightResource;


public interface ResourceDao {
	 //通过管理员账号获取权限菜单集合
	 public List<Map<String,Object>> getResourceList(Map<String,Object> params);
	 //添加资源信息
	 public void addResource(TblResourceInfo tblResourceInfo);
	 //修改资源
	 public void editResource(TblResourceInfo tblResourceInfo);
	 
	 //添加权限与资源信息
	 public void addRightAndResource(TblRightResource resource);
	 
	 //删除资源信息
	 public void delResourceById(long resourid);
	 //删除资源权限信息
	 public void delRightAndResource(long resourid);
	 
	
}
