package com.catic.mobilehos.service.vo;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.catic.mobilehos.GlobalConstants;
import com.catic.mobilehos.po.ServiceEvaluationPO;

public class ServiceEvaluationVO extends ServiceEvaluationPO {
	
	
	public String getCreateDateDesc(){
		return DateFormatUtils.format(this.getCreatetime(), GlobalConstants.DEF_DATEFORMAT);
	}
	
	
	}
	
