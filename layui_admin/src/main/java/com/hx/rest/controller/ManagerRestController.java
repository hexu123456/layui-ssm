package com.hx.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hx.dao.ManagerDao;
import com.hx.entity.TblManagerInfo;
import com.hx.entity.TblRightInfo;
import com.hx.entity.TblRoleInfo;
import com.hx.service.ManagerService;
import com.hx.tools.ResultVo;
import com.hx.tools.WebResult;
import com.hx.tools.WebUtils;

@Controller
public class ManagerRestController {
	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private ManagerService managerService;
	//超管列表
	@RequestMapping("/admin/queryManagerList")
	@ResponseBody
	public ResultVo queryManagerList(HttpServletResponse response,HttpServletRequest request,
			@RequestParam("page") Integer pageNo,
			@RequestParam("limit") Integer pageSize,
			@RequestParam(value="account",defaultValue="")String account
			) throws Exception{
				//实例化封装类
		ResultVo rv=new ResultVo();
		try {
			Map<String,Object> params=new WebUtils().getMap();
			params.put("account", account);
		    //实例分页插件
			PageHelper.startPage(pageNo, pageSize);
			List<Map<String,Object>> rightList = managerDao.managerList(params);
			PageInfo<Map<String,Object>> pm = new PageInfo<Map<String,Object>>(rightList,pageSize);
			rv.setCount(pm.getTotal());//往封装类存入数据量
			rv.setData(pm.getList());//往封装类存入数据
			rv.setCode("0");
		} catch (Exception e) {
			rv.setCode("1");
			e.printStackTrace();
		}
		return rv;
	}
	
	//查询添加时当前登录员权限集合
	@RequestMapping("admin/queryAddMangerResource")
	@ResponseBody
	public WebResult queryAddMangerResource(HttpServletRequest request,HttpSession session) throws Exception{
		WebResult webResult=null;
		String ecatehtml="";
		Map<String,Object> map=WebUtils.getMap();
		String managerid=session.getAttribute("managerid").toString();//当前管理员ID
		try {
			
			 List<TblRightInfo> resourceList = managerDao.queryAddMangerResource(Long.valueOf(managerid));
			 if(resourceList!=null && resourceList.size()>0)
				{
					int i=0;
					//ecatehtml+="<div class='layui-input-block'>";
					for(TblRightInfo rightinfo:resourceList)
					{
						i++;
						ecatehtml+="<input type='checkbox' name='erightid' value='e"+rightinfo.getRightid()+"' id='e"+rightinfo.getRightid()+"' title='"+rightinfo.getRightname()+"'/>";
						if(i%3==0)
							ecatehtml+="<br>";
					}
					//ecatehtml+=" </label>";
				}
				map.put("ecatehtml",ecatehtml);
				webResult=WebResult.success("请求成功",map);
			
		} catch (Exception e) {
			
		}
		return webResult;
	}
	//查询修改时当前登录员权限集合
	@RequestMapping("admin/queryEditMangerResource")
	@ResponseBody
	public WebResult queryEditMangerResource(HttpServletRequest request,HttpSession session,
			@RequestParam(value="managerid" ,defaultValue="")long managerid) throws Exception{
		WebResult webResult=null;
		TblManagerInfo managerinfo=null;
		
		List<TblRightInfo> parentRightList = null;
		List<TblRightInfo> selfRightList=null;
		List<Long> rightidlist=new ArrayList<Long>();
		String ecatehtml="";
		Map<String,Object> map=WebUtils.getMap();
		String manager_id=session.getAttribute("managerid").toString();//当前管理员ID
		try {
			
			if(managerid>0){
				managerinfo=managerDao.findManagerById(managerid);
				if(managerinfo!=null){
					//查询当前登录人拥有的权限集
					parentRightList=managerDao.getParentRightListbyManagerId(Long.valueOf(manager_id));
					//查询修改账号拥有的权限集
					selfRightList=managerDao.getSelfRightListbyManagerId(managerinfo.getManagerid());
					for(TblRightInfo selfright:selfRightList){
						rightidlist.add(selfright.getRightid());
					}
					if(parentRightList!=null && parentRightList.size()>0)
					{
						int i=0;
						for(TblRightInfo rightinfo:parentRightList)
						{
							i++;
							if(rightidlist.contains(rightinfo.getRightid()))
				    		{
								ecatehtml+="<input type='checkbox' name='erightid' value='e"+rightinfo.getRightid()+"' id='e"+rightinfo.getRightid()+"' checked='checked' title='"+rightinfo.getRightname()+"' />";
				    		}else
				    		{
								ecatehtml+="<input type='checkbox' name='erightid' value='e"+rightinfo.getRightid()+"' id='e"+rightinfo.getRightid()+"' title='"+rightinfo.getRightname()+"'/>";
				    		}
							if(i%3==0)
								ecatehtml+="<br>";
						}
					}
					map.put("ecatehtml",ecatehtml);
					return WebResult.success("请求成功",map);
				}
			}
			webResult=WebResult.error("服务正忙",null);
		} catch (Exception e) {
			webResult=WebResult.error("服务正忙",null);
			e.printStackTrace();
		}
		return webResult;
	}
	//查询修改时超管权限集合
	@RequestMapping("admin/queryEditAdminrResource")
	@ResponseBody
	public WebResult queryEditAdminrResource(HttpServletRequest request,HttpSession session) throws Exception{
		WebResult webResult=null;
		TblManagerInfo managerinfo=null;
		
		List<TblRightInfo> adminRightList = null;
		List<TblRightInfo> selfRightList=null;
		List<Long> rightidlist=new ArrayList<Long>();
		String ecatehtml="";
		Map<String,Object> map=WebUtils.getMap();
		String manager_id=session.getAttribute("managerid").toString();//当前管理员ID
		try {
			
			if(Long.valueOf(manager_id)>0){
				managerinfo=managerDao.findManagerById(Long.valueOf(manager_id));
				if(managerinfo!=null){
					//查询所有的权限集
					adminRightList=managerDao.queryAdminRight();
					for (TblRightInfo r : adminRightList) {
						System.out.println(r.toString());
					}
					//查询修改账号拥有的权限集
					selfRightList=managerDao.getSelfRightListbyManagerId(managerinfo.getManagerid());
					for(TblRightInfo selfright:selfRightList){
						rightidlist.add(selfright.getRightid());
					}
					if(adminRightList!=null && adminRightList.size()>0)
					{
						int i=0;
						for(TblRightInfo rightinfo:adminRightList)
						{
							i++;
							if(rightidlist.contains(rightinfo.getRightid()))
							{
								ecatehtml+="<input type='checkbox' name='erightid' value='e"+rightinfo.getRightid()+"' id='e"+rightinfo.getRightid()+"' checked='checked' title='"+rightinfo.getRightname()+"' />";
							}else
							{
								ecatehtml+="<input type='checkbox' name='erightid' value='e"+rightinfo.getRightid()+"' id='e"+rightinfo.getRightid()+"' title='"+rightinfo.getRightname()+"'/>";
							}
							if(i%3==0)
								ecatehtml+="<br>";
						}
					}
					TblRoleInfo roleInfo = managerDao.findRoleByAccount(managerinfo.getAccount());
					if(roleInfo!=null){
						managerinfo.setRolename(roleInfo.getRolename());
					}
					map.put("ecatehtml",ecatehtml);
					map.put("managerinfo",managerinfo);
					return WebResult.success("请求成功",map);
				}
			}
			webResult=WebResult.error("服务正忙",null);
		} catch (Exception e) {
			webResult=WebResult.error("服务正忙",null);
			e.printStackTrace();
		}
		return webResult;
	}
	//添加平台管理员权限
	@RequestMapping("admin/saveFlatManagerInfo")
	@ResponseBody
	public WebResult saveFlatManagerInfo(HttpServletRequest request,HttpSession session,
			@RequestParam(value="rolename" ,defaultValue="")String rolename,
			@RequestParam(value="linkman" ,defaultValue="")String linkman,
			@RequestParam(value="linkphone" ,defaultValue="")String linkphone,
			@RequestParam(value="account" ,defaultValue="")String account,
			@RequestParam(value="password" ,defaultValue="")String password,
			@RequestParam(value="rightids" ,defaultValue="",required=false)String rightids) throws Exception{
		WebResult webResult=null;
		Map<String,Object> map=WebUtils.getMap();
		String managerid=session.getAttribute("managerid").toString();
		try {
			map.put("managerid",managerid);
			map.put("rolename",rolename.trim().replaceAll(" ", ""));
			map.put("linkman",linkman.trim().replaceAll(" ", ""));
			map.put("linkphone",linkphone.trim().replaceAll(" ", ""));
			map.put("account",account.trim().replaceAll(" ", ""));
			map.put("password",password);
			map.put("rightids",rightids);
			return managerService.saveFlatManagerInfo(map);
		} catch (Exception e) {
			webResult=WebResult.error("服务正忙",null);
			e.printStackTrace();
		}
		return webResult;
	}
	//修改平台管理员权限
	@RequestMapping("admin/editFlatManagerInfo")
	@ResponseBody
	public WebResult editFlatManagerInfo(HttpServletRequest request,HttpSession session,
			@RequestParam(value="managerid" ,defaultValue="")Long managerid,
			@RequestParam(value="rolename" ,defaultValue="")String rolename,
			@RequestParam(value="linkman" ,defaultValue="")String linkman,
			@RequestParam(value="linkphone" ,defaultValue="")String linkphone,
			@RequestParam(value="account" ,defaultValue="")String account,
			@RequestParam(value="rightids" ,defaultValue="")String rightids) throws Exception{
		WebResult webResult=null;
		Map<String,Object> map=WebUtils.getMap();
		try {
			map.put("managerid",managerid);
			map.put("rolename",rolename.trim().replaceAll(" ", ""));
			map.put("linkman",linkman.trim().replaceAll(" ", ""));
			map.put("linkphone",linkphone.trim().replaceAll(" ", ""));
			map.put("account",account.trim().replaceAll(" ", ""));
			map.put("rightids",rightids);
			return managerService.editFlatManagerInfo(map);
		} catch (Exception e) {
			webResult=WebResult.error("服务正忙",null);
			e.printStackTrace();
		}
		return webResult;
	}
	// 删除平台管理员信息
	@RequestMapping(value = "/admin/delFlatManagerById",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public WebResult delFlatManagerById(HttpServletRequest request,
			@RequestParam(value="managerid" ,defaultValue="")long managerid
			)
	{
		WebResult webResult=null;
		try{
			if(managerid>0)
			{
				managerService.delFlatManagerById(managerid);
				webResult=WebResult.success("删除成功",null);
			}else
			{
				webResult=WebResult.error("服务正忙",null);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			webResult=WebResult.error("服务正忙",null);
		}
		return webResult;
	}
	//重置密码
	@RequestMapping(value = "/admin/resertPwd",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public WebResult resertPwd(HttpServletRequest request,
			@RequestParam(value="managerid" ,defaultValue="")long managerid,
			@RequestParam(value="password" ,defaultValue="")String password
			)
	{
		WebResult webResult=null;
		try{
			if(managerid>0)
			{
				TblManagerInfo managerInfo = managerDao.findManagerById(managerid);
				if(managerInfo!=null){
					managerInfo.setPassword(WebUtils.Md5(password, managerInfo.getAccount()));
					int count = managerDao.updateManagerById(managerInfo);
					if(count>0)
						return WebResult.success("重置密码成功",null);
				}
			}
			webResult=WebResult.error("服务正忙",null);
		}catch(Exception e)
		{
			e.printStackTrace();
			webResult=WebResult.error("服务正忙",null);
		}
		return webResult;
	}
}
