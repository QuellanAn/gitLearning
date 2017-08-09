package com.catic.mobilehos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 即时通信用户组
 * 
 * @author linchunda
 * 
 */
public class OpenFireGroupPO implements RowMapper<OpenFireGroupPO> {
	/** 组名 */
	private String groupName;
	/** 组名描述信息 */
	private String description;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public OpenFireGroupPO mapRow(ResultSet resultSet, int rows)
			throws SQLException {
		OpenFireGroupPO openFireGroupPO = new OpenFireGroupPO();
		openFireGroupPO.setGroupName(resultSet.getString("groupName"));
		openFireGroupPO.setDescription(resultSet.getString("description"));
		return openFireGroupPO;
	}

}
