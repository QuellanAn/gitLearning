package com.catic.mobilehos.service;  

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.dao.SelfServiceVersionDao;
import com.catic.mobilehos.pay.biz.IPayDictionaryBiz;
import com.catic.mobilehos.pay.entity.PayDictionary;
import com.catic.mobilehos.po.SelfServiceVersionPO;
import com.catic.mobilehos.po.SelfserviceUpgradeHistoryPO;
import com.catic.mobilehos.utils.Page;

/**  
 * 类说明:自助终端日志service接口实现
 * @author 朱伟权
 * 创建时间: 2017-7-6 上午11:08:48
 */
@Service("selfServiceVersionService")
public class SelfServiceVersionServiceImpl implements SelfServiceVersionService {
	
	@Resource(name="selfServiceVersionDao")
	private SelfServiceVersionDao selfServiceVersionDao;
	@Resource
	protected IPayDictionaryBiz payDictionaryBiz;
	
	@Override
	public List<SelfServiceVersionPO> findAll(Page page,
			SelfServiceVersionPO po, boolean isGroupby) {
		return selfServiceVersionDao.findAll(page, po, isGroupby);
	}

	@Override
	public List<SelfServiceVersionPO> findAll(Page page, SelfServiceVersionPO po) {
		return selfServiceVersionDao.findAll(page, po, false);
	}

	@Override
	public List<SelfServiceVersionPO> findUpgradeHistory(Page page, String facilityId, String upgradeType){
		return selfServiceVersionDao.findUpgradeHistory(page, facilityId, upgradeType);
	}

	@Override
	public SelfServiceVersionPO findById(int id) {
		return selfServiceVersionDao.findById(id);
	}

	@Override
	public List<SelfServiceVersionPO> findByIds(String ids) {
		return selfServiceVersionDao.findByIds(ids);
	}

	@Override
	public boolean activateVersions(String ids) {
		return selfServiceVersionDao.activateVersions(ids);
	}

	@Override
	public boolean save(SelfServiceVersionPO po) throws Exception {
		po.setUpdateTime(new Timestamp(new Date().getTime()));
		if("0".equals(po.getActivationStatus())){
			po.setActivationTime(null);
		}else if("1".equals(po.getActivationStatus())){
			po.setActivationTime(new Timestamp(new Date().getTime()));
		}
		List<PayDictionary> list = payDictionaryBiz.findAll("facility_type");
		if(list != null && list.size() > 0){
			for (PayDictionary payDictionary : list) {
				if(payDictionary.getCodeNo().equals(po.getFacilityType())){
					// 补全设备类型名称
					po.setFacilityTypeName(payDictionary.getCodeName());
				}
			}
		}
		return selfServiceVersionDao.save(po);
	}

	@Override
	public SelfServiceVersionPO countUpgrade(SelfServiceVersionPO po) {
		SelfServiceVersionPO countUpgrade = selfServiceVersionDao.countUpgrade(po);
		if(countUpgrade != null){
			// 未升级台数 = 总台数 - 已升级台数
			countUpgrade.setNotupgradeSum(countUpgrade.getAllSum() - countUpgrade.getUpgradedSum());
		}
		return countUpgrade;
	}

	@Override
	public SelfServiceVersionPO findByVersioncode(String upgradeType,
			String facilityType, int versioncode) {
		return selfServiceVersionDao.findByVersioncode(upgradeType, facilityType, versioncode);
	}

	@Override
	public boolean checkVersionCode(SelfServiceVersionPO po) {
		return selfServiceVersionDao.checkVersionCode(po);
	}

	@Override
	public SelfServiceVersionPO findLatestVersion(String upgradeType,
			String facilityType) {
		return selfServiceVersionDao.findLatestVersion(upgradeType, facilityType);
	}

	@Override
	public boolean saveUpgradeHistory(SelfserviceUpgradeHistoryPO po) {
		return selfServiceVersionDao.saveUpgradeHistory(po);
	}

	@Override
	public boolean checkUpgradeHistory(SelfserviceUpgradeHistoryPO po) {
		return selfServiceVersionDao.checkUpgradeHistory(po);
	}

}
 