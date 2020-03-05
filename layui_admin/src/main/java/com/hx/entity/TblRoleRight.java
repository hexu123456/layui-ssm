package com.hx.entity;


/**
 * 角色权限关系表
 */
public class TblRoleRight implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long relid;//主键ID
	private Long roleid;//角色ID
	private Long rightid;//权限ID

	/** default constructor */
	public TblRoleRight() {
	}

	/** full constructor */
	public TblRoleRight(Long roleid, Long rightid) {
		this.roleid = roleid;
		this.rightid = rightid;
	}

	public Long getRelid() {
		return this.relid;
	}

	public void setRelid(Long relid) {
		this.relid = relid;
	}

	public Long getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public Long getRightid() {
		return this.rightid;
	}

	public void setRightid(Long rightid) {
		this.rightid = rightid;
	}
}