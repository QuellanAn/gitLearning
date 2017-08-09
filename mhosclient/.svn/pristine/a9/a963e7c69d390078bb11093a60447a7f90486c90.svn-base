package com.catic.mobilehos.dao.jdbc;

import java.sql.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.ReportsDAO;
import com.catic.mobilehos.po.ChkReportDetailPO;
import com.catic.mobilehos.po.ExamReportDetailPO;
import com.catic.mobilehos.po.ReportsPO;

public class ReportsDAOImpl extends JdbcDaoSupport implements ReportsDAO {
	
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public List<ReportsPO> findReportsByUserId(String userId) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from reports where user_id = ? order by doctor_date desc";
			List<ReportsPO> lst = jt.query(sql, new ReportsPO(), userId.trim());
			return lst;
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public List<ReportsPO> findLimitedReportsByUserId(String userId,
			Date doctorDate) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from reports where user_id = ? and doctor_date >= ? order by doctor_date desc";
			List<ReportsPO> lst = jt.query(sql, new ReportsPO(), userId.trim(), doctorDate);
			return lst;
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}

	}

	@Override
	public ChkReportDetailPO getChkReportDetail(String reportId) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from chk_report_detail where report_id = ?";
			ChkReportDetailPO rd = jt.queryForObject(sql, new ChkReportDetailPO(), reportId);
			return rd;
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}

	}

	@Override
	public List<ExamReportDetailPO> getExamReportDetails(String reportId) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from exam_report_detail where report_id = ?";
			List<ExamReportDetailPO> rds = jt.query(sql, new ExamReportDetailPO(), reportId);
			return rds;
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
	}

}
