package com.catic.mobilehos.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.catic.mobilehos.MsgTemplateConstants;
import com.catic.mobilehos.dao.BusTypeDicDAO;
import com.catic.mobilehos.dao.MessageDAO;
import com.catic.mobilehos.dao.NetMsgTemplateDAO;
import com.catic.mobilehos.dao.PhoneMsgTemplateDAO;
import com.catic.mobilehos.po.BusTypeDicPO;
import com.catic.mobilehos.po.MessagePO;
import com.catic.mobilehos.po.NetMsgTemplatePO;
import com.catic.mobilehos.po.PhoneMsgTemplatePO;
import com.catic.mobilehos.service.message.BaiduMessage;
import com.catic.mobilehos.service.vo.BusTypeDicVO;
import com.catic.mobilehos.service.vo.MessageVO;
import com.catic.mobilehos.service.vo.MsgBusMainTypeVO;
import com.catic.mobilehos.service.vo.MsgCfgVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.VOPOConverter;

public class MsgMngServiceImpl implements MsgMngService {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private BusTypeDicDAO busTypeDicDAO;
	
	private MessageDAO messageDAO;
	
	private NetMsgTemplateDAO netMsgTemplateDAO;
	
	private PhoneMsgTemplateDAO phoneMsgTemplateDAO;
	
	public BusTypeDicDAO getBusTypeDicDAO() {
		return busTypeDicDAO;
	}

	public NetMsgTemplateDAO getNetMsgTemplateDAO() {
		return netMsgTemplateDAO;
	}

	public void setNetMsgTemplateDAO(NetMsgTemplateDAO netMsgTemplateDAO) {
		this.netMsgTemplateDAO = netMsgTemplateDAO;
	}

	public PhoneMsgTemplateDAO getPhoneMsgTemplateDAO() {
		return phoneMsgTemplateDAO;
	}

	public void setPhoneMsgTemplateDAO(PhoneMsgTemplateDAO phoneMsgTemplateDAO) {
		this.phoneMsgTemplateDAO = phoneMsgTemplateDAO;
	}
	
	public void setBusTypeDicDAO(BusTypeDicDAO busTypeDicDAO) {
		this.busTypeDicDAO = busTypeDicDAO;
	}
	
	public MessageDAO getMessageDAO() {
		return messageDAO;
	}

	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}
	
	

	@Override
	public List<BusTypeDicVO> getAllMsgBusType() {
		List<BusTypeDicPO> lst = this.busTypeDicDAO.getAllBusTypeDic();
		VOPOConverter<BusTypeDicVO, BusTypeDicPO> cvt 
			= new VOPOConverter<BusTypeDicVO, BusTypeDicPO>(BusTypeDicVO.class, BusTypeDicPO.class);
		List<BusTypeDicVO> lstVO = cvt.fromPOList(lst);
		return lstVO;
	}

	@Override
	public Page<MessageVO> queryMsgByParas(String typeCode, String phone,
			Integer status, Timestamp startDate, Timestamp endDate, int page,
			int pagesize) {
		int totalRecord = this.messageDAO.countMsgsByParas(typeCode, phone, status, startDate, endDate);
		Page<MessageVO> p = new Page<MessageVO>(totalRecord, pagesize, page);
		List<MessagePO> lst = this.messageDAO.findMsgsByParas(typeCode, phone, status
				, startDate, endDate, p.getOffset(), pagesize);
		VOPOConverter<MessageVO, MessagePO> cvt 
			= new VOPOConverter<MessageVO, MessagePO>(MessageVO.class, MessagePO.class);
		p.setCurPageData(cvt.fromPOList(lst));
		return p;

	}

	@Override
	public List<MsgBusMainTypeVO> getAllMsgBusTypeByMainType() {
		List<BusTypeDicVO> dics =  getAllMsgBusType();
		List<MsgBusMainTypeVO> mainTypes = new ArrayList<MsgBusMainTypeVO>();
		for (BusTypeDicVO dic : dics){
			addBusTypeDic(dic, mainTypes);
		}
		
		return mainTypes;
	}
	
	private void addBusTypeDic(BusTypeDicVO dic, List<MsgBusMainTypeVO> mainTypes){
		boolean added = false;
		for (MsgBusMainTypeVO m : mainTypes){
			if (dic.getMainType() == m.getMainType()){
				m.addBusTypeDic(dic);
				added = true;
			}
		}
		
		if (!added){
			MsgBusMainTypeVO m = new MsgBusMainTypeVO();
			m.setMainType(dic.getMainType());
			m.addBusTypeDic(dic);
			mainTypes.add(m);
		}
		
	}
	
	@Override
	public ServiceResult getMsgConfig(String msgBusCode) {
		try{
			BusTypeDicPO dic = this.busTypeDicDAO.getBusTypeDic(msgBusCode);
			String template = "";
			if (dic.getSupportPhoneMsg() == 1){
				PhoneMsgTemplatePO t = this.phoneMsgTemplateDAO.getPhoneMsgTemplate(msgBusCode);
				template = t.getTemplate();
			}else if (dic.getSupportNetMsg() == 1){
				NetMsgTemplatePO t = this.netMsgTemplateDAO.getNetMsgTemplate(msgBusCode);
				template = JSONObject.fromObject(t.getTemplate()).optString(BaiduMessage.MSG_KEY_DESC);
			}
			final MsgCfgVO cfg = new MsgCfgVO();
			cfg.setMsgBusCode(msgBusCode);
			cfg.setMsgBusName(dic.getBusName());
			cfg.setSupportNetMsg(dic.getSupportNetMsg() == 1 ? true : false);
			cfg.setSupportPhoneMsg(dic.getSupportPhoneMsg() == 1 ? true : false);
			cfg.setTemplate(template);
			return new ServiceResult(){

				@Override
				public boolean isSuccess() {
					return true;
				}

				@Override
				public JSONObject getJSON() {
					return JSONObject.fromObject(cfg);
				}
				
			};
			
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，读取消息配置失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，读取消息配置失败！");
			return sr;
		}
	}
	@Override
	public ServiceResult saveMsgConfig(String msgBusCode, boolean supportPhoneMsg,
			boolean supportNetMsg, String template) {
		try{
			this.busTypeDicDAO.updateBusTypeDic(msgBusCode, supportPhoneMsg, supportNetMsg);
			if (supportPhoneMsg){
				PhoneMsgTemplatePO p = new PhoneMsgTemplatePO();
				p.setBusTypeCode(msgBusCode);
				p.setTemplate(template);
				this.phoneMsgTemplateDAO.addOrUpdPhoneMsgTemplate(p);
			}
			if (supportNetMsg){
				BaiduMessage bm = new BaiduMessage();
				bm.setTitle(MsgTemplateConstants.NET_MSG_TITLE);
				bm.setDescription(template);
				bm.setMsgBusCode(msgBusCode);
				NetMsgTemplatePO n = new NetMsgTemplatePO();
				n.setBusTypeCode(msgBusCode);
				n.setTemplate(bm.toString());
				this.netMsgTemplateDAO.addOrUpdNetMsgTemplate(n);
			}
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


}
