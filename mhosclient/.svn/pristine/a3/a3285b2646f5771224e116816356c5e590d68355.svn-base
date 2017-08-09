package com.catic.mobilehos.service;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.catic.mobilehos.ErrorCode;
import com.catic.mobilehos.GlobalConstants;
import com.catic.mobilehos.dao.ReportsDAO;
import com.catic.mobilehos.po.ChkReportDetailPO;
import com.catic.mobilehos.po.ExamReportDetailPO;
import com.catic.mobilehos.po.ReportsPO;

public class ReportsServiceImpl implements ReportsService {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private ReportsDAO reportsDAO;
	
	public ReportsDAO getReportsDAO() {
		return reportsDAO;
	}

	public void setReportsDAO(ReportsDAO reportsDAO) {
		this.reportsDAO = reportsDAO;
	}

	@Override
	public ServiceResult getAllReportsListByUserId(String userId) {
		try{
			final List<ReportsPO> reports = reportsDAO.findReportsByUserId(userId);
			return new ServiceResult(){

				@Override
				public boolean isSuccess() {
					return true;
				}

				@Override
				public JSONArray getJSONArray() {
					JSONArray jarr = new JSONArray();
					for(ReportsPO r : reports){
						JSONObject j = new JSONObject();
						j.put("reportid", r.getReportId());
						j.put("patientid", r.getPatientId());
						j.put("patientname", r.getPatientName());
						j.put("departmentname", r.getDepartmentName());
						j.put("doctordate", DateFormatUtils.ISO_DATE_FORMAT.format(r.getDoctorDate()));
						j.put("chkname", r.getChkName());
						j.put("chkdate", DateFormatUtils.format(r.getChkDate(), GlobalConstants.DEF_DATE_A_FMT));
						j.put("reportstatus", String.valueOf(r.getReportStatus()));
						j.put("reporttype", String.valueOf(r.getReportType()));
						j.put("cdepartmentname", r.getCdepartmentName());
						jarr.add(j);
					}
					return jarr;
				}
				
			};
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance(ErrorCode.LS_BUS_EXEC_ERR
					, "系统异常，获取报告列表失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance(ErrorCode.LS_ILLEGAL_BUS_ARGUMENT
					, "参数错误，获取报告列表失败！");
			return sr;
		}
	}

	@Override
	public ServiceResult getMonthReportsListByUserId(String userId) {
		try{
			Date now = new Date();
			// 一个月前的日期
			Date doctorDate = DateUtils.addMonths(now, -1);
			final List<ReportsPO> reports = reportsDAO.findLimitedReportsByUserId(userId
					, new java.sql.Date(doctorDate.getTime()));
			return new ServiceResult(){

				@Override
				public boolean isSuccess() {
					return true;
				}

				@Override
				public JSONArray getJSONArray() {
					JSONArray jarr = new JSONArray();
					for(ReportsPO r : reports){
						JSONObject j = new JSONObject();
						j.put("reportid", r.getReportId());
						j.put("patientid", r.getPatientId());
						j.put("patientname", r.getPatientName());
						j.put("departmentname", r.getDepartmentName());
						j.put("doctordate", DateFormatUtils.ISO_DATE_FORMAT.format(r.getDoctorDate()));
						j.put("chkname", r.getChkName());
						j.put("chkdate", DateFormatUtils.format(r.getChkDate(), GlobalConstants.DEF_DATE_A_FMT));
						j.put("reportstatus", String.valueOf(r.getReportStatus()));
						j.put("reporttype", String.valueOf(r.getReportType()));
						j.put("cdepartmentname", r.getCdepartmentName());
						jarr.add(j);
					}
					return jarr;
				}
			};
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance(ErrorCode.LS_BUS_EXEC_ERR
					, "系统异常，获取报告列表失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance(ErrorCode.LS_ILLEGAL_BUS_ARGUMENT
					, "参数错误，获取报告列表失败！");
			return sr;
		}
	}
	
	

	@Override
	public ServiceResult getChkReportDetail(String reportId) {
		try{
			final ChkReportDetailPO chkDetail = reportsDAO.getChkReportDetail(reportId);
			return new ServiceResult(){

				@Override
				public boolean isSuccess() {
					return true;
				}

				@Override
				public JSONObject getJSON() {
					JSONObject j = new JSONObject();
					j.put("result", chkDetail.getResult());
					j.put("chkdoctor", chkDetail.getChkDoctor());
					j.put("examdoctor", chkDetail.getExamDoctor());
					return j;
				}

			};
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance(ErrorCode.LS_BUS_EXEC_ERR
					, "系统异常，获取检查报告明细失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance(ErrorCode.LS_ILLEGAL_BUS_ARGUMENT
					, "参数错误，获取检查报告明细失败！");
			return sr;
		}
	}
	

	@Override
	public ServiceResult getExamReportDetails(String reportId) {
		try{
			final List<ExamReportDetailPO> details = reportsDAO.getExamReportDetails(reportId);
			return new ServiceResult(){

				@Override
				public boolean isSuccess() {
					return true;
				}

				@Override
				public JSONArray getJSONArray() {
					JSONArray jarr = new JSONArray();
					for(ExamReportDetailPO r : details){
						JSONObject j = new JSONObject();
						j.put("itemname", r.getItemName());
						j.put("resultvalue", r.getResultValue());
						j.put("resultdesc", r.getResultDesc());
						j.put("unit", r.getUnit());
						j.put("refvalue", r.getRefValue());
						jarr.add(j);
					}
					return jarr;
				}
			};
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance(ErrorCode.LS_BUS_EXEC_ERR
					, "系统异常，获取检验报告明细列表失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance(ErrorCode.LS_ILLEGAL_BUS_ARGUMENT
					, "参数错误，获取检验报告明细列表失败！");
			return sr;
		}
	}

}
