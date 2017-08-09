package com.catic.mobilehos.dao.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.NetMsgTemplateDAO;
import com.catic.mobilehos.po.NetMsgTemplatePO;

public class NetMsgTemplateDAOImpl extends JdbcDaoSupport implements NetMsgTemplateDAO {
	
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public NetMsgTemplatePO getNetMsgTemplate(String busCode) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from net_msg_template where bus_type_code=?";
			NetMsgTemplatePO nmt = jt.queryForObject(sql, new NetMsgTemplatePO(), busCode);
			return nmt;
		}catch(EmptyResultDataAccessException ex){
			return null;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}

	}
	
	@Override
	public void addOrUpdNetMsgTemplate(NetMsgTemplatePO t){
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "insert into net_msg_template(bus_type_code, template) values(?,?) "
					   + "ON DUPLICATE KEY UPDATE template=?";
			jt.update(sql, t.getBusTypeCode(), t.getTemplate(), t.getTemplate());
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

}
