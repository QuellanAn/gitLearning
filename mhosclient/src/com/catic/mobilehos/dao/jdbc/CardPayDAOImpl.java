package com.catic.mobilehos.dao.jdbc;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.CardPayDao;
import com.catic.mobilehos.po.CardPayDetailPO;
import com.catic.mobilehos.po.CardPayPO;

public class CardPayDAOImpl extends JdbcDaoSupport implements CardPayDao{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public CardPayPO findCardPayPOByCPId(String cpId) {
		// TODO Auto-generated method stub
		String sql = "select cp.*,p.patient_name AS patientName from card_pay cp, patients p where cp.patient_id = p.patient_id and cp.cp_id = '"+cpId+"'";
		//System.out.println("sq "+sql);
		List<CardPayPO> list = this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(CardPayPO.class));
		//List<AppRegOrdersPO> list = this.getJdbcTemplate().query(sql, new Object[]{appRegOrderId}, new CardPayPO());
		if(list != null && list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public int countCardPayByParas(String cpId, String receiptNo, String payWay,
			String cardNumber, String startCreateTime,
			String endCreateTime) {
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			sql.setBaseSQL("select count(*) from card_pay", true);
			sql.addPara("create_date", ">=", startCreateTime);
			sql.addPara("create_date", "<=", endCreateTime);
			if (StringUtils.isNotBlank(cpId)) {
				sql.addPara("cp_id", "=", cpId);
			}
			if (StringUtils.isNotBlank(receiptNo)) {
				sql.addPara("receiptNo", "=", receiptNo);
			}
			if (StringUtils.isNotBlank(payWay)) {
				sql.addPara("payWay", "=", payWay);
			}
			if (StringUtils.isNotBlank(cardNumber)) {
				sql.addPara("card_number", "=", cardNumber);
			}
			String fullSQL = sql.getFullSQL().toString();
			int c = 0;
			if (sql.getParas().size() > 0) {
				c = jt.queryForObject(fullSQL, sql.getParas().toArray(),
						Integer.class);
			} else {
				c = jt.queryForObject(fullSQL, Integer.class);
			}
			//System.out.println(c+" "+fullSQL);
			return c;
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CardPayPO> findCardPayByParas(String cpId, String receiptNo, String payWay,
			String cardNumber, String startCreateTime,
			String endCreateTime, int offset, int length) {
		List<CardPayPO> list = null;
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select cp.*,p.patient_name AS patientName from card_pay cp, patients p where cp.patient_id=p.patient_id";
			StringBuffer sb = new StringBuffer(sql);
			if (StringUtils.isNotBlank(cpId)) {
				sb.append(" AND cp.cp_id = '"+cpId+"'");
			}
			if (StringUtils.isNotBlank(receiptNo)) {
				sb.append(" AND cp.receiptNo = '"+receiptNo+"'");
			}
			if (StringUtils.isNotBlank(payWay)) {
				sb.append(" AND cp.payWay = '"+payWay+"'");			
			}
			if (StringUtils.isNotBlank(cardNumber)) {
				sb.append(" AND cp.card_number = '"+cardNumber+"'");
			}
			if (startCreateTime != null) {
				sb.append(" AND cp.create_date >= '"+startCreateTime+"'");
			}
			if (endCreateTime != null) {
				sb.append(" AND cp.create_date <= '"+endCreateTime+"'");
			}
			sb.append(" order by cp.create_date desc");
			sb.append(" LIMIT "+offset+" ,"+length+"" );
			//System.out.println(offset+"  "+length+" sql "+sb.toString());
			list = jt.query(sb.toString(), new BeanPropertyRowMapper(CardPayPO.class));
			//System.out.println(list.size());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public CardPayDetailPO findCardPayDetailPOByCPId(String cpId) {
		// TODO Auto-generated method stub
		String sql = "select * from card_pay_detail where cp_id = '"+cpId+"'";
		//System.out.println("sq "+sql);
		List<CardPayDetailPO> list = this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(CardPayDetailPO.class));
		//List<AppRegOrdersPO> list = this.getJdbcTemplate().query(sql, new Object[]{appRegOrderId}, new CardPayPO());
		if(list != null && list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CardPayDetailPO> findCardPayDetailByParas(String cpId,
			int offset, int length) {
		List<CardPayDetailPO> list = null;
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			//String sql = "select * from card_pay_detail where cp_id= ? limit ?,?";
			//list = jt.query(sql, new Object[]{cpId, offset, length}, new BeanPropertyRowMapper(CardPayDetailPO.class));
			String sql = "select * from card_pay_detail where 1=1";
			StringBuffer sb = new StringBuffer(sql);
			sb.append(" AND cp_id = '"+cpId+"'");
			sb.append(" LIMIT "+offset+","+length+"" );
			list = jt.query(sb.toString(), new BeanPropertyRowMapper(CardPayDetailPO.class));
			//System.out.println(offset+" "+length+" deta "+list.size()+"  "+sb.toString());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int countCardPayDetailByParas(String cpId) {
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select count(*) from card_pay_detail where cp_id=?";
			int c = 0;
			c= jt.queryForObject(sql, Integer.class, new Object[]{cpId});
			//System.out.println(c+" 9");
			return c;
			/*SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			sql.setBaseSQL("select count(*) from card_pay_detail", true);
			if (StringUtils.isNotBlank(cpId)) {
				sql.addPara("cp_id", "=", cpId);
			}
			String fullSQL = sql.getFullSQL().toString();
			System.out.println("sql  "+fullSQL);
			int c = 0;
			if (sql.getParas().size() > 0) {
				System.out.println("c1 "+c);
				c = jt.queryForObject(fullSQL, sql.getParas().toArray(), Integer.class);
				System.out.println("c2 "+c);
			} else {
				c = jt.queryForObject(fullSQL, Integer.class);
			}
			System.out.println(c);
			return c;*/
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

}
