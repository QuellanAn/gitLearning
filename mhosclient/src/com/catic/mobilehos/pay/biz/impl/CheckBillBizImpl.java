package com.catic.mobilehos.pay.biz.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.ICheckBillBiz;
import com.catic.mobilehos.pay.entity.Bill;
import com.catic.mobilehos.pay.entity.CheckBillDetails;
import com.catic.mobilehos.pay.util.Constant;
import com.catic.mobilehos.utils.CommonUtils;
import com.catic.mobilehos.utils.ExportExcel;
import com.catic.mobilehos.utils.ExportExcel.CustomHeaderInterf;
import com.catic.mobilehos.utils.Page;


@Service("checkBillBiz")
public class CheckBillBizImpl extends BaseBiz implements ICheckBillBiz{

	
	@Override
	public void exportCheckBill(List<Bill> checkBillList, List<CheckBillDetails> detailList, HttpServletRequest request, HttpServletResponse response) {
		List<List<Object>> list = null;
		if(checkBillList != null){// 总对账
			list = getExportCheckBillDataList(checkBillList);
		}else{// 对账详情
			list = getExportCheckBillDetailDataList(detailList);
		}
		
		String fileName = checkBillList != null ? "总对账表" : "对账详情";
		CustomHeaderInterf headerInterf = getCustomHeaderInterf(checkBillList, detailList);
		ExportExcel.excelOutWithCustomHeader(headerInterf, list, fileName, 2, request, response);
	}
	
	/**
	 * 获取自定义表头实现对象
	 * @param checkBillList 不为null即为总对账
	 * @param detailList 不为null即为对账详情
	 * @return
	 */
	private CustomHeaderInterf getCustomHeaderInterf(List<Bill> checkBillList, List<CheckBillDetails> detailList) {
		CustomHeaderInterf interf = null;
		if(checkBillList != null){// 总对账
			interf = new CustomHeaderInterf() {
				
				@Override
				public void dealCustomHeader(HSSFSheet sheet, HSSFCellStyle style) {
					Row row = sheet.createRow(0);
					style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框    
					style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框    
					style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框    
					style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
					
					CellRangeAddress cra=new CellRangeAddress(0, 1, 0, 0);        
			        //在sheet里增加合并单元格  
			        sheet.addMergedRegion(cra);  
			        Cell cell_1 = row.createCell(0); 
			        cell_1.setCellValue("账单日期");			        
			        CellRangeAddress cra1=new CellRangeAddress(0, 1, 1, 1);			        
			        sheet.addMergedRegion(cra1);  
			        Cell cell_2 = row.createCell(1); 
			        cell_2.setCellValue("资金账户");
			        CellRangeAddress cra2=new CellRangeAddress(0, 0, 2, 4);        
			        sheet.addMergedRegion(cra2);  
			        Cell cell_3 = row.createCell(2);
			        cell_3.setCellValue("支付平台");
			        
			        CellRangeAddress cra3=new CellRangeAddress(0, 0, 5, 7);        
			        sheet.addMergedRegion(cra3);
			        Cell cell_4 = row.createCell(5);  
			        cell_4.setCellValue("第三方支付平台");
			        
			        CellRangeAddress cra4=new CellRangeAddress(0, 0, 8, 10);        
			        sheet.addMergedRegion(cra4);  
			        Cell cell_5 = row.createCell(8);  
			        cell_5.setCellValue("HIS支付平台");
			        
			        CellRangeAddress cra5=new CellRangeAddress(0, 1, 11, 11);        
			        sheet.addMergedRegion(cra5);  
			        Cell cell_6 = row.createCell(11);  
			        cell_6.setCellValue("对账状态");
			        
			        CellRangeAddress cra6=new CellRangeAddress(0, 1, 12, 12);        
			        sheet.addMergedRegion(cra6);  
			        Cell cell_7 = row.createCell(12);  
			        cell_7.setCellValue("异常笔数");
			        
			        CellRangeAddress cra8=new CellRangeAddress(0, 1, 13, 13);        
			        sheet.addMergedRegion(cra8);  
			        Cell cell_8= row.createCell(13);  
			        cell_8.setCellValue("对账时间");
			        
			        CellRangeAddress cra9=new CellRangeAddress(0, 1, 14, 14);        
			        sheet.addMergedRegion(cra9);  
			        Cell cell_9= row.createCell(14);  
			        cell_9.setCellValue("状态");
			        
			        cell_1.setCellStyle(style);
			        cell_2.setCellStyle(style);
			        cell_3.setCellStyle(style);
			        cell_4.setCellStyle(style);
			        cell_5.setCellStyle(style);
			        cell_6.setCellStyle(style);
			        cell_7.setCellStyle(style);
			        cell_8.setCellStyle(style);
			        cell_9.setCellStyle(style);
			        
			        Row row2 = sheet.createRow(1);
			        String[] columns = new String[]{"订单总数(笔)", "交易总金额(元)", "退款总金额(元)"};
			        Cell cell = null;
			        for(int i = 0; i < 3; i++){
			        	for(int j = 0; j < columns.length; j++){
			        		cell = row2.createCell((j + 2) + i * columns.length);  
			        		cell.setCellValue(columns[j]);// 单元格的值
			        		cell.setCellStyle(style);// 单元格样式
			        	}
			        }
				}
			};
		}else{// 对账详情
			interf = new CustomHeaderInterf() {
				
				@Override
				public void dealCustomHeader(HSSFSheet sheet, HSSFCellStyle style) {
					Row row = sheet.createRow(0);
					style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框    
					style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框    
					style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框    
					style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
					
					CellRangeAddress cra=new CellRangeAddress(0, 1, 0, 0);        
			        // 在sheet里增加合并单元格  
			        sheet.addMergedRegion(cra);  
			        Cell cell_1 = row.createCell(0); 
			        cell_1.setCellValue("交易时间");
			        
			        CellRangeAddress cra2=new CellRangeAddress(0, 1, 1, 1);        
			        // 在sheet里增加合并单元格  
			        sheet.addMergedRegion(cra2);  
			        Cell cell_2 = row.createCell(1); 
			        cell_2.setCellValue("资金账户");
			        
			        CellRangeAddress cra3=new CellRangeAddress(0, 1, 2, 2);        
			        // 在sheet里增加合并单元格  
			        sheet.addMergedRegion(cra3);  
			        Cell cell_3 = row.createCell(2); 
			        cell_3.setCellValue("交易单号");
			        
			        CellRangeAddress cra4=new CellRangeAddress(0, 1, 3, 3);        
			        // 在sheet里增加合并单元格  
			        sheet.addMergedRegion(cra4);  
			        Cell cell_4 = row.createCell(3); 
			        cell_4.setCellValue("交易类型");
			        
			        CellRangeAddress cra5=new CellRangeAddress(0, 0, 4, 5);      
			        sheet.addMergedRegion(cra5);  
			        Cell cell_5= row.createCell(4);
			        cell_5.setCellValue("综合支付平台");
			        
			        CellRangeAddress cra6=new CellRangeAddress(0, 0, 6, 7);        
			        sheet.addMergedRegion(cra6);
			        Cell cell_6= row.createCell(6);  
			        cell_6.setCellValue("第三方支付平台");
			        
			        CellRangeAddress cra7=new CellRangeAddress(0, 0, 8, 9);        
			        sheet.addMergedRegion(cra7);  
			        Cell cell_7 = row.createCell(8);  
			        cell_7.setCellValue("HIS支付平台");
			        
			        CellRangeAddress cra8=new CellRangeAddress(0, 1, 10, 10);        
			        sheet.addMergedRegion(cra8);
			        Cell cell_8 = row.createCell(10);
			        cell_8.setCellValue("对账状态");
			        
			        CellRangeAddress cra9=new CellRangeAddress(0, 1, 11, 11);        
			        sheet.addMergedRegion(cra9);  
			        Cell cell_9 = row.createCell(11);
			        cell_9.setCellValue("处理状态");
			        
			        cell_1.setCellStyle(style);
			        cell_2.setCellStyle(style);
			        cell_3.setCellStyle(style);
			        cell_4.setCellStyle(style);
			        cell_5.setCellStyle(style);
			        cell_6.setCellStyle(style);
			        cell_7.setCellStyle(style);
			        cell_8.setCellStyle(style);
			        cell_9.setCellStyle(style);
			        Row row2 = sheet.createRow(1);
			        String[] columns = new String[]{"交易金额(元)", "交易状态"};
			        Cell cell = null;
			        for(int i = 0; i < 3; i++){
			        	for(int j = 0; j < columns.length; j++){
			        		cell = row2.createCell((j + 4) + i * columns.length);  
			        		cell.setCellValue(columns[j]);// 单元格的值
			        		cell.setCellStyle(style);// 单元格样式
			        	}
			        }
				}
			};
		}
		return interf;
	}
	
	/**
	 * 根据对账记录list获取准备导出的数据list
	 * @param checkBillList 对账记录list
	 * @return 准备导出的数据list
	 */
	private List<List<Object>> getExportCheckBillDataList(List<Bill> checkBillList){
		List<List<Object>> list = new ArrayList<List<Object>>();
		if(checkBillList != null && checkBillList.size() > 0){
			for (Bill checkBill : checkBillList) {
				List<Object> rowDataList = new ArrayList<Object>();
				// 账单日期
				rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getBillDate()));
				rowDataList.add(checkBill.getAccountName());//账户名
//				if(checkBill.getTotal_w() != null){
					// 支付平台-订单总数(笔)
					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getTotal()));
					// 支付平台-交易总金额(元)
					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getAmount()));
					// 支付平台-退款总金额(元)
					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getRefund_amount()));
					// 第三方支付平台-订单总数(笔)
					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getTotal_out()));
					// 第三方支付平台-交易总金额(元)
					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getAmount_out()));
					// 第三方支付平台-退款总金额(元)
					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getRefund_amount_out()));
//				}else if(checkBill.getTotal_a() != null){
//					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getTotal_a()));
//					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getAmount_a()));
//					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getRefund_amount_a()));
//					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getA_total()));
//					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getA_amount()));
//					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getA_refund_amount()));
//				}else{
//					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getTotal()));
//					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getAmount()));
//					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getRefund_amount()));
//					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getTotal_out()));
//					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getAmount_out()));
//					rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getRefund_amount_out()));
//				}
				// HIS支付平台-订单总数(笔)
				rowDataList.add(CommonUtils.nullStringToEmpty(""));
				// HIS支付平台-交易总金额(元)
				rowDataList.add(CommonUtils.nullStringToEmpty(""));
				// HIS支付平台-退款总金额(元)
//				rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getOutAmout()));
				rowDataList.add(CommonUtils.nullStringToEmpty(""));
				// 对账状态
				if(checkBill.getCheckStatus() == null){// 应对值为null的情况
					rowDataList.add("");
	            }else if(checkBill.getCheckStatus() == 0){
					rowDataList.add("正常");
	            }else if(checkBill.getCheckStatus() == 1){
	            	rowDataList.add("异常");
	            }else{
	            	rowDataList.add("");
	            }
				//异常笔数
				rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getAbnormalNum()));
				// 对账时间
				rowDataList.add(CommonUtils.nullStringToEmpty(checkBill.getCreateTime()));
				//状态
				if(checkBill.getStatus() == null){// 应对值为null的情况
					rowDataList.add("");
	            }else if(checkBill.getStatus() == 0){
					rowDataList.add("确认");
	            }else if(checkBill.getStatus() == 1){
	            	rowDataList.add("待确认");
	            }else{
	            	rowDataList.add("");
	            }
				list.add(rowDataList);
			}
		}
		return list;
	}
	
	/**
	 * 根据对账详情记录list获取准备导出的数据list
	 * @param checkBillList 对账详情记录list
	 * @return 准备导出的数据list
	 */
	private List<List<Object>> getExportCheckBillDetailDataList(List<CheckBillDetails> detailList){
		List<List<Object>> list = new ArrayList<List<Object>>();
		if(detailList != null && detailList.size() > 0){
			for (CheckBillDetails detail : detailList) {
				List<Object> rowDataList = new ArrayList<Object>();
				// 交易时间
				rowDataList.add(CommonUtils.nullStringToEmpty(detail.getCreatDate()));
				//资金账户
				rowDataList.add(CommonUtils.nullStringToEmpty(detail.getAccountName()));
				// 交易单号
				rowDataList.add(CommonUtils.nullStringToEmpty(detail.getOut_trade_no()));
				// 交易类型
				rowDataList.add(CommonUtils.nullStringToEmpty(detail.getTransType()));
				// 支付平台-交易金额(元)
				rowDataList.add(detail.getActualPay() == null ? "" : detail.getActualPay() / 100.0d);
				// 支付平台-交易状态
				if(detail.getPayStatus() == null){// 应对值为null的情况
					rowDataList.add("");
	            }else if(detail.getPayStatus() == 0){
					rowDataList.add("失败");
	            }else if(detail.getPayStatus() == 1){
	            	rowDataList.add("成功");
	            }else if(detail.getPayStatus() == 3){
	            	rowDataList.add("已退款");
	            }else{
	            	rowDataList.add("");
	            }
				// 第三方平台-交易金额(元)
				rowDataList.add(detail.getOutAmount() == null ? "" : detail.getOutAmount() / 100.0d);
				// 第三方支付平台-交易状态
				if(detail.getOutStatus() == null){// 应对值为null的情况
					rowDataList.add("");
	            }else if(detail.getOutStatus() == 0){
					rowDataList.add("失败");
	            }else if(detail.getOutStatus() == 1){
	            	rowDataList.add("成功");
	            }else if(detail.getOutStatus() == 3){
	            	rowDataList.add("已退款");
	            }else{
	            	rowDataList.add("");
	            }
				// HIS支付平台-交易金额(元)
				rowDataList.add(detail.getHisAmount() == null ? "" : detail.getHisAmount() / 100.0d);
				// HIS支付平台-交易状态
				if(detail.getHisStatus() == null){// 应对值为null的情况
					rowDataList.add("");
	            }else if(detail.getHisStatus() == 0){
					rowDataList.add("失败");
	            }else if(detail.getHisStatus() == 1){
	            	rowDataList.add("成功");
	            }else{
	            	rowDataList.add("");
	            }
				// 对账状态
				if(detail.getBillStatus() == null){// 应对值为null的情况
					rowDataList.add("");
	            }else if(detail.getBillStatus() == 0){
					rowDataList.add("异常");
	            }else if(detail.getBillStatus() == 1){
	            	rowDataList.add("正常");
	            }else{
	            	rowDataList.add("");
	            }
				// 处理状态
				if(detail.getBillStatus() == 1 && detail.getHandle() == 0){
					rowDataList.add("--");
				}else if(detail.getHandle() == 0){
					rowDataList.add("未处理");
				}else{
					rowDataList.add("已处理");
				}
				
				list.add(rowDataList);
			}
		}
		return list;
	}
	
	    
	    /* 
	     * 重新对账
	     */
	    public Boolean recheck(String[] ids,String path) throws Exception{
	    	  List<CheckBillDetails> list2;;
	    	  CheckBillDetails sbd=new CheckBillDetails();
	    	  for(int i = 0;i < ids.length; i++){
	    		 Bill bill = billDao.findById(ids[i]);
	    		 String path1=readfile(path,bill.getBillDate()); 
	    		 List<CheckBillDetails> list = readXls(path1);//his对账单
	    		 if(bill != null){
	    			  sbd.setBillDate(bill.getBillDate());
	    			  sbd.setAccountCode(bill.getAccountCode());
	    			  list2 = checkBillDetailsDao.findAll(sbd, null);
	    			  Page page = new Page(0,list2.get(0).getCount(), list2.get(0).getCount());
	    			  list2 = checkBillDetailsDao.findAll(sbd, page);
	    			  Boolean flag=false;
	    			  Boolean status=true;//标记对账状态
	    			  Integer total_his = 0;//his订单数
	    			  Integer abnormalNum = 0;//异常笔数
	    			  Double amount_his = 0.0;//his总金额
	    			  Double refund_amount_his = 0.0;//his退款金额
	    			  for(int j = 0;j < list2.size(); j++){
	    				  for(int z = 0;z < list.size();z++){
	    					  String outTradeNo = list2.get(j).getOrderCode();
	    					  String transType = list2.get(j).getTransType();
	    					  //交易类型、订单相同
	    					  if(outTradeNo.equals(list.get(z).getOut_trade_no())&&transType.equals(list.get(z).getTransType())){
	    						  //累加笔数
	    						  total_his+=1;
	    						  Integer outAccount = list2.get(j).getOutAmount();
	    						  Integer outStatus = list2.get(j).getOutStatus();
	    						  Integer account = list2.get(j).getActualPay();
	    						  Integer payStatus = list2.get(j).getPayStatus();
	    						  //对比 金额、订单状态
	    						  if(outAccount == list.get(z).getHisAmount()&&outStatus == list.get(z).getHisStatus()
	    								  &&account == list.get(z).getHisAmount()&&payStatus == list.get(z).getHisStatus()
	    								  ){
	    							 
	    							  if(list2.get(z).getBillStatus().equals(0)){
	    								  abnormalNum-=1;
	    								  flag=checkBillDetailsDao.upHis(list.get(z).getHisStatus(),  list.get(z).getHisAmount(),1,list2.get(j).getId());  
	    							  }else{
	    								  flag=checkBillDetailsDao.upHis(list.get(z).getHisStatus(),  list.get(z).getHisAmount(),null,list2.get(j).getId());  
	    							  }
	    						  }else{
	    							  status=false;//标记对账异常
	    							  if(list2.get(z).getBillStatus().equals(1)){
	    								  //向对账明细表中添加HIS信息
	    								  abnormalNum+=1;
	    								  flag =checkBillDetailsDao.upHis(list.get(z).getHisStatus(),  list.get(z).getHisAmount(), 0,list2.get(j).getId());
	    							  }else{
	    								  flag =checkBillDetailsDao.upHis(list.get(z).getHisStatus(),  list.get(z).getHisAmount(),null,list2.get(j).getId());
	    							  }
	    						  }
	    						  if(transType.equals("0")){//交易类型：支付
	    							 //累加金额
	    							 amount_his+=list.get(z).getHisAmount(); 
	    						  }else{//交易类型：退款
	    							  //累加退款金额
	    							  refund_amount_his+=list.get(z).getHisAmount();
	    						  }
	    						  list.remove(j);
	    						  list2.remove(z);
	    						  j=j-1;
	    						  break;
	    					  }
	    				  }
	    			  }	
	    			  if(status){
	    				  bill=new Bill();
	    				  bill.setBid(Integer.parseInt(ids[i]));
	    				  bill.setTotal_his(total_his);
	    				  bill.setAmount_his(amount_his/100.0);
	    				  bill.setRefund_amount_his(refund_amount_his/100.0);
	    				  bill.setCheckStatus(0);
	    				  bill.setAbnormalNum(abnormalNum);
	    			  }else{
	    				  bill=new Bill();
	    				  bill.setBid(Integer.parseInt(ids[i]));
	    				  bill.setTotal_his(total_his);
	    				  bill.setAmount_his(amount_his/100.0);
	    				  bill.setRefund_amount_his(refund_amount_his/100.0);
	    				  bill.setCheckStatus(1);
	    				  bill.setAbnormalNum(abnormalNum);
	    			  }
	    			  if(flag&& billDao.modify(bill)){
	    				  return true;
	    			  }else{
	    				  return false;
	    			  }
	    		 }else{
	    			 return false;
	    		 }
	    	  }
			      return false;
	      }
	
	
	
	      /**
	        * 读取HIS对账单Excel
	        * @param path the path of the Excel
	        * @return
	        * @throws IOException
	      */
	     public List<CheckBillDetails> readXls(String path) throws IOException {
	 
	         InputStream is = new FileInputStream(path);
	         HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	      
	         List<CheckBillDetails> list = new ArrayList<CheckBillDetails>();
	         // Read the Sheet
	         for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
	             HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	             if (hssfSheet == null) {
	                 continue;
	             }
	             // Read the Row
	             for (int rowNum = 8; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	                 HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	                 if (hssfRow != null) {
	                	 CheckBillDetails bill = new CheckBillDetails();
	                     HSSFCell transType = hssfRow.getCell(0);//订单类型
	                     transType.setCellType(HSSFCell.CELL_TYPE_STRING);  
	                     HSSFCell outTradeNo = hssfRow.getCell(1);   //订单号
	                     outTradeNo.setCellType(HSSFCell.CELL_TYPE_STRING);  
	                     HSSFCell hisAmount = hssfRow.getCell(4);    //金额
	                     HSSFCell hisStatus = hssfRow.getCell(5);  //订单状态
	                     hisStatus.setCellType(HSSFCell.CELL_TYPE_STRING);  
	                     bill.setTransType(getValue(transType));
	                     bill.setOut_trade_no(getValue(outTradeNo));
	                     bill.setHisAmount((int)(Double.parseDouble(getValue(hisAmount))*100));
	                     bill.setHisStatus(Integer.parseInt(getValue(hisStatus)));
	                     list.add(bill);
	                 }
	             }
	         }
	         return list;
	     }
	       private String getValue(HSSFCell hssfCell) {
	          if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	              return String.valueOf(hssfCell.getBooleanCellValue());
	         } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	        	  return String.valueOf(hssfCell.getNumericCellValue());
	         } else {
	              return String.valueOf(hssfCell.getStringCellValue());
	          }
	      }
	       
	        /**
	         *根据日期查找对账单
	         */
			public static String  readfile(String filepath,String billDate) throws Exception {
				File file = new File(filepath);
				if (file.isDirectory()) {
					String[] filelist = file.list();
					for (int i = 0; i < filelist.length; i++) {
						File readfile = new File(filepath + "\\" + filelist[i]);
						if (!readfile.isDirectory()) {
							if(readfile.getName().contains(billDate)) {
								return readfile.getAbsolutePath();
							}
						}
						break;
					}
				}
				return "";
			}
	       
	       
	       public static void main(String[] args) throws Exception{
	    	   readfile(Constant.HIS_CHECK_PATH,"2017-07-04");
	       }
}
