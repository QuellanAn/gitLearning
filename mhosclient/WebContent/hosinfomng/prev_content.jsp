<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  


<div style="margin-left: 30px;margin-right: 30px;">
		<p>
			<s:label name="subject" id="subject"></s:label>
		</p>
		<p>
			<label>发布时间：</label>
			<s:label name="author" id="author"></s:label>
			<label id="pub_time"></label>
		</p>
		<div id="div_content">
				<s:hidden name="content" id="content"></s:hidden>
		</div>
		
</div>
