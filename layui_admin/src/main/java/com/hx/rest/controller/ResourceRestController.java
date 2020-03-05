package com.hx.rest.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hx.dao.FlatRightDao;
import com.hx.dao.ResourceDao;
import com.hx.entity.TblResourceInfo;
import com.hx.entity.TblRightInfo;
import com.hx.service.ResourceService;
import com.hx.tools.ResultVo;
import com.hx.tools.WebResult;
import com.hx.tools.WebUtils;
@Controller
public class ResourceRestController {
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private FlatRightDao rightDao;
	@Autowired
	private ResourceService resourceService;
	@RequestMapping("/admin/getresourceList")
	@ResponseBody
	public ResultVo getresourceList(HttpServletResponse response,HttpServletRequest request,
			@RequestParam("page") Integer pageNo,
			@RequestParam("limit") Integer pageSize,
			@RequestParam(value="resourname",defaultValue="")String resourname
			) throws Exception{
				//实例化封装类
		ResultVo rv=new ResultVo();
		try {
			Map<String,Object> params=new WebUtils().getMap();
			params.put("resourname", resourname);
		    //实例分页插件
			PageHelper.startPage(pageNo, pageSize);
			 List<Map<String,Object>>  resourceList = resourceDao.getResourceList(params);
			PageInfo<Map<String,Object>> pm = new PageInfo<Map<String,Object>>(resourceList,pageSize);
			rv.setCount(pm.getTotal());//往封装类存入数据量
			rv.setData(pm.getList());//往封装类存入数据
			rv.setCode("0");
		} catch (Exception e) {
			rv.setCode("1");
			e.printStackTrace();
		}
		return rv;
	}
	
	@RequestMapping("/admin/addResoure")
	@ResponseBody
	public WebResult addResoure(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="resourname",defaultValue="")String resourname,
			@RequestParam(value="url",defaultValue="")String url,
			@RequestParam(value="rightid",defaultValue="")long rightid
			){
		WebResult webResult=null;
		try {
			resourceService.addResource(resourname, url, rightid);
			webResult=WebResult.success("添加成功", null);
		} catch (Exception e) {
			webResult=WebResult.error("添加失败", null);
			e.printStackTrace();
		}
		return webResult;
	}
	@RequestMapping("/admin/editResource")
	@ResponseBody
	public WebResult editResource(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="resourname",defaultValue="")String resourname,
			@RequestParam(value="url",defaultValue="")String url,
			@RequestParam(value="resourid",defaultValue="")long resourid
			){
		WebResult webResult=null;
		try {
			TblResourceInfo  tblResourceInfo=new TblResourceInfo();
			tblResourceInfo.setResourid(resourid);
			tblResourceInfo.setResourname(resourname);
			tblResourceInfo.setUrl(url);
			resourceDao.editResource(tblResourceInfo);
			webResult=WebResult.success("修改成功", null);
		} catch (Exception e) {
			webResult=WebResult.error("修改失败", null);
			e.printStackTrace();
		}
		return webResult;
	}
		//删除资源信息
		@RequestMapping("/admin/delResourceById")
		@ResponseBody
		public WebResult delResourceById(HttpServletResponse response,HttpServletRequest request,
				@RequestParam(value="resourid",defaultValue="")long resourid
				){
			WebResult webResult=null;
			try {
				 resourceService.delResource(resourid);
				webResult=WebResult.success("删除成功", null);
			} catch (Exception e) {
				webResult=WebResult.error("删除失败", null);
				e.printStackTrace();
			}
			return webResult;
		}
	//查询权限列表
	@RequestMapping("/admin/queryRightList")
	@ResponseBody
	public WebResult queryRightList(HttpServletResponse response,HttpServletRequest request){
		WebResult webResult=null;
		try {
			List<TblRightInfo> rightList = rightDao.rightList(null);
			webResult=WebResult.success("请求成功!", rightList);
		} catch (Exception e) {
			webResult=WebResult.error("请求失败!", null);
		}
		return webResult;
	}
}
