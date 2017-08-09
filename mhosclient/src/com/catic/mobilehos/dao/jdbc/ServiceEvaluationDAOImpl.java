package com.catic.mobilehos.dao.jdbc;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.ServiceEvaluationDAO;
import com.catic.mobilehos.po.AppRegOrdersPO;
import com.catic.mobilehos.po.QuestionPO;
import com.catic.mobilehos.po.ServiceEvaluationPO;
import com.catic.mobilehos.po.ShowQuesPO;
import com.catic.mobilehos.utils.CommonUtils;
import com.catic.mobilehos.utils.DateUtil;

/**
 * 服务评价dao层实现类
 * 
 * @author dsh
 * 
 */
public class ServiceEvaluationDAOImpl extends JdbcDaoSupport implements
		ServiceEvaluationDAO {

	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public int countServiceEvaluationByparas(String departmentId,
			String doctorId, java.sql.Date startDate, java.sql.Date endDate, String title) {
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			sql.setBaseSQL(
					"select COUNT(distinct f.app_reg_order_id ) FROM feedback f LEFT JOIN app_reg_orders a ON f.app_reg_order_id=a.app_reg_order_id LEFT JOIN questionnaire q ON q.id=f.questionnaire_id WHERE 1=1",
					false);
			if(StringUtils.isNotBlank(title)){
				sql.addPara("q.title", "like", "%"+title+"%");			
			}
			if(StringUtils.isNotBlank(departmentId)){
				sql.addPara("a.department_id", "=", departmentId);			
			}
			if(StringUtils.isNotBlank(doctorId)){
				sql.addPara("a.doctor_id", "=", doctorId);
			}
			if(startDate != null){
				Date start=CommonUtils.addTime(startDate, 0, 0, 0);
				startDate= new java.sql.Date(start.getTime());
				sql.addPara("f.createtime", ">=", startDate);
				Date d = CommonUtils.addTime(endDate, 23, 59, 59);
				endDate = new java.sql.Date(d.getTime());
				sql.addPara("f.createtime", "<=", endDate);
			}
			String fullSQL = sql.getFullSQL().toString();
			//System.out.println(fullSQL);
			int c = 0;
			log.debug("sql.getParas().size() = " + sql.getParas().size());
			if (sql.getParas().size() > 0) {
				c = jt.queryForObject(fullSQL, sql.getParas().toArray(),
						Integer.class);
			} else {
				c = jt.queryForObject(fullSQL, Integer.class);
			}
			return c;
		} catch (DataAccessException ex) {
			System.out.println(ex);
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public List<ServiceEvaluationPO> findServiceEvaluationByParas(
			String departmentId, String doctorId, java.sql.Date startDate,
			java.sql.Date endDate, int offset, int length, String title) {
//		System.out.println(departmentId);
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			sql.setBaseSQL(
					"select a.app_reg_order_id,a.patient_name,a.department_name,a.doctor_name,a.doctor_date,q.title,q.id ,f.createtime FROM feedback f LEFT JOIN app_reg_orders a ON f.app_reg_order_id =a.app_reg_order_id LEFT JOIN questionnaire q ON f.questionnaire_id =q.id",
					true);
			sql.setLimit(offset, length);
			sql.addPara("a.department_id", "=", departmentId);
			if(StringUtils.isNotBlank(title)){
				sql.addPara("q.title", "like", "%"+title+"%");			
			}
			if (StringUtils.isNotEmpty(doctorId)) {
				sql.addPara("a.doctor_name", "like", "%" + doctorId + "%");
			}
			
			if (StringUtils.isNotEmpty(departmentId)) {
				sql.addPara("a.department_name", "like", "%" + departmentId + "%");
			}
			if(startDate != null){
				Date date1 = new Date(startDate.getTime());
				String startDate1 = DateUtil.date2Str(date1,"yyyy-MM-dd")+" 00:00:00";
				startDate = DateUtil.strToDate(startDate1);
				
				Date date = new Date(endDate.getTime());
				String endDate1 = DateUtil.date2Str(date,"yyyy-MM-dd")+" 59:23:23";
				endDate = DateUtil.strToDate(endDate1);
				sql.addPara("f.createtime", ">=", startDate1);
				sql.addPara("f.createtime", "<=", endDate1);
			}
			sql.addSQLPart("GROUP BY a.app_reg_order_id ORDER BY f.createtime DESC");

			String fullSQL = sql.getFullSQL().toString();
			Object[] args = sql.getParas().toArray();

			List<ServiceEvaluationPO> lst;
			//System.out.println(fullSQL);
			if (sql.getParas().size() > 0) {
				lst = jt.query(fullSQL, args, new ServiceEvaluationPO());
			} else {
				lst = jt.query(fullSQL, new ServiceEvaluationPO());
			}
			
			return lst;
		} catch (Exception ex) {
			ex.printStackTrace();
			// log.error(null,ex);
			return null;
		}

	}

	@Override
	public List<ShowQuesPO> findQuestionsById(
			String questionnaireId, String appRegOrderId) {
		try {
			String sql ="SELECT  f.suggest ,qn.title,q.* FROM question q LEFT JOIN feedback f ON q.id=f.question_id LEFT JOIN questionnaire qn ON qn.id=q.questionnaire_id WHERE f.app_reg_order_id=? AND q.questionnaire_id =?";
			return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<ShowQuesPO>(ShowQuesPO.class), appRegOrderId,questionnaireId);
		} catch (DataAccessException ex) {
			// log.error(null, ex);
			return null;
		}
		
	}


}
