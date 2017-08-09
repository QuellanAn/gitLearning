package com.catic.mobilehos.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.jdbc.JobDAO;
import com.catic.mobilehos.service.vo.Job;

public class JobServiceImpl implements JobService{
	private Log log = LogFactory.getLog(this.getClass());
	private JobDAO jobDAO;
	
	
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}

	public JobDAO getJobDAO() {
		return jobDAO;
	}

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}



	@Override
	public List<Job> getJobList() {
		
		return jobDAO.getJobList();
	}

}
