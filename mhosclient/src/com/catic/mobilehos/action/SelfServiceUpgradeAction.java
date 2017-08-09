package com.catic.mobilehos.action;  

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.action.BaseAction;
import com.catic.mobilehos.pay.entity.PayDictionary;
import com.catic.mobilehos.pay.entity.PayTerminal;
import com.catic.mobilehos.po.SelfServiceVersionPO;
import com.catic.mobilehos.service.SelfServiceVersionService;
import com.catic.mobilehos.utils.Page;

/**  
 * 类说明:自助设备升级管理action
 * @author 朱伟权
 * 创建时间: 2017-7-6 上午9:01:24
 */
@Controller
@Scope("prototype")
public class SelfServiceUpgradeAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	@Resource
	private SelfServiceVersionService selfServiceVersionService;
	
	private List<SelfServiceVersionPO> versionList;
	private List<PayTerminal> ptlist;
	private SelfServiceVersionPO po;
	private PayTerminal payTer;
	private String terminalId;// 设备id
	private String upgradeType;// 升级类型，0为主程序升级，1为守护程序升级
	private String versionCode;// 版本code
	
	private File upgradePkg;// 上传的升级包
	private String upgradePkgFileName;// 上传升级包的文件名
	
	/**
	 * 查询自助终端发布版本
	 * @return
	 */
	public String findAllInfo(){
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo="1";
			}
			versionList = selfServiceVersionService.findAll(page, po, true);
			if(versionList != null && versionList.size() > 0){
				page = new Page(Integer.parseInt(pageNo), 10, versionList.get(0).getCount());
				versionList=selfServiceVersionService.findAll(page, po, true);
				if(versionList != null && versionList.size() > 0){
					for (SelfServiceVersionPO version : versionList) {
						SelfServiceVersionPO countUpgrade = selfServiceVersionService.countUpgrade(version);
						if(countUpgrade != null){
							// 总台数、已升级台数、未升级台数
							version.setAllSum(countUpgrade.getAllSum());
							version.setUpgradedSum(countUpgrade.getUpgradedSum());
							version.setNotupgradeSum(countUpgrade.getNotupgradeSum());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查看设备版本统计台数链接对应的终端
	 * @return
	 */
	public String toUpgradeCountDetail(){
		request.setAttribute("facilityType", payTer.getFacilityType());
		request.setAttribute("upgradeType", upgradeType);
		request.setAttribute("versionCode", versionCode);
		request.setAttribute("isLargerThan", payTer.getIsLargerThan());
		return "toUpgradeCountDetail";
	}
	
	/**
	 * 查询自助终端当前版本
	 * @return
	 */
	public String findFacilityVersion(){
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo="1";
			}
			ptlist = payTerBiz.findFacilityVersion(page, payTer);
			if(ptlist != null && ptlist.size() > 0){
				page = new Page(Integer.parseInt(pageNo), 10, ptlist.get(0).getCount());
				ptlist=payTerBiz.findFacilityVersion(page, payTer);
				Map<String, String> facilityTypeMap = getFacilityTypeMap();
				if(ptlist != null && ptlist.size() > 0){
					for (PayTerminal terminal : ptlist) {
						terminal.setFacilityType(facilityTypeMap.get(terminal.getFacilityType()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "facilityVersion";
	}
	
	/**
	 * 查看终端版本详情
	 * @return
	 */
	public String toFacilityVersionDetail(){
		try {
			payTer = payTerBiz.findById(Integer.parseInt(terminalId));
			String master_version_code = payTer.getMaster_version_code();
			String daemon_version_code = payTer.getDaemon_version_code();
			if(!StringUtils.isBlank(master_version_code)){
				// 主程序版本信息
				request.setAttribute("master", selfServiceVersionService.findByVersioncode("0", payTer.getFacilityType(), Integer.parseInt(master_version_code)));
			}
			if(!StringUtils.isBlank(daemon_version_code)){
				// 守护程序版本信息
				request.setAttribute("daemon", selfServiceVersionService.findByVersioncode("1", payTer.getFacilityType(), Integer.parseInt(daemon_version_code)));
			}
			Map<String, String> facilityTypeMap = getFacilityTypeMap();
			if(payTer != null){
				payTer.setFacilityType(facilityTypeMap.get(payTer.getFacilityType()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "facilityVersionDetail";
	}
	
	/**
	 * 跳转到查看历史升级记录页面
	 * @return
	 */
	public String toVersionHistory(){
		try {
			request.setAttribute("terminalId", terminalId);
			request.setAttribute("upgradeType", upgradeType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "versionHistory";
	}
	
	/**
	 * 历史升级记录列表
	 * @return
	 */
	public String versionHistoryList(){
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo="1";
			}
			if(StringUtils.isBlank(terminalId)){
				versionList = selfServiceVersionService.findAll(page, po);
			}else{
				versionList = selfServiceVersionService.findUpgradeHistory(page, terminalId, po.getUpgradeType());
			}
			if(versionList != null && versionList.size() > 0){
				page = new Page(Integer.parseInt(pageNo), 10, versionList.get(0).getCount());
				if(StringUtils.isBlank(terminalId)){
					versionList=selfServiceVersionService.findAll(page, po);
				}else{
					versionList = selfServiceVersionService.findUpgradeHistory(page, terminalId, po.getUpgradeType());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "versionHistoryList";
	}
	
	/**
	 * 检查勾选的程序版本有几个需要激活
	 */
	public void checkActivate(){
		// 勾选激活的设备版本id
		String xzsj = request.getParameter("xzsj");
		if(!StringUtils.isBlank(xzsj)){
			int needActivateCount = 0;// 需要激活的设备版本个数
			List<SelfServiceVersionPO> list = selfServiceVersionService.findByIds(xzsj);
			if(list != null && list.size() > 0){
				for (SelfServiceVersionPO selfServiceVersionPO : list) {
					if("0".equals(selfServiceVersionPO.getActivationStatus())){
						needActivateCount++;
					}
				}
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", needActivateCount);
			jsonObj(jsonObject.toString());
		}
	}
	
	/**
	 * 激活指定设备版本
	 */
	public void activateVersions(){
		// 勾选激活的设备版本id
		String xzsj = request.getParameter("xzsj");
		if(!StringUtils.isBlank(xzsj)){
			boolean updateResult = selfServiceVersionService.activateVersions(xzsj);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", updateResult);
			jsonObj(jsonObject.toString());
		}
	}
	
	/**
	 * 上传升级包
	 */
	public void uploadPkg(){
		JSONObject result = new JSONObject();
		try{
			if(po == null || StringUtils.isBlank(po.getUpgradeType()) || StringUtils.isBlank(po.getFacilityType()) 
					|| StringUtils.isBlank(po.getVersionName()) || po.getVersionCode() == null/* || StringUtils.isBlank(po.getContent())*/){
				result.put("end", "error");
				result.put("data", "请填写完整信息");
				jsonObj(result.toString());
				return;
			}
			if(StringUtils.isBlank(po.getForceUpgrade())){
				po.setForceUpgrade("0");
			}
			if(StringUtils.isBlank(po.getActivationStatus())){
				po.setActivationStatus("0");
			}
			if(po.getVersionCode() <= 0){
				result.put("end", "error");
				result.put("data", "版本号必须为正整数");
				jsonObj(result.toString());
				return;
			}
			if(upgradePkg == null){
				result.put("end", "error");
				result.put("data", "请选择上传的升级包");
				jsonObj(result.toString());
				return;
			}
			
			// 检验版本号
			boolean exists = selfServiceVersionService.checkVersionCode(po);
			if(exists){
				result.put("end", "error");
				result.put("data", "已存在大于或者等于当前版本号的设备版本，请检查版本号");
				jsonObj(result.toString());
				return;
			}
			
			// 保存升级包文件
			String root = ServletActionContext.getServletContext().getRealPath("/upgradePkg");
			File folder = new File(root);
			// 如果文件夹不存在，则创建
			if(!folder.exists()){
				folder.mkdirs();
			}
			File saveFile = new File(root, upgradePkgFileName);
	        FileUtils.copyFile(upgradePkg, saveFile);
	        
	        // 保存版本记录
	        po.setFilePath("/upgradePkg/" + upgradePkgFileName);
			boolean saveResult = selfServiceVersionService.save(po);
			if(saveResult){
				result.put("end", "ok");
				result.put("data", "恭喜，上传成功！");
			}else{
				result.put("end", "error");
				result.put("data", "上传失败，请重新尝试");
			}
			jsonObj(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("end", "error");
			result.put("data", "上传出现异常，请重新尝试");
			jsonObj(result.toString());
		}
	}
	
	/**
	 * 获取key为code，value为名称的设备类型map
	 * @throws Exception
	 */
	private Map<String, String> getFacilityTypeMap() throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		List<PayDictionary> list = payDictionaryBiz.findAll("facility_type");
		if(list != null && list.size() > 0){
			for (PayDictionary payDictionary : list) {
				map.put(payDictionary.getCodeNo(), payDictionary.getCodeName());
			}
		}
		return map;
	}
	
	public List<SelfServiceVersionPO> getVersionList() {
		return versionList;
	}

	public void setVersionList(List<SelfServiceVersionPO> versionList) {
		this.versionList = versionList;
	}

	public SelfServiceVersionPO getPo() {
		return po;
	}

	public void setPo(SelfServiceVersionPO po) {
		this.po = po;
	}

	public PayTerminal getPayTer() {
		return payTer;
	}

	public void setPayTer(PayTerminal payTer) {
		this.payTer = payTer;
	}

	public List<PayTerminal> getPtlist() {
		return ptlist;
	}

	public void setPtlist(List<PayTerminal> ptlist) {
		this.ptlist = ptlist;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getUpgradeType() {
		return upgradeType;
	}

	public void setUpgradeType(String upgradeType) {
		this.upgradeType = upgradeType;
	}

	public File getUpgradePkg() {
		return upgradePkg;
	}

	public void setUpgradePkg(File upgradePkg) {
		this.upgradePkg = upgradePkg;
	}

	public String getUpgradePkgFileName() {
		return upgradePkgFileName;
	}

	public void setUpgradePkgFileName(String upgradePkgFileName) {
		this.upgradePkgFileName = upgradePkgFileName;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

}
 