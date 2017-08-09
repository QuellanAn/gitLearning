<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<s:append var="list">
	<s:param value="%{pageBean.curPageData}" />
</s:append>
<s:hidden id="hid_totalcount" value="%{pageBean.totalRecord}" />
<s:hidden id="hid_currentPage" value="%{pageBean.curPage}" />

<%
	Page<CardPayVO> pageBean = (Page<CardPayVO>) request
	.getAttribute("pageBean");
	List<CardPayVO> lst = pageBean.getCurPageData();
	
%>
	
<input id="hid_totalcount" type="hidden"
	value="<%=pageBean.getTotalRecord()%>" />
<script type="text/javascript">
	refresh_totalcount();
</script>
<table id="tb_cnt" class="list-table" style="margin-top: 5px;">
	<thead>
		<tr>
			<th style="margin-left: 5px;margin-right: 5px;">卡号</th>
			<th style="margin-left: 5px;margin-right: 5px;">就诊人</th>
			<th style="margin-left: 5px;margin-right: 5px;">医嘱单号</th>
			<th style="margin-left: 5px;margin-right: 5px;">缴费单号</th>
			<th style="margin-left: 5px;margin-right: 5px;">缴费时间</th>
			<th style="margin-left: 5px;margin-right: 5px;">支付方式</th>
			<th style="margin-left: 5px;margin-right: 5px;">状态</th>
			<th style="margin-left: 5px;margin-right: 5px;">金额</th>
			<th style="margin-left: 5px;margin-right: 5px;">账户余额</th>
		</tr>
	</thead>
	<tbody id="tb_cnt_body">
		<s:iterator value="list" var="list" status="status">
		<tr>
			<td align="center">${cardNumber} </td>
			<td align="center"><span
				onclick="showInfo(value= '${cpId} ');"
				style="color: #0079ff; text-decoration: underline;cursor:pointer;">${patientName}</span></td>
			<td align="center">
				<a href="busrecord/appreg/payMentDetail_main.jsp?cpId=${cpId}">${cpId}</a>
				<%-- <span onclick="showdetail(value= '${cpId} ');"
				style="color: #0079ff; text-decoration: underline;cursor:pointer;">${cpId}</span> --%>
			</td>
			<td align="center">${receiptNo} </td>
			<td align="center">${fn:substring(createDate, 0, 16) }</td>
			<td align="center">${payWay} </td>
			<td align="center">
				<s:if test="#list.payStatus == 0">缴费成功</s:if> 
				<s:if test="#list.payStatus == 1">缴费失败</s:if> 
			</td>
			<td align="center">${payFee} </td>
			<td align="center">${remainMoney} </td>
			
		</tr>
		</s:iterator>
	</tbody>
</table>

<div class="pagination-wrap f-fr">
	<s:if test="%{pageBean.isFirstPage()}">
		<a class="pagination">首页</a>
	</s:if>
	<s:else>
		<a href="#" class="pagination"
			onclick="javascript:datalist_ctrl.topage('<s:property value="%{pageBean.getFirstPageUrl()}"/>');return false;">
			首页 </a>
	</s:else>
	<s:if test="%{pageBean.isFirstPage()}">
		<a class="pagination">上页</a>
	</s:if>
	<s:else>
		<a href="#" class="pagination"
			onclick="javascript:datalist_ctrl.topage('<s:property value="%{pageBean.getPrevPageUrl()}"/>');return false;">
			上页 </a>
	</s:else>
	<s:if test="%{pageBean.isLastPage()}">
		<a class="pagination">下页</a>
	</s:if>
	<s:else>
		<a href="#" class="pagination"
			onclick="javascript:datalist_ctrl.topage('<s:property value="%{pageBean.getNextPageUrl()}"/>'); return false;">
			下页 </a>
	</s:else>
	<s:if test="%{pageBean.isLastPage()}">
		<a class="pagination">末页</a>
	</s:if>
	<s:else>
		<a href="#" class="pagination"
			onclick="javascript:datalist_ctrl.topage('<s:property value="%{pageBean.getLastPageUrl()}"/>');return false;">
			末页 </a>
	</s:else>
	&nbsp; <span class="pagination">共<s:property
			value="%{pageBean.curPage}" />/<s:property
			value="%{pageBean.totalPage}" />页
	</span>
</div>
