package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class NetMessagesPO implements RowMapper<NetMessagesPO>{
	
	private String msgId;
	
	private String busTypeCode;
	
	private String userId;
	
	private String msgContent;
	
	private Timestamp expectTime;
	
	private Timestamp validTime;
	
	private Timestamp lastSendTime;
	
	private String statusCode;
	
	private String statusDesc;
	
	private int sendType;
	
	private String msgGatewayName;
	
	private int success;
	
	private int broadcast;
	
	

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
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public int getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(int broadcast) {
		this.broadcast = broadcast;
	}
	
	@Override
	public NetMessagesPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		NetMessagesPO nm = new NetMessagesPO();
		nm.setMsgId(StringUtils.trim(rs.getString("msg_id")));
		nm.setUserId(StringUtils.trim(rs.getString("user_id")));
		nm.setMsgContent(StringUtils.trim(rs.getString("msg_content")));
		nm.setMsgGatewayName(StringUtils.trim(rs.getString("msg_gateway_name")));
		nm.setSuccess(rs.getInt("success"));
		nm.setBroadcast(rs.getInt("broadcast"));
		nm.setValidTime(rs.getTimestamp("valid_time"));
		nm.setLastSendTime(rs.getTimestamp("last_send_time"));
		nm.setSendType(rs.getInt("send_type"));
		nm.setStatusCode(StringUtils.trim(rs.getString("status_code")));
		nm.setStatusDesc(StringUtils.trim(rs.getString("status_desc")));
		nm.setBusTypeCode(StringUtils.trim(rs.getString("bus_type_code")));
		nm.setExpectTime(rs.getTimestamp("expect_time"));
		return nm;
	}

	
	
	

}
