package com.catic.mobilehos.pay.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.ICheckBillDetailsBiz;
import com.catic.mobilehos.pay.entity.CheckBillDetails;
import com.catic.mobilehos.utils.Page;


@Service("checkBillDetailsBiz")
public class CheckBillDetailsBizImpl extends BaseBiz implements ICheckBillDetailsBiz{

	@Override
	public List<CheckBillDetails> findBillDetails(CheckBillDetails cbd,Page page) throws Exception{
		
		return checkBillDetailsDao.findAll(cbd, page);
	}

	@Override
	public Boolean updateOutHis(String id, Integer outStatus,
			Integer hisStatus) throws Exception {
		// TODO Auto-generated method stub
		return checkBillDetailsDao.updateOutHis(id, outStatus, hisStatus);
	}

	@Override
	public Boolean reBillStatus(Integer billStatus, String id)
			throws Exception {
		// TODO Auto-generated method stub
		return checkBillDetailsDao.reBillStatus(billStatus, id);
	}


	@Override
	public CheckBillDetails findByTrandeNo(String outTrandeNo,Integer transType) throws Exception {
		
		return checkBillDetailsDao.findByTrandeNo(outTrandeNo, transType);
	}


	@Override
	public CheckBillDetails findByOId(String orderId) {
		return checkBillDetailsDao.findByOId(orderId);
	}

	@Override
	public Boolean updateStatus(String id, String handle,String remark)
			throws Exception {
		return checkBillDetailsDao.updateStatus(id, handle,remark);
	}

	@Override
	public CheckBillDetails find(String id) throws Exception {
		return checkBillDetailsDao.find(id);
	}

	@Override
	public Boolean existUnhandled(String accountCode, String date)
			throws Exception {
		return checkBillDetailsDao.existUnhandled(accountCode, date);
	}

}
