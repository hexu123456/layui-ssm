package com.hx.entity;

public class User {

	private int id;
	private String name;
	private String dept;
	private String website;
	private String phone;
	private Employee employee;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public User(int id, String name, String dept, String website, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.website = website;
		this.phone = phone;
	}
	public User(String name, String dept, String website, String phone) {
		super();
		this.name = name;
		this.dept = dept;
		this.website = website;
		this.phone = phone;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dept=" + dept
				+ ", website=" + website + ", phone=" + phone + "]";
	}
	
}
