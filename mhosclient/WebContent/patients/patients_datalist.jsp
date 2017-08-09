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
	Page<PatientsVO> pageBean = (Page<PatientsVO>) request
	.getAttribute("pageBean");
	List<PatientsVO> lst = pageBean.getCurPageData();
	
%>
	
<input id="hid_totalcount" type="hidden"
	value="<%=pageBean.getTotalRecord()%>" />
<script type="text/javascript">
	refresh_totalcount();
</script>
<table id="tb_cnt" class="list-table" style="margin-top: 5px;">
	<thead>
		<tr>
			<th style="margin-left: 5px;margin-right: 5px;">就诊卡号</th>
			<th style="margin-left: 5px;margin-right: 5px;">姓名</th>
			<th style="margin-left: 5px;margin-right: 5px;">性别</th>
			<th style="margin-left: 5px;margin-right: 5px;">年龄</th>
			<th style="margin-left: 5px;margin-right: 5px;">手机号</th>
			<th style="margin-left: 5px;margin-right: 5px;">身份证号</th>
			<th style="margin-left: 5px;margin-right: 5px;">建档途径</th>
			<th style="margin-left: 5px;margin-right: 5px;">建档发卡时间</th>
			<th style="margin-left: 5px;margin-right: 5px;">操作</th>
		</tr>
	</thead>
	<tbody id="tb_cnt_body">
		<s:iterator value="list" var="list" status="status">
		<tr>
			<td align="center">${cardNo} </td>
			<td align="center">${patientName } </td>
			<td align="center">
				<s:if test="#list.sexCode == 0">男</s:if> 
				<s:if test="#list.sexCode == 1">女</s:if> 
			</td>
			<td align="center">${birthday}</td>
			<td align="center">${phone}  </td>
			<td align="center">${identityCard}</td>
			<td align="center">
				${sourceName}
				<%-- <s:if test="#list.patientSource == 1">微信</s:if> 
				<s:if test="#list.patientSource == 2">支付宝</s:if> 
				<s:if test="#list.patientSource == 3">自助机</s:if>  --%>
		  	</td>
		  	<td align="center">${fn:substring(createDate, 0, 16) } </td>
			<td align="center">
				<span onclick="showPatients(value= '${patientId} ');"
				style="color: #0079ff; text-decoration: underline;cursor:pointer;">查看</span>
			</td>
			<%-- <td align="center">${feeType} </td> --%>
		</tr>
		</s:iterator>
		<s:if test="%{pageBean.totalRecord == 0}"> <tr><td align="center" colspan="11" >暂无数据</td></tr></s:if>
	</tbody>
</table>

<div class="pagination-wrap f-fr" >
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
