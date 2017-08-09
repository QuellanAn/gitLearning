package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DoctorNamePO implements RowMapper<DoctorNamePO>{
	
/*	public static final int INFO_TYPE_HEALTH = 1;
	
	public static final int INFO_TYPE_NEWS = 2;*/

	/*public static boolean isValidInfoType(int infoType){
		return infoType == INFO_TYPE_HEALTH || infoType == INFO_TYPE_NEWS;
	}*/
	private String doctorId;
	
	private String doctorName;
	
	private String departmentId;

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
	
	@Override
	public DoctorNamePO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DoctorNamePO dnpo =new DoctorNamePO();
		dnpo.setDoctorId(rs.getString("doctor_id"));
		dnpo.setDoctorName(rs.getString("doctor_name"));
		dnpo.setDepartmentId(rs.getString("department_id"));
		return dnpo;
	
	}
	

	
	
	
	
	

}
