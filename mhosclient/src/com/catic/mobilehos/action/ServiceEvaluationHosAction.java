package com.catic.mobilehos.action;

import java.sql.Date;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.catic.mobilehos.service.ServiceEvaluationHosService;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.ServiceEvaluationHosVO;

public class ServiceEvaluationHosAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private Page<ServiceEvaluationHosVO> pageBean;
	
	private ServiceEvaluationHosService serviceEvaluationHosService;
	
	private Integer serviceEvaluationId;
	
	private String overallEvaluation;
	private ServiceEvaluationHosVO serviceEvaluationHosVO;
	
	
	private String doctorName;

	private String departmentCode;
	
	
	public Integer getServiceEvaluationId() {
		return serviceEvaluationId;
	}

	public void setServiceEvaluationId(Integer serviceEvaluationId) {
		this.serviceEvaluationId = serviceEvaluationId;
	}

	private String startdate;
	
	/**
	 * 查询的开始日期
	 */
	private Date startSQLDate;

	/**
	 * 查询的结束日期
	 */
	private String enddate;

	/**
	 * 查询的结束日期
	 */
	private Date endSQLDate;

	/**
	 * 当前页
	 */
	private int page;

	/**
	 * 缺省页大小
	 */
	private final int DEFAULT_PAGESIZE = 10;

	public Page<ServiceEvaluationHosVO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<ServiceEvaluationHosVO> pageBean) {
		this.pageBean = pageBean;
	}

	public String getOverallEvaluation() {
		return overallEvaluation;
	}

	public void setOverallEvaluation(String overallEvaluation) {
		this.overallEvaluation = overallEvaluation;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public Date getStartSQLDate() {
		return startSQLDate;
	}

	public void setStartSQLDate(Date startSQLDate) {
		this.startSQLDate = startSQLDate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public Date getEndSQLDate() {
		return endSQLDate;
	}

	public void setEndSQLDate(Date endSQLDate) {
		this.endSQLDate = endSQLDate;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	
	
	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	


	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public ServiceEvaluationHosService getServiceEvaluationHosService() {
		return serviceEvaluationHosService;
	}

	public void setServiceEvaluationHosService(
			ServiceEvaluationHosService serviceEvaluationHosService) {
		this.serviceEvaluationHosService = serviceEvaluationHosService;
	}

	
	
	public ServiceEvaluationHosVO getServiceEvaluationHosVO() {
		return serviceEvaluationHosVO;
	}

	public void setServiceEvaluationHosVO(
			ServiceEvaluationHosVO serviceEvaluationHosVO) {
		this.serviceEvaluationHosVO = serviceEvaluationHosVO;
	}

	public void validateGetEvaluateList() {
		
		if (StringUtils.isNotBlank(this.startdate)) {
			this.startSQLDate = this.toSQLDate(this.startdate);
			if (this.startSQLDate == null) {
				this.addFieldError("", "日期格式不正确！");
			}
		}
		if (StringUtils.isNotBlank(this.enddate)) {
			this.endSQLDate = this.toSQLDate(this.enddate);
			if (this.endSQLDate == null) {
				this.addFieldError("", "日期格式不正确！");
			}
		}
	}
	
	
public void validateStatisticEvaluateHos() {
		
		if (StringUtils.isNotBlank(this.startdate)) {
			this.startSQLDate = this.toSQLDate(this.startdate);
			if (this.startSQLDate == null) {
				this.addFieldError("", "日期格式不正确！");
			}
		}
		if (StringUtils.isNotBlank(this.enddate)) {
			this.endSQLDate = this.toSQLDate(this.enddate);
			if (this.endSQLDate == null) {
				this.addFieldError("", "日期格式不正确！");
			}
		}
	}
	
	
	/**
	 * YJJ
	 * 获取服务评价记录
	 */
	public String getEvaluateList() {
		try {
			
			if (!StringUtils.isEmpty(doctorName)) {
				doctorName = java.net.URLDecoder.decode(doctorName, "utf-8");
			}
			if (!StringUtils.isEmpty(departmentCode)) {
				departmentCode = java.net.URLDecoder.decode(departmentCode, "utf-8");
			}
			String departmentName = request.getParameter("departmentName");
			if (!StringUtils.isEmpty(departmentName)) {
				departmentName = java.net.URLDecoder.decode(departmentName, "utf-8");
			}
			String cardNumber = request.getParameter("cardNumber");
			String patientname = request.getParameter("patientname");
			String evaSource = request.getParameter("evaSource");
			if (!StringUtils.isEmpty(patientname)) {
				patientname=new String(patientname.getBytes("ISO-8859-1"),"utf-8");
				patientname = java.net.URLDecoder.decode(patientname, "utf-8");
			}
			//System.out.println(evaSource);
			pageBean = serviceEvaluationHosService.findListByParas(
					doctorName,departmentCode,overallEvaluation, startSQLDate, endSQLDate, page,
					DEFAULT_PAGESIZE, cardNumber, patientname, departmentName, evaSource);
			//System.out.println(pageBean.getCurPageData().get(0).getOverallEvaluationName());
			Map<String, String> paras = new TreeMap<String, String>();
			paras.put("departmentCode", departmentCode);
			paras.put("doctorName", doctorName);
			paras.put("departmentName", departmentName);
			paras.put("startDate", startdate);
			paras.put("endDate", enddate);
			paras.put("overallEvaluation", overallEvaluation);
			String baseUrl = "busrecord/getEvaluateHosList";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		} catch (Exception e) {
			log.error(null, e);
//			System.out.println(e);
			return ERROR;
		}
	}
	
	
	
	public String findEvaluateHosDetail(){
		
		serviceEvaluationHosVO=serviceEvaluationHosService.findServiceEvaluationHosById(serviceEvaluationId);
		//System.out.println(serviceEvaluationHosVO.getSourceName());
		return SUCCESS;
	}
	
	//评价统计
	
	public String statisticEvaluateHos() {
		try {
			
			/*if (!StringUtils.isEmpty(doctorName)) {
				doctorName = java.net.URLDecoder.decode(doctorName, "utf-8");
			}*/
			
			
			if (!StringUtils.isEmpty(departmentCode)) {
				departmentCode = java.net.URLDecoder.decode(departmentCode, "utf-8");
			}
			
			serviceEvaluationHosVO= serviceEvaluationHosService.statisticEvaluateHos(departmentCode, startSQLDate, endSQLDate);
	
			return SUCCESS;
		} catch (Exception e) {
			log.error(null, e);
//			System.out.println(e);
			return ERROR;
		}
	}
	
	
}
