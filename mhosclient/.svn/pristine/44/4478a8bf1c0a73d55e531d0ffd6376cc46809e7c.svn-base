package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class DoctorPO implements RowMapper<DoctorPO>{
	
	private String doctorId;
	
	private String doctorName;
	
	private String job;
	//职称名字
	private String jobName;
	private String speciality;
	//编号
	private String code;
	private int number;
	
	private String introduction;
	
	private String hospital;
	
	private String departmentId;
	
	private String departmentName;
	private String deptName;
	private int isExpert; 
	private int sex;
	private String photo;
	
	private String sortNumber;//排序值
	private String integrity;
	private int status;
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
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
	

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getIsExpert() {
		return isExpert;
	}

	public void setIsExpert(int isExpert) {
		this.isExpert = isExpert;
	}

	
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(String sortNumber) {
		this.sortNumber = sortNumber;
	}

	public String getIntegrity() {
		return integrity;
	}

	public void setIntegrity(String integrity) {
		this.integrity = integrity;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public DoctorPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DoctorPO d = new DoctorPO();
		d.setDoctorId(StringUtils.trim(rs.getString("doctor_id")));
		d.setDoctorName(StringUtils.trim(rs.getString("doctor_name")));
		d.setJob(StringUtils.trim(rs.getString("job")));
		try{
			d.setNumber(rs.getInt("number"));
		}catch(SQLException e){}
		d.setSpeciality(StringUtils.trim(rs.getString("speciality")));
		d.setIntroduction(StringUtils.trim(rs.getString("introduction")));
		d.setHospital(StringUtils.trim(rs.getString("hospital")));
		d.setCode(StringUtils.trim(rs.getString("code")));
		d.setIsExpert(rs.getInt("isExpert"));
		d.setSex(rs.getInt("sex"));
		d.setPhoto(StringUtils.trim(rs.getString("photo")));
		d.setSortNumber(StringUtils.trim(rs.getString("sortNumber")));
		d.setIntegrity(StringUtils.trim(rs.getString("integrity")));
		d.setStatus(rs.getInt("status"));
		try{
			d.setDepartmentId(StringUtils.trim(rs.getString("department_id")));
		}catch(SQLException e){};
		try{
			d.setDepartmentName(StringUtils.trim(rs.getString("department_name")));
			d.setDeptName(StringUtils.trim(rs.getString("dept_name")));
		}catch(SQLException e){}
		try{
			d.setJobName(StringUtils.trim(rs.getString("jobName")));
		}catch(SQLException e){}
		return d;
	}

}
