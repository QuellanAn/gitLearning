package com.catic.mobilehos.pay.action;

import java.util.List;


import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.entity.PayScene;
import com.catic.mobilehos.utils.Page;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class PaySceneAction extends BaseAction{
	
	private String code;
	private String name;
	private String status;
	private PayScene ps;
	private List<PayScene> pslist;
	
	public String findAllInfo(){
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo="1";
			}
			pslist=paySceneBiz.findAll(null);
			if(pslist!=null&&pslist.size()>0){
				page=new Page(Integer.parseInt(pageNo), 10, pslist.get(0).getCount());
				pslist=paySceneBiz.findAll(page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String toUpdate(){
		try {
			if(StringUtils.isNotBlank(code)){
				ps=paySceneBiz.findByCode(code);
			}else{
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "toUpdate";
	}
	
	public String Update(){
		try {
			ps=new PayScene();
			if(StringUtils.isNotBlank(code)){
				ps.setSCode(code);
			}
			if(StringUtils.isNotBlank(name)){
				ps.setSName(name);
			}
			if(StringUtils.isNotBlank(status)){
				ps.setSStatus(status);
			}
			paySceneBiz.modify(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Update";
	}
	
/**
 * 查询所有	
 */
	public void findAllJson(){
		try {
			pslist=paySceneBiz.findAll();
			 if(pslist!=null&&pslist.size()>0){
					jsonArray=JSONArray.fromObject(pslist);
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

	public PayScene getPs() {
		return ps;
	}

	public void setPs(PayScene ps) {
		this.ps = ps;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
