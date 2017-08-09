package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.HtmlImagesPO;

public interface HtmlImagesDAO {
	
	void addHtmlImages(HtmlImagesPO htmlImages);
	
	void deleteHtmlImages(String infoid);
	
	List<HtmlImagesPO> findHtmlImagesByInfoId(String id);

}
