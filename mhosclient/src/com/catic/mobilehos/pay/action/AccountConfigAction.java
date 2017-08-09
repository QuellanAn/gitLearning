package com.catic.mobilehos.pay.action;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.entity.Account;
import com.catic.mobilehos.pay.entity.PayType;
import com.catic.mobilehos.pay.entity.YQInfo;
import com.catic.mobilehos.pay.util.Constant;
import com.catic.mobilehos.utils.Page;


/**
 * 资金帐户配置
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class AccountConfigAction extends BaseAction{

	private String accountCode;
	private Account account;
	private List<Account> accountList;
	private List<YQInfo> yqList;
	private List<PayType> ptList;
	private List<String>  payTypeChecked;
	/**
	 * 查询所有账户
	 * @return
	 */
	public String findAll(){
		try {
			
             if(StringUtils.isBlank(pageNo)){
            	 pageNo="1";
             }            
             accountList=accountConfigBiz.findAll(null);
			if(accountList!=null&&accountList.size()>0){
				page=new Page(Integer.parseInt(pageNo),15, accountList.get(0).getCount());
				accountList=accountConfigBiz.findAll(page);
				if(accountList!=null&&accountList.size()>0){
					for(int i=0;i<accountList.size();i++){
						StringBuilder sb=new StringBuilder();
						if(accountList.get(i).getPayType().contains("00")){
							sb.append("扫码支付   ");
						}
						if(accountList.get(i).getPayType().contains("01")){
							sb.append("刷卡支付   ");
						}						
						yqList=yQAccountBiz.findYQ(accountList.get(i).getYq_ac_id()+"");
						StringBuilder yqName=new StringBuilder();
						for(int j=0;j<yqList.size();j++){
						   yqName.append(yqList.get(j).getName()+"  ");	
						} 
						accountList.get(i).setYqName(yqName.toString());
						accountList.get(i).setPayType(sb.toString());
					}
				}
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
			account=accountConfigBiz.findByCode(accountCode);
			payTypeChecked=account.getPayTypes();
			ptList=payTypeBiz.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "modify";		
	}
	
	/**
	 * 添加账户
	 * @return
	 */
	public String addAccount(){
		
		try {
			if(account!=null){
				account.setOperator((String)request.getSession().getAttribute("userName"));//创建人
			}
			accountConfigBiz.save(account);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addSuccess";
		
	}
	
	/**
	 * 修改账户
	 * @return
	 */
	public String modifyAccount(){
		
		try { 
			   accountConfigBiz.modify(account);
			   if(StringUtils.isBlank(pageNo)){
	            	 pageNo="1";
	             }            
			    accountList=accountConfigBiz.findAll(null);
				if(accountList!=null&&accountList.size()>0){
					page=new Page(Integer.parseInt(pageNo),Constant.DEFAULT_PAGE_SIZE, accountList.get(0).getCount());
					accountList=accountConfigBiz.findAll(page);
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
		  accountConfigBiz.delete(accountCode);
	    if(StringUtils.isBlank(pageNo)){
          	 pageNo="1";
          }            
	    accountList=accountConfigBiz.findAll(null);
		if(accountList!=null&&accountList.size()>0){
				page=new Page(Integer.parseInt(pageNo),Constant.DEFAULT_PAGE_SIZE, accountList.get(0).getCount());
				accountList=accountConfigBiz.findAll(page);
			}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return SUCCESS;
	}
	
	/**
	 * 查询所有json返回
	 */
	public void findAllJson(){
		try {
			 accountList=accountConfigBiz.findAll();
			 if(accountList!=null&&accountList.size()>0){
					jsonArray=JSONArray.fromObject(accountList);
					result=resultBiz.getResult(resultBiz.SUCCESS, jsonArray.toString());
				}else{
					result=resultBiz.getResult(resultBiz.FAIL, jsonArray.toString());
				}
		} catch (Exception e) {
			e.printStackTrace();
			result=resultBiz.getResult(resultBiz.SYSTEM_BUSY, jsonArray.toString());
		}finally{
			jsonObj(result.toJson());
		}
	}
	
	
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public List<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public List<PayType> getPtList() {
		return ptList;
	}

	public void setPtList(List<PayType> ptList) {
		this.ptList = ptList;
	}

	public List<String> getPayTypeChecked() {
		return payTypeChecked;
	}

	public void setPayTypeChecked(List<String> payTypeChecked) {
		this.payTypeChecked = payTypeChecked;
	}	
	
}
