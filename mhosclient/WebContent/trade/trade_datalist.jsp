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
	Page<TradeVO> pageBean = (Page<TradeVO>) request
	.getAttribute("pageBean");
	List<TradeVO> lst = pageBean.getCurPageData();
	
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
			<th style="margin-left: 5px;margin-right: 5px;">就诊人</th>
			<!-- <th style="margin-left: 5px;margin-right: 5px;">商户订单号</th> -->
			<!-- <th style="margin-left: 5px;margin-right: 5px;">交易流水号</th> -->
			<!-- <th style="margin-left: 5px;margin-right: 5px;">商品名称</th> -->
			<!-- <th style="margin-left: 5px;margin-right: 5px;">交易类型</th> -->
			<th style="margin-left: 5px;margin-right: 5px;">充值金额(元)</th>
			<th style="margin-left: 5px;margin-right: 5px;">充值状态</th>
			<th style="margin-left: 5px;margin-right: 5px;">充值时间</th>
			<!-- <th style="margin-left: 5px;margin-right: 5px;">货币类型</th> -->
		</tr>
	</thead>
	<tbody id="tb_cnt_body">
		<s:iterator value="list" var="list" status="status">
		<tr>
			<td align="center">${cardNo} </td>
			<%-- <td align="center">${patientName } </td> --%>
			<td align="center">${fn:substring( fn:substring(attach, fn:indexOf(attach,",")+1 , fn:length(attach)) , 0 , fn:indexOf(  fn:substring(attach, fn:indexOf(attach,",")+1 , fn:length(attach))  ,",")  ) } </td>
			<%-- <td align="center">${tradeNo} </td> --%>
			<%-- <td align="center"><span
				onclick="showTradeInfo(value= '${tradeNo} ');"
				style="color: #0079ff; text-decoration: underline;cursor:pointer;">${tradeNo}</span>
			</td> --%>
			<%-- <td align="center">${transactionId}</td> --%>
			<%-- <td align="center">${goodsName}  </td> --%>
			<%-- <td align="center">${tradeType}</td> --%>
			<td align="center">${totalFee}  </td>
			<td align="center">
				<s:if test="#list.status == 0">未充值</s:if> 
				<s:if test="#list.status == 1">充值成功</s:if> 
				<s:if test="#list.status == 2">充值失败,退款成功</s:if> 
				<s:if test="#list.status == 3">充值失败,退款失败</s:if> 
				<s:if test="#list.status == 4">充值异常</s:if> 
			</td>
			<td align="center">${fn:substring(endDate, 0, 16) } </td>
			<%-- <td align="center">${feeType} </td> --%>
		</tr>
		</s:iterator>
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
