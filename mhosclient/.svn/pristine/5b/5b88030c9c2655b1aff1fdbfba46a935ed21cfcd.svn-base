package com.catic.mobilehos.pay.entity;

import net.sf.json.JSONObject;

/**
 * 请求结果
 * @author YUXY
 */
public class Result {

	/**
	 * 错误代码
	 */
	private Integer code;

	/**
	 * 错误描述
	 */
	private String message;
	
	/**
	 * 返回响应集合
	 */
	private String data;


	/**
	 * json结果
	 */
	private String jsonResult;

	
	public Result() {}
	

	public Result(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	
	/**
	 * 转为JSON String
	 * 
	 * @return
	 */
	public String toJson() {

		// 减少转换
		/*if (this.jsonResult != null) {
			return jsonResult;
		}
		System.out.println(this.code +" "+ this.message+" "+this.getResponse());*/
		jsonResult = JSONObject.fromObject(this).toString();
		
		return jsonResult;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}
	
}
