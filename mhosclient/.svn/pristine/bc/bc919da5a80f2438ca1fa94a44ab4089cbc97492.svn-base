/**   
 * @Title: AppRegOrderExceptionDao.java 
 * @Package com.catic.mobilehos.dao 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-19 下午5:07:34 
 * 
 */
package com.catic.mobilehos.dao.synchronize;

import java.util.List;

import com.catic.mobilehos.po.AppRegExceptionPO;

/**
 * @author WANG
 * 
 */
public interface IAppRegExceptionDao {
	void addOrderExceptFromHIS(List<AppRegExceptionPO> apoeList);

	long findLastException();

	int updateOrderStatus(String orderid, String excType);
	
	int setUserStatus(String orderid, String excType, int limitDays, int times, int blackDays);
}
