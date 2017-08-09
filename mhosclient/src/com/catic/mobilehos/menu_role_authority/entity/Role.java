package com.catic.mobilehos.menu_role_authority.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息表
 * @author YUXY
 *
 */
@SuppressWarnings("serial")
public class Role implements Serializable {

	private Integer roleId;
	private Integer category;//类别（服务商、银行、超级管理员）
	private String name;
	private String depict;
	private Date createDate;
	
	private String ids;
	
	public Role() {
		super();
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepict() {
		return depict;
	}
	public void setDepict(String depict) {
		this.depict = depict;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}
	
}
