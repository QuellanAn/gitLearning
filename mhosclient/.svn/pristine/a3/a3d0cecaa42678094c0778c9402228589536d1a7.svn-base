package com.catic.mobilehos.service;  

import java.util.List;

import com.catic.mobilehos.po.SelfServiceLogPO;
import com.catic.mobilehos.utils.Page;

/**  
 * 类说明:自助终端日志service接口
 * @author 朱伟权
 * 创建时间: 2017-6-27 上午11:32:58
 */
public interface SelfServiceLogService {
	
	/**
	 * 根据条件查询自助终端日志
	 * @param po 查询条件
	 * @return
	 */
	List<SelfServiceLogPO> findAll(Page page, SelfServiceLogPO po);
	
	/**
	 * 保存上传日志记录
	 * @param po 上传日志
	 * @return true保存成功，false保存失败
	 */
	public boolean save(SelfServiceLogPO po);

}
 