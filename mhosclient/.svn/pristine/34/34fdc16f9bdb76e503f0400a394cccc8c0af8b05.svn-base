package com.catic.mobilehos.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 封装服务的返回结果,返回结果可以是成功的数据
 * 也可以是错误信息
 * @author xieweipeng
 *
 */
public abstract class ServiceResult {
	
	public static final String ERROR_CODE = "errorcode";
	
	public static final String MSG = "msg";
	
	public static ServiceResult getSucInstance(){
		return new ServiceResult(){

			@Override
			public boolean isSuccess() {
				return true;
			}
			
		};
	}
	
	/**
	 * 得到一个封装错误信息的服务结果对象
	 * @param errorCode
	 * @param msg
	 * @return
	 */
	public static ServiceResult getFailedInstance(final String errorCode, final String msg){
		
		return new ServiceResult(){

			@Override
			public JSONObject getFailedInfo() {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(ERROR_CODE, errorCode);
				jsonObj.put(MSG, msg);
				return jsonObj;
			}

			@Override
			public boolean isSuccess() {
				return false;
			}
			
		};
		
		
	}
		
	/**
	 * 得到包含错误信息的JSON对象
	 * @return
	 */
	public JSONObject getFailedInfo(){
		return null;
	}
	
	public String getErrMsg(){
		if (getFailedInfo() != null){
			return getFailedInfo().getString(MSG);
		}else{
			return null;
		}
	}
	
	public String getErrCode(){
		if (getFailedInfo() != null){
			return getFailedInfo().getString(ERROR_CODE);
		}else{
			return null;
		}
	}

	
	/**
	 * 得到包含单条成功结果数据的JSON对象, 
	 * 如果执行成功但不无数据返回也可以为空
	 * @return
	 */
	public JSONObject getJSON(){
		return null;
	}

	/**
	 * 获得包含多条成功结果数据的JSONArray对象,
	 * 如果执行成功但不无数据返回也可以为空
	 * @return
	 */
	public JSONArray getJSONArray(){
		return null;
	}

	/**
	 * 获得非JSON的数据对象，
	 * 如果执行成功但不无数据返回也可以为空
	 * @return
	 */
	public Object getObject() {
		return null;
	}
	
	/**
	 * 服务是否执行成功
	 * @return 成功返回true, 失败返回false
	 */
	public abstract boolean isSuccess();

	
	

}
