package com.catic.mobilehos.service.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;


public class WechatMainMenuVO implements RowMapper<WechatMainMenuVO>{
	private String id;//菜单ID
	private String parentId;//父类ID
	private String name;//菜单名称
	private String type;//菜单相应类型
	private String key;//菜单KEY值
	private String url;//目标值，参考回复配置
	private String holderId;//公众号ID
	private int sequence;//序号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHolderId() {
		return holderId;
	}
	public void setHolderId(String holderId) {
		this.holderId = holderId;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	@Override
	public WechatMainMenuVO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		// TODO Auto-generated method stub
		WechatMainMenuVO main = new WechatMainMenuVO();
		main.setId(StringUtils.trim(rs.getString("id")));
		main.setParentId(StringUtils.trim(rs.getString("parentId")));
		main.setName(rs.getString("name"));
		main.setType(rs.getString("type"));
		main.setKey(rs.getString("key"));
		main.setUrl(rs.getString("url"));
		main.setHolderId(StringUtils.trim(rs.getString("holderId")));
		main.setSequence(rs.getInt("sequence"));
		return main;
	}
	
	
	

}
