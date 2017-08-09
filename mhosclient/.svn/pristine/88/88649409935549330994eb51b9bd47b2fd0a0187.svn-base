package com.catic.mobilehos.service;

import java.io.File;
import java.util.List;
import javax.servlet.ServletContext;

import com.catic.mobilehos.po.InfoCatPO;
import com.catic.mobilehos.service.vo.HosPubInfoCatMenuVO;
import com.catic.mobilehos.service.vo.HosPubInfoVO;
import com.catic.mobilehos.service.vo.InfoCatVO;
import com.catic.mobilehos.service.vo.Page;

public interface HospitalInfoService {
	
	ServiceResult getInfoID();
	
	HosPubInfoVO getHosPubInfo(ServletContext context, String infoId);
	
	ServiceResult saveInfoImage(ServletContext context, String infoid, File f);
	
	ServiceResult saveInfoHtmlImage(ServletContext context, String infoid, File f);
	
	ServiceResult editHosInfo(
			String infoid
			, String subject
			, File image
			, String concise
			, String html
			, int isMain
			, String infoCatCode
			, java.sql.Date expectedPubDate);
	
	void deleteHosPubInfo(String infoid);
	
	
	ServiceResult createHosInfoHtml(ServletContext ctx, String id);
	
	
	List<InfoCatVO> getInfoCatCodes(int infoType);
	
	List<InfoCatPO> getInfoCat();
	
	/**
	 * 获得新发布和未审批通过的资讯列表
	 * @param infoType
	 * @param infoCatCode
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Page<HosPubInfoVO> getUnApprovedHosPubInfoList(int infoType,String infoCatCode, int page, int pageSize);
	
	/**
	 * 获得等待审批的资讯列表
	 * @param infoType
	 * @param infoCatCode
	 * @param startExpDate
	 * @param endExpDate
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Page<HosPubInfoVO> getWaitAppHosPubInfoList(Integer infoType, String infoCatCode
				, java.sql.Date startExpDate
				, java.sql.Date endExpDate
				, String editor, int page, int pageSize);
	
	/**
	 * 获得已发布资讯列表
	 * @param infoType
	 * @param infoCatCode
	 * @param startExpDate
	 * @param endExpDate
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Page<HosPubInfoVO> getApprovedHosPubInfoList(Integer infoType, String infoCatCode,int isMain
			, java.sql.Date startExpDate
			, java.sql.Date endExpDate,String editor,String approver
			, int page, int pageSize);
	
	/**
	 * 获得资讯目录菜单
	 * @return
	 */
	HosPubInfoCatMenuVO getUnApprovedCatMenu();
	
	/**
	 * 编辑资讯目录名
	 * @param infoCatCode
	 * @param infoCatName
	 * @return
	 */
	ServiceResult editCatName(String infoCatCode, String infoCatName);
	
	/**
	 * 删除资讯目录
	 * @param infoCatCode
	 * @return
	 */
	ServiceResult deleteCat(String infoCatCode);
	
	/**
	 * 添加资讯目录
	 * @param infoCatName
	 * @param infoType
	 * @return
	 */
	ServiceResult addCat(String infoCatName, int infoType);
	
	/**
	 * 提交审核通过操作
	 * @param infoid
	 * @return
	 */
	ServiceResult submitApproved(String infoid, String adminUserName);
	
	/**
	 * 提交审核不通过操作
	 * @param infoid
	 * @return
	 */
	ServiceResult submitUnApproved(String infoid, String adminUserName);
	
	/**
	 * 提交取消资讯操作
	 * @param infoid
	 * @return
	 */
	ServiceResult submitCancel(String infoid);

	ServiceResult createNewHosInfo(String infoid, String subject, File image,
			String concise, String html, int isMain, String infoCatCode,
			java.sql.Date expectedPubDate, String adminUserName);

}
