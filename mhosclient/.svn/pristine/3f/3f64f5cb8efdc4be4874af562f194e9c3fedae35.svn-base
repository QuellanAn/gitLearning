package com.catic.mobilehos.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.NoticeDAO;
import com.catic.mobilehos.po.NoticePO;

public class NoticeDAOImpl extends JdbcDaoSupport implements NoticeDAO {

	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public String getNotice() {
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select notice_content from reg_notice";
			String notice = jt.queryForObject(sql, String.class);
			return notice;
		} catch (DataAccessException ex) {
			log.error(null, ex);
			throw ex;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.catic.mobilehos.dao.NoticeDAO#getNoticePO()
	 */
	@Override
	public NoticePO getNoticePO(int noticeId) {
		String sql = "select * from reg_notice where notice_id=?";
		List<NoticePO> list = getJdbcTemplate().query(sql, new NoticePO(),noticeId);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.catic.mobilehos.dao.NoticeDAO#saveNotice(com.catic.mobilehos.po.NoticePO
	 * )
	 */
	@Override
	public boolean saveNotice(final NoticePO noticePO) {
		String sql = "insert into reg_notice(notice_id,notice_content,create_date,update_date) values(1,?,?,?)";
		int rows = getJdbcTemplate().update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement)
					throws SQLException {
				preparedStatement.setString(1, noticePO.getNotice_content());
				preparedStatement.setTimestamp(2, noticePO.getCreate_date());
				preparedStatement.setTimestamp(3, noticePO.getUpdate_date());
			}
		});
		return rows > 0;
	}

	/*
	 * (non-Javadoc)
	 * @see com.catic.mobilehos.dao.NoticeDAO#updateNotice(com.catic.mobilehos.po.NoticePO)
	 */
	@Override
	public boolean updateNotice(final NoticePO noticePO) {
		String sql = "update reg_notice set notice_content=?,update_date=? where notice_id=?";
		int rows = getJdbcTemplate().update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement)
					throws SQLException {
				preparedStatement.setString(1, noticePO.getNotice_content());
				preparedStatement.setTimestamp(2, noticePO.getUpdate_date());
				preparedStatement.setInt(3, noticePO.getNotice_id());
			}
		});
		return rows > 0;
	}

}
