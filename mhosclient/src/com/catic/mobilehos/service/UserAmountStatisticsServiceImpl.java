package com.catic.mobilehos.service;  

import java.util.List;

import com.catic.mobilehos.dao.UserDAO;
import com.catic.mobilehos.po.UserPO;

/**  
 * 类说明:用户量统计service实现类
 * @author 朱伟权  
 * 创建事件: 2017-5-4 17:18:12
 */
public class UserAmountStatisticsServiceImpl implements UserAmountStatisticsService {
	
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public List<UserPO> getUserDayCountByPeriod(UserPO po) {
		return userDAO.getUserDayCountByPeriod(po);
	}

	@Override
	public int getCountUser(UserPO po) {
		return userDAO.getCountUser(po);
	}

}
 