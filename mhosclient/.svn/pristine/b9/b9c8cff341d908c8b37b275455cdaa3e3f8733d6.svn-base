package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class OrdersPayPO implements RowMapper<OrdersPayPO>{
	
//	public static final int STATUS_SUBMITED = 1;
//	public static final int STATUS_CANCELED = 0;

	private String appRegOrderId;//预约挂号ID
	private String appROrderNumber;//预约单号
	private String patientId;
	private String patientName;
    private double payableFees;//应缴费用
	private double paidFees;//已缴费用
	private String payMode;//缴费方式
	private int paid;//是否已经缴费状态

	
	

	@Override
	public OrdersPayPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		OrdersPayPO opo = new OrdersPayPO();
		opo.setAppRegOrderId(StringUtils.trim(rs.getString("app_reg_order_id")));
		opo.setPatientId(StringUtils.trim(rs.getString("patient_id")));
		opo.setPatientName(rs.getString("patient_name"));
		opo.setPayableFees(rs.getDouble("payable_fees"));
		opo.setPaidFees(rs.getDouble("paid_fees"));
		opo.setPaid(rs.getInt("paid"));
		opo.setAppROrderNumber(StringUtils.trim(rs.getString("app_r_order_number")));
		return opo;
	}

	public String getAppRegOrderId() {
		return appRegOrderId;
	}

	public void setAppRegOrderId(String appRegOrderId) {
		this.appRegOrderId = appRegOrderId;
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

	public double getPayableFees() {
		return payableFees;
	}

	public void setPayableFees(double payableFees) {
		this.payableFees = payableFees;
	}

	public double getPaidFees() {
		return paidFees;
	}

	public void setPaidFees(double paidFees) {
		this.paidFees = paidFees;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}


	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public String getAppROrderNumber() {
		return appROrderNumber;
	}

	public void setAppROrderNumber(String appROrderNumber) {
		this.appROrderNumber = appROrderNumber;
	}

	
	

	
	
	
	
}

