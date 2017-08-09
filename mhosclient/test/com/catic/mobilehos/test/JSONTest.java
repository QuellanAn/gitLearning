package com.catic.mobilehos.test;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.catic.mobilehos.service.AppRegOrderStatus;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONTest {
	
	
	public static  void main(String[] args) throws ParseException{
		String curnum = "a2";
		String pnum = "b22";
		System.out.println(getFrontcount(curnum, pnum));
	}
	
	
	private static String getFrontcount(String curnum, String pnum){
		for (int i=curnum.length()-1; i>=0; i--){
			char c = curnum.charAt(i);
			if (!Character.isDigit(c)){
				curnum = i+1<curnum.length()?curnum.substring(i+1) : null;
				break;
			}
		}
		
		for (int i=pnum.length()-1; i>=0; i--){
			char c = pnum.charAt(i);
			if (!Character.isDigit(c)){
				pnum = i+1<pnum.length()?pnum.substring(i+1) : null;
				break;
			}
		}
		
		if (curnum != null && pnum != null){
			int count = Integer.parseInt(pnum) - Integer.parseInt(curnum);
			count = count < 0 ? 0: count;
			return String.valueOf(count);
		}else{
			return "0";
		}
	}
	


}
