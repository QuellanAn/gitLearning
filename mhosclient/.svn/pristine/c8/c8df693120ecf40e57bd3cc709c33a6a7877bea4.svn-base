package com.catic.mobilehos.service;

import java.util.List;

import com.catic.mobilehos.po.ItemsList;
import com.catic.mobilehos.po.ItemsPO;
import com.catic.mobilehos.service.vo.ItemsVO;
import com.catic.mobilehos.service.vo.Page;

public interface ItemsService {
	ItemsPO findItemsById(String admRowid);
	/**
	 * 获取医嘱列表
	 * @param admRowid
	 * @return
	 */
	List<ItemsList> findItemsListsByParms(String admRowid, String tarOCCateDesc);
	/**
	 * 获取缴费列表
	 * @param admRowid
	 * @param startCreateTime
	 * @param endCreateTime
	 * @param offset
	 * @param length
	 * @return
	 */
	Page<ItemsVO> queryItemsPOByParas(String cardNo, String patientname, String tarOCCateDesc, String admRowid, String startCreateTime, String endCreateTime, int page, int pageSize);
	
	/**
	 * 按日期统计缴费量
	 * @param po 查询条件
	 * @return 按日期统计缴费量查询结果
	 */
	List<ItemsPO> getCountByDate(ItemsPO po);
	
	/**
	 * 按缴费来源统计缴费量
	 * @param po 查询条件
	 * @return 按缴费来源统计缴费量
	 */
	List<ItemsPO> getCountBySource(ItemsPO po);
	
}
