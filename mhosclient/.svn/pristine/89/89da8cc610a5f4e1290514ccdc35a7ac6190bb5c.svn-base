package com.catic.mobilehos.dao;

import java.sql.Date;
import java.util.List;

import com.catic.mobilehos.po.ChkReportDetailPO;
import com.catic.mobilehos.po.ExamReportDetailPO;
import com.catic.mobilehos.po.ReportsPO;

public interface ReportsDAO {
	
	List<ReportsPO> findReportsByUserId(String userId);
	
	
	List<ReportsPO> findLimitedReportsByUserId(String userId, Date doctorDate);
	
	
	ChkReportDetailPO getChkReportDetail(String reportId);
	
	List<ExamReportDetailPO> getExamReportDetails(String reportId);

}
