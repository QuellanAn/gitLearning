package com.catic.mobilehos.action;  

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.Element;

import com.catic.mobilehos.common.ResultHandler;
import com.catic.mobilehos.common.ReturnCode;
import com.catic.mobilehos.pay.entity.PayTerminal;
import com.catic.mobilehos.po.SelfServiceLogPO;
import com.catic.mobilehos.po.SelfServiceVersionPO;
import com.catic.mobilehos.po.SelfserviceUpgradeHistoryPO;
import com.catic.mobilehos.service.SelfServiceLogService;
import com.catic.mobilehos.service.SelfServiceVersionService;
import com.catic.mobilehos.utils.CommonUtils;
import com.catic.mobilehos.utils.DateUtil;
import com.google.gson.JsonObject;

/**  
 * 类说明:提供给自助接口action
 * @author 朱伟权
 * 创建时间: 2017-7-21 上午10:35:28
 */
public class SelfServiceApiAction extends ResultHandler {
	
	private static final long serialVersionUID = 1L;
	// 升级包下载地址
	private static final String DOWNLOAD_URL = "http://112.74.85.246:8080/mhosclient/";
	
	@Resource
	private SelfServiceVersionService selfServiceVersionService;
	@Resource(name="selfServiceLogService")
	private SelfServiceLogService selfServiceLogService;
	
	private File log;// 上传日志文件
	private String logFileName;// 上传日志文件名

	/**
	 * 获取最新的版本信息接口
	 */
	/*public void getLatestVersion(){
		JsonObject result = new JsonObject();
		try{
			// 升级类型，0主程序升级，1守护程序升级
			String upgradeType = request.getParameter("upgradeType");
			// 终端设备号
			String deviceInfo = request.getParameter("deviceInfo");
			if(StringUtils.isBlank(upgradeType) || StringUtils.isBlank(deviceInfo)){
				writeResultJsonByCode(response, ReturnCode.PARAM_ERROR_CODE);
		    	return;
			}
			
			// 验证终端编号
			PayTerminal payTerminal = payTerBiz.findByCode(deviceInfo);
			if(payTerminal == null || "4".equals(payTerminal.getFacilityStatus())){
				// 终端被禁用
				writeResultJsonByCode(response, ReturnCode.TERMINAL_NOT_CODE);
		    	return;
			}
			
			// 设备类型
			String facilityType = payTerminal.getFacilityType();
			if(StringUtils.isBlank(facilityType)){
				result = getResultJson(ReturnCode.FAIL_CODE, "终端类型为空");
			}else{
				SelfServiceVersionPO latestVersion = selfServiceVersionService.findLatestVersion(upgradeType, facilityType);
				if(latestVersion == null){
					result = getResultJson(ReturnCode.FAIL_CODE, "没有最新版本");
				}else{
					if(StringUtils.isBlank(latestVersion.getFilePath())){
						result = getResultJson(ReturnCode.FAIL_CODE, "升级包下载地址为空");
					}else{
						result = getResultJson(ReturnCode.SUCCESS_CODE);
						result.addProperty("versionCode", CommonUtils.nullStringToEmpty(latestVersion.getVersionCode()));// 版本号
						result.addProperty("versionName", CommonUtils.nullStringToEmpty(latestVersion.getVersionName()));// 版本名称
						result.addProperty("content", CommonUtils.nullStringToEmpty(latestVersion.getContent()));// 更新内容
						result.addProperty("forceUpgrade", CommonUtils.nullStringToEmpty(latestVersion.getForceUpgrade()));// 是否强制更新
						result.addProperty("downloadLink", DOWNLOAD_URL + latestVersion.getFilePath());// 下载地址
					}
				}
			}
			jsonObj(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
			returnErrorJson(response);
		}
	}*/
	
