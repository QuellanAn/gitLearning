package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.BasicCntCfgPO;
import com.catic.mobilehos.po.HosPubInfoPO;

/**
 * 基础内容配置
 * 
 * @author linchunda
 * 
 */
public interface BasicCntCfgDao {

	/**
	 * 获取基础内容配置
	 * 
	 * @param cfg_type
	 *            类别
	 * @param cat
	 *            目录
	 * @return
	 */
	BasicCntCfgPO getBasicCntCfgPO(int cfg_type, int cat);
	
	int getTotalContent(String catName,String updatePeople,java.sql.Date startDate,java.sql.Date endDate);
	
    List<BasicCntCfgPO> getAllContent(String catName,String updatePeople,java.sql.Date startDate,java.sql.Date endDate,int offset, int length);

	/**
	 * 保存基础内容配置
	 * 
	 * @param basicCntCfgPO
	 * @return
	 */
	int saveBasicCntCfg(BasicCntCfgPO basicCntCfgPO);

	/**
	 * 修改基础内容配置
	 * 
	 * @param basicCntCfgPO
	 * @param cfg_type
	 *            类别
	 * @param cat
	 *            目录
	 * @return
	 */
	void updateBasicCntCfg(BasicCntCfgPO basicCntCfgPO);
	
	void addBaseContent(BasicCntCfgPO basicCntCfgPO);

	void deleteBaseContent(int cat);
}
