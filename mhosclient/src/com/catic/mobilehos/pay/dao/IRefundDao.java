package com.catic.mobilehos.pay.dao;

import java.util.List;

import com.catic.mobilehos.pay.entity.Refund;
import com.catic.mobilehos.utils.Page;

public interface IRefundDao {

	public List<Refund> findAll(Refund rf,Page page) throws Exception ;
	
	public List<Refund> findNotRefund(int from,int to)throws Exception ;
	
	public Boolean save(Refund refund) throws Exception ;
	
	public Boolean update(Refund refund) throws Exception ;
	
	public List<Refund> findDetails(Refund refund)throws Exception;
	
	public List<Refund> findByTradeNo(String tradeNo) throws Exception ;
	
	public List<Refund> findByDate(String date)throws Exception;
	
	public Refund findByrefundNo(String refundNo)throws Exception;
}
