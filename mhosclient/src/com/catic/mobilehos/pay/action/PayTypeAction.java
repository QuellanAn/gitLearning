package com.catic.mobilehos.pay.action;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.entity.PayType;
import com.catic.mobilehos.pay.util.Constant;
import com.catic.mobilehos.utils.Page;
/**
 * 支付方式
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class PayTypeAction extends BaseAction{

	private PayType payType;
	private List<PayType> payTypeList;
	private String ptCode;
	/**
	 * 查询所有json返回
	 */
	public void findAll(){
		try {
			payTypeList= payTypeBiz.findAll();
			if(payTypeList!=null&&payTypeList.size()>0){
				jsonArray=JSONArray.fromObject(payTypeList);
				result=resultBiz.getResult(resultBiz.SUCCESS, jsonArray.toString());
			}else{
				result=resultBiz.getResult(resultBiz.FAIL, jsonArray.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			result=resultBiz.getResult(resultBiz.SYSTEM_BUSY, jsonArray.toString());
		}finally{
			jsonObj(result.toJson());
		}
	}
	
	
	public String find(){
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo="1";
			}
			payTypeList=payTypeBiz.find(null);
			if(payTypeList!=null&&payTypeList.size()>0){
				page=new Page(Integer.parseInt(pageNo),Constant.DEFAULT_PAGE_SIZE, payTypeList.get(0).getCount());
				payTypeList= payTypeBiz.find(page);
			}					
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return SUCCESS;
		}
	}
	
	
	/**
	 * 跳转到编辑界面
	 * @return
	 */
	public String toModify(){
		try {
			payType=payTypeBiz.findByCode(ptCode);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return "toModify";
		}
	}
	
	
	/**
	 * 编辑
	 * @return
	 */
	public String modify(){
		try {
			payTypeBiz.modify(payType);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return "main";
		}
	}
	

	public PayType getPayType() {
		return payType;
	}


	public void setPayType(PayType payType) {
		this.payType = payType;
	}


	public List<PayType> getPayTypeList() {
		return payTypeList;
	}


	public void setPayTypeList(List<PayType> payTypeList) {
		this.payTypeList = payTypeList;
	}


	public String getPtCode() {
		return ptCode;
	}


	public void setPtCode(String ptCode) {
		this.ptCode = ptCode;
	}	
	
}
