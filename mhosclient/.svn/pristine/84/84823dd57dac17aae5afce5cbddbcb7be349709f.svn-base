package com.catic.mobilehos.pay.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.IRefundBiz;
import com.catic.mobilehos.pay.entity.PayOrder;
import com.catic.mobilehos.pay.entity.Refund;
import com.catic.mobilehos.utils.CommonUtils;
import com.catic.mobilehos.utils.Page;
@Service("refundBiz")
public class RefundBizImpl extends BaseBiz implements IRefundBiz {

	public List<PayOrder> findAll(PayOrder po,Page page) throws Exception{
		return payOrderDao.findRefund(po, page);
	}

	@Override
	public List<PayOrder> findRefundDetails(PayOrder po) throws Exception {
		return payOrderDao.findRefundDetails(po);
	}

	@Override
	public List<Refund> findNotRefund(int from, int to) throws Exception {
		// TODO Auto-generated method stub
		return refundDao.findNotRefund(from, to);
	}


	@Override
	public Boolean save(Refund refund) throws Exception {
		// TODO Auto-generated method stub
		return refundDao.save(refund);
	}

	@Override
	public Boolean update(Refund refund) throws Exception  {
		// TODO Auto-generated method stub
		return refundDao.update(refund);
	}
	
	@Override
	public List<Refund> findAll(Refund refund,Page page) throws Exception  {
		// TODO Auto-generated method stub
		return refundDao.findAll(refund,page);
	}
	
	@Override
	public List<Refund> findDetails(Refund refund) throws Exception {
		return refundDao.findDetails(refund);
	}
	
	@Override
	public List<Refund> findByTradeNo(String tradeNo) throws Exception {
		return refundDao.findByTradeNo(tradeNo);
	}

	@Override
	public List<Refund> findByDate(String date)
			throws Exception {
		// TODO Auto-generated method stub
		return refundDao.findByDate(date);
	}

	@Override
	public Refund findByrefundNo(String refundNo) throws Exception {
		// TODO Auto-generated method stub
		return refundDao.findByrefundNo(refundNo);
	}

	@Override
	public List<List<Object>> getExportRefundDataList(List<Refund> refundList) {
		List<List<Object>> list = new ArrayList<List<Object>>();
		if(refundList != null && refundList.size() > 0){
			for (Refund refund : refundList) {
				List<Object> rowDataList = new ArrayList<Object>();
				// 退款单号
				rowDataList.add(CommonUtils.nullStringToEmpty(refund.getRefundNo()));
				// 第三方退款流水号
				rowDataList.add(CommonUtils.nullStringToEmpty(refund.getRefundId()));
				// 原交易单号
				rowDataList.add(CommonUtils.nullStringToEmpty(refund.getTradeNo()));
				// 院区
				rowDataList.add(CommonUtils.nullStringToEmpty(refund.getYqName()));
				//资金账户
				rowDataList.add(CommonUtils.nullStringToEmpty(refund.getAccountName()));
				// 退款方
				if(refund.getPattern() == null){// 应对值为null的情况
					rowDataList.add("");
	            }else if(refund.getPattern() == 0){
					rowDataList.add("微信");
	            }else if(refund.getPattern() == 1){
	            	rowDataList.add("支付宝");
	            }else{
	            	rowDataList.add("");
	            }
				// 退款方式
				if(refund.getRefundType() == null){// 应对值为null的情况
					rowDataList.add("");
	            }else if(refund.getRefundType() == 0){
					rowDataList.add("自动退款");
	            }else if(refund.getRefundType() == 1){
	            	rowDataList.add("人工退款");
	            }else{
	            	rowDataList.add("");
	            }
				// 退款金额(元)
				rowDataList.add(CommonUtils.nullStringToEmpty(refund.getRefundFee()));
				// 退款状态
				if(refund.getRefundStatus() == null){// 应对值为null的情况
					rowDataList.add("");
	            }else if(refund.getRefundStatus() == 0){
					rowDataList.add("退款成功");
	            }else if(refund.getRefundStatus() == 1){
	            	rowDataList.add("退款失败");
	            }else{
	            	rowDataList.add("");
	            }
				// 操作人
				rowDataList.add(CommonUtils.nullStringToEmpty(refund.getOperatorName()));
				// 退款时间
				rowDataList.add(CommonUtils.nullStringToEmpty(refund.getRefundTime()));
				
				list.add(rowDataList);
			}
		}
		return list;
	}
}
