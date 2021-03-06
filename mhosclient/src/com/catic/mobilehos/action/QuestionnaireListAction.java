package com.catic.mobilehos.action;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

import com.catic.mobilehos.po.QuestionnairePO;
import com.catic.mobilehos.service.QuestionnaireService;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.QuestionnaireVO;

/**
 * 调查问卷
 * 
 * @author dengshenghui
 * 
 */
@SuppressWarnings("serial")
public class QuestionnaireListAction extends BaseAction {
	private int id;
	private String questions;

	private String title;
	private String sub_title;
	private int type;
	private String subject;
	private QuestionnairePO questionnairePO;
	private QuestionnaireService questionnaireService;

	private final int DEFAULT_PAGESIZE = 10;

	private int page;

	private QuestionnaireVO questionnaireVO;

	private Page<QuestionnaireVO> pageBean;


	private int status = -1;
	private String questionnaire;
	private String userName;

	/*private JSONObject getJSONQuestionnaire() {
		JSONObject json_ques = StringUtils.isNotBlank(questions) ? JSONObject.fromObject(questions) : null;
		return json_ques;
	}*/

	/**
	 * 调查问卷管理页面，根据分布获取系统用户数据
	 * 
	 * @return
	 */

	public String getQuestionnaiList() {
		try {
			String title = request.getParameter("title");
			String startdate = request.getParameter("startdate");
			String enddate = request.getParameter("enddate");
			String upType = request.getParameter("upType");
			/*if (!StringUtils.isEmpty(title)) {
				title=new String(title.getBytes("ISO-8859-1"),"utf-8");
				title = java.net.URLDecoder.decode(title, "utf-8");
			}*/
			if(StringUtils.isNotBlank(startdate) && StringUtils.isNotBlank(enddate)){
				startdate += " 00:00:00";
				enddate += " 23:59:59";
			}
			//System.out.println(title);
			pageBean = questionnaireService.getQuestionnairePObyParas(id, status, page, DEFAULT_PAGESIZE, title, startdate, enddate);
			//System.out.println(pageBean.getCurPageData().get(0).getUserName());
			Map<String, String> paras = new TreeMap<String, String>();
			paras.put("title", title);
			paras.put("startdate", startdate);
			paras.put("enddate", enddate);
			paras.put("upType", upType);
			String baseUrl = "questionnaire/getQuestionnaiList";
			pageBean.setQueryUrl(baseUrl, paras);
			if ("0".equals(upType)) {
				return "li1";
			} else {
				return "li";
			}
		} catch (Exception e) {
			log.error("获取系统用户数据出错:", e);
			return ERROR;
		}
	}

	/**
	 * 增加调查问卷
	 * 
	 * @return
	 */

	public String saveQuestionnaire() {
		try {
			String adminUserName = (String) request.getSession().getAttribute("userName");
			JSONObject json_qn = JSONObject.fromObject(questionnaire);
			questionnaireService.saveQuestionnaire(adminUserName,json_qn);
			return SUCCESS;
		} catch (Exception e) {
			log.error("获取系统用户数据出错:", e);
			return ERROR;
		}

	}
	/**
	 * 修改调查问卷
	 */
	public String updateQuestionnaireAtion(){
		try {
			String adminUserName = (String) request.getSession().getAttribute("userName");
			JSONObject json_qn = JSONObject.fromObject(questionnaire);
			questionnaireService.updateQuestionnaire(adminUserName,json_qn);
			return SUCCESS;
		} catch (Exception e) {
			log.error("获取系统用户数据出错:", e);
			return ERROR;
		}
	}
	/**
	 * 删除调查问卷及问题
	 * 
	 * @return
	 */
	public String deleteQuestionnaire(){
		int id = Integer.parseInt(request.getParameter("id"));
		questionnaireService.deleteQuestionnaire(id);
		
		return SUCCESS;
	}
	
	/**
	 * 进入到修改调查问卷列表
	 * 
	 */
	/*public String updateQuestionnaire(){
		int id =Integer.parseInt(request.getParameter("id"));
		System.out.println("进入到修改调查问卷列表id"+id);
		questionnairePO = questionnaireService.getQuestionnaire(id);
		
		VOPOConverter<QuestionnaireVO, QuestionnairePO> converter = new VOPOConverter<QuestionnaireVO, QuestionnairePO>(QuestionnaireVO.class,
				QuestionnairePO.class);
		questionnaireVO = converter.poToVO(questionnairePO);
		return SUCCESS;
	}*/
	
	public String updateQuestionnaire(){
		int id =Integer.parseInt(request.getParameter("id"));
		String upType = request.getParameter("upType");
		JSONObject json = questionnaireService.getQuestionnaire(id);
		//ServletActionContext.getRequest().setAttribute("data", json.toString());
		request.setAttribute("data", json);
		if("0".equals(upType)){
			return "up1";
		}else{
			return "up";
		}
	}
	/**
	 * 获取调查问卷中的问题列表
	 * @return
	 */
	public String getQuestionnaireDetail(){
		
		int qnId =Integer.parseInt(request.getParameter("questionnaire_id"));
		
		JSONObject json = questionnaireService.getQuestionnaireDetail(qnId);

		//this.result = Result.success(json);
		return SUCCESS;

		
	}
	
	/*
	 * try{
	 * 
	 * int userId = 3;//(Integer) request.getSession().getAttribute("userName");
	 * String title="xfv"; 
	 * String sub_title="aqaqaqaqa"; 
	 * int status = 1;
	 * ServiceResult sr = questionnaireService.createQuestionnairePO(title,
	  sub_title,status,userId); 
	  if (sr.isSuccess())
	  	{ return SUCCESS; }
	  else{
	  this.addActionError(sr.getFailedInfo().getString(ServiceResult.MSG));
	  return ERROR; 
	  } 
	 * }catch(Exception e)
	 * 		{ log.error(null, e);
	 * 	this.addActionError("系统异常，调查问卷失败！"); 
	 * return ERROR; }
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public QuestionnairePO getQuestionnairePO() {
		return questionnairePO;
	}

	public void setQuestionnairePO(QuestionnairePO questionnairePO) {
		this.questionnairePO = questionnairePO;
	}

	public QuestionnaireService getQuestionnaireService() {
		return questionnaireService;
	}

	public void setQuestionnaireService(QuestionnaireService questionnaireService) {
		this.questionnaireService = questionnaireService;
	}

	public QuestionnaireVO getQuestionnaireVO() {
		return questionnaireVO;
	}

	public void setQuestionnaireVO(QuestionnaireVO questionnaireVO) {
		this.questionnaireVO = questionnaireVO;
	}

	public Page<QuestionnaireVO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<QuestionnaireVO> pageBean) {
		this.pageBean = pageBean;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	

	public int getDEFAULT_PAGESIZE() {
		return DEFAULT_PAGESIZE;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(String questionnaire) {
		this.questionnaire = questionnaire;
	}

	

}
