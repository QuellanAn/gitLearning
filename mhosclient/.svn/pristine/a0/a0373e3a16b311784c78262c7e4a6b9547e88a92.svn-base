package com.catic.mobilehos.pay.biz;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import com.catic.mobilehos.pay.entity.HisCheck;

public interface IHisCheckBiz {
	public int save(HisCheck ci)throws Exception;
	
	public Map<String,Object> readXls(Workbook workBook,String uploadFileName,HttpServletRequest request,HttpServletResponse response);
}
