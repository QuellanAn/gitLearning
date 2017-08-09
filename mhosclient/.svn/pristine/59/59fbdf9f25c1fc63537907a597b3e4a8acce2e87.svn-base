package com.catic.mobilehos.pay.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {

	/**
	 * 
	 * @param response
	 * @param result
	 */
	public static void response(HttpServletResponse response, String json) {

		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			response.getWriter().print(json);
			System.out.println("response-->"+json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg>" +
                "</xml>";

	}
	
	/**
	 * 
	 * @param response
	 * @param result
	 */
	public static void responseHtml(HttpServletResponse response, String html) {

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			response.getWriter().print(html);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
     * 功能描述:通过传入一个列表对象,调用指定方法将列表中的数据生成一个JSON规格指定字符串 
     * 
     * @param list 
     *             列表对象 
     * @return java.lang.String 
     */    
   public static String listToJson(List<?> list) {     
        StringBuilder json = new StringBuilder();     
        json.append("[");     
       if (list != null && list.size() > 0) {     
           for (Object obj : list) {     
                json.append(objectToJson(obj));     
                json.append(",");     
            }     
            json.setCharAt(json.length() - 1, ']');     
        } else {     
            json.append("]");     
        }     
       return json.toString();     
    }  
   
   /** 
    * @param object 
    *             任意对象 
    * @return java.lang.String 
    */    
  public static String objectToJson(Object object) {     
       StringBuilder json = new StringBuilder();     
      if (object == null) {     
           json.append("\"\"");     
       } else if (object instanceof String || object instanceof Integer) {   
           json.append("\"").append(object.toString()).append("\"");    
       } else {     
           json.append(beanToJson(object));     
       }     
      return json.toString();     
   }     
  
  /** 
   * 功能描述:传入任意一个 javabean 对象生成一个指定规格的字符串 
   * 
   * @param bean 
   *             bean对象 
   * @return String 
   */    
 public static String beanToJson(Object bean) {     
      StringBuilder json = new StringBuilder();     
      json.append("{");     
      PropertyDescriptor[] props = null;     
     try {     
          props = Introspector.getBeanInfo(bean.getClass(), Object.class)     
                  .getPropertyDescriptors();     
      } catch (IntrospectionException e) {     
      }     
     if (props != null) {     
         for (int i = 0; i < props.length; i++) {     
             try {    
                  String name = objectToJson(props[i].getName());     
                  String value = objectToJson(props[i].getReadMethod().invoke(bean));    
                  json.append(name);     
                  json.append(":");     
                  json.append(value);     
                  json.append(",");    
              } catch (Exception e) {     
              }     
          }     
          json.setCharAt(json.length() - 1, '}');     
      } else {     
          json.append("}");     
      }     
     return json.toString();     
  }   
}
