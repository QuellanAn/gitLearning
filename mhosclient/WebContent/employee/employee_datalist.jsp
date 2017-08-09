<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:append var="lst">
	<s:param value="%{pageBean.curPageData}"></s:param>
</s:append>

<%-- <input id="hid_totalcount" type="hidden" value="${page.totalCount }"/> --%>
<s:hidden name="infocatcode"></s:hidden>
<input id="hid_totalcount" type="hidden" value="<s:property value="%{pageBean.totalRecord}"/>" /> 
<script type="text/javascript">
		refresh_unpubcount();
	</script>
<table class="list-table">
	<tbody>
		<tr>
			
			<th width="10%">序号</th>
			<th>ID</th>
			<th>姓名</th>
			<!-- <th>资讯细类</th> -->
			<th>性别</th>
			<th>部门</th>
			<th>工资</th>
			<th>操作</th>
		</tr>
		<s:iterator var="employeePO" value="%{#lst}" status="status">
			<tr>
				<td><s:property value="%{#status.index+1}"/></td>
				<%-- <td><s:property value="#hosPubInfoVO.getInfoTypeDesc()" /></td> --%>
				<!-- <td><s:property value="#hosPubInfoVO.infoCatName" /></td> -->
				<td><s:property value="#employeePO.employeeId" /></td>
				<td><s:property value="#employeePO.employeeName" /></td>	
				<td><s:property value="#employeePO.sex" /></td>	
				<td><s:property value="#employeePO.department" /></td>
				<td><s:property value="#employeePO.salary" /></td>										
				
				<td align="center">
					<a class="act_edit"	href="employeesAction/updateNewEmployee?selectOrderNumber=<s:property value="#employeePO.employeeId"/>">编辑 </a>
					&nbsp;&nbsp;
					<a class="act_delete" href="javascript:deletehosinfo('<s:property value="#employeePO.employeeId"/>', <s:property value="%{pageBean.curPage}"/>);">删除</a>
				</td>	
		</s:iterator>
		<s:if test="%{pageBean.totalRecord == 0}"> <tr><td align="center" colspan="7" >暂无数据</td></tr></s:if>
	</tbody>
</table>

<div style="float: left;" align="left" class="pagination-wrap f-fr">
	&nbsp;&nbsp;共有 <font style="font-weight: bolder;"><s:property value="%{pageBean.totalRecord}"/></font> 条记录，&nbsp;共 <s:property value="%{pageBean.curPage}" />/<s:property value="%{pageBean.totalPage}" /> 页
</div>
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
	&nbsp; <span class="pagination">共<s:property value="%{pageBean.curPage}" />/<s:property value="%{pageBean.totalPage}" />页
	</span>
</div>

<%-- 
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
		<a href="javascript:loadPage('1');" class="pagination">首页 </a>
		<a href="javascript:loadPage('${page.pageNo-1}')" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:loadPage('${page.pageNo+1}');" class="pagination">下页 </a>
		<a href="javascript:loadPage('${page.pageCount}');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>


 --%>