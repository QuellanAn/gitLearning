package com.catic.mobilehos.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.AppRegOrdersDAO;
import com.catic.mobilehos.dao.RegSucNoticeDAO;
import com.catic.mobilehos.po.AppRegOrdersPO;
import com.catic.mobilehos.po.PatientPO;
import com.catic.mobilehos.service.vo.AppRegOrdersVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.VOPOConverter;

public class AppRegOrderServiceImpl implements AppRegOrderService {
	
	private Log log = LogFactory.getLog(this.getClass());
	private AppRegOrdersDAO appRegOrdersDAO;
	
	private RegSucNoticeDAO regSucNoticeDAO;

	public AppRegOrdersDAO getAppRegOrdersDAO() {
		return appRegOrdersDAO;
	}

	public void setAppRegOrdersDAO(AppRegOrdersDAO appRegOrdersDAO) {
		this.appRegOrdersDAO = appRegOrdersDAO;
	}
	

	public RegSucNoticeDAO getRegSucNoticeDAO() {
		return regSucNoticeDAO;
	}

	public void setRegSucNoticeDAO(RegSucNoticeDAO regSucNoticeDAO) {
		this.regSucNoticeDAO = regSucNoticeDAO;
	}

	@Override
	public Page<AppRegOrdersVO> queryAppRegOrdersByParas(String orderid,
			String patientname,String status,
			java.sql.Date startDocDate, java.sql.Date endDocDate,
			Timestamp startCreateTime, Timestamp endCreateTime, int page, int pageSize,
			String type, String cardNumber, String doctorName, String departmentCode, String paid, String departmentName, String regSource) {
		try {
			int totalRecord = this.appRegOrdersDAO.countAppRegOrdersByParas(orderid,patientname,
					status, startDocDate, endDocDate, startCreateTime,
					endCreateTime, type, cardNumber, doctorName, departmentCode, paid, departmentName, regSource);
			Page<AppRegOrdersVO> p = new Page<AppRegOrdersVO>(totalRecord,
					pageSize, page);
			List<AppRegOrdersPO> lst = appRegOrdersDAO
					.findAppRegOrdersByParas(orderid,patientname,status, startDocDate, endDocDate,
							startCreateTime, endCreateTime, p.getOffset(),
							pageSize, type, cardNumber, doctorName, departmentCode, paid, departmentName, regSource);
			VOPOConverter<AppRegOrdersVO, AppRegOrdersPO> cvt = new VOPOConverter<AppRegOrdersVO, AppRegOrdersPO>(
					AppRegOrdersVO.class, AppRegOrdersPO.class);
			p.setCurPageData(cvt.fromPOList(lst));
			return p;
		} catch (Exception e) {
			log.error("queryAppRegOrdersByParas出现错误：",e);
			return null;
		}
	}

	@Override
	public List<PatientPO> findPatientByPatentId(String patientId) {
		return appRegOrdersDAO.findPatientByPatentId(patientId);
	}

	@Override
	public List<AppRegOrdersPO> findRegInfoByOrderId(String orderid) {
		return  appRegOrdersDAO.findRegInfoByOrderId(orderid);
	}

	@Override
	public List<AppRegOrdersPO> findAppRegOrdersPOByAppRegOrderId(
			String appRegOrderId) {
		// TODO Auto-generated method stub
		return appRegOrdersDAO.findAppRegOrdersPOByAppRegOrderId(appRegOrderId);
	}

	@Override
	public List<AppRegOrdersPO> getCountByDepartment(AppRegOrdersPO po) {
		return appRegOrdersDAO.getCountByDepartment(po);
	}

	@Override
	public List<AppRegOrdersPO> getCountByDate(AppRegOrdersPO po) {
		return appRegOrdersDAO.getCountByDate(po);
	}

	@Override
	public List<AppRegOrdersPO> getCountByDoctor(AppRegOrdersPO po) {
		return appRegOrdersDAO.getCountByDoctor(po);
	}
}
