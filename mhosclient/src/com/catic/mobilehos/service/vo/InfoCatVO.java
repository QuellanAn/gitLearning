package com.catic.mobilehos.service.vo;

import com.catic.mobilehos.po.InfoCatPO;

public class InfoCatVO extends InfoCatPO{
	
	public String getInfoTypeName(){
		if (this.getInfoType() == INFO_TYPE_HEALTH){
			return DescConstants.INFO_TYPE_HEALTH;
		}else if (this.getInfoType() == INFO_TYPE_NEWS){
			return DescConstants.INFO_TYPE_NEWS;
		}
		
		return null;
	}
	
	

}
