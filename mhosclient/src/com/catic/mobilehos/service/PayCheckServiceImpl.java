package com.catic.mobilehos.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.PayCheckDAO;
import com.catic.mobilehos.po.PayCheckPO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.PayCheckVO;
import com.catic.mobilehos.service.vo.VOPOConverter;

public class PayCheckServiceImpl implements PayCheckService {

	private Log log = LogFactory.getLog(this.getClass());
	private PayCheckDAO payCheckDAO;


	public PayCheckDAO getPayCheckDAO() {
		return payCheckDAO;
	}

	public void setPayCheckDAO(PayCheckDAO payCheckDAO) {
		this.payCheckDAO = payCheckDAO;
	}

	@Override
	public Page<PayCheckVO> queryPayCheckByParas(String orderid,
			String patientname, String payid, String wechatid,
			String tradetype, String status, java.sql.Date startdate, java.sql.Date enddate,
			int page, int pageSize) {
		try {
			int totalRecord = this.payCheckDAO.countPayCheckByParas( orderid,
					 patientname,  payid,  wechatid,
					 tradetype,  status,  startdate,  enddate);
			Page<PayCheckVO> p = new Page<PayCheckVO>(totalRecord, pageSize,
					page);
			List<PayCheckPO> lst = this.payCheckDAO.findPayCheckByParas(
					orderid, patientname,  payid,  wechatid, tradetype,  status,  startdate,  enddate,
					p.getOffset(), pageSize);
			VOPOConverter<PayCheckVO, PayCheckPO> cvt = new VOPOConverter<PayCheckVO, PayCheckPO>(
					PayCheckVO.class, PayCheckPO.class);
			p.setCurPageData(cvt.fromPOList(lst));
			return p;
		} catch (Exception e) {
			System.out.println("错误");
			log.error("queryOrderPayByParas出现错误", e);
			return null;

		}
	}




	
}