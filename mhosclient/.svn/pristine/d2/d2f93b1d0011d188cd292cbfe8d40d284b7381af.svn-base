package com.catic.mobilehos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.catic.mobilehos.dao.RolesDAO;
import com.catic.mobilehos.po.RolesPO;

/**
 * 角色管理
 * 
 * @author linchunda
 * 
 */
public class RolesDAOImpl extends JdbcDaoSupport implements RolesDAO {

	@Override
	public RolesPO getRolesPOByroleId(int roleId) {
		String sql = "select * from roles where roleId=?";
		List<RolesPO> list = getJdbcTemplate()
				.query(sql, new RolesPO(), roleId);
		if (null != list && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int createRolesPO(final RolesPO rolesPO) {
		try {
			final String sql = "insert into roles(roleName,remark,createTime,updateTime) values(?,?,?,?)";
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
									new String[] { "roleName","remark","createTime","updateTime"});
					preparedStatement.setString(1,
							rolesPO.getRoleName());
					preparedStatement.setString(2, rolesPO.getRemark());
					preparedStatement.setTimestamp(3,
							rolesPO.getCreateTime());
					preparedStatement.setTimestamp(4,
							rolesPO.getUpdateTime());
					return preparedStatement;
				}
			}, keyHolder);
			return keyHolder.getKey().intValue();
		} catch (DataAccessException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void updateRolesPO(final RolesPO rolesPO, final int roleId) {
		try {
			String sql = "update roles set roleName=?,remark=?,updateTime=? where roleId=?";
			getJdbcTemplate().update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement preparedStatement)
						throws SQLException {
					preparedStatement.setString(1, rolesPO.getRoleName());
					preparedStatement.setString(2, rolesPO.getRemark());
					preparedStatement.setTimestamp(3, rolesPO.getUpdateTime());
					preparedStatement.setInt(4, roleId);
				}
			});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteRolesPO(int roleId) {
		String sql = "delete from roles where roleId=?";
		int row = getJdbcTemplate().update(sql, roleId);
		return row > 0;
	}

	@Override
	public void addAuthority(final int roleId, final int[] au_ids) {
		final int length = au_ids.length;
		try {
			String sql = "insert into role_authority(role_id,au_id) value(?,?)";
			getJdbcTemplate().batchUpdate(sql,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(
								PreparedStatement preparedStatement, int i)
								throws SQLException {
							preparedStatement.setInt(1, roleId);
							preparedStatement.setInt(2, au_ids[i]);
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
	public boolean removeAuthority(int roleId) {
		String sql = "delete from role_authority where role_id=?";
		int row = getJdbcTemplate().update(sql, roleId);
		return row > 0;
	}
	
	@Override
	public List<Integer> getAuthority(int roleId) {
		String sql = "select au_id from role_authority where role_id=?";
		return getJdbcTemplate().queryForList(sql, Integer.class, roleId);
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getCountRolesPO() {
		String sql = "select count(*) from roles";

		return getJdbcTemplate().queryForInt(sql);
	}

	@Override
	public List<RolesPO> findRolesPOByParas(int offset, int length) {
		String sql = "select * from roles order by updateTime desc limit ?,?";
		List<RolesPO> rolesPOs = getJdbcTemplate().query(sql, new RolesPO(),
				offset, length);
		if (null == rolesPOs) {
			return new ArrayList<RolesPO>();
		}
		return rolesPOs;
	}
	
	@Override
	public List<RolesPO> findAllRolesPO() {
		String sql = "select * from roles order by updateTime desc";
		List<RolesPO> rolesPOs = getJdbcTemplate().query(sql, new RolesPO());
		if (null == rolesPOs) {
			return new ArrayList<RolesPO>();
		}
		return rolesPOs;
	}

}
