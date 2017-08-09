package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

/**
 * 客户端版本发布
 * 
 * @author linchunda
 * 
 */
public class CliVersionPO implements RowMapper<CliVersionPO> {
	private int ID;
	/** 客户端平台 */
	private String ver_cat;
	/** 客户端类别 */
	private String ver_type;
	/** 版本号 */
	private String versionNo;
	/** 发布日期 */
	private Timestamp createDate;
	/** 更新内容 */
	private String content;
	/** 强制升级版本 */
	private String update_version;
	/** 文件大小 */
	private int filesize;
	/** 文件包 */
	private String apkName;
	/** 文件包路径 */
	private String apkPath;

	public CliVersionPO() {
		super();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getVer_cat() {
		return ver_cat;
	}

	public void setVer_cat(String ver_cat) {
		this.ver_cat = ver_cat;
	}

	public String getVer_type() {
		return ver_type;
	}

	public void setVer_type(String ver_type) {
		this.ver_type = ver_type;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUpdate_version() {
		return update_version;
	}

	public void setUpdate_version(String update_version) {
		this.update_version = update_version;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public String getApkName() {
		return apkName;
	}

	public void setApkName(String apkName) {
		this.apkName = apkName;
	}

	public String getApkPath() {
		return apkPath;
	}

	public void setApkPath(String apkPath) {
		this.apkPath = apkPath;
	}

	@Override
	public CliVersionPO mapRow(ResultSet resultSet, int rowNum)
			throws SQLException {
		CliVersionPO cliVersionPO = new CliVersionPO();
		cliVersionPO.setID(resultSet.getInt("ID"));
		cliVersionPO.setVer_cat(resultSet.getString("ver_cat"));
		cliVersionPO.setVer_type(resultSet.getString("ver_type"));
		cliVersionPO.setUpdate_version(resultSet.getString("update_version"));
		cliVersionPO.setContent(resultSet.getString("content"));
		cliVersionPO.setCreateDate(resultSet.getTimestamp("createDate"));
		cliVersionPO.setVersionNo(resultSet.getString("versionNo"));
		cliVersionPO.setFilesize(resultSet.getInt("filesize"));
		cliVersionPO.setApkName(resultSet.getString("apkName"));
		cliVersionPO.setApkPath(resultSet.getString("apkPath"));
		return cliVersionPO;
	}

}
