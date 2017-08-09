package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class PayPO implements RowMapper<PayPO> {

	private String docBillSn;// 缴费单号
	private String patientId;
	private String patientName;
	private double amount;// 应缴费用
	private String curDocSn;// 就诊序号
	private String paidFees;// 已缴费用
	private String payMode;// 缴费方式
	private String payTime;// 缴费时间
	private String payOrderid;// 缴费单号

	@Override
	public PayPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		PayPO ppo = new PayPO();
		ppo.setDocBillSn(StringUtils.trim(rs.getString("doc_bill_sn")));
		ppo.setPatientId(StringUtils.trim(rs.getString("patient_id")));
		ppo.setPatientName(rs.getString("patient_name"));
		ppo.setAmount(rs.getDouble("amount"));
		ppo.setCurDocSn(StringUtils.trim(rs.getString("cur_doc_sn")));
		ppo.setPaidFees(rs.getString("paid_fees"));
		ppo.setPayMode(rs.getString("pay_mode"));
		ppo.setPayTime(rs.getString("pay_time"));
		ppo.setPayOrderid(rs.getString("pay_orderid"));
		return ppo;
	}

	public String getDocBillSn() {
		return docBillSn;
	}

	public void setDocBillSn(String docBillSn) {
		this.docBillSn = docBillSn;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurDocSn() {
		return curDocSn;
	}

	public void setCurDocSn(String curDocSn) {
		this.curDocSn = curDocSn;
	}

	public String getPaidFees() {
		return paidFees;
	}

	public void setPaidFees(String paidFees) {
		this.paidFees = paidFees;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getPayOrderid() {
		return payOrderid;
	}

	public void setPayOrderid(String payOrderid) {
		this.payOrderid = payOrderid;
	}


}
