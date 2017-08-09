package com.catic.mobilehos.service;

import java.sql.Date;
import java.util.List;

import com.catic.mobilehos.po.DocBillDetailPO;
import com.catic.mobilehos.po.DocBillsPO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.PayVO;

/**
 * 就诊缴费服务
 * @author dsh
 *
 */
public interface PayService {


	Page<PayVO> queryPayByParas(String docbillsn, String patientname,
			 String status, String curdocsn, Date startdate, Date enddate,
			int page, int pageSize);

	List<DocBillDetailPO> findPayInfoByDocBillSn(String docBillSn);
	
	DocBillsPO findDocBillsPOByDocBillSn(String docBillSn);
}
