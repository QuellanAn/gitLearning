<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.catic.mobilehos.service.vo.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<s:append var="list">
	<s:param value="%{pageBean.curPageData}" />
</s:append>
<s:hidden id="hid_totalcount" value="%{pageBean.totalRecord}" />
<s:hidden id="hid_currentPage" value="%{pageBean.curPage}" />
<%
	Page<ServiceEvaluationHosVO> pageBean = (Page<ServiceEvaluationHosVO>) request
			.getAttribute("pageBean");
	List<ServiceEvaluationHosVO> lst = pageBean.getCurPageData();
%>
<input id="hid_totalcount" type="hidden" value="<%=pageBean.getTotalRecord() %>"/>
<script type="text/javascript">
		refresh_totalcount();
</script>
	<table id="tb_cnt" class="list-table">
		<thead>
			<tr>
				<th style="margin-left: 5px;margin-right: 5px;">就诊卡号</th>
				<th style="margin-left: 5px;margin-right: 5px;">就诊人</th>
				<th style="margin-left: 5px;margin-right: 5px;">就诊科室</th>
				<th style="margin-left: 5px;margin-right: 5px;">就诊医生</th>
				<th style="margin-left: 5px;margin-right: 5px;">评价途径</th>
				<th style="margin-left: 5px;margin-right: 5px;">总体评价</th>
				<th style="margin-left: 5px;margin-right: 5px;">评价时间</th>
				<th style="margin-left: 5px;margin-right: 5px;">操作</th>
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
		<s:iterator value="list" var="list" status="status">
			<tr>
				<td align="center">${cardNumber }</td>
				<td align="center">${patientName }</td>
				<td align="center">${departmentName }</td>
				<td align="center">${doctorName }</td>
				<td align="center">
					${sourceName }
					<%-- <s:if test="%{regSource == 1}">微信</s:if>
					<s:if test="%{regSource == 2}">支付宝</s:if>
					<s:if test="%{regSource == 3}">自助机</s:if> --%>
				</td>
					<s:if test="%{overallEvaluation == 0}"><td align="center">好评</td></s:if>
					<s:if test="%{overallEvaluation == 1}"><td align="center">中评</td></s:if>
					<s:if test="%{overallEvaluation == 2}"><td align="center" style="color: red;">差评</td></s:if>
				<%-- ${overallEvaluation } --%>
				
				<td align="center">${fn:substring(createDate, 0, 16) }</td>
				<td align="center">
					<span onclick="findDetail('${serviceEvaluationId}');"
						style="color: #0079ff; text-decoration: underline;cursor:pointer;">查看</span>
				</td>
			</tr>
		</s:iterator>
		<s:if test="%{pageBean.totalRecord == 0}"> <tr><td align="center" colspan="11" >暂无数据</td></tr></s:if>
		</tbody>
	</table>

<div class=" pagination-wrap f-fr">
					<%
		if (pageBean.isFirstPage()) {
	%>
	<a class="pagination">首页</a>
	<%
		} else {
	%>
	<a href="#" class="pagination"
		onclick="javascript:datalist_ctrl.topage('<%=pageBean.getFirstPageUrl()%>');return false;">
		首页 </a>
	<%
		}
	%>
	<%
		if (pageBean.isFirstPage()) {
	%>
	<a class="pagination">上页</a>
	<%
		} else {
	%>
	<a href="#" class="pagination"
		onclick="javascript:datalist_ctrl.topage('<%=pageBean.getPrevPageUrl()%>');return false;">
		上页 </a>
	<%
		}
	%>
	<%
		if (pageBean.isLastPage()) {
	%>
	<a class="pagination">下页</a>
	<%
		} else {
	%>
	<a href="#" class="pagination"
		onclick="javascript:datalist_ctrl.topage('<%=pageBean.getNextPageUrl()%>'); return false;">
		下页 </a>
	<%
		}
	%>
	<%
		if (pageBean.isLastPage()) {
	%>
	<a class="pagination">末页</a>
	<%
		} else {
	%>
	<a href="#" class="pagination"
		onclick="javascript:datalist_ctrl.topage('<%=pageBean.getLastPageUrl()%>');return false;">
		末页 </a>
	<%
		}
	%>
	&nbsp; <span class="pagination">共<%=pageBean.getCurPage()%>/<%=pageBean.getTotalPage()%>页
	</span>
</div>