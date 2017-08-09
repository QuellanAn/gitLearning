package com.catic.mobilehos.service.vo;

import java.util.ArrayList;
import java.util.List;

public class MsgBusMainTypeVO {
	
	private int mainType;
	
	private List<BusTypeDicVO> busTypeDics;

	public int getMainType() {
		return mainType;
	}

	public void setMainType(int mainType) {
		this.mainType = mainType;
	}

	public List<BusTypeDicVO> getBusTypeDics() {
		return busTypeDics;
	}

	public void setBusTypeDics(List<BusTypeDicVO> busTypeDics) {
		this.busTypeDics = busTypeDics;
	}
	
	
	public String getMainTypeDesc(){
		if (this.mainType == BusTypeDicVO.MAIN_TYPE_USERMNG_MSG){
			return "会员管理消息";
		}else if (this.mainType == BusTypeDicVO.MAIN_TYPE_BUS_MSG){
			return "业务消息";
		}else if (this.mainType == BusTypeDicVO.MAIN_TYPE_INFO_MSG){
			return "资讯消息";
		}else{
			return "其他";
		}
	}
	
	public void addBusTypeDic(BusTypeDicVO dic){
		if (this.busTypeDics == null){
			this.busTypeDics = new ArrayList<BusTypeDicVO>();
		}
		busTypeDics.add(dic);
	}

}
