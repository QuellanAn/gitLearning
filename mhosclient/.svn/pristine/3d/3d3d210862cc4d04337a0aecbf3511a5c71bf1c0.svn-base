package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class DepartmentNamePO implements RowMapper<DepartmentNamePO> {
	private String departmentId;
	private String departmentName;
	private String doctorId;
	private String doctorName;

	@Override
	public DepartmentNamePO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		DepartmentNamePO dpnpo = new DepartmentNamePO();
		dpnpo.setDepartmentId(rs.getString("department_id"));
		dpnpo.setDepartmentName(rs.getString("department_name"));
		dpnpo.setDoctorId(rs.getString("doctor_id"));
		dpnpo.setDoctorName(rs.getString("doctor_name"));
		
		return dpnpo;
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

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

}
