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
			<th style="margin-left: 5px;margin-right: 5px;">
				<input type="checkbox" name="checkbox" id="check" onclick="dosubmit('patientId', 'check');"/>
			</th>
			<th style="margin-left: 5px;margin-right: 5px;">就诊卡号</th>
			<th style="margin-left: 5px;margin-right: 5px;">姓名</th>
			<th style="margin-left: 5px;margin-right: 5px;">性别</th>
			<th style="margin-left: 5px;margin-right: 5px;">年龄</th>
			<th style="margin-left: 5px;margin-right: 5px;">手机号</th>
			<th style="margin-left: 5px;margin-right: 5px;">身份证号</th>
			<th style="margin-left: 5px;margin-right: 5px;">状态</th>
			<th style="margin-left: 5px;margin-right: 5px;">操作</th>
		</tr>
	</thead>
	<tbody id="tb_cnt_body">
		<s:iterator value="list" var="list" status="status">
		<tr>
			<td align="center">
				<input id="cb" type="checkbox" name="patientId" value="${patientId }" class="box"/>
			</td>
			<td align="center">${cardNo} </td>
			<td align="center">${patientName } </td>
			<td align="center">
				<s:if test="#list.sexCode == 0">男</s:if> 
				<s:if test="#list.sexCode == 1">女</s:if> 
			</td>
			<td align="center">${birthday}</td>
			<td align="center">${phone}  </td>
			<td align="center">${identityCard}</td>
	 		<s:if test="#list.status == 0"><td align="center">正常</td></s:if> 
			<s:if test="#list.status == 1"><td align="center" style="color: red;">冻结</td></s:if> 
			<td align="center">
				<span onclick="showPatientUser(value= '${patientId} ');"
				style="color: #0079ff; text-decoration: underline;cursor:pointer;">查看</span>
			</td>
			<%-- <td align="center">${feeType} </td> --%>
		</tr>
		</s:iterator>
		<s:if test="%{pageBean.totalRecord == 0}"> <tr><td align="center" colspan="9" >暂无数据</td></tr></s:if>
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
