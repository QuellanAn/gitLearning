package com.catic.mobilehos.action;

import java.sql.Date;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.catic.mobilehos.service.OrderPayService;
import com.catic.mobilehos.service.vo.OrdersPayVO;
import com.catic.mobilehos.service.vo.Page;

/**
 * 后台预约挂号记录管理
 * 
 * @author dsh
 * 
 */
public class OrdersPayAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String orderid;
	private String patientname;
	private Date startdate;// 没有缴费成功
	private Date enddate;
	private String status;
	private int page;
	private final int DEFAULT_PAGESIZE = 10;
	private Page<OrdersPayVO> pageBean;
	private OrderPayService orderPayService;

	public String getOrdersPayList() {

		try {
			if (!StringUtils.isEmpty(patientname)) {
				patientname = java.net.URLDecoder.decode(patientname, "utf-8");
			}
			pageBean = this.orderPayService.queryOrdersPayByParas(orderid,
					patientname, status, startdate, enddate, page,
					DEFAULT_PAGESIZE);
			Map<String, String> paras = new TreeMap<String, String>();
			if (!StringUtils.isEmpty(patientname)) {
				paras.put("patientname", patientname);
			}
			paras.put("orderid", this.orderid);
			paras.put("status", this.status);
			/*paras.put("startdate", this.startdate);
			paras.put("enddate", this.enddate);*/
			String baseUrl = "busrecord/orderpay/getOrdersPayList";
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

	public Page<OrdersPayVO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<OrdersPayVO> pageBean) {
		this.pageBean = pageBean;
	}

	public OrderPayService getOrderPayService() {
		return orderPayService;
	}

	public void setOrderPayService(OrderPayService orderPayService) {
		this.orderPayService = orderPayService;
	}

	public int getDEFAULT_PAGESIZE() {
		return DEFAULT_PAGESIZE;
	}

}
