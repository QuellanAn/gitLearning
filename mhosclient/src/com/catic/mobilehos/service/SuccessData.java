package com.catic.mobilehos.service;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

/**
 * 封装服务执行成功返回的数据
 * @author xieweipeng
 *
 */
public interface SuccessData<T> {
	
	T getObject();
	
	JSONObject getJSON();
	
	JSONArray getJSONArray(); 
	

}
