<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input id="hid_totalcount" type="hidden" value="${page.totalCount }"/>
	<script type="text/javascript">
		refresh_totalcount();
	</script>
<table id="tb_cnt" class="list-table">
	<thead>
		<tr>
			<th width="25%">场景编号</th>
			<th width="45%">场景名称</th>
			<th width="20%">状态</th>
			<th width="20%">操作</th>
		</tr>
	</thead>

	<tbody id="tb_cnt_body">
		<c:forEach items="${pslist}" var="ter">
			<tr>
			<td align="center">${ter.SCode }</td>
			<td align="center">${ter.SName }</td>
			<td align="center">
				<c:if test="${ter.SStatus==0 }"><span style="color: #44B549">启用</span></c:if>
				<c:if test="${ter.SStatus==1 }"><span style="color: #FF0000">禁用</span></c:if>
			</td>
			<td align="center">
			<a class="act_edit"	href="javascript:modify('${ter.SCode }');">编辑 </a>
			</td>		
		</tr>
		</c:forEach>
		   <s:if test="%{page.pageCount == 0}"> <tr><td align="center" colspan="4" >暂无数据</td></tr></s:if>
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
		<a href="javascript:datalist_ctrl.loaddatalist1('unpaid/paySceneAction_findAllInfo?pageNo=1');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('unpaid/paySceneAction_findAllInfo?pageNo=${page.pageNo-1}')" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:datalist_ctrl.loaddatalist1('unpaid/paySceneAction_findAllInfo?pageNo=${page.pageNo+1}');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('unpaid/paySceneAction_findAllInfo?pageNo=${page.pageCount}');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>