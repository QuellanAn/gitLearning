package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class UserSettingPO implements RowMapper<UserSettingPO>{
	
	/**
	 * 就诊排队提醒设置
	 */
	public static final int CTYPE_DOC_QUEUE_REMIND = 1;
	
	
	private String userId;
	
	private String config;
	
	private String ctype;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	@Override
	public UserSettingPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserSettingPO p = new UserSettingPO();
		p.setUserId(StringUtils.trim(rs.getString("user_id")));
		p.setConfig(rs.getString("config"));
		p.setCtype(rs.getString("c_type"));
		return p;
	}
	

}
