package com.catic.mobilehos.pay.biz;

import javax.annotation.Resource;

import com.catic.mobilehos.menu_role_authority.dao.IManagerDao;
import com.catic.mobilehos.menu_role_authority.dao.IMenuDao;
import com.catic.mobilehos.menu_role_authority.dao.IPopedomDao;
import com.catic.mobilehos.menu_role_authority.dao.IRoleDao;
import com.catic.mobilehos.menu_role_authority.dao.IRoleMenuDao;
import com.catic.mobilehos.pay.dao.IAccountConfigDao;
import com.catic.mobilehos.pay.dao.IAccountPayTypeDao;
import com.catic.mobilehos.pay.dao.IBillDao;
import com.catic.mobilehos.pay.dao.ICheckBillDetailsDao;
import com.catic.mobilehos.pay.dao.ICheckImportDao;
import com.catic.mobilehos.pay.dao.ICheckRecordDao;
import com.catic.mobilehos.pay.dao.ICheckSourceDao;
import com.catic.mobilehos.pay.dao.IHisCheckDao;
import com.catic.mobilehos.pay.dao.IOrderDao;
import com.catic.mobilehos.pay.dao.IPayChannelDao;
import com.catic.mobilehos.pay.dao.IPayDictionaryDao;
import com.catic.mobilehos.pay.dao.IPayLogDao;
import com.catic.mobilehos.pay.dao.IPayOrderDao;
import com.catic.mobilehos.pay.dao.IPayOvertimeDao;
import com.catic.mobilehos.pay.dao.IPaySceneDao;
import com.catic.mobilehos.pay.dao.IPayTerminalDao;
import com.catic.mobilehos.pay.dao.IPayTypeDao;
import com.catic.mobilehos.pay.dao.IRefundDao;
import com.catic.mobilehos.pay.dao.ISystermUserDao;
import com.catic.mobilehos.pay.dao.ITransFlowDao;
import com.catic.mobilehos.pay.dao.IYQAccountDao;
import com.catic.mobilehos.pay.dao.IYQConfigDao;


public class BaseBiz {
	
	@Resource
	protected IOrderDao orderDao;
	
	@Resource
	protected IPayLogDao payLogDao;
	
//	@Resource
//	protected ICheckBillDao checkBillDao;
	
	@Resource
	protected ICheckBillDetailsDao checkBillDetailsDao;
	
	@Resource
	protected IRefundDao refundDao;
	
	@Resource
	protected IPayOrderDao payOrderDao;
	
	@Resource
	protected IPayOvertimeDao payOvertimeDao;
	
	@Resource
	protected IYQConfigDao  yQConfigDao;
	
	@Resource
	protected IPayTerminalDao payTemDao;
	
	@Resource
	protected IAccountConfigDao  accountConfigDao;
	@Resource
	protected IYQAccountDao yQAccountDao;
	
	@Resource
	protected ISystermUserDao systermUserDao;

	@Resource
	protected ICheckRecordDao checkRecordDao;

	@Resource
	protected IMenuDao menuDao;
	
	@Resource
	protected IRoleDao roleDao;
	
	@Resource
	protected IRoleMenuDao roleMenuDao;
	
	@Resource
	protected IPopedomDao popedomDao;
	
	@Resource
	protected IManagerDao managerDao;
	
	@Resource
	protected ICheckImportDao checkImportDao;
	
	@Resource
	protected IHisCheckDao hisCheckDao;
	
	@Resource
	protected IPayTypeDao payTypeDao;
	
	@Resource 
	protected IPayDictionaryDao payDictionaryDao;
	
	@Resource 
	protected IPaySceneDao paySceneDao;
	
	@Resource 
	protected ICheckSourceDao checkSourceDao;
	
	@Resource 
	protected IAccountPayTypeDao accountPayTypeDao;
	
	@Resource 
	protected IPayChannelDao payChannelDao;
	
	@Resource 
	protected IBillDao billDao;
	
	@Resource 
	protected ITransFlowDao transFlowDao;
}
