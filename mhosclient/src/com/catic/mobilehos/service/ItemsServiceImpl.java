package com.catic.mobilehos.service;

import java.util.List;

import com.catic.mobilehos.dao.ItemsDao;
import com.catic.mobilehos.po.ItemsList;
import com.catic.mobilehos.po.ItemsPO;
import com.catic.mobilehos.service.vo.ItemsVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.VOPOConverter;

public class ItemsServiceImpl implements ItemsService{
	private ItemsDao itemsDao;
	
	public ItemsDao getItemsDao() {
		return itemsDao;
	}

	public void setItemsDao(ItemsDao itemsDao) {
		this.itemsDao = itemsDao;
	}

	@Override
	public ItemsPO findItemsById(String admRowid) {
		// TODO Auto-generated method stub
		return itemsDao.findItemsById(admRowid);
	}

	@Override
	public List<ItemsList> findItemsListsByParms(String admRowid, String tarOCCateDesc) {
		// TODO Auto-generated method stub
		return itemsDao.findItemsListsByParms(admRowid, tarOCCateDesc);
	}

	@Override
	public Page<ItemsVO> queryItemsPOByParas(String cardNo, String patientname, String tarOCCateDesc, String admRowid,
			String startCreateTime, String endCreateTime, int page, int pageSize) {
		// TODO Auto-generated method stub
		try {
			int totalRecord = itemsDao.countItemsPOsByParms(cardNo, patientname, tarOCCateDesc, admRowid, startCreateTime, endCreateTime);
			Page<ItemsVO> p = new Page<ItemsVO>(totalRecord, pageSize, page);
			List<ItemsPO> list = itemsDao.findItemsPOsByParms(cardNo, patientname, tarOCCateDesc, admRowid, startCreateTime, endCreateTime, p.getOffset(), pageSize);
			VOPOConverter<ItemsVO, ItemsPO> cvt = new VOPOConverter<ItemsVO, ItemsPO>(ItemsVO.class, ItemsPO.class);
			p.setCurPageData(cvt.fromPOList(list));
			return p;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ItemsPO> getCountByDate(ItemsPO po) {
		return itemsDao.getCountByDate(po);
	}

	@Override
	public List<ItemsPO> getCountBySource(ItemsPO po) {
		return itemsDao.getCountBySource(po);
	}

}
