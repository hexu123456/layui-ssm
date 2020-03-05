package com.hx.entity;

import java.util.Date;

/**
 * 角色信息表
 */
public class TblRoleInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long roleid;//角色主键ID
	private String rolename;//角色名称
	private String account;//管理员账号
	private Date createdtime=new Date();//创建时间
	
	public TblRoleInfo() {
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}
}