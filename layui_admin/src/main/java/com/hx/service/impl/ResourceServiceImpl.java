package com.hx.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.dao.ResourceDao;
import com.hx.entity.TblResourceInfo;
import com.hx.entity.TblRightResource;
import com.hx.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao dao;
	@Override
	public void addResource(String resourname,String url,long rightid)
			throws Exception {
		TblResourceInfo tblResourceInfo=new TblResourceInfo();
		tblResourceInfo.setResourname(resourname);
		tblResourceInfo.setUrl(url);
		dao.addResource(tblResourceInfo);
		TblRightResource resource=new TblRightResource();
		resource.setResourid(tblResourceInfo.getResourid());
		resource.setRightid(rightid);
		dao.addRightAndResource(resource);
		
	}
	@Override
	public void delResource(long resourid){
		dao.delResourceById(resourid);
		dao.delRightAndResource(resourid);
	}
}
