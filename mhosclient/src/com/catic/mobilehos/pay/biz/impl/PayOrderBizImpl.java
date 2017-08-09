package com.catic.mobilehos.pay.biz.impl;



import java.util.ArrayList;
import java.util.List;

import com.catic.mobilehos.pay.biz.IPayOrderBiz;
import com.catic.mobilehos.pay.dao.IPayOrderDao;
import com.catic.mobilehos.pay.entity.PayOrder;
import com.catic.mobilehos.pay.entity.TransFlow;
import com.catic.mobilehos.utils.CommonUtils;
import com.catic.mobilehos.utils.Page;



public class PayOrderBizImpl  implements  IPayOrderBiz{
	protected IPayOrderDao payOrderDao;
    
	//保存
	public boolean savePayOrder(PayOrder payOrder){
		return payOrderDao.save(payOrder);
	}
	
	public List<PayOrder> findAll(PayOrder po,Page page){
		return payOrderDao.findAll(po,page);
	}

	@Override
	public List<PayOrder> findByIS(String itemId, String payStatus){
		// TODO Auto-generated method stub
		return payOrderDao.findByIS(itemId, payStatus);
	}
	
	public PayOrder findByOrderId(String orderId){
		return payOrderDao.findByOrderId(orderId);
	}
	
	
	@Override
	public PayOrder findByTradeNo(String tradeNo) {
		// TODO Auto-generated method stub
		return payOrderDao.findByTradeNo(tradeNo);
	}
	
	@Override
	public PayOrder  findByNoAndCode(String tradeNo,String orderCode) {
		// TODO Auto-generated method stub
		return payOrderDao.findByNoAndCode(tradeNo, orderCode);
	}
	
	
	
	@Override
	public Boolean updatePayStatus(PayOrder po) {
		// TODO Auto-generated method stub
		return payOrderDao.updatePayStatus(po);
	}
	
	@Override
	public Boolean saveRefund(PayOrder po) {
		return payOrderDao.saveRefund(po);
	}
	
	@Override
	public Boolean saveHisTransId(PayOrder po) {
		return payOrderDao.saveHisTransId(po);
	}
	
	
	@Override
	public Boolean alterPayStautus(Integer payStatus, String outTradeNO)
			throws Exception {
		return payOrderDao.alterPayStautus(payStatus, outTradeNO);
	}
	
	@Override
	public List<PayOrder> findByDate(String date, Integer payStatus,
			String upDate) throws Exception {
		return payOrderDao.findByDate(date, payStatus, upDate);
	}
	
	
	public IPayOrderDao getPayOrderDao() {
		return payOrderDao;
	}

	public void setPayOrderDao(IPayOrderDao payOrderDao) {
		this.payOrderDao = payOrderDao;
	}

	@Override
	public List<PayOrder> findAllByPL(PayOrder po, Page page) {
		return payOrderDao.findAllByPL(po, page);
	}
	
	@Override
	public List<TransFlow> findAllByPlFlow(TransFlow tf, Page page) {
		return payOrderDao.findAllByPlFlow(tf, page);
	}
	
	@Override
	public PayOrder findByTimeExpire(String timeExpire) {
		return payOrderDao.findByTimeExpire(timeExpire);
	}

	public PayOrder findByNearTime(){
		return payOrderDao.findByNearTime();
	}

	@Override
	public List<PayOrder> findByStatistical(PayOrder po) {
		return payOrderDao.findByStatistical(po);
	}

