package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.CliVersionPO;

/**
 * 版本发布
 * 
 * @author linchunda
 * 
 */
public interface CliVersionDAO {

	/**
	 * 客户端版本检查
	 * 
	 * @param ver_cat
	 *            客户端平台 android iOS
	 * @param ver_type
	 *            客户端类别 0为大众版,1为医护版
	 * @return
	 */
	CliVersionPO checkVersion(String ver_cat, String ver_type);

	/**
	 * 根据标识进行查找
	 * 
	 * @param ID
	 * @return
	 */
	CliVersionPO getCliVersionPOById(int ID);

	/**
	 * 根据客户端平台和类型,获取版本号
	 * 
	 * @param ver_cat
	 *            客户端平台
	 * @param ver_type
	 *            客户端类别
	 * @return
	 */
	List<String> getVersionNo(String ver_cat, String ver_type);

	/**
	 * 获取总记录数
	 * 
	 * @param ver_cat
	 *            客户端平台
	 * @return
	 */
	int getCountCliVersionPO(String ver_cat);

	/**
	 * 分页获取版本分布
	 * 
	 * @param ver_cat
	 * @param offset
	 * @param length
	 * @return
	 */
	List<CliVersionPO> findCliVersionPOByParas(String ver_cat, int offset,
			int length);

	/**
	 * 添加版本发布
	 * 
	 * @param cliVersionPO
	 * @return
	 */
	boolean saveCliVersionPO(CliVersionPO cliVersionPO);

	/**
	 * 修改版本发布
	 * 
	 * @param cliVersionPO
	 * @param ID
	 * @return
	 */
	boolean updateCliVersionPO(CliVersionPO cliVersionPO, int ID);

	/**
	 * 根据id进行删除
	 * 
	 * @param ID
	 * @return
	 */
	boolean deleteCliVersionPO(int ID);

}