	/**
	 * 获取最新的版本信息接口
	 */
	public void getLatestVersion(){
		try{
			Document document = null;
			
			Document doc = getRequestXml(request);
		    Element rootNode = doc.getRootElement();
		    // 升级类型，0主程序升级，1守护程序升级
		    String upgradeType = rootNode.elementText("upgradeType");
		    // 终端设备号
		    String deviceInfo = rootNode.elementText("deviceInfo");
			if(StringUtils.isBlank(upgradeType) || StringUtils.isBlank(deviceInfo)){
				writeResultByCode(response, ReturnCode.PARAM_ERROR_CODE);
		    	return;
			}
			
			// 验证终端编号
			PayTerminal payTerminal = payTerBiz.findByCode(deviceInfo);
			if(payTerminal == null || "4".equals(payTerminal.getFacilityStatus())){
				// 终端被禁用
				writeResultByCode(response, ReturnCode.TERMINAL_NOT_CODE);
		    	return;
			}
			
			// 设备类型
			String facilityType = payTerminal.getFacilityType();
			if(StringUtils.isBlank(facilityType)){
				document = getResultXml(ReturnCode.FAIL_CODE, "终端类型为空");
			}else{
				SelfServiceVersionPO latestVersion = selfServiceVersionService.findLatestVersion(upgradeType, facilityType);
				if(latestVersion == null){
					document = getResultXml(ReturnCode.FAIL_CODE, "没有最新版本");
				}else{
					if(StringUtils.isBlank(latestVersion.getFilePath())){
						document = getResultXml(ReturnCode.FAIL_CODE, "升级包下载地址为空");
					}else{
						document = getResultXml(ReturnCode.SUCCESS_CODE);
						Element rootElement = document.getRootElement();
					    rootElement.addElement("versionCode").setText(CommonUtils.nullStringToEmpty(latestVersion.getVersionCode()));// 版本号
					    rootElement.addElement("versionName").setText(CommonUtils.nullStringToEmpty(latestVersion.getVersionName()));// 版本名称
					    rootElement.addElement("content").setText(CommonUtils.nullStringToEmpty(latestVersion.getContent()));// 更新内容
					    rootElement.addElement("forceUpgrade").setText(CommonUtils.nullStringToEmpty(latestVersion.getForceUpgrade()));// 是否强制更新
					    rootElement.addElement("downloadLink").setText(DOWNLOAD_URL + latestVersion.getFilePath());// 下载地址
					}
				}
			}
			// 返回处理结果
			writeResult(response, document);
		} catch (Exception e) {
			e.printStackTrace();
			returnError(response);
		}
	}
	
