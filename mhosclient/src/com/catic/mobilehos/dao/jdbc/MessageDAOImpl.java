package com.catic.mobilehos.dao.jdbc;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.MessageDAO;
import com.catic.mobilehos.po.MessagePO;
import com.catic.mobilehos.utils.CommonUtils;

public class MessageDAOImpl extends JdbcDaoSupport implements MessageDAO {
	
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public List<MessagePO> findMsgsByParas(String typeCode, String phone,
			Integer status, Timestamp startDate, Timestamp endDate
			, int offset, int length) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			sql.setBaseSQL(" select un.*, b.bus_name from " +
						   " ( " + 
					       " SELECT msg_id, bus_type_code, '' phone, msg_content, expect_time,  1 send_way, success, last_send_time FROM net_messages" + 
						   " union" + 
					       " select msg_id, bus_type_code, phone, msg_content, expect_time, 2 send_way, success, last_send_time from phone_messages" +
						   " ) un, bus_type_dic b where un.bus_type_code = b.bus_type_code", false);
			sql.setLimit(offset, length);
			sql.addPara("un.bus_type_code", "=", typeCode);
			if(phone!=null&&!phone.isEmpty()){
				sql.addPara("phone", "like", "%"+phone+"%");
			}
			sql.addPara("success", "=", status);
			sql.addPara("expect_time", ">=", startDate);
			if (endDate != null){
				Date d = CommonUtils.addTime(endDate, 23, 59, 59);
				endDate = new Timestamp(d.getTime());
			}
			sql.addPara("expect_time", "<=", endDate);
			sql.addSQLPart(" order by un.last_send_time desc");
			String fullSQL = sql.getFullSQL().toString();
			return jt.query(fullSQL, sql.getParas().toArray(), new MessagePO());
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	
	
	@Override
	public int countMsgsByParas(String typeCode, String phone,
			Integer status, Timestamp startDate, Timestamp endDate) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			sql.setBaseSQL(" select count(*) from " +
						   " ( " + 
					       " SELECT msg_id, bus_type_code, '' phone, msg_content, expect_time,  1 send_way, success FROM net_messages" + 
						   " union" + 
					       " select msg_id, bus_type_code, phone, msg_content, expect_time, 2 send_way, success from phone_messages" +
						   " ) un", true);
			sql.addPara("bus_type_code", "=", typeCode);
			if(phone!=null&&!phone.isEmpty()){
				sql.addPara("phone", "like", "%"+phone+"%");
			}
			sql.addPara("success", "=", status);
			sql.addPara("expect_time", ">=", startDate);
			sql.addPara("expect_time", "<=", endDate);
			String fullSQL = sql.getFullSQL().toString();
			return jt.queryForObject(fullSQL, sql.getParas().toArray(), Integer.class);
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}


}
