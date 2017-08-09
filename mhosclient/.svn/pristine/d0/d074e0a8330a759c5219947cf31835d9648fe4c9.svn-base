package com.catic.mobilehos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.catic.mobilehos.dao.SysUsersDAO;
import com.catic.mobilehos.po.RolesPO;
import com.catic.mobilehos.po.SysUsersPO;

public class SysUsersDAOImpl extends JdbcDaoSupport implements SysUsersDAO {

	@Override
	public SysUsersPO getUser(int userId) {
		String sql = "select u.*,r.* from sysusers u left join sysusers_roles ur on u.userId=ur.userId left join roles r on ur.roleId=r.roleId where u.userId=?";
		final SysUsersPO sysUsersPO = new SysUsersPO();
		getJdbcTemplate().query(sql, new Object[] { userId },
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet resultSet)
							throws SQLException {
						sysUsersPO.setUserId(resultSet.getInt("userId"));
						sysUsersPO.setRealName(resultSet.getString("realName"));
						sysUsersPO.setUserName(resultSet.getString("userName"));
						sysUsersPO.setPassword(resultSet.getString("password"));
						sysUsersPO.setStatus(resultSet.getInt("status"));

						sysUsersPO.setDepartment(resultSet
								.getString("department"));
						sysUsersPO.setSex(resultSet.getString("sex"));
						sysUsersPO.setTel(resultSet.getString("tel"));
						sysUsersPO.setRemark(resultSet.getString("u.remark"));
						sysUsersPO.setCreateTime(resultSet
								.getTimestamp("u.createTime"));
						sysUsersPO.setUpdateTime(resultSet
								.getTimestamp("u.updateTime"));

						if (null == sysUsersPO.getRolesPOs()) {
							List<RolesPO> rolesPOs = new ArrayList<RolesPO>();
							sysUsersPO.setRolesPOs(rolesPOs);
						}

						if (resultSet.getInt("roleId") != 0) {
							RolesPO rolesPO = new RolesPO();
							rolesPO.setRoleId(resultSet.getInt("roleId"));
							rolesPO.setRoleName(resultSet.getString("roleName"));
							rolesPO.setRemark(resultSet.getString("r.remark"));
							rolesPO.setCreateTime(resultSet
									.getTimestamp("r.createTime"));
							rolesPO.setUpdateTime(resultSet
									.getTimestamp("r.updateTime"));
							sysUsersPO.getRolesPOs().add(rolesPO);
						}
					}
				});
		return sysUsersPO;
	}

	@Override
	public int createSysUsersPO(final SysUsersPO sysUsersPO) {
		final String sql = "insert into sysusers(userName,password,realName,"
				+ "sex,tel,department,status,remark,createTime,updateTime)"
				+ " values(?,?,?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement preparedStatement = getJdbcTemplate()
						.getDataSource()
						.getConnection()
						.prepareStatement(
								sql,
								new String[] { "userName", "password",
										"realName", "sex", "tel", "department",
										"status", "remark", "createTime",
										"updateTime" });
				preparedStatement.setString(1, sysUsersPO.getUserName());
				preparedStatement.setString(2, sysUsersPO.getPassword());
				preparedStatement.setString(3, sysUsersPO.getRealName());
				preparedStatement.setString(4, sysUsersPO.getSex());
				preparedStatement.setString(5, sysUsersPO.getTel());

				preparedStatement.setString(6, sysUsersPO.getDepartment());
				preparedStatement.setInt(7, sysUsersPO.getStatus());
				preparedStatement.setString(8, sysUsersPO.getRemark());
				preparedStatement.setTimestamp(9, sysUsersPO.getCreateTime());
				preparedStatement.setTimestamp(10, sysUsersPO.getUpdateTime());
				return preparedStatement;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public void updateSysUsersPO(final SysUsersPO sysUsersPO, final int userId) {
		String sql = "update sysusers set userName=?,password=?,realName=?,sex=?,tel=?,department=?,status=?"
				+ ",remark=?,updateTime=? where userId=?";
		getJdbcTemplate().update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement preparedStatement)
					throws SQLException {
				preparedStatement.setString(1, sysUsersPO.getUserName());
				preparedStatement.setString(2, sysUsersPO.getPassword());
				preparedStatement.setString(3, sysUsersPO.getRealName());
				preparedStatement.setString(4, sysUsersPO.getSex());
				preparedStatement.setString(5, sysUsersPO.getTel());
				preparedStatement.setString(6, sysUsersPO.getDepartment());

				preparedStatement.setInt(7, sysUsersPO.getStatus());
				preparedStatement.setString(8, sysUsersPO.getRemark());
				preparedStatement.setTimestamp(9, sysUsersPO.getUpdateTime());
				preparedStatement.setInt(10, userId);
			}
		});
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean existsUsername(String username) {
		String sql = "select count(userName) from sysusers where userName=?";
		int count = getJdbcTemplate().queryForInt(sql, username);
		return count > 0;
	}

	@Override
	public boolean deleteSysUser(int userId) {
		String sql = "delete from sysusers where userId=?";
		int row = getJdbcTemplate().update(sql, userId);
		return row > 0;
	}

	@Override
	public void addRoles(final int userId, final int[] role_ids) {
		final int length = role_ids.length;
		try {
			String sql = "insert into sysusers_roles(userId,roleId) value(?,?)";
			getJdbcTemplate().batchUpdate(sql,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(
								PreparedStatement preparedStatement, int i)
								throws SQLException {
							preparedStatement.setInt(1, userId);
							preparedStatement.setInt(2, role_ids[i]);
						}

						@Override
						public int getBatchSize() {
							return length;
						}
					});
		} catch (CannotGetJdbcConnectionException e) {
			e.printStackTrace();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean removeRoles(int userId) {
		String sql = "delete from sysusers_roles where userId=?";
		int row = getJdbcTemplate().update(sql, userId);
		return row > 0;
	}

	@Override
	public List<Integer> getRoles(int userId) {
		String sql = "select au_id from sysusers_roles where userId=?";
		return getJdbcTemplate().queryForList(sql, Integer.class, userId);
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getCountSysUsersPO(String realName, int status, int roleId) {
		boolean isParas = false;
		String sql = "select count(*) from sysusers u left join sysusers_roles ur on u.userId=ur.userId ";

		if (roleId > 0) {
			sql += " where roleId=" + roleId;
			isParas = true;
		} else {
			sql = "select count(*) from sysusers ";
		}

		if (!StringUtils.isEmpty(realName)) {
			if (isParas) {
				sql += " ands  realName like '%" + realName + "%' ";
			} else {
				sql += " where realName like '%" + realName + "%' ";
			}
			isParas = true;
		}

		if (status == 0 || status == 1) {
			if (isParas) {
				sql += " and status=" + status;
			} else {
				sql += " where status=" + status;
			}
		}

		return getJdbcTemplate().queryForInt(sql);
	}

	@Override
	public List<SysUsersPO> getSysUsersPOsbyParas(String realName, int status,
			int roleId, int offset, int length) {

		boolean isParas = false;
		String sql = "select distinct u.* from sysusers u";
		if (!StringUtils.isEmpty(realName)) {
			isParas = true;
			sql += " where realName like '%" + realName + "%' ";
		}

		if (status == 0 || status == 1) {
			if (isParas) {
				sql += " and status=" + status;
			} else {
				sql += " where status=" + status;
			}
		}

		sql += " ORDER BY updateTime DESC limit ? ,?";

		List<SysUsersPO> temp = getJdbcTemplate().query(sql, new SysUsersPO(),
				offset, length);
		List<SysUsersPO> list = new ArrayList<SysUsersPO>();

		for (SysUsersPO sysUsersPO : temp) {
			String query = "select r.* from sysusers_roles ur "
					+ "left join roles r on ur.roleId=r.roleId where userId="
					+ sysUsersPO.getUserId();
			if (roleId > 0) {
				query += " and ur.roleId=" + roleId;
			}

			List<RolesPO> rolesPOs = getJdbcTemplate().query(query,
					new RolesPO());

			if (roleId > 0) {
				if (null != rolesPOs && !rolesPOs.isEmpty()) {
					sysUsersPO.setRolesPOs(rolesPOs);
					list.add(sysUsersPO);
				}
			} else {
				sysUsersPO.setRolesPOs(rolesPOs);
				list.add(sysUsersPO);
			}
		}

		return list;
	}

	@Override
	public SysUsersPO findSysUsersPOByParas(String userName) {
		String sql = "select * from sysusers where userName=?";
		List<SysUsersPO> list = getJdbcTemplate().query(sql, new SysUsersPO(),
				userName);
		if (null == list || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<String> getAuthorityByUserId(int userId) {
		String sql = "select distinct a.authorityName from sysusers u"
				+ " left join sysusers_roles ur on u.userId=ur.userId"
				+ " left join roles r on ur.roleId=r.roleId"
				+ " left join role_authority ra on r.roleId=ra.role_id"
				+ " left join authority a on ra.au_id=a.au_id"
				+ " where u.userId=?";

		return getJdbcTemplate().queryForList(sql, String.class, userId);
	}

}
