package com.catic.mobilehos.dao.jdbc;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.UserDAO;
import com.catic.mobilehos.po.UserPO;

public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {

	private final Log log = LogFactory.getLog(this.getClass());

	@Override
	public UserPO getUser(String userId) {
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			List<UserPO> users = jt.query("select * from users where user_id=?", new UserPO());
			if (users != null && users.size() > 0) {
				return users.get(0);
			} else {
				return null;
			}
		} catch (DataAccessException ex) {
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public UserPO findUserByUsername(final String username) {
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from users where user_name=?";
			UserPO user = jt.queryForObject(sql, new UserPO(), username);
			return user;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		} catch (DataAccessException ex) {
			log.error(null, ex);
			throw ex;
		}
	}

	public List<UserPO> getAllUsers() {
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from users";
			List<UserPO> lst = jt.query(sql, new UserPO());
			return lst;
		} catch (DataAccessException ex) {
			log.error(null, ex);
			throw ex;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getCountUser(String user_name, String status, Timestamp start_create_date, Timestamp end_create_date) {
		String sql = "select count(*) from users";

		boolean hasParas = false;// 如果有查询条件为true;

		if (!StringUtils.isEmpty(user_name)) {
			sql += " where user_name like '%" + user_name + "%'";
			hasParas = true;
		}

		if (!StringUtils.isEmpty(status)) {
			if (hasParas) {
				if (!status.equals("2")) {
					sql += " and status='" + status + "'";
				} else {
					sql += " AND black_time >= NOW() ";
				}
			} else {
				if (!status.equals("2")) {
					sql += " where status='" + status + "'";
					hasParas = true;
				} else {
					sql += " where black_time >= NOW() ";
				}
			}
		}

		if (null != start_create_date) {
			if (hasParas) {
				sql += " and create_date>='" + start_create_date + "'";
			} else {
				sql += " where create_date>='" + start_create_date + "'";
				hasParas = true;
			}
		}

		if (null != end_create_date) {
			Timestamp end = new Timestamp(end_create_date.getTime());
			end.setDate(end.getDate() + 1);
			if (hasParas) {
				sql += " and create_date<'" + end + "'";
			} else {
				sql += " where create_date<'" + end + "'";
			}
		}
		return getJdbcTemplate().queryForInt(sql);

	}

	@SuppressWarnings("deprecation")
	@Override
	public List<UserPO> getUsersbyParas(String user_name, String status, Timestamp start_create_date, Timestamp end_create_date, int offset, int length) {
		String sql = "select * from users";

		boolean hasParas = false;// 如果有查询条件为true;

		if (!StringUtils.isEmpty(user_name)) {
			sql += " where user_name like '%" + user_name + "%'";
			hasParas = true;
		}

		if (!StringUtils.isEmpty(status)) {
			if (hasParas) {
				if (!status.equals("2")) {
					sql += " and status='" + status + "'";
				} else {
					sql += " AND black_time >= NOW() ";
				}
			} else {
				if (!status.equals("2")) {
					sql += " where status='" + status + "'";
					hasParas = true;
				} else {
					sql += " where black_time >= NOW() ";
				}
			}
		}

		if (null != start_create_date) {
			if (hasParas) {
				sql += " and create_date>='" + start_create_date + "'";
			} else {
				sql += " where create_date>='" + start_create_date + "'";
				hasParas = true;
			}
		}

		if (null != end_create_date) {
			Timestamp end = new Timestamp(end_create_date.getTime());
			end.setDate(end.getDate() + 1);
			if (hasParas) {
				sql += " and create_date<='" + end + "'";
			} else {
				sql += " where create_date<='" + end + "'";
			}
		}

		sql += " order by create_date desc limit ?,?";// 添加按注册时间进行排序 linchunda
		//System.out.println("sql "+sql+" "+offset+"  "+length);
		return getJdbcTemplate().query(sql, new UserPO(), offset, length);
	}

	@Override
	public int updateStatus(String userId, int status, Date blcakTime) {
		List<Object> params = new LinkedList<Object>();
		String sql = null;
		if (status == 0) {
			sql = "UPDATE users SET `status` = ?, black_time = ? WHERE user_id = ?";
			params.add(status);
			params.add(blcakTime);
			params.add(userId);
		} else {
			sql = "UPDATE users SET `status` = ? WHERE user_id = ?";
			params.add(status);
			params.add(userId);
		}
		return getJdbcTemplate().update(sql, params.toArray());
	}

	@Override
	public List<UserPO> getUserDayCountByPeriod(UserPO po) {
		StringBuffer sql = null;
		if("2".equals(po.getUserType())){// 移动端用户
			sql = new StringBuffer("SELECT DATE_FORMAT(create_date, '%Y-%m-%d') as countDate,count(1) as count FROM users WHERE 1=1 ");
		}else{// 就诊人
			sql = new StringBuffer("SELECT DATE_FORMAT(create_date, '%Y-%m-%d') as countDate,count(1) as count FROM patients WHERE 1=1 ");
		}
		List<Object> params = new ArrayList<Object>();
		if(StringUtils.isNotBlank(po.getBeginDate())){
			sql.append(" AND create_date>=?");
			params.add(po.getBeginDate()+" 00:00:00");
		}
		if(StringUtils.isNotBlank(po.getEndDate())){
			sql.append(" AND create_date<=?");
			params.add(po.getEndDate()+" 23:59:59");
		}
		if(StringUtils.isNotBlank(po.getUserSource())){
			if("2".equals(po.getUserType())){
				sql.append(" AND user_source=?");
			}else{
				sql.append(" AND patient_source=?");
			}
			params.add(po.getUserSource());
		}
		if("0".equals(po.getUserType()) || "1".equals(po.getUserType())){
			sql.append(" AND type=?");
			params.add(po.getUserType());
		}
		sql.append(" GROUP BY DATE_FORMAT(create_date, '%Y-%m-%d')");
		return getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(UserPO.class));
	}
	
	@Override
	public int getCountUser(UserPO po) {
		List<Object> params = new ArrayList<Object>();
		// 移动端用户
		StringBuffer sql = new StringBuffer("select count(*) from users where 1=1");
		if(!"2".equals(po.getUserType())){// 就诊人
			sql = new StringBuffer("select count(*) from patients where 1=1");
		}

		if (StringUtils.isNotBlank(po.getBeginDate())) {
			sql.append(" AND create_date<?");
			params.add(po.getBeginDate() + " 00:00:00");
		}
		if(StringUtils.isNotBlank(po.getUserSource())){// 用户来源
			if("2".equals(po.getUserType())){
				sql.append(" AND user_source=?");
			}else{
				sql.append(" AND patient_source=?");
			}
			params.add(po.getUserSource());
		}
		if("0".equals(po.getUserType()) || "1".equals(po.getUserType())){
			sql.append(" AND type=?");
			params.add(po.getUserType());
		}
		return getJdbcTemplate().queryForInt(sql.toString(), params.toArray());

	}
}
