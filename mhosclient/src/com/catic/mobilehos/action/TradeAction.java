package com.catic.mobilehos.action;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.catic.mobilehos.po.PatientPO;
import com.catic.mobilehos.po.TradePO;
import com.catic.mobilehos.service.AppRegOrderService;
import com.catic.mobilehos.service.TradeService;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.TradeVO;

/**
 * 缴费记录
 * @author gds
 *
 */
public class TradeAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 当前页
	 */
	private int page;

	/**
	 * 缺省页大小
	 */
	private final int DEFAULT_PAGESIZE = 10;
	
	/**
	 * 查询的开始日期
	 */
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
	 * 查询的开始创建时间
	 */
	private String startCreateTime;

	/**
	 * 查询的开始创建时间
	 */
	private Timestamp startSQLCreateTime;

	/**
	 * 查询的结束创建时间
	 */
	private String endCreateTime;

	/**
	 * 查询的结束创建时间
	 */
	private Timestamp endSQLCreateTime;
	
	private Page<TradeVO> pageBean;
	
	private TradeService tradeService;
	
	private List<TradePO> tradePOs;
	
	private TradePO tradePO;
	
	private PatientPO patientPO;
	
	private AppRegOrderService appRegOrderService;
	
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

	public Page<TradeVO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<TradeVO> pageBean) {
		this.pageBean = pageBean;
	}

	public TradeService getTradeService() {
		return tradeService;
	}

	public void setTradeService(TradeService tradeService) {
		this.tradeService = tradeService;
	}

	public List<TradePO> getTradePOs() {
		return tradePOs;
	}

	public void setTradePOs(List<TradePO> tradePOs) {
		this.tradePOs = tradePOs;
	}

	public TradePO getTradePO() {
		return tradePO;
	}

	public void setTradePO(TradePO tradePO) {
		this.tradePO = tradePO;
	}

	/**
	 * 验证参数
	 */
	public void validateGetAppRegOrdersList() {

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
		if (StringUtils.isNotBlank(this.startCreateTime)) {
			this.startSQLCreateTime = this.toTimestamp(this.startCreateTime);
			if (this.startSQLCreateTime == null) {
				this.addFieldError("", "时间格式不正确！");
			}
		}
		if (StringUtils.isNotBlank(this.endCreateTime)) {
			this.endSQLCreateTime = this.toTimestamp(this.endCreateTime);
			if (this.endSQLCreateTime == null) {
				this.addFieldError("", "时间格式不正确！");
			}
		}
	}

	public String getTradeList() {
		try {
			String cardNo = request.getParameter("cardNo");
			String tradeNo = request.getParameter("tradeNo");
			String transactionId = request.getParameter("transactionId");
			String status = request.getParameter("status");
			String startSQLDate = request.getParameter("startdate");
			String endSQLDate = request.getParameter("enddate");
			String patientname = request.getParameter("patientname");
			if (!StringUtils.isEmpty(patientname)) {
				patientname=new String(patientname.getBytes("ISO-8859-1"),"utf-8");
				patientname = java.net.URLDecoder.decode(patientname, "utf-8");
			}
			if(StringUtils.isNotBlank(startSQLDate) && StringUtils.isNotBlank(endSQLDate)){
				startSQLDate += " 00:00:00";
				endSQLDate += " 23:59:59";
			}
			//System.out.println(startSQLDate+"  "+endSQLDate);
			validateGetAppRegOrdersList();
			pageBean = tradeService.queryTradesByParas(cardNo, tradeNo, transactionId, status, startSQLDate, endSQLDate, page,
					DEFAULT_PAGESIZE, patientname);
			//System.out.println("***********"+pageBean.getCurPageData().get(0).getPatientName());
			Map<String, String> paras = new TreeMap<String, String>();
			paras.put("startdate", startSQLDate);
			paras.put("enddate", endSQLDate);
			paras.put("cardNo", cardNo);
			paras.put("tradeNo", tradeNo);
			paras.put("transactionId", transactionId);
			paras.put("status", status);
			paras.put("patientname", patientname);
			String baseUrl = "busrecord/getTradeList";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		} catch (Exception e) {
			log.error(null, e);
			return ERROR;
		}

	}

	public String showTradeInfo(){
		String tradeNo = request.getParameter("tradeNo");
		tradePOs = tradeService.showTradeInfo(tradeNo);
		String[] array = tradePOs.get(0).getAttach().split(",");
		List<PatientPO> list = appRegOrderService.findPatientByPatentId(array[0]);
		if(list.size() > 0){
			patientPO = list.get(0);
		}else{
			patientPO = null;
		}
		//patientPO = patientService.getPatientPOByCardNo(tradePOs.get(0).getCardNo());
		return SUCCESS;
	}
}
