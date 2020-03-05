package com.hx.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.dao.ManagerDao;
import com.hx.entity.TblManagerInfo;
import com.hx.entity.TblManagerRole;
import com.hx.entity.TblRoleInfo;
import com.hx.entity.TblRoleRight;
import com.hx.service.ManagerService;
import com.hx.tools.WebResult;
import com.hx.tools.WebUtils;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDao managerDao;
	@Override
	public WebResult saveFlatManagerInfo(Map<String, Object> map)
			throws Exception {
		String managerid=null;
		String companyname=null;
		String rolename=null;
		String linkman=null;
		String linkphone=null;
		String account=null;
        String password=null;
        String rightids=null;
        
        TblRoleRight roleRight=null;
		String[] rits=null;
        long record=0;
        TblManagerInfo mangerInfo=null;
        TblRoleInfo roleInfo=null;
        TblManagerRole mangerRole=null;
        
        managerid=map.get("managerid").toString();
		rolename=map.get("rolename").toString();
        linkman=map.get("linkman").toString();
        linkphone=map.get("linkphone").toString();
        account=map.get("account").toString();
        password=WebUtils.Md5(map.get("password").toString(),account);
        rightids=map.get("rightids").toString();
        //判断账号是否存在
        record=managerDao.queryManagerCount(account);
        if(record>0)
        {
        	return WebResult.error("该账号已被占用", null);
        }
      //创建平台管理员
  		mangerInfo=new TblManagerInfo();
  		mangerInfo.setMparentid(Long.parseLong(managerid));//父级管理者ID  超管默认0
  		mangerInfo.setType("0");//类型(type:x.平台超管 0.平台管理员  1.物业公司 2.物业管理员)
  		mangerInfo.setEnabled("1");//是否可用 0.禁用  1.可用
  		mangerInfo.setHeadimg("1.jpg");
  		mangerInfo.setCompanyname("平台管理公司");
  		mangerInfo.setLinkman(linkman);
  		mangerInfo.setLinkphone(linkphone);
  		mangerInfo.setAccount(account);
  		mangerInfo.setPassword(password);
  		mangerInfo.setProvinceid(520000);
  		mangerInfo.setProvincename("贵州省");
  		mangerInfo.setCityid(520100);
  		mangerInfo.setCityname("贵阳市");
  		mangerInfo.setCountyid(520102);
  		mangerInfo.setCuntryname("南明区");
  		mangerInfo.setLinkaddress(null);
  		managerDao.savePTManager(mangerInfo);
  		//2.创建角色
		roleInfo=new TblRoleInfo();
		roleInfo.setAccount(account);
		roleInfo.setRolename(rolename);
		managerDao.saveRole(roleInfo);
		//3.创建管理员角色关系
		mangerRole=new TblManagerRole();
		mangerRole.setManagerid(mangerInfo.getManagerid());
		mangerRole.setRoleid(roleInfo.getRoleid());
		managerDao.saveManagerRole(mangerRole);
		//保存角色权限关系
		roleRight=null;
		rits=rightids.split(",");
		for(int i=0;i<rits.length;i++)
		{   
			//5.创建角色权限关系
			roleRight=new TblRoleRight();
			roleRight.setRoleid(roleInfo.getRoleid());
			roleRight.setRightid(Long.parseLong(rits[i]));
			managerDao.saveRoleRight(roleRight);
		}
		return WebResult.success("添加成功", null);
	}
	@Override
	public WebResult editFlatManagerInfo(Map<String, Object> map)
			throws Exception {
		String managerid=null;
		String rolename=null;
        String linkman=null;
        String linkphone=null;
        String account=null;
        String rightids=null;
        TblRoleRight roleRight=null;
		String[] rits=null;
		List<Long> mrolelist=null;
		TblManagerInfo mangerInfo=null;
		TblRoleInfo roleinfo=null;
		try {
			managerid=map.get("managerid").toString();
			rolename=map.get("rolename").toString();
	        linkman=map.get("linkman").toString();
	        linkphone=map.get("linkphone").toString();
	        rightids=map.get("rightids").toString();
	        account=map.get("account").toString();
	        mangerInfo=managerDao.findManagerById(Long.valueOf(managerid));
	        if(mangerInfo!=null){
	        	//修改管理员基本信息
				mangerInfo.setLinkman(linkman);
				mangerInfo.setLinkphone(linkphone);
				managerDao.updateManagerById(mangerInfo);
				//修改账号
				if(!mangerInfo.getAccount().equals(account)){
					   long record=0;
					 //判断账号是否存在
					   record=managerDao.queryManagerCount(account);
			        if(record>0)
			        {
			        	return WebResult.error("该账号已被占用", null);
			        }
			        mangerInfo.setAccount(account);			
			        managerDao.updateManagerById(mangerInfo);
			  }
			 //查询该公司的权限
			 mrolelist=managerDao.managerRoleListByManagerId(Long.valueOf(managerid));
			 if(mrolelist!=null && mrolelist.size()>0)
		     {
				//修改角色名称
				 roleinfo=managerDao.findRoleById(mrolelist.get(0));
				 if(roleinfo!=null)
				 {
					 roleinfo.setRolename(rolename);
					 if(!mangerInfo.getAccount().equals(account)){
						   long record=0;
						 //判断账号是否存在
						   record=managerDao.queryManagerCount(account);
				        if(record>0)
				        {
				        	return WebResult.error("该账号已被占用", null);
				        }
				        roleinfo.setAccount(account);	
				     }
					 managerDao.updateRoleById(roleinfo);
				     //删除该管理权限
					 managerDao.delRoleRightByRoleId(mrolelist.get(0));
					//保存角色权限关系
					roleRight=null;
					rits=rightids.split(",");
					for(int i=0;i<rits.length;i++)
					{   
						//5.创建角色权限关系
						roleRight=new TblRoleRight();
						roleRight.setRoleid(mrolelist.get(0));
						roleRight.setRightid(Long.parseLong(rits[i]));
						managerDao.saveRoleRight(roleRight);
					}
				 }
			 }
	        }
		} catch (Exception e) {
			e.printStackTrace();
			return WebResult.error("修改失败,服务正忙!", null);
		}
		return WebResult.success("修改成功", null);
	}
	@Override
	public void delFlatManagerById(long managerid) throws Exception {
		TblManagerInfo managerInfo=null;
		TblRoleInfo roleInfo=null;
		managerInfo=managerDao.findManagerById(managerid);
		roleInfo=managerDao.findRoleByAccount(managerInfo.getAccount());
		if(managerInfo!=null){
			//删除管理员基本信息
			managerDao.delManagerById(managerid);
			//删除管理员角色基本信息
			managerDao.delManagerRoleByManagerId(managerid);
			//根据账号删除角色信息
			managerDao.delRoleInfoByAccount(managerInfo.getAccount());
			//删除角色关联菜单
			managerDao.delRoleRight(roleInfo.getRoleid());
		}
		
	}

}
