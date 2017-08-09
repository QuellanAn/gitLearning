package com.catic.mobilehos.menu_role_authority.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 管理员信息表
 * @author YUXY
 *
 */
@SuppressWarnings("serial")
public class Manager implements Serializable {

	/** 标识 */
	private Integer userId;
	/** 帐号 */
	private String userName;
	/** 密码 */
	private String password;
	/** 姓名 */
	private String realName;
	/** 性别 */
	private String sex;
	/** 联系方式 */
	private String tel;
	/** 所属科室 */
	private String department;
	/** 帐号状态 */
	private Integer status;
	/** 备注 */
	private String remark;
	/** 创建时间 */
	private Timestamp createTime;
	/** 修改时间 */
	private Timestamp updateTime;
	
	private String companyFullName;
	private String customerFullName;
	
	private Integer[] ids;
	private Integer roleId;
	private String roleName;
	
	private Integer category;
	public Manager() {
		super();
	}
	
	public Manager(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCompanyFullName() {
		return companyFullName;
	}

	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}

	public String getCustomerFullName() {
		return customerFullName;
	}

	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

}
