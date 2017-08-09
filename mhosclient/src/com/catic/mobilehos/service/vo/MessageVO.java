package com.catic.mobilehos.service.vo;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.catic.mobilehos.GlobalConstants;
import com.catic.mobilehos.po.MessagePO;
import com.catic.mobilehos.service.message.BaiduMessage;

public class MessageVO extends MessagePO{
	
	public String getExpectTimeDesc(){
		return DateFormatUtils.format(this.getExpectTime()
				, GlobalConstants.DEF_DATETIME_FMT);
	}
	
	public String getSendWayDesc(){
		if (this.getSendWay() == SEND_WAY_PHONE){
			return "短信";
		}else if (this.getSendWay() == SEND_WAY_NET){
			return "消息";
		}else{
			return "";
		}
	}
	
	public String getStatusDesc(){
		if (this.getStatus() == STATUS_UNSENT){
			return "未发送";
		}else if (getStatus() == STATUS_SUCCESS){
			return "发送成功";
		}else if (getStatus() == STATUS_FAILED){
			return "发送失败";
		}else {
			return "";
		}
	}

	public String getMsgContentDesc() {
		if (getSendWay() == SEND_WAY_NET){
			try{
				JSONObject j = JSONObject.fromObject(this.getMsgContent());
				return j.optString(BaiduMessage.MSG_KEY_DESC);
			}catch(Exception e){
				return this.getMsgContent();
			}
		}else{
			return super.getMsgContent();
		}
	}
	
	
	
	

}
