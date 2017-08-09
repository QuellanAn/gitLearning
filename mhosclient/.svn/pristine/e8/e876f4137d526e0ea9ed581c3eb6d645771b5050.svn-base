package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

/**
 * 基础内容配置
 * 
 * @author linchunda
 * 
 */
public class BasicCntCfgPO implements RowMapper<BasicCntCfgPO> {
	/** 就医指南 */
	public static final int CFG_TYPE_DOC_GUIDE = 0x1;
	/** 挂号须知 */
	public static final int CAT_REG_NOTICE = 0x1;
	/** 就诊须知 */
	public static final int CAT_DOC_NOTICE = 0x2;
	/** 取药须知 */
	public static final int CAT_MED_NOTICE = 0x3;
	/** 住院须知 */
	public static final int CAT_HOS_NOTICE = 0x4;

	/** 类别 */
	private int cfg_type;
	/** 目录 */
	private int cat;
	/** 分类名 */
	private String cat_name;
	/** 内容 */
	private String content;
	private String icon;
	private String updatePeople;
	/** 更新时间 */
	private Timestamp update_time;

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

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUpdatePeople() {
		return updatePeople;
	}

	public void setUpdatePeople(String updatePeople) {
		this.updatePeople = updatePeople;
	}

	@Override
	public BasicCntCfgPO mapRow(ResultSet rs, int arg1) throws SQLException {
		BasicCntCfgPO info = new BasicCntCfgPO();
		info.setCfg_type(rs.getInt("cfg_type"));
		info.setCat(rs.getInt("cat"));
		info.setCat_name(StringUtils.trim(rs.getString("cat_name")));
		info.setContent(StringUtils.trim(rs.getString("content")));
		info.setIcon(StringUtils.trim(rs.getString("icon")));
		info.setUpdatePeople(StringUtils.trim(rs.getString("update_people")));
		info.setUpdate_time(rs.getTimestamp("update_time"));
		return info;
	}

	

}
