package com.catic.mobilehos.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.TradeDAO;
import com.catic.mobilehos.po.TradePO;

public class TradeDaoImpl extends JdbcDaoSupport implements TradeDAO{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<TradePO> findTradePOsByParas(String cardNo, String tradeNo, String transactionId, String status, 
			String startSQLDate, String endSQLDate, int offset, int length, String patientname) {
		// TODO Auto-generated method stub
		List<TradePO> list = null;
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			//String sql = "select t.*,p.patient_name from tradeorders t left join patients p on t.cardNo = p.cardNo where 1=1";
			String sql = "select t.* from tradeorders t where 1=1";
			StringBuffer sb = new StringBuffer(sql);
			if(StringUtils.isNotBlank(cardNo)){
				sb.append("  and t.cardNo = '"+cardNo+"'");
			}
			if(StringUtils.isNotBlank(tradeNo)){
				sb.append("  and t.tradeNo = '"+tradeNo+"'");
			}
			if(StringUtils.isNotBlank(transactionId)){
				sb.append("  and t.transactionId = '"+transactionId+"'");
			}
			if(StringUtils.isNotBlank(status) && !"0".equals(status)){
				sb.append("  and t.status = '"+status+"'");
			}else if(!StringUtils.isNotBlank(status)){
				sb.append("  and t.status != '0'");
			}
			if (startSQLDate != null && StringUtils.isNotBlank(startSQLDate)) {
				sb.append(" AND t.endDate >= '"+startSQLDate+"'");
			}
			if (endSQLDate != null && StringUtils.isNotBlank(endSQLDate)) {
				sb.append(" AND t.endDate <= '"+endSQLDate+"'");
			}
			if (StringUtils.isNotBlank(patientname)) {
				sb.append(" AND t.attach like '%"+patientname+"%'");
			}
			sb.append(" order by t.endDate desc");
			sb.append(" LIMIT "+offset+" ,"+length+"" );
			list = jt.query(sb.toString(), new BeanPropertyRowMapper(TradePO.class));
			//System.out.println(list.size()+" li "+sb.toString());
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int countTradePOsByParas(String cardNo, String tradeNo, String transactionId, String status, 
			String startSQLDate, String endSQLDate, String patientname) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			//sql.setBaseSQL("select count(*) from tradeorders where status != 0", true);
			sql.setBaseSQL("select count(*) from tradeorders ", true);
			if(StringUtils.isNotBlank(cardNo)){
				sql.addPara("cardNo", "=", cardNo);
			}
			if(StringUtils.isNotBlank(tradeNo)){
				sql.addPara("tradeNo", "=", tradeNo);
			}
			if(StringUtils.isNotBlank(transactionId)){
				sql.addPara("transactionId", "=", transactionId);
			}
			if(StringUtils.isNotBlank(status) && !"0".equals(status)){
				sql.addPara("status", "=", status);
			}else if(!StringUtils.isNotBlank(status)){
				sql.addPara("status", "!=", "0");
			}
			if(StringUtils.isNotBlank(startSQLDate) && StringUtils.isNotBlank(endSQLDate)){
				sql.addPara("endDate", ">=", startSQLDate);
				sql.addPara("endDate", "<=", endSQLDate);
			}
			if (StringUtils.isNotEmpty(patientname)) {
				sql.addPara("attach", "like", "%" + patientname + "%");
			}
			String fullSQL = sql.getFullSQL().toString();
			int c = 0;
			if (sql.getParas().size() > 0) {
				c = jt.queryForObject(fullSQL, sql.getParas().toArray(),
						Integer.class);
			} else {
				c = jt.queryForObject(fullSQL, Integer.class);
			}
			//System.out.println(c+" c "+fullSQL);
			return c;
		} catch (DataAccessException ex) {
			// TODO: handle exception
			throw ex;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<TradePO> showTradeInfo(String tradeNo) {
		// TODO Auto-generated method stub
		List<TradePO> list = null;
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			//String sql = "select t.*,p.patient_name from tradeorders t,patients p where t.cardNo = p.cardNo and t.tradeNo= '"+tradeNo+"' group by t.cardNo";
			String sql = "select t.* from tradeorders t where t.tradeNo= '"+tradeNo+"' group by t.cardNo";
			list = jt.query(sql, new BeanPropertyRowMapper(TradePO.class));
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TradePO> getCountByDate(TradePO po) {
		String pattern = "%Y-%m-%d";
		if("month".equals(po.getCountDateType())){
			pattern = "%Y-%m";
		}
		StringBuffer sql = new StringBuffer("SELECT DATE_FORMAT(t.endDate, '" + pattern + "') as countDate, count(1) as count FROM tradeorders t WHERE 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(StringUtils.isNotBlank(po.getStartTime())){
			sql.append(" AND t.endDate>=?");
			params.add(po.getStartTime()+" 00:00:00");
		}
		if(StringUtils.isNotBlank(po.getEndTime())){
			sql.append(" AND t.endDate<=?");
			params.add(po.getEndTime()+" 23:59:59");
		}
		if(StringUtils.isNotBlank(po.getRechargeSource())){
			sql.append(" AND t.recharge_source=?");
			params.add(po.getRechargeSource());
		}
		sql.append(" AND t. STATUS != '0' GROUP BY DATE_FORMAT(t.endDate, '" + pattern + "') ");
		return getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<TradePO>(TradePO.class));
	}

	@Override
	public List<TradePO> getCountBySource(TradePO po) {
		StringBuffer sql = new StringBuffer("SELECT t.recharge_source as rechargeSource, count(1) as count FROM tradeorders t WHERE 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(StringUtils.isNotBlank(po.getStartTime())){
			sql.append(" AND t.endDate>=?");
			params.add(po.getStartTime()+" 00:00:00");
		}
		if(StringUtils.isNotBlank(po.getEndTime())){
			sql.append(" AND t.endDate<=?");
			params.add(po.getEndTime()+" 23:59:59");
		}
		/*if(StringUtils.isNotBlank(po.getRechargeSource())){
			sql.append(" AND t.recharge_source=?");
			params.add(po.getRechargeSource());
		}*/
		sql.append(" AND t. STATUS != '0' GROUP BY t.recharge_source ");
		return getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<TradePO>(TradePO.class));
	}
	
}
