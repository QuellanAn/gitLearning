package com.catic.mobilehos.pay.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.entity.YQInfo;
import com.catic.mobilehos.pay.util.Constant;
import com.catic.mobilehos.utils.Page;

/**
 * 院区配置
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class YQConfigAction extends BaseAction{

	private String yqCode;
	private YQInfo yq;
	private List<YQInfo> yqList;
	
	/**
	 * 查询所有院区
	 * @return
	 */
	public String findAll(){
		try {
             if(StringUtils.isBlank(pageNo)){
            	 pageNo="1";
             }            
			yqList=yQConfigBiz.findAll(null);
			if(yqList!=null&&yqList.size()>0){
				page=new Page(Integer.parseInt(pageNo),15, yqList.get(0).getCount());
				yqList=yQConfigBiz.findAll(page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 进入添加界面
	 * @return
	 */
	public String toSave(){
		
		return "toSave";
		
	}
	
	/**
	 * 进入编辑界面
	 * @return
	 */
	public String toModify(){
		try {
			yq=yQConfigBiz.findByCode(yqCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "modify";		
	}
	
	/**
	 * 添加院区
	 * @return
	 */
	public String addYQ(){
		
		try {
			if(yq!=null){
				yq.setCreator((String)request.getSession().getAttribute("userName"));//创建人
			}
			yQConfigBiz.insert(yq);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addSuccess";
		
	}
	
	/**
	 * 修改院区
	 * @return
	 */
	public String modifyYQ(){
		
		try { 
			   yQConfigBiz.modify(yq);
			   if(StringUtils.isBlank(pageNo)){
	            	 pageNo="1";
	             }            
				yqList=yQConfigBiz.findAll(null);
				if(yqList!=null&&yqList.size()>0){
					page=new Page(Integer.parseInt(pageNo),Constant.DEFAULT_PAGE_SIZE, yqList.get(0).getCount());
					yqList=yQConfigBiz.findAll(page);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "modifySuccess";
		
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
	  try {
		yQConfigBiz.delete(yqCode);
	    if(StringUtils.isBlank(pageNo)){
          	 pageNo="1";
          }            
		yqList=yQConfigBiz.findAll(null);
		if(yqList!=null&&yqList.size()>0){
				page=new Page(Integer.parseInt(pageNo),Constant.DEFAULT_PAGE_SIZE, yqList.get(0).getCount());
				yqList=yQConfigBiz.findAll(page);
			}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return SUCCESS;
	}
	
	
	public String getYqCode() {
		return yqCode;
	}
	public void setYqCode(String yqCode) {
		this.yqCode = yqCode;
	}
	public YQInfo getYq() {
		return yq;
	}
	public void setYq(YQInfo yq) {
		this.yq = yq;
	}
	public List<YQInfo> getYqList() {
		return yqList;
	}
	public void setYqList(List<YQInfo> yqList) {
		this.yqList = yqList;
	}
	
	
	
	
}
