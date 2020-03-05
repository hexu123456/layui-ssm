package com.hx.entity;

import java.util.Date;

/**
 * 管理员信息表
 */
public class TblManagerInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private long managerid;//管理员ID
	private long mparentid=0;//父级管理者ID  超管默认0
	private int provinceid;//省份ID
	private int cityid;//市区ID
	private int countyid;//县级ID
	private String provincename;
	private String cityname;
	private String cuntryname;
	
	private String type;//类型(type:x.平台超管  0.平台管理员  1.酒店管理员  )
	private String enabled="1";//是否可用 0.禁用  1.可用
	
	private String companyname;//公司名称
	private String headimg;//公司封面或管理员头像
	private String interestrate="0.00";//利息
	
	private String account;//登陆账号
	private String password;//登录密码
	
	private String linkphone;//联系电话  
	private String linkman;//联系人
	private String linkaddress;//联系地址
	
	private Date logintime=new Date();//登录时间
	private Date createdtime=new Date();//创建时间
	
	private String rolename="未知角色";
	
	

	/** default constructor */
	public TblManagerInfo() {
	}

	public long getManagerid() {
		return managerid;
	}

	public void setManagerid(long managerid) {
		this.managerid = managerid;
	}

	public long getMparentid() {
		return mparentid;
	}

	public void setMparentid(long mparentid) {
		this.mparentid = mparentid;
	}


	public int getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public int getCountyid() {
		return countyid;
	}

	public void setCountyid(int countyid) {
		this.countyid = countyid;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getCuntryname() {
		return cuntryname;
	}

	public void setCuntryname(String cuntryname) {
		this.cuntryname = cuntryname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getInterestrate() {
		return interestrate;
	}

	public void setInterestrate(String interestrate) {
		this.interestrate = interestrate;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLinkphone() {
		return linkphone;
	}

	public void setLinkphone(String linkphone) {
		this.linkphone = linkphone;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkaddress() {
		return linkaddress;
	}

	public void setLinkaddress(String linkaddress) {
		this.linkaddress = linkaddress;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}