package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.CardPayDetailPO;
import com.catic.mobilehos.po.CardPayPO;
/**
 * 缴费接口层
 * @author gds
 *
 */
public interface CardPayDao {
	/**
	 * 缴费信息
	 * @param cpId
	 * @return
	 */
	CardPayPO findCardPayPOByCPId(String cpId);
	/**
	 * 医嘱信息
	 * @param cpId
	 * @return
	 */
	CardPayDetailPO findCardPayDetailPOByCPId(String cpId);
	
	int countCardPayByParas(String cpId, String receiptNo, String payWay, String cardNumber, String startCreateTime, String endCreateTime);
	
	List<CardPayPO> findCardPayByParas(String cpId, String receiptNo, String payWay, String cardNumber,
			String startCreateTime, String endCreateTime, int offset, int length);
	
	int countCardPayDetailByParas(String cpId);
	
	List<CardPayDetailPO> findCardPayDetailByParas(String cpId, int offset, int length);
}
