package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;


/**
 * @author xieweipeng
 *
 */
public class MessagePO implements RowMapper<MessagePO>{
	
	
	public static final int SEND_WAY_PHONE = 2;
	
	public static final int SEND_WAY_NET = 1;
	
	public static final int STATUS_UNSENT = 0;
	
	public static final int STATUS_SUCCESS = 1;
	
	public static final int STATUS_FAILED = 2;
	
	private String msgId;
	
	private String busTypeCode;
	
	private String busName;
	
	private String phone;
	
	private String msgContent;
	
	private Timestamp expectTime;
	
	private int sendWay;
	
	private int status;

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
	
	


	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
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

	public int getSendWay() {
		return sendWay;
	}

	public void setSendWay(int sendWay) {
		this.sendWay = sendWay;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public MessagePO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MessagePO m = new MessagePO();
		m.setMsgId(StringUtils.trim(rs.getString("msg_id")));
		m.setBusTypeCode(StringUtils.trim(rs.getString("bus_type_code")));
		m.setPhone(StringUtils.trim(rs.getString("phone")));
		m.setMsgContent(StringUtils.trim(rs.getString("msg_content")));
		m.setExpectTime(rs.getTimestamp("expect_time"));
		m.setSendWay(rs.getInt("send_way"));
		m.setStatus(rs.getInt("success"));
		m.setBusName(rs.getString("bus_name"));
		return m;
	}
	
	
	
	
	

}
