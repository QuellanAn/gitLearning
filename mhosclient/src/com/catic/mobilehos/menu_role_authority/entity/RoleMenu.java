package com.catic.mobilehos.menu_role_authority.entity;

import java.io.Serializable;

/**
 * 角色菜单信息表
 * @author YUXY
 *
 */
@SuppressWarnings("serial")
public class RoleMenu implements Serializable {

	private Integer rmid;
	private Integer roleId;
	private Integer menuId;
	
	private String name;
	private String url;
	private Integer parentId;
	
	public RoleMenu() {
		super();
	}

	public RoleMenu(Integer roleId, Integer menuId) {
		super();
		this.roleId = roleId;
		this.menuId = menuId;
	}

	public Integer getRmid() {
		return rmid;
	}

	public void setRmid(Integer rmid) {
		this.rmid = rmid;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
}
