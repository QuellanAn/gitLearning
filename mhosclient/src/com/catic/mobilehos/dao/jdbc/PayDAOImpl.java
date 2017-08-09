package com.catic.mobilehos.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.PayDAO;
import com.catic.mobilehos.po.AppRegOrdersPO;
import com.catic.mobilehos.po.CardPayPO;
import com.catic.mobilehos.po.DocBillDetailPO;
import com.catic.mobilehos.po.DocBillsPO;
import com.catic.mobilehos.po.PayPO;

/**
 * 预约缴费dao层实现类
 * @author dsh
 *
 */
public class PayDAOImpl extends JdbcDaoSupport implements PayDAO {
	
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public int countPayByParas(String docbillsn, String patientname,
			String status, String curdocsn, java.sql.Date startdate, java.sql.Date enddate) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			sql.setBaseSQL("select count(*)  FROM doc_bills", true);
			if(StringUtils.isNotEmpty(patientname)){
				sql.addPara("patient_name", "like", "%" + patientname + "%");
			}
			if(StringUtils.isNotEmpty(docbillsn)){
				sql.addPara("doc_bill_sn", "like", "%" + docbillsn + "%");
			}
			if(StringUtils.isNotEmpty(curdocsn)){
				sql.addPara("cur_doc_sn", "like", "%" + curdocsn + "%");
			}
			//sql.addPara("paid", "=", status);
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
			//System.out.println(c+" count "+fullSQL);
			return c;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<PayPO> findPayByParas(String docbillsn, String patientname,
			String status, String curdocsn, java.sql.Date startdate, java.sql.Date enddate,
			int offset, int length) {
		// TODO Auto-generated method stub
		try{
			/*JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "SELECT db.*, dbp.* FROM doc_bills db, doc_bill_payment dbp where db.doc_bill_sn = dbp.doc_bill_sn ";
			StringBuffer sb = new StringBuffer(sql);
			if(StringUtils.isNotEmpty(patientname)){
				sb.append(" and db.patient_name like %" + patientname + "%");
			}
			if(StringUtils.isNotEmpty(docbillsn)){
				sb.append(" and doc_bill_sn like %" + docbillsn + "%");
			}
			if(StringUtils.isNotEmpty(curdocsn)){
				sb.append(" and cur_doc_sn like %" + curdocsn + "%");
			}
			sb.append(" order by db.`create_date` desc");
			sb.append(" LIMIT "+offset+" ,"+length+"" );
			List<PayPO> lst;
			lst = jt.query(sb.toString(), new BeanPropertyRowMapper(PayPO.class));
			
			System.out.println("full "+sb.toString());*/
			
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			//sql.setBaseSQL("SELECT doc_bill_sn ,patient_id,patient_name,amount,cur_doc_sn FROM doc_bills", true);
			sql.setBaseSQL("SELECT db.*, dbp.* FROM doc_bills db, doc_bill_payment dbp ", true);
			sql.setLimit(offset, length);
			if(StringUtils.isNotEmpty(patientname)){
				sql.addPara("db.patient_name", "like", "%" + patientname + "%");
			}
			if(StringUtils.isNotEmpty(docbillsn)){
				sql.addPara("db.doc_bill_sn", "like", "%" + docbillsn + "%");
			}
			if(StringUtils.isNotEmpty(curdocsn)){
				sql.addPara("db.cur_doc_sn", "like", "%" + curdocsn + "%");
			}
			
			if(sql.getParas() != null && sql.getParas().size() > 2){
				sql.addSQLPart("and db.doc_bill_sn = dbp.doc_bill_sn");
			}else{
				sql.addSQLPart("where db.doc_bill_sn = dbp.doc_bill_sn");
			}
			sql.addSQLPart("order by db.create_date desc");
			String fullSQL = sql.getFullSQL().toString();
			//System.out.println("full "+fullSQL+"  "+sql.getParas().size());
			Object[] args = sql.getParas().toArray();
			List<PayPO> lst;
			if (sql.getParas().size() > 0){
				lst = jt.query(fullSQL, args, new PayPO());
			}else{
				lst = jt.query(fullSQL, new PayPO());
			}
			return lst;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

	@Override
	public List<DocBillDetailPO> findPayInfoByDocBillSn( String docBillSn) {
		// TODO Auto-generated method stub
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "SELECT b.doc_bill_sn,b.cur_doc_sn,d.patient_name,d.amount,b.bill_item_name,b.item_amount FROM doc_bills d LEFT JOIN doc_bill_detail b ON d.cur_doc_sn= b.cur_doc_sn WHERE b.doc_bill_sn = ? ";
			final List<DocBillDetailPO> docBillDetail =new ArrayList<DocBillDetailPO>();
			
			jt.query(sql, new Object[] { docBillSn }, new RowCallbackHandler() {

				@Override
				public void processRow(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					DocBillDetailPO dbpo = new DocBillDetailPO();
					dbpo.setDocBillSN(rs.getString("doc_bill_sn"));
					dbpo.setCurDocSN(rs.getString("cur_doc_sn"));
					dbpo.setPatientName(rs.getString("patient_name"));
					dbpo.setBillItemName(rs.getString("bill_item_name"));
					dbpo.setItemAmount(rs.getDouble("item_amount"));
					docBillDetail.add(dbpo);
					
				}
			});
			System.out.println(docBillSn+" PayInfo "+sql);
			return docBillDetail;
		}catch(DataAccessException ex) {
			throw ex;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DocBillsPO findDocBillsPOByDocBillSn(String docBillSn) {
		// TODO Auto-generated method stub
		String sql = "select * from doc_bills where doc_bill_sn = ?";
		//List<DocBillsPO> list = this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(DocBillsPO.class));
		List<DocBillsPO> list = this.getJdbcTemplate().query(sql, new Object[]{docBillSn}, new DocBillsPO());
		if(list != null && list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}

	
}
