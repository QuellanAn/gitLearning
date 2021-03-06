package com.catic.mobilehos.pay.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;

import com.catic.mobilehos.pay.entity.CheckRecord;
import com.catic.mobilehos.pay.entity.PayLog;
import com.catic.mobilehos.pay.entity.PayOrder;
import com.catic.mobilehos.pay.entity.Refund;
import com.catic.mobilehos.pay.util.Common;
import com.catic.mobilehos.pay.util.Constant;
import com.catic.mobilehos.pay.util.DateUtils;
import com.catic.mobilehos.utils.ExportExcel;
import com.catic.mobilehos.utils.Page;

/*
 * 退账
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class RefundAction extends BaseAction{

	private String tradeNo;
	private String refundNo;
	private String refundId;
	private String districtId;
	private String account;
	private String operatorName;
	private String pattern;
	private String refundType;
	private String tk_startdate;
	private String tk_enddate;
	private String refundStatus;
	
	private String cardNo;
	private String amount;
	private String refundReason;
	
	private String password;
	
	private Page page;
	
	private Refund rf;
	private List<Refund> rfList;

	private PayOrder po;
	private List<PayOrder> polist;
	
	private PayLog payLog=new PayLog();
	
	private Log log =LogFactory.getLog(this.getClass());

	private String flag;//5:来自对账异常的退款
	private String checkDetailsId;
	
    public String findAll(){
    	try {
    	if(StringUtils.isBlank(pageNo)){
			pageNo = "1";
		}
    /*	po=new PayOrder();
    	po.setOut_trade_no(tradeNo);
    	po.setRefundNo(refundNo);
    	if(StringUtils.isNotBlank(pattern)){
    		po.setPattern(Integer.parseInt(pattern));
    	}
    	if(StringUtils.isNotBlank(refundType)){
    		po.setRefundType(Integer.parseInt(refundType));
    	}
    	    po.setPatientName(patientName);
    	    polist=refundBiz.findAll(po, null);*/
    	    
    	    rf=new Refund();
    	    rf.setTradeNo(tradeNo);
    	    rf.setRefundNo(refundNo);
    	    rf.setRefundId(refundId);
    	    rf.setDistrictId(districtId);
    	    rf.setAccount(account);
    		if(StringUtils.isNotBlank(pattern)){
    			rf.setPattern(Integer.parseInt(pattern));
        	}
        	if(StringUtils.isNotBlank(refundType)){
        		rf.setRefundType(Integer.parseInt(refundType));
        	}
        	    rf.setOperatorName(operatorName);
        	    rf.setTk_startdate(tk_startdate);
        	    rf.setTk_enddate(tk_enddate);
        	    if(StringUtils.isNotBlank(refundStatus)){
        	    	 rf.setRefundStatus(Integer.parseInt(refundStatus));
        	    }
        	   
    	        rfList=refundBiz.findAll(rf, null);
    	        
    	        if(rfList!=null&&rfList.size()>0){	
            		page=new Page(Integer.parseInt(pageNo), 10, rfList.get(0).getCount());	
            		rfList=refundBiz.findAll(rf, page);
            	}
        	/*if(polist!=null&&polist.size()>0){	
        		page=new Page(Integer.parseInt(pageNo), 10, polist.get(0).getCount());	
        	    polist=refundBiz.findAll(po, page);
        	}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
    
    }
	
    /*
     * 查看详情
     */
    public String findDetails(){
    	try {
    /*		po=new PayOrder();
        	po.setOut_trade_no(tradeNo);
			polist=refundBiz.findRefundDetails(po);
			if(polist!=null){
				po=polist.get(0);
			}
			unpaidPO=new UnpaidPO();
			unpaidPO=unpaidBiz.findOne(unpaidPO);*/
    		rf=new Refund();
    		rf.setTradeNo(tradeNo);
    		rfList=refundBiz.findDetails(rf);
    		if(rfList!=null){
				rf=rfList.get(0);
			}
    		rf.setOutStatus(rf.getPayStatus());
    	/*	Boolean flag=false;	
			if(rf.getPattern()==0){//微信支付				
				ScanPayQueryReqData scanPayQueryReqData = new ScanPayQueryReqData("", tradeNo);
				ScanPayQueryBusiness.ResultListener resultListener = new ScanPayQueryResultListener();
				ScanPayQueryResData scanPayQueryResData = WXPay.doScanPayQueryBusiness(scanPayQueryReqData, resultListener);
				if(scanPayQueryResData != null){
					//交易状态用来确认是否交易成功
					//SUCCESS—支付成功    REFUND—转入退款  NOTPAY—未支付  CLOSED—已关闭  USERPAYING--用户支付中  PAYERROR--支付失败(其他原因，如银行返回失败)
					String tradeState = scanPayQueryResData.getTrade_state();				
					if(tradeState.equals("SUCCESS")){//支付成功
						rf.setOutStatus(1);					
						if(rf!=null&&rf.getPayStatus()!=1){//不匹配则修改数据的支付状态
							flag=true;
						}
					}else if(tradeState.equals("REFUND")){
						rf.setOutStatus(2);
					}else{
						rf.setOutStatus(0);
					}
				}
			}else if(rf!=null&&rf.getPattern()==1){//支付宝支付
		            String result=new AliPay().trade_query(tradeNo);          
		            if(result.equals("1")){
		            	rf.setOutStatus(0);
		            }else if(result.equals("2")){
		            	rf.setOutStatus(0);
		            }else if(result.equals("3")){
		            	rf.setOutStatus(0);
		            }else{
		            	rf.setOutStatus(1);
		            	if(rf.getPayStatus()!=1){
		            		flag=true;
		            	}
		            }
			}
			if(flag){
				payOrderBiz.alterPayStautus(1,tradeNo);	
			}	*/
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    		return "details";
    }
	
    
	public void refund(){
		try {
	/*	payLog.setItemName("执行退款");
		payLog.setStatus(0);
		payLog.setOut_trade_no(tradeNo);
		payLogBiz.saveLog(payLog);		*/
		PayOrder order=payOrderBiz.findByTradeNo(tradeNo);
		if((Integer)request.getSession().getAttribute("userId")!=null){
			String pass=systermUserBiz.findPassword((Integer)request.getSession().getAttribute("userId")+"");
			//验证密码
		   if(pass.equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes()))){
		
//		 boolean flag=false;
		if(order!=null){	//order.getPayStatus()==1	
			String refundNo = "2" + order.getCardNo() + DateUtils.formatDate(new Date(), 2) + Common.getRandom(8);   
		    CloseableHttpClient httpclient = HttpClients.createDefault();
		    HttpPost httppost = new HttpPost(Constant.URL_REFUND);  
		    // 创建参数队列    
	        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
	        formparams.add(new BasicNameValuePair("tradeNo",tradeNo));  //退款单号
	        formparams.add(new BasicNameValuePair("refundNo",refundNo));  //退款单号
	    	formparams.add(new BasicNameValuePair("refundReason", refundReason));  //退款原因
	    	formparams.add(new BasicNameValuePair("pattern", order.getPattern()+""));//***  
	    	formparams.add(new BasicNameValuePair("amount",amount));  //退款金额
	    	formparams.add(new BasicNameValuePair("totalFee",order.getActualPay()+""));  //支付金额
	    	formparams.add(new BasicNameValuePair("deviceInfo", order.getDeviceInfo()));  
	    	formparams.add(new BasicNameValuePair("userId", (Integer)request.getSession().getAttribute("userId")+""));
	    	formparams.add(new BasicNameValuePair("userName",(String)request.getSession().getAttribute("userName")));
	   	 UrlEncodedFormEntity  uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
         httppost.setEntity(uefEntity);  
         CloseableHttpResponse response = httpclient.execute(httppost);  
         HttpEntity entity = response.getEntity();  
         if (entity != null) {             
             JSONObject obj = JSONObject.fromObject(EntityUtils.toString(entity, "UTF-8"));
             if(obj.getString("code").equals("0")){//成功
            	 
            	result=resultBiz.getResult(resultBiz.SUCCESS);
            	
            	if(flag.equals("5")){
            	  	CheckRecord record=new CheckRecord();
        			record.setTransactionNum(tradeNo);
        			record.setTaskState("执行“退款”操作，退款成功");
        			record.setOperator((String)getSession().getAttribute("userName"));
        			record.setRecordType(1);
        			record.setTradeType(0); 
        			record.setAccount(order.getAssetAccount());//资金账户
        			checkRecodeBiz.save(record);//保存操作记录
       			
       			    checkBillDetailsBiz.updateStatus(checkDetailsId, "1", null);//更改状态为已处理
            	}
            	
            	// return "refund";	 
             }else{
            	 result=resultBiz.getResult(resultBiz.FAIL); 
            		if(flag.equals("5")){
                	  	CheckRecord record=new CheckRecord();
            			record.setTransactionNum(tradeNo);
            			record.setTaskState("执行“退款”操作，退款失败");
            			record.setOperator((String)getSession().getAttribute("userName"));
            			record.setRecordType(1);
            			record.setTradeType(0);   				
            			checkRecodeBiz.save(record);//保存操作记录
                	}
             }
         }  
         response.close();  
         httpclient.close();  
		}else{//订单不存在
			 result=resultBiz.getResult(resultBiz.FAIL);  
		}
		   }else{//输入密码不正确
			   result=resultBiz.getResult(resultBiz.PASSWORD_ERRO);  
		   }			
		}else{
			result=resultBiz.getResult(resultBiz.USER_NO);  
		   }	
              
         
	/*	if(pattern.equals("0")){//微信支付
		
		}else if(pattern.equals("1")){//支付宝
//				Integer code=new AliPay().trade_refund(tradeNo, amount,refundNo,"用户支付成功后切换了其他支付方式", "");
			 Integer code=0;
				if(code==0){//退款成功
					flag=true;
				}
		}		*/
	
		
	/*	 if(flag){//微信退款成功则更新退款状态
	    	    System.out.println("执行第三方退款");
	    	    payLog.setItemName("执行第三方退款");
	  		    payLog.setStatus(0);
	  		    payLog.setOut_trade_no(tradeNo);
	  		    payLogBiz.saveLog(payLog); 
	    	
	    	log.warn("【执行第三方退款成功】商户订单号："+ tradeNo);
	    	//创建退账记录
	    	Refund refund=new Refund();
	        refund.setId("2"+com.catic.mobilehos.utils.Common.getUUID());
	    	refund.setTradeNo(tradeNo);
	    	refund.setRefundNo(refundNo);
	    	refund.setRefundFee(Double.parseDouble(amount));//元
	    	refund.setRefundStatus(0);//待退款
		    refund.setRefundType(1);//退款 
		    refund.setOperatorId((Integer)request.getSession().getAttribute("userId")+"");
		    refund.setOperatorName((String)request.getSession().getAttribute("userName"));
		    refund.setRefundReason(refundReason);
	 //   	refundBiz.save(refund);
	        
		    //更新退账状态
	    	com.catic.mobilehos.pay.entity.Refund r=new com.catic.mobilehos.pay.entity.Refund();
	    	r.setRefundNo(refundNo);//退款流水号
	    	r.setRefundStatus(1);//退款成功
	    	r.setTradeNo(tradeNo);
	    	//更新退款表
	    	if(refundBiz.save(refund)&&refundBiz.update(r)){
	    		 System.out.println("执行第三方退款成功-更新退款表");
	    		log.warn("【执行第三方退款成功-更新退款表】商户订单号："+tradeNo);
	    	}
	    
	       PayOrder payOrder1=new PayOrder();
	       payOrder1.setOut_trade_no(tradeNo);
	       payOrder1.setPayStatus(3);//已退款
	      //更新订单表
	       if(payOrderBiz.saveHisTransId(payOrder1)){
	    	 System.out.println("执行第三方退款成功-更新订单表");
 		log.warn("【执行第三方退款成功-更新订单表】商户订单号："+ tradeNo);
 	  }				  			    
	    }else{//退款失败则加入退款表中，状态为待退款
	    	Refund refund=new Refund();
	        refund.setId("2"+com.catic.mobilehos.utils.Common.getUUID());
	    	refund.setTradeNo(tradeNo);
	    	refund.setRefundNo(refundNo);
	    	refund.setRefundFee(Double.parseDouble(amount));
	    	refund.setRefundStatus(0);//待退款
		    refund.setRefundType(0);//自动退款 
		    refundBiz.save(refund);
		    
		    PayOrder payOrder1=new PayOrder();
		    payOrder1.setOut_trade_no(tradeNo);
		    payOrder1.setPayStatus(2);//退款中
		      //更新订单表
		    if(payOrderBiz.saveHisTransId(payOrder1)){
		        System.out.println("执行第三方退款成功-更新订单表");
	    	    log.warn("【执行第三方退款成功-更新订单表】商户订单号："+tradeNo);
	    	}
		    
	    }		*/
	
/*	    if(flag){
	    	payLog.setItemName("微信退款");
			payLog.setOut_trade_no(tradeNo);
			payLog.setStatus(0);
			payLogBiz.saveLog(payLog);
	    	 	
	    	po=new PayOrder();
	    	po.setOut_trade_no(tradeNo);
	    	po.setRefundNo(refundNo);
	    	po.setRefundFee(refundFee);
	    	po.setRefundType(1);
	    	po.setRefundReason(refundReason);
	    	po.setOperatorId((Integer)request.getSession().getAttribute("userId")+"");
	    	po.setOperatorName((String)request.getSession().getAttribute("userName"));
	    	po.setPayStatus(3);//已退款
	    if(	payOrderBiz.saveRefund(po)){
	    	return "refund";
	    }
		}else{
		    payLog.setItemName("微信退款");
			payLog.setStatus(1);
			payLogBiz.saveLog(payLog);
		}*/
		} catch (Exception e) {		
		    payLog.setItemName("退款过程");
			payLog.setOut_trade_no(tradeNo);
			payLog.setStatus(1);
			try {
				payLogBiz.saveLog(payLog);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		}
		jsonObj(result.toJson());
	//	return "refund";
	}
	
