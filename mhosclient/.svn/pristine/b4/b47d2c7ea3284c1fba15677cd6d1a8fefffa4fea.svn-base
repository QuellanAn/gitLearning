package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.IYQAccountDao;
import com.catic.mobilehos.pay.entity.YQInfo;

@Repository("yQAccountDao")
public class YQAccountDaoImpl extends BaseDao implements IYQAccountDao{

	/**
	 * 查询院区
	 * @param id
	 * @return
	 */
	@Override
	public List<YQInfo> findYQ(String id)throws Exception {
		String sql="SELECT yq.name FROM pay_yq_account ya LEFT JOIN pay_yqinfo yq ON ya.yqid=yq.yqId WHERE ya.accountId=?";
		List<Object> param=new ArrayList<Object>();
		param.add(id);
		List<YQInfo> list=jdbc.query(sql, param.toArray(),new BeanPropertyRowMapper(YQInfo.class));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
		
	}
	/**
	 * 保存
	 * @param yqid
	 * @param accountId
	 * @return
	 */
	@Override
	public Boolean save(String yqid, String accountId)throws Exception {
		String sql="INSERT INTO pay_yq_account yqid=?,accountId=? ";
		List<Object> param=new ArrayList<Object>();
		param.add(yqid);
		param.add(accountId);
		int count=jdbc.update(sql, param.toArray());
		if(count>0){
			return true;
		}
		return false;
		
	}
	
}
