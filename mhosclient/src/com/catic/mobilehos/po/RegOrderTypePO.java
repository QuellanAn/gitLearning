// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HisAppRegOrderType.java

package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RegOrderTypePO implements RowMapper<RegOrderTypePO> {

	private String typeCode;
	private String typeName;

	public RegOrderTypePO() {
	}

	public RegOrderTypePO mapRow(ResultSet rs, int rowcount)
			throws SQLException {
		RegOrderTypePO appRegOrderType = new RegOrderTypePO();
		appRegOrderType.setTypeCode(rs.getString("type_code"));
		appRegOrderType.setTypeName(rs.getString("type_name"));
		return appRegOrderType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

}
