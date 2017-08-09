package com.catic.mobilehos.service;

import com.catic.mobilehos.po.OpenFireUserPO;
import com.catic.mobilehos.service.vo.Page;

/**
 * 及时通信OpenFire用户
 * 
 * @author linchunda
 * 
 */
public interface OpenFireUserService {
	/**
	 * 获取passwordKey
	 * 
	 * @return
	 */
	String getPasswordKey();

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	OpenFireUserPO findByUsername(String username);

	/**
	 * 添加openFire用户,并添加到医院所在组里
	 * 
	 * @param openFireUserPO
	 *            用户
	 * @param groupName
	 *            组名
	 * 
	 */
	void addOpenFireUserPO(OpenFireUserPO openFireUserPO, String groupName);

	/**
	 * 修改openFire用户
	 * 
	 * @param username
	 * @param openFireUserPO
	 * @return
	 */
	boolean updateOpenFireUserPO(String username, OpenFireUserPO openFireUserPO);

	/**
	 * 删除用户
	 * 
	 * @param username
	 * @return
	 */
	boolean deleteOpenFireUserPO(String username);

	/**
	 * 根据分页获取医护人员
	 * @param groupName
	 * @param username
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Page<OpenFireUserPO> findOpenFireUserPOByByParas(String groupName,
			String username,int curPage, int pageSize);

}
