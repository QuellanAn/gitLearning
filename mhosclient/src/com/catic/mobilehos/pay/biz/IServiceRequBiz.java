package com.catic.mobilehos.pay.biz;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;


/**
 * 这里定义服务层需要请求器标准接口
 */
public interface IServiceRequBiz {

	
	 //Service依赖的底层https请求器必须实现这么一个接口
    public String sendPost(String api_url,Object xmlObj) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException;
}
