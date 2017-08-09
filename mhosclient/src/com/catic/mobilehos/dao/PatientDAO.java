package com.catic.mobilehos.dao;

import java.sql.Timestamp;
import java.util.List;

import com.catic.mobilehos.po.PatientCardPO;
import com.catic.mobilehos.po.PatientPO;
import com.catic.mobilehos.po.UserPO;

/**
 * 就诊人相关的持久化操作
 * @author xieweipeng
 *
 */
public interface PatientDAO {
	
	/**
	 * 
	 * @Title: findPatientsByUserId 
	 * @Description: TODO 根据会员ID获取就诊人列表
	 * @param @param userId 会员ID
	 * @return List<PatientPO> 就诊人列表 
	 * @throws
	 */
	List<PatientPO> findPatientsByUserId(String userId);
	
	PatientPO findPatientByPatentId(String patientId, String type);
	
	int countPatientsByParas(String cardNo, String patientname, String phone, 
			String identityCard, String startCreateTime, String endCreateTime, String type, String status, String patientSource);
	
	List<PatientPO> findPatientsByParas(String cardNo, String patientname, String phone, 
			String identityCard, String startCreateTime, String endCreateTime, String type, int offset, int length, String status, String patientSource);
	
	List<PatientCardPO> findPatientsCardByParms(String userId, String patientId, String cardNo);

	List<UserPO> findUserByPatientId(String patientId);
	
	void updatePatientOrUser(String type, String status, String patientId, String userId);
}
