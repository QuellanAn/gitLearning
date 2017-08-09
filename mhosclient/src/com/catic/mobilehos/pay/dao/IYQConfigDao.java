package com.catic.mobilehos.pay.dao;

import java.util.List;

import com.catic.mobilehos.pay.entity.YQInfo;
import com.catic.mobilehos.utils.Page;

public interface IYQConfigDao {
	
	public Boolean insert(YQInfo yq) throws Exception;
	
	public List<YQInfo> findAll(Page page) throws Exception ;
	
	public Boolean delete(String yqCode) throws Exception ;
	
	public Boolean modify(YQInfo yq) throws Exception ;
	
	public YQInfo  findByCode(String code) throws Exception ;
	
	public YQInfo  findById(String id) throws Exception ;
	
	public List<YQInfo> findAllyq() throws Exception;
}
