package com.catic.mobilehos.pay.dao;

import java.util.List;

import com.catic.mobilehos.pay.entity.YQInfo;

public interface IYQAccountDao {

	public List<YQInfo> findYQ(String id)throws Exception;
	public Boolean save(String yqid, String accountId)throws Exception;
}
