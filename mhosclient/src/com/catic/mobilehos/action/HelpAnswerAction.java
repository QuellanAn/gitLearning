package com.catic.mobilehos.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.catic.mobilehos.po.HelpPO;
import com.catic.mobilehos.service.HelpAnswerService;
import com.catic.mobilehos.service.vo.HelpVO;
import com.catic.mobilehos.service.vo.Page;

@SuppressWarnings("serial")
public class HelpAnswerAction extends BaseAction {
	private final int DEFAULT_PAGESIZE = 10;
	
	private int helpId;
	private String question;
	private String answer;
	private String createPeople;
	private HelpAnswerService haService;
	private Page<HelpVO> pageBean;
	private java.sql.Date startdate;
	private java.sql.Date enddate;
	private int page;
	
	public HelpAnswerService getHaService() {
		return haService;
	}
	public void setHaService(HelpAnswerService haService) {
		this.haService = haService;
	}
	public Page<HelpVO> getPageBean() {
		return pageBean;
	}
	public void setPageBean(Page<HelpVO> pageBean) {
		this.pageBean = pageBean;
	}
	public java.sql.Date getStartdate() {
		return startdate;
	}
	public void setStartdate(java.sql.Date startdate) {
		this.startdate = startdate;
	}
	public java.sql.Date getEnddate() {
		return enddate;
	}
	public void setEnddate(java.sql.Date enddate) {
		this.enddate = enddate;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getCreatePeople() {
		return createPeople;
	}
	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}
	public int getHelpId() {
		return helpId;
	}
	public void setHelpId(int helpId) {
		this.helpId = helpId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getHelpAnswerList(){
		try{
			request.setCharacterEncoding("UTF-8");
			pageBean = this.haService.getAllHelpList(question,createPeople, startdate,
					enddate, page, DEFAULT_PAGESIZE);
			return SUCCESS;
		}catch(Exception ex){
			log.error(null, ex);
			return ERROR;
		}
		
	}
	
	public String toUpdateHelp(){
		HelpPO helpPo = haService.getHelpAnswer(helpId);
		this.question=helpPo.getQuestion();
		this.answer=helpPo.getAnswer();
		return SUCCESS;
	}
	
	public String AddUpdateHelp(){
		try {
			String cPeople = (String) request.getSession().getAttribute("userName");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HelpPO helpPo=new HelpPO();
			helpPo.setQuestion(question);
			helpPo.setAnswer(answer);
			helpPo.setCreatePeople(cPeople);
			helpPo.setCreateTime(this.toTimestamp(format.format(new Date())));
			if(helpId==0){
				this.haService.addHelp(helpPo);
			}else{
				helpPo.setHelpId(helpId);
				this.haService.updateHelp(helpPo);
			}
			pageBean = this.haService.getAllHelpList(question,createPeople, startdate,
					enddate, page, DEFAULT_PAGESIZE);
			return SUCCESS;
		} catch (Exception e) {
			log.error(null, e);
			return ERROR;
		}
	}
	
	public String deleteHelp(){
		try {
			this.haService.deleteHelp(helpId);
			pageBean = this.haService.getAllHelpList(question,createPeople, startdate,
					enddate, page, DEFAULT_PAGESIZE);
			return SUCCESS;
		} catch (Exception e) {
			log.error(null, e);
			return ERROR;
		}
	}
}
