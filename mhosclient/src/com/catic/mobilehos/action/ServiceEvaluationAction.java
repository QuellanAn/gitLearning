package com.catic.mobilehos.action;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.catic.mobilehos.po.ShowQuesPO;
import com.catic.mobilehos.service.ServiceEvaluationService;
import com.catic.mobilehos.service.vo.DepartmentNameVO;
import com.catic.mobilehos.service.vo.DoctorNameVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.ServiceEvaluationVO;

/**
 * 后台服务评价记录管理
 * 
 * @author dengshenghui
 * 
 */
public class ServiceEvaluationAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String departmentId;
	private String doctorId;

	private String departmentname;
	private String doctorname;
	
	private String questionnaireId;
	private String appRegOrderId;
	private List<ShowQuesPO> showQuesPOs;

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

	private Page<ServiceEvaluationVO> pageBean;
	private ServiceEvaluationService serviceEvaluationService;

	private List<DepartmentNameVO> depart;
	private List<DoctorNameVO> doctor;

	public List<DoctorNameVO> getDoctor() {
		return doctor;
	}

	public void setDoctor(List<DoctorNameVO> doctor) {
		this.doctor = doctor;
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

	public Page<ServiceEvaluationVO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<ServiceEvaluationVO> pageBean) {
		this.pageBean = pageBean;
	}

	public ServiceEvaluationService getServiceEvaluationService() {
		return serviceEvaluationService;
	}

	public void setServiceEvaluationService(
			ServiceEvaluationService serviceEvaluationService) {
		this.serviceEvaluationService = serviceEvaluationService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getDEFAULT_PAGESIZE() {
		return DEFAULT_PAGESIZE;
	}

	public List<DepartmentNameVO> getDepart() {
		return depart;
	}

	public void setDepart(List<DepartmentNameVO> depart) {
		this.depart = depart;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getDoctorname() {
		return doctorname;
	}

	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}
	
	public String getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public String getAppRegOrderId() {
		return appRegOrderId;
	}

	public void setAppRegOrderId(String appRegOrderId) {
		this.appRegOrderId = appRegOrderId;
	}

	public void getDepartmentNameJson() {
		try {
			List<DepartmentNameVO> depart = this.serviceEvaluationService
					.getAllDepartmentName();
			JSONArray jarr = JSONArray.fromObject(depart);
			this.writeJSON(jarr);
		} catch (Exception e) {
			log.error(null, e);
		}
	}

	public void getDoctorNameJson() {
		try {
			List<DoctorNameVO> doctor = this.serviceEvaluationService
					.getDoctorName(departmentId);
			JSONArray jarr = JSONArray.fromObject(doctor);
			this.writeJSON(jarr);
		} catch (Exception e) {
			log.error(null, e);
		}
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

	/**
	 * 评价详情
	 */
	public String showQuestionnaire(){
		JSONObject json= serviceEvaluationService.findQuestionsById(questionnaireId,appRegOrderId);
		request.setAttribute("data", json);
		return SUCCESS;
	}
	
	
	/**
	 * 获取服务评价记录
	 * 
	 */
	public String getEvaluateList() {
		try {
			
			if (!StringUtils.isEmpty(doctorname)) {
				doctorname = java.net.URLDecoder.decode(doctorname, "utf-8");
			}
			if (!StringUtils.isEmpty(departmentname)) {
				departmentname = java.net.URLDecoder.decode(departmentname, "utf-8");
			}
			String title =request.getParameter("title");
			if (!StringUtils.isEmpty(title)) {
				title = java.net.URLDecoder.decode(title, "utf-8");
			}
			String b = "0";
			pageBean = serviceEvaluationService.queryServiceEvaluationByParas(
					departmentname.equals(b) ? null:departmentname, StringUtils.isNotBlank(doctorname) ? doctorname : null, startSQLDate, endSQLDate, page,
					DEFAULT_PAGESIZE, title);
			Map<String, String> paras = new TreeMap<String, String>();
			paras.put("departmentname", departmentname);
			paras.put("doctorname", doctorname);
			paras.put("startDate", startdate);
			paras.put("endDate", enddate);
			String baseUrl = "busrecord/evaluate/getEvaluateList";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		} catch (Exception e) {
			log.error(null, e);
//			System.out.println(e);
			return ERROR;
		}
	}

}
