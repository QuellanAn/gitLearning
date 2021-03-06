package com.catic.mobilehos.pay.biz;

import java.util.List;

import com.catic.mobilehos.pay.entity.CheckBillDetails;
import com.catic.mobilehos.utils.Page;

public interface ICheckBillDetailsBiz {

	public List<CheckBillDetails> findBillDetails(CheckBillDetails cbd,Page page) throws Exception;
	
	public Boolean updateOutHis(String id,Integer outStatus,Integer hisStatus)throws Exception;
	
	public Boolean reBillStatus(Integer billStatus,String id) throws Exception;
	
	public CheckBillDetails findByTrandeNo(String outTrandeNo,Integer transType) throws Exception;

	public CheckBillDetails findByOId(String orderId);
	
	public Boolean updateStatus(String id,String handle,String remark) throws Exception;
	
	public CheckBillDetails find(String id) throws Exception;
	
	public Boolean existUnhandled(String accountCode,String date) throws Exception;
}
