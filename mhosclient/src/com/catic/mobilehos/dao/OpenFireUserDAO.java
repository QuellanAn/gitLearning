package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.OpenFireUserPO;

/**
 * 及时通信OpenFire用户
 * 
 * @author linchunda
 * 
 */
public interface OpenFireUserDAO {
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
	 * 添加openFire用户
	 * 
	 * @param openFireUserPO
	 * @return
	 */
	boolean addOpenFireUserPO(OpenFireUserPO openFireUserPO);

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
	 * 把用户添加到组里，默认为非管理员身份
	 * 
	 * @param groupName
	 *            组名
	 * @param username
	 *            用户名
	 * @return
	 */
	boolean addUserToGroup(String groupName, String username);

	/**
	 * 获取总记录数
	 * 
	 * @param groupName
	 * @param username
	 * @return
	 */
	int getCountOpenFireUserPO(String groupName, String username);

	/**
	 * 根据分页获取openfire用户
	 * 
	 * @param groupName
	 * @param username
	 * @param offset
	 * @param length
	 * @return
	 */
	List<OpenFireUserPO> findOpenFireUserPOByByParas(String groupName,
			String username,  int offset, int length);

}
