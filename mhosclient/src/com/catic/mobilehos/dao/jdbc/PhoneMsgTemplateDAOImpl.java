package com.catic.mobilehos.dao.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.PhoneMsgTemplateDAO;
import com.catic.mobilehos.po.PhoneMsgTemplatePO;

public class PhoneMsgTemplateDAOImpl extends JdbcDaoSupport implements PhoneMsgTemplateDAO {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	
	@Override
	public PhoneMsgTemplatePO getPhoneMsgTemplate(String busCode) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from phone_msg_template where bus_type_code=?";
			PhoneMsgTemplatePO pmt = jt.queryForObject(sql, new PhoneMsgTemplatePO(), busCode);
			return pmt;
		}catch(EmptyResultDataAccessException ex){
			return null;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	
	
	@Override
	public void addOrUpdPhoneMsgTemplate(PhoneMsgTemplatePO t){
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "insert into phone_msg_template(bus_type_code, template) values(?,?) "
					   + "ON DUPLICATE KEY UPDATE template=?";
			jt.update(sql, t.getBusTypeCode(), t.getTemplate(), t.getTemplate());
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}


}
