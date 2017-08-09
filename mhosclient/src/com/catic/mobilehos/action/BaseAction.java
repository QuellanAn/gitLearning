package com.catic.mobilehos.action;


import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.catic.mobilehos.ErrorCode;
import com.catic.mobilehos.GlobalConstants;
import com.catic.mobilehos.menu_role_authority.biz.IMenuBiz;
import com.catic.mobilehos.service.FailedInfo;
import com.catic.mobilehos.service.ServiceResult;
import com.catic.mobilehos.service.SuccessData;
import com.catic.mobilehos.utils.Constant;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 所有Action的基类
 * @author xieweipeng
 *
 */
public abstract class BaseAction extends ActionSupport 
	implements ServletRequestAware,ServletResponseAware,Constant{
	
	@Resource
	protected IMenuBiz menuBiz;
	
	private static final long serialVersionUID = 1L;

	protected static final Log log = LogFactory.getLog(BaseAction.class);
	
	protected HttpServletRequest request;
	
	protected HttpServletResponse response;
	
	/**
	 * 以JSON返回的失败信息
	 */
	protected JSONObject simpleJsonErrResult;
	
	/**
	 * 以JSON返回的结果
	 */
	protected JSONObject simpleJsonResult;
	
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	
	/**
	 * 将失败信息写入Response
	 * @param errCode
	 * @param msg
	 */
	protected void writeFailedResult(String errCode, String msg, String... target){
		writeFailedResult(this.response, errCode, msg, target);
	}
	
	
	
	/**
	 * 将失败信息写入Response
	 * @param failedInfo
	 */
	protected void writeFailedResult(FailedInfo failedInfo){
		writeFailedResult(response, failedInfo);
	}
	
	/**
	 * 将失败或成功信息写入Response
	 * @param result
	 */
	protected void writeResult(Object result){
		if (result != null){
			if (result instanceof SuccessData){
				this.writeSuccessData((SuccessData) result);
			}else{
				this.writeFailedResult((FailedInfo) result);
			}
		}
	}
	
	/**
	 * 将成功信息写入Response
	 * @param sd
	 */
	protected void writeSuccessData(SuccessData sd){
		writeSuccessData(response,sd);
	}
	
	/**
	 * 将成功或失败的结果通过Response返回给客户端
	 * @param sr
	 */
	protected void writeServiceReuslt(ServiceResult sr){
		writeServiceReuslt(response, sr);
	}
	
	
	protected void writeJSON(JSON json){
		writeJSON(response, json);
	}
	
	
	/**
	 * 将失败信息写入Response
	 * @param errCode
	 * @param msg
	 */
	public static void writeFailedResult(HttpServletResponse response, String errCode, String msg, String...target){
		try{
			JSONObject jsonObj = new JSONObject();
			jsonObj.put(ErrorCode.ERROR_CODE, errCode);
			jsonObj.put(ErrorCode.MSG, msg);
			if (target != null && target.length > 0){
				jsonObj.put(ErrorCode.ERROR_TARGET, target[0]);
			}
			writeJSON(response, jsonObj);
		}catch(Exception ex){
			log.error(null, ex);
		}
	}
	

	/**
	 * 将失败信息写入Response
	 * @param failedInfo
	 */
	public static void writeFailedResult(HttpServletResponse response, FailedInfo failedInfo){
		try{
			writeJSON(response, failedInfo.getJSON());
		}catch(Exception ex){
			log.error(null, ex);
		}
	}
	
	/**
	 * 将失败或成功信息写入Response
	 * @param result
	 */
	public static void writeResult(HttpServletResponse response, Object result){
		if (result != null){
			if (result instanceof SuccessData){
				writeSuccessData(response, (SuccessData) result);
			}else{
				writeFailedResult(response, (FailedInfo) result);
			}
		}
	}
	
	/**
	 * 将成功信息写入Response
	 * @param sd
	 */
	public static void writeSuccessData(HttpServletResponse response, SuccessData sd){
		try{
			if (sd.getJSON() != null){
				writeJSON(response, sd.getJSON());
			}else if (sd.getJSONArray() != null){
				writeJSON(response, sd.getJSONArray());
			}else{
				log.debug("writeSuccessData: SuccessData is empty!" );
			}
		}catch(Exception ex){
			log.error(null, ex);
		}
	}
	
	/**
	 * 将成功或失败的结果通过Response返回给客户端
	 * @param sr
	 */
	public static void writeServiceReuslt(HttpServletResponse response, ServiceResult sr){
		if (sr.isSuccess()){
			if (sr.getJSON() != null){
				writeJSON(response, sr.getJSON());
			}else if (sr.getJSONArray() != null){
				writeJSON(response, sr.getJSONArray());
			}else{
				log.warn("writeServiceReuslt: result is empty!");
			}
		}else{
			writeJSON(response, sr.getFailedInfo());
		}
	}
	
	
	public static void writeJSON(HttpServletResponse response, JSON json){
		try{
			String strJson = json.toString();
			log.debug("writeJSON: " + strJson);
			byte[] rst = strJson.getBytes(GlobalConstants.DEF_ENCODE);
			response.setContentType("text/html; charset=UTF-8");
			response.setContentLength(rst.length);
			response.getOutputStream().write(rst);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}catch(Exception ex){
			log.error(null, ex);
		}
	}

	public static void writeObject(HttpServletResponse response, Object obj){
		try{
			String strJson = obj.toString();
			log.debug("writeObject: " + strJson);
			byte[] rst = strJson.getBytes(GlobalConstants.DEF_ENCODE);
			response.setContentType("text/html; charset=UTF-8");
			response.setContentLength(rst.length);
			response.getOutputStream().write(rst);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}catch(Exception ex){
			log.error(null, ex);
		}
	}
	
	protected void writeObject(Object obj){
		writeObject(response, obj);
	}
	
	public static void writeString(HttpServletResponse response, String str){
		try{
			log.debug("writeString: " + str);
			byte[] rst = str.getBytes(GlobalConstants.DEF_ENCODE);
			response.setContentType("text/html; charset=UTF-8");
			response.setContentLength(rst.length);
			response.getOutputStream().write(rst);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}catch(Exception ex){
			log.error(null, ex);
		}
	}
	
	protected void writeString(String str){
		writeString(response, str);
	}
	
	public JSONObject getSimpleJsonErrResult() {
		return simpleJsonErrResult;
	}

	public void setSimpleJsonErrResult(JSONObject simpleJsonErrResult) {
		this.simpleJsonErrResult = simpleJsonErrResult;
	}
	
	
	public JSONObject getSimpleJsonResult() {
		return simpleJsonResult;
	}

	public void setSimpleJsonResult(JSONObject simpleJsonResult) {
		this.simpleJsonResult = simpleJsonResult;
	}

	protected java.sql.Date toSQLDate(String d){
		Date date;
		try {
			date = DateUtils.parseDate(d, GlobalConstants.DEF_DATEFORMAT);
			return new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			return null;
		}
		
	}
	
	protected java.sql.Timestamp toTimestamp(String d){
		Date date;
		try {
			date = DateUtils.parseDateStrictly(d, GlobalConstants.DEF_DATETIME_FMT
					, GlobalConstants.DEF_DATEFORMAT);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
			return null;
		}
	}
	
	protected java.sql.Time toSQLTime(String t){
		Date time;
		try {
			time = DateUtils.parseDate(t, GlobalConstants.DEF_TIMEFORMAT);
			return new java.sql.Time(time.getTime());
		} catch (ParseException e) {
			return null;
		}
	}
	

	@Override
	public void addActionError(String anErrorMessage) {
		super.addActionError(anErrorMessage);
		this.simpleJsonErrResult = ServiceResult.getFailedInstance("", anErrorMessage).getFailedInfo();
	}

	@Override
	public void addFieldError(String fieldName, String errorMessage) {
		super.addFieldError(fieldName, errorMessage);
		this.simpleJsonErrResult = ServiceResult.getFailedInstance(fieldName, errorMessage).getFailedInfo();
	}
	
	public String setServiceResultToJson(ServiceResult sr){
		if (sr.isSuccess()){
			this.simpleJsonResult = sr.getJSON();
			return SUCCESS;
		}else{
			this.simpleJsonErrResult = sr.getFailedInfo();
			return ERROR;
		}
	}
	
	
	
	
	

	
	
	
	

}