	/**
	 * 上报版本升级信息接口
	 */
	/*public void reportUpgradeInfo(){
		JsonObject result = new JsonObject();
		try{
			// 升级类型，0主程序升级，1守护程序升级
			String upgradeType = request.getParameter("upgradeType");
			// 终端设备号
			String deviceInfo = request.getParameter("deviceInfo");
			// 升级后的版本号
			String versionCode = request.getParameter("versionCode");
			// 升级时间
			String upgradeTime = request.getParameter("upgradeTime");
			if(StringUtils.isBlank(upgradeType) || StringUtils.isBlank(deviceInfo)
					|| StringUtils.isBlank(versionCode) || StringUtils.isBlank(upgradeTime)){
				writeResultJsonByCode(response, ReturnCode.PARAM_ERROR_CODE);
				return;
			}
			// 验证版本号是否是数字
			boolean numeric = StringUtils.isNumeric(versionCode);
			if(!numeric){
				result = getResultJson(ReturnCode.PARAM_ERROR_CODE, "版本号格式有误");
				jsonObj(result.toString());
				return;
			}
			// 转换升级时间
			Date date = DateUtil.str2Date(upgradeTime, "yyyy-MM-dd HH:mm:ss");
			if(date == null){
				result = getResultJson(ReturnCode.PARAM_ERROR_CODE, "升级时间格式有误");
				jsonObj(result.toString());
				return;
			}
			
			// 验证终端编号
			PayTerminal payTerminal = payTerBiz.findByCode(deviceInfo);
			if(payTerminal == null || "4".equals(payTerminal.getFacilityStatus())){
				writeResultJsonByCode(response, ReturnCode.TERMINAL_NOT_CODE);
				return;
			}
			
			if("0".equals(upgradeType)){// 主程序
				payTerminal.setMaster_version_code(versionCode);
			}else if("1".equals(upgradeType)){// 守护程序
				payTerminal.setDaemon_version_code(versionCode);
			}
			// 更新终端记录的版本号
			boolean updateVersioncode = payTerBiz.updateVersioncode(payTerminal);
			if(updateVersioncode){// 更新终端版本成功
				// 设备类型
				String facilityType = payTerminal.getFacilityType();
				if(StringUtils.isBlank(facilityType)){
					result = getResultJson(ReturnCode.FAIL_CODE, "终端类型为空");
				}else{
					result = getResultJson(ReturnCode.SUCCESS_CODE);
					SelfServiceVersionPO version = selfServiceVersionService.findByVersioncode(upgradeType, facilityType, Integer.parseInt(versionCode));
					if(version != null){
						SelfserviceUpgradeHistoryPO historyPO = new SelfserviceUpgradeHistoryPO();
						historyPO.setFacilityId(deviceInfo);
						historyPO.setVersionId(version.getId());
						historyPO.setUpgrade_time(new Timestamp(date.getTime()));
						if(!selfServiceVersionService.checkUpgradeHistory(historyPO)){
							// 不存在则保存升级记录
							boolean saveResult = selfServiceVersionService.saveUpgradeHistory(historyPO);
							if(!saveResult){// 保存成功
								result = getResultJson(ReturnCode.FAIL_CODE, "处理失败，请重新尝试");
							}
						}
					}
				}
			}else{// 更新终端版本失败
				result = getResultJson(ReturnCode.FAIL_CODE, "处理失败，请重新尝试");
			}
			jsonObj(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
			returnErrorJson(response);
		}
	}*/
	
	/**
	 * 上报版本升级信息接口
	 */
	public void reportUpgradeInfo(){
		try{
			Document document = null;
			
			Document doc = getRequestXml(request);
		    Element rootNode = doc.getRootElement();
		    // 升级类型，0主程序升级，1守护程序升级
		    String upgradeType = rootNode.elementText("upgradeType");
		    // 终端设备号
		    String deviceInfo = rootNode.elementText("deviceInfo");
			// 升级后的版本号
			String versionCode = rootNode.elementText("versionCode");
			// 升级时间
			String upgradeTime = rootNode.elementText("upgradeTime");
			if(StringUtils.isBlank(upgradeType) || StringUtils.isBlank(deviceInfo)
					|| StringUtils.isBlank(versionCode) || StringUtils.isBlank(upgradeTime)){
				writeResultByCode(response, ReturnCode.PARAM_ERROR_CODE);
				return;
			}
			// 验证版本号是否是数字
			boolean numeric = StringUtils.isNumeric(versionCode);
			if(!numeric){
				document = getResultXml(ReturnCode.PARAM_ERROR_CODE, "版本号格式有误");
				writeResult(response, document);
				return;
			}
			// 转换升级时间
			Date date = DateUtil.str2Date(upgradeTime, "yyyy-MM-dd HH:mm:ss");
			if(date == null){
				document = getResultXml(ReturnCode.PARAM_ERROR_CODE, "升级时间格式有误");
				writeResult(response, document);
				return;
			}
			
			// 验证终端编号
			PayTerminal payTerminal = payTerBiz.findByCode(deviceInfo);
			if(payTerminal == null || "4".equals(payTerminal.getFacilityStatus())){
				writeResultByCode(response, ReturnCode.TERMINAL_NOT_CODE);
		    	return;
			}
			
			if("0".equals(upgradeType)){// 主程序
				payTerminal.setMaster_version_code(versionCode);
			}else if("1".equals(upgradeType)){// 守护程序
				payTerminal.setDaemon_version_code(versionCode);
			}
			// 更新终端记录的版本号
			boolean updateVersioncode = payTerBiz.updateVersioncode(payTerminal);
			if(updateVersioncode){// 更新终端版本成功
				// 设备类型
				String facilityType = payTerminal.getFacilityType();
				if(StringUtils.isBlank(facilityType)){
					document = getResultXml(ReturnCode.FAIL_CODE, "终端类型为空");
				}else{
					document = getResultXml(ReturnCode.SUCCESS_CODE);
					SelfServiceVersionPO version = selfServiceVersionService.findByVersioncode(upgradeType, facilityType, Integer.parseInt(versionCode));
					if(version != null){
						SelfserviceUpgradeHistoryPO historyPO = new SelfserviceUpgradeHistoryPO();
						historyPO.setFacilityId(deviceInfo);
						historyPO.setVersionId(version.getId());
						historyPO.setUpgrade_time(new Timestamp(date.getTime()));
						if(!selfServiceVersionService.checkUpgradeHistory(historyPO)){
							// 不存在则保存升级记录
							boolean saveResult = selfServiceVersionService.saveUpgradeHistory(historyPO);
							if(!saveResult){// 保存成功
								document = getResultXml(ReturnCode.FAIL_CODE, "处理失败，请重新尝试");
							}
						}
					}
				}
			}else{// 更新终端版本失败
				document = getResultXml(ReturnCode.FAIL_CODE, "处理失败，请重新尝试");
			}
			// 返回处理结果
			writeResult(response, document);
		} catch (Exception e) {
			e.printStackTrace();
			returnError(response);
		}
	}
	
