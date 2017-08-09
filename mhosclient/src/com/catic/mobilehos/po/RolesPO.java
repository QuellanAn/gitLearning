package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * 角色
 * 
 * @author linchunda
 * 
 */
public class RolesPO implements RowMapper<RolesPO> {
	/** 标识 */
	private int roleId;
	/** 角色名称 */
	private String roleName;
	/** 备注 */
	private String remark;
	/** 创建时间 */
	private Timestamp createTime;
	/** 修改时间 */
	private Timestamp updateTime;

	/** 权限资源 */
	List<String> authority;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public List<String> getAuthority() {
		return authority;
	}

	public void setAuthority(List<String> authority) {
		this.authority = authority;
	}

	@Override
	public RolesPO mapRow(ResultSet resultSet, int rows) throws SQLException {
		RolesPO rolesPO = new RolesPO();
		rolesPO.setRoleId(resultSet.getInt("roleId"));
		rolesPO.setRoleName(resultSet.getString("roleName"));
		rolesPO.setRemark(resultSet.getString("remark"));
		rolesPO.setCreateTime(resultSet.getTimestamp("createTime"));
		rolesPO.setUpdateTime(resultSet.getTimestamp("updateTime"));
		return rolesPO;
	}

}
