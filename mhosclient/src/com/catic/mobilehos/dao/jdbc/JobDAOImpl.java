package com.catic.mobilehos.dao.jdbc;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.catic.mobilehos.service.vo.Job;

public class JobDAOImpl extends JdbcDaoSupport implements JobDAO {
	
	private Log log = LogFactory.getLog(this.getClass());
	@Override
	public List<Job> getJobList(){
		StringBuffer sql=new StringBuffer();
		JdbcTemplate jt = this.getJdbcTemplate();
		sql.append("SELECT job.id,job.jobName FROM job");
		log.debug("getJobList sql: " + sql.toString());
		List<Job> lt=jt.query(sql.toString(),new Job());
	
		return lt;
	};
}
