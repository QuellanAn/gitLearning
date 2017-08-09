package com.catic.mobilehos.service.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.catic.mobilehos.GlobalConstants;
import com.catic.mobilehos.po.HosPubInfoPO;
import com.catic.mobilehos.po.InfoCatPO;

public class HosPubInfoVO extends HosPubInfoPO{
	

	private String imageUrl;
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String getEditor() {
		if (super.getEditor() == null){
			return "";
		}
		return super.getEditor();
	}

	public String getInfoTypeDesc(){
		if (this.getInfoType() == InfoCatPO.INFO_TYPE_HEALTH){
			return DescConstants.INFO_TYPE_HEALTH;
		}else if (this.getInfoType() == InfoCatPO.INFO_TYPE_NEWS){
			return DescConstants.INFO_TYPE_NEWS;
		}
		return null;
	}
	
	public String getExpPubDateDesc(){
		if (this.getExpPubDate() != null){
			return DateFormatUtils.format(this.getExpPubDate(), GlobalConstants.DEF_DATEFORMAT);
		}else{
			return "";
		}
	}
	
	public String getUpdateDateDesc(){
		if (this.getUpdateDate() != null){
			return DateFormatUtils.format(this.getUpdateDate(), GlobalConstants.DEF_DATEFORMAT);
		}else{
			return "";
		}
	}
	
	
	public String getPubDateDesc(){
		if (this.getPubDate() != null){
			return DateFormatUtils.format(this.getPubDate(), GlobalConstants.DEF_DATETIME_FMT);
		}else{
			return "";
		}
	}
	
	public String getApproveDateDesc(){
		if (this.getApproveDate() != null){
			return DateFormatUtils.format(this.getApproveDate(), GlobalConstants.DEF_DATEFORMAT);
		}else{
			return "";
		}
	}

	
	public String getInfoTypeName(){
		if (this.getInfoType() == InfoCatPO.INFO_TYPE_HEALTH){
			return DescConstants.INFO_TYPE_HEALTH;
		}else if (this.getInfoType() == InfoCatPO.INFO_TYPE_NEWS){
			return DescConstants.INFO_TYPE_NEWS;
		}
		
		return null;
	}
	
	
	public String getStatusDesc(){
		if (this.getStatus() == STATUS_NEW){
			return "待审批";
		}else if (this.getStatus() == STATUS_UNAPPROVED){
			return "未通过";
		}else if (this.getStatus() == STATUS_APPROVED){
			return "已发布";
		}else if (this.getStatus() == STATUS_CANCEL){
			return "取消";
		}
		return "";
	}
	
	public boolean isApproved(){
		return this.getStatus() == STATUS_APPROVED;
	}
	
	public boolean isCanceled(){
		return this.getStatus() == STATUS_CANCEL;
	}
	
	
	public static HosPubInfoVO fromPO(HosPubInfoPO po){
		HosPubInfoVO vo = new HosPubInfoVO();
		try {
			PropertyUtils.copyProperties(vo, po);
			return vo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	public static List<HosPubInfoVO> fromList(List<HosPubInfoPO> oriList){
		List<HosPubInfoVO> destList = new ArrayList<HosPubInfoVO>();
		for (HosPubInfoPO p : oriList){
			destList.add(fromPO(p));
		}
		return destList;
	}
	
	public static class StatByCatVO extends StatByCatPO{
		
		public static StatByCatVO fromPO(StatByCatPO po){
			StatByCatVO vo = new StatByCatVO();
			try {
				PropertyUtils.copyProperties(vo, po);
				return vo;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}

}
