package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class ReportsPO implements RowMapper<ReportsPO> {
	
	private String reportId;
	
	private String userId;
	
	private String patientId;
	
	private String patientName;
	
	private String departmentName;
	
	private java.sql.Date doctorDate;
	
	private String chkName;
	
	private Timestamp chkDate;
	
	private int reportStatus;
	
	private int reportType;
	
	private String cdepartmentName;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public java.sql.Date getDoctorDate() {
		return doctorDate;
	}

	public void setDoctorDate(java.sql.Date doctorDate) {
		this.doctorDate = doctorDate;
	}

	public String getChkName() {
		return chkName;
	}

	public void setChkName(String chkName) {
		this.chkName = chkName;
	}

	public Timestamp getChkDate() {
		return chkDate;
	}

	public void setChkDate(Timestamp chkDate) {
		this.chkDate = chkDate;
	}

	public int getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(int reportStatus) {
		this.reportStatus = reportStatus;
	}

	public int getReportType() {
		return reportType;
	}

	public void setReportType(int reportType) {
		this.reportType = reportType;
	}
	
	public String getCdepartmentName() {
		return cdepartmentName;
	}

	public void setCdepartmentName(String cdepartmentName) {
		this.cdepartmentName = cdepartmentName;
	}

	@Override
	public ReportsPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReportsPO r = new ReportsPO();
		r.setReportId(StringUtils.trim(rs.getString("report_id")));
		r.setPatientId(StringUtils.trim(rs.getString("patient_id")));
		r.setPatientName(StringUtils.trim(rs.getString("patient_name")));
		r.setChkName(StringUtils.trim(rs.getString("chk_name")));
		r.setChkDate(rs.getTimestamp("chk_date"));
		r.setUserId(StringUtils.trim(rs.getString("user_id")));
		r.setDepartmentName(StringUtils.trim(rs.getString("department_name")));
		r.setDoctorDate(rs.getDate("doctor_date"));
		r.setReportStatus(rs.getInt("report_status"));
		r.setReportType(rs.getInt("report_type"));
		r.setCdepartmentName(StringUtils.trim(rs.getString("c_department_name")));
		return r;
	}

}
