package com.catic.mobilehos.action;

import java.io.File;

import com.catic.mobilehos.service.BasicCntCfgService;
import com.catic.mobilehos.service.ServiceResult;

/**
 * 基本内容配置图片上传
 * 
 * @author linchundas
 * 
 */
@SuppressWarnings("serial")
public class BasicCntCfgImgUploadAction extends KindEditorAction {

	BasicCntCfgService basicCntCfgService;

	public BasicCntCfgService getBasicCntCfgService() {
		return basicCntCfgService;
	}

	public void setBasicCntCfgService(BasicCntCfgService basicCntCfgService) {
		this.basicCntCfgService = basicCntCfgService;
	}

	@Override
	protected ServiceResult processFile(File file) {
		int cfg_type = Integer.parseInt(request.getParameter("cfg_type"));
		int cat = Integer.parseInt(request.getParameter("cat"));
		ServiceResult sr = basicCntCfgService.saveBasicCntCfgHtmlImage(
				request.getServletContext(), cfg_type, cat, file);

		return sr;
	}

}
