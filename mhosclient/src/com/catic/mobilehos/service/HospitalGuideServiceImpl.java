package com.catic.mobilehos.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import net.coobird.thumbnailator.Thumbnails;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.catic.mobilehos.ErrorCode;
import com.catic.mobilehos.GlobalConstants;
import com.catic.mobilehos.dao.DepartmentDAO;
import com.catic.mobilehos.dao.HospitalIntroInfoDAO;
import com.catic.mobilehos.po.DepartmentPO;
import com.catic.mobilehos.po.HosIntroInfoPO;
import com.catic.mobilehos.service.vo.HosIntroVO;
import com.catic.mobilehos.service.vo.HosIntroVO.ContactsVO;
import com.catic.mobilehos.service.vo.VOPOConverter;
import com.catic.mobilehos.utils.CompressPic;
import com.sun.org.apache.xml.internal.security.utils.Base64;


public class HospitalGuideServiceImpl implements HospitalGuideService {
	
	private static final int HOS_IMG_HEIGHT = 300;

	private static final int HOS_IMG_WIDTH = 400;
	
	private final String BASIC_CFG_DIRNAME = "basiccntcfg";
	
	private final String BASIC_CFG_IMG_SHORTPATH
		= GlobalConstants.DYNAMIC_FOLDER
			+ "/" + GlobalConstants.DYNAMIC_IMAGE_FOLDER
			+ "/" + BASIC_CFG_DIRNAME;

	
	private Log log = LogFactory.getLog(this.getClass());
	
	private HospitalIntroInfoDAO hospitalInfoDAO;
	
	private DepartmentDAO departmentDAO;
	

	public HospitalIntroInfoDAO getHospitalInfoDAO() {
		return hospitalInfoDAO;
	}


