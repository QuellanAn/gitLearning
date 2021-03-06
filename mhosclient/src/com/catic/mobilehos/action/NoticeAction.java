package com.catic.mobilehos.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.ErrorCode;
import com.catic.mobilehos.po.NoticePO;
import com.catic.mobilehos.service.NoticeService;
import com.catic.mobilehos.service.ServiceResult;

/**
 * 挂号须知
 * 
 * @author xieweipeng
 * 
 */
public class NoticeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	private int noticeId;
	private String notice_content;

	private NoticeService noticeService;

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public NoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public void getNotice() {
		try {
			ServiceResult sr = noticeService.getNotice();
			this.writeServiceReuslt(sr);
		} catch (Exception e) {
			log.error(null, e);
			this.writeFailedResult(ErrorCode.LS_ILLEGAL_BUS_ARGUMENT,
					"获取挂号须知信息失败！");
		}
	}

	/**
	 * 获取预约挂号须知
	 * @throws IOException 
	 */
	public void getNoticePO() throws IOException {
		NoticePO noticePO = noticeService.getNoticePO(noticeId);
		if (null != noticePO) {
//			this.writeJSON(JSONObject.fromObject(noticePO));
			response.setContentType("text/html;charset=UTF-8");
			String json = JSONObject.fromObject(noticePO).toString();  
			response.getWriter().write(json.toString());  
			response.getWriter().flush(); 
			response.getWriter().close();  
		}
	}

	/**
	 * 保存预约挂号须知
	 */
	public void saveNoticePO() {
		NoticePO noticePO = new NoticePO();
		noticePO.setNotice_content(notice_content);
		noticePO.setCreate_date(new Timestamp(new Date().getTime()));
		noticePO.setUpdate_date(new Timestamp(new Date().getTime()));

		boolean result = noticeService.saveNotice(noticePO);
		if (result) {
			JSONObject json = new JSONObject();
			json.put("data", "保存预约挂号须知成功!");
			writeJSON(json);
		} else {
			JSONObject json = new JSONObject();
			json.put("data", "保存预约挂号须知失败!");
			writeJSON(json);
		}
	}

	/**
	 * 修改预约挂号须知
	 */
	public void updateNoticePO() {
		NoticePO noticePO = new NoticePO();
		noticePO.setNotice_id(noticeId);
		noticePO.setNotice_content(notice_content);
		noticePO.setUpdate_date(new Timestamp(new Date().getTime()));
		boolean result = noticeService.updateNotice(noticePO);
		if (result) {
			JSONObject json = new JSONObject();
			json.put("data", "修改预约挂号须知成功!");
			writeJSON(json);
		} else {
			JSONObject json = new JSONObject();
			json.put("data", "修改预约挂号须知失败!");
			writeJSON(json);
		}
	}

}
