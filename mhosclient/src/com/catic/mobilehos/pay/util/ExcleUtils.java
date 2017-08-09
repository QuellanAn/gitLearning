package com.catic.mobilehos.pay.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcleUtils {
	@SuppressWarnings("static-access")
	public static String getValue(Cell dName) {
		if(dName==null){
			return String.valueOf("");
		}else{
			if(DateUtil.isCellDateFormatted(dName)){  
		       Date date2 = dName.getDateCellValue();
		        return DateUtils.formatDate(date2, 0); 
			}else if (dName.getCellType() == dName.CELL_TYPE_BOOLEAN) {
				// 返回布尔类型的值
				return String.valueOf(dName.getBooleanCellValue());
			} else if (dName.getCellType() == dName.CELL_TYPE_NUMERIC) {
				// 返回数值类型的值
				NumberFormat nf = NumberFormat.getInstance();
		        nf.setGroupingUsed(false);
				return String.valueOf(nf.format(dName.getNumericCellValue()));
			}else if (dName.getCellType() == dName.CELL_TYPE_BLANK) {
				// 返回空类型的值
				return String.valueOf("");
			}else {
				// 返回字符串类型的值
				return String.valueOf(dName.getStringCellValue());
			}
		}
	}

	
	public static void excelOut(String[] cloumName, List<List<Object>> list,String fileName,String path){  
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(fileName);
		HSSFRow oneRow=sheet.createRow(0);
		oneRow.setHeight((short) 500);
		HSSFCell cell = null;
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		style.setFont(font);
		style.setWrapText(true);
		
		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style1.setFillForegroundColor(HSSFColor.AQUA.index);
		style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
		style1.setFont(font); 
          
        HSSFRow row = sheet.createRow(7);    
        for(int i = 0; i < cloumName.length; i++){    
            //单元格  
        	cell = row.createCell(i);    
        	cell.setCellStyle(style1);    
        	cell.setCellValue(cloumName[i]);   
        }    
          
        for (int i = 0; i < list.size(); i++){    
            row = sheet.createRow(i + 7);    
            List<Object> dataList = list.get(i);    
            for (int j = 0; j < dataList.size(); j++) {  
                // 表格内容样式设置  
            	cell = row.createCell(j);
            	cell.setCellStyle(style);    
            	cell.setCellValue(String.valueOf(dataList.get(j)));   
            	sheet.autoSizeColumn(( short ) j);
            }  
         }  
        
        try{    
        	FileOutputStream os = new FileOutputStream(path+fileName+".xls"); 
            workbook.write(os);  
            os.close();
        } catch (Exception e){  
             e.printStackTrace();    
        }
	}
	
	public static void modeDown(HttpServletRequest request,HttpServletResponse response){  
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFCell cell = null;
		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style1.setWrapText(true);
         
		String info="1.当订单类型为交易/退款时，各个字段表示为交易/退款单号（订单单号），第三方交易/退款流水号（第三方流水号），HIS交易/退款流水号（HIS流水号），交易/退款金额（订单金额），交易/退款状态（订单状态），交易/退款时间（订单时间）。" +
				"2.当订单类型为交易时，可填写“缴费项目”字段，“退款类型”不用填写；当订单类型为退款时，可填写“退款类型”，“缴费项目”不用填写。" +
				"3.数据请严格按照模板格式以及提示信息填写正确，数据填写完成修改好文件名称则直接导入改文件即可！";
		String infos="订单类型(0:交易/1:退款)，订单状态(0,)";
		String[] headers={"订单类型", "订单单号", "第三方流水号", "HIS流水号", "订单金额", "订单状态",
				"订单时间", "缴费项目(交易单填写)", "退款类型(退款单填写)"};
		sheet.addMergedRegion(new Region(0, (short)0, 4, (short)(headers.length-1))); 
		HSSFRow rInfo = sheet.createRow(0);   
	    HSSFCell cInfo = rInfo.createCell(0); 
	    cInfo.setCellStyle(style1);  
	    cInfo.setCellValue(info);
	    
	    sheet.addMergedRegion(new Region(5, (short)0, 6, (short)(headers.length-1)));
	    rInfo = sheet.createRow(5);   
	    cInfo = rInfo.createCell(0); 
	    cInfo.setCellStyle(style1);  
	    cInfo.setCellValue(infos);
	    
		HSSFRow row = sheet.createRow(7);    
        for(int i = 0; i < headers.length; i++){    
            //单元格  
        	cell = row.createCell(i);    
        	cell.setCellStyle(style1);    
        	cell.setCellValue(headers[i]);
        	if(headers[i].equals("订单时间")){
        		sheet.setColumnWidth(i, headers[i].getBytes().length*480);
        	}else{
        		sheet.setColumnWidth(i, headers[i].getBytes().length*280);
        	}
        }
        try{    
        	response.reset();
        	OutputStream os = response.getOutputStream();
			response.setHeader("Content-disposition","attachment; filename="+URLEncoder.encode("模板文件", "utf-8")+".xls");//设定输出文件头
			response.setContentType("application/msexcel");//定义输出类型
            workbook.write(os);  
            os.close();
        } catch (Exception e){  
             e.printStackTrace();    
        }
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
    
    /**
	 * 获取Excel文件的第一个sheet
	 * @param file excel文件
	 * @param fileName 文件完成路径
	 * @return 第一个sheet
	 */
	public static Sheet getFirstSheet(File file, String fileName){
		try{
			InputStream stream = new FileInputStream(file);
			Workbook wb = null;
			if (fileName.endsWith(".xls")) {
				wb = new HSSFWorkbook(stream);
			} else if (fileName.endsWith(".xlsx")) {
				wb = new XSSFWorkbook(file.getAbsolutePath());
			}
			Sheet sheet = wb.getSheetAt(0);
			// 去除空白行
			removeBlankRow(sheet);
			return sheet;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
     * 去除空白行，以防出现NullPointer异常
     * @param sheet sheet页
     */
    private static void removeBlankRow(Sheet sheet){
		CellReference cellReference = new CellReference("A4");
		boolean flag = false;
		for (int i = cellReference.getRow(); i <= sheet.getLastRowNum();) {
			Row r = sheet.getRow(i);
			if (r == null) {
				// 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动
				sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
				continue;
			}
			flag = false;
			for (Cell c : r) {
				if (c.getCellType() != Cell.CELL_TYPE_BLANK) {
					flag = true;
					break;
				}
			}
			if (flag) {
				i++;
				continue;
			} else {// 如果是空白行（即可能没有数据，但是有一定格式）
				if (i == sheet.getLastRowNum())// 如果到了最后一行，直接将那一行remove掉
					sheet.removeRow(r);
				else
					// 如果还没到最后一行，则数据往上移一行
					sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
			}
		}
    }
    
    /**
     * 获取单元格数据内容为字符串类型的数据
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    public static String getStringCellValue(Cell cell) {
        String strCell = "";
        if(cell == null){
        	return "";
        }
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf((int)cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        return strCell;
    }
	
}