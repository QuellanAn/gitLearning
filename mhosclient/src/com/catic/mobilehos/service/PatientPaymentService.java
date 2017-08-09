package com.catic.mobilehos.service;

public interface PatientPaymentService {
	
	/**
	 * 获取缴费单列表
	 * @return
	 */
	ServiceResult getPaymentBills(String userId);
	
	
	/**
	 * 获取缴费单明细信息
	 * @return
	 */
	ServiceResult getPaymentBillDetail(String userId, String curdocsn, String billid);

}
