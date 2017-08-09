package com.catic.mobilehos.pay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class PaulTool {

	public String doPost(String url, String params) {
		String result = "";
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		if (session != null) {
			httpclient = (CloseableHttpClient) session.getAttribute("httpclient");
		}
		if (httpclient == null) {
			httpclient = HttpClients.createDefault();
		}
		try {
			HttpPost httppost = new HttpPost(url);
			httppost.setHeader("accept", "*/*");
			httppost.setHeader("connection", "Keep-Alive");
			httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");

			if (params != null && !"".equals(params)) {
				MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
				reqEntity.setCharset(Charset.forName("utf-8"));
				String[] nameAndValue = params.split("&");
				for (String s : nameAndValue) {
					String[] keyAndvalue = s.split("=");
					StringBody value = new StringBody("", ContentType.TEXT_PLAIN);
					if(keyAndvalue.length == 2) {
						value = new StringBody(keyAndvalue[1], Charset.forName("utf-8"));
						//value = new StringBody(keyAndvalue[1],  ContentType.TEXT_PLAIN);
					}
					reqEntity.addPart(keyAndvalue[0], value);
				}
				HttpEntity httpEntity = reqEntity.build();
				httppost.setEntity(httpEntity);
			}
			response = httpclient.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			if(resEntity != null) {
				result = EntityUtils.toString(resEntity, "utf-8");
			}
			EntityUtils.consume(resEntity);
			//if (url.endsWith("center/login") || url.endsWith("#forgetPswd") || url.endsWith("#register")) {
				session.setAttribute("httpclient", httpclient);
			//}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				// httpclient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}

	public String doPost2(String url, String params) {
		String result = "";
		BufferedReader in = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpPost httppost = new HttpPost(url);

			httppost.setHeader("accept", "*/*");
			httppost.setHeader("connection", "Keep-Alive");
			httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");

			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = null;
			if (request != null) {
				session = request.getSession();
				if (session != null) {
					Header[] wscookie = (Header[]) session.getAttribute("wscookie");
					if (wscookie != null && wscookie.length > 0) {
						httppost.setHeaders(wscookie);
						System.out.println("add header cookie!");
						for (Header h : wscookie) {
							System.out.println(h);
						}
					}
				}
			}
			if (params != null && !"".equals(params)) {
				MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();

				String[] nameAndValue = params.split("&");
				for (String s : nameAndValue) {
					String[] keyAndvalue = s.split("=");
					StringBody value = new StringBody(keyAndvalue[1], ContentType.TEXT_PLAIN);
					reqEntity.addPart(keyAndvalue[0], value);
				}
				HttpEntity httpEntity = reqEntity.build();
				httppost.setEntity(httpEntity);
			}

			response = httpclient.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null && resEntity.getContentLength() > 0) {
				// 定义 BufferedReader输入流来读取URL的响应
				in = new BufferedReader(new InputStreamReader(resEntity.getContent(), "UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			}
			EntityUtils.consume(resEntity);

			if (session != null) {
				Header[] cookie = response.getHeaders("Set-Cookie");
				if (cookie != null && cookie.length > 0) {
					session.setAttribute("wscookie", cookie);
					System.out.println("get header cookie!");
					for (Header h : cookie) {
						System.out.println(h);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}

	public String doPost(String url) {
		return doPost(url, null);
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public String doPost_bak(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			System.out.println(url + "  " + param);

			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			if (session != null) {
				String wscookie = (String) session.getAttribute("wscookie");
				if (wscookie != null && !"".equals(wscookie)) {
					conn.setRequestProperty("Cookie", wscookie);
				}
			}
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			if (param != null) {
				out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
				out.print(param);
				out.flush();
			}
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			if (session != null) {
				String cookie = conn.getHeaderField("Set-Cookie");
				// System.out.println(cookie);
				if (cookie != null && !cookie.equals("")) {
					session.setAttribute("wscookie", cookie);
				}
			}
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
		System.out.println("result is: " + result);

		return result;
	}

	public String Md5(String text) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte b[] = md.digest();

			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// System.out.println("result: " + buf.toString());// 32位的加密
			// System.out.println("result: " +
			// buf.toString().substring(8,24));//16位的加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	/**
	 * 
	 * 将JSONObjec对象转换成Map-List集合
	 * 
	 * @see JSONHelper#reflect(JSONArray)
	 * 
	 * @param json
	 * 
	 * @return
	 */
	public HashMap<String, Object> reflect(JSONObject json) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Set<?> keys = json.keySet();
		for (Object key : keys) {
			Object o = json.get(key);
			if (o instanceof JSONArray)
				map.put((String) key, reflect((JSONArray) o));
			else if (o instanceof JSONObject)
				map.put((String) key, reflect((JSONObject) o));
			else
				map.put((String) key, o);
		}
		return map;
	}

	/**
	 * 
	 * 将JSONArray对象转换成Map-List集合
	 * 
	 * @see JSONHelper#reflect(JSONObject)
	 * 
	 * @param json
	 * 
	 * @return
	 */
	public List<Object> reflect(JSONArray json) {
		List<Object> list = new ArrayList<Object>();
		for (Object o : json) {
			if (o instanceof JSONArray) {
				list.add(reflect((JSONArray) o));
			} else if (o instanceof JSONObject) {
				list.add(reflect((JSONObject) o));
			} else {
				list.add(o);
			}
		}
		return list;
	}
	
	/**
	 * <p>
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * </p>
	 * 
	 * @param url 请求的URL地址
	 *         
	 * @param params请求的查询参数,可以为null
	 *            
	 * @param charset字符集
	 *            
	 * @param pretty是否美化
	 *            
	 * @return 返回请求响应的HTML
	 */
	public String getDoPostURL(String url, Map<String, Object> map, String charset, boolean pretty) {
		StringBuffer sb = new StringBuffer();
		PostMethod method = new PostMethod(url);
		try {
			HttpClient client = new HttpClient();
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
			// 设置Http Post数据
			if (map != null) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					method.addParameter(entry.getKey(), entry.getValue().toString());
				}
			}
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				// 读取为 InputStream，在网页内容数据量大时候推荐使用
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (pretty) {
						sb.append(line).append(System.getProperty("line.separator"));
					} else {
						sb.append(line);
					}
				}
				reader.close();
			}
		} catch (IOException e) {
			System.out.println("执行HTTP Post请求" + url + "时，发生异常！");
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return sb.toString();
	}
}
