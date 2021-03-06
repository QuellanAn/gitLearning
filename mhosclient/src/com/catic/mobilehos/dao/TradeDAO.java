package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.TradePO;

/**
 * 充值记录
 * @author gds
 *
 */
public interface TradeDAO {
	List<TradePO> findTradePOsByParas(String cardNo, String tradeNo, String transactionId, String status, 
			String startSQLDate, String endSQLDate, int offset, int length, String patientname);
	
	int countTradePOsByParas(String cardNo, String tradeNo, String transactionId, String status, String startSQLDate, String endSQLDate, String patientname);
	
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
