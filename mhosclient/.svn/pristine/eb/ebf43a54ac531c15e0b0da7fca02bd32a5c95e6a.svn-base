package com.catic.mobilehos.action;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.catic.mobilehos.po.CardPayPO;
import com.catic.mobilehos.po.PatientPO;
import com.catic.mobilehos.service.AppRegOrderService;
import com.catic.mobilehos.service.CardPayService;
import com.catic.mobilehos.service.vo.CardPayDetailVO;
import com.catic.mobilehos.service.vo.CardPayVO;
import com.catic.mobilehos.service.vo.Page;

/**
 * 缴费记录
 * @author gds
 *
 */
public class CardPayAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 当前页
	 */
	private int page;

	/**
	 * 缺省页大小
	 */
	private final int DEFAULT_PAGESIZE = 10;

	private Page<CardPayVO> pageBean;
	private Page<CardPayDetailVO> pageBeanDetail;
	private CardPayPO cardPayPO;
	private List<CardPayPO> cardPayPOs;
	private PatientPO patientPO;
	private CardPayService cardPayService;
	private AppRegOrderService appRegOrderService;
	
	public String getCardPayList(){
		try {
			String receiptNo = request.getParameter("receiptNo");
			String cpId = request.getParameter("cpId");
			String payWay = request.getParameter("payWay");
			String cardNumber = request.getParameter("cardNumber");
			String startCreateTime = request.getParameter("startCreateTime");
			String endCreateTime = request.getParameter("endCreateTime");
			pageBean = cardPayService.queryCardPayByParas(cpId, receiptNo, payWay, cardNumber, startCreateTime, endCreateTime, page, DEFAULT_PAGESIZE);
			//System.out.println(pageBean.getCurPageData().get(0).getPayStatus());
			Map<String, String> paras = new TreeMap<String, String>();
			paras.put("receiptNo", receiptNo);
			paras.put("payWay", payWay);
			paras.put("cardNumber", cardNumber);
			paras.put("startCreateTime", startCreateTime);
			paras.put("endCreateTime", endCreateTime);
			String baseUrl;
			baseUrl = "busrecord/getCardPayList";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	public String showInfo() {
		String cpId = request.getParameter("cpId");
		cardPayPO = cardPayService.findCardPayPOByCPId(cpId);
		//System.out.println(cardPayPO.getPayStatus()+ "  cpid "+cpId);
		List<PatientPO> list = appRegOrderService.findPatientByPatentId(cardPayPO.getPatientId());
		if(list.size() > 0){
			patientPO = list.get(0);
		}else{
			patientPO=null;
		}
		return SUCCESS;
	}
	
	public String getCardPayDetailList(){
		try {
			String cpid = request.getParameter("cpId");
			//System.out.println("detail "+cpid);
			pageBeanDetail = cardPayService.queryCardPayDetailByParas(cpid, page, DEFAULT_PAGESIZE);
			//System.out.println(pageBeanDetail.getCurPageData().size());
			//System.out.println(pageBeanDetail.getCurPageData().get(0).getAmount());
			Map<String, String> paras = new TreeMap<String, String>();
			paras.put("cpId", cpid);
			String baseUrl;		 
			baseUrl = "busrecord/getCardPayDetailList";
			pageBeanDetail.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	
	
	
	public Page<CardPayDetailVO> getPageBeanDetail() {
		return pageBeanDetail;
	}

	public void setPageBeanDetail(Page<CardPayDetailVO> pageBeanDetail) {
		this.pageBeanDetail = pageBeanDetail;
	}

	public AppRegOrderService getAppRegOrderService() {
		return appRegOrderService;
	}

	public void setAppRegOrderService(AppRegOrderService appRegOrderService) {
		this.appRegOrderService = appRegOrderService;
	}

	public PatientPO getPatientPO() {
		return patientPO;
	}

	public void setPatientPO(PatientPO patientPO) {
		this.patientPO = patientPO;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Page<CardPayVO> getPageBean() {
		return pageBean;
	}
	public void setPageBean(Page<CardPayVO> pageBean) {
		this.pageBean = pageBean;
	}
	public CardPayPO getCardPayPO() {
		return cardPayPO;
	}
	public void setCardPayPO(CardPayPO cardPayPO) {
		this.cardPayPO = cardPayPO;
	}
	public List<CardPayPO> getCardPayPOs() {
		return cardPayPOs;
	}
	public void setCardPayPOs(List<CardPayPO> cardPayPOs) {
		this.cardPayPOs = cardPayPOs;
	}
	public CardPayService getCardPayService() {
		return cardPayService;
	}
	public void setCardPayService(CardPayService cardPayService) {
		this.cardPayService = cardPayService;
	}
}
