package com.catic.mobilehos.menu_role_authority.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单信息表
 * @author YUXY
 *
 */
@SuppressWarnings("serial")
public class Menu implements Serializable {

	private Integer menuId;
	private String name;
	private String url;
	private Integer parentId;
	private Integer status;
	private Date createDate;
	
	private List<Menu> submenus;
	
	public Menu() {
		super();
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
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<Menu> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(List<Menu> submenus) {
		this.submenus = submenus;
	}
	
}
