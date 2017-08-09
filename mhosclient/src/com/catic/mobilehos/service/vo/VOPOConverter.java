package com.catic.mobilehos.service.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * PO,VO的转换工具
 * @author xieweipeng
 *
 * @param <VO>
 * @param <PO>
 */
public class VOPOConverter<VO, PO> {
	
	private Class<VO> clsVO;
	
	private Class<PO> clsPO;
	
	public VOPOConverter(Class<VO> clsVO, Class<PO> clsPO){
		this.clsVO = clsVO;
		this.clsPO = clsPO;
	}
	
	
	public VO poToVO(Object po){
		try {
			VO v = clsVO.newInstance();
			PropertyUtils.copyProperties(v, po);
			return v;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public PO voToPO(Object v){
		try {
			PO p = clsPO.newInstance();
			PropertyUtils.copyProperties(p, v);
			return p;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<VO> fromPOList(List<PO> listPO){
		List<VO> listVO = new ArrayList<VO>();
		for (Object po : listPO){
			try {
				VO v = clsVO.newInstance();
				PropertyUtils.copyProperties(v, po);
				listVO.add(v);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		}
		return listVO;
		
	}
	
	public List<PO> fromVOList(List<VO> listVO){
		List<PO> listPO = new ArrayList<PO>();
		for (Object vo : listVO){
			try {
				PO p = clsPO.newInstance();
				PropertyUtils.copyProperties(p, vo);
				listPO.add(p);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		}
		return listPO;
		
	}

}
