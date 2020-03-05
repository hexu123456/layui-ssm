package com.hx.entity;


/**
 * 权限资源关系表
 */
public class TblRightResource implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long relid;//主键
	private Long rightid;//权限ID
	private Long resourid;//资源ID
	
	/** default constructor */
	public TblRightResource() {
	}

	/** full constructor */
	public TblRightResource(Long rightid, Long resourid) {
		this.rightid = rightid;
		this.resourid = resourid;
	}

	public Long getRelid() {
		return this.relid;
	}

	public void setRelid(Long relid) {
		this.relid = relid;
	}

	public Long getRightid() {
		return this.rightid;
	}

	public void setRightid(Long rightid) {
		this.rightid = rightid;
	}

	public Long getResourid() {
		return this.resourid;
	}

	public void setResourid(Long resourid) {
		this.resourid = resourid;
	}
}