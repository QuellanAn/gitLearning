package com.catic.mobilehos.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.po.PatientCardPO;
import com.catic.mobilehos.po.PatientPO;
import com.catic.mobilehos.po.UserPO;
import com.catic.mobilehos.service.PatientsService;
import com.catic.mobilehos.service.UserMngService;
import com.catic.mobilehos.service.vo.Page;

/**
 * 后台会员管理
 * 
 * @author xieweipeng
 * 
 */
public class UserMngAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private final int DEFAULT_PAGESIZE = 10;

	private Log log = LogFactory.getLog(this.getClass());

	private UserMngService userMngService;

	private String userId;

	private Page<UserPO> pageBean;
	private int page;

	private String user_name;
	private String status;
	private Timestamp start_create_date;
	private Timestamp end_create_date;

	private List<PatientPO> patientPOs;
	private List<PatientCardPO> patientCardPOs;
	private UserPO userPO;
	private PatientsService patientsService;
	
	public PatientsService getPatientsService() {
		return patientsService;
	}

	public void setPatientsService(PatientsService patientsService) {
		this.patientsService = patientsService;
	}

	public List<PatientCardPO> getPatientCardPOs() {
		return patientCardPOs;
	}

	public void setPatientCardPOs(List<PatientCardPO> patientCardPOs) {
		this.patientCardPOs = patientCardPOs;
	}

	public UserPO getUserPO() {
		return userPO;
	}

	public void setUserPO(UserPO userPO) {
		this.userPO = userPO;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserMngService getUserMngService() {
		return userMngService;
	}

	public void setUserMngService(UserMngService userMngService) {
		this.userMngService = userMngService;
	}

	public Page<UserPO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<UserPO> pageBean) {
		this.pageBean = pageBean;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getStart_create_date() {
		return start_create_date;
	}

	public void setStart_create_date(Timestamp start_create_date) {
		this.start_create_date = start_create_date;
	}

	public Timestamp getEnd_create_date() {
		return end_create_date;
	}

	public void setEnd_create_date(Timestamp end_create_date) {
		this.end_create_date = end_create_date;
	}

	public List<PatientPO> getPatientPOs() {
		return patientPOs;
	}

	public void setPatientPOs(List<PatientPO> patientPOs) {
		this.patientPOs = patientPOs;
	}

	public String getAllUsers() {
		try {
			JSONArray result = userMngService.getAllUsersList();
			this.writeJSON(result);
			return null;
		} catch (Exception ex) {
			log.error(null, ex);
			return "error";
		}
	}

	/**
	 * 根据分页显示会员
	 * 
	 * @return
	 */
	public String getUsers() {
		try {

			pageBean = userMngService.findUserPOByByParas(user_name, status, start_create_date, end_create_date, page, DEFAULT_PAGESIZE);
			//System.out.println(pageBean.getCurPageData().get(0).getNickName());
			Map<String, String> paras = new TreeMap<String, String>();
			if (!StringUtils.isEmpty(user_name)) {
				paras.put("user_name", user_name);
			}

			if (!StringUtils.isEmpty(status)) {
				paras.put("status", status);
			}

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (null != start_create_date) {
				paras.put("start_create_date", format.format(start_create_date));
			}

			if (null != end_create_date) {
				paras.put("end_create_date", format.format(end_create_date));
			}

			String baseUrl = "manager/user/getUsers";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		} catch (Exception e) {
			log.error("获取会员数据出错:", e);
			return ERROR;
		}
	}

	/**
	 * 显示就诊人信息
	 * 
	 * @return
	 */
	public String showPatients() {
		setUser_name(user_name);
		patientPOs = userMngService.findPatientsByUserId(userId);
		return SUCCESS;
	}
	
	public String showUserInfo(){
		//String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		patientCardPOs = patientsService.findPatientsCardByParms(userName, "", "");
		patientPOs = patientsService.findPatientsByUserId(userName);
		//System.out.println(patientPOs.get(0).getCardNo());
		userPO = userMngService.findUserByUsername(userName);
		return SUCCESS;
	}

	private String p_userId;
	private int p_status;
	private String p_blackTime;
	
	public void updateStatus() {
		Date d = null;
		java.sql.Date date = null;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			d = df.parse(p_blackTime);
			date = new java.sql.Date(d.getTime());
		} catch (ParseException e) {
			//e.printStackTrace();
			d = null;
			date = null;
		}
		int k = userMngService.updateStatus(p_userId, p_status, date);
		writeString(String.valueOf(k));
	}

	public String getP_userId() {
		return p_userId;
	}

	public void setP_userId(String p_userId) {
		this.p_userId = p_userId;
	}

	public int getP_status() {
		return p_status;
	}

	public void setP_status(int p_status) {
		this.p_status = p_status;
	}

	public String getP_blackTime() {
		return p_blackTime;
	}

	public void setP_blackTime(String p_blackTime) {
		this.p_blackTime = p_blackTime;
	}

}
