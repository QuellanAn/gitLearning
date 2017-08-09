package com.catic.mobilehos.common;  

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.catic.mobilehos.pay.action.BaseAction;
import com.google.gson.JsonObject;

/**  
 * 类说明:返回xml数据处理工具
 * @author 朱伟权
 * 创建时间: 2017-6-5 上午11:28:17
 */
public class ResultHandler extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(this.getClass());
	
	protected PrintWriter out = null;
	protected static final Map<String, String> resultMap = new HashMap<String, String>();
	
	static{
		resultMap.put(ReturnCode.SUCCESS_CODE, ReturnCode.SUCCESS);// 交易成功
		resultMap.put(ReturnCode.FAIL_CODE, ReturnCode.FAIL);// 交易失败
		resultMap.put(ReturnCode.SYSTEM_CODE, ReturnCode.SYSTEM);// 系统异常
		resultMap.put(ReturnCode.PARAM_ERROR_CODE, ReturnCode.PARAM_ERROR);// 参数不能为空
		resultMap.put(ReturnCode.PAY_CHANNEL_ERRO_CODE, ReturnCode.PAY_CHANNEL_ERRO);// 支付通道异常
		resultMap.put(ReturnCode.PAY_CHANNEL_NOT_CODE, ReturnCode.PAY_CHANNEL_NOT);// 支付通道不存在
		resultMap.put(ReturnCode.TERMINAL_NOT_CODE, ReturnCode.TERMINAL_NOT);// 终端被禁用
		resultMap.put(ReturnCode.METHOD_POST_CODE, ReturnCode.METHOD_POST);// 请求方式不支持
	}
	
	/**
	 * 解析请求过来的xml数据，返回Document
	 * @param request HttpServletRequest
	 * @return 请求的xml数据
	 * @throws Exception
	 */
	public Document getRequestXml(HttpServletRequest request) throws Exception {
		logger.info("request：" + request);
		InputStream input = request.getInputStream();
		String xml = IOUtils.toString(input, "UTF-8");
		logger.info("getRequestXml()接收的xml：" + xml);
//		System.out.println(xml);
		
		long start = System.currentTimeMillis();
		SAXReader reader = new SAXReader();  
	    Document doc = reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
	    long cost = System.currentTimeMillis() - start;
	    logger.info("getRequestXml()解析doc成功(耗时" + cost + "ms)：" + doc);
	    return doc;
	}
	
	/**
	 * 根据code获取对应的返回结果xml(只包括code和message节点)
	 * @param code 结果code
	 * @return 结果xml
	 */
	public Document getResultXml(String code) {
	    return getResultXml(code, null);
	}
	
	/**
	 * 根据code获取对应的返回结果xml(只包括code和message节点)
	 * @param code 结果code
	 * @param message 自定义message
	 * @return 结果xml
	 */
	public Document getResultXml(String code, String message) {
		if (StringUtils.isBlank(message)) {
			message = resultMap.get(code);
			if(StringUtils.isBlank(message)){
				message = "未知错误";
			}
		}

		Document document = DocumentHelper.createDocument();  
        Element rootEle = document.addElement("xml");
  	    Element codeEle = rootEle.addElement("code");
	    Element messageEle = rootEle.addElement("message");	
	    codeEle.setText(code);
	    messageEle.setText(message);
	    
	    return document;
	}
	
	/**
	 * map转化为Document
	 * @param params key为子节点名称，value为子节点值
	 * @return 返回创建好的Document
	 */
	public Document map2Document(Map<String, String> params){
		Document docu = DocumentHelper.createDocument();  
		Element root = docu.addElement("xml");
		if(params != null && params.size() > 0){
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				root.addElement(key).setText(value);// 增加子节点
			}
		}
		return docu;
	}
	
	/**
	 * 返回xml结果
	 * @param response HttpServletResponse
	 * @param document xml结果数据
	 */
	public void writeResult(HttpServletResponse response, Document document){
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache");
//			response.setContentType("application/xml; charset=UTF-8");
			response.setContentType("text/plain; charset=UTF-8");
			out = response.getWriter();
			if(document == null){
				throw new Exception("document cannot be null.");
			}
			logger.info("返回结果：" + document.getRootElement().asXML());
			out.write(document.getRootElement().asXML());
		} catch (Exception e) {
			logger.error("ResultHandler-returnError-Exception", e);
		}finally{
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 根据code返回结果
	 * @param response HttpServletResponse
	 * @param code 结果code
	 */
	public void writeResultByCode(HttpServletResponse response, String code){
		Document document = getResultXml(code);
		writeResult(response, document);
	}
	
	/**
	 * 业务处理出现异常，返回系统异常
	 * @param response HttpServletResponse
	 */
	public void returnError(HttpServletResponse response){
		Document document = getResultXml(ReturnCode.SYSTEM_CODE);
		writeResult(response, document);
	}
	
	
	/*****************************返回json处理******************************/
	
	/**
	 * 根据code返回json结果
	 * @param response HttpServletResponse
	 * @param code 结果code
	 */
	public void writeResultJsonByCode(HttpServletResponse response, String code){
		JsonObject jsonObject = getResultJson(code);
		jsonObj(jsonObject.toString());
	}
	
	/**
	 * 根据code获取对应的返回结果json(只包括code和message节点)
	 * @param code 结果code
	 * @return 结果json
	 */
	public JsonObject getResultJson(String code) {
	    return getResultJson(code, null);
	}
	
	/**
	 * 根据code获取对应的返回结果json(只包括code和message节点)
	 * @param code 结果code
	 * @param message 自定义message
	 * @return 结果json
	 */
	public JsonObject getResultJson(String code, String message) {
		if (StringUtils.isBlank(message)) {
			message = resultMap.get(code);
			if(StringUtils.isBlank(message)){
				message = "未知错误";
			}
		}

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", code);
		jsonObject.addProperty("message", message);
	    return jsonObject;
	}
	
	/**
	 * 业务处理出现异常，返回系统异常
	 * @param response HttpServletResponse
	 */
	public void returnErrorJson(HttpServletResponse response){
		JsonObject jsonObject = getResultJson(ReturnCode.SYSTEM_CODE);
		jsonObj(jsonObject.toString());
	}

}
 