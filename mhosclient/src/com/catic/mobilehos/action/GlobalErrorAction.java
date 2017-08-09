package com.catic.mobilehos.action;

import net.sf.json.JSONObject;

import com.catic.mobilehos.ErrorCode;
import com.catic.mobilehos.service.ServiceResult;

public class GlobalErrorAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JSONObject errorResult = ServiceResult.getFailedInstance(ErrorCode.LS_ILLEGAL_SIGN, "无效请求！").getFailedInfo();

	public JSONObject getErrorResult() {
		return errorResult;
	}

	public void setErrorResult(JSONObject errorResult) {
		this.errorResult = errorResult;
	}
	
	public String execute(){
		return SUCCESS;
	}
	
	

}
