package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.ITransFlowDao;
import com.catic.mobilehos.pay.entity.TransFlow;

@Repository("transFlowDao")
public class TransFlowDaoImpl extends BaseDao implements ITransFlowDao{

	/* 
	 * 根据商户号、交易类型查询
	 */
	@Override
	public List<TransFlow> find(String outTradeNo, Integer transType)
			throws Exception {
		String sql="SELECT * FROM pay_transactionflow WHERE 1=1 ";
		StringBuilder sb=new StringBuilder(sql);
		List<Object> params=new ArrayList<Object>();
		if(StringUtils.isNotBlank(outTradeNo)){
			sb.append(" AND outTradeNo=?");
			params.add(outTradeNo);
		}
		if(transType!=null){
			sb.append(" AND transType=?");
			params.add(transType);
		}
		List<TransFlow> list=jdbc.query(sb.toString(), params.toArray(),new BeanPropertyRowMapper(TransFlow.class));
		return list;
	}

}
