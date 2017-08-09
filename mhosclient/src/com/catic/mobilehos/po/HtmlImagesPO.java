package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class HtmlImagesPO implements RowMapper<HtmlImagesPO>{
	
	private String infoId;
	
	private byte[] image;
	
	private String url;
	
	private String filename;
	
	private Timestamp createDate;

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public HtmlImagesPO mapRow(ResultSet rs, int arg1) throws SQLException {
		HtmlImagesPO h = new HtmlImagesPO();
		h.setCreateDate(rs.getTimestamp("create_date"));
		h.setFilename(StringUtils.trim(rs.getString("filename")));
		h.setImage(rs.getBytes("image"));
		h.setInfoId(StringUtils.trim(rs.getString("info_id")));
		h.setUrl(StringUtils.trim(rs.getString("url")));
		return h;
	}
	

}
