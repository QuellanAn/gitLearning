<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:append var="list">
	<s:param value="%{pageBean.curPageData}" />
</s:append>
<s:hidden id="hid_totalcount" value="%{pageBean.totalRecord}" />

<script type="text/javascript">
	refresh_unpubcount();
</script>

<table class="list-table">
	<thead>
		<tr>
			<th align="center" width="25%">角色名</th>
			<th align="center" width="27%">创建时间</th>
			<th align="center" width="36%">备注</th>
			<th align="center" width="12%"></th>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="rolesVO" value="%{#list}">
			<tr>
				<td align="center"><s:property value="#rolesVO.roleName" /></td>
				<td align="center"><s:date name="#rolesVO.createTime"
						format="yyyy-MM-dd HH:mm:ss" /></td>
				<td align="center"><s:property value="#rolesVO.remark" /></td>
				<td align="center"><s:form action="updateRole" theme="simple" cssStyle="margin-left:8px;">
						<s:hidden name="roleId" value="%{#rolesVO.roleId}" />
						<span class="editCliVersion icon edit-icon f-fl" title="编辑" onclick="datalist_ctrl.updateRole(this);" style="margin-left: 10px;margin-right: 10px;"></span>
					</s:form>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="delCliVersion icon del-icon f-fl" title="删除  "
					onclick="datalist_ctrl.deleteRole(this,'<s:property value="#rolesVO.roleId" />');" style="margin-left: 10px;margin-right: 2px;"></span></td>
			</tr>
		</s:iterator>
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