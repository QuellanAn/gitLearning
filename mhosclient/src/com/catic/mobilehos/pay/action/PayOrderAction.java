package com.catic.mobilehos.pay.action;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.biz.IPayOrderBiz;
import com.catic.mobilehos.pay.entity.PayOrder;
import com.catic.mobilehos.pay.entity.TransFlow;
import com.catic.mobilehos.pay.util.DateUtils;
import com.catic.mobilehos.utils.ExportExcel;
import com.catic.mobilehos.utils.Page;


//订单
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class PayOrderAction extends BaseAction{

private List<PayOrder> polist;
private PayOrder po;
private List<TransFlow> tflist;
private TransFlow tf;

private String pageNo;
private Page page;

private String orderId;
private String out_trade_no;//订单号
private String body;
private String startdate;
private String enddate;
private String status;
private String pattern;
private String payType;
private String districtId;
private String orderCode;//交易流水号
private String hisTransId;
private String jyType;
private String payScene;
private String account;

private String cardNo;
private String refundOrder;
private String otherOrder;

private String flag="0";//0：跳转到详情界面  1:跳转到退款界面  


public String findAll(){
try {
	if(StringUtils.isBlank(pageNo)){
		pageNo = "1";
	}
	
	PayOrder payOrder=new PayOrder();
	payOrder.setStartdate(startdate);
	payOrder.setEnddate(enddate);

	if(StringUtils.isNotBlank(status)){
		payOrder.setPayStatus(Integer.parseInt(status));	
	}
	if(StringUtils.isNotBlank(pattern)){
		payOrder.setPattern(Integer.parseInt(pattern));
	}
	if(StringUtils.isNotBlank(payType)){
		payOrder.setPayType(Integer.parseInt(payType));
	}
	if(StringUtils.isNotBlank(districtId)){
		payOrder.setDistrictId(Integer.parseInt(districtId));
	}
	if(StringUtils.isNotBlank(body)){
		payOrder.setBody(body);	
	}
	if(StringUtils.isNotBlank(payScene)){
		payOrder.setPayScene(Integer.parseInt(payScene));
	}
	if(StringUtils.isNotBlank(account)){
		payOrder.setAssetAccount(account);
	}
	if(StringUtils.isNotBlank(cardNo)){
		payOrder.setCardNo(cardNo);
	}
	if(StringUtils.isNotBlank(out_trade_no)){
		payOrder.setOut_trade_no(out_trade_no);
	}
	if(StringUtils.isNotBlank(hisTransId)){
		payOrder.setHisTransId(hisTransId);
	}
	if(StringUtils.isNotBlank(orderCode)){
		payOrder.setOrderCode(orderCode);
	}
	polist=payOrderBiz.findAllByPL(payOrder,null);	

	page=new Page(Integer.parseInt(pageNo),10, this.polist.get(0).getCount());	
	polist=payOrderBiz.findAllByPL(payOrder,page);
/*	if(polist!=null&&polist.size()>0){
		for(int i=0;i<polist.size();i++){
			Boolean flag=false;	
			if(polist.get(i)!=null&&polist.get(i).getHisStatus()==0){
			if(polist.get(i).getPattern()==0){//微信支付				
				ScanPayQueryReqData scanPayQueryReqData = new ScanPayQueryReqData("", polist.get(i).getOut_trade_no());
				ScanPayQueryBusiness.ResultListener resultListener = new ScanPayQueryResultListener();
				ScanPayQueryResData scanPayQueryResData = WXPay.doScanPayQueryBusiness(scanPayQueryReqData, resultListener);
				if(scanPayQueryResData != null){
					//交易状态用来确认是否交易成功
					//SUCCESS—支付成功    REFUND—转入退款  NOTPAY—未支付  CLOSED—已关闭  USERPAYING--用户支付中  PAYERROR--支付失败(其他原因，如银行返回失败)
					String tradeState = scanPayQueryResData.getTrade_state();
					if(tradeState.equals("SUCCESS")){//支付成功
						polist.get(i).setOutStatus(1);					
						if(polist.get(i)!=null&&polist.get(i).getPayStatus()!=1){//不匹配则修改数据的支付状态
							flag=true;
						}
					}else{
						polist.get(i).setOutStatus(0);
					}
				}
			}else if(polist.get(i)!=null&&polist.get(i).getPattern()==1){//支付宝支付
		            String result=new AliPay().trade_query(polist.get(i).getOut_trade_no());          
		            if(result.equals("1")){
		            	polist.get(i).setOutStatus(0);
		            }else if(result.equals("2")){
		            	polist.get(i).setOutStatus(0);
		            }else if(result.equals("3")){
		            	polist.get(i).setOutStatus(0);
		            }else{
		            	polist.get(i).setOutStatus(1);
		            	if(polist.get(i).getPayStatus()!=1){
		            		flag=true;
		            	}
		            }
			}
			if(flag){
				payOrderBiz.alterPayStautus(1, polist.get(i).getOut_trade_no());	
			}	
			}
		}
	}*/

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}			
	return SUCCESS;
}
/**
 * gds
 * 查询所有的充值记录或者缴费记录
 * @return
 */
public String findAllPayOrRecharge (){
	String redirectType = null;
	try {
		if(StringUtils.isBlank(pageNo)){
			pageNo = "1";
		}
		redirectType = request.getParameter("redirectType");
		String patientName = request.getParameter("patientname");
		String cardNo = request.getParameter("cardNo");
		PayOrder payOrder=new PayOrder();
		payOrder.setStartdate(startdate);
		payOrder.setEnddate(enddate);
	
		if(StringUtils.isNotBlank(patientName)){
			payOrder.setPatientName(patientName);
		}
		if(StringUtils.isNotBlank(cardNo)){
			payOrder.setCardNo(cardNo);
		}
		if(StringUtils.isNotBlank(status)){
			payOrder.setPayStatus(Integer.parseInt(status));	
		}
		if(StringUtils.isNotBlank(pattern)){
			payOrder.setPattern(Integer.parseInt(pattern));
		}
		if(StringUtils.isNotBlank(payType)){
			payOrder.setPayType(Integer.parseInt(payType));
		}
		if(StringUtils.isNotBlank(districtId)){
			payOrder.setDistrictId(Integer.parseInt(districtId));
		}
		if(StringUtils.isNotBlank(body)){
			payOrder.setBody(body);	
		}
		if(StringUtils.isNotBlank(payScene)){
			payOrder.setPayScene(Integer.parseInt(payScene));
		}
		if(StringUtils.isNotBlank(out_trade_no)){
			payOrder.setOut_trade_no(out_trade_no);
		}
		if(StringUtils.isNotBlank(hisTransId)){
			payOrder.setHisTransId(hisTransId);
		}
		if(StringUtils.isNotBlank(orderCode)){
			payOrder.setOrderCode(orderCode);
		}
		//System.out.println(redirectType+"  "+patientName+"  "+payScene);
		polist=payOrderBiz.findAllPayOrRecharge(payOrder, null, redirectType);	
		page=new Page(Integer.parseInt(pageNo),10, this.polist.get(0).getCount());	
		polist=payOrderBiz.findAllPayOrRecharge(payOrder,page, redirectType);
		//System.out.println(polist.get(0).getPayScene());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}			
	if("0".equals(redirectType)){
		return "cz";
	}else{
		return "jf";
	}
}
/**
 * 查询充值或者缴费详情，0位充值，1位缴费
 * gds
 * @return
 */
public String findPayOrRechargeDetails(){
	String redirectType = null;
	try {
		redirectType = request.getParameter("redirectType");
		po=payOrderBiz.findByOrderId(orderId);
	} catch (Exception e) {
		
	}
	if("0".equals(redirectType)){
		return "czdetail";
	}else{
		return "jfdetail";
	}
	
}

/*
 * 查看订单详情
 */
public String findDetails(){
	try {
		po=payOrderBiz.findByOrderId(orderId);
//		if(po!=null){
//			unpaid=new UnpaidPO();
//			unpaid.setUnpaid_id(po.getUnpaid_id());
//			unpaid=unpaidBiz.findOne(unpaid);
//		}
	
	} catch (Exception e) {
		
	}
	if(flag.equals("1")){
		return "refund";
	}else{
		return "details";
	}
	
}

public String findAllByPlFlow(){
	if(StringUtils.isBlank(pageNo)){
		pageNo = "1";
	}
	TransFlow tflow=new TransFlow();
	tflow.setStartdate(startdate);
	tflow.setEnddate(enddate);

	if(StringUtils.isNotBlank(jyType)){
		tflow.setTransType(Integer.parseInt(jyType));	
	}
	if(StringUtils.isNotBlank(pattern)){
		tflow.setPattern(Integer.parseInt(pattern));
	}
	if(StringUtils.isNotBlank(payType)){
		tflow.setPayType(Integer.parseInt(payType));
	}
	if(StringUtils.isNotBlank(districtId)){
		tflow.setDistrictId(Integer.parseInt(districtId));
	}
	if(StringUtils.isNotBlank(payScene)){
		tflow.setPayScene(Integer.parseInt(payScene));
	}
	if(StringUtils.isNotBlank(account)){
		tflow.setAccount(account);
	}
	
	tflist=payOrderBiz.findAllByPlFlow(tflow,null);	
	page=new Page(Integer.parseInt(pageNo),10, this.tflist.get(0).getCount());	
	tflist=payOrderBiz.findAllByPlFlow(tflow,page);
	return "flow";
}

public void selectBytime(){
	try {
		PayOrder order=payOrderBiz.findByNearTime();
		double toatal=order.getTotalMoney();
		double count=0;
		double num=0;
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object> listMap=new ArrayList<Object>();
		for(int i=6;i>=0;i--){
			String timeExpire=DateUtils.getDateBefore(i);
			Map<String,Object> maps=new HashMap<String,Object>();
			po=payOrderBiz.findByTimeExpire(timeExpire);
			count+=po.getTotalMoney();
			maps.put("name", timeExpire);
			maps.put("val", po.getTotalMoney());
			listMap.add(maps);
		}
		if(toatal>0){
			if(count>=toatal){
				num=((double)count-toatal)/toatal*100;
				order.setUpDown("上升");
			}else{
				num=((double)toatal-count)/toatal*100;
				order.setUpDown("下降");
			}
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
			String size = df.format(num);//返回的是String类型的
			order.setContrast(size+"%");
		}else{
			order.setUpDown("上升");
			order.setContrast("0%");
		}
		order.setTotalMoney(count);
		
		map.put("all", listMap);
		map.put("po",order);
		
		JSONObject json=JSONObject.fromObject(map);
		this.jsonObj(json.toString());
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

public void selectTotalCount(){
	try {
		PayOrder order=payOrderBiz.findByNearTime();
		Integer zongCount=order.getTotalCount();
		Integer totalCount=0;
		double num=0;
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object> listMap=new ArrayList<Object>();
		for(int i=6;i>=0;i--){
			String timeExpire=DateUtils.getDateBefore(i);
			Map<String,Object> maps=new HashMap<String,Object>();
			po=payOrderBiz.findByTimeExpire(timeExpire);
			totalCount+=po.getTotalCount();
			maps.put("name", timeExpire);
			maps.put("val", po.getTotalCount());
			listMap.add(maps);
		}
		if(zongCount>0){
			if(totalCount>=zongCount){
				num=((double)totalCount-zongCount)/zongCount*100;
				order.setUpDown("上升");
			}else{
				num=((double)zongCount-totalCount)/zongCount*100;
				order.setUpDown("下降");
			}
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
			String size = df.format(num);//返回的是String类型的
			order.setContrast(size+"%");
		}else{
			order.setUpDown(" 上升 ");
			order.setContrast("0%");
		}
		
		order.setTotalCount(totalCount);
		
		map.put("all", listMap);
		map.put("po",order);
		
		JSONObject json=JSONObject.fromObject(map);
		this.jsonObj(json.toString());
	}catch (Exception e) {
		e.printStackTrace();
	}
}

public void selectAvgMoney(){
	try{
		PayOrder order=payOrderBiz.findByNearTime();
		double junMoney=order.getAvgMoney();
		double totalMoney=0;
		Integer totalCount=0;
		double avgMoney=0;
		double num=0;
		//int len=0;
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object> listMap=new ArrayList<Object>();
		for(int i=6;i>=0;i--){
			String timeExpire=DateUtils.getDateBefore(i);
			Map<String,Object> maps=new HashMap<String,Object>();
			po=payOrderBiz.findByTimeExpire(timeExpire);
			totalMoney+=po.getTotalMoney();
			totalCount+=po.getTotalCount();
			if(po.getAvgMoney()>0){
				avgMoney+=po.getAvgMoney();
				//len++;
			}
			maps.put("name", timeExpire);
			maps.put("val", po.getAvgMoney());
			listMap.add(maps);
		}
		if(junMoney>0){
			if((totalMoney/totalCount)>=junMoney){
				num=((totalMoney/totalCount)-junMoney)/junMoney*100;
				order.setUpDown("上升");
			}else{
				num=(junMoney-totalMoney/totalCount)/junMoney*100;
				order.setUpDown("下降");
			}
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
			String size = df.format(num);//返回的是String类型的
			order.setContrast(size+"%");
		}else{
			order.setUpDown("上升");
			order.setContrast("0%");
		}
		if(totalMoney/totalCount>0){
//			double avge=(double)(totalMoney)/totalCount;
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
//			avgMoney=avgMoney/7;
			String avgMoneys = df.format(totalMoney/totalCount);
			order.setAvgMoney(Double.parseDouble(avgMoneys));
		}else{
			order.setAvgMoney(0);
		}
				
		map.put("all", listMap);
		map.put("po",order);
		
		JSONObject json=JSONObject.fromObject(map);
		this.jsonObj(json.toString());
	}catch (Exception e) {
		e.printStackTrace();
	}
}

public void selectByTjfx(){
	String startTime=request.getParameter("startTime");
	String endTime=request.getParameter("endTime");
	if(StringUtils.isNotBlank(startTime) ||  StringUtils.isNotBlank(endTime)){
		
		po.setStime(startTime);
		po.setEtime(endTime);
	}
	String xiangmu=request.getParameter("xiangmu");
	Map<String, Object> maps=new HashMap<String, Object>();
	List<Map<String, Object>> listMap=new ArrayList<Map<String, Object>>();
	List<PayOrder> list=payOrderBiz.findByStatistical(po);
	for(int i=0;i<list.size();i++){
		po=list.get(i);
		Map<String, Object> map=new HashMap<String, Object>();
		if(xiangmu.equals("1")){
			map.put("value", po.getTotalMoney());
			map.put("name", po.getAnalysisType());
		}else if(xiangmu.equals("2")){
			map.put("value", po.getTotalCount());
			map.put("name", po.getAnalysisType());
		}
		listMap.add(map);
	}
	maps.put("view", listMap);
	JSONObject json=JSONObject.fromObject(maps);
	this.jsonObj(json.toString());
}

public void selectOfStatisByAll(){
	DecimalFormat df = new DecimalFormat("0.00");
	PayOrder pay=new PayOrder();
	String timeType=request.getParameter("timeType");
	if(StringUtils.isNotBlank(timeType)){
		pay.setTimeType(timeType);
	}
	if(StringUtils.isNotBlank(pattern)){
		pay.setPattern(Integer.parseInt(pattern));
	}
	if(StringUtils.isNotBlank(payType)){
		pay.setPayScene(Integer.parseInt(payType));
	}
	if(StringUtils.isNotBlank(account)){
		pay.setAssetAccount(account);
	}
	if(StringUtils.isNotBlank(districtId)){
		pay.setDistrictId(Integer.parseInt(districtId));
	}
	if(StringUtils.isNotBlank(body)){
		pay.setBody(body);	
	}
	double money=0;
	Integer count=0;
//	double avgMon=0;
	PayOrder payZ=new PayOrder();
	PayOrder payR=new PayOrder();
	Map<String, Object> map=new HashMap<String, Object>();
	List<Object> listMapZe=new ArrayList<Object>();
	List<Object> listMapZb=new ArrayList<Object>();
	List<Object> listMapRj=new ArrayList<Object>();
	if(StringUtils.isNotBlank(startdate) && StringUtils.isNotBlank(enddate) && StringUtils.isNotBlank(timeType)){
		if(timeType.equals("1")){
			int day=DateUtils.getDayCha(enddate,startdate);
			for(int i=day;i>=0;i--){
				String timeExpire=DateUtils.getDateBefores(enddate,i);
				Map<String,Object> mapZe=new HashMap<String,Object>();
				Map<String,Object> mapZb=new HashMap<String,Object>();
				Map<String,Object> mapRj=new HashMap<String,Object>();
				pay.setStime(timeExpire);
				if(status.equals("1")){
					payZ=payOrderBiz.selectOfStatisticalByPay(pay);
					payR=payOrderBiz.selectOfStatisticalByRefund(pay);
					money+=payZ.getTotalMoney()-payR.getTotalMoney();
					mapZe.put("val", df.format(payZ.getTotalMoney()+payR.getTotalMoney()));
					count+=payZ.getTotalCount()-payR.getTotalCount();
					mapZb.put("val", payZ.getTotalCount()+payR.getTotalCount());
//					avgMon+=payZ.getAvgMoney()-payR.getAvgMoney();
					mapRj.put("val", df.format(payZ.getAvgMoney()+payR.getAvgMoney()));
				}else if(status.equals("2")){
					po=payOrderBiz.selectOfStatisticalByPay(pay);
				}else if(status.equals("3")){
					po=payOrderBiz.selectOfStatisticalByRefund(pay);
				}
				if(po!=null && !po.equals("")){
					money+=po.getTotalMoney();
					mapZe.put("val", df.format(po.getTotalMoney()));
					count+=po.getTotalCount();
					mapZb.put("val", po.getTotalCount());
					mapRj.put("val", df.format(po.getAvgMoney()));
				}
				mapZe.put("name", timeExpire);
				mapZb.put("name", timeExpire);
				mapRj.put("name", timeExpire);
				listMapZe.add(mapZe);
				listMapZb.add(mapZb);
				listMapRj.add(mapRj);
			}
		}else if(timeType.equals("2")){
			String endmonth=request.getParameter("endmonth");
			String startmonth=request.getParameter("startmonth");
			int month=DateUtils.getMonthCha(endmonth,startmonth);
			for(int i=month;i>=0;i--){
				String monthVal=DateUtils.getMonthByBefore(endmonth,i);
				Map<String,Object> mapZe=new HashMap<String,Object>();
				Map<String,Object> mapZb=new HashMap<String,Object>();
				Map<String,Object> mapRj=new HashMap<String,Object>();
				pay.setStime(monthVal);
				if(status.equals("1")){
					payZ=payOrderBiz.selectOfStatisticalByPay(pay);
					payR=payOrderBiz.selectOfStatisticalByRefund(pay);
					money+=payZ.getTotalMoney()-payR.getTotalMoney();
					mapZe.put("val", df.format(payZ.getTotalMoney()-payR.getTotalMoney()));
					count+=payZ.getTotalCount()+payR.getTotalCount();
					mapZb.put("val", payZ.getTotalCount()+payR.getTotalCount());
//					avgMon+=payZ.getAvgMoney()-payR.getAvgMoney();
					mapRj.put("val", df.format(payZ.getAvgMoney()+payR.getAvgMoney()));
				}else if(status.equals("2")){
					po=payOrderBiz.selectOfStatisticalByPay(pay);
				}else if(status.equals("3")){
					po=payOrderBiz.selectOfStatisticalByRefund(pay);
				}
				if(po!=null && !po.equals("")){
					money+=po.getTotalMoney();
					mapZe.put("val", df.format(po.getTotalMoney()));
					count+=po.getTotalCount();
					mapZb.put("val", po.getTotalCount());
					mapRj.put("val", df.format(po.getAvgMoney()));
				}
				
				mapZe.put("name", monthVal);
				mapZb.put("name", monthVal);
				mapRj.put("name", monthVal);
				listMapZe.add(mapZe);
				listMapZb.add(mapZb);
				listMapRj.add(mapRj);
			}
		}
	}
	map.put("listMapZe", listMapZe);
	map.put("listMapZb", listMapZb);
	map.put("listMapRj", listMapRj);
	map.put("count",count);
	map.put("money",df.format(money));
	if(count>0){
		double avge=money/count;
		//格式化小数，不足的补0
		String size = df.format(avge);
		map.put("avgMon",size);
	}else{
		map.put("avgMon",0);
	}
	
	JSONObject json=JSONObject.fromObject(map);
	this.jsonObj(json.toString());
}

public String selectListByStatistical() throws UnsupportedEncodingException{
	DecimalFormat df = new DecimalFormat("0.00");  
	if(StringUtils.isBlank(pageNo)){
		pageNo = "1";
	}
	PayOrder payOrder=new PayOrder();
	PayOrder pay=new PayOrder();
	PayOrder payZ=new PayOrder();
	PayOrder payR=new PayOrder();
	if(StringUtils.isNotBlank(status)){
		payOrder.setPayStatus(Integer.parseInt(status));	
	}
	if(StringUtils.isNotBlank(pattern)){
		payOrder.setPattern(Integer.parseInt(pattern));
	}
	if(StringUtils.isNotBlank(payType)){
		payOrder.setPayScene(Integer.parseInt(payType));
	}
	if(StringUtils.isNotBlank(account)){
		payOrder.setAssetAccount(account);
	}
	if(StringUtils.isNotBlank(districtId)){
		payOrder.setDistrictId(Integer.parseInt(districtId));
	}
	if(StringUtils.isNotBlank(body)){
		payOrder.setBody(new String(body.getBytes("ISO8859-1"),"UTF-8"));	
	}
	String timeType=request.getParameter("timeType");
	if(StringUtils.isNotBlank(timeType)){
		payOrder.setTimeType(timeType);
	}
	List<PayOrder> payList = new ArrayList<PayOrder>();
	if(StringUtils.isNotBlank(startdate) && StringUtils.isNotBlank(enddate) && StringUtils.isNotBlank(timeType)){
		if(timeType.equals("1")){
			int day=DateUtils.getDayCha(enddate,startdate);
			for(int i=day;i>=0;i--){
				String timeExpire=DateUtils.getDateBefores(enddate,i);
				payOrder.setStime(timeExpire);
				if(status.equals("1")){
					pay=new PayOrder();
					payZ=payOrderBiz.selectOfStatisticalByPay(payOrder);
					payR=payOrderBiz.selectOfStatisticalByRefund(payOrder);
					pay.setTotalCount(payZ.getTotalCount()-payR.getTotalCount());
					pay.setTotalMoney(Double.valueOf(df.format(payZ.getTotalMoney()-payR.getTotalMoney())));
					pay.setAvgMoney(Double.valueOf(df.format(payZ.getAvgMoney()-payR.getAvgMoney())));
				}else if(status.equals("2")){
					pay=payOrderBiz.selectOfStatisticalByPay(payOrder);
				}else if(status.equals("3")){
					pay=payOrderBiz.selectOfStatisticalByRefund(payOrder);
				}
				pay.setStime(timeExpire);
				payList.add(pay);
			}
		}else if(timeType.equals("2")){
			String endmonth=request.getParameter("endmonth");
			String startmonth=request.getParameter("startmonth");
			int month=DateUtils.getMonthCha(endmonth,startmonth);
			for(int i=month;i>=0;i--){
				String monthVal=DateUtils.getMonthByBefore(endmonth,i);
				payOrder.setStime(monthVal);
				if(status.equals("1")){
					pay=new PayOrder();
					payZ=payOrderBiz.selectOfStatisticalByPay(payOrder);
					payR=payOrderBiz.selectOfStatisticalByRefund(payOrder);
					pay.setTotalCount(payZ.getTotalCount()-payR.getTotalCount());
					pay.setTotalMoney(Double.valueOf(df.format(payZ.getTotalMoney()-payR.getTotalMoney())));
					pay.setAvgMoney(Double.valueOf(df.format(payZ.getAvgMoney()-payR.getAvgMoney())));
				}else if(status.equals("2")){
					pay=payOrderBiz.selectOfStatisticalByPay(payOrder);
				}else if(status.equals("3")){
					pay=payOrderBiz.selectOfStatisticalByRefund(payOrder);
				}
				pay.setStime(monthVal);
				payList.add(pay);
			}
		}
	}
	
	int dataSize = 10;
	int totalSize = payList.size();
	page=new Page(Integer.parseInt(pageNo),10, totalSize);
	polist=new ArrayList<PayOrder>();
	for ( int i = (Integer.parseInt(pageNo)-1)*dataSize; i < (((Integer.parseInt(pageNo) -1)*dataSize) + dataSize > 
		totalSize ? totalSize:((Integer.parseInt(pageNo) -1)*dataSize) +dataSize);i++) {
		polist.add(payList.get(i));
	}
	request.getSession().setAttribute("polist", polist);
	if(status.equals("1")){
		return "allList";
	}else if(status.equals("2")){
		return "payList";
	}else{
		return "refundList";
	}
}

public void exportCheck(){
	List<PayOrder> payOrder=(List<PayOrder>) request.getSession().getAttribute("polist");
		List<List<Object>> listExport=new ArrayList<List<Object>>();
		String fileName="";
		String[] headers=new String[4];
		headers[0]="订单日期";
		if(status.equals("1")){
			fileName="实际数据";
			headers[1]="实际总收入(元)";
			headers[2]="实际总笔数(笔)";
			headers[3]="人均金额(元)";
		}else if(status.equals("2")){
			fileName="交易数据";
			headers[1]="交易总金额(元)";
			headers[2]="交易总笔数(笔)";
			headers[3]="人均交易金额(元)";
		}else if(status.equals("3")){
			fileName="退款数据";
			headers[1]="退款总金额(元)";
			headers[2]="退款总笔数(笔)";
			headers[3]="人均退款金额(元)";
		}
		for (PayOrder pOrder : payOrder) {
			List<Object> listOb=new ArrayList<Object>();
			listOb.add(pOrder.getStime());
			listOb.add(pOrder.getTotalMoney());
			listOb.add(pOrder.getTotalCount());
			listOb.add(pOrder.getAvgMoney());
			listExport.add(listOb);
		}
		ExportExcel.excelOut(headers, listExport, fileName, request, response);
}

	/**
	 * 导出交易流水
	 * @return 
	 * @author 朱伟权
	 */
	public String exportTransflow(){
		PayOrder payOrder=new PayOrder();
		payOrder.setStartdate(startdate);
		payOrder.setEnddate(enddate);
	
		if(StringUtils.isNotBlank(status)){
			payOrder.setPayStatus(Integer.parseInt(status));	
		}
		if(StringUtils.isNotBlank(pattern)){
			payOrder.setPattern(Integer.parseInt(pattern));
		}
		if(StringUtils.isNotBlank(payType)){
			payOrder.setPayType(Integer.parseInt(payType));
		}
		if(StringUtils.isNotBlank(districtId)){
			payOrder.setDistrictId(Integer.parseInt(districtId));
		}
		if(StringUtils.isNotBlank(body)){
			payOrder.setBody(body);	
		}
		if(StringUtils.isNotBlank(jyType)){
			payOrder.setPayStatus(Integer.parseInt(jyType));	
		}
		
		if(StringUtils.isNotBlank(out_trade_no)){
			payOrder.setOut_trade_no(out_trade_no);
		}
		if(StringUtils.isNotBlank(hisTransId)){
			payOrder.setHisTransId(hisTransId);
		}
		if(StringUtils.isNotBlank(orderCode)){
			payOrder.setOrderCode(orderCode);
		}
		if(StringUtils.isNotBlank(refundOrder)){
			payOrder.setRefundOrder(refundOrder);
		}
		if(StringUtils.isNotBlank(otherOrder)){
			payOrder.setOtherOrder(otherOrder);
		}
		
		tflist = payOrderBiz.findAllByPlFlow(tf,null);	
		page = new Page(1, this.polist.get(0).getCount(), this.polist.get(0).getCount());	
		tflist = payOrderBiz.findAllByPlFlow(tf,page);
		
		List<List<Object>> list = payOrderBiz.getExportTransflowDataList(polist);
		String[] headers={"交易时间", "交易类型", "交易单号", "第三方交易流水号", "院区",
				"支付方式", "支付场景", "退款单号", "第三方退款流水单号", "病人姓名", "缴费项目", "交易金额(元)", "订单状态"};
		String fileName = "交易流水";
		ExportExcel.excelOut(headers, list, fileName, request, response);
		return "flow";
	}
	
	/**
	 * 导出交易订单
	 * @return 
	 * @author 朱伟权
	 * @throws UnsupportedEncodingException 
	 */
	public void exportPayOrder() throws UnsupportedEncodingException{
		if(StringUtils.isBlank(pageNo)){
			pageNo = "1";
		}	
		PayOrder payOrder=new PayOrder();
		payOrder.setStartdate(startdate);
		payOrder.setEnddate(enddate);

		if(StringUtils.isNotBlank(status)){
			payOrder.setPayStatus(Integer.parseInt(status));	
		}
		if(StringUtils.isNotBlank(pattern)){
			payOrder.setPattern(Integer.parseInt(pattern));
		}
		if(StringUtils.isNotBlank(payType)){
			payOrder.setPayType(Integer.parseInt(payType));
		}
		if(StringUtils.isNotBlank(districtId)){
			payOrder.setDistrictId(Integer.parseInt(districtId));
		}
		if(StringUtils.isNotBlank(body)){
			payOrder.setBody(new String(body.getBytes("ISO8859-1"),"UTF-8"));	
		}
		if(StringUtils.isNotBlank(payScene)){
			payOrder.setPayScene(Integer.parseInt(payScene));
		}
		if(StringUtils.isNotBlank(account)){
			payOrder.setAssetAccount(account);
		}
		if(StringUtils.isNotBlank(cardNo)){
			payOrder.setCardNo(cardNo);
		}
		if(StringUtils.isNotBlank(out_trade_no)){
			payOrder.setOut_trade_no(out_trade_no);
		}
		if(StringUtils.isNotBlank(hisTransId)){
			payOrder.setHisTransId(hisTransId);
		}
		if(StringUtils.isNotBlank(orderCode)){
			payOrder.setOrderCode(orderCode);
		}
		polist=payOrderBiz.findAllByPL(payOrder,null);	
		page=new Page(Integer.parseInt(pageNo), this.polist.get(0).getCount(), this.polist.get(0).getCount());	
		polist=payOrderBiz.findAllByPL(payOrder,page);
//		polist = payOrderBiz.findAllByPL(payOrder,null);	
//		page = new Page(1, this.polist.get(0).getCount(), this.polist.get(0).getCount());	
//		polist = payOrderBiz.findAllByPL(payOrder,page);
		List<List<Object>> list = payOrderBiz.getExportPayOrderDataList(polist);
		String[] headers={"交易时间","交易单号", "第三方交易流水号", "院区","资金账户","支付场景",
				"支付方式","就诊卡号","就诊人", "缴费项目", "交易金额(元)", "交易状态", "交易完成时间"};
		String fileName = "交易订单";
		ExportExcel.excelOut(headers, list, fileName, request, response);
		//return SUCCESS;
	}

public List<PayOrder> getPolist() {
	return polist;
}
public void setPolist(List<PayOrder> polist) {
	this.polist = polist;
}
public IPayOrderBiz getPayOrderBiz() {
	return payOrderBiz;
}
public void setPayOrderBiz(IPayOrderBiz payOrderBiz) {
	this.payOrderBiz = payOrderBiz;
}
public String getPageNo() {
	return pageNo;
}
public void setPageNo(String pageNo) {
	this.pageNo = pageNo;
}
public Page getPage() {
	return page;
}
public void setPage(Page page) {
	this.page = page;
}

public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
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
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getPattern() {
	return pattern;
}
public void setPattern(String pattern) {
	this.pattern = pattern;
}
public String getOrderId() {
	return orderId;
}

public void setOrderId(String orderId) {
	this.orderId = orderId;
}
public PayOrder getPo() {
	return po;
}
public void setPo(PayOrder po) {
	this.po = po;
}
public String getOrderCode() {
	return orderCode;
}
public void setOrderCode(String orderCode) {
	this.orderCode = orderCode;
}
public String getOut_trade_no() {
	return out_trade_no;
}
public void setOut_trade_no(String out_trade_no) {
	this.out_trade_no = out_trade_no;
}
public String getFlag() {
	return flag;
}
public void setFlag(String flag) {
	this.flag = flag;
}
public String getPayType() {
	return payType;
}
public void setPayType(String payType) {
	this.payType = payType;
}
public String getDistrictId() {
	return districtId;
}
public void setDistrictId(String districtId) {
	this.districtId = districtId;
}

public String getHisTransId() {
	return hisTransId;
}

public void setHisTransId(String hisTransId) {
	this.hisTransId = hisTransId;
}

public String getJyType() {
	return jyType;
}

public void setJyType(String jyType) {
	this.jyType = jyType;
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

public List<TransFlow> getTflist() {
	return tflist;
}

public void setTflist(List<TransFlow> tflist) {
	this.tflist = tflist;
}

public TransFlow getTf() {
	return tf;
}

public void setTf(TransFlow tf) {
	this.tf = tf;
}

public String getCardNo() {
	return cardNo;
}

public void setCardNo(String cardNo) {
	this.cardNo = cardNo;
}

public String getPayScene() {
	return payScene;
}

public void setPayScene(String payScene) {
	this.payScene = payScene;
}

public String getAccount() {
	return account;
}

public void setAccount(String account) {
	this.account = account;
}

}