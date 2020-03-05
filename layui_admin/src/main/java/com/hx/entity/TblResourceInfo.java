package com.hx.entity;


/**
 * 资源信息表
 */
public class TblResourceInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long resourid;//主键ID
	private String resourname;//资源名称
	private String url;//访问地址
	
	private long rightid=0;
	private int show=0;//是否显示被选中
	/** default constructor */
	public TblResourceInfo() {
	}

	/** full constructor */
	public TblResourceInfo(String resourname, String url) {
		this.resourname = resourname;
		this.url = url;
	}

	public Long getResourid() {
		return this.resourid;
	}

	public void setResourid(Long resourid) {
		this.resourid = resourid;
	}

	public String getResourname() {
		return this.resourname;
	}

	public void setResourname(String resourname) {
		this.resourname = resourname;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getRightid() {
		return rightid;
	}

	public void setRightid(long rightid) {
		this.rightid = rightid;
	}

	public int getShow() {
		return show;
	}

	public void setShow(int show) {
		this.show = show;
	}
}