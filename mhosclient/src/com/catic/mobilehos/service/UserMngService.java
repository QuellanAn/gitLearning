package com.catic.mobilehos.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.catic.mobilehos.po.PatientPO;
import com.catic.mobilehos.po.UserPO;
import com.catic.mobilehos.service.vo.Page;

import net.sf.json.JSONArray;

/**
 * 后台会员管理服务
 * 
 * @author xieweipeng
 * 
 */
public interface UserMngService {

	/**
	 * 获取所有用户列表
	 * 
	 * @return
	 */
	JSONArray getAllUsersList();


	/**
	 * 根据分页获取会员
	 * 
	 * @param user_name
	 *            会员账号
	 * @param status
	 *            会员状态
	 * @param start_create_date
	 *            注册起始时间
	 * @param end_create_date
	 *            注册结束时间
	 * @param page
	 *            当前页
	 * @param pageSize
	 *            每页大小
	 * @return
	 */
	Page<UserPO> findUserPOByByParas(String user_name, String status,
			Timestamp start_create_date, Timestamp end_create_date, int page,
			int pageSize);


	/**
	 * 根据userId查找就诊人
	 * 
	 * @param userId
	 * @return
	 */
	List<PatientPO> findPatientsByUserId(String userId);

	
	public int updateStatus(String userId, int status, Date blcakTime);
	
	UserPO findUserByUsername(String username);
}
