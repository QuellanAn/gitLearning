package com.catic.mobilehos.service.vo;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Jsp页面分页管理
 * @author xieweipeng
 *
 * @param <VO>
 */
public class Page<VO> {
	
	/**
	 * 当前页页的数据
	 */
	private List<VO> curPageData;
	
	/**
	 * 总记录数
	 */
	private int totalRecord;
	
	/**
	 * 总页数
	 */
	private int totalPage;
    
    /**
     * 当前页
     */
    private int curPage;
    
    /**
     * 每页记录数
     */
    private int pageSize;
    
    private String queryUrl;
    
    
    
    
    
    public Page(int totalRecord, int pageSize, int curPage){
    	this.totalRecord = totalRecord;
    	this.pageSize = pageSize;
    	this.totalPage = countTotalPage(pageSize, totalRecord);
    	if (curPage > 0 && curPage <= totalPage){
    		this.curPage = curPage;
    	}else if (curPage <= 0 || totalPage <= 0){
    		this.curPage = 1;
    	}else{
    		this.curPage = totalPage;
    	}
    }
    
    
    
    public String getQueryUrl() {
		return queryUrl;
	}


	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
	
	public void setQueryUrl(String baseUrl, Map<String, String> paras){
		this.queryUrl = addParasToUrl(baseUrl, paras);
	}
	
	private String addParasToUrl(String baseUrl, Map<String, String> paras){
		baseUrl += "?";
		for (Entry<String, String> p : paras.entrySet()){
			if (p.getValue() != null){
				baseUrl += "&" + p.getKey() + "=" + p.getValue();
			}
		}
		return baseUrl;
	}



	public boolean isFirstPage() {
        return curPage == 1;    
    }
    
    public boolean isLastPage() {
        return curPage == totalPage || totalPage == 0;   
    }
    
    public boolean isHasPreviousPage() {
        return curPage > 1;        
    }
    
    public boolean isHasNextPage() {
        return curPage < totalPage;   
    }
    

    public int getPageSize() {
		return pageSize;
	}
    

	public int getTotalPage() {
		return totalPage;
	}
	
	public int getLastPage(){
		return totalPage;
	}
	
	public String getLastPageUrl(){
		return getUrl(this.queryUrl, getLastPage());
	}
	
	public String getPrevPageUrl(){
		return getUrl(this.queryUrl, getPrevPage());
	}
	
	public String getFirstPageUrl(){
		return getUrl(this.queryUrl, 1);
	}
	
	public String getNextPageUrl(){
		return getUrl(this.queryUrl, getNextPage());
	}


	private String getUrl(String url, int page){
		if (url != null){
			if (url.contains("?")){
				url = url + "&page=" + page;
			}else{
				url = url + "?page=" + page;
			}
			return url;
		}else{
			return "";
		}

	}
	
	public int getCurPage() {
		return curPage;
	}
	
	public int getPrevPage(){
		int prevPage = 1;
		if (curPage > 1){
			prevPage = curPage - 1;
		}else{
			prevPage = 1;
		}
		return prevPage;
	}
	
	public int getNextPage(){
		int nextPage = totalPage;
		if (curPage < totalPage){
			nextPage = curPage + 1;
		}else{
			nextPage = totalPage;
		}
		return nextPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}
	
	public int getOffset(){
		return countOffset(this.pageSize, this.curPage);
	}
	
	public List<VO> getCurPageData() {
		return curPageData;
	}

	public void setCurPageData(List<VO> curPageData) {
		this.curPageData = curPageData;
	}

	/**
     * 计算总页数,静态方法,供外部直接通过类名调用
     * @param pageSize 每页记录数
     * @param totalRecord 总记录数
     * @return 总页数
     */
    public static int countTotalPage(final int pageSize, final int totalRecord){
        int totalPage = totalRecord % pageSize == 0 ? totalRecord/pageSize : totalRecord/pageSize+1;
        return totalPage;
    }
    
    /**
     * 计算当前页开始记录
     * @param pageSize 每页记录数
     * @param currentPage 当前第几页
     * @return 当前页开始记录号
     */
    public static int countOffset(final int pageSize,final int currentPage){
        final int offset = pageSize*(currentPage-1);
        return offset;
    }
    
    /**
     * 计算当前页,若为0或者请求的URL中没有"?page=",则用1代替
     * @param page 传入的参数(可能为空,即0,则返回1)
     * @return 当前页
     */
    public static int countCurrentPage(int page){
        final int curPage = (page==0?1:page);
        return curPage;
    }
    
    

}
