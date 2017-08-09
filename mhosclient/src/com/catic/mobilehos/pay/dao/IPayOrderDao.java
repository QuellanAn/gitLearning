package com.catic.mobilehos.pay.dao;

import java.util.List;

import com.catic.mobilehos.pay.entity.PayOrder;
import com.catic.mobilehos.pay.entity.TransFlow;
import com.catic.mobilehos.utils.Page;

public interface IPayOrderDao  {
	public boolean save(final PayOrder payOrder);
	
	public List<PayOrder> findAll(PayOrder po,Page page);
	
	public List<PayOrder> findByIS(String itemId,String payStatus);
	
	public PayOrder findByOrderId(String orderId);
	
	public PayOrder findByTradeNo(String tradeNo);
	
	public List<PayOrder>  findRefund(PayOrder po,Page page);
	
	public List<PayOrder>  findRefundDetails(PayOrder po);
	
	public Boolean updatePayStatus(PayOrder po);
	
	public Boolean saveRefund(PayOrder po);
	
	public PayOrder findByNoAndCode(String tradeNo,String orderCode);
	
	public Boolean saveHisTransId(PayOrder po);
	
	public Boolean alterPayStautus(Integer  payStatus,String outTradeNO) throws Exception;
	
	public List<PayOrder> findByDate(String date,Integer payStatus,String upDate) throws Exception;
	
	public List<PayOrder> findAllByPL(PayOrder po,Page page);
	
	public List<TransFlow> findAllByPlFlow(TransFlow tf,Page page);
	
	public PayOrder findByTimeExpire(String timeExpire);
	
	public PayOrder findByNearTime();
	
	public List<PayOrder> findByStatistical(PayOrder po);
	
	public PayOrder selectOfStatisticalByPay(PayOrder po);
	
	public PayOrder selectOfStatisticalByRefund(PayOrder po);
	
	public List<PayOrder> selectOfStatisticalList(PayOrder po,Page page);
	
	/**
	 * gds
	 * @param po
	 * @param page
	 * @param redirectType
	 * @return
	 */
	public List<PayOrder> findAllPayOrRecharge(PayOrder po,Page page, String redirectType);
}
