package com.catic.mobilehos.pay.biz;

import java.util.List;

import com.catic.mobilehos.pay.entity.PayOrder;
import com.catic.mobilehos.pay.entity.Refund;
import com.catic.mobilehos.utils.Page;

public interface IRefundBiz {

	public List<PayOrder> findAll(PayOrder po,Page page) throws Exception;
	
	public List<PayOrder>  findRefundDetails(PayOrder po) throws Exception;
	
	public List<Refund> findNotRefund(int from,int to) throws Exception ;
	
	public Boolean save(Refund refund) throws Exception ;
	
	public Boolean update(Refund refund) throws Exception ;
	
	public List<Refund> findAll(Refund refund,Page page) throws Exception;  
	
	public List<Refund> findDetails(Refund refund) throws Exception ;
	
	public List<Refund> findByTradeNo(String tradeNo) throws Exception ;
	
	public List<Refund> findByDate(String date)throws Exception;
	
	public Refund findByrefundNo(String refundNo)throws Exception;
	
	/**
	 * 根据退款记录list获取转化后准备导出的报表数据
	 * @param list 退款记录list
	 * @return 准备导出的报表数据
	 * @author 朱伟权
	 */
	public List<List<Object>> getExportRefundDataList(List<Refund> refundList);
}
