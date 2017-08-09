package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class WechatCfgPO implements RowMapper<WechatCfgPO> {

	private String dataName;
	private String dataValue;

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	@Override
	public WechatCfgPO mapRow(ResultSet rs, int i) throws SQLException {
		WechatCfgPO cfg = new WechatCfgPO();
		cfg.setDataName(rs.getString("data_name"));
		cfg.setDataValue(rs.getString("data_value"));
		return cfg;
	}

}
