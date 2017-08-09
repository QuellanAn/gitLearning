package com.catic.mobilehos.service;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.catic.mobilehos.ErrorCode;
import com.catic.mobilehos.dao.NoticeDAO;
import com.catic.mobilehos.po.NoticePO;

public class NoticeServiceImpl implements NoticeService {

	private Log log = LogFactory.getLog(this.getClass());

	private NoticeDAO noticeDAO = null;

	public NoticeDAO getNoticeDAO() {
		return noticeDAO;
	}

	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	@Override
	public ServiceResult getNotice() {
		try {
			final String notice = noticeDAO.getNotice();
			return new ServiceResult() {

				@Override
				public boolean isSuccess() {
					return true;
				}

				@Override
				public JSONObject getJSON() {
					JSONObject j = new JSONObject();
					j.put("notice", notice);
					return j;
				}

			};
		} catch (DataAccessException dacEx) {
			ServiceResult sr = ServiceResult.getFailedInstance(
					ErrorCode.LS_BUS_EXEC_ERR, "系统异常，获取挂号须知失败！");
			return sr;
		} catch (Exception ex) {
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance(
					ErrorCode.LS_ILLEGAL_BUS_ARGUMENT, "参数错误，查询失败！");
			return sr;
		}

	}

	@Override
	public NoticePO getNoticePO(int noticeId) {
		try {
			return noticeDAO.getNoticePO(noticeId);
		} catch (Exception e) {
			log.error("系统异常，获取挂号须知失败！", e);
			return null;
		}
	}

	@Override
	public boolean saveNotice(NoticePO noticePO) {
		try {
			return noticeDAO.saveNotice(noticePO);
		} catch (Exception e) {
			log.error("系统异常，获取挂号须知失败！", e);
			return false;
		}
	}

	@Override
	public boolean updateNotice(NoticePO noticePO) {
		try {
			return noticeDAO.updateNotice(noticePO);
		} catch (Exception e) {
			log.error("系统异常，获取挂号须知失败！", e);
			return false;
		}
	}

}
