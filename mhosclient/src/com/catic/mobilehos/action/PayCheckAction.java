package com.catic.mobilehos.action;

import java.sql.Date;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.catic.mobilehos.service.PayCheckService;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.PayCheckVO;

/**
 * 手机对账
 * 
 * @author dsh
 * 
 */
public class PayCheckAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String orderid;// 
	private String patientname;
	private String payid; // 
	private String wechatid; // 
	private Date startdate;
	private Date enddate;
	private String tradetype;
	private String status;// 没有缴费成功
	private int page;
	private final int DEFAULT_PAGESIZE = 10;
	private Page<PayCheckVO> pageBean;
	private PayCheckService payCheckService;

	public String getPayCheckList() {
		try {
			if (!StringUtils.isEmpty(patientname)) {
				patientname = java.net.URLDecoder.decode(patientname, "utf-8");
			}
			pageBean = this.payCheckService.queryPayCheckByParas(orderid, patientname,payid,wechatid,tradetype,
					status, startdate, enddate, page,
					DEFAULT_PAGESIZE);
			Map<String, String> paras = new TreeMap<String, String>();
			if (!StringUtils.isEmpty(patientname)) {
				paras.put("patientname", patientname);
			}
			//paras.put("docbillsn", this.docbillsn);
			paras.put("status", this.status);
			/*
			 * paras.put("startdate", this.startdate); paras.put("enddate",
			 * this.enddate);
			 */
			String baseUrl = "busrecord/orderpay/getPayList";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public String getWechatid() {
		return wechatid;
	}

	public void setWechatid(String wechatid) {
		this.wechatid = wechatid;
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

	public String getTradetype() {
		return tradetype;
	}

	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
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

	public Page<PayCheckVO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<PayCheckVO> pageBean) {
		this.pageBean = pageBean;
	}

	public PayCheckService getPayCheckService() {
		return payCheckService;
	}

	public void setPayCheckService(PayCheckService payCheckService) {
		this.payCheckService = payCheckService;
	}

	public int getDEFAULT_PAGESIZE() {
		return DEFAULT_PAGESIZE;
	}


}
