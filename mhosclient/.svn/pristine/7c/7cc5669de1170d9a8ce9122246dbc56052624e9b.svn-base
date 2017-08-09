<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:append var="list">
	<s:param value="%{pageBean.curPageData}" />
</s:append>
<s:hidden id="hid_totalcount" value="%{pageBean.totalRecord}" />
<s:hidden id="hid_currentPage" value="%{pageBean.curPage}" />

<%
	Page<PayCheckVO> pageBean = (Page<PayCheckVO>) request
	.getAttribute("pageBean");
	List<PayCheckVO> lst = pageBean.getCurPageData();
%>
<input id="hid_totalcount" type="hidden"
	value="<%=pageBean.getTotalRecord()%>" />
<script type="text/javascript">
	refresh_totalcount();
</script>
<table id="tb_cnt" class="list-table" style="margin-top: 5px;">
	<thead>
		<tr>
			<th style="margin-left: 5px;margin-right: 5px;">交易时间</th>
			<th style="margin-left: 5px;margin-right: 5px;">预约号</th>
			<th style="margin-left: 5px;margin-right: 5px;">就诊人</th>
			<th style="margin-left: 5px;margin-right: 5px;">缴费号</th>
			<th style="margin-left: 5px;margin-right: 5px;">微信订单号</th>
			<th style="margin-left: 5px;margin-right: 5px;">交易类型</th>
			<th style="margin-left: 5px;margin-right: 5px;">金额</th>
			<th style="margin-left: 5px;margin-right: 5px;">交易状态</th>
		</tr>
	</thead>
	<tbody id="tb_cnt_body">
		<%
			for (PayCheckVO v : lst) {
		%>
		<tr>
			<td align="center"><%=v.getCreate()%></td>
			<td align="center"><span
				onclick="showRegInfo(value='<%=v.getOrderid()%>');"
				style="color: #0079ff; text-decoration: underline;cursor:pointer;"><%=v.getOrderid()%></span></td>
			<td align="center"><span
				onclick="showPatients(value= '<%=v.getPatientid()%>');"
				style="color: #0079ff; text-decoration: underline;cursor:pointer;"><%=v.getPatientname()%></span></td>
			
			<td align="center"><%=v.getPayid()%></td>
			<td align="center"><%=v.getWechatid()%></td>
		 	<td align="center">
				<%
					if ( v.getTradetype().equals("1")) {
				%>挂号费 <%
					} else if(v.getTradetype().equals("2")){
				%>就诊费<%
					}else if(v.getTradetype().equals("3")){
				%>药品费<%
					}
				%>
				</td>
		<td align="center"><%=v.getTotalmoney()%></td>
				<td align="center">
				<%
					if (v.getStatus().equals("1")) {
				%> 未支付<%
					} else if(v.getStatus().equals("2")){
				%> 已支付<%
					}else if(v.getStatus().equals("3")){
				%> 已退款<%
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
