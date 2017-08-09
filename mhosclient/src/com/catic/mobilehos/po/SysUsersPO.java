package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author linchunda
 * 
 *         系统用户管理
 * 
 */
public class SysUsersPO implements RowMapper<SysUsersPO> {
	/** 标识 */
	private int userId;
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
	private int status;
	/** 备注 */
	private String remark;
	/** 创建时间 */
	private Timestamp createTime;
	/** 修改时间 */
	private Timestamp updateTime;

	/** 角色 */
	private List<RolesPO> rolesPOs;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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

	public List<RolesPO> getRolesPOs() {
		return rolesPOs;
	}

	public void setRolesPOs(List<RolesPO> rolesPOs) {
		this.rolesPOs = rolesPOs;
	}

	@Override
	public SysUsersPO mapRow(ResultSet resultSet, int rows) throws SQLException {
		SysUsersPO sysUsersPO = new SysUsersPO();
		sysUsersPO.setUserId(resultSet.getInt("userId"));
		sysUsersPO.setUserName(resultSet.getString("userName"));
		sysUsersPO.setRealName(resultSet.getString("realName"));
		sysUsersPO.setPassword(resultSet.getString("password"));
		sysUsersPO.setSex(resultSet.getString("sex"));
		sysUsersPO.setTel(resultSet.getString("tel"));
		sysUsersPO.setDepartment(resultSet.getString("department"));
		sysUsersPO.setStatus(resultSet.getInt("status"));
		sysUsersPO.setRemark(resultSet.getString("remark"));
		sysUsersPO.setCreateTime(resultSet.getTimestamp("createTime"));
		sysUsersPO.setUpdateTime(resultSet.getTimestamp("updateTime"));
		return sysUsersPO;
	}

}
