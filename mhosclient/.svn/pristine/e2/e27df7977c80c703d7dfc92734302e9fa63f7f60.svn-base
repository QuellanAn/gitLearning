package com.catic.mobilehos.service;

import java.util.List;

import com.catic.mobilehos.po.SysUsersPO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.SysUsersVO;

/**
 * 系统用户
 * 
 * @author linchunda
 * 
 */
public interface SysUsersService {
	/**
	 * 根据标识查找系统用户
	 * 
	 * @param userId
	 *            标识
	 * @return
	 */
	SysUsersPO getUser(int userId);

	/**
	 * 保存系统用户
	 * 
	 * @param sysUsersPO
	 */
	int createSysUsersPO(SysUsersPO sysUsersPO);

	/**
	 * 修改系统用户
	 * 
	 * @param sysUsersPO
	 * @param userId
	 *            标识
	 */
	void updateSysUsersPO(SysUsersPO sysUsersPO, int userId);

	/**
	 * 验证用户名是否已经存在
	 * 
	 * @param username
	 * @return 存在返回true,不存在返回false
	 */
	boolean existsUsername(String username);

	/**
	 * 删除系统用户
	 * 
	 * @param userId
	 * @return
	 */
	boolean deleteSysUser(int userId);

	/**
	 * 用户添加角色
	 * 
	 * @param roleId
	 *            用户标识
	 * @param role_ids
	 *            角色标识
	 * @return
	 */
	void addRoles(int userId, int[] role_ids);

	/**
	 * 移除用户的角色
	 * 
	 * @param userId
	 *            用户标识
	 * @return
	 */
	boolean removeRoles(int userId);

	/**
	 * 获取用户的角色
	 * 
	 * @param userId
	 * @return
	 */
	List<Integer> getRoles(int userId);

	/**
	 * 根据分页获取用户
	 * 
	 * @param realName
	 *            姓名
	 * @param status
	 *            状态
	 * @param roleId
	 *            角色
	 * @param offset
	 * @param length
	 * @return
	 */
	Page<SysUsersVO> getSysUsersPOsbyParas(String realName, int status,
			int roleId, int offset, int length);
	
	/**
	 * 根据账号名进行系统用户
	 * @param userName
	 * @return
	 */
	SysUsersPO findSysUsersPOByParas(String userName);
	
	/**
	 * 根据用户标识查找所具有的权限
	 * @param userId
	 * @return
	 */
	List<String> getAuthorityByUserId(int userId);

}
