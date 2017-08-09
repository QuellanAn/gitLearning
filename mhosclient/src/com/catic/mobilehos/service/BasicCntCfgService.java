package com.catic.mobilehos.service;

import java.io.File;
import javax.servlet.ServletContext;

import com.catic.mobilehos.po.BasicCntCfgPO;
import com.catic.mobilehos.service.vo.BasicCntCfgVO;
import com.catic.mobilehos.service.vo.Page;

/**
 * 基础内容配置
 * 
 * @author linchunda
 * 
 */
public interface BasicCntCfgService {

	/**
	 * 获取基础内容配置，含基础内容网页图片
	 * 
	 * @param cfg_type
	 *            类别
	 * @param cat
	 *            目录
	 * @return
	 */
	ServiceResult getBasicCntCfg(int cfg_type, int cat);
	
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

	/**
	 * 保存基本内容配置图片文件
	 * 
	 * @param context
	 * @param cfg_type
	 *            类别
	 * @param cat
	 *            目录
	 * @param f
	 *            图片文件
	 * @return
	 */
	ServiceResult saveBasicCntCfgHtmlImage(ServletContext context,
			int cfg_type, int cat, File f);
	
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
	
	public Page<BasicCntCfgVO> getAllContentList(String catName, String updatePeople,
			java.sql.Date startDate, java.sql.Date endDate,int page, int pageSize);
	
	ServiceResult addBaseContent(BasicCntCfgPO basicCntCfgPO);
	
	void deleteBaseContent(int cat);
}
