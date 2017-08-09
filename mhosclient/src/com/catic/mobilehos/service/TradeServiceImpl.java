package com.catic.mobilehos.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.TradeDAO;
import com.catic.mobilehos.po.TradePO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.TradeVO;
import com.catic.mobilehos.service.vo.VOPOConverter;

public class TradeServiceImpl implements TradeService{
	private Log log = LogFactory.getLog(this.getClass());
	private TradeDAO tradeDao;

	public TradeDAO getTradeDao() {
		return tradeDao;
	}


	public void setTradeDao(TradeDAO tradeDao) {
		this.tradeDao = tradeDao;
	}


	@Override
	public Page<TradeVO> queryTradesByParas(String cardNo, String tradeNo, String transactionId, 
			String status, String startSQLDate, String endSQLDate, int page, int pageSize, String patientname) {
		// TODO Auto-generated method stub
		try {
			int totalRecord = this.tradeDao.countTradePOsByParas(cardNo, tradeNo, transactionId, status,
					startSQLDate, endSQLDate, patientname);
			Page<TradeVO> t = new Page<TradeVO>(totalRecord, pageSize, page);
			List<TradePO> lst = tradeDao.findTradePOsByParas(cardNo, tradeNo, transactionId, status,
					startSQLDate, endSQLDate, t.getOffset(),pageSize, patientname);
			VOPOConverter<TradeVO, TradePO> cvt = new VOPOConverter<TradeVO, TradePO>(
					TradeVO.class, TradePO.class);
			t.setCurPageData(cvt.fromPOList(lst));
			return t;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}


	@Override
	public List<TradePO> showTradeInfo(String tradeNo) {
		// TODO Auto-generated method stub
		return tradeDao.showTradeInfo(tradeNo);
	}


	@Override
	public List<TradePO> getCountByDate(TradePO po) {
		return tradeDao.getCountByDate(po);
	}


	@Override
	public List<TradePO> getCountBySource(TradePO po) {
		return tradeDao.getCountBySource(po);
	}

}
