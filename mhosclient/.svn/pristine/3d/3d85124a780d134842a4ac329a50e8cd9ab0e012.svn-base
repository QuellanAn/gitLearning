package com.catic.mobilehos.pay.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.IOrderDao;
import com.catic.mobilehos.pay.entity.PayOrder;

@Repository("orderDao")
public class OrderDaoImpl extends BaseDao implements IOrderDao {

	@Override
	public boolean find() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from pay_order";
		List<PayOrder> list = jdbc.query(sql, new BeanPropertyRowMapper<PayOrder>(PayOrder.class));
		if (list.size() >0) {
			return true;
		}
		return false;
	}



	

}