/**
 * 	跳到退款界面
 * @return
 */
	public String toRefund(){
		return "toRefund";
		
	}
	
	/**
	 * 导出退款订单
	 * @return
	 */
	public String exportRefund() {
		try {
			rf = new Refund();
			// 原交易单号
			rf.setTradeNo(tradeNo);
			// 退款单号
			rf.setRefundNo(refundNo);
			if (StringUtils.isNotBlank(pattern)) {
				// 退款方
				rf.setPattern(Integer.parseInt(pattern));
			}
			if (StringUtils.isNotBlank(refundType)) {
				// 退款方式
				rf.setRefundType(Integer.parseInt(refundType));
			}
			// 操作人
			rf.setOperatorName(operatorName);
			// 退款申请时间-开始时间
//			rf.setSq_startdate(sq_startdate);
			// 退款申请时间-结束时间
//			rf.setSq_enddate(sq_enddate);
			// 退款完成时间-开始时间
			rf.setTk_startdate(tk_startdate);
			// 退款完成时间-结束时间
			rf.setTk_enddate(tk_enddate);
			if (StringUtils.isNotBlank(refundStatus)) {
				// 退款状态
				rf.setRefundStatus(Integer.parseInt(refundStatus));
			}
		    rf.setRefundId(refundId);//第三方退款流水号
    	    rf.setDistrictId(districtId);//院区id
    	    rf.setAccount(account);//资金账户编号
			rfList=refundBiz.findAll(rf, null);
	        
	        if(rfList!=null&&rfList.size()>0){	
        		page=new Page(1, rfList.get(0).getCount(), rfList.get(0).getCount());	
        		rfList=refundBiz.findAll(rf, page);
        	}
			List<List<Object>> list = refundBiz.getExportRefundDataList(rfList);
			String[] headers={"退款单号","第三方退款单号", "原交易单号", "院区","资金账户", "退款方", "退款方式",
					"退款金额(元)", "退款状态", "操作人", "退款时间"};
			String fileName = "退款订单";
			ExportExcel.excelOut(headers, list, fileName, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getRefundNo() {
		return refundNo;
	}
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	public Refund getRf() {
		return rf;
	}

	public void setRf(Refund rf) {
		this.rf = rf;
	}

	public List<Refund> getRfList() {
		return rfList;
	}

	public void setRfList(List<Refund> rfList) {
		this.rfList = rfList;
	}

	public String getRefundType() {
		return refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}


	public List<PayOrder> getPolist() {
		return polist;
	}


	public void setPolist(List<PayOrder> polist) {
		this.polist = polist;
	}


	public PayOrder getPo() {
		return po;
	}


	public void setPo(PayOrder po) {
		this.po = po;
	}
	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getRefundReason() {
		return refundReason;
	}


	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}


	/*public String getSq_startdate() {
		return sq_startdate;
	}


	public void setSq_startdate(String sq_startdate) {
		this.sq_startdate = sq_startdate;
	}*/

/*
	public String getSq_enddate() {
		return sq_enddate;
	}


	public void setSq_enddate(String sq_enddate) {
		this.sq_enddate = sq_enddate;
	}*/


	public String getTk_startdate() {
		return tk_startdate;
	}


	public void setTk_startdate(String tk_startdate) {
		this.tk_startdate = tk_startdate;
	}


	public String getTk_enddate() {
		return tk_enddate;
	}


	public void setTk_enddate(String tk_enddate) {
		this.tk_enddate = tk_enddate;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getOperatorName() {
		return operatorName;
	}


	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}


	public String getRefundStatus() {
		return refundStatus;
	}


	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public String getCheckDetailsId() {
		return checkDetailsId;
	}


	public void setCheckDetailsId(String checkDetailsId) {
		this.checkDetailsId = checkDetailsId;
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
