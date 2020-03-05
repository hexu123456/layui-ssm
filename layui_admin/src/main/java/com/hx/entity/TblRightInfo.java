package com.hx.entity;

import java.util.List;

/**
 * 权限信息表
 */
public class TblRightInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long rightid;//权限主键ID
	private String rightname;//权限名称
	private String icon;//图标
	private int sort=0;//排序
	private int isenabled=1;//是否启用(0.禁用  1.启用)
	
	private List<TblResourceInfo> resourlist;//资源集合
	private String resourceNameStr="暂无资源";//资源名称字符串

	/** default constructor */
	public TblRightInfo() {
	}

	/** minimal constructor */
	public TblRightInfo(String rightname) {
		this.rightname = rightname;
	}

	public Long getRightid() {
		return this.rightid;
	}

	public void setRightid(Long rightid) {
		this.rightid = rightid;
	}

	public String getRightname() {
		return this.rightname;
	}

	public void setRightname(String rightname) {
		this.rightname = rightname;
	}
	public List<TblResourceInfo> getResourlist() {
		return resourlist;
	}

	public void setResourlist(List<TblResourceInfo> resourlist) {
		this.resourlist = resourlist;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getIsenabled() {
		return isenabled;
	}

	public void setIsenabled(int isenabled) {
		this.isenabled = isenabled;
	}

	public String getResourceNameStr() {
		return resourceNameStr;
	}

	public void setResourceNameStr(String resourceNameStr) {
		this.resourceNameStr = resourceNameStr;
	}

	@Override
	public String toString() {
		return "TblRightInfo [rightid=" + rightid + ", rightname=" + rightname
				+ ", icon=" + icon + ", sort=" + sort + ", isenabled="
				+ isenabled + "]";
	}
	
}