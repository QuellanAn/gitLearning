package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class NetMsgTemplatePO implements RowMapper<NetMsgTemplatePO>{
	
	private String busTypeCode;
	
	private String template;

	public String getBusTypeCode() {
		return busTypeCode;
	}

	public void setBusTypeCode(String busTypeCode) {
		this.busTypeCode = busTypeCode;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	@Override
	public NetMsgTemplatePO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		NetMsgTemplatePO nmt = new NetMsgTemplatePO();
		nmt.setBusTypeCode(StringUtils.trim(rs.getString("bus_type_code")));
		nmt.setTemplate(StringUtils.trim(rs.getString("template")));
		return nmt;
	}
	
	
	
	
	
	

}
