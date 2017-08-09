package com.catic.mobilehos.service;

import com.catic.mobilehos.po.CardPayDetailPO;
import com.catic.mobilehos.po.CardPayPO;
import com.catic.mobilehos.service.vo.CardPayDetailVO;
import com.catic.mobilehos.service.vo.CardPayVO;
import com.catic.mobilehos.service.vo.Page;
/**
 * 此就诊卡充值服务层,不通过微信
 * @author gds
 *
 */
public interface CardPayService {
	CardPayPO findCardPayPOByCPId(String cpId);
	
	CardPayDetailPO findCardPayDetailPOByCPId(String cpId);
	
	Page<CardPayVO> queryCardPayByParas(String cpId, String receiptNo, String payWay, String cardNumber,
			String startCreateTime, String endCreateTime, int page, int pageSize);
	
	Page<CardPayDetailVO> queryCardPayDetailByParas(String cpId, int page, int pageSize);
}
