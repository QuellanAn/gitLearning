package com.catic.mobilehos.service;

import java.util.List;

import com.catic.mobilehos.dao.CardPayDao;
import com.catic.mobilehos.po.CardPayDetailPO;
import com.catic.mobilehos.po.CardPayPO;
import com.catic.mobilehos.service.vo.CardPayDetailVO;
import com.catic.mobilehos.service.vo.CardPayVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.VOPOConverter;

public class CardPayServiceImpl implements CardPayService{
	private CardPayDao cardPayDao;
	
	public CardPayDao getCardPayDao() {
		return cardPayDao;
	}

	public void setCardPayDao(CardPayDao cardPayDao) {
		this.cardPayDao = cardPayDao;
	}

	@Override
	public CardPayPO findCardPayPOByCPId(String cpId) {
		// TODO Auto-generated method stub
		return cardPayDao.findCardPayPOByCPId(cpId);
	}

	@Override
	public Page<CardPayVO> queryCardPayByParas(String cpId, String receiptNo, String payWay,
			String cardNumber, String startCreateTime,
			String endCreateTime, int page, int pageSize) {
		try {
			int totalRecord = cardPayDao.countCardPayByParas(cpId, receiptNo, payWay, cardNumber, startCreateTime, endCreateTime);
			Page<CardPayVO> p = new Page<CardPayVO>(totalRecord, pageSize, page);
			List<CardPayPO> list = cardPayDao.findCardPayByParas(cpId, receiptNo, payWay, cardNumber, startCreateTime, endCreateTime, p.getOffset(), pageSize);
			VOPOConverter<CardPayVO, CardPayPO> cvt = new VOPOConverter<CardPayVO, CardPayPO>(CardPayVO.class, CardPayPO.class);
			p.setCurPageData(cvt.fromPOList(list));
			return p;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public CardPayDetailPO findCardPayDetailPOByCPId(String cpId) {
		// TODO Auto-generated method stub
		return cardPayDao.findCardPayDetailPOByCPId(cpId);
	}

	@Override
	public Page<CardPayDetailVO> queryCardPayDetailByParas(String cpId,
			int page, int pageSize) {
		// TODO Auto-generated method stub
		try {
			int totalRecord = cardPayDao.countCardPayDetailByParas(cpId);
			Page<CardPayDetailVO> p = new Page<CardPayDetailVO>(totalRecord, pageSize, page);
			List<CardPayDetailPO> list = cardPayDao.findCardPayDetailByParas(cpId, p.getOffset(), pageSize);
			VOPOConverter<CardPayDetailVO, CardPayDetailPO> cvt = new VOPOConverter<CardPayDetailVO, CardPayDetailPO>(CardPayDetailVO.class, CardPayDetailPO.class);
			p.setCurPageData(cvt.fromPOList(list));
			return p;
		} catch (Exception e) {
			return null;
		}
	}

}
