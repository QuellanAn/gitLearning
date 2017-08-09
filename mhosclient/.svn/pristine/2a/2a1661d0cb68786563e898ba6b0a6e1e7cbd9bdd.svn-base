package com.catic.mobilehos.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.catic.mobilehos.ErrorCode;
import com.catic.mobilehos.GlobalConstants;
import com.catic.mobilehos.dao.BasicCntCfgDao;
import com.catic.mobilehos.dao.BasicCntCfgImgsDao;
import com.catic.mobilehos.po.BasicCntCfgImgsPO;
import com.catic.mobilehos.po.BasicCntCfgPO;
import com.catic.mobilehos.service.vo.BasicCntCfgVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.VOPOConverter;
import com.catic.mobilehos.utils.CompressPic;
import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * 基础内容配置
 * 
 * @author linchunda
 * 
 */
public class BasicCntCfgServiceImpl implements BasicCntCfgService {
	private Log log = LogFactory.getLog(this.getClass());
	/** images\bascicntcfg */
	private final String BASIC_CNT_CFG_DIRNAME = GlobalConstants.DYNAMIC_IMAGE_FOLDER
			+ File.separator + "bascicntcfg";
	/** dynamic\images\bascicntcfg */
	private final String BASIC_CNT_CFG_HTMLIMG_SHORTPATH = GlobalConstants.DYNAMIC_FOLDER
			+ File.separator + BASIC_CNT_CFG_DIRNAME;

	private final int THUMBNAIL_HEIGHT = 300;

	private final int THUMBNAIL_WIDTH = 400;

	private final int WEB_IMG_HEIGHT = 1024;

	private final int WEB_IMG_WIDTH = 1024;

	private BasicCntCfgDao basicCntCfgDao;
	private BasicCntCfgImgsDao basicCntCfgImgsDao;

	public BasicCntCfgDao getBasicCntCfgDao() {
		return basicCntCfgDao;
	}

	public void setBasicCntCfgDao(BasicCntCfgDao basicCntCfgDao) {
		this.basicCntCfgDao = basicCntCfgDao;
	}

	public BasicCntCfgImgsDao getBasicCntCfgImgsDao() {
		return basicCntCfgImgsDao;
	}

	public void setBasicCntCfgImgsDao(BasicCntCfgImgsDao basicCntCfgImgsDao) {
		this.basicCntCfgImgsDao = basicCntCfgImgsDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.catic.mobilehos.service.BasicCntCfgService#getBasicCntCfg(int,
	 * int)
	 */
	@Override
	public ServiceResult getBasicCntCfg(int cfg_type, int cat) {
		try {
			final BasicCntCfgPO basicCntCfgPO = basicCntCfgDao
					.getBasicCntCfgPO(cfg_type, cat);
			final List<BasicCntCfgImgsPO> basicCntCfgIimgsPOs = basicCntCfgImgsDao
					.getBasicCntCfgImgs(cfg_type, cat);

			return new ServiceResult() {

				@Override
				public boolean isSuccess() {
					return true;
				}

				@Override
				public JSONObject getJSON() {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("content", basicCntCfgPO.getContent());
					JSONArray array = new JSONArray();
					for (BasicCntCfgImgsPO basicCntCfgIimgsPO : basicCntCfgIimgsPOs) {
						JSONObject value = new JSONObject();
						value.put("filename", basicCntCfgIimgsPO.getFilename());
						value.put("url", basicCntCfgIimgsPO.getUrl());
						value.put("image_content", Base64
								.encode(basicCntCfgIimgsPO.getImage_content()));

						array.add(value);
					}

					jsonObject.put("imgs", array);
					return jsonObject;
				}
			};
		} catch (DataAccessException dacEx) {
			ServiceResult sr = ServiceResult.getFailedInstance(
					ErrorCode.LS_BUS_EXEC_ERR, "系统异常，获取基础内容配置失败！");
			return sr;
		} catch (Exception ex) {
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance(
					ErrorCode.LS_ILLEGAL_BUS_ARGUMENT, "参数错误，获取基础内容配置失败！");
			return sr;
		}
	}

	@Override
	public ServiceResult saveBasicCntCfgHtmlImage(ServletContext context,
			int cfg_type, int cat, File f) {
		try {
			Date createDate = new Date();
			String newFileName = UUID.randomUUID().toString() + "_"
					+ createDate.getTime() + ".jpg";

			// 相对html生成的目录路径
			String shortUrl = ".." + File.separator + ".." + File.separator
					+ BASIC_CNT_CFG_DIRNAME + File.separator + newFileName;

			final String fullUrl = context.getContextPath() + File.separator
					+ BASIC_CNT_CFG_HTMLIMG_SHORTPATH + File.separator
					+ newFileName;
			String filePath = context.getRealPath("/") + File.separator
					+ BASIC_CNT_CFG_HTMLIMG_SHORTPATH + File.separator
					+ newFileName;

			CompressPic compress = new CompressPic();
			compress.setOutputHeight(this.WEB_IMG_HEIGHT);
			compress.setOutputWidth(this.WEB_IMG_WIDTH);
			compress.setProportion(true);
			byte[] fileData = compress.compress(new FileInputStream(f));
			FileUtils.writeByteArrayToFile(new File(filePath), fileData);

			BasicCntCfgImgsPO cntCfgImgsPO = new BasicCntCfgImgsPO();
			cntCfgImgsPO.setCfg_type(cfg_type);
			cntCfgImgsPO.setCat(cat);
			cntCfgImgsPO.setFilename(newFileName);
			cntCfgImgsPO.setUrl(shortUrl);
			basicCntCfgImgsDao.saveBasiCntCfgImgs(cntCfgImgsPO, f);

			return new ServiceResult() {
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
		} catch (DataAccessException dacEx) {
			ServiceResult sr = ServiceResult.getFailedInstance("",
					"系统异常，上传图片失败！");
			return sr;
		} catch (Exception ex) {
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("",
					"系统异常，上传图片失败！");
			return sr;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.catic.mobilehos.service.BasicCntCfgService#getBasicCntCfgPO(int,
	 * int)
	 */
	@Override
	public BasicCntCfgPO getBasicCntCfgPO(int cfg_type, int cat) {
		try {
			return this.basicCntCfgDao.getBasicCntCfgPO(cfg_type, cat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.catic.mobilehos.service.BasicCntCfgService#saveBasicCntCfg(com.catic
	 * .mobilehos.po.BasicCntCfgPO)
	 */
	@Override
	public int saveBasicCntCfg(BasicCntCfgPO basicCntCfgPO) {
		try {
			return this.basicCntCfgDao.saveBasicCntCfg(basicCntCfgPO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.catic.mobilehos.service.BasicCntCfgService#updateBasicCntCfg(com.
	 * catic.mobilehos.po.BasicCntCfgPO, int, int)
	 */
	@Override
	public void updateBasicCntCfg(BasicCntCfgPO basicCntCfgPO) {
		try {
			this.basicCntCfgDao.updateBasicCntCfg(basicCntCfgPO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Page<BasicCntCfgVO> getAllContentList(String catName,
			String updatePeople, java.sql.Date startDate, java.sql.Date endDate,int page, int pageSize) {
		int totalRecord = this.basicCntCfgDao.getTotalContent(catName, updatePeople, startDate, endDate);
		Page<BasicCntCfgVO> pageCont = new Page<BasicCntCfgVO>(totalRecord, pageSize, page);
		List<BasicCntCfgPO> list = this.basicCntCfgDao.getAllContent(catName, updatePeople, startDate, 
				endDate,pageCont.getOffset(),pageCont.getPageSize());
		VOPOConverter<BasicCntCfgVO, BasicCntCfgPO> cvt 
		= new VOPOConverter<BasicCntCfgVO, BasicCntCfgPO>(BasicCntCfgVO.class, BasicCntCfgPO.class);
		List<BasicCntCfgVO> vlist = cvt.fromPOList(list);
		pageCont.setCurPageData(vlist);
		return pageCont;
	}

	public ServiceResult addBaseContent(BasicCntCfgPO basicCntCfgPO) {
		try{
			this.basicCntCfgDao.addBaseContent(basicCntCfgPO);
			return new ServiceResult(){
				public boolean isSuccess() {
					return true;
				}
			};
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，添加就医指南失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，添加就医指南失败！");
			return sr;
		}
	}

	@Override
	public void deleteBaseContent(int cat) {
		basicCntCfgDao.deleteBaseContent(cat);
	}

}