	public void setHospitalInfoDAO(HospitalIntroInfoDAO hospitalInfoDAO) {
		this.hospitalInfoDAO = hospitalInfoDAO;
	}
	

	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}


	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
	
	public HosIntroVO getHospitalIntroductionWithVO(){
		HosIntroInfoPO info = hospitalInfoDAO.getHospitalIntroInfo();
//		List<HosIntroInfoPO.ContactsPO> lstContactsPO = hospitalInfoDAO.getHospitalContacts();
		HosIntroVO hosIntroVO = new HosIntroVO();
		VOPOConverter<HosIntroVO, HosIntroInfoPO> cvt1 
			= new VOPOConverter<HosIntroVO, HosIntroInfoPO>(HosIntroVO.class, HosIntroInfoPO.class);
//		VOPOConverter<HosIntroVO.ContactsVO, HosIntroInfoPO.ContactsPO> cvt2 
//			= new VOPOConverter<HosIntroVO.ContactsVO, HosIntroInfoPO.ContactsPO>(HosIntroVO.ContactsVO.class
//					, HosIntroInfoPO.ContactsPO.class);
		hosIntroVO = cvt1.poToVO(info);
		/*List<ContactsVO> lstContactsVO = cvt2.fromPOList(lstContactsPO);
		hosIntroVO.setContacts(lstContactsVO);*/
		return hosIntroVO;
	}


	@Override
	public ServiceResult getHospitalIntroduction() {
		try{
			final HosIntroInfoPO info = hospitalInfoDAO.getHospitalIntroInfo();
			final List<HosIntroInfoPO.ContactsPO> contacts = hospitalInfoDAO.getHospitalContacts();
			return new ServiceResult(){
				@Override
				public boolean isSuccess() {
					return true;
				}
				@Override
				public JSONObject getJSON() {
					JSONObject j = new JSONObject();
					j.put("name", info.getHospitalName());
					j.put("level", info.getLevel());
					j.put("address", info.getAddress());
					j.put("picture", Base64.encode(info.getPicture()));
					j.put("website", info.getWebsite());
					j.put("feature", info.getFeatureDepartment());
					j.put("introduction", info.getIntroduction());
					JSONArray jContacts = new JSONArray();
					if (contacts != null && contacts.size() > 0){
						for (HosIntroInfoPO.ContactsPO c : contacts){
							JSONObject jc = new JSONObject();
							jc.put("linename", c.getName());
							jc.put("phone", c.getPhone());
							jContacts.add(jc);
						}
					}
					j.put("contacts", jContacts);
					return j;
				}
			};
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance(ErrorCode.LS_BUS_EXEC_ERR
					, "系统异常，获取医院介绍失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance(ErrorCode.LS_ILLEGAL_BUS_ARGUMENT
					, "参数错误，获取医院介绍失败！");
			return sr;
		}
	}
	
	
	@Override
	public ServiceResult saveHospitalIntroduction(HosIntroVO v) {
		try{
//			if (!v.compressPic()){
//				System.out.println("picture compress failer.");
//				return ServiceResult.getFailedInstance("", "压缩图片文件失败！");
//			}
			
			VOPOConverter<HosIntroVO, HosIntroInfoPO> cvt = new VOPOConverter<HosIntroVO, HosIntroInfoPO>(HosIntroVO.class, HosIntroInfoPO.class);
//			VOPOConverter<HosIntroVO.ContactsVO, HosIntroInfoPO.ContactsPO> cvt2 = new VOPOConverter<HosIntroVO.ContactsVO, HosIntroInfoPO.ContactsPO>(HosIntroVO.ContactsVO.class, HosIntroInfoPO.ContactsPO.class);
			HosIntroInfoPO p  = cvt.voToPO(v);
//			List<HosIntroInfoPO.ContactsPO> contacts = cvt2.fromVOList(v.getContacts());
			if(v.getPic() != null && v.getPic().length() > 0 && p.getPicture() == null) {
				String type=v.getPicContentType().substring(6);
//				byte[] bufferedpic = compressPic(v.getPic());
				byte[] bufferedpic = getImageBinary(v.getPic(),type);
				p.setPicture(bufferedpic);
			}
			if (p.getPicture() == null){
				HosIntroInfoPO p2 = this.hospitalInfoDAO.getHospitalIntroInfo();
				p.setPicture(p2.getPicture());
			}
			this.hospitalInfoDAO.clearHospitalIntroInfo();
			
			//System.out.println("this.hospitalInfoDAO.clearHospitalIntroInfo();");
			
			
			/*this.hospitalInfoDAO.clearHospitalContacts();
			
			System.out.println("this.hospitalInfoDAO.clearHospitalContacts();");*/
			
			this.hospitalInfoDAO.addHospitalIntroInfo(p);
			
			//System.out.println("this.hospitalInfoDAO.addHospitalIntroInfo(p);");
			
			/*this.hospitalInfoDAO.addHospitalContacts(contacts);
			
			System.out.println("this.hospitalInfoDAO.addHospitalContacts(contacts);");*/
			
			return ServiceResult.getSucInstance();
		}
//		catch(DataAccessException dacEx){
//			dacEx.printStackTrace();
//			return ServiceResult.getFailedInstance("", "保存数据失败！");
//		}
		catch(Exception ex){
			ex.printStackTrace();
			return ServiceResult.getFailedInstance("", "系统异常，未知错误！");
		}
	}
	
	
	@Override
	public ServiceResult saveHosIntroImage(ServletContext context, File f) {
		try{
			String newFileName = UUID.randomUUID().toString() + ".jpg";
			final String shortUrl = BASIC_CFG_IMG_SHORTPATH
					+ "/" + newFileName;
			String filePath = context.getRealPath("/") + shortUrl;
			
			byte[] fileData = FileUtils.readFileToByteArray(f);
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
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，预览图片失败！");
			return sr;
		}
	}
	
	public byte[] compressPic(File f) {
		try {
			BufferedImage bi = ImageIO.read(f);
			if(bi.getWidth() > 400 || bi.getHeight() > 300) {
				bi = Thumbnails.of(bi)
				.size(400, 300)
				.outputFormat("jpg")
				.keepAspectRatio(true)
				.outputQuality(1f) //保持高清
				.asBufferedImage(); 
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ImageIO.write(bi, f.getName().substring(f.getName().lastIndexOf(".") + 1), bos);
				return bos.toByteArray();
			} else {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				FileInputStream fis = new FileInputStream(f);
	            byte[] b = new byte[1000];
	            int n;
	            while ((n = fis.read(b)) != -1) {
	                bos.write(b, 0, n);
	            }
	            fis.close();
	            bos.close();
	            return bos.toByteArray();
			}
		} catch (IOException e) {
			System.out.println("compress picture fail : " + e);
			e.printStackTrace();
			return null;
		}
	}
	
	byte[] getImageBinary(File f,String type){      
        BufferedImage bi;      
        try {      
            bi = ImageIO.read(f);      
            ByteArrayOutputStream baos = new ByteArrayOutputStream();      
            ImageIO.write(bi, type, baos);
            byte[] bytes = baos.toByteArray();      
            baos.close();
            return bytes;      
        } catch (IOException e) {      
            e.printStackTrace();      
        }      
        return null;      
    }      
}
