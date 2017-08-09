package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class InfoCatPO implements RowMapper<InfoCatPO>{
	
	public static final int INFO_TYPE_HEALTH = 1;
	
	public static final int INFO_TYPE_NEWS = 2;

	
	private String infoCatCode;
	
	private String infoCatName;
	
	private int infoType;
	
	public static boolean isValidInfoType(int infoType){
		return infoType == INFO_TYPE_HEALTH || infoType == INFO_TYPE_NEWS;
	}

	public String getInfoCatCode() {
		return infoCatCode;
	}

	public void setInfoCatCode(String infoCatCode) {
		this.infoCatCode = infoCatCode;
	}

	public String getInfoCatName() {
		return infoCatName;
	}

	public void setInfoCatName(String infoCatName) {
		this.infoCatName = infoCatName;
	}

	public int getInfoType() {
		return infoType;
	}

	public void setInfoType(int infoType) {
		this.infoType = infoType;
	}

	@Override
	public InfoCatPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		InfoCatPO infoCat = new InfoCatPO();
		infoCat.setInfoCatCode(rs.getString("info_cat_code"));
		infoCat.setInfoCatName(rs.getString("info_cat_name"));
		infoCat.setInfoType(rs.getInt("info_type"));
		return infoCat;
	}
	
	
	
	

}
