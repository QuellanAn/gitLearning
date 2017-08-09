package com.catic.mobilehos.action;  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.action.BaseAction;
import com.catic.mobilehos.pay.entity.PayDictionary;
import com.catic.mobilehos.po.SelfServiceLogPO;
import com.catic.mobilehos.service.SelfServiceLogService;
import com.catic.mobilehos.utils.Page;

/**  
 * 类说明:自助终端日志action
 * @author 朱伟权
 * 创建时间: 2017-6-27 上午10:05:00
 */
@Controller
@Scope("prototype")
public class SelfServiceLogAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	@Resource(name="selfServiceLogService")
	private SelfServiceLogService selfServiceLogService;
	
	private List<SelfServiceLogPO> logList;
	private SelfServiceLogPO po;
	
	/**
	 * 查询自助终端日志
	 * @return
	 */
	public String findAllInfo(){
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo="1";
			}
			logList = selfServiceLogService.findAll(page, po);
			if(logList != null && logList.size() > 0){
				page = new Page(Integer.parseInt(pageNo), 10, logList.get(0).getCount());
				logList=selfServiceLogService.findAll(page, po);
				replaceFacilityType(logList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 替换设备类型的code为名称
	 * @throws Exception
	 */
	private void replaceFacilityType(List<SelfServiceLogPO> loglist) throws Exception{
		if(loglist != null && loglist.size() > 0){
			Map<String, String> map = new HashMap<String, String>();
			List<PayDictionary> list = payDictionaryBiz.findAll("facility_type");
			if(list != null && list.size() > 0){
				for (PayDictionary payDictionary : list) {
					map.put(payDictionary.getCodeNo(), payDictionary.getCodeName());
				}
			}
			
			for (SelfServiceLogPO po : loglist) {
				po.setFacilityType(map.get(po.getFacilityType()));
			}
		}
	}

	public List<SelfServiceLogPO> getLogList() {
		return logList;
	}

	public void setLogList(List<SelfServiceLogPO> logList) {
		this.logList = logList;
	}

	public SelfServiceLogPO getPo() {
		return po;
	}

	public void setPo(SelfServiceLogPO po) {
		this.po = po;
	}
	
}
 