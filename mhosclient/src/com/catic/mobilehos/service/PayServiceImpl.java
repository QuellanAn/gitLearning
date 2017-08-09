package com.catic.mobilehos.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.PayDAO;
import com.catic.mobilehos.po.DocBillDetailPO;
import com.catic.mobilehos.po.DocBillsPO;
import com.catic.mobilehos.po.PayPO;
import com.catic.mobilehos.service.vo.AppRegOrdersVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.PayVO;
import com.catic.mobilehos.service.vo.VOPOConverter;

public class PayServiceImpl implements PayService {

	private Log log = LogFactory.getLog(this.getClass());
	private PayDAO payDAO;

	public PayDAO getPayDAO() {
		return payDAO;
	}

	public void setPayDAO(PayDAO payDAO) {
		this.payDAO = payDAO;
	}

	@Override
	public Page<PayVO> queryPayByParas(String docbillsn, String patientname,
			String status, String curdocsn, java.sql.Date startdate, java.sql.Date enddate,
			int page, int pageSize) {
		try {
			int totalRecord = this.payDAO.countPayByParas(docbillsn,
					patientname, status,curdocsn, startdate, enddate);
			Page<PayVO> p = new Page<PayVO>(totalRecord, pageSize,
					page);
			List<PayPO> lst = this.payDAO.findPayByParas(
					docbillsn, patientname, status,curdocsn, startdate, enddate,
					p.getOffset(), pageSize);
			VOPOConverter<PayVO, PayPO> cvt = new VOPOConverter<PayVO, PayPO>(
					PayVO.class, PayPO.class);
			p.setCurPageData(cvt.fromPOList(lst));
			return p;
		} catch (Exception e) {
			System.out.println("错误");
			log.error("queryOrderPayByParas出现错误", e);
			return null;

		}
	}

	@Override
	public List<DocBillDetailPO> findPayInfoByDocBillSn(String docBillSn) {
		// TODO Auto-generated method stub
		return payDAO.findPayInfoByDocBillSn(docBillSn);
	}

	@Override
	public DocBillsPO findDocBillsPOByDocBillSn(String docBillSn) {
		// TODO Auto-generated method stub
		return payDAO.findDocBillsPOByDocBillSn(docBillSn);
	}


	
}