package com.catic.mobilehos.pay.biz;

import java.util.List;

import com.catic.mobilehos.pay.entity.PayOrder;
import com.catic.mobilehos.pay.entity.TransFlow;
import com.catic.mobilehos.utils.Page;

public interface IPayOrderBiz {

	public boolean savePayOrder(PayOrder payOrder);
	
	public List<PayOrder> findAll(PayOrder po,Page page);
	
	public List<PayOrder> findByIS(String itemId,String payStatus);
	
	public PayOrder findByOrderId(String orderId);
	
	public PayOrder findByTradeNo(String tradeNo);
	
	public PayOrder findByNoAndCode(String tradeNo,String orderCode) ;
	
	public Boolean updatePayStatus(PayOrder po);
	
	public Boolean saveRefund(PayOrder po);
	
	public Boolean saveHisTransId(PayOrder po);
	
	public Boolean alterPayStautus(Integer payStatus,String outTradeNO) throws Exception;
	
	public List<PayOrder> findByDate(String date,Integer payStatus,String upDate) throws Exception;
	
	public List<PayOrder> findAllByPL(PayOrder po,Page page);
	
	public List<TransFlow> findAllByPlFlow(TransFlow tf, Page page);
	
	public PayOrder findByTimeExpire(String timeExpire);
	
	public PayOrder findByNearTime();
	
	public List<PayOrder> findByStatistical(PayOrder po);
	
	public PayOrder selectOfStatisticalByPay(PayOrder po);
	
	public PayOrder selectOfStatisticalByRefund(PayOrder po);
	
	public List<PayOrder> selectOfStatisticalList(PayOrder po,Page page);
	
	/**
	 * 根据交易流水list获取转化后准备导出的报表数据
	 * @param list 交易流水list
	 * @return 准备导出的报表数据
	 * @author 朱伟权
	 */
	public List<List<Object>> getExportTransflowDataList(List<PayOrder> payOrderList);
	
	/**
	 * 根据交易订单list获取转化后准备导出的报表数据
	 * @param list 交易订单list
	 * @return 准备导出的报表数据
	 * @author 朱伟权
	 */
	public List<List<Object>> getExportPayOrderDataList(List<PayOrder> payOrderList);
	
	/**
	 * 查询所有的缴费充值记录
	 * gds
	 * @param po
	 * @param page
	 * @return
	 */
	public List<PayOrder> findAllPayOrRecharge(PayOrder po,Page page, String redirectType);
}
