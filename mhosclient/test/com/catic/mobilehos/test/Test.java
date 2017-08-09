package com.catic.mobilehos.test;  

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

/**  
 * 类说明:  
 * @author yjj   
 * Date: 2017-5-2
 */
public class Test {
	
	/*public static void main(String[] args) throws IOException {
		FileOutputStream fos=new FileOutputStream("D:\\13.xls");  
        
		HSSFWorkbook wb=new HSSFWorkbook();  
          
        HSSFSheet sheet=wb.createSheet("测试");  
        
        HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
        
        CellRangeAddress cra=new CellRangeAddress(0, 1, 0, 0);        
        //在sheet里增加合并单元格  
        sheet.addMergedRegion(cra);  
        Row row = sheet.createRow(0);  
        Cell cell_1 = row.createCell(0); 
        cell_1.setCellStyle(style);
        cell_1.setCellValue("账单日期");
        
        CellRangeAddress cra2=new CellRangeAddress(0, 0, 1, 3);        
        //在sheet里增加合并单元格  
        sheet.addMergedRegion(cra2);  
        Cell cell_2 = row.createCell(1);
        cell_2.setCellStyle(style);
        cell_2.setCellValue("支付平台");
        
        CellRangeAddress cra3=new CellRangeAddress(0, 0, 4, 6);        
        //在sheet里增加合并单元格  
        sheet.addMergedRegion(cra3);  
        Cell cell_3 = row.createCell(4);  
        cell_3.setCellStyle(style);
        cell_3.setCellValue("第三方支付平台");
        
        CellRangeAddress cra4=new CellRangeAddress(0, 0, 7, 9);        
        //在sheet里增加合并单元格  
        sheet.addMergedRegion(cra4);  
        Cell cell_4 = row.createCell(7);  
        cell_4.setCellStyle(style);
        cell_4.setCellValue("HIS支付平台");
        
        CellRangeAddress cra5=new CellRangeAddress(0, 1, 10, 10);        
        //在sheet里增加合并单元格  
        sheet.addMergedRegion(cra5);  
        Cell cell_5 = row.createCell(10);  
        cell_5.setCellValue("对账时间");
        
        CellRangeAddress cra6=new CellRangeAddress(0, 1, 11, 11);        
        //在sheet里增加合并单元格  
        sheet.addMergedRegion(cra6);  
        Cell cell_6 = row.createCell(11);  
        cell_6.setCellValue("对账状态");
          
        
        Row row2 = sheet.createRow(1);
        Cell cell_7 = row2.createCell(1);  
        cell_7.setCellValue("订单总数(笔)");
        
        Cell cell_8 = row2.createCell(2);  
        cell_8.setCellValue("交易总金额(笔)");
        
        Cell cell_9 = row2.createCell(3);  
        cell_9.setCellValue("退款总金额(笔)");
        
        Cell cell_10 = row2.createCell(4);  
        cell_10.setCellValue("订单总数(笔)");
        
        Cell cell_11 = row2.createCell(5);  
        cell_11.setCellValue("交易总金额(笔)");
        
        Cell cell_12 = row2.createCell(6);  
        cell_12.setCellValue("退款总金额(笔)");
        
        Cell cell_13 = row2.createCell(7);  
        cell_13.setCellValue("订单总数(笔)");
        
        Cell cell_14 = row2.createCell(8);  
        cell_14.setCellValue("交易总金额(笔)");
        
        Cell cell_15 = row2.createCell(9);  
        cell_15.setCellValue("退款总金额(笔)");
        
        cell_4.setCellStyle(style);
        cell_5.setCellStyle(style);
        cell_6.setCellStyle(style);
        cell_7.setCellStyle(style);
        cell_8.setCellStyle(style);
        cell_9.setCellStyle(style);
        cell_10.setCellStyle(style);
        cell_11.setCellStyle(style);
        cell_12.setCellStyle(style);
        cell_13.setCellStyle(style);
        cell_14.setCellStyle(style);
        cell_15.setCellStyle(style);
          
        wb.write(fos);  
          
        fos.close();
	}*/
	
	public static void main(String[] args) throws IOException {
		FileOutputStream fos=new FileOutputStream("D:\\template.xls");  
        
		HSSFWorkbook wb=new HSSFWorkbook();  
          
        HSSFSheet sheet=wb.createSheet();  
        
        HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
        
		String[] headers = new String[]{"终端编号(*)","终端名称(*)","院区(*)","终端类型(*)","终端状态(*)","位置(*)","备注"};
		HSSFRow row = sheet.createRow(0);    
		HSSFCell cell = null;
        for(int i = 0; i < headers.length; i++){    
            //单元格  
        	cell = row.createCell(i);    
        	cell.setCellStyle(style);    
        	cell.setCellValue(headers[i]);
        	sheet.setColumnWidth(i, headers[i].getBytes().length * 256);
        }
        
        String[] textlist1 = new String[]{"南区","北院"};
        sheet = setHSSFValidation(sheet, textlist1, 1, 65535, 2, 2);
        String[] textlist2 = new String[]{"发卡一体机","自助取票机","报告查询机"};
        sheet = setHSSFValidation(sheet, textlist2, 1, 65535, 3, 3);
        String[] textlist3 = new String[]{"启用","故障","关机","禁用"};
        sheet = setHSSFValidation(sheet, textlist3, 1, 65535, 4, 4);
          
        wb.write(fos);  
          
        fos.close();
	}
	
	/** 
     * 设置某些列的值只能输入预制的数据,显示下拉框. 
     * @param sheet 要设置的sheet. 
     * @param textlist 下拉框显示的内容 
     * @param firstRow 开始行 
     * @param endRow 结束行 
     * @param firstCol   开始列 
     * @param endCol  结束列 
     * @return 设置好的sheet. 
     */  
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet,  
            String[] textlist, int firstRow, int endRow, int firstCol,  
            int endCol) {  
        // 加载下拉列表内容  
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);  
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列  
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,endRow, firstCol, endCol);  
        // 数据有效性对象  
        HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);  
        sheet.addValidationData(data_validation_list);  
        return sheet;  
    }

}
 