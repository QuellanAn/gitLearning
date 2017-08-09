package com.catic.mobilehos.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONValue;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.catic.mobilehos.dao.WechatMenuDao;
import com.catic.mobilehos.service.vo.DoctorNameVO;
import com.catic.mobilehos.service.vo.WechatMainMenuVO;
import com.catic.mobilehos.utils.PaulTool;

public class WechatMenuServiceImpl implements WechatMenuService {

	private Log log = LogFactory.getLog(this.getClass());

	private WechatMenuDao wechatMenuDao;

	public WechatMenuDao getWechatMenuDao() {
		return wechatMenuDao;
	}

	public void setWechatMenuDao(WechatMenuDao wechatMenuDao) {
		this.wechatMenuDao = wechatMenuDao;
	}

	@Override
	public List<WechatMainMenuVO> getAllMenu() {
		// TODO Auto-generated method stub
		List<WechatMainMenuVO> lst = wechatMenuDao.getAllMenu();
		return lst;
	}

	@Override
	public ServiceResult loadWechatMenu(String id) {
		// TODO Auto-generated method stub
		try {
			final WechatMainMenuVO main = this.wechatMenuDao.loadWechatMenu(id);
			return new ServiceResult() {

				@Override
				public boolean isSuccess() {
					return true;
				}

				@Override
				public JSONObject getJSON() {
					return JSONObject.fromObject(main);
				}

			};
		} catch (DataAccessException dacEx) {
			ServiceResult sr = ServiceResult.getFailedInstance("",
					"系统异常，读取消息配置失败！");
			return sr;
		} catch (Exception ex) {
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("",
					"系统异常，读取消息配置失败！");
			return sr;
		}
	}

	@Override
	public ServiceResult saveMenuContent(String id, String name, boolean view,
			boolean click, boolean key, String url,String submenu) {
		// TODO Auto-generated method stub
		try{
			String type ="";
			String keyCust="";
			if(view == true){
				 type ="view";
			}else {
				 type ="click";
			}
			if(key == true){
			    keyCust = "CustomerService";
				String viewCust ="new|"+ keyCust;
				url =viewCust;
			}
			
			this.wechatMenuDao.updateWechatMenu(id, name, type,keyCust,url,submenu);
			return ServiceResult.getSucInstance();
		}catch(DataAccessException dacEx){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，保存消息配置失败！");
			return sr;
		}catch(Exception ex){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，保存消息配置失败！");
			return sr;
		}
		
	}

	@Override
	public ServiceResult deleMenuContent(String id) {
		// TODO Auto-generated method stub
		try{
			this.wechatMenuDao.deleWechatMenu(id);
			return ServiceResult.getSucInstance();
		}catch(DataAccessException dacEx){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，保存消息配置失败！");
			return sr;
		}catch(Exception ex){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，保存消息配置失败！");
			return sr;
		}
	}

	@Override
	public ServiceResult pubMenuContent() {
		// TODO Auto-generated method stub
		try{
			String json = PaulTool.doPost(PaulTool.serverUrl+ "createMenu");
			JSONObject j = JSONObject.fromObject(json);   
			String errcode =j.getString("errcode");
			//String errmsg =j.getString("errmsg");
			
			if(errcode.equals("0")){
				return ServiceResult.getSucInstance();
			}else{
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				ServiceResult sr = ServiceResult.getFailedInstance("", "发布失败");
				return sr;
			}
			
			
		}catch(DataAccessException dacEx){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，保存消息配置失败！");
			return sr;
		}catch(Exception ex){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，保存消息配置失败！");
			return sr;
		}
	}

	@Override
	public List<WechatMainMenuVO> getmenuJson() {
		// TODO Auto-generated method stub
		List<WechatMainMenuVO> lst = this.wechatMenuDao.getmenuJson();
		List<WechatMainMenuVO> lst2 = new ArrayList<WechatMainMenuVO>();
		for ( WechatMainMenuVO i :lst){
			WechatMainMenuVO w = new WechatMainMenuVO();
			w.setId(i.getId());
			w.setName(i.getName());
			lst2.add(w);
		}
		return lst2;
	}


}
