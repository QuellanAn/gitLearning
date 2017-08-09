package com.catic.mobilehos.service;

import java.sql.Timestamp;
import java.util.List;

import com.catic.mobilehos.dao.PatientDAO;
import com.catic.mobilehos.po.PatientCardPO;
import com.catic.mobilehos.po.PatientPO;
import com.catic.mobilehos.po.UserPO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.PatientsVO;
import com.catic.mobilehos.service.vo.VOPOConverter;

public class PatientsServiceImpl implements PatientsService{
	private PatientDAO patientDao;
	
	public PatientDAO getPatientDao() {
		return patientDao;
	}

	public void setPatientDao(PatientDAO patientDao) {
		this.patientDao = patientDao;
	}

	@Override
	public Page<PatientsVO> queryPatientsByParas(String cardNo,
			String patientname, String phone, String identityCard,
			String startCreateTime, String endCreateTime, String type, int page, int pageSize, String status, String patientSource) {
		// TODO Auto-generated method stub
		try {
			int totalRecord = this.patientDao.countPatientsByParas(cardNo, patientname, 
					phone, identityCard, startCreateTime, endCreateTime, type, status, patientSource);
			Page<PatientsVO> p = new Page<PatientsVO>(totalRecord, pageSize, page);
			List<PatientPO> list = patientDao.findPatientsByParas(cardNo, patientname, phone, identityCard,
					startCreateTime, endCreateTime, type, p.getOffset(), pageSize, status, patientSource);
			VOPOConverter<PatientsVO, PatientPO> cvt = new VOPOConverter<PatientsVO, PatientPO>(
					PatientsVO.class, PatientPO.class);
			p.setCurPageData(cvt.fromPOList(list));
			return p;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public PatientPO findPatientByPatentId(String patientId, String type) {
		// TODO Auto-generated method stub
		return patientDao.findPatientByPatentId(patientId, type);
	}

	@Override
	public List<PatientCardPO> findPatientsCardByParms(String userId,
			String patientId, String cardNo) {
		// TODO Auto-generated method stub
		return patientDao.findPatientsCardByParms(userId, patientId, cardNo);
	}

	@Override
	public List<UserPO> findUserByPatientId(String patientId) {
		// TODO Auto-generated method stub
		return patientDao.findUserByPatientId(patientId);
	}

	@Override
	public void updatePatientOrUser(String type, String status,
			String patientId, String userName) {
		// TODO Auto-generated method stub
		patientDao.updatePatientOrUser(type, status, patientId, userName);
	}

	@Override
	public List<PatientPO> findPatientsByUserId(String userId) {
		// TODO Auto-generated method stub
		return patientDao.findPatientsByUserId(userId);
	}

}
