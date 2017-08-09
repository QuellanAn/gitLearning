<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:append var="list">
	<s:param value="%{pageBean.curPageData}" />
</s:append>
<s:hidden id="hid_totalcount" value="%{pageBean.totalRecord}" />
<s:hidden id="hid_currentPage" value="%{pageBean.curPage}" />
<%
	Page<OrdersPayVO> pageBean = (Page<OrdersPayVO>) request
	.getAttribute("pageBean");
	List<OrdersPayVO> lst = pageBean.getCurPageData();
%>



<input id="hid_totalcount" type="hidden"
	value="<%=pageBean.getTotalRecord()%>" />
<script type="text/javascript">
	refresh_totalcount();
</script>
<table id="tb_cnt" class="list-table" style="margin-top: 5px;">
	<thead>
		<tr>
			<th style="margin-left: 5px;margin-right: 5px;">预约号</th>
			<th style="margin-left: 5px;margin-right: 5px;">就诊人</th>
			<th style="margin-left: 5px;margin-right: 5px;">应缴费</th>
			<th style="margin-left: 5px;margin-right: 5px;">实缴费</th>
			<th style="margin-left: 5px;margin-right: 5px;">缴费方式</th>
			<th style="margin-left: 5px;margin-right: 5px;">缴费时间</th>
			<th style="margin-left: 5px;margin-right: 5px;">缴费结果</th>
		</tr>
	</thead>
	<tbody id="tb_cnt_body">
		<%
			for (OrdersPayVO v : lst) {
		%>
		<tr>
			<td align="center"><span
				onclick="showInfo(value='<%=v.getAppROrderNumber()%>');"
				style="color: #0079ff; text-decoration: underline;cursor:pointer;"><%=v.getAppROrderNumber()%></td>
			<td align="center"><span
				onclick="showPatients(value= '<%=v.getPatientId()%>');"
				style="color: #0079ff; text-decoration: underline;cursor:pointer;"><%=v.getPatientName()%></td>
			<td align="center"><%=v.getPayableFees()%></td>
			<td align="center"><%=v.getPaidFees()%></td>
			<td align="center"></td>
			<td align="center"></td>

			<td align="center">
				<%
					if (0 == v.getPaid()) {
				%> 未交费 <%
					} else {
				%> 已交费 <%
					}
				%>
			</td>
		</tr>
		<%
			}
		%>
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
