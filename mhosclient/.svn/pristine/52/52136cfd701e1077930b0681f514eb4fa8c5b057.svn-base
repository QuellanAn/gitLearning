package com.catic.mobilehos;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * 该类已经不使用
 * @author xieweipeng
 *
 */
public class CustomStrutsPrepareAndExecuteFilter extends
		StrutsPrepareAndExecuteFilter {
	
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		super.init(arg0);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
        String url = ((HttpServletRequest)request).getRequestURI();  
        if (!url.toLowerCase().endsWith("servlet")) {
            super.doFilter(request, response, chain);  
        } else {  
            chain.doFilter(request, response);  
        }  
	}
	
	

}
