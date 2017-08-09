package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class BusTypeDicPO implements RowMapper<BusTypeDicPO> {
	
	public static final int MAIN_TYPE_USERMNG_MSG = 1;
	
	public static final int MAIN_TYPE_BUS_MSG = 2;
	
	public static final int MAIN_TYPE_INFO_MSG = 9;
	
	private String busTypeCode;
	
	private String busName;
	
	private int supportPhoneMsg;
	
	private int supportNetMsg;
	
	private int mainType;

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

	public int getSupportPhoneMsg() {
		return supportPhoneMsg;
	}

	public void setSupportPhoneMsg(int supportPhoneMsg) {
		this.supportPhoneMsg = supportPhoneMsg;
	}

	public int getSupportNetMsg() {
		return supportNetMsg;
	}

	public void setSupportNetMsg(int supportNetMsg) {
		this.supportNetMsg = supportNetMsg;
	}
	
	public int getMainType() {
		return mainType;
	}

	public void setMainType(int mainType) {
		this.mainType = mainType;
	}

	@Override
	public BusTypeDicPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BusTypeDicPO busTypeDic = new BusTypeDicPO();
		busTypeDic.setBusName(StringUtils.trim(rs.getString("bus_name")));
		busTypeDic.setBusTypeCode(StringUtils.trim(rs.getString("bus_type_code")));
		busTypeDic.setSupportNetMsg(rs.getInt("support_net_msg"));
		busTypeDic.setSupportPhoneMsg(rs.getInt("support_phone_msg"));
		busTypeDic.setMainType(rs.getInt("main_type"));
		return busTypeDic;
	}
	

}
