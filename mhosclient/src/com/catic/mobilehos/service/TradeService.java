package com.catic.mobilehos.service;

import java.util.List;

import com.catic.mobilehos.po.TradePO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.TradeVO;

/**
 * 充值记录
 * @author Administrator
 *
 */
public interface TradeService {
	Page<TradeVO> queryTradesByParas(String cardNo, String tradeNo, String transactionId, String status, 
			String startSQLDate, String endSQLDate, int page, int pageSize, String patientname);
	List<TradePO> showTradeInfo(String tradeNo);
	
	/**
	 * 按日期统计充值量
	 * @param po 查询条件
	 * @return 按日期统计充值量
	 */
	List<TradePO> getCountByDate(TradePO po);
	
	/**
	 * 按充值来源统计充值量
	 * @param po 查询条件
	 * @return 按充值来源统计充值量
	 */
	List<TradePO> getCountBySource(TradePO po);
}
