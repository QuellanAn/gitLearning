package com.catic.mobilehos.action;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.service.HospitalInfoService;
import com.catic.mobilehos.service.ServiceResult;

/**
 * 该Action配合KindEditor使用，用于保存KindEditor里医院咨讯的图片
 * @author xieweipeng
 *
 */
public class HosPubInfoHtmlImgUploadAction extends KindEditorAction {

	private static final long serialVersionUID = 1L;
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private HospitalInfoService hospitalInfoService;
	
	
	public HospitalInfoService getHospitalInfoService() {
		return hospitalInfoService;
	}

	public void setHospitalInfoService(HospitalInfoService hospitalInfoService) {
		this.hospitalInfoService = hospitalInfoService;
	}

	@Override
	protected ServiceResult processFile(File file) {
		String infoid = request.getParameter("infoid");
		ServiceResult sr = this.hospitalInfoService.saveInfoHtmlImage(request.getServletContext()
				, infoid, file);
		return sr;
	}

}
