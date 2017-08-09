package com.catic.mobilehos.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.OrdersPayDAO;
import com.catic.mobilehos.po.OrdersPayPO;
import com.catic.mobilehos.service.vo.OrdersPayVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.VOPOConverter;

public class OrderPayServiceImpl implements OrderPayService {

	private Log log = LogFactory.getLog(this.getClass());
	private OrdersPayDAO ordersPayDAO;

	public OrdersPayDAO getOrdersPayDAO() {
		return ordersPayDAO;
	}

	public void setOrdersPayDAO(OrdersPayDAO ordersPayDAO) {
		this.ordersPayDAO = ordersPayDAO;
	}

	@Override
	public Page<OrdersPayVO> queryOrdersPayByParas(String orderid,
			String patientname, String status, java.sql.Date startdate,
			java.sql.Date enddate, int page, int pageSize) {
		try {
			int totalRecord = this.ordersPayDAO.countOrderPayByParas(orderid,
					patientname, status, startdate, enddate);
			Page<OrdersPayVO> p = new Page<OrdersPayVO>(totalRecord, pageSize,
					page);
			List<OrdersPayPO> lst = this.ordersPayDAO.findOrdersPayByParas(
					orderid, patientname, status, startdate, enddate,
					p.getOffset(), pageSize);
			VOPOConverter<OrdersPayVO, OrdersPayPO> cvt = new VOPOConverter<OrdersPayVO, OrdersPayPO>(
					OrdersPayVO.class, OrdersPayPO.class);
			p.setCurPageData(cvt.fromPOList(lst));
			return p;
		} catch (Exception e) {
			System.out.println("错误");
			log.error("queryOrderPayByParas出现错误", e);
			return null;

		}
	}

}
