package com.catic.mobilehos.service;

import net.sf.json.JSONObject;

/**
 * 封装服务失败信息
 * @author xieweipeng
 *
 */
public class FailedInfo {
	
	private JSONObject jsonObj = new JSONObject();
	
	
	public FailedInfo(String errorCode, String msg){
		jsonObj.put("errorcode", errorCode);
		jsonObj.put("msg", msg);
	}
	
	public JSONObject getJSON(){
		return jsonObj;
	}
	
	
	
	

}
