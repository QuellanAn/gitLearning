package com.catic.mobilehos.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class PaulTool {
	public static String serverUrl = "http://localhost:80/mhoswechat/";
	
	public static String doPost(String url ) {
		// TODO Auto-generated method stub
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			//System.out.println(url + "*****");

			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			System.out.println("*****1");
//			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//			HttpSession session = request.getSession();
//			if (session != null) {
//				String wscookie = (String) session.getAttribute("wscookie");
//				if (wscookie != null && !"".equals(wscookie)) {
//					conn.setRequestProperty("Cookie", wscookie);
//				}
//			}
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
//			if (param != null) {
//				System.out.println("****"+param);
//				out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
//				out.print(param);
//				out.flush();
//			}
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
//			if (session != null) {
//				String cookie = conn.getHeaderField("Set-Cookie");
//				// System.out.println(cookie);
//				if (cookie != null && !cookie.equals("")) {
//					session.setAttribute("wscookie", cookie);
//				}
//			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
			result = "";
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		//System.out.println("result is: " + result);

		return result;
	}
}
