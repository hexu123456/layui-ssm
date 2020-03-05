package com.hx.entity;


/**
 * 管理员角色关系表
 */
public class TblManagerRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long relid;//主键ID
	private Long managerid;//管理员ID
	private Long roleid;//角色ID
	
	public TblManagerRole() {
	}

	public TblManagerRole(Long managerid, Long roleid) {
		this.managerid = managerid;
		this.roleid = roleid;
	}

	public Long getRelid() {
		return this.relid;
	}

	public void setRelid(Long relid) {
		this.relid = relid;
	}

	public Long getManagerid() {
		return this.managerid;
	}

	public void setManagerid(Long managerid) {
		this.managerid = managerid;
	}

	public Long getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
}