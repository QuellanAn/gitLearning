package com.catic.mobilehos.dao.jdbc;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.BusTypeDicDAO;
import com.catic.mobilehos.po.BusTypeDicPO;

public class BusTypeDicDAOImpl extends JdbcDaoSupport implements BusTypeDicDAO {
	
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public BusTypeDicPO getBusTypeDic(String busTypeCode){
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from bus_type_dic where bus_type_code=?";
			BusTypeDicPO busTypeDic = jt.queryForObject(sql, new BusTypeDicPO(), busTypeCode);
			return busTypeDic;
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public List<BusTypeDicPO> getAllBusTypeDic() {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from bus_type_dic";
			List<BusTypeDicPO> lst = jt.query(sql, new BusTypeDicPO());
			return lst;
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public void updateBusTypeDic(String busTypeCode, boolean supportPhoneMsg, boolean supportNetMsg) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "update bus_type_dic set support_phone_msg = ?, support_net_msg = ?"
					     + " where bus_type_code = ?";
			jt.update(sql, supportPhoneMsg ? 1 : 0, supportNetMsg ? 1 : 0, busTypeCode);
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
	}
	
	
	
	
	

}
