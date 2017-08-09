// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HisDepartmentDoctor.java

package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DepartmentDoctorPO implements RowMapper<DepartmentDoctorPO> {

	private String doctorId;
	private String departmentId;

	public DepartmentDoctorPO() {
	}

	public DepartmentDoctorPO mapRow(ResultSet rs, int rowCount)
			throws SQLException {
		DepartmentDoctorPO hisDepartmentDoctor = new DepartmentDoctorPO();
		hisDepartmentDoctor.setDoctorId(rs.getString("doctor_id"));
		hisDepartmentDoctor.setDepartmentId(rs.getString("department_id"));
		return hisDepartmentDoctor;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}
