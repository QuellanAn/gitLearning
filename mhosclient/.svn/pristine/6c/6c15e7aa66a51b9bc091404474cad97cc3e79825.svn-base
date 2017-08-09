package com.catic.mobilehos.pay.dao;

import java.util.List;

import com.catic.mobilehos.pay.entity.PayTerminal;
import com.catic.mobilehos.utils.Page;

public interface IPayTerminalDao {
	
	public Boolean insert(PayTerminal payTer) throws Exception;
	
	public List<PayTerminal> findAll(Page page,PayTerminal payTer) throws Exception ;
	
	public Boolean delete(int id) throws Exception ;
	
	public Boolean modify(PayTerminal payTer) throws Exception ;
	
	public PayTerminal findById(int id) throws Exception ;
	
	public PayTerminal findByCode(String code) throws Exception;
	
	/**
	 * 查询终端版本
	 * @param page 分页条件
	 * @param payTer 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<PayTerminal> findFacilityVersion(Page page, PayTerminal payTer) throws Exception ;
	
	/**
	 * 更新终端当前版本号
	 * @param payTer 终端信息
	 * @return true更新成功，false更新失败
	 */
	boolean updateVersioncode(PayTerminal payTer);
}
