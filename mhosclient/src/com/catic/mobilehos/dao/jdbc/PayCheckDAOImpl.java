package com.catic.mobilehos.dao.jdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.PayCheckDAO;
import com.catic.mobilehos.po.DocBillDetailPO;
import com.catic.mobilehos.po.PayCheckPO;
import com.catic.mobilehos.po.PayPO;

/**
 * 手机支付对账dao层实现类
 * @author dsh
 *
 */
public class PayCheckDAOImpl extends JdbcDaoSupport implements PayCheckDAO {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	
	@Override
	public int countPayCheckByParas(String orderid, String patientname,
			String payid, String wechatid, String tradetype, String status,
			java.sql.Date startdate, java.sql.Date enddate) {
		// TODO Auto-generated method stub
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			sql.setBaseSQL("select count(*)  FROM pay_check", true);
			if(StringUtils.isNotEmpty(patientname)){
				sql.addPara("patient_name", "like", "%" + patientname + "%");
			}
			if(StringUtils.isNotEmpty(orderid)){
				sql.addPara("app_reg_order_id", "like", "%" + orderid + "%");
			}
			if(StringUtils.isNotEmpty(payid)){
				sql.addPara("pay_id", "like", "%" + payid + "%");
			}
			if(StringUtils.isNotEmpty(wechatid)){
				sql.addPara("wechat_id", "like", "%" + wechatid + "%");
			}
			sql.addPara("trade_type", "=", tradetype);
			sql.addPara("status", "=", status);
			sql.addPara("create", ">=", startdate);
			sql.addPara("create", "<=", enddate);
			
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
	public List<PayCheckPO> findPayCheckByParas(String orderid,
			String patientname, String payid, String wechatid,
			String tradetype, String status, java.sql.Date startdate, java.sql.Date enddate,
			int offset, int length) {
		try{
			
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			sql.setBaseSQL("SELECT * FROM pay_check", true);
			sql.setLimit(offset, length);
			
			if(StringUtils.isNotEmpty(patientname)){
				sql.addPara("patient_name", "like", "%" + patientname + "%");
			}
			if(StringUtils.isNotEmpty(orderid)){
				sql.addPara("app_reg_order_id", "like", "%" + orderid + "%");
			}
			if(StringUtils.isNotEmpty(payid)){
				sql.addPara("pay_id", "like", "%" + payid + "%");
			}
			if(StringUtils.isNotEmpty(wechatid)){
				sql.addPara("wechat_id", "like", "%" + wechatid + "%");
			}
			
			sql.addPara("trade_type", "=", tradetype);
			sql.addPara("status", "=", status);
			sql.addPara("create", ">=", startdate);
			sql.addPara("create", "<=", enddate);
			
			String fullSQL = sql.getFullSQL().toString();
			Object[] args = sql.getParas().toArray();
			List<PayCheckPO> lst;
			if (sql.getParas().size() > 0){
				lst = jt.query(fullSQL, args, new PayCheckPO());
			}else{
				lst = jt.query(fullSQL, new PayCheckPO());
			}
			return lst;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

	
}
