package com.catic.mobilehos.service;  

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.dao.SelfServiceLogDao;
import com.catic.mobilehos.po.SelfServiceLogPO;
import com.catic.mobilehos.utils.Page;

/**  
 * 类说明:自助终端日志service接口实现
 * @author 朱伟权
 * 创建时间: 2017-6-27 上午11:34:17
 */
@Service("selfServiceLogService")
public class SelfServiceLogServiceImpl implements SelfServiceLogService {
	
	@Resource(name="selfServiceLogDao")
	private SelfServiceLogDao selfServiceLogDao;

	@Override
	public List<SelfServiceLogPO> findAll(Page page, SelfServiceLogPO po) {
		return selfServiceLogDao.findAll(page, po);
	}

	@Override
	public boolean save(SelfServiceLogPO po) {
		po.setUpdateTime(new Timestamp(new Date().getTime()));
		return selfServiceLogDao.save(po);
	}

}
 