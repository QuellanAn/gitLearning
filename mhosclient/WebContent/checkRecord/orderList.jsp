<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<input id="hid_totalcount" type="hidden" value="${page.totalCount }"/>
	<script type="text/javascript">
		refresh_totalcount();
	</script>
<table id="tb_cnt" class="list-table">
	<thead>
		<tr>
			<th width="15%">处理时间</th>
			<th width="20%">交易单号</th>
			<th width="10%">资金账户</th>
			<th width="10%">操作内容</th>
			<th width="10%">操作状态</th>
		<!-- 	<th width="10%">处理状态</th> -->
			<th width="10%">操作者</th>
		</tr>
	</thead>

	<tbody id="tb_cnt_body">
		<s:iterator value="crlist" var="cr" status="status">
		<tr>
		    <td align="center">${executionTime.substring(0,19) }</td>
			<s:if test="#cr.tradeType==0"><td align="center"><a href="javascript:findDetails('${transactionNum }')">${transactionNum }</a></td></s:if>
			<s:if test="#cr.tradeType==1"><td align="center"><a href="javascript:findRefundDetails('${transactionNum }')">${transactionNum }</a></td></s:if>
			<td align="center">${accName }</td>
			<%-- <td align="center">${remark }</td> --%>
			<td align="center">${taskState }</td>
			<td align="center">${managerStatus }</td>
			<td align="center">${operator }</td>
		</tr>
		</s:iterator>
		<s:if test="%{page.pageCount == 0}">
			<tr><td colspan="7" align="center">暂无数据</td></tr>
		</s:if>
	</tbody>
</table>
<s:if test="%{page.pageCount != 0}">
<div style="float: left;" align="left" class="pagination-wrap f-fr">
	&nbsp;&nbsp;共有 <font style="font-weight: bolder;">${page.totalCount}</font> 条记录，&nbsp;共 ${page.pageNo}/${page.pageCount} 页
</div>
<div class=" pagination-wrap f-fr">
	<s:if test="%{page.pageNo == 1}">
		<a class="pagination">首页</a>
		<a class="pagination">上页</a>
	</s:if>
	<s:if test="%{page.pageNo != 1}">
		<a href="javascript:datalist_ctrl.loaddatalist1('1','${recordType}','${transactionNum}','${orderCode}','${hisTransId}','${startdate}','${enddate}','${account }');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageNo-1}','${recordType}','${transactionNum}','${orderCode}','${hisTransId}','${startdate}','${enddate}','${account }')" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageNo+1}','${recordType}','${transactionNum}','${orderCode}','${hisTransId}','${startdate}','${enddate}','${account }');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageCount}','${recordType}','${transactionNum}','${orderCode}','${hisTransId}','${startdate}','${enddate}','${account }');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>