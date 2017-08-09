package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class DocBillsPO implements RowMapper<DocBillsPO> {

	private String curDocSN;

	private String docBillSN;

	private String patientId;

	private String patientName;

	private String department;

	private java.sql.Date docDate;

	private String docName;

	private java.sql.Time docTime;

	private String billName;

	private double amount;

	private Timestamp createDate;


	@Override
	public DocBillsPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocBillsPO p = new DocBillsPO();
		p.setCurDocSN(rs.getString("cur_doc_sn"));
		p.setDocBillSN(rs.getString("doc_bill_sn"));
		p.setAmount(rs.getDouble("amount"));
		p.setBillName(rs.getString("bill_name"));
		p.setCreateDate(rs.getTimestamp("create_date"));
		p.setDepartment(rs.getString("department"));
		p.setDocDate(rs.getDate("doc_date"));
		p.setDocName(rs.getString("doc_name"));
		p.setDocTime(rs.getTime("doc_time"));
		p.setPatientId(rs.getString("patient_id"));
		p.setPatientName(rs.getString("patient_name"));
		return p;
	}


	public String getCurDocSN() {
		return curDocSN;
	}


	public void setCurDocSN(String curDocSN) {
		this.curDocSN = curDocSN;
	}


	public String getDocBillSN() {
		return docBillSN;
	}


	public void setDocBillSN(String docBillSN) {
		this.docBillSN = docBillSN;
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


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public java.sql.Date getDocDate() {
		return docDate;
	}


	public void setDocDate(java.sql.Date docDate) {
		this.docDate = docDate;
	}


	public String getDocName() {
		return docName;
	}


	public void setDocName(String docName) {
		this.docName = docName;
	}


	public java.sql.Time getDocTime() {
		return docTime;
	}


	public void setDocTime(java.sql.Time docTime) {
		this.docTime = docTime;
	}


	public String getBillName() {
		return billName;
	}


	public void setBillName(String billName) {
		this.billName = billName;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Timestamp getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	
}
