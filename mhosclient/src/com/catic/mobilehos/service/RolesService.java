package com.catic.mobilehos.service;

import java.util.List;

import com.catic.mobilehos.po.RolesPO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.RolesVO;

/**
 * 角色管理
 * 
 * @author linchunda
 * 
 */
public interface RolesService {

	/**
	 * 根据标识进行查找角色
	 * 
	 * @param roleId
	 * @return
	 */
	RolesPO getRolesPOByroleId(int roleId);

	/**
	 * 增加角色
	 * 
	 * @param rolesPO
	 */
	int createRolesPO(RolesPO rolesPO);

	/**
	 * 修改角色
	 * 
	 * @param rolesPO
	 */
	void updateRolesPO(RolesPO rolesPO, int roleId);

	/**
	 * 根据标识删除角色
	 * 
	 * @param roleId
	 * @return
	 */
	boolean deleteRolesPO(int roleId);

	/**
	 * 角色添加权限
	 * 
	 * @param roleId
	 *            角色标识
	 * @param au_ids
	 *            权限标识
	 * @return
	 */
	void addAuthority(int roleId, int[] au_ids);

	/**
	 * 移除角色的权限
	 * 
	 * @param roleId
	 *            角色标识
	 * @return
	 */
	boolean removeAuthority(int roleId);

	/**
	 * 获取角色的权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Integer> getAuthority(int roleId);

	/**
	 * 分页获取角色
	 * 
	 * @return
	 */
	Page<RolesVO> getRolesPOByParas(int curPage, int pageSize);

	/**
	 * 获取所有角色
	 * 
	 * @return
	 */
	List<RolesVO> findAllRolesVO();
}
