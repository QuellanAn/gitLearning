package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.IHisCheckDao;
import com.catic.mobilehos.pay.entity.HisCheck;

@Repository("hisCheckDao")
public class HisCheckDaoImpl extends BaseDao implements IHisCheckDao {

	@Override
	public int save(HisCheck ci) throws Exception {
		String sql="INSERT INTO pay_hischeck(orderNum,othersNum,hisNum,body,orderType,orderMoney,orderStatus,orderTime,refundType,createTime) values(?,?,?,?,?,?,?,?,?,NOW()) ";
		List<Object> param=new ArrayList<Object>();
		param.add(ci.getOrderNum());
		param.add(ci.getOthersNum());
		param.add(ci.getHisNum());
		param.add(ci.getBody());
		param.add(ci.getOrderType());
		param.add(ci.getOrderMoney());
		param.add(ci.getOrderStatus());
		param.add(ci.getOrderTime());
		param.add(ci.getRefundType());
//		param.add(ci.getCreateTime());
		int count=jdbc.update(sql, param.toArray());
		return count;
	}

}
