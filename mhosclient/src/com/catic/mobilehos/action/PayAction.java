package com.catic.mobilehos.action;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.catic.mobilehos.po.DocBillDetailPO;
import com.catic.mobilehos.po.DocBillsPO;
import com.catic.mobilehos.po.PatientPO;
import com.catic.mobilehos.service.AppRegOrderService;
import com.catic.mobilehos.service.CardPayService;
import com.catic.mobilehos.service.PayService;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.PayVO;

/**
 * 后台预约挂号记录管理
 * 
 * @author dsh
 * 
 */
public class PayAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String docbillsn;// 缴费单编号
	private String patientname;
	private String curdocsn; // 就诊编号作为流水号
	private Date startdate;
	private Date enddate;
	private String status;// 没有缴费成功
	private int page;
	private final int DEFAULT_PAGESIZE = 10;
	private Page<PayVO> pageBean;
	private PayService payService;
	private List<DocBillDetailPO> docBillDetailPO;
	private String docBillSn;
	
	private AppRegOrderService appRegOrderService;
	private List<PatientPO> patientPOs;
	private PatientPO patientPO;
	private String patientId;
	private DocBillsPO docBillsPO;
	
	public String getPayList() {

		try {
			if (!StringUtils.isEmpty(patientname)) {
				patientname = java.net.URLDecoder.decode(patientname, "utf-8");
			}
			pageBean = this.payService.queryPayByParas(docbillsn, patientname,
					status, curdocsn, startdate, enddate, page,
					DEFAULT_PAGESIZE);
			//System.out.println(pageBean.getCurPageData().get(0).getAmount());
			//System.out.println("page "+pageBean.getCurPageData().get(0).getAmount());
			Map<String, String> paras = new TreeMap<String, String>();
			if (!StringUtils.isEmpty(patientname)) {
				paras.put("patientname", patientname);
			}
			paras.put("docbillsn", this.docbillsn);
			paras.put("status", this.status);
			
			//paras.put("startdate", this.startdate); 
			//paras.put("enddate", this.enddate);
			 
			String baseUrl = "busrecord/orderpay/getPayList";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	/**
	 * 显示就诊人信息
	 */
	public String showPatient() {
		patientPOs = appRegOrderService.findPatientByPatentId(patientId);
		if(patientPOs != null && patientPOs.size() > 0){
			patientPO = patientPOs.get(0);
		}else{
			patientPO = null;
		}
		String docBillSn = request.getParameter("docBillSn");
		//System.out.println("pay "+patientId+" "+docBillSn);
		docBillsPO = payService.findDocBillsPOByDocBillSn(docBillSn);
		//System.out.println("doc "+docBillsPO.getDepartment()+" doct "+docBillsPO.getDocBillSN());
		return SUCCESS;
	}

	public String showPayInfo(){
		docBillDetailPO   = payService.findPayInfoByDocBillSn(docBillSn);
		return SUCCESS;
	}

	public String getDocbillsn() {
		return docbillsn;
	}

	public void setDocbillsn(String docbillsn) {
		this.docbillsn = docbillsn;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getCurdocsn() {
		return curdocsn;
	}

	public void setCurdocsn(String curdocsn) {
		this.curdocsn = curdocsn;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Page<PayVO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<PayVO> pageBean) {
		this.pageBean = pageBean;
	}

	public PayService getPayService() {
		return payService;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}

	public int getDEFAULT_PAGESIZE() {
		return DEFAULT_PAGESIZE;
	}


	public String getDocBillSn() {
		return docBillSn;
	}

	public void setDocBillSn(String docBillSn) {
		this.docBillSn = docBillSn;
	}

	public List<DocBillDetailPO> getDocBillDetailPO() {
		return docBillDetailPO;
	}

	public void setDocBillDetailPO(List<DocBillDetailPO> docBillDetailPO) {
		this.docBillDetailPO = docBillDetailPO;
	}
	public AppRegOrderService getAppRegOrderService() {
		return appRegOrderService;
	}
	public void setAppRegOrderService(AppRegOrderService appRegOrderService) {
		this.appRegOrderService = appRegOrderService;
	}
	public List<PatientPO> getPatientPOs() {
		return patientPOs;
	}
	public void setPatientPOs(List<PatientPO> patientPOs) {
		this.patientPOs = patientPOs;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public PatientPO getPatientPO() {
		return patientPO;
	}
	public void setPatientPO(PatientPO patientPO) {
		this.patientPO = patientPO;
	}
	public DocBillsPO getDocBillsPO() {
		return docBillsPO;
	}
	public void setDocBillsPO(DocBillsPO docBillsPO) {
		this.docBillsPO = docBillsPO;
	}


}
