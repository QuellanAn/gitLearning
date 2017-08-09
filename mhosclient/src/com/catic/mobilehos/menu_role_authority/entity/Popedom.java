package com.catic.mobilehos.menu_role_authority.entity;

import java.io.Serializable;

/**
 * 权限信息表
 * @author YUXY
 *
 */
@SuppressWarnings("serial")
public class Popedom implements Serializable {

	private Integer pmid;
	private Integer mrid;
	private Integer roleId;
	
	public Popedom() {
		super();
	}
	
	public Popedom(Integer mrid, Integer roleId) {
		super();
		this.mrid = mrid;
		this.roleId = roleId;
	}



	public Integer getPmid() {
		return pmid;
	}

	public void setPmid(Integer pmid) {
		this.pmid = pmid;
	}

	public Integer getMrid() {
		return mrid;
	}

	public void setMrid(Integer mrid) {
		this.mrid = mrid;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
}
