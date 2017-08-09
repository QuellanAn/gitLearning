package com.catic.mobilehos.pay.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.entity.Account;
import com.catic.mobilehos.pay.entity.PayChannel;
import com.catic.mobilehos.pay.entity.PayScene;
import com.catic.mobilehos.pay.entity.PayType;
import com.catic.mobilehos.pay.entity.YQInfo;
import com.catic.mobilehos.pay.util.Constant;
import com.catic.mobilehos.utils.Page;

/**
 * 支付通道配置
 * 
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class PayChannelAction extends BaseAction {
	private PayChannel payChannel;
	private List<PayChannel> pcList;
	private String yqID;
	private String ptCode;
	private String sCode;
	private String accountCode;
	private String cCode;
	private String cID;
	private String cStatus;
	private List<PayType> payTypeList;
	private List<YQInfo> yqList;
	private List<PayScene> paySceneList;
	private List<Account> accountList;
	/**
	 * 分页查询
	 * 
	 * @return
	 */
	public String findAll() {
		try {
			if (StringUtils.isBlank(pageNo)) {
				pageNo = "1";
			}
			payChannel = new PayChannel();
			YQInfo yq=yQConfigBiz.findById(yqID);
			if(yq!=null){
				payChannel.setYqCode(yq.getYqCode());
			}
			payChannel.setPtCode(ptCode);
			payChannel.setSCode(sCode);
			payChannel.setAccountCode(accountCode);
			payChannel.setCStatus(cStatus);
			pcList = payChannelBiz.findAll(payChannel, null);
			if(pcList!=null&&pcList.size()>0){
				page=new Page(Integer.parseInt(pageNo),Constant.DEFAULT_PAGE_SIZE, pcList.get(0).getCount());
				pcList = payChannelBiz.findAll(payChannel, page);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	/**
	 * 跳转到添加界面
	 * @return
	 */
	public String toAdd(){
		return "toAdd";
	}
	
	/**
	 *添加
	 * @return
	 */
	public String add(){
		try {
			payChannel.setCID(com.catic.mobilehos.utils.Common.getUUID());
			YQInfo yq=yQConfigBiz.findById(payChannel.getYqCode());//此处上传是yqId
			if(yq!=null){
				payChannel.setYqCode(yq.getYqCode());//设置yqCode
			}
			payChannel.setCreator((String)request.getSession().getAttribute("userName"));
			if(payChannelBiz.save(payChannel)){
				return "save";
			}else{
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return "";
	}
	
	
	/**
	 *跳转到修改界面
	 * @return
	 */
	public String toModify(){
		try {
			payChannel=payChannelBiz.findByID(cID);
			YQInfo yq=yQConfigBiz.findByCode(payChannel.getYqCode());
			if(yq!=null){
				payChannel.setYqCode(yq.getYqId()+"");
			}
			payTypeList=payTypeBiz.findAll();
		 yqList = yQConfigBiz.findAllyq();;
		 paySceneList=paySceneBiz.findAll();
		 accountList=accountConfigBiz.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toModify";
		
	}
	
	/**
	 * 更改
	 * @return
	 */
	public String modify(){
		try {
			YQInfo yq=yQConfigBiz.findById(payChannel.getYqCode());//此处上传是yqId
			if(yq!=null){
				payChannel.setYqCode(yq.getYqCode());//设置yqCode
			}
			if(payChannelBiz.modify(payChannel)){
				pcList=payChannelBiz.findAll(payChannel, page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modify";
		
	}
	public PayChannel getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(PayChannel payChannel) {
		this.payChannel = payChannel;
	}

	public List<PayChannel> getPcList() {
		return pcList;
	}

	public void setPcList(List<PayChannel> pcList) {
		this.pcList = pcList;
	}

	public String getPtCode() {
		return ptCode;
	}

	public void setPtCode(String ptCode) {
		this.ptCode = ptCode;
	}

	public String getSCode() {
		return sCode;
	}

	public void setSCode(String sCode) {
		this.sCode = sCode;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getCCode() {
		return cCode;
	}

	public void setCCode(String cCode) {
		this.cCode = cCode;
	}

	public String getYqID() {
		return yqID;
	}

	public void setYqID(String yqID) {
		this.yqID = yqID;
	}

	public String getsCode() {
		return sCode;
	}

	public void setsCode(String sCode) {
		this.sCode = sCode;
	}

	public String getCID() {
		return cID;
	}

	public void setCID(String cID) {
		this.cID = cID;
	}

	public String getCStatus() {
		return cStatus;
	}

	public void setCStatus(String cStatus) {
		this.cStatus = cStatus;
	}

	public List<PayType> getPayTypeList() {
		return payTypeList;
	}

	public void setPayTypeList(List<PayType> payTypeList) {
		this.payTypeList = payTypeList;
	}

	public List<YQInfo> getYqList() {
		return yqList;
	}

	public void setYqList(List<YQInfo> yqList) {
		this.yqList = yqList;
	}

	public List<PayScene> getPaySceneList() {
		return paySceneList;
	}

	public void setPaySceneList(List<PayScene> paySceneList) {
		this.paySceneList = paySceneList;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

}
