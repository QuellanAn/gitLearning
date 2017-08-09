package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class ServiceEvaluationPO implements RowMapper<ServiceEvaluationPO> {

	private String appRegOrderId;
	private String questionnaireId;
	private String title;
	private String patientName;
	private String departmentName;
	private String doctorName;
	private java.sql.Date doctorDate;
	private Timestamp createtime;
	
	
	@Override
	public ServiceEvaluationPO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		ServiceEvaluationPO seo = new ServiceEvaluationPO();
		
		seo.setQuestionnaireId(rs.getString("id"));
		seo.setAppRegOrderId(rs.getString("app_reg_order_id"));
		seo.setTitle(rs.getString("title"));
		seo.setPatientName(rs.getString("patient_name"));
		seo.setDepartmentName(rs.getString("department_name"));
		seo.setDoctorName(rs.getString("doctor_name"));
		seo.setDoctorDate(rs.getDate("doctor_date"));
		seo.setCreatetime(rs.getTimestamp("createtime"));
		return seo;
	}


	public String getAppRegOrderId() {
		return appRegOrderId;
	}


	public void setAppRegOrderId(String appRegOrderId) {
		this.appRegOrderId = appRegOrderId;
	}


	public String getQuestionnaireId() {
		return questionnaireId;
	}


	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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


	public String getDoctorName() {
		return doctorName;
	}


	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	public java.sql.Date getDoctorDate() {
		return doctorDate;
	}


	public void setDoctorDate(java.sql.Date doctorDate) {
		this.doctorDate = doctorDate;
	}


	public Timestamp getCreatetime() {
		return createtime;
	}


	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}




}
