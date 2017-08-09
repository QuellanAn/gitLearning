package com.catic.mobilehos.action;  

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.action.BaseAction;
import com.catic.mobilehos.pay.entity.PayDictionary;
import com.catic.mobilehos.pay.entity.PayTerminal;
import com.catic.mobilehos.pay.entity.YQInfo;
import com.catic.mobilehos.pay.util.ExcleUtils;
import com.catic.mobilehos.utils.Page;

/**  
 * 类说明:自助设备管理action
 * @author 朱伟权
 * 创建时间: 2017-6-23 上午11:23:39
 */
@Controller
@Scope("prototype")
public class SelfServiceManageAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private PayTerminal payTer;
	private List<PayTerminal> ptlist;
	private String id;
	private File upload;// 导入文件
    private String uploadFileName;// 导入文件的文件名
	
	/**
	 * 查询终端设备
	 * @return
	 */
	public String findAllInfo(){
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo="1";
			}
			ptlist=payTerBiz.findAll(page, payTer);
			if(ptlist!=null&&ptlist.size()>0){
				page=new Page(Integer.parseInt(pageNo), 10, ptlist.get(0).getCount());
				ptlist=payTerBiz.findAll(page, payTer);
				replaceFacilityType(ptlist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String toSave(){
		return "toSave";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	public String toUpdate(){
		try {
			payTer=payTerBiz.findById(Integer.parseInt(id));
			request.setAttribute("yqlist", yQConfigBiz.findAllyq());
			request.setAttribute("facilityTypeList", payDictionaryBiz.findAll("facility_type"));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "toUpdate";
	}
	
	/**
	 * 保存/更新终端信息
	 * @return
	 */
	public String saveOrUpdate(){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			payTer.setCreateTime(this.toTimestamp(format.format(new Date())));
			if(StringUtils.isBlank(id)){
				payTerBiz.insert(payTer);
			}else{
				payTer.setId(Integer.parseInt(id));
				payTerBiz.modify(payTer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveUpdate";
	}
	
	/**
	 * 删除终端
	 * @return
	 */
	public String delete(){
		try {
			payTerBiz.delete(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}
	
	/**
	 * 查看终端详情
	 * @return
	 */
	public String toDetail(){
		try {
			payTer = payTerBiz.findById(Integer.parseInt(id));
			List<PayTerminal> list = new ArrayList<PayTerminal>();
			list.add(payTer);
			replaceFacilityType(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "detail";
	}
	
	/**
	 * 检查终端编号是否已存在
	 */
	public void checkFacilityId(){
		JSONObject jsonObject = new JSONObject();
		boolean checkResult = false;// 终端编号检查标记
		try{
			String facilityId = request.getParameter("facilityId");
			PayTerminal terminal = payTerBiz.findByCode(facilityId);
			if(terminal == null){
				checkResult = true;// 终端编号检查通过
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObject.put("result", checkResult);
		jsonObj(jsonObject.toString());
	}
	
	/**
	 * 下载导入模板
	 */
	public void downloadTemplate(){
		try{
			List<PayDictionary> list = payDictionaryBiz.findAll("facility_type");
			List<YQInfo> yqList = yQConfigBiz.findAllyq();
			// 设备类型
			String[] facilityTypes = null;
			// 院区
			String[] yqInfos = null;
			// 设备状态
			String[] facilityStatus = new String[]{"启用", "故障", "关机", "禁用"};
			if(list != null && list.size() > 0){
				facilityTypes = new String[list.size()];
				int i = 0;
				for (PayDictionary pd : list) {
					facilityTypes[i++] = pd.getCodeName();
				}
			}
			if(yqList != null && yqList.size() > 0){
				yqInfos = new String[yqList.size()];
				int i = 0;
				for (YQInfo yq : yqList) {
					yqInfos[i++] = yq.getName();
				}
			}
			
			HSSFWorkbook wb=new HSSFWorkbook();  
	        HSSFSheet sheet=wb.createSheet();  
	        // 全局样式
	        HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setWrapText(true);
	        
			// 头部字段说明
			String[] headers = new String[]{"终端编号(*)","终端名称(*)","院区(*)","终端类型(*)","终端状态(*)","位置(*)","备注"};
			HSSFRow row = sheet.createRow(0);    
			HSSFCell cell = null;
	        for(int i = 0; i < headers.length; i++){    
	            //单元格  
	        	cell = row.createCell(i);    
	        	cell.setCellStyle(style);    
	        	cell.setCellValue(headers[i]);
	        	sheet.setColumnWidth(i, headers[i].getBytes().length * 2 * 256);
	        }
	        if(yqInfos != null && yqInfos.length > 0){
	        	sheet = ExcleUtils.setHSSFValidation(sheet, yqInfos, 1, 65535, 2, 2);// 院区限制下拉框
	        }
	        if(facilityTypes != null && facilityTypes.length > 0){
	        	sheet = ExcleUtils.setHSSFValidation(sheet, facilityTypes, 1, 65535, 3, 3);// 终端类型限制下拉框
	        }
	        if(facilityStatus != null && facilityStatus.length > 0){
	        	sheet = ExcleUtils.setHSSFValidation(sheet, facilityStatus, 1, 65535, 4, 4);// 终端状态限制下拉框
	        }
	          
	        response.reset();
	        OutputStream os = response.getOutputStream();
	        response.setHeader("Content-disposition","attachment; filename="+URLEncoder.encode("导入模板", "utf-8")+".xls");//设定输出文件头
			response.setContentType("application/msexcel");//定义输出类型
			wb.write(os);
            os.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量导入
	 * @return
	 */
	public String importExcel() {
		JSONObject result = new JSONObject();
		try {
			Sheet sheet = ExcleUtils.getFirstSheet(upload, uploadFileName);
			System.out.println("总共"+sheet.getLastRowNum()+"行数据");
			Map<String, String> facilityStatusMap = new HashMap<String, String>();// 设备状态
			Map<String, String> facilityTypeMap = new HashMap<String, String>();// 设备类型
			Map<String, String> districtMap = new HashMap<String, String>();// 院区
			facilityStatusMap.put("启用", "0");
			facilityStatusMap.put("故障", "1");
			facilityStatusMap.put("关机", "2");
			facilityStatusMap.put("禁用", "3");
			List<PayDictionary> list = payDictionaryBiz.findAll("facility_type");
			List<YQInfo> yqList = yQConfigBiz.findAllyq();
			if(list != null && list.size() > 0){
				for(PayDictionary dictionary : list){
					facilityTypeMap.put(dictionary.getCodeName(), dictionary.getCodeNo());
				}
			}
			if(yqList != null && yqList.size() > 0){
				for(YQInfo yq : yqList){
					districtMap.put(yq.getName(), yq.getYqId()+"");
				}
			}
			if(sheet.getLastRowNum() > 0){// 有数据需要导入
				List<PayTerminal> terminals = new ArrayList<PayTerminal>();
				// 用来检查是否有重复终端编号的map
				Map<String, PayTerminal> facilityIdMap = new HashMap<String, PayTerminal>();
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					PayTerminal payTerminal = new PayTerminal();
					String facilityId = ExcleUtils.getStringCellValue(sheet.getRow(i).getCell(0)).trim();
					payTerminal.setFacilityId(facilityId);
					payTerminal.setFacilityName(ExcleUtils.getStringCellValue(sheet.getRow(i).getCell(1)).trim());
					String districtStr = ExcleUtils.getStringCellValue(sheet.getRow(i).getCell(2)).trim();
					// 根据院区名称得到院区id
					String districtId = districtMap.get(districtStr);
					// 验证院区是否存在
					if(districtId == null){
						result.put("end", "error");
						result.put("data", "院区不符合预设值");
						jsonObj(result.toString());
						return null;
					}else{
						payTerminal.setDistrict(districtId);
					}
					String facilityTypeStr = ExcleUtils.getStringCellValue(sheet.getRow(i).getCell(3)).trim();
					// 根据终端类型得到终端类型code
					String facilityTypeCode = facilityTypeMap.get(facilityTypeStr);
					// 验证终端类型是否存在
					if(facilityTypeCode == null){
						result.put("end", "error");
						result.put("data", "终端类型不符合预设值");
						jsonObj(result.toString());
						return null;
					}else{
						payTerminal.setFacilityType(facilityTypeCode);
					}
					String facilityStatusStr = ExcleUtils.getStringCellValue(sheet.getRow(i).getCell(4)).trim();
					// 根据终端状态得到终端状态code
					String facilityStatusCode = facilityStatusMap.get(facilityStatusStr);
					// 验证终端状态是否存在
					if(facilityStatusCode == null){
						result.put("end", "error");
						result.put("data", "终端状态不符合预设值");
						jsonObj(result.toString());
						return null;
					}else{
						payTerminal.setFacilityStatus(facilityStatusCode);
					}
					payTerminal.setPutAddress(ExcleUtils.getStringCellValue(sheet.getRow(i).getCell(5)).trim());
					payTerminal.setRemark(ExcleUtils.getStringCellValue(sheet.getRow(i).getCell(6)).trim());
					if(StringUtils.isBlank(payTerminal.getFacilityId()) || StringUtils.isBlank(payTerminal.getFacilityName())
							|| StringUtils.isBlank(payTerminal.getFacilityType()) || StringUtils.isBlank(payTerminal.getDistrict())
							|| StringUtils.isBlank(payTerminal.getFacilityStatus()) || StringUtils.isBlank(payTerminal.getPutAddress())){
						result.put("end", "error");
						result.put("data", "请检查必输信息是否均填写完整");
						jsonObj(result.toString());
						return null;
					}
					if(facilityIdMap.get(facilityId) != null){
						result.put("end", "error");
						result.put("data", "终端编号有重复，请检查");
						jsonObj(result.toString());
						return null;
					}else{
						facilityIdMap.put(facilityId, payTerminal);
					}
					PayTerminal terminal = payTerBiz.findByCode(facilityId);
					if(terminal != null){
						result.put("end", "error");
						result.put("data", "终端编号“" + facilityId + "”已存在");
						jsonObj(result.toString());
						return null;
					}
					terminals.add(payTerminal);
				}
				// 保存所有终端
				for (PayTerminal payTerminal : terminals) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					payTerminal.setCreateTime(this.toTimestamp(format.format(new Date())));
					payTerBiz.insert(payTerminal);
				}
				result.put("end", "ok");
				result.put("data", "成功导入" + terminals.size() + "条记录");
			}else{
				result.put("end", "error");
				result.put("data", "没有数据需要导入");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("end", "error");
			result.put("data", "导入失败");
		}
		jsonObj(result.toString());
		return null;
	}
	
	/**
	 * 替换设备类型的code为名称
	 * @throws Exception
	 */
	private void replaceFacilityType(List<PayTerminal> ptlist) throws Exception{
		if(ptlist != null && ptlist.size() > 0){
			Map<String, String> map = new HashMap<String, String>();
			List<PayDictionary> list = payDictionaryBiz.findAll("facility_type");
			if(list != null && list.size() > 0){
				for (PayDictionary payDictionary : list) {
					map.put(payDictionary.getCodeNo(), payDictionary.getCodeName());
				}
			}
			
			for (PayTerminal payTerminal : ptlist) {
				payTerminal.setFacilityType(map.get(payTerminal.getFacilityType()));
			}
		}
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

}
 