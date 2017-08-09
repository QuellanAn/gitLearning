package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 即时通信 openfire 组属性名-值
 * 
 * @author linchunda
 * 
 */
public class OpenFireGroupPropPO implements RowMapper<OpenFireGroupPropPO> {
	/** 组名 */
	private String groupName;
	/** 组属性名 */
	private String name;
	/** 组属性值 */
	private String propValue;

	public OpenFireGroupPropPO() {
		super();
	}

	public OpenFireGroupPropPO(String groupName, String name, String propValue) {
		super();
		this.groupName = groupName;
		this.name = name;
		this.propValue = propValue;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	@Override
	public OpenFireGroupPropPO mapRow(ResultSet resultSet, int arg1)
			throws SQLException {
		OpenFireGroupPropPO openFireGroupPropPO = new OpenFireGroupPropPO();
		openFireGroupPropPO.setGroupName(resultSet.getString("groupName"));
		openFireGroupPropPO.setName(resultSet.getString("name"));
		openFireGroupPropPO.setPropValue(resultSet.getString("propValue"));
		return openFireGroupPropPO;
	}

}
