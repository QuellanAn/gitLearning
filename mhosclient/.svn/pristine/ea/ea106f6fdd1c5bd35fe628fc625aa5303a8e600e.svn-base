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
				<th style="margin-left: 5px;margin-right: 5px;">通道名称</th>
				<th style="margin-left: 5px;margin-right: 5px;">院区</th>
				<th style="margin-left: 5px;margin-right: 5px;">支付场景</th>
				<th style="margin-left: 5px;margin-right: 5px;">支付方式</th>
				<th style="margin-left: 5px;margin-right: 5px;">资金账户</th>
				<th style="margin-left: 5px;margin-right: 5px;">状态</th>
				<th style="margin-left: 5px;margin-right: 5px;">创建者</th>
				<th style="margin-left: 5px;margin-right: 5px;">创建时间</th>
				<th style="margin-left: 5px;margin-right: 5px;">操作</th>
		</tr>
	</thead>

	<tbody id="tb_cnt_body">
		<s:iterator value="pcList" var="pcList" status="status">
		<tr>
			<td align="center">${cName}</td>
			<td align="center">${yqName}</td>
			<td align="center">${sName}</td>
			<td align="center">${ptName}</td>
			<td align="center">${accountName}</td>
			<td align="center">
				<s:if test="#pcList.cStatus==0"><font color="green">启用</font></s:if>
				<s:if test="#pcList.cStatus==1"><font color="red">禁用</font></s:if>
			</td>
			<td align="center">${creator}</td>
		   <td align="center">${createTime.substring(0,19)}</td> 
			<td align="center">
			<a class="act_edit"	href="javascript:toModify('${cID}');">编辑 </a>
			</td>		
		</tr>
		</s:iterator>
		   <s:if test="%{page.pageCount == 0}"> <tr><td align="center" colspan="9" >暂无数据</td></tr></s:if>
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
		<a href="javascript:datalist_ctrl.loaddatalist('1','${yqCode }','${sCode }','${ptCode}','${accountCode}','${cStatus}');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist('${page.pageNo-1}','${yqCode }','${sCode }','${ptCode}','${accountCode}','${cStatus}')" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:datalist_ctrl.loaddatalist('${page.pageNo+1}','${yqCode }','${sCode }','${ptCode}','${accountCode}','${cStatus}');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist('${page.pageCount}','${yqCode }','${sCode }','${ptCode}','${accountCode}','${cStatus}');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>