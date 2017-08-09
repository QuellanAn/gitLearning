package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

/**
 * 此对象用于就诊卡缴费,
 * @author gds
 *
 */
public class CardPayPO implements RowMapper<CardPayPO>{
	/**
	 * 此字段医嘱单号
	 */
	private String cpId;
	
	private String patientId;
	
	private String userId;
	
	private String receiptNo;
	
	private String remainMoney;
	
	private String pvisitNo;
	
	private String payFee;
	
	private String payWay;
	
	private String payStatus;
	
	private String payType;
	
	private String canal;
	
	private String cardNumber;
	
	private Date createDate;
	
	private String patientName;
	
	private String totalFee;

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getRemainMoney() {
		return remainMoney;
	}

	public void setRemainMoney(String remainMoney) {
		this.remainMoney = remainMoney;
	}

	public String getPvisitNo() {
		return pvisitNo;
	}

	public void setPvisitNo(String pvisitNo) {
		this.pvisitNo = pvisitNo;
	}

	public String getPayFee() {
		return payFee;
	}

	public void setPayFee(String payFee) {
		this.payFee = payFee;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	@Override
	public CardPayPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		CardPayPO cp = new CardPayPO();
		cp.setUserId(StringUtils.trim(rs.getString("user_id")));
		cp.setCpId(rs.getString("cp_id"));
		cp.setPatientId(StringUtils.trim(rs.getString("patient_id")));
		cp.setRemainMoney(rs.getString("remainMoney"));
		cp.setReceiptNo(rs.getString("receiptNo"));
		cp.setPvisitNo(rs.getString("pvisitNo"));
		cp.setPayFee(rs.getString("payFee"));
		cp.setTotalFee(rs.getString("totalFee"));
		cp.setPayWay(rs.getString("payWay"));
		cp.setPayStatus(rs.getString("payStatus"));
		cp.setPayType(rs.getString("payType"));
		cp.setCanal(rs.getString("canal"));
		cp.setCardNumber(rs.getString("card_number"));
		cp.setCreateDate(rs.getDate("create_date"));
		cp.setPatientName(rs.getString("patientName"));
		return cp;
	}
}
