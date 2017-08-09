package com.catic.mobilehos.pay.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.entity.CheckSource;
import com.catic.mobilehos.pay.entity.PayType;
import com.catic.mobilehos.pay.util.Constant;
import com.catic.mobilehos.utils.Page;
import com.google.gson.JsonObject;
/**
 * 对账来源
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class CheckSourceAction extends BaseAction{

	private CheckSource checkSource;
	private List<CheckSource> checkList;
	private String code;
	
	private String status;
	
	public String findAll(){
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo="1";
			}
			checkList=checkSourceBiz.findAll(null);
			if(checkList!=null&&checkList.size()>0){
				page=new Page(Integer.parseInt(pageNo),Constant.DEFAULT_PAGE_SIZE, checkList.get(0).getCount());
				checkList=checkSourceBiz.findAll(page);
			}					
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return SUCCESS;
		}
	}
	
	
	public void find(){
		try {
			checkList= checkSourceBiz.findByJson();
			if(checkList!=null&&checkList.size()>0){
				jsonArray=JSONArray.fromObject(checkList);
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
	
	
	/**
	 * 跳转到编辑界面
	 * @return
	 */
	public String toModify(){
		try {
			checkSource=checkSourceBiz.findByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return "toModify";
		}
	}
	
	public void toStatus(){
		try {
			checkSource=new CheckSource();
			if(StringUtils.isNotBlank(code)){
				checkSource.setCheckCode(code);
			}
			if(StringUtils.isNotBlank(status)){
				checkSource.setStatus(Integer.parseInt(status));
			}
			int count =	checkSourceBiz.modify(checkSource);
			Map map=new HashMap();
			map.put("shu", count);
			JSONObject json=JSONObject.fromObject(map);
			this.jsonObj(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 编辑
	 * @return
	 */
	public String modify(){
		try {
			checkSourceBiz.modify(checkSource);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return "main";
		}
	}
	
	public void exportFile(){
		String payType=request.getParameter("payType");
		String time=request.getParameter("createTime");
	}
	
	public CheckSource getCheckSource() {
		return checkSource;
	}
	public void setCheckSource(CheckSource checkSource) {
		this.checkSource = checkSource;
	}
	public List<CheckSource> getCheckList() {
		return checkList;
	}
	public void setCheckList(List<CheckSource> checkList) {
		this.checkList = checkList;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
