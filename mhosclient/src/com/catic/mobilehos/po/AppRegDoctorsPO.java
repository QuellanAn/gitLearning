package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class AppRegDoctorsPO implements RowMapper<AppRegDoctorsPO>{
	
	private String userId;
	
	private String doctorId;
	
	private String doctorName;
	
	private String departmentId;
	
	private String departmentName;
	
	private Timestamp lastdate;
	
	private DoctorPO doctor;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Timestamp getLastdate() {
		return lastdate;
	}

	public void setLastdate(Timestamp lastdate) {
		this.lastdate = lastdate;
	}
	

	public DoctorPO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorPO doctor) {
		this.doctor = doctor;
	}

	@Override
	public AppRegDoctorsPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AppRegDoctorsPO ard = new AppRegDoctorsPO();
		ard.setUserId(StringUtils.trim(rs.getString("user_id")));
		ard.setDepartmentId(StringUtils.trim(rs.getString("department_id")));
		ard.setDepartmentName(StringUtils.trim(rs.getString("department_name")));
		ard.setDoctorId(StringUtils.trim(rs.getString("doctor_id")));
		ard.setDoctorName(StringUtils.trim(rs.getString("doctor_name")));
		ard.setLastdate(rs.getTimestamp("lastdate"));
		DoctorPO doctor = new DoctorPO();
		try{
			doctor = doctor.mapRow(rs, rowNum);
			ard.setDoctor(doctor);
		}catch(SQLException e){};
		return ard;
	}
	

}
