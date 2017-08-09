package com.catic.mobilehos.pay.entity;

import java.sql.Timestamp;

public class PayTerminal{
	private int id;
	private String facilityId;
	private String collectorId;
	private String facilityName;
	private String district;
	private String facilityStatus;
	private String putAddress;
	private Timestamp createTime;
	private String payScene;
	private String remark;
	private String pay_scene;
	private String facilityType;// 终端类型
	
	private Integer  count;
	private String name;
	private String sceneName;
	
	private String master_version_code;// 主程序版本version_code
	private String daemon_version_code;// 守护程序版本version_code
	private String mainProgramVersion;// 主程序版本名称
	private String daemonVersion;// 守护程序版本名称
	private String isLargerThan;// 是否大于等于，1表示是，-1表示小于
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	public String getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(String collectorId) {
		this.collectorId = collectorId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getFacilityStatus() {
		return facilityStatus;
	}

	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
	}

	public String getPutAddress() {
		return putAddress;
	}

	public void setPutAddress(String putAddress) {
		this.putAddress = putAddress;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPayScene() {
		return payScene;
	}

	public void setPayScene(String payScene) {
		this.payScene = payScene;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}
	
	public String getPay_scene() {
		return pay_scene;
	}

	public void setPay_scene(String pay_scene) {
		this.pay_scene = pay_scene;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public String getMainProgramVersion() {
		return mainProgramVersion;
	}

	public void setMainProgramVersion(String mainProgramVersion) {
		this.mainProgramVersion = mainProgramVersion;
	}

	public String getDaemonVersion() {
		return daemonVersion;
	}

	public void setDaemonVersion(String daemonVersion) {
		this.daemonVersion = daemonVersion;
	}

	public String getMaster_version_code() {
		return master_version_code;
	}

	public void setMaster_version_code(String master_version_code) {
		this.master_version_code = master_version_code;
	}

	public String getDaemon_version_code() {
		return daemon_version_code;
	}

	public void setDaemon_version_code(String daemon_version_code) {
		this.daemon_version_code = daemon_version_code;
	}

	public String getIsLargerThan() {
		return isLargerThan;
	}

	public void setIsLargerThan(String isLargerThan) {
		this.isLargerThan = isLargerThan;
	}

}
