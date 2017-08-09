package com.catic.mobilehos.dao;

import java.util.Date;
import java.util.List;

import com.catic.mobilehos.po.HosPubInfoPO;

public interface HosPubInfoDAO {
	
	void addHosPubInfo(HosPubInfoPO info);
	
	void updateHosPubInfo(final HosPubInfoPO info);
	
	void updateHosPubInfoStatus(String infoid, int status, String approver);
	
	void deleteHosPubInfo(String infoid);
	
	List<HosPubInfoPO> findLastPubHosInfo(int infoType, int num);
	
	List<HosPubInfoPO> findNewHosInfoByTime(int infoType, Date time, int num);
	
	List<HosPubInfoPO> findOldHosInfoByTime(int infoType, Date time, int num);
	
	List<HosPubInfoPO> findUnApprovedHosInfo(int infoType, String infoCatCode, int offset, int length);
	
	List<HosPubInfoPO> findUnApprovedHosInfoByParas(
			Integer infoType
			, String infoCatCode
			, java.sql.Date startExpDate
			, java.sql.Date endExpDate
			, String editor
			, int offset, int length);
	
	List<HosPubInfoPO> findApprovedHosInfoByParas(
			Integer infoType
			, String infoCatCode,Integer isMain
			, java.sql.Date startExpDate
			, java.sql.Date endExpDate
			,String editor,String approver
			, int offset, int length);
	
	int countUnApprovedHosInfoByParas(
			Integer infoType
			, String infoCatCode
			, java.sql.Date startExpDate
			, java.sql.Date endExpDate,String editor);
	
	int countApprovedHosInfoByParas(
			Integer infoType
			, String infoCatCode , Integer isMain
			, java.sql.Date startExpDate
			, java.sql.Date endExpDate,String editor,String approver);

	
	int countUnApprovedHosInfo(int infoType, String infoCatCode);
	
	int countHosInfoByCatCode(String infoCatCode);
	
	HosPubInfoPO getHosInfo(String id);
	
	void increaseBrowseCount(String id);
	
	List<HosPubInfoPO.StatByCatPO> statUnApprovedInfoByCat();
	
	List<HosPubInfoPO.StatByTypePO> statUnApprovedInfoByType();
	

}
