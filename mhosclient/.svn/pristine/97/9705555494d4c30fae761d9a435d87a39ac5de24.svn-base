package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DocBillDetailPO implements RowMapper<DocBillDetailPO>{
	
	private String curDocSN;
	
	private String docBillSN;
	
	private String billItemName;
	
	private double itemAmount;
	
	private String patientName;

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

	public String getBillItemName() {
		return billItemName;
	}

	public void setBillItemName(String billItemName) {
		this.billItemName = billItemName;
	}
	

	public double getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(double itemAmount) {
		this.itemAmount = itemAmount;
	}
	

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@Override
	public DocBillDetailPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocBillDetailPO p = new DocBillDetailPO();
		p.setCurDocSN(rs.getString("cur_doc_sn"));
		p.setDocBillSN(rs.getString("doc_bill_sn"));
		p.setBillItemName(rs.getString("bill_item_name"));
		p.setItemAmount(itemAmount);
		p.setPatientName(rs.getString("patient_name"));
		return p;
	}

}
