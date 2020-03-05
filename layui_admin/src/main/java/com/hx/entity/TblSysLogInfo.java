package com.hx.entity;
import java.util.Date;

/**
 * 日志信息实体表
 */
public class TblSysLogInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;//主键ID
	private String content;//日志内容
	private String ipaddress;//IP地址
	private String type;//日志类型(type:1.用户  2.系统)
	private String status;//是否正常(0.正常  1.异常)
	private Date createdtime=new Date();//创建时间
	
	public TblSysLogInfo(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}