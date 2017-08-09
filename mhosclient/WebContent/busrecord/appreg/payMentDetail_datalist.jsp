<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<s:append var="list">
	<s:param value="%{pageBeanDetail.curPageData}" />
</s:append>
<s:hidden id="hid_totalcount" value="%{pageBeanDetail.totalRecord}" />
<s:hidden id="hid_currentPage" value="%{pageBeanDetail.curPage}" />

<%
	Page<CardPayDetailVO> pageBean = (Page<CardPayDetailVO>) request
	.getAttribute("pageBeanDetail");
	List<CardPayDetailVO> lst = pageBean.getCurPageData();
	
%>
	
<input id="hid_totalcount" type="hidden"
	value="<%=pageBean.getTotalRecord()%>" />
<script type="text/javascript">
	refresh_totalcount();
</script>
<table id="tb_cnt" class="list-table" style="margin-top: 5px;margin-left: 5px;">
	<thead>
		<tr>
			<th style="margin-left: 5px;margin-right: 5px;">医嘱单号</th>
			<th style="margin-left: 5px;margin-right: 5px;">收费项目</th>
			<th style="margin-left: 5px;margin-right: 5px;">单价</th>
			<th style="margin-left: 5px;margin-right: 5px;">数量</th>
			<th style="margin-left: 5px;margin-right: 5px;">金额</th>
		</tr>
	</thead>
	<tbody id="tb_cnt_body">
		<s:iterator value="list" var="list" status="status">
		<tr>
			<td align="center">${cpId} </td>
			<td align="center">${name}</td>
			<td align="center">${amount}</td>
			<td align="center">${number}</td>
			<td align="center">${totalAmount}</td>
		</tr>
		</s:iterator>
	</tbody>
</table>

<div class="pagination-wrap f-fr">
	<s:if test="%{pageBeanDetail.isFirstPage()}">
		<a class="pagination">首页</a>
	</s:if>
	<s:else>
		<a href="#" class="pagination"
			onclick="javascript:datalist_ctrl.topage('<s:property value="%{pageBeanDetail.getFirstPageUrl()}"/>');return false;">
			首页 </a>
	</s:else>
	<s:if test="%{pageBeanDetail.isFirstPage()}">
		<a class="pagination">上页</a>
	</s:if>
	<s:else>
		<a href="#" class="pagination"
			onclick="javascript:datalist_ctrl.topage('<s:property value="%{pageBeanDetail.getPrevPageUrl()}"/>');return false;">
			上页 </a>
	</s:else>
	<s:if test="%{pageBeanDetail.isLastPage()}">
		<a class="pagination">下页</a>
	</s:if>
	<s:else>
		<a href="#" class="pagination"
			onclick="javascript:datalist_ctrl.topage('<s:property value="%{pageBeanDetail.getNextPageUrl()}"/>'); return false;">
			下页 </a>
	</s:else>
	<s:if test="%{pageBeanDetail.isLastPage()}">
		<a class="pagination">末页</a>
	</s:if>
	<s:else>
		<a href="#" class="pagination"
			onclick="javascript:datalist_ctrl.topage('<s:property value="%{pageBeanDetail.getLastPageUrl()}"/>');return false;">
			末页 </a>
	</s:else>
	&nbsp; <span class="pagination">共<s:property
			value="%{pageBeanDetail.curPage}" />/<s:property
			value="%{pageBeanDetail.totalPage}" />页
	</span>
</div>
