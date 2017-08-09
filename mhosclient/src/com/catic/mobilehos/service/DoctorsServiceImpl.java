package com.catic.mobilehos.service;

import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.catic.mobilehos.dao.DoctorDAO;
import com.catic.mobilehos.dao.OpenFireDoctorDao;
import com.catic.mobilehos.po.DoctorPO;
import com.catic.mobilehos.service.vo.DoctorVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.VOPOConverter;

public class DoctorsServiceImpl implements DoctorsService{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private DoctorDAO doctorDAO;
	private OpenFireDoctorDao ofDoctorDao;

	public DoctorDAO getDoctorDAO() {
		return doctorDAO;
	}

	public void setDoctorDAO(DoctorDAO doctorDAO) {
		this.doctorDAO = doctorDAO;
	}
	


	/**
	 * @param ofDoctorDao the ofDoctorDao to set
	 */
	public void setOfDoctorDao(OpenFireDoctorDao ofDoctorDao) {
		this.ofDoctorDao = ofDoctorDao;
	}

	@Override
	public DoctorVO getDoctorDetail(String docId) {
		DoctorPO doctorPO=doctorDAO.getDoctorPO(docId);
		if(doctorPO==null){
			return null;
		}else{
			VOPOConverter<DoctorVO, DoctorPO> converter = new VOPOConverter<DoctorVO, DoctorPO>(
					DoctorVO.class, DoctorPO.class);
			DoctorVO doctorVO=converter.poToVO(doctorPO);
			return doctorVO;
		}
		
	}


	@Override
	public Page<DoctorVO> getDoctorsListByName(String name,int page, int pageSize) {
		
		int totalRecord = this.doctorDAO.getDoctorCount(name);
		
		Page<DoctorVO> p = new Page<DoctorVO>(totalRecord, pageSize, page);
		List<DoctorPO> dlist = doctorDAO.findDoctorsByParas(name,p.getOffset(),p.getPageSize());
		VOPOConverter<DoctorVO, DoctorPO> cvt 
			= new VOPOConverter<DoctorVO, DoctorPO>(DoctorVO.class, DoctorPO.class);
		List<DoctorVO> vlist = cvt.fromPOList(dlist);
		p.setCurPageData(vlist);
		return p;
	}

	@Override
	public void updateDoctorPO(DoctorPO doctorPO, String docId) {
		doctorDAO.updateDoctorDepartment(doctorPO);
		doctorDAO.updateDoctor(doctorPO, docId);
	}

	@Override
	public boolean deleteDoctor(String doctorId) {
		return doctorDAO.deleteDoctor(doctorId);
	}

	/* (non-javadoc) 
	 * <p>Title: saveDoctor</p> 
	 * <p>Description: </p> 
	 * @param doctorPO 
	 * @see com.catic.mobilehos.service.DoctorsService#saveDoctor(com.catic.mobilehos.po.DoctorPO) 
	*/ 
	@Override
	public ServiceResult saveDoctor(DoctorPO doctorPO) {
		try {
			doctorDAO.saveDoctor(doctorPO);
			doctorDAO.saveDoctorDepartment(doctorPO);
			ofDoctorDao.saveOfDoctor(doctorPO.getDoctorName());
			return ServiceResult.getSucInstance();
		} catch (DataAccessException ex){
			log.error(null,ex);
			return ServiceResult.getFailedInstance("", "数据保存错误");
		} catch (Exception e) {
			log.error(null,e);
			return ServiceResult.getFailedInstance("系统异常","未知错误" );
		}
	}

	@Override
	public Page<DoctorVO> getDoctorsList(DoctorVO doctor, int page, int pageSize) {
		
        int totalRecord = this.doctorDAO.getDoctorCount(doctor);
		
		Page<DoctorVO> p = new Page<DoctorVO>(totalRecord, pageSize, page);
		List<DoctorPO> dlist = doctorDAO.findDoctorsByParas(doctor,p.getOffset(),p.getPageSize());
		VOPOConverter<DoctorVO, DoctorPO> cvt 
			= new VOPOConverter<DoctorVO, DoctorPO>(DoctorVO.class, DoctorPO.class);
		List<DoctorVO> vlist = cvt.fromPOList(dlist);
		p.setCurPageData(vlist);
		return p;
	}

	@Override
	public ServiceResult saveOrUpdateDoctor(DoctorPO doctorPO, int type) {
		try {
			
			doctorDAO.saveOrUpdateDoctor(doctorPO,type);
			return ServiceResult.getSucInstance();
		} catch (DataAccessException ex){
			log.error(null,ex);
			return ServiceResult.getFailedInstance("", "数据保存错误");
		} catch (Exception e) {
			log.error(null,e);
			return ServiceResult.getFailedInstance("系统异常","未知错误" );
		}
	}

	@Override
	public DoctorVO getDoctorDetailByCode(String code) {
		DoctorPO doctorPO=doctorDAO.getDoctorDetailByCode(code);
		if(doctorPO==null){
			return null;
		}else{
			VOPOConverter<DoctorVO, DoctorPO> converter = new VOPOConverter<DoctorVO, DoctorPO>(
					DoctorVO.class, DoctorPO.class);
			DoctorVO doctorVO=converter.poToVO(doctorPO);
			return doctorVO;
		}
	}

	@Override
	public DoctorPO findByUpDown(String sortNum,String operate) {
		DoctorPO doctorPO=doctorDAO.findByUpDown(sortNum,operate);
		return doctorPO;
	}

	@Override
	public void updateIdByOperate(String newId, String oldId) {
		doctorDAO.updateIdByOperate(newId, oldId);
	}

	@Override
	public List<DoctorPO> findByExcle() {
		List<DoctorPO> doctor=doctorDAO.findByExcle();
		return doctor;
	}

	@Override
	public List<DoctorPO> getDoctorByDepartmentId(DoctorPO po) {
		return doctorDAO.getDoctorByDepartmentId(po);
	}
}
