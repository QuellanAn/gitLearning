package com.catic.mobilehos.dao.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.RegSucNoticeDAO;

public class RegSucNoticeDAOImpl extends JdbcDaoSupport implements RegSucNoticeDAO {
	
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public String getNotice() {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select notice_content from reg_suc_notice";
			String notice = jt.queryForObject(sql, String.class);
			return notice;
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
	}
	
	

}
