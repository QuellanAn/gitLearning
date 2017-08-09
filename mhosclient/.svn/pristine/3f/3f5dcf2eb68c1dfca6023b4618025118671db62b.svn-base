package com.catic.mobilehos.dao.jdbc;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.OrdersPayDAO;
import com.catic.mobilehos.po.AppRegOrdersPO;
import com.catic.mobilehos.po.OrdersPayPO;

/**
 * 预约缴费dao层实现类
 * @author dsh
 *
 */
public class OrdersPayDAOImpl extends JdbcDaoSupport implements OrdersPayDAO {
	
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public int countOrderPayByParas(String orderid, String patientname,
			String status, java.sql.Date startdate, java.sql.Date enddate) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			sql.setBaseSQL("select count(*) from app_reg_orders", true);
			
			
			if(StringUtils.isNotEmpty(patientname)){
				sql.addPara("patient_name", "like", "%" + patientname + "%");
			}
			if(StringUtils.isNotEmpty(orderid)){
				sql.addPara("app_r_order_number", "like", "%" + orderid + "%");
			}
			sql.addPara("paid", "=", status);
//			sql.addPara("doctor_date", ">=", startdate);
//			sql.addPara("doctor_date", "<=", enddate);
			String fullSQL = sql.getFullSQL().toString();
			int c = 0;
			log.debug("sql.getParas().size() = " + sql.getParas().size());
			if (sql.getParas().size() > 0){
				c = jt.queryForObject(fullSQL, sql.getParas().toArray(), Integer.class);
			}else{
				c = jt.queryForObject(fullSQL,  Integer.class);
			}
			return c;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	
		
	}

	@Override
	public List<OrdersPayPO> findOrdersPayByParas(String orderid,
			String patientname, String status, java.sql.Date startdate, java.sql.Date enddate,
			int offset, int length) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			sql.setBaseSQL(" select app_reg_order_id,patient_id,patient_name,payable_fees,paid_fees,app_r_order_number,paid from app_reg_orders", true);
			sql.setLimit(offset, length);
			if(StringUtils.isNotEmpty(patientname)){
				sql.addPara("patient_name", "like", "%" + patientname + "%");
			}
			if(StringUtils.isNotEmpty(orderid)){
				sql.addPara("app_r_order_number", "like", "%" + orderid + "%");
			}
			sql.addPara("paid", "=", status);
			String fullSQL = sql.getFullSQL().toString();
			Object[] args = sql.getParas().toArray();
			List<OrdersPayPO> lst;
			if (sql.getParas().size() > 0){
				lst = jt.query(fullSQL, args, new OrdersPayPO());
			}else{
				lst = jt.query(fullSQL, new OrdersPayPO());
			}
			return lst;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	
}
