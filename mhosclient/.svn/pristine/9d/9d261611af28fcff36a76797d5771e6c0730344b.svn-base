package com.catic.mobilehos.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.catic.mobilehos.po.UserPO;

/**
 * 用户相关的持久化操作
 * 
 * @author xieweipeng
 * 
 */
public interface UserDAO {

	UserPO getUser(String userId);
	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	UserPO findUserByUsername(String username);



	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	List<UserPO> getAllUsers();

	/**
	 * 获取总记录数
	 * 
	 * @param user_name
	 *            会员账号
	 * @param status
	 *            会员状态
	 * @param start_create_date
	 *            注册起始时间
	 * @param end_create_date
	 *            注册结束时间
	 * @return
	 */
	int getCountUser(String user_name, String status,
			Timestamp start_create_date, Timestamp end_create_date);

	/**
	 * 根据分页获取用户
	 * 
	 * @param user_name
	 *            会员账号
	 * @param status
	 *            会员状态
	 * @param start_create_date
	 *            注册起始时间
	 * @param end_create_date
	 *            注册结束时间
	 * @param offset
	 * @param length
	 * @return
	 */
	List<UserPO> getUsersbyParas(String user_name, String status,
			Timestamp start_create_date, Timestamp end_create_date, int offset,
			int length);
	
	public int updateStatus(String userId, int status, Date blcakTime);
	
	/**
	 * 查询指定时间区间的用户日增量
	 * @param po 查询条件
	 * @return 指定时间区间的用户日增量
	 */
	public List<UserPO> getUserDayCountByPeriod(UserPO po);
	
	/**
	 * 查询指定条件的用户总人数
	 * @param po 查询条件
	 * @return 指定条件的用户总人数
	 */
	public int getCountUser(UserPO po);
	
}
