package com.catic.mobilehos.service;

import java.sql.Timestamp;
import java.util.List;

import com.catic.mobilehos.po.PatientCardPO;
import com.catic.mobilehos.po.PatientPO;
import com.catic.mobilehos.po.UserPO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.PatientsVO;

public interface PatientsService {
	Page<PatientsVO> queryPatientsByParas(String cardNo, String patientname, String phone, 
			String identityCard, String startSQLDate, String endSQLDate, String type, int page, int pageSize, String status, String patientSource);
	
	PatientPO findPatientByPatentId(String patientId, String type);
	
	List<PatientCardPO> findPatientsCardByParms(String userId, String patientId, String cardNo);
	
	List<UserPO> findUserByPatientId(String patientId);
	
	void updatePatientOrUser(String type, String status, String patientId, String userId);
	
	List<PatientPO> findPatientsByUserId(String userId);
}
