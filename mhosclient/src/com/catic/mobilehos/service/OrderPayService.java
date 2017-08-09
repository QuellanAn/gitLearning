package com.catic.mobilehos.service;

import com.catic.mobilehos.service.vo.OrdersPayVO;
import com.catic.mobilehos.service.vo.Page;

/**
 * 预约挂号缴费服务
 * @author dsh
 *
 */
public interface OrderPayService {

	Page<OrdersPayVO> queryOrdersPayByParas(String orderid, String patientname,
			String status, java.sql.Date startdate, java.sql.Date enddate, int page,
			int pageSize);

}
