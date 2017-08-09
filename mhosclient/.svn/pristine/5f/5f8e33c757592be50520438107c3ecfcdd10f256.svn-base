package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.IPayDictionaryDao;
import com.catic.mobilehos.pay.entity.PayDictionary;

@Repository("payDictionaryDao")
public class PayDictionaryDaoImpl extends BaseDao implements IPayDictionaryDao{

	/**
	 * @author lxg
	 * @param dictionaryCode:代码标识字段
	 * 查询字典数据
	 */
	public List<PayDictionary> findAll(String dictionaryCode) throws Exception {
		String  sql = "SELECT * FROM pay_dictionary WHERE status=0 and dictionary_code=?";
		List<Object> param=new ArrayList<Object>();
		if(StringUtils.isNotBlank(dictionaryCode)){
			param.add(dictionaryCode);
		}
		List<PayDictionary> list = jdbc.query(sql,param.toArray(), new BeanPropertyRowMapper(PayDictionary.class));
		if (list.size() >0) {
			return list;
		}
		return null;
	}

}
