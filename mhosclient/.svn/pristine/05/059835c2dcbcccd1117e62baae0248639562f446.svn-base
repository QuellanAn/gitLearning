package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.PayCheckPO;


/**
 * 手机对账dao层接口
 * @author dsh
 *
 */
public interface PayCheckDAO {

	int countPayCheckByParas(String orderid, String patientname, String payid,
			String wechatid, String tradetype, String status, java.sql.Date  startdate,
			java.sql.Date enddate);

	List<PayCheckPO> findPayCheckByParas(String orderid, String patientname,
			String payid, String wechatid, String tradetype, String status,
			java.sql.Date  startdate, java.sql.Date  enddate, int offset, int length);

	
	

}
