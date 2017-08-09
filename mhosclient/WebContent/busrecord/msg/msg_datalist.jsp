<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.catic.mobilehos.service.vo.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
	Page<MessageVO> pageBean = (Page<MessageVO>) request
			.getAttribute("pageBean");
	List<MessageVO> lst = pageBean.getCurPageData();
	
%>

	<s:hidden name="infocatcode"></s:hidden>
	<input id="hid_totalcount" type="hidden" value="<%=pageBean.getTotalRecord() %>"/>
	<script type="text/javascript">
		refresh_totalcount();
	</script>
	<table id="tb_cnt" class="list-table">
		<thead>
			<tr>
				<th width="15%">发送时间</th>
				<th width="12%">消息类型</th>
				<th width="15%">手机号</th>
				<th width="38%">消息内容</th>
				<th width="10%">发送方式</th>
				<th width="10%">发送状态</th>
			</tr>
		</thead>


		<tbody id="tb_cnt_body">

			<% for (MessageVO v : lst) {%>
			
			<tr>
				<td align="center"><%=v.getExpectTimeDesc()%></td>
				<td align="center"><%=v.getBusName()%></td>
				<td align="center"><%=v.getPhone()%></td>
				<td style="text-align: left;"><%=v.getMsgContentDesc()%></td>
				<td align="center"><%=v.getSendWayDesc()%></td>
				<td align="center"><%=v.getStatusDesc()%>
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
	&nbsp; <span class="pagination">共<%if(pageBean.getTotalPage()!=0){
	out.print(pageBean.getCurPage()+"/");}%><%=pageBean.getTotalPage()%>页
	</span>
</div>




