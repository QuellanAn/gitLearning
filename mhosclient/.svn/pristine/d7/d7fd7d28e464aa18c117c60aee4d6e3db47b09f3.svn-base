package com.catic.mobilehos.utils;

import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

public class Common implements Constant {
	
	private static Calendar calendar = Calendar.getInstance();
	
	
	public static String getUUID(){
    	return UUID.randomUUID().toString().replaceAll("-", "");
    }
	
    /**
     * 
     * 取最大值
     * @author  YangJiaJie
     * @created 2016-8-18 下午4:00:26
     */
    	public static int getMaxNo(Object[] array) {
    		for (int i = 0; i < array.length - 1; i++) {
    			for (int j = 0; j < array.length - i - 1; j++) {
    				if (Integer.parseInt(array[j].toString()) < Integer.parseInt(array[j + 1].toString())) {
    					Object temp = array[j];
    					array[j] = array[j + 1];
    					array[j + 1] = temp;
    				}
    			}
    		}
    		return Integer.parseInt(array[0].toString());
    	}
    
	public static void main(String[] args) {
		//getFristDayByMonth();
		//getLastDay();
	
	}

}
