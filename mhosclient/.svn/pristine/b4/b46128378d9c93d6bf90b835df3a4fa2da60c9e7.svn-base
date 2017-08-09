package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class PhoneMessagesPO implements RowMapper<PhoneMessagesPO>{
	
	private String msgId;
	
	private String busTypeCode;
	
	private String phone;
	
	private String msgContent;
	
	private Timestamp expectTime;
	
	private Timestamp validTime;
	
	private Timestamp lastSendTime;
	
	private String statusCode;
	
	private String statusDesc;
	
	private int sendType;
	
	private String msgGatewayName;
	
	private int success;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getBusTypeCode() {
		return busTypeCode;
	}

	public void setBusTypeCode(String busTypeCode) {
		this.busTypeCode = busTypeCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Timestamp getExpectTime() {
		return expectTime;
	}

	public void setExpectTime(Timestamp expectTime) {
		this.expectTime = expectTime;
	}
	

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public int getSendType() {
		return sendType;
	}

	public void setSendType(int sendType) {
		this.sendType = sendType;
	}

	public String getMsgGatewayName() {
		return msgGatewayName;
	}

	public void setMsgGatewayName(String msgGatewayName) {
		this.msgGatewayName = msgGatewayName;
	}

	@Override
	public PhoneMessagesPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PhoneMessagesPO pm = new PhoneMessagesPO();
		pm.setMsgId(StringUtils.trim(rs.getString("msg_id")));
		pm.setPhone(StringUtils.trim(rs.getString("phone")));
		pm.setMsgContent(StringUtils.trim(rs.getString("msg_content")));
		pm.setMsgGatewayName(StringUtils.trim(rs.getString("msg_gateway_name")));
		pm.setSuccess(rs.getInt("success"));
		pm.setValidTime(rs.getTimestamp("valid_time"));
		pm.setLastSendTime(rs.getTimestamp("last_send_time"));
		pm.setSendType(rs.getInt("send_type"));
		pm.setStatusCode(StringUtils.trim(rs.getString("status_code")));
		pm.setStatusDesc(StringUtils.trim(rs.getString("status_desc")));
		pm.setBusTypeCode(StringUtils.trim(rs.getString("bus_type_code")));
		pm.setExpectTime(rs.getTimestamp("expect_time"));
		return pm;
	}

	public Timestamp getValidTime() {
		return validTime;
	}

	public void setValidTime(Timestamp validTime) {
		this.validTime = validTime;
	}

	public Timestamp getLastSendTime() {
		return lastSendTime;
	}

	public void setLastSendTime(Timestamp lastSendTime) {
		this.lastSendTime = lastSendTime;
	}
	
	
	
	

}
