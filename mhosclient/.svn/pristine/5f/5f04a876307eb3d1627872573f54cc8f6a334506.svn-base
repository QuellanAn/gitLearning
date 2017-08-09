package com.catic.mobilehos.action;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.catic.mobilehos.service.ItemsService;
import com.catic.mobilehos.service.vo.AppRegOrdersVO;
import com.catic.mobilehos.service.vo.ItemsVO;
import com.catic.mobilehos.service.vo.Page;

/**
 * 缴费记录
 * @author gds
 *
 */
public class ItemsAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private ItemsService itemsService;
	private int page;
	private final int DEFAULT_PAGESIZE = 10;
	private Page<ItemsVO> pageBean;
	
	public ItemsService getItemsService() {
		return itemsService;
	}

	public void setItemsService(ItemsService itemsService) {
		this.itemsService = itemsService;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Page<ItemsVO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<ItemsVO> pageBean) {
		this.pageBean = pageBean;
	}

	public String getItems(){
		try {
			String cardNo = request.getParameter("cardNo");
			String patientname = request.getParameter("patientname");
			String tarOCCateDesc = request.getParameter("tarOCCateDesc");
			if(StringUtils.isNotBlank(tarOCCateDesc)){
				tarOCCateDesc=new String(tarOCCateDesc.getBytes("ISO-8859-1"),"utf-8");
				tarOCCateDesc = java.net.URLDecoder.decode(tarOCCateDesc, "utf-8");
			}
			String admRowid = request.getParameter("admRowid");
			String startCreateTime = request.getParameter("startCreateTime");
			String endCreateTime = request.getParameter("endCreateTime");
			
			pageBean = itemsService.queryItemsPOByParas(cardNo, patientname, tarOCCateDesc, admRowid, 
					startCreateTime, endCreateTime, page, DEFAULT_PAGESIZE);
			
			//System.out.println(pageBean.getCurPageData().get(0).getTarOCCateDesc());
			Map<String, String> paras = new TreeMap<String, String>();
			paras.put("cardNo", cardNo);
			if (!StringUtils.isEmpty(patientname)) {
				paras.put("patientname", patientname);
			}
			paras.put("tarOCCateDesc", tarOCCateDesc);
			paras.put("admRowid", admRowid);
			paras.put("startCreateTime", startCreateTime);
			paras.put("endCreateTime", endCreateTime);
			String baseUrl = "busrecord/getItems";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			return ERROR;
		}
	}
}
