package com.catic.mobilehos.dao;  

import java.util.List;

import com.catic.mobilehos.po.SelfServiceVersionPO;
import com.catic.mobilehos.po.SelfserviceUpgradeHistoryPO;
import com.catic.mobilehos.utils.Page;

/**  
 * 类说明:自助终端版本dao接口
 * @author 朱伟权
 * 创建时间: 2017-7-6 上午9:51:20
 */
public interface SelfServiceVersionDao {
	
	/**
	 * 根据条件查询自助终端发布版本
	 * @param page 分页条件
	 * @param po 查询条件
	 * @param isGroupby 是否需要groupby
	 * @return
	 */
	List<SelfServiceVersionPO> findAll(Page page, SelfServiceVersionPO po, boolean isGroupby);
	
	/**
	 * 查询终端升级历史
	 * @param page 分页条件
	 * @param facilityId 终端编号
	 * @param upgradeType 升级类型
	 * @return 终端升级历史
	 */
	List<SelfServiceVersionPO> findUpgradeHistory(Page page, String facilityId, String upgradeType);
	
	/**
	 * 根据id查询设备版本信息
	 * @param id 版本信息id
	 * @return 设备版本信息
	 */
	SelfServiceVersionPO findById(int id);

	/**
	 * 根据指定的所有id查询设备版本信息
	 * @param ids 指定的所有id
	 * @return 设备版本信息查询结果
	 */
	List<SelfServiceVersionPO> findByIds(String ids);
	
	/**
	 * 激活指定的设备版本
	 * @param ids 指定的设备版本id
	 * @return true激活成功，false激活失败
	 */
	boolean activateVersions(String ids);
	
	/**
	 * 保存上传的升级包信息
	 * @param po 升级包信息
	 * @return true保存成功，false保存失败
	 */
	boolean save(SelfServiceVersionPO po);
	
	/**
	 * 统计升级情况
	 * @param po 统计对应的版本
	 * @return 返回信息主要包括相应终端类型的总台数和已升级台数
	 */
	SelfServiceVersionPO countUpgrade(SelfServiceVersionPO po);
	
	/**
	 * 根据版本code查询版本记录
	 * @param upgradeType 升级类型
	 * @param facilityType 终端类型
	 * @param versioncode 版本code
	 * @return 版本记录
	 */
	SelfServiceVersionPO findByVersioncode(String upgradeType, String facilityType, int versioncode);
	
	/**
	 * 检查是否已存在>=指定版本号的版本
	 * @param po 版本查询条件
	 * @return true已存在，false不存在
	 */
	boolean checkVersionCode(SelfServiceVersionPO po);
	
	/**
	 * 查询指定升级类型和终端类型对应的最新版本信息
	 * @param upgradeType 升级类型
	 * @param facilityType 终端类型
	 * @return 最新版本信息
	 */
	SelfServiceVersionPO findLatestVersion(String upgradeType, String facilityType);
	
	/**
	 * 保存版本升级记录
	 * @param po 版本升级记录
	 * @return true保存成功，false保存失败
	 */
	boolean saveUpgradeHistory(SelfserviceUpgradeHistoryPO po);
	
	/**
	 * 检查指定终端的指定版本升级记录是否已存在
	 * @param po 版本升级记录
	 * @return true已存在，false不存在
	 */
	boolean checkUpgradeHistory(SelfserviceUpgradeHistoryPO po);

}
 