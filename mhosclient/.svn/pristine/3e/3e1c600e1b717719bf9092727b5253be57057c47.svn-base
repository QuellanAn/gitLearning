package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.DocBillDetailPO;
import com.catic.mobilehos.po.DocBillsPO;
import com.catic.mobilehos.po.PayPO;


/**
 * 预约缴费dao层接口
 * @author dsh
 *
 */
public interface PayDAO {

	int countPayByParas(String docbillsn, String patientname, String status,
			String curdocsn, java.sql.Date startdate, java.sql.Date enddate);

	List<PayPO> findPayByParas(String docbillsn, String patientname,
			String status, String curdocsn, java.sql.Date startdate, java.sql.Date enddate,
			int offset, int length);

	List<DocBillDetailPO> findPayInfoByDocBillSn(String docBillSn);

	DocBillsPO findDocBillsPOByDocBillSn(String docBillSn);
	

}
