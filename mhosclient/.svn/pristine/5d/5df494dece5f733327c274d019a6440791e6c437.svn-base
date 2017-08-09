package com.catic.mobilehos.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.ItemsDao;
import com.catic.mobilehos.po.ItemsList;
import com.catic.mobilehos.po.ItemsPO;

public class ItemsDaoImpl extends JdbcDaoSupport implements ItemsDao{

	@Override
	public ItemsPO findItemsById(String admRowid) {
		// TODO Auto-generated method stub
		String sql = "select * from items where admRowid = ?";
		List<ItemsPO> list = getJdbcTemplate().query(sql, new Object[]{admRowid}, new ItemsPO());
		if(list==null||list.size()==0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<ItemsList> findItemsListsByParms(String admRowid, String tarOCCateDesc) {
		// TODO Auto-generated method stub
		String sql = "select * from items_list where 1 = 1";
		if(StringUtils.isNotBlank(admRowid)){
			sql+= " AND admRowid = ?";
		}
		if(StringUtils.isNotBlank(tarOCCateDesc)){
			sql+= " AND tarOCCateDesc like % ? %";
		}
		List<ItemsList> list = getJdbcTemplate().query(sql, new Object[]{admRowid, tarOCCateDesc}, new ItemsList());
		if(list==null||list.size()==0){
			return null;
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<ItemsPO> findItemsPOsByParms(String cardNo, String patientname, String tarOCCateDesc, String admRowid,
			String startCreateTime, String endCreateTime, int offset, int length) {
		List<ItemsPO> list = null;
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select t.* from (select it.*,GROUP_CONCAT(il.tarOCCateDesc) as tarOCCateDesc from items it INNER JOIN items_list il ON il.admRowid=it.admRowid";
			StringBuffer sb = new StringBuffer(sql);
			if(StringUtils.isNotBlank(admRowid)){
				sb.append(" AND it.admRowid = '"+admRowid+"'");
			}
			if (StringUtils.isNotBlank(startCreateTime)) {
				sb.append(" AND it.admDate >= '"+startCreateTime+"'");
			}
			if (StringUtils.isNotBlank(endCreateTime)) {
				sb.append(" AND it.admDate <= '"+endCreateTime+"'");
			}
			if(StringUtils.isNotBlank(cardNo)){
				sb.append(" AND it.cardNo = '"+cardNo+"'");
			}
			if(StringUtils.isNotBlank(patientname)){
				sb.append(" AND it.patient_Name like '%"+patientname+"%'");
			}
			sb.append(" GROUP BY it.admRowid order by it.admDate desc ) t");
			if(StringUtils.isNotBlank(tarOCCateDesc)){
				sb.append(" where t.tarOCCateDesc like '%"+tarOCCateDesc+"%'");
			}
			sb.append(" LIMIT "+offset+","+length+"" );
			//System.out.println(tarOCCateDesc+" "+length+" deta "+"  "+sb.toString());
			list = jt.query(sb.toString(), new BeanPropertyRowMapper(ItemsPO.class));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int countItemsPOsByParms(String cardNo, String patientname, String tarOCCateDesc, String admRowid, String startCreateTime,
			String endCreateTime) {
		// TODO Auto-generated method stub
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			sql.setBaseSQL("SELECT COUNT(*) FROM (select count(*),GROUP_CONCAT(il.tarOCCateDesc) as tarOCCateDesc from items it left JOIN items_list il ON il.admRowid=it.admRowid where 1=1", false);
			if(StringUtils.isNotBlank(startCreateTime) && StringUtils.isNotBlank(endCreateTime)){
				sql.addPara("it.admDate", ">=", startCreateTime);
				sql.addPara("it.admDate", "<=", endCreateTime);
			}
			if(StringUtils.isNotBlank(admRowid)){
				sql.addPara("it.admRowid", "=", admRowid);
			}
			if(StringUtils.isNotBlank(cardNo)){
				sql.addPara("it.cardNo", "=", cardNo);
			}
			if(StringUtils.isNotBlank(patientname)){
				sql.addPara("it.patient_Name", "like", "%" + patientname +"%");
			}
			sql.addSQLPart("GROUP BY it.admRowid) t");
			if(StringUtils.isNotBlank(tarOCCateDesc)){
				sql.addSQLPart(" where t.tarOCCateDesc like '%"+ tarOCCateDesc +"%'"); 
			}
			//System.out.println(sql.getFullSQL().toString()+"  "+tarOCCateDesc);
			int count = jt.queryForObject(sql.getFullSQL().toString()
					, sql.getParas().toArray(), Integer.class);
			return count;
		} catch(DataAccessException ex){
			throw ex;
		}
	}

	@Override
	public List<ItemsPO> getCountByDate(ItemsPO po) {
		String pattern = "%Y-%m-%d";
		if("month".equals(po.getCountDateType())){
			pattern = "%Y-%m";
		}
		//StringBuffer sql = new StringBuffer("SELECT DATE_FORMAT(it.admDate, '" + pattern + "') as countDate, count(DISTINCT it.id) as count FROM items it INNER JOIN items_list il ON il.admRowid = it.admRowid WHERE it.`status`='0' ");
		StringBuffer sql = new StringBuffer("SELECT DATE_FORMAT(it.creatDate, '" + pattern + "') as countDate, count(DISTINCT it.orderId) as count FROM pay_order it WHERE it.`body` NOT LIKE '充值' ");
		List<Object> params = new ArrayList<Object>();
		if(StringUtils.isNotBlank(po.getBeginDate())){
			sql.append(" AND it.creatDate>=?");
			params.add(po.getBeginDate());
		}
		if(StringUtils.isNotBlank(po.getEndDate())){
			sql.append(" AND it.creatDate<=?");
			params.add(po.getEndDate()+" 23:59:59");
		}
		if(StringUtils.isNotBlank(po.getPaySource())){
			sql.append(" AND it.payScene=?");
			params.add(po.getPaySource());
		}
		sql.append(" GROUP BY DATE_FORMAT(it.creatDate, '" + pattern + "') ");
		return getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<ItemsPO>(ItemsPO.class));
	}

	@Override
	public List<ItemsPO> getCountBySource(ItemsPO po) {
		//StringBuffer sql = new StringBuffer("SELECT it.pay_source as paySource, count(DISTINCT it.id) as count FROM items it INNER JOIN items_list il ON il.admRowid = it.admRowid WHERE it.`status`='0' ");
		StringBuffer sql = new StringBuffer("SELECT it.payScene as paySource, count(DISTINCT it.orderId) as count FROM pay_order it WHERE it.`body` NOT LIKE '充值' ");
		List<Object> params = new ArrayList<Object>();
		if(StringUtils.isNotBlank(po.getBeginDate())){
			sql.append(" AND it.creatDate>=?");
			params.add(po.getBeginDate());
		}
		if(StringUtils.isNotBlank(po.getEndDate())){
			sql.append(" AND it.creatDate<=?");
			params.add(po.getEndDate());
		}
		sql.append(" GROUP BY it.payScene ");
		return getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<ItemsPO>(ItemsPO.class));
	}
	
}
