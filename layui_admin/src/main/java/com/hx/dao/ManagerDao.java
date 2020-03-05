package com.hx.dao;

import java.util.List;
import java.util.Map;

import com.hx.entity.TblManagerInfo;
import com.hx.entity.TblManagerRole;
import com.hx.entity.TblRightInfo;
import com.hx.entity.TblRoleInfo;
import com.hx.entity.TblRoleRight;

public interface ManagerDao {
	//查询超管列表
	public List<Map<String,Object>> managerList(Map<String,Object> params) throws Exception;
	//查询添加时当前登录员权限集合
	public List<TblRightInfo> queryAddMangerResource(long managerid)throws Exception;
	//查询账号是否存在
	public int queryManagerCount(String account)throws Exception;
	//保存平台管理员,返回主键
	public int savePTManager(TblManagerInfo manager)throws Exception;
	//保存角色信息
	public int saveRole(TblRoleInfo role)throws Exception;
	//保存角色管理员关系
	public int saveManagerRole(TblManagerRole managerRole)throws Exception;
	//保存角色权限
	public int saveRoleRight(TblRoleRight roleRight)throws Exception;
	//根据管理员ID查询平台管理员基本信息
	public TblManagerInfo findManagerById(long managerid)throws Exception;
	//查询当前登录人拥有的权限集
	public List<TblRightInfo> getParentRightListbyManagerId(long managerid)throws Exception;
	//查询修改账号拥有的权限集
	public List<TblRightInfo> getSelfRightListbyManagerId(long managerid) throws Exception;
	//修改管理员基本信息
	public int updateManagerById(TblManagerInfo manager)throws Exception;
	//查询该公司的权限
	public List<Long> managerRoleListByManagerId(Long managerid)throws Exception;
	//查询角色基本信息
	public TblRoleInfo findRoleById(long roleid)throws Exception;
	//查询角色基本信息
	public TblRoleInfo findRoleByAccount(String account)throws Exception;
	//修改角色基本信息
	public int updateRoleById(TblRoleInfo role)throws Exception;
	//通过角色ID删除权限关系集
	public void delRoleRightByRoleId(long roleid)throws Exception;
	//根据id删除管理员基本信息
	public int delManagerById(long managerid)throws Exception;
	//根据managerid删除管理员角色信息
	public int delManagerRoleByManagerId(long managerid)throws Exception;
	//根据账号删除角色信息
	public int delRoleInfoByAccount(String account)throws Exception;
	//删除角色关联菜单
  	public void delRoleRight(long roleid)throws Exception;
  	//查询所有权限集合
  	public List<TblRightInfo> queryAdminRight()throws Exception;
	
	
}
