package com.catic.mobilehos.po;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

/**
 * 即时通信OpenFire用户
 * 
 * @author linchunda
 * 
 */
public class OpenFireUserPO implements RowMapper<OpenFireUserPO> {
	/** 用户名 */
	private String username;
	/** 纯文本密码 */
	private String plainPassword;
	/** 加密密码 */
	private String encryptedPassword;
	/** 名字 */
	private String name;
	/** 邮件地址 */
	private String email;
	/** 创建日期 */
	private String creationDate;
	/** 最后修改日期 */
	private String modificationDate;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}
	
	public String getCreationDateString(){
		try {
			Long date = Long.parseLong(creationDate);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			return format.format(new Date(date));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public OpenFireUserPO mapRow(ResultSet resultSet, int arg1)
			throws SQLException {
		OpenFireUserPO openFireUserPO = new OpenFireUserPO();
		openFireUserPO.setUsername(resultSet.getString("username"));
		openFireUserPO.setPlainPassword(resultSet.getString("plainPassword"));
		openFireUserPO.setEncryptedPassword(resultSet
				.getString("encryptedPassword"));
		openFireUserPO.setName(resultSet.getString("name"));
		openFireUserPO.setEmail(resultSet.getString("email"));

		openFireUserPO.setCreationDate(resultSet.getString("creationDate"));
		openFireUserPO.setModificationDate(resultSet
				.getString("modificationDate"));
		return openFireUserPO;
	}

}