	/**
	 * 上传日志接口
	 */
	public void uploadLog(){
		JsonObject result = new JsonObject();
		try{
			// 日志类型
			String logType = request.getParameter("logType");
			// 终端设备号
			String deviceInfo = request.getParameter("deviceInfo");
			if(StringUtils.isBlank(logType) || StringUtils.isBlank(deviceInfo)){
				writeResultJsonByCode(response, ReturnCode.PARAM_ERROR_CODE);
				return;
			}
			if(log == null){
				result = getResultJson(ReturnCode.PARAM_ERROR_CODE, "上传文件为空");
				jsonObj(result.toString());
				return;
			}
			
			// 验证终端编号
			PayTerminal payTerminal = payTerBiz.findByCode(deviceInfo);
			if(payTerminal == null || "4".equals(payTerminal.getFacilityStatus())){
				writeResultJsonByCode(response, ReturnCode.TERMINAL_NOT_CODE);
				return;
			}
			
			// 保存日志文件
			String root = ServletActionContext.getServletContext().getRealPath("/upload");
			File folder = new File(root);
			// 如果文件夹不存在，则创建
			if(!folder.exists()){
				folder.mkdirs();
			}
			File saveFile = new File(root, logFileName);
	        FileUtils.copyFile(log, saveFile);
	        
	        // 保存日志上传记录
	        SelfServiceLogPO po = new SelfServiceLogPO();
	        po.setLogType(logType);
	        po.setFacilityId(deviceInfo);
	        po.setLogFileName(logFileName);
	        po.setLogPath("/upload/" + logFileName);
			boolean saveResult = selfServiceLogService.save(po);
			if(saveResult){
				result = getResultJson(ReturnCode.SUCCESS_CODE);
			}else{
				result = getResultJson(ReturnCode.FAIL_CODE, "上传失败，请重新尝试");
			}
			jsonObj(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
			returnErrorJson(response);
		}
	}

	public File getLog() {
		return log;
	}

	public void setLog(File log) {
		this.log = log;
	}

	public String getLogFileName() {
		return logFileName;
	}

	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}

}
 