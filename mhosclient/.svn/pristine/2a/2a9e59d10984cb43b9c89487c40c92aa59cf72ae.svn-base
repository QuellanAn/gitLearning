package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class NetChannelPO implements RowMapper<NetChannelPO>{
	
	private String userId;
	
	private String imei;
	
	private String channel;
	
	private String deviceType;
	
	private Timestamp createDate;
	
	private Timestamp updateDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public NetChannelPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		NetChannelPO n = new NetChannelPO();
		n.setUserId(StringUtils.trim(rs.getString("user_id")));
		n.setImei(StringUtils.trim(rs.getString("imei")));
		n.setDeviceType(StringUtils.trim(rs.getString("device_type")));
		n.setChannel(StringUtils.trim(rs.getString("channel")));
		n.setCreateDate(rs.getTimestamp("create_date"));
		n.setUpdateDate(rs.getTimestamp("update_date"));
		return n;
	}
	
	
	
	
	
	

}
