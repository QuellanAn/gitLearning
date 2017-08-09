package com.catic.mobilehos.pay.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catic.mobilehos.pay.entity.Bill;
import com.catic.mobilehos.pay.entity.CheckBillDetails;

public interface ICheckBillBiz {
	
	/**
	 * 导出对账记录
	 * @param checkBillList 准备导出的总对账记录
	 * @param detailList 准备导出的对账详情记录
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void exportCheckBill(List<Bill> checkBillList, List<CheckBillDetails> detailList, HttpServletRequest request, HttpServletResponse response);
	
	public Boolean recheck(String[] ids,String path) throws Exception;
}
