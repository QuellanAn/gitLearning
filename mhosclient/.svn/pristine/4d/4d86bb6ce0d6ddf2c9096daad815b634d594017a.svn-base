package com.catic.mobilehos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 即时通信 openFire 用户－组
 * 
 * @author linchunda
 * 
 */
public class OpenFireGroupUserPO implements RowMapper<OpenFireGroupUserPO> {
	/** 组名 */
	private String groupName;
	/** 用户名 */
	private String username;
	/** 管理员身份 */
	private int administrator;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAdministrator() {
		return administrator;
	}

	public void setAdministrator(int administrator) {
		this.administrator = administrator;
	}

	@Override
	public OpenFireGroupUserPO mapRow(ResultSet resultSet, int arg1)
			throws SQLException {
		OpenFireGroupUserPO openFireGroupUserPO = new OpenFireGroupUserPO();
		openFireGroupUserPO.setGroupName(resultSet.getString("groupName"));
		openFireGroupUserPO.setUsername(resultSet.getString("username"));
		openFireGroupUserPO.setAdministrator(resultSet.getInt("administrator"));
		return openFireGroupUserPO;
	}

}
