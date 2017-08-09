package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class PhoneMsgTemplatePO implements RowMapper<PhoneMsgTemplatePO>{
	
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
	public PhoneMsgTemplatePO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		PhoneMsgTemplatePO pmt = new PhoneMsgTemplatePO();
		pmt.setBusTypeCode(StringUtils.trim(rs.getString("bus_type_code")));
		pmt.setTemplate(StringUtils.trim(rs.getString("template")));
		return pmt;
	}
	
	
	
	
	
	
	

}
