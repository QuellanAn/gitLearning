package com.catic.mobilehos.pay.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.entity.CheckBill;
import com.catic.mobilehos.pay.entity.CheckBillDetails;
import com.catic.mobilehos.pay.entity.CheckRecord;
import com.catic.mobilehos.pay.entity.PayOrder;
import com.catic.mobilehos.utils.Page;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class CheckRecordAction extends BaseAction{
	
	private int recordType;
	private String startdate;
	private String enddate;
	private String transactionNum;
	private String orderCode;
	private String hisTransId;
	private CheckRecord checkRecode;
	private PayOrder po;
	private CheckBillDetails cb;
	private List<CheckRecord> crlist;
	
	private String account;
	
	public String findAll(){
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo="1";
			}
			CheckRecord check=new CheckRecord();
			check.setRecordType(recordType);
			if(StringUtils.isNotBlank(transactionNum)){
				check.setTransactionNum(transactionNum);
			}
			/*if(StringUtils.isNotBlank(orderCode)){
				check.setOrderCode(orderCode);
			}
			if(StringUtils.isNotBlank(hisTransId)){
				check.setHisTransId(hisTransId);
			}*/
			if(StringUtils.isNotBlank(startdate)){
				check.setStartdate(startdate);
			}
			if(StringUtils.isNotBlank(enddate)){
				check.setEnddate(enddate);
			}
			if(StringUtils.isNotBlank(account)){
				check.setAccount(account);
			}
			crlist=checkRecodeBiz.findAll(page, check);
			if(crlist!=null&&crlist.size()>0){
				page=new Page(Integer.parseInt(pageNo), 10, crlist.get(0).getCount());
				crlist=checkRecodeBiz.findAll(page, check);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(recordType==0){
			return "bill";
		}else{
			return "order";
		}
	}
	
	public String findInfoByOId(){
		if(StringUtils.isNotBlank(transactionNum)){
			po=payOrderBiz.findByTradeNo(transactionNum);
			cb=checkBillDetailsBiz.findByOId(transactionNum);
			return "detail";
		}else{
			return ERROR;
		}
	}

	public int getRecordType() {
		return recordType;
	}

	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}

	public CheckRecord getCheckRecode() {
		return checkRecode;
	}

	public void setCheckRecode(CheckRecord checkRecode) {
		this.checkRecode = checkRecode;
	}

	public List<CheckRecord> getCrlist() {
		return crlist;
	}

	public void setCrlist(List<CheckRecord> crlist) {
		this.crlist = crlist;
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

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getHisTransId() {
		return hisTransId;
	}

	public void setHisTransId(String hisTransId) {
		this.hisTransId = hisTransId;
	}

	public String getTransactionNum() {
		return transactionNum;
	}

	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}

	public PayOrder getPo() {
		return po;
	}

	public void setPo(PayOrder po) {
		this.po = po;
	}

	public CheckBillDetails getCb() {
		return cb;
	}

	public void setCb(CheckBillDetails cb) {
		this.cb = cb;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
