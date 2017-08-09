package com.catic.mobilehos.dao;

import com.catic.mobilehos.po.NoticePO;

public interface NoticeDAO {

	String getNotice();

	/**
	 * 获取预约挂号须知
	 * 
	 * @return
	 */
	NoticePO getNoticePO(int noticeId);

	/**
	 * 保存预约挂号须知
	 * 
	 * @param noticePO
	 * @return
	 */
	boolean saveNotice(NoticePO noticePO);

	/**
	 * 修改预约挂号须知
	 * 
	 * @param noticePO
	 * @return
	 */
	boolean updateNotice(NoticePO noticePO);

}
