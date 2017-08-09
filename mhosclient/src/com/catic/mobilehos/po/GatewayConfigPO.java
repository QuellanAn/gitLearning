package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class GatewayConfigPO implements RowMapper<GatewayConfigPO>{
	
	private String gatewayName;
	
	private String config;

	public String getGatewayName() {
		return gatewayName;
	}

	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	@Override
	public GatewayConfigPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		GatewayConfigPO cfg = new GatewayConfigPO();
		cfg.setGatewayName(StringUtils.trim(rs.getString("gateway_name")));
		cfg.setConfig(StringUtils.trim(rs.getString("config")));
		return cfg;
	}
	
	
	
	

}
