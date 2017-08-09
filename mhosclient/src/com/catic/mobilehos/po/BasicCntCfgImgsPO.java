package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

/**
 * 基础内容网页图片
 * 
 * @author linchunda
 * 
 */
public class BasicCntCfgImgsPO implements RowMapper<BasicCntCfgImgsPO> {
	/** 类别 */
	private int cfg_type;
	/** 目录 */
	private int cat;
	/** 图片内容 */
	private byte[] image_content;
	/** 图片URL */
	private String url;
	/** 图片文件名 */
	private String filename;

	public BasicCntCfgImgsPO() {
		super();
	}

	public int getCfg_type() {
		return cfg_type;
	}

	public void setCfg_type(int cfg_type) {
		this.cfg_type = cfg_type;
	}

	public int getCat() {
		return cat;
	}

	public void setCat(int cat) {
		this.cat = cat;
	}

	public byte[] getImage_content() {
		return image_content;
	}

	public void setImage_content(byte[] image_content) {
		this.image_content = image_content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public BasicCntCfgImgsPO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		BasicCntCfgImgsPO basicCntCfgIimgs = new BasicCntCfgImgsPO();
		basicCntCfgIimgs.setCfg_type(rs.getInt("cfg_type"));
		basicCntCfgIimgs.setCat(rs.getInt("cat"));
		basicCntCfgIimgs.setImage_content(rs.getBytes("image_content"));
		basicCntCfgIimgs.setUrl(StringUtils.trim(rs.getString("url")));
		basicCntCfgIimgs
				.setFilename(StringUtils.trim(rs.getString("filename")));
		return basicCntCfgIimgs;
	}

}
