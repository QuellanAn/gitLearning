/**   
 * @Title: GetServiceImplPort.java 
 * @Package com.catic.mobilehos.utils 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-19 下午2:25:47 
 * 
 */
package com.catic.mobilehos.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

import com.catic.mobilehos.webservice.timing.TimingService;
import com.catic.mobilehos.webservice.timing.TimingServiceImplService;

/**
 * @author WANG webService 获取服务接口的公共类
 */
public class GetServiceImplPort {
	public final static QName SERVICE_NAME = new QName("http://impl.timing.webService.mobilehos.catic.com/", "TimingServiceImplService");
	private static final URL WSDL_LOCATION;
	static {
		URL url = null;
		try { 			// http://localhost:8080/mhos_mgt_server/ws/timingService?wsdl
			url = new URL("http://localhost:8080/mhos_mgt_server/ws/timingService?wsdl");
		} catch (MalformedURLException e) {
			Logger.getLogger(TimingServiceImplService.class.getName()).log(java.util.logging.Level.INFO, "Can not initialize the default wsdl from {0}",
					"http://localhost:8080/mhos_mgt_server/ws/timingService?wsdl");
		}
		WSDL_LOCATION = url;
	}

	public TimingService getPort() {
		return new TimingServiceImplService(WSDL_LOCATION, SERVICE_NAME).getTimingServiceImplPort();
	}
}
