<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.catic.mobilehos.service.vo.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
	Page<PayVO> pageBean = (Page<PayVO>) request
			.getAttribute("pageBean");
	List<PayVO> lst = pageBean.getCurPageData();
%>



	<input id="hid_totalcount" type="hidden" value="<%=pageBean.getTotalRecord() %>"/>
	<script type="text/javascript">
		refresh_totalcount();
	</script>
	<table id="tb_cnt" class="list-table" style="margin-top: 5px;">
		<thead>
			<tr>
				<th style="margin-left: 5px;margin-right: 5px;">缴费单号</th>
				<th style="margin-left: 5px;margin-right: 5px;">就诊人</th>
				<th style="margin-left: 5px;margin-right: 5px;">应缴费</th>
				<th style="margin-left: 5px;margin-right: 5px;">实缴费</th>
				<th style="margin-left: 5px;margin-right: 5px;">缴费方式</th>
				<th style="margin-left: 5px;margin-right: 5px;">缴费流水号</th>
				<th style="margin-left: 5px;margin-right: 5px;">缴费时间</th>
				<th style="margin-left: 5px;margin-right: 5px;">缴费结果</th>
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
			<% for (PayVO v : lst) { %>
			<tr>
				<td align="center"><span
				onclick="showPayInfo(value= '<%=v.getDocBillSn()%>');"
				style="color: #0079ff; text-decoration: underline;cursor:pointer;"><%=v.getDocBillSn() %></td>
				<td align="center">
				<span onclick="showPatients('<%=v.getPatientId()%>','<%=v.getDocBillSn()%> ');"
					style="color: #0079ff; text-decoration: underline;cursor:pointer;"><%=v.getPatientName()%></span>
				</td>
				<td align="center"><%=v.getAmount()%></td>
				<td align="center"><%=v.getPaidFees()%></td>
				<td align="center"><%=v.getPayMode()%></td>
				<td align="center"><%=v.getPayOrderid() %></td>
				<%-- <td align="center"><%=v.getCurDocSn()%></td> --%>
				<td align="center"><%=v.getPayTime().toString().substring(0, 16) %></td>
				<td align="center">未缴费</td>
			</tr>
			<%
				}
			%>
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