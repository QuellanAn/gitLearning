package com.catic.mobilehos.action;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.catic.mobilehos.service.MsgMngService;
import com.catic.mobilehos.service.vo.BusTypeDicVO;
import com.catic.mobilehos.service.vo.MessageVO;
import com.catic.mobilehos.service.vo.Page;

import net.sf.json.JSONArray;

/**
 * 后台消息发送记录管理
 * @author xieweipeng
 *
 */
public class MsgBusMngAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private static final int DEFAULT_PAGESIZE = 10;
	
	private Page<MessageVO> pageBean;
	
	private int page;
	
	/**
	 * 消息类型编码
	 */
	private String bustypecode;
	
	/**
	 * 目标手机
	 */
	private String phone;
	
	private String status = "";
	
	/**
	 * 查询的开始日期
	 */
	private String startdate;
	
	/**
	 * 查询的结束日期
	 */
	private String enddate;
	
	private Timestamp  startSQLDate;
	
	private Timestamp endSQLDate;
	
	private List<BusTypeDicVO> types;
	
	private MsgMngService msgMngService;
	
	
	public MsgMngService getMsgMngService() {
		return msgMngService;
	}


	public void setMsgMngService(MsgMngService msgMngService) {
		this.msgMngService = msgMngService;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Page<MessageVO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<MessageVO> pageBean) {
		this.pageBean = pageBean;
	}

	public String getBustypecode() {
		return bustypecode;
	}

	public void setBustypecode(String bustypecode) {
		this.bustypecode = bustypecode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	public List<BusTypeDicVO> getTypes() {
		return types;
	}


	public void setTypes(List<BusTypeDicVO> types) {
		this.types = types;
	}


	public void getMsgBusTypeJson(){
		try{
			List<BusTypeDicVO> types = this.msgMngService.getAllMsgBusType();
			JSONArray jarr = JSONArray.fromObject(types);
			this.writeJSON(jarr);
		}catch(Exception e){
			log.error(null, e);
		}
	}
	
	public void validateGetMessagesList(){
		if (StringUtils.isNotBlank(this.startdate)){
			this.startSQLDate = this.toTimestamp(this.startdate);
			if (this.startSQLDate == null){
				this.addFieldError("", "日期格式不正确！");
			}
		}
		if (StringUtils.isNotBlank(this.enddate)){
			this.endSQLDate = this.toTimestamp(this.enddate);
			if (this.endSQLDate == null){
				this.addFieldError("", "日期格式不正确！");
			}
		}
	}
	
	
	/**
	 * 按条件查询消息发送记录
	 * @return
	 */
	public String getMessagesList(){
		try{
			pageBean = msgMngService.queryMsgByParas(
					StringUtils.isNotBlank(bustypecode) ? bustypecode : null
					, StringUtils.isNotBlank(phone) ? phone : null
					, StringUtils.isNotBlank(status)? Integer.parseInt(status) : null
					, this.startSQLDate, this.endSQLDate, page, DEFAULT_PAGESIZE);
			Map<String, String> paras = new TreeMap<String, String>();
			paras.put("bustypecode", bustypecode);//添加消息类型条件  linchunda
			paras.put("status", String.valueOf(this.status));
			paras.put("phone", phone);
			paras.put("startdate", this.startdate);
			paras.put("enddate", this.enddate);
			String baseUrl = "busrecord/msg/getMessagesList";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		}catch(Exception e){
			log.error(null, e);
			return ERROR;
		}
		
	}

	
	

}
