package com.catic.mobilehos.pay.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csvreader.CsvReader;

/**
 * @author Administrator 
 * 解析对账单
 */
public class ParseBill {

	/**
	 * 解析支付宝对账单
	 * 
	 * @param filePath
	 * @return
	 */
	public ArrayList<String[]> parseAlipay(String filePath) {
		try {
			ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
			CsvReader reader = new CsvReader(filePath.substring(0,
					filePath.length() - 4)
					+ "_业务明细(汇总).csv", ',', Charset.forName("gbk")); // 一般用这编码读就可以了
			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。

			while (reader.readRecord()) { // 逐行读入除表头的数据
				csvList.add(reader.getValues());
			}
			reader.close();
			return csvList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 解析微信对账单
	 * @param filePath
	 * @return
	 */
	public List<HashMap<String, String>> parseWechat(String filePath) {
	  try {
			String result = readTxtFile(filePath, "gbk");
			String[] strs = result.split(",");
			strs = result.split("`", 2);
			String[] key = strs[0].split(",");
			String[] value = strs[1].split("总", 2);
			String[] value1 = value[0].split("`");
			List<HashMap<String, String>> list1=new ArrayList<HashMap<String,String>>();
			int i=0;
			while(i<value1.length){	
				HashMap<String, String> map=new HashMap<String, String>();		
				for(int j=0;j<key.length;j++){						
					map.put(key[j], value1[i]);
					System.out.println(key[j]+"/"+value1[i]);		
					i=i+1;	
				}						
				list1.add(map);
			}
			return list1;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;

	}
	
	 /**
	  * 读取TXT文件
	 * @param filePath
	 * @param encoding
	 * @return
	 */
	public static String readTxtFile(String filePath,String encoding ){
	        try {
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    StringBuilder sb=new StringBuilder(lineTxt);
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                    	sb.append(lineTxt);
	                       
	                    }
	                    read.close();
	                    return sb.toString();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
	        } catch (Exception e) {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }
	        return null;
	    }
}
