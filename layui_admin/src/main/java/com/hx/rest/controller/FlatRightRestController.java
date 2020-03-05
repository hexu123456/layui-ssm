package com.hx.rest.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hx.dao.FlatRightDao;
import com.hx.entity.TblRightInfo;
import com.hx.service.FlatRightService;
import com.hx.tools.ResultVo;
import com.hx.tools.WebResult;
import com.hx.tools.WebUtils;

@Controller
public class FlatRightRestController {

	@Autowired
	private FlatRightService flatAdminService;
	@Autowired
	private FlatRightDao flatAdminDao;
	@RequestMapping("/admin/loadMenu")
	@ResponseBody
	public WebResult loadMenu(HttpSession session){
		WebResult webResult=null;
		try {
			//类型(type:x.平台超管 0.平台管理员  1.物业公司 2.物业管理员)
			//查询管理员权限菜单列表
			List<TblRightInfo> plist = flatAdminService.getRightListByAccount("gzjojo");
			webResult=webResult.success("加载菜单成功", plist);
		} catch (Exception e) {
			webResult=WebResult.error("服务正忙", null);
		}
		return webResult;
	}
	//权限列表
	@RequestMapping("/admin/rightList")
	@ResponseBody
	public ResultVo rightList(HttpServletResponse response,HttpServletRequest request,
			@RequestParam("page") Integer pageNo,
			@RequestParam("limit") Integer pageSize,
			@RequestParam(value="rightname",defaultValue="")String rightname
			) throws Exception{
				//实例化封装类
		ResultVo rv=new ResultVo();
		try {
			Map<String,Object> params=new WebUtils().getMap();
			params.put("rightname", rightname);
		    //实例分页插件
			PageHelper.startPage(pageNo, pageSize);
			List<TblRightInfo> rightList = flatAdminDao.rightList(params);
			PageInfo<TblRightInfo> pm = new PageInfo<TblRightInfo>(rightList,pageSize);
			rv.setCount(pm.getTotal());//往封装类存入数据量
			rv.setData(pm.getList());//往封装类存入数据
			rv.setCode("0");
		} catch (Exception e) {
			rv.setCode("1");
			e.printStackTrace();
		}
		return rv;
	}
	//添加权限
	@RequestMapping("/admin/addRight")
	@ResponseBody
	public WebResult addRight(HttpServletResponse response,HttpServletRequest request,
			TblRightInfo right
			){
		WebResult webResult=null;
		try {
			int count = flatAdminDao.addRight(right);
			if(count>0)
				webResult=WebResult.success("添加成功", null);
			else 
				webResult=WebResult.error("添加失败", null);
		} catch (Exception e) {
			webResult=WebResult.error("添加失败", null);
			e.printStackTrace();
		}
		return webResult;
	}
	//删除权限
	@RequestMapping("/admin/delRightById")
	@ResponseBody
	public WebResult delRightById(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="rightid",defaultValue="")long rightid
			){
		WebResult webResult=null;
		try {
			int count = flatAdminDao.delRightById(rightid);
			if(count>0)
				webResult=WebResult.success("删除成功", null);
			else 
				webResult=WebResult.error("删除失败", null);
		} catch (Exception e) {
			webResult=WebResult.error("删除失败", null);
			e.printStackTrace();
		}
		return webResult;
	}
	//修改权限
	@RequestMapping("/admin/editRight")
	@ResponseBody
	public WebResult editRight(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="rightid",defaultValue="")long rightid,
			@RequestParam(value="rightname",defaultValue="")String rightname,
			@RequestParam(value="icon",defaultValue="")String icon
			){
		WebResult webResult=null;
		TblRightInfo rightInfo=null;
		try {
			rightInfo = flatAdminDao.findRightById(rightid);
			if(rightInfo!=null){
				rightInfo.setRightname(rightname);
				rightInfo.setIcon(icon);
				boolean editRight = flatAdminDao.editRight(rightInfo);
				if(editRight)
				  return WebResult.success("修改成功", null);
				return WebResult.error("修改失败", null);
			}
			webResult=WebResult.error("修改失败", null);
		} catch (Exception e) {
			webResult=WebResult.error("修改失败", null);
			e.printStackTrace();
		}
		return webResult;
	}
	
}
