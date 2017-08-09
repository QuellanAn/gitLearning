package com.catic.mobilehos.pay.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.entity.PayScene;
import com.catic.mobilehos.pay.entity.PayTerminal;
import com.catic.mobilehos.pay.entity.YQInfo;
import com.catic.mobilehos.utils.Page;

/**
 * @author Administrator
 *支付通道
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class PayTerminalAction extends BaseAction{
	
	private String id;
	private PayTerminal payTer;
	private List<PayTerminal> ptlist;
	private List<PayScene> pslist;
	private String code;//终端编号
	public String findAllInfo(){
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo="1";
			}
			ptlist=payTerBiz.findAll(page, payTer);
			if(ptlist!=null&&ptlist.size()>0){
				page=new Page(Integer.parseInt(pageNo), 10, ptlist.get(0).getCount());
				ptlist=payTerBiz.findAll(page, payTer);
			}
			this.allYq();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String toSave(){
		try {
			findPayScene();
			allYq();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toSave";
	}
	
	public String toUpdate(){
		try {
			payTer=payTerBiz.findById(Integer.parseInt(id));
			pslist=paySceneBiz.findAll();
			allYq();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "toUpdate";
	}
	
	public String saveOrUpdate(){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			payTer.setCreateTime(this.toTimestamp(format.format(new Date())));
			if(StringUtils.isBlank(id)){
				payTerBiz.insert(payTer);
			}else{
				payTer.setId(Integer.parseInt(id));
				payTerBiz.modify(payTer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveUpdate";
	}
	
	public String delete(){
		try {
			payTerBiz.delete(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}
	
	public void allYq(){
		try {
			List<YQInfo> yqlist = yQConfigBiz.findAllyq();
			request.setAttribute("yqlist", yqlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findPayScene(){
		try {
			pslist=paySceneBiz.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void allYqJson(){
		try {
			List<YQInfo> yqlist = yQConfigBiz.findAllyq();
			JSONArray jarr = JSONArray.fromObject(yqlist);
			this.jsonArr(jarr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 校验编码是否存在
	 */
	public void checkCode(){
		try {
			payTer=payTerBiz.findByCode(code);
			if(payTer!=null){
			   result=resultBiz.getResult(resultBiz.SUCCESS);//存在
			}else{
			   result=resultBiz.getResult(resultBiz.FAIL); //不存在
			}
		} catch (Exception e) {
			e.printStackTrace();
			 result=resultBiz.getResult(resultBiz.SYSTEM_BUSY);
		}finally{
			jsonObj(result.toJson());
		}
	}
	
	
	public PayTerminal getPayTer() {
		return payTer;
	}
	public void setPayTer(PayTerminal payTer) {
		this.payTer = payTer;
	}
	public List<PayTerminal> getPtlist() {
		return ptlist;
	}
	public void setPtlist(List<PayTerminal> ptlist) {
		this.ptlist = ptlist;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<PayScene> getPslist() {
		return pslist;
	}
	public void setPslist(List<PayScene> pslist) {
		this.pslist = pslist;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	} 
	
}