	@Override
	public PayOrder selectOfStatisticalByPay(PayOrder po) {
		return payOrderDao.selectOfStatisticalByPay(po);
	}
	@Override
	public List<List<Object>> getExportTransflowDataList(List<PayOrder> payOrderList) {
		List<List<Object>> list = new ArrayList<List<Object>>();
		if(payOrderList != null && payOrderList.size() > 0){
			for (PayOrder payOrder : payOrderList) {
				List<Object> rowDataList = new ArrayList<Object>();
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getTime_start()));// 交易时间
				// 交易类型
				if(payOrder.getPayStatus() == null){// 应对值为null的情况
					rowDataList.add("");
	            }else if(payOrder.getPayStatus() == 1){
					rowDataList.add("支付");
	            }else if(payOrder.getPayStatus() == 3){
	            	rowDataList.add("退款");
	            }else{
	            	rowDataList.add("");
	            }
				// 交易单号
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getOut_trade_no()));
				// 第三方交易流水号
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getOrderCode()));
				// HIS交易流水号
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getHisTransId()));
				// 院区
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getDistrictName()));
				// 支付方式
				if(payOrder.getPattern() == null){// 应对值为null的情况
					rowDataList.add("");
	            }else if(payOrder.getPattern() == 0){
					rowDataList.add("微信");
	            }else if(payOrder.getPattern() == 1){
	            	rowDataList.add("支付宝");
	            }else{
	            	rowDataList.add("");
	            }
				// 支付场景
				if(payOrder.getPayType() == null){// 应对值为null的情况
					rowDataList.add("");
	            }else if(payOrder.getPayType() == 0){
					rowDataList.add("扫码支付");
	            }else if(payOrder.getPayType() == 1){
	            	rowDataList.add("刷卡支付");
	            }else{
	            	rowDataList.add("");
	            }
				// 退款单号
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getRefundOrder()));
				// 第三方退款流水单号
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getOtherOrder()));
				// 病人姓名
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getPatientName()));
				// 缴费项目
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getBody()));
				// 交易金额
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getFee()));
				// 订单状态
				if(payOrder.getPayStatus() == null){// 应对值为null的情况
					rowDataList.add("");
	            }else if(payOrder.getPayStatus() == 0){
					rowDataList.add("未支付");
	            }else if(payOrder.getPayStatus() == 1){
	            	rowDataList.add("支付成功");
	            }else if(payOrder.getPayStatus() == 2){
	            	rowDataList.add("退款中");
	            }else if(payOrder.getPayStatus() == 3){
	            	rowDataList.add("已退款");
	            }else{
	            	rowDataList.add("");
	            }
				
				list.add(rowDataList);
			}
		}
		return list;
	}

	@Override
	public List<List<Object>> getExportPayOrderDataList(
			List<PayOrder> payOrderList) {
		List<List<Object>> list = new ArrayList<List<Object>>();
		if(payOrderList != null && payOrderList.size() > 0){
			for (PayOrder payOrder : payOrderList) {
				List<Object> rowDataList = new ArrayList<Object>();
				//交易时间
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getCreatDate()));
				// 交易单号
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getOut_trade_no()));
				// 第三方交易流水号
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getOrderCode()));
				// HIS交易流水号
//				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getHisTransId()));
				//院区
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getDistrictName()));
				//资金账户
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getAccountName()));
				//支付场景
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getPaySceneName()));
				// 支付方式
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getPatternName()));
				// 就诊卡号
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getCardNo()));
//				if(payOrder.getPattern() == null){// 应对值为null的情况
//					rowDataList.add("");
//	            }else if(payOrder.getPattern() == 0){
//					rowDataList.add("微信");
//	            }else if(payOrder.getPattern() == 1){
//	            	rowDataList.add("支付宝");
//	            }else{
//	            	rowDataList.add("");
//	            }
//				// 支付场景 
//				if(payOrder.getPayType() == null){// 应对值为null的情况
//					rowDataList.add("");
//	            }else if(payOrder.getPayType() == 0){
//					rowDataList.add("扫码支付");
//	            }else if(payOrder.getPayType() == 1){
//	            	rowDataList.add("刷卡支付");
//	            }else{
//	            	rowDataList.add("");
//	            }
				// 就诊人
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getPatientName()));
				// 缴费项目 
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getBody()));
				// 交易金额(元) 
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getFee()));
				// 交易状态
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getPayStatusName()));
//				if(payOrder.getPayStatus() == null){// 应对值为null的情况
//					rowDataList.add("");
//	            }else if(payOrder.getPayStatus() == 0){
//					rowDataList.add("未支付");
//	            }else if(payOrder.getPayStatus() == 1){
//	            	rowDataList.add("支付成功");
//	            }else if(payOrder.getPayStatus() == 2){
//	            	rowDataList.add("退款中");
//	            }else if(payOrder.getPayStatus() == 3){
//	            	rowDataList.add("已退款");
//	            }else{
//	            	rowDataList.add("");
//	            }
				// 交易完成时间
				rowDataList.add(CommonUtils.nullStringToEmpty(payOrder.getTime_expire()));// 交易时间
				
				list.add(rowDataList);
			}
		}
		return list;
	}

	
	@Override
	public PayOrder selectOfStatisticalByRefund(PayOrder po) {
		return payOrderDao.selectOfStatisticalByRefund(po);
	}

	public List<PayOrder> selectOfStatisticalList(PayOrder po,Page page){
		return payOrderDao.selectOfStatisticalList(po,page);
	}

	@Override
	public List<PayOrder> findAllPayOrRecharge(PayOrder po, Page page,
			String redirectType) {
		// TODO Auto-generated method stub
		return payOrderDao.findAllPayOrRecharge(po, page, redirectType);
	}
}
