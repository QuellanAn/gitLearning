package com.catic.mobilehos.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.catic.mobilehos.BusCodeConstants;
import com.catic.mobilehos.GlobalConstants;
import com.catic.mobilehos.MsgTemplateConstants;
import com.catic.mobilehos.dao.HosPubInfoDAO;
import com.catic.mobilehos.dao.HtmlImagesDAO;
import com.catic.mobilehos.dao.InfoCatDAO;
import com.catic.mobilehos.po.HosPubInfoPO;
import com.catic.mobilehos.po.HosPubInfoPO.StatByTypePO;
import com.catic.mobilehos.po.HtmlImagesPO;
import com.catic.mobilehos.po.InfoCatPO;
import com.catic.mobilehos.service.message.Message;
import com.catic.mobilehos.service.vo.HosPubInfoCatMenuVO;
import com.catic.mobilehos.service.vo.HosPubInfoVO;
import com.catic.mobilehos.service.vo.InfoCatVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.HosPubInfoVO.StatByCatVO;
import com.catic.mobilehos.utils.CompressPic;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class HospitalInfoServiceImpl implements HospitalInfoService {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private final String INFO_PUB_DIRNAME = "infopub";
	
	private final String PUB_INFO_HTML_SHORTPATH 
		= GlobalConstants.DYNAMIC_FOLDER + "/"
			+ GlobalConstants.DYNAMIC_HTML_FOLDER 
			+ "/" + INFO_PUB_DIRNAME;
	
	private final String PUB_INFO_HTMLIMG_SHORTPATH
		= GlobalConstants.DYNAMIC_FOLDER
			+ "/" + GlobalConstants.DYNAMIC_IMAGE_FOLDER
			+ "/" + INFO_PUB_DIRNAME;
	
	
	private final String NEWS_INFO_WEB_URL = "hosinfo/getnewsinfoweb?id=";
	
	private final String HEALTH_INFO_WEB_URL = "hosinfo/gethealthinfoweb?id=";

		
	private HtmlImagesDAO htmlImagesDAO;
	
	private HosPubInfoDAO hosPubInfoDAO;
	
	private InfoCatDAO infoCatDAO;
	
	private int fetchSize = 15;
	
	private final int THUMBNAIL_HEIGHT = 300;
	
	private final int THUMBNAIL_WIDTH = 400;
	
	private final int WEB_IMG_HEIGHT = 1024;
	
	private final int WEB_IMG_WIDTH = 1024;
	
	
	
	
	public int getFetchSize() {
		return fetchSize;
	}

	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	public HtmlImagesDAO getHtmlImagesDAO() {
		return htmlImagesDAO;
	}

	public void setHtmlImagesDAO(HtmlImagesDAO htmlImagesDAO) {
		this.htmlImagesDAO = htmlImagesDAO;
	}
	
	public HosPubInfoDAO getHealthInfoDAO() {
		return hosPubInfoDAO;
	}

	public void setHealthInfoDAO(HosPubInfoDAO healthInfoDAO) {
		this.hosPubInfoDAO = healthInfoDAO;
	}
	
	public InfoCatDAO getInfoCatDAO() {
		return infoCatDAO;
	}

	public void setInfoCatDAO(InfoCatDAO infoCatDAO) {
		this.infoCatDAO = infoCatDAO;
	}
	
	public HosPubInfoDAO getHosPubInfoDAO() {
		return hosPubInfoDAO;
	}

	public void setHosPubInfoDAO(HosPubInfoDAO hosPubInfoDAO) {
		this.hosPubInfoDAO = hosPubInfoDAO;
	}
	
	


	@Override
	public ServiceResult getInfoID() {
		return new ServiceResult(){

			@Override
			public boolean isSuccess() {
				return true;
			}

			@Override
			public Object getObject() {
				return UUID.randomUUID().toString();
			}
		};
	}
	
	@Override
	public HosPubInfoVO getHosPubInfo(ServletContext context, String infoId) {
		HosPubInfoPO p = this.hosPubInfoDAO.getHosInfo(infoId);
		HosPubInfoVO v = HosPubInfoVO.fromPO(p);
		v.setImageUrl(this.getInfoImageShortUrl(infoId));
		String imageRealPath = this.getInfoImageRealPath(context, infoId);
		try {
			File f = new File(imageRealPath);
			if (!f.exists()){
				FileUtils.writeByteArrayToFile(new File(imageRealPath), v.getImage());
			}
		} catch (IOException e) {
			log.error(null, e);
		}
		return v;
	}

	@Override
	public ServiceResult saveInfoHtmlImage(ServletContext context, String infoid, File f) {
		try{
			Date createDate = new Date();
			String newFileName = UUID.randomUUID().toString() + "_" 
					+ createDate.getTime() + ".jpg";
			
			String shortUrl = "/" + PUB_INFO_HTMLIMG_SHORTPATH
					+ "/" + newFileName;
			
			final String fullUrl = context.getContextPath() + shortUrl;
			String filePath = context.getRealPath("/") + shortUrl;
			
			CompressPic compress = new CompressPic();
			compress.setOutputHeight(this.WEB_IMG_HEIGHT);
			compress.setOutputWidth(this.WEB_IMG_WIDTH);
			compress.setProportion(true);
			byte[] fileData = compress.compress(new FileInputStream(f));
			FileUtils.writeByteArrayToFile(new File(filePath), fileData);
			System.out.println("位置："+filePath);
			HtmlImagesPO hi = new HtmlImagesPO();
			hi.setInfoId(infoid);
			hi.setImage(fileData);
			hi.setFilename(newFileName);
			hi.setUrl(shortUrl);
			hi.setCreateDate(new Timestamp((new Date()).getTime()));
			this.htmlImagesDAO.addHtmlImages(hi);
			return new ServiceResult(){
				@Override
				public boolean isSuccess() {
					return true;
				}

				@Override
				public JSONObject getJSON() {
					return super.getJSON();
				}

				@Override
				public Object getObject() {
					return fullUrl;
				}
			};
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，上传图片失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，上传图片失败！");
			return sr;
		}
	}
	
	public String getInfoImageShortUrl(String infoid){
		String newFileName = infoid + ".jpg";
		
		final String shortUrl = PUB_INFO_HTMLIMG_SHORTPATH
				+ "/" + newFileName;
		
		return shortUrl;
	}
	
	
	private String getInfoImageRealPath(ServletContext context, String infoid){
		String shortUrl = getInfoImageShortUrl(infoid);
		String filePath = context.getRealPath("/") + shortUrl;
		return filePath;
	}
	
	@Override
	public ServiceResult saveInfoImage(ServletContext context, String infoid,
			File f) {
		try{
			final String shortUrl = getInfoImageShortUrl(infoid);
			String filePath = getInfoImageRealPath(context, infoid);
			
			CompressPic compress = new CompressPic();
			compress.setOutputHeight(this.THUMBNAIL_HEIGHT);
			compress.setOutputWidth(this.THUMBNAIL_HEIGHT);
			compress.setProportion(true);
			byte[] fileData = compress.compress(new FileInputStream(f));
			FileUtils.writeByteArrayToFile(new File(filePath), fileData);
			
			return new ServiceResult(){
				@Override
				public boolean isSuccess() {
					return true;
				}

				@Override
				public JSONObject getJSON() {
					JSONObject j = new JSONObject();
					j.put("url", shortUrl);
					return j;
				}

				@Override
				public Object getObject() {
					return shortUrl;
				}
			};
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，上传图片失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，上传图片失败！");
			return sr;
		}
	}
	
	private Date addTimeToDate(Date target, Date time){
		Long hour = DateUtils.getFragmentInHours(time, Calendar.DAY_OF_MONTH);
		Long min = DateUtils.getFragmentInMinutes(time, Calendar.HOUR_OF_DAY);
		Long sec = DateUtils.getFragmentInSeconds(time, Calendar.MINUTE);
		Date date = new Date(target.getTime());
		date = DateUtils.setHours(date, hour.intValue());
		date = DateUtils.setMinutes(date, min.intValue());
		date = DateUtils.setSeconds(date, sec.intValue());
		return date;
	}
	

	
	
	
	
	
	@Override
	public ServiceResult editHosInfo(String infoid, String subject, File image,
			String concise, String html, int isMain, String infoCatCode,
			java.sql.Date expectedPubDate) {
		try{
			HosPubInfoPO info = new HosPubInfoPO();
			if (image != null && image.exists()){
				CompressPic compress = new CompressPic();
				compress.setOutputHeight(this.THUMBNAIL_HEIGHT);
				compress.setOutputWidth(this.THUMBNAIL_WIDTH);
				compress.setProportion(true);
				byte[] imgData = compress.compress(image);
				if (imgData != null){
					info.setImage(imgData);
				}else{
					ServiceResult sr = ServiceResult.getFailedInstance("", "压缩上传图片文件失败，请检查文件格式是否为支持的格式！");
					return sr;
				}
			}
			info.setHosInfoId(infoid);
			info.setSubject(subject);
			info.setConcise(concise);
			info.setHtmlContent(html);
			info.setHtml(getFullHtml(html));
			info.setUpdateDate(new Timestamp((new Date()).getTime()));
			info.setInfoCatCode(infoCatCode);
			info.setExpPubDate(expectedPubDate);
			info.setIsMain(isMain);
			Date pubDate = addTimeToDate(info.getExpPubDate(), new Date());
			info.setPubDate(new Timestamp(pubDate.getTime()));
			this.hosPubInfoDAO.updateHosPubInfo(info);
			
			return new ServiceResult(){
				@Override
				public boolean isSuccess() {
					return true;
				}
			};
			
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，更新资讯失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，更新资讯失败！");
			return sr;
		}
	}

	private ServiceResult getInfoList(int infoType, Date time, String upordown, final String url){
		try{
			List<HosPubInfoPO> lst = null;
			if (time == null){
				lst = this.hosPubInfoDAO.findLastPubHosInfo(infoType, this.fetchSize);
			}else if (upordown.trim().equalsIgnoreCase("up")){
				lst = this.hosPubInfoDAO.findNewHosInfoByTime(infoType, time, this.fetchSize);
			}else if (upordown.trim().equalsIgnoreCase("down")){
				lst = this.hosPubInfoDAO.findOldHosInfoByTime(infoType, time, this.fetchSize);
			}
			final List<HosPubInfoPO> lst2 = lst;
			return new ServiceResult(){
				@Override
				public boolean isSuccess() {
					return true;
				}

				@Override
				public JSONArray getJSONArray() {
					JSONArray jarr = new JSONArray();
					for (HosPubInfoPO i : lst2){
						JSONObject j = new JSONObject();
						j.put("infoid", i.getHosInfoId());
						j.put("subject", i.getSubject());
						j.put("image", Base64.encode(i.getImage()));
						j.put("concise", i.getConcise());
						j.put("htmlpath", url + i.getHosInfoId());
						j.put("pubdate", DateFormatUtils.format(i.getPubDate()
								, GlobalConstants.DEF_DATETIME_FMT));
						j.put("infocatcode", "");
						j.put("infocatname", "");
						j.put("count", String.valueOf(i.getBrowseCount()));
						
						jarr.add(j);
						
					}
					return jarr;
				}
				
			};
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，获取资讯列表失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，获取资讯列表失败！");
			return sr;
		}
		
	}
	


	private String getFullHtml(String html){
		return ServiceUtils.getFullMobileHtml(html);
	}

	@Override
	public ServiceResult createHosInfoHtml(ServletContext ctx, String id) {
		try{
			HosPubInfoPO info = this.hosPubInfoDAO.getHosInfo(id);
			final String htmlShortPath = this.createHtml(ctx, id, info.getHtml());
			this.hosPubInfoDAO.increaseBrowseCount(id);
			return new ServiceResult(){
				@Override
				public boolean isSuccess() {
					return true;
				}

				@Override
				public Object getObject() {
					return "/" + htmlShortPath;
				}
				
			};
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "获取资讯网页失败！");
			return sr;
		}
	}

	
	
	
	public String createHtml(ServletContext ctx, String id, String html) throws IOException{
		final String htmlFileName = id + ".html";
		final String htmlShortPath = PUB_INFO_HTML_SHORTPATH + "/" + htmlFileName;
		String htmlPath = ctx.getRealPath("/") + htmlShortPath; 
		File htmlFile = new File(htmlPath);
		if (!htmlFile.exists()){
			log.debug("health info html not exists!");
			List<HtmlImagesPO> images = this.htmlImagesDAO.findHtmlImagesByInfoId(id);
			FileUtils.writeByteArrayToFile(htmlFile
				, html.getBytes(GlobalConstants.DEF_ENCODE));
			String imagePath = null;
			for(HtmlImagesPO i : images){
				imagePath = ctx.getRealPath("/") + PUB_INFO_HTMLIMG_SHORTPATH
					+ File.separator + i.getFilename();
				FileUtils.writeByteArrayToFile(new File(imagePath), i.getImage());
			}
		}
		
		return htmlShortPath;
	}

	@Override
	public List<InfoCatVO> getInfoCatCodes(int infoType) {
		List<InfoCatPO> lst = this.infoCatDAO.findInfoCatByType(infoType);
		List<InfoCatVO> lst2 = new ArrayList<InfoCatVO>();
		for (InfoCatPO i: lst){
			InfoCatVO v = new InfoCatVO();
			v.setInfoCatCode(i.getInfoCatCode());
			v.setInfoCatName(i.getInfoCatName());
			v.setInfoType(i.getInfoType());
			lst2.add(v);
		}
		return lst2;

	}
	
	@Override
	public List<InfoCatPO> getInfoCat() {
		List<InfoCatPO> infoList = this.infoCatDAO.getInfoCat();
		return infoList;
	}

	@Override
	public Page<HosPubInfoVO> getUnApprovedHosPubInfoList(int infoType
			, String infoCatCode,
			int page, int pageSize) {
		//满足条件的总共有多少条数据。
		int totalRecord = this.hosPubInfoDAO.countUnApprovedHosInfo(infoType, infoCatCode);
		
		Page<HosPubInfoVO> p = new Page<HosPubInfoVO>(totalRecord, pageSize, page);
		List<HosPubInfoPO> orig = this.hosPubInfoDAO.findUnApprovedHosInfo(infoType
				, infoCatCode, p.getOffset(), p.getPageSize());
		List<HosPubInfoVO> dest = HosPubInfoVO.fromList(orig);
		p.setCurPageData(dest);
		return p;
	}
	
	
	

	@Override
	public Page<HosPubInfoVO> getWaitAppHosPubInfoList(Integer infoType,
			String infoCatCode, java.sql.Date startExpDate,
			java.sql.Date endExpDate,String editor, int page, int pageSize) {
		int totalRecord = this.hosPubInfoDAO.countUnApprovedHosInfoByParas(infoType, infoCatCode, startExpDate, endExpDate,editor);
		Page<HosPubInfoVO> p = new Page<HosPubInfoVO>(totalRecord, pageSize, page);
		List<HosPubInfoPO> orig = this.hosPubInfoDAO.findUnApprovedHosInfoByParas(
				infoType, infoCatCode, startExpDate, endExpDate,editor, p.getOffset(), p.getPageSize());
		List<HosPubInfoVO> dest = HosPubInfoVO.fromList(orig);
		p.setCurPageData(dest);
		return p;

	}
	
	@Override
	public Page<HosPubInfoVO> getApprovedHosPubInfoList(Integer infoType,
			String infoCatCode, int isMain, java.sql.Date startExpDate,
			java.sql.Date endExpDate,String editor,String approver, int page, int pageSize) {
		int totalRecord = this.hosPubInfoDAO.countApprovedHosInfoByParas(infoType, infoCatCode, isMain, startExpDate, endExpDate,editor,approver);
		Page<HosPubInfoVO> p = new Page<HosPubInfoVO>(totalRecord, pageSize, page);
		List<HosPubInfoPO> orig = this.hosPubInfoDAO.findApprovedHosInfoByParas(
				infoType, infoCatCode, isMain, startExpDate, endExpDate,editor,approver, p.getOffset(), p.getPageSize());
		List<HosPubInfoVO> dest = HosPubInfoVO.fromList(orig);
		p.setCurPageData(dest);
		return p;

	}

	@Override
	public void deleteHosPubInfo(String infoid) {
	    this.hosPubInfoDAO.deleteHosPubInfo(infoid);
	    this.htmlImagesDAO.deleteHtmlImages(infoid);
	}

	@Override
	public HosPubInfoCatMenuVO getUnApprovedCatMenu() {
		HosPubInfoCatMenuVO menu = new HosPubInfoCatMenuVO();
		List<HosPubInfoPO.StatByCatPO> lstPO = this.hosPubInfoDAO.statUnApprovedInfoByCat();
		List<HosPubInfoVO.StatByCatVO> healthList = new ArrayList<HosPubInfoVO.StatByCatVO>();
		List<HosPubInfoVO.StatByCatVO> newsList = new ArrayList<HosPubInfoVO.StatByCatVO>();
		
		for (HosPubInfoPO.StatByCatPO p : lstPO){
			if (p.getInfoType() == InfoCatPO.INFO_TYPE_HEALTH){
				healthList.add(StatByCatVO.fromPO(p));
			}else if (p.getInfoType() == InfoCatPO.INFO_TYPE_NEWS){
				newsList.add(StatByCatVO.fromPO(p));
			}
		}
		menu.setHealthStatCat(healthList);
		menu.setNewsStatCat(newsList);
		
		List<HosPubInfoPO.StatByTypePO> lstTypePO = this.hosPubInfoDAO.statUnApprovedInfoByType();
		for (StatByTypePO p: lstTypePO){
			if (p.getInfoType() == InfoCatPO.INFO_TYPE_HEALTH){
				menu.setHealthInfoCount(p.getStatCount());
			}else if (p.getInfoType() == InfoCatPO.INFO_TYPE_NEWS){
				menu.setNewsInfoCount(p.getStatCount());
			}
			menu.setTotalCount(menu.getTotalCount() + p.getStatCount());
		}
		
		return menu;
		
	}

	@Override
	public ServiceResult editCatName(String infoCatCode, String infoCatName) {
		try{
			boolean exists = this.infoCatDAO.existsInfoCat(infoCatName);
			if (exists){
				return ServiceResult.getFailedInstance("", "存在相同的类别名！");
			}else{
				this.infoCatDAO.updateInfoCatName(infoCatCode, infoCatName);
				return new ServiceResult(){
					@Override
					public boolean isSuccess() {
						return true;
					}
				};
			}
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，添加资讯类别失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，添加资讯类别失败！");
			return sr;
		}
	}

	@Override
	public ServiceResult deleteCat(String infoCatCode) {
		try{
			int count = this.hosPubInfoDAO.countHosInfoByCatCode(infoCatCode);
			if (count > 0){
				return ServiceResult.getFailedInstance("", "该类别还有" + count + "条资讯，不能删除！");
			}
			this.infoCatDAO.deleteInfoCat(infoCatCode);
			return new ServiceResult(){
				@Override
				public boolean isSuccess() {
					return true;
				}
			};
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，删除资讯类别失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，删除资讯类别失败！");
			return sr;
		}

	}

	@Override
	public ServiceResult addCat(String infoCatName, int infoType) {
		try{
			boolean exists = this.infoCatDAO.existsInfoCat(infoCatName);
			if (exists){
				return ServiceResult.getFailedInstance("", "存在相同的类别名！");
			}else{
				if (InfoCatPO.isValidInfoType(infoType)){
					InfoCatPO p = new InfoCatPO();
					p.setInfoCatName(infoCatName);
					p.setInfoType(infoType);
					this.infoCatDAO.addInfoCat(p);
					return new ServiceResult(){
						@Override
						public boolean isSuccess() {
							return true;
						}
					};
				}else{
					return ServiceResult.getFailedInstance("", "无效参数，添加资讯类别失败！");
				}
			}
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，添加资讯类别失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，添加资讯类别失败！");
			return sr;
		}
	}


	@Override
	public ServiceResult submitUnApproved(String infoid, String adminUserName) {
		try{
			this.hosPubInfoDAO.updateHosPubInfoStatus(infoid
					, HosPubInfoPO.STATUS_UNAPPROVED, adminUserName);
			return new ServiceResult(){
				@Override
				public boolean isSuccess() {
					return true;
				}
			};
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，提交审批不通过操作失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，提交审批不通过操作失败！");
			return sr;
		}
	}

	@Override
	public ServiceResult submitCancel(String infoid) {
		try{
			this.hosPubInfoDAO.updateHosPubInfoStatus(infoid, HosPubInfoPO.STATUS_CANCEL, null);
			return new ServiceResult(){
				@Override
				public boolean isSuccess() {
					return true;
				}
			};
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，提交取消操作失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，提交取消操作失败！");
			return sr;
		}
	}
	public ServiceResult submitApproved(String infoid, String adminUserName) {
		try{
			this.hosPubInfoDAO.updateHosPubInfoStatus(infoid, HosPubInfoPO.STATUS_APPROVED, adminUserName);
			HosPubInfoPO info = this.hosPubInfoDAO.getHosInfo(infoid);
			Date expDate = info.getExpPubDate();
			Date now = new Date();
			Message msg = new Message();
			msg.setBroadcast(true);
			if (Integer.parseInt(info.getInfoCatCode()) == InfoCatPO.INFO_TYPE_HEALTH){
				msg.setBusCode(BusCodeConstants.PUB_HEALTH_INFO);
				msg.addPara(MsgTemplateConstants.NET_MSG_TITLE, info.getSubject());
				msg.addPara(MsgTemplateConstants.NET_MSG_DESC, info.getConcise());
				msg.addPara(MsgTemplateConstants.NET_MSG_BUSCODE
						, BusCodeConstants.PUB_HEALTH_INFO);
				msg.addPara(MsgTemplateConstants.NET_MSG_INFOID, infoid);
				msg.addPara(MsgTemplateConstants.NET_MSG_URL, HEALTH_INFO_WEB_URL + infoid);
				
			}else if (Integer.parseInt(info.getInfoCatCode()) == InfoCatPO.INFO_TYPE_NEWS){
				msg.setBusCode(BusCodeConstants.PUB_NEWS_INFO);
				msg.addPara(MsgTemplateConstants.NET_MSG_TITLE, info.getSubject());
				msg.addPara(MsgTemplateConstants.NET_MSG_DESC, info.getConcise());
				msg.addPara(MsgTemplateConstants.NET_MSG_BUSCODE
						, BusCodeConstants.PUB_NEWS_INFO);
				msg.addPara(MsgTemplateConstants.NET_MSG_INFOID, infoid);
				msg.addPara(MsgTemplateConstants.NET_MSG_URL, NEWS_INFO_WEB_URL + infoid);


			}
			//屏蔽发布消息
			//if (now.after(expDate)){
				//log.debug("准备立即推送发布内容。。。");
				//this.msgPlatformServer.sendRealTimeMsg(msg, SendWay.net);
			//}else{
				//log.debug("准备定时推送发布内容。。。");
				//List<Message> msgs = new ArrayList<Message>();
				//msgs.add(msg);//添加定时发布的消息
				//this.msgPlatformServer.sendScheduledMsg(msgs, expDate, null, SendWay.net);
			//}
			//屏蔽发布消息 end
			return new ServiceResult(){
				@Override
				public boolean isSuccess() {
					return true;
				}
			};

		}catch(DataAccessException dacEx){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，提交审批失败！");
			return sr;
		}catch(Exception ex){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，提交审批失败！");
			return sr;
		}
	}
	@Override
	public ServiceResult createNewHosInfo(String infoid, String subject,
			File image, String concise, String html, int isMain
			, String infoCatCode, java.sql.Date expectedPubDate
			, String adminUserName) {
		try{
			CompressPic compress = new CompressPic();
			compress.setOutputHeight(this.THUMBNAIL_HEIGHT);
			compress.setOutputWidth(this.THUMBNAIL_WIDTH);
			compress.setProportion(true);
			
			byte[] imgData = compress.compress(image);
			if (imgData != null && imgData.length > 0){
				HosPubInfoPO info = new HosPubInfoPO();
				info.setHosInfoId(infoid);
				info.setSubject(subject);
				info.setImage(imgData);
				info.setConcise(concise);
				info.setHtmlContent(html);
				info.setHtml(getFullHtml(html));
				info.setIsMain(isMain);
				info.setCreateDate(new Timestamp((new Date()).getTime()));
				info.setUpdateDate(info.getCreateDate());
				info.setInfoCatCode(infoCatCode);
				info.setExpPubDate(expectedPubDate);
				Date pubDate = addTimeToDate(info.getExpPubDate(), new Date());
				info.setPubDate(new Timestamp(pubDate.getTime()));
				info.setEditor(adminUserName);
				this.hosPubInfoDAO.addHosPubInfo(info);
				
				return new ServiceResult(){
					@Override
					public boolean isSuccess() {
						return true;
					}
				};
			}else{
				ServiceResult sr = ServiceResult.getFailedInstance("", "压缩上传图片文件失败，请检查文件格式是否为支持的格式！");
				return sr;
			}
			
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，发布资讯失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，发布资讯失败！");
			return sr;
		}
	}
}
