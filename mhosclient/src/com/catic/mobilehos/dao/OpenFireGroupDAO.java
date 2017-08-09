package com.catic.mobilehos.dao;

import com.catic.mobilehos.po.OpenFireGroupPO;
import com.catic.mobilehos.po.OpenFireGroupPropPO;

/**
 * 即时通信 openFire 用户组
 * 
 * @author linchunda
 * 
 */
public interface OpenFireGroupDAO {

	/**
	 * 添加用户组
	 * 
	 * @param openFireGroupPO
	 * @return
	 */
	boolean addGroup(OpenFireGroupPO openFireGroupPO);

	/**
	 * 添加组属性名-值
	 * 
	 * @param openFireGroupPropPO
	 *            组属性名-值
	 * @return
	 */
	boolean addGroupProp(OpenFireGroupPropPO openFireGroupPropPO);
	
	/**
	 * 是否已经存在该用户组名
	 * 
	 * @param groupName
	 * @return 存在返回true
	 */
	boolean haveGroupName(String groupName);

}
