package com.hx.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.dao.FlatRightDao;
import com.hx.entity.TblResourceInfo;
import com.hx.entity.TblRightInfo;
import com.hx.service.FlatRightService;

@Service
public class FlatRightServiceImpl implements FlatRightService {

	@Autowired
	private FlatRightDao FlatAdminDao;
	@Override
	public List<TblRightInfo> getRightListByAccount(String account)
			throws Exception {
		List<TblResourceInfo> resourlist = null;
		List<TblRightInfo> plist =null;
		try{
			if(!StringUtils.isEmpty(account))
			{
				//通过管理员账号查询权限集合
				plist=FlatAdminDao.getMenuRightListbyAccount(account);
				if(plist!=null && plist.size()>0)
				{
					int index = 0;
					for(TblRightInfo rightinfo:plist)
					{
						//通过权限ID获取资源集合
						resourlist=FlatAdminDao.getResourceListbyRightid(rightinfo.getRightid());
						rightinfo.setSort(index);
						// 把资源注入到权限实体中
						rightinfo.setResourlist(resourlist);
						index++;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			resourlist = null;
		}
		return plist;
	}

}
