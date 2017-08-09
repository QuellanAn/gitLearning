package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

/**
 * 预约挂号须知
 * 
 * @author linchunda
 * 
 */
public class NoticePO implements RowMapper<NoticePO> {

	private int notice_id;
	/** 挂号须知信息 */
	private String notice_content;
	/** 创建时间 */
	private Timestamp create_date;
	/** 更新时间 */
	private Timestamp update_date;

	public NoticePO() {
		super();
	}

	public int getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	public Timestamp getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Timestamp update_date) {
		this.update_date = update_date;
	}

	@Override
	public NoticePO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		NoticePO noticePO = new NoticePO();
		noticePO.setNotice_id(resultSet.getInt("notice_id"));
		noticePO.setNotice_content(resultSet.getString("notice_content"));
		noticePO.setCreate_date(resultSet.getTimestamp("create_date"));
		noticePO.setUpdate_date(resultSet.getTimestamp("update_date"));
		return noticePO;
	}

}
