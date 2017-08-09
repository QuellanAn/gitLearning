package com.catic.mobilehos.action;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.GlobalConstants;
import com.catic.mobilehos.po.InfoCatPO;
import com.catic.mobilehos.service.HospitalInfoService;
import com.catic.mobilehos.service.ServiceResult;
import com.catic.mobilehos.service.vo.HosPubInfoCatMenuVO;
import com.catic.mobilehos.service.vo.HosPubInfoVO;
import com.catic.mobilehos.service.vo.InfoCatVO;
import com.catic.mobilehos.service.vo.Page;

/**
 * 后台医院咨讯管理(发布，查询）
 * @author xieweipeng
 *
 */
public class HosInfoMngAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private final int DEFAULT_PAGESIZE = 10;
	
	private HospitalInfoService hospitalInfoService;
	
	/**
	 * 资讯id
	 */
	private String infoid;
	
	/**
	 * 资讯标题
	 */
	private String subject;
	
	/**
	 * 资讯图片
	 */
	private File image;
	
	/**
	 * 资讯图片文件名
	 */
	private String imageFileName;
	
	/**
	 * 资讯图片类型
	 */
	private String imageContentType;
	
	/**
	 * 资讯图片url
	 */
	private String imageurl;
	
	/**
	 * 资讯简要说明
	 */
	private String concise;
	
	/**
	 * 资讯内容
	 */
	private String content;
	
	/**
	 * 资讯类目编码
	 */
	private String infocatcode;
	
	/**
	 * 资讯类型（健康，新闻）
	 */
	private int infotype;
	
	/**
	 * 期望发布日期
	 */
	private String expdate;
	
	private String editor;
	
	private String approver;
	
	/**
	 * 查询的开始期望发布日期
	 */
	private String startexpdate;
	
	/**
	 * 查询的结束发布日期
	 */
	private String endexpdate;
	
	/**
	 * 发布日期
	 */
	private String pubdate;
	
	private int isMain;
	
	private java.sql.Date expSQLDate;
	
	private java.sql.Date startExpSQLDate;
	
	private java.sql.Date endExpSQLDate;
	
	/**
	 * 资讯类目列表
	 */
	private List<InfoCatVO> infoCats;
	
	private List<InfoCatPO> infoCatPO;
	
	/**
	 * 资讯状态
	 */
	private int status;
	
	private Page<HosPubInfoVO> pageBean;
	
	private int page;
	
	private String updateaction = "";
	
	/**
	 * 添加新资讯的标记
	 */
	private final String UPD_ACT_ADD = "add";
	
	/**
	 * 编辑资讯的标记
	 */
	private final String UPD_ACT_EDIT = "edit";
	
	/**
	 * 资讯类目菜单
	 */
	private HosPubInfoCatMenuVO catMenu;

	/**
	 * 资讯类目名
	 */
	private String infocatname;
	
	private HosPubInfoVO hosPubInfoVO;
	
	

	public String getInfoid() {
		return infoid;
	}

	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}
	
	public HosPubInfoVO getHosPubInfoVO() {
		return hosPubInfoVO;
	}

	public void setHosPubInfoVO(HosPubInfoVO hosPubInfoVO) {
		this.hosPubInfoVO = hosPubInfoVO;
	}

	public String getStartexpdate() {
		return startexpdate;
	}

	public int getIsMain() {
		return isMain;
	}

	public void setIsMain(int isMain) {
		this.isMain = isMain;
	}

	public void setStartexpdate(String startExpDate) {
		this.startexpdate = startExpDate;
	}

	public String getEndexpdate() {
		return endexpdate;
	}

	public void setEndexpdate(String endExpDate) {
		this.endexpdate = endExpDate;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	
	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	
	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getConcise() {
		return concise;
	}

	public void setConcise(String concise) {
		this.concise = concise;
	}
	
	public String getInfocatcode() {
		return infocatcode;
	}

	public void setInfocatcode(String infocatcode) {
		this.infocatcode = infocatcode;
	}
	
	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getInfocatname() {
		return infocatname;
	}

	public void setInfocatname(String infocatname) {
		this.infocatname = infocatname;
	}

	public int getInfotype() {
		return infotype;
	}

	public void setInfotype(int infotype) {
		this.infotype = infotype;
	}
	
	public String getExpdate() {
		return expdate;
	}

	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<InfoCatVO> getInfoCats() {
		return infoCats;
	}

	public List<InfoCatPO> getInfoCatPO() {
		return infoCatPO;
	}

	public void setInfoCatPO(List<InfoCatPO> infoCatPO) {
		this.infoCatPO = infoCatPO;
	}

	public void setInfoCats(List<InfoCatVO> infoCats) {
		this.infoCats = infoCats;
	}
	
	public Page<HosPubInfoVO> getPageBean() {
		return pageBean;
	}


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPageBean(Page<HosPubInfoVO> pageBean) {
		this.pageBean = pageBean;
	}
	
	
	public String getUpdateaction() {
		return updateaction;
	}

	public void setUpdateaction(String updateaction) {
		this.updateaction = updateaction;
	}

	public HospitalInfoService getHospitalInfoService() {
		return hospitalInfoService;
	}

	public void setHospitalInfoService(HospitalInfoService hospitalInfoService) {
		this.hospitalInfoService = hospitalInfoService;
	}
	
	public HosPubInfoCatMenuVO getCatMenu() {
		return catMenu;
	}

	public void setCatMenu(HosPubInfoCatMenuVO catMenu) {
		this.catMenu = catMenu;
	}

	public void validateAddHosInfo(){
		log.debug("validateAddHosInfo start.");
		validateAddOrEdit(true);
	}
	
	private void validateAddOrEdit(boolean isAdd){
		if (infotype < 1 || infotype > 2){
			this.addFieldError("infotype", "请选择内容大类!");
		}
		if (StringUtils.isBlank(subject)){
			this.addFieldError("subject", "标题不能为空!");
		}
		if (isAdd && StringUtils.isBlank(imageFileName)){
			this.addFieldError("image", "请选择一个图片文件!");
		}
		if (StringUtils.isBlank(this.concise)){
			this.addFieldError("concise", "请输入简要内容!");
		}
		if (StringUtils.isBlank(this.content)){
			this.addFieldError("content", "内容不能为空!");
		}
		if (StringUtils.isBlank(this.expdate)){
			this.addFieldError("expdate", "预期发布日期不能为空!");
		}else{
			this.expSQLDate = toSQLDate(this.expdate);
			if (expSQLDate == null){
				this.addFieldError("expdate", "预期发布日期的格式不正确！正确格式为："
						+ GlobalConstants.DEF_DATEFORMAT);
			}
		}

	}
	
	public void validateEditHosInfo(){
		log.debug("validateEditHosInfo start.");
		validateAddOrEdit(false);
	}

	
	/**
	 * 载入资讯添加页
	 * @return
	 */
	public String showAddHosInfoPage(){
		try{
			updateaction = UPD_ACT_ADD;
//			infotype = infotype == 0? 1 : infotype;
			infotype = 0;
			infoCats = this.hospitalInfoService.getInfoCatCodes(infotype);
//			infoCatPO = this.hospitalInfoService.getInfoCat();
			if (StringUtils.isBlank(infoid)){
				infoid = (String)this.hospitalInfoService.getInfoID().getObject();
			}
			return SUCCESS;
		}catch(Exception e){
			log.error(null, e);
			return ERROR;
		}
	}
	
	/**
	 * 载入资讯编辑页
	 * @return
	 */
	public String showEditHosInfoPage(){
		try{
			updateaction = UPD_ACT_EDIT;
//			infotype = infotype == 0? 1 : infotype;
			infotype=0;
			HosPubInfoVO v = this.hospitalInfoService.getHosPubInfo(
					this.request.getServletContext(), infoid);
			infoCats = this.hospitalInfoService.getInfoCatCodes(infotype);
			this.subject = v.getSubject();
			this.concise = v.getConcise();
			this.content = v.getHtml();
			this.infocatcode = v.getInfoCatCode();
//			this.infotype = v.getInfoType();
			this.isMain = v.getIsMain();
			this.status = v.getStatus();
			this.imageurl = v.getImageUrl();
			this.expdate = v.getExpPubDateDesc();
			
			return SUCCESS;
		}catch(Exception e){
			log.error(null, e);
			return ERROR;
		}
		
	}
	


	/**
	 * 添加新资讯
	 * @return
	 */
	public String addHosInfo(){
		try{
			String adminUserName = (String) request.getSession().getAttribute("userName");
			ServiceResult sr = hospitalInfoService.createNewHosInfo(
					infoid, subject, image, concise, content, isMain
					, infocatcode, expSQLDate, adminUserName);
			if (sr.isSuccess()){
				this.infoid = null;
				return SUCCESS;
			}else{
				this.addActionError(sr.getFailedInfo().getString(ServiceResult.MSG));
				return ERROR;
			}
		}catch(Exception e){
			log.error(null, e);
			this.addActionError("系统异常，发布资讯失败！");
			return ERROR;
		}
		
	}
	
	
	
	/**
	 * 编辑资讯
	 * @return
	 */
	public String editHosInfo(){
		try{
			ServiceResult sr = hospitalInfoService.editHosInfo(
					infoid, subject, image, concise, content, isMain
					, infocatcode, expSQLDate);
			if (sr.isSuccess()){
				return SUCCESS;
			}else{
				this.addActionError(sr.getFailedInfo().getString(ServiceResult.MSG));
				return ERROR;
			}
		}catch(Exception e){
			log.error(null, e);
			this.addActionError("输入的参数格式错误，发布资讯失败！");
			return ERROR;
		}
		
	}
	
	
	/**
	 * 删除资讯
	 * @return
	 */
	public String deleteHosInfo(){
		try{
			hospitalInfoService.deleteHosPubInfo(infoid);
			pageBean = hospitalInfoService.getUnApprovedHosPubInfoList(infotype
					,infocatcode, page, DEFAULT_PAGESIZE);
			return SUCCESS;
		}catch(Exception e){
			log.error(null, e);
			this.addActionError("系统异常，删除失败！");
			return ERROR;
		}
		
	}
	
	/**
	 * 删除已发布资讯
	 * @return
	 */
	public void deleteHosPub(){
		try{
			hospitalInfoService.deleteHosPubInfo(infoid);
		}catch(Exception e){
			log.error(null, e);
		}
		
	}

	
	/**
	 * 预览未创建完成的资讯内容
	 * @return
	 */
	public String prevTempHosInfo(){
		try{
			return SUCCESS;
		}catch(Exception e){
			log.error(null, e);
			this.addActionError("系统异常，预览失败！");
			return ERROR;
		}
		
	}
	
	/**
	 * 预览已创建未发布的资讯内容
	 * @return
	 */
	public String prevUnpubHosInfo(){
		try{
			HosPubInfoVO v = this.hospitalInfoService.getHosPubInfo(request.getServletContext(), infoid);
			this.hosPubInfoVO = v;
			this.subject = v.getSubject();
			this.concise = v.getConcise();
			this.expdate = v.getExpPubDateDesc();
			this.pubdate = v.getPubDateDesc();
			this.content = v.getHtmlContent();
			this.status = v.getStatus();
			return SUCCESS;
		}catch(Exception e){
			log.error(null, e);
			this.addActionError("系统异常，预览失败！");
			return ERROR;
		}
	}
	
	
	/**
	 * 上传资讯缩略图片
	 */
	public void uploadImage(){
		try{
			ServiceResult sr = hospitalInfoService.saveInfoImage(request.getServletContext(), infoid, image);
			response.setContentType("text/html; charset=UTF-8");
			this.writeServiceReuslt(sr);
		}catch(Exception e){
			log.error(null, e);
		}
	}
	
	
	/**
	 * 获得指定类型的资讯目录列表
	 */
	public void getInfoCatsJson(){
		try{
			infoCats = this.hospitalInfoService.getInfoCatCodes(infotype);
			JSONArray jarr = JSONArray.fromObject(infoCats);
			this.writeJSON(jarr);
		}catch(Exception e){
			log.error(null, e);
		}
	}
	
	
	/**
	 * 获得新发布及未通过的资讯列表
	 * @return
	 */
	public String getUnApprovedHosPubInfoList(){
		try{
			pageBean = hospitalInfoService.getUnApprovedHosPubInfoList(
					infotype, this.infocatcode, page , DEFAULT_PAGESIZE);
			return SUCCESS;
		}catch(Exception e){
			log.error(null, e);
			return ERROR;
		}
	}
	
	
	public void validateGetWaitAppHosPubInfoList(){
		if (StringUtils.isNotBlank(this.startexpdate)){
			this.startExpSQLDate = this.toSQLDate(this.startexpdate);
			if (this.startExpSQLDate == null){
				this.addFieldError("", "日期格式不正确！");
			}
		}
		if (StringUtils.isNotBlank(this.endexpdate)){
			this.endExpSQLDate = this.toSQLDate(this.endexpdate);
			if (this.endExpSQLDate == null){
				this.addFieldError("", "日期格式不正确！");
			}
		}
	}

	
	/**
	 * 获取等待审批的资讯列表
	 * @return
	 */
	public String getWaitAppHosPubInfoList(){
		try{
			pageBean = this.hospitalInfoService.getWaitAppHosPubInfoList(
					infotype == 0 ? null :infotype
					, infocatcode, startExpSQLDate
					, endExpSQLDate,editor, page, DEFAULT_PAGESIZE);
			Map<String, String> paras = new TreeMap<String, String>();
			paras.put("infotype", infotype == 0 ? null :String.valueOf(infotype));
			paras.put("infocatcode", infocatcode);
			paras.put("startExpDate", startexpdate);
			paras.put("endExpDate", endexpdate);
			String baseUrl = "hosinfomng/getWaitAppHosPubInfoList";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		}catch(Exception e){
			log.error(null, e);
			return ERROR;
		}
	}
	
	
	public void validateGetApprovedHosPubInfoList(){
		validateGetWaitAppHosPubInfoList();
	}

	
	/**
	 * 获取已审批通过的资讯列表
	 * @return
	 */
	public String getApprovedHosPubInfoList(){
		try{
			String show=request.getParameter("isMain");
			if(StringUtils.isBlank(show)){
				show="2";
			}
			pageBean = this.hospitalInfoService.getApprovedHosPubInfoList(
					infotype, infocatcode, Integer.parseInt(show), startExpSQLDate
					, endExpSQLDate,editor,approver, page, DEFAULT_PAGESIZE);
			Map<String, String> paras = new TreeMap<String, String>();
			paras.put("infotype", infotype == 0 ? null :String.valueOf(infotype));
			paras.put("infocatcode", infocatcode);
			paras.put("startExpDate", startexpdate);
			paras.put("endExpDate", endexpdate);
			String baseUrl = "hosinfomng/getApprovedHosPubInfoList";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		}catch(Exception e){
			log.error(null, e);
			return ERROR;
		}
	}


	
	/**
	 * 获取资讯目录菜单
	 * @return
	 */
	public String getUnApprovedCatMenu(){
		try{
			this.catMenu = hospitalInfoService.getUnApprovedCatMenu();
			return SUCCESS;
		}catch(Exception e){
			log.error(null, e);
			return ERROR;
		}
	}
	
	public void validateEditCatName(){
		if (StringUtils.isBlank(infocatcode)){
			this.writeServiceReuslt(ServiceResult.getFailedInstance("", "无效的请求参数！"));
		}
		if (StringUtils.isBlank(infocatname)){
			this.writeServiceReuslt(ServiceResult.getFailedInstance("", "类别名不能为空！"));
		}

	}
	
	/**
	 * 编辑资讯目录名
	 */
	public void editCatName(){
		try{
			ServiceResult sr = hospitalInfoService.editCatName(infocatcode, infocatname);
			this.writeServiceReuslt(sr);
		}catch(Exception e){
			log.error(null, e);
		}
	}
	
	public void validateAddCat(){
		if (!InfoCatVO.isValidInfoType(infotype)){
			this.writeServiceReuslt(ServiceResult.getFailedInstance("", "咨讯大类无效！"));
		}
		if (StringUtils.isBlank(infocatname)){
			this.writeServiceReuslt(ServiceResult.getFailedInstance("", "类别名不能为空！"));
		}
	}
	
	/**
	 * 添加资讯目录
	 */
	public void addCat(){
		try{
			ServiceResult sr = hospitalInfoService.addCat(infocatname, infotype);
			this.writeServiceReuslt(sr);
		}catch(Exception e){
			log.error(null, e);
		}
	}
	
	/**
	 * 删除资讯目录
	 */
	public void deleteCat(){
		try{
			ServiceResult sr = hospitalInfoService.deleteCat(infocatcode);
			this.writeServiceReuslt(sr);
		}catch(Exception e){
			log.error(null, e);
		}
	}
	
	/**
	 * 提交审核通过操作
	 */
	public void submitApproved(){
		try{
			String adminUserName = (String) request.getSession().getAttribute("userName");
			ServiceResult sr = hospitalInfoService.submitApproved(infoid, adminUserName);
			this.writeServiceReuslt(sr);
		}catch(Exception e){
			log.error(null, e);
		}
	}
	
	/**
	 * 提交审核未通过操作
	 */
	public void submitUnApproved(){
		try{
			String adminUserName = (String) request.getSession().getAttribute("userName");
			ServiceResult sr = hospitalInfoService.submitUnApproved(infoid, adminUserName);
			this.writeServiceReuslt(sr);
		}catch(Exception e){
			log.error(null, e);
		}
	}
	
	
	/**
	 * 提交取消咨讯操作
	 */
	public void submitCancel(){
		try{
			ServiceResult sr = hospitalInfoService.submitCancel(infoid);
			if (!sr.isSuccess()){
				this.addActionError("提交取消操作失败！");
			}
		}catch(Exception e){
			log.error(null, e);
		}
	}
	

}
