package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class DepartmentPO implements RowMapper<DepartmentPO>{
	//科室id
	private int id;
	//科室编号
	private String departmentId;
	
	private String departmentName;
	
	private String parentId;
	
	private String departmentAddr;
	//科室介绍
	private String introduction;
	//联系电话
    private String phone;
	private int department_status;

	private int app_reg_status;
	
	//排序编号
	private String display_no;
	
	public int getDepartment_status() {
		return department_status;
	}

	public void setDepartment_status(int department_status) {
		this.department_status = department_status;
	}

	public int getApp_reg_status() {
		return app_reg_status;
	}

	public void setApp_reg_status(int app_reg_status) {
		this.app_reg_status = app_reg_status;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getDepartmentAddr() {
		return departmentAddr;
	}

	public void setDepartmentAddr(String departmentAddr) {
		this.departmentAddr = departmentAddr;
	}

	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	
	

	public String getDisplay_no() {
		return display_no;
	}

	public void setDisplay_no(String display_no) {
		this.display_no = display_no;
	}

	@Override
	public DepartmentPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DepartmentPO d = new DepartmentPO();
		d.setId(rs.getInt("id"));
		d.setDepartmentId(StringUtils.trim(rs.getString("department_id")));
		d.setDepartmentName(StringUtils.trim(rs.getString("department_name")));
		d.setParentId(StringUtils.trim(rs.getString("parent_id")));
		d.setPhone(StringUtils.trim(rs.getString("phone")));
		d.setDepartmentAddr(StringUtils.trim(rs.getString("department_addr")));
		d.setIntroduction(rs.getString("introduction"));
		d.setDepartment_status(rs.getInt("department_status"));
		d.setApp_reg_status(rs.getInt("app_reg_status"));
		d.setDisplay_no(rs.getString("display_no"));
		return d;
	}
	
	
	
	

}
