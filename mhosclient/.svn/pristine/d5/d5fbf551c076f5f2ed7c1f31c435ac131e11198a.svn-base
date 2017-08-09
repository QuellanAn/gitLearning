package com.catic.mobilehos.dao;

import java.sql.Date;
import java.util.List;

import com.catic.mobilehos.po.OrdersPayPO;

/**
 * 预约缴费dao层接口
 * @author dsh
 *
 */
public interface OrdersPayDAO {

	int countOrderPayByParas(String orderid, String patientname, String status,
			java.sql.Date  startdate, java.sql.Date  enddate);

	List<OrdersPayPO> findOrdersPayByParas(String orderid, String patientname,
			String status, java.sql.Date startdate, java.sql.Date enddate, int offset,
			int length);
	

}
