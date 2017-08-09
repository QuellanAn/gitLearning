package com.catic.mobilehos.pay.entity;

public class PayOrder {

	private String orderId;
	private String itemId;
	private String orderCode;
	private String cardNo;
	private Integer pattern;
	private Integer payType;
	private String  out_trade_no;
	private String  body;
	private Integer total_fee;
	private Integer actualPay;
	private String  time_start;
	private Integer payStatus;
	private String  attach;
	private String time_expire;
	private String hisTransId;
	private String deviceInfo;
	private String cardType;
	private String payStatusName;
	private String paySceneName;
	private String patternName;
	private String accountName;
	private Integer payScene;
	
	private Integer count;
	private String startdate;
	private String creatDate;
	private String updateTime;
	private String enddate;
	private String patientName;
	private String patientId;
	private String unpaid_id;
	
	private String refundReason;
	private String refundNo;
	private Double refundFee;
	private Integer refundType;
	private String  operatorId;
	private String operatorName;
	
	private Integer outStatus;
	private Integer hisStatus;
	private Integer isCancel;
	
	private Double fee;
	private String assetAccount;
	private Integer districtId;
	private String districtName;
	
	private String refundOrder;
	private String otherOrder;
	
	//交易概览参数、统计分析（占比分析）参数
	private Integer totalCount;//总笔数
	private double totalMoney;//总金额
	private double avgMoney;//人均金额
	private String upDown;//上升或下降
	private String contrast;//百分比
	
	private String timeType;//时间按钮
	private String analysisType;//分析类别按钮
	
	private String stime;//开始时间
	private String etime;//截止时间
	
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String getAnalysisType() {
		return analysisType;
	}
	public void setAnalysisType(String analysisType) {
		this.analysisType = analysisType;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public double getAvgMoney() {
		return avgMoney;
	}
	public void setAvgMoney(double avgMoney) {
		this.avgMoney = avgMoney;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getUpDown() {
		return upDown;
	}
	public void setUpDown(String upDown) {
		this.upDown = upDown;
	}
	public String getContrast() {
		return contrast;
	}
	public void setContrast(String contrast) {
		this.contrast = contrast;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public Integer getPattern() {
		return pattern;
	}
	public void setPattern(Integer pattern) {
		this.pattern = pattern;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Integer getActualPay() {
		return actualPay;
	}
	public void setActualPay(Integer actualPay) {
		this.actualPay = actualPay;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getUnpaid_id() {
		return unpaid_id;
	}
	public void setUnpaid_id(String unpaid_id) {
		this.unpaid_id = unpaid_id;
	}
	public String getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public String getRefundNo() {
		return refundNo;
	}
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
	public Double getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(Double refundFee) {
		this.refundFee = refundFee;
	}
	public Integer getRefundType() {
		return refundType;
	}
	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Integer getOutStatus() {
		return outStatus;
	}
	public void setOutStatus(Integer outStatus) {
		this.outStatus = outStatus;
	}
	public Integer getHisStatus() {
		return hisStatus;
	}
	public void setHisStatus(Integer hisStatus) {
		this.hisStatus = hisStatus;
	}
	public String getHisTransId() {
		return hisTransId;
	}
	public void setHisTransId(String hisTransId) {
		this.hisTransId = hisTransId;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public Integer getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}
	public String getAssetAccount() {
		return assetAccount;
	}
	public void setAssetAccount(String assetAccount) {
		this.assetAccount = assetAccount;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getRefundOrder() {
		return refundOrder;
	}
	public void setRefundOrder(String refundOrder) {
		this.refundOrder = refundOrder;
	}
	public String getOtherOrder() {
		return otherOrder;
	}
	public void setOtherOrder(String otherOrder) {
		this.otherOrder = otherOrder;
	}
	public String getPayStatusName() {
		return payStatusName;
	}
	public void setPayStatusName(String payStatusName) {
		this.payStatusName = payStatusName;
	}
	public String getPaySceneName() {
		return paySceneName;
	}
	public void setPaySceneName(String paySceneName) {
		this.paySceneName = paySceneName;
	}
	public String getPatternName() {
		return patternName;
	}
	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Integer getPayScene() {
		return payScene;
	}
	public void setPayScene(Integer payScene) {
		this.payScene = payScene;
	}
}
