<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<input id="count" type="hidden" value="${page.totalCount}"/>
	<table id="tb_cnt" class="list-table" style="margin-top: 5px;table-layout:fixed;width: 1030px;margin-left: 30px;">
		<thead>
			<tr>
				<th align="center" width="25%">订单日期</th>
				<th align="center" width="25%">退款总金额</th>
				<th align="center" width="25%">退款总笔数</th>
				<th align="center" width="25%">人均退款金额</th>
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
			 <s:iterator value="polist" var="polist" status="status">
             <tr>
             <td align="center"  style="word-wrap:break-word;">${stime}</td>
             <td align="center" style="word-wrap:break-word;">${totalMoney}</td>   
             <td align="center" style="word-wrap:break-word;">${totalCount}</td>
             <td align="center" style="word-wrap:break-word;">${avgMoney}</td>   
             </tr>
           </s:iterator>
           <s:if test="%{page.pageCount == 0}">
			<tr><td colspan="4" align="center">暂无数据</td></tr>
			</s:if>
		</tbody>
	</table>
	<s:if test="%{page.pageCount != 0}">
<div style="float: left;margin-left: 30px;" align="left" class="pagination-wrap f-fr">
	&nbsp;&nbsp;共有 <font style="font-weight: bolder;">${page.totalCount}</font> 条记录，&nbsp;共 ${page.pageNo}/${page.pageCount} 页
</div>
<div class=" pagination-wrap f-fr" style="margin-right: 30px;">
	<s:if test="%{page.pageNo == 1}">
		<a class="pagination">首页</a>
		<a class="pagination">上页</a>
	</s:if>
	<s:if test="%{page.pageNo != 1}">
		<a href="javascript:datalist_ctrl.loaddatalist1('1');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageNo-1}');" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageNo+1}');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageCount}');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>
</div>