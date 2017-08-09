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
			<th align="center" width="60">帐号</th>
			<th align="center" width="80">姓名</th>
			<th align="center" width="45">性别</th>
			<th align="center" width="85">联系方式</th>
			<th align="center" width="93">注册时间</th>
			<th align="center" width="127">角色</th>
			<th align="center" width="70">帐号状态</th>
			<th align="center" width="112">备注</th>
			<th align="center" width="56"></th>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="sysUsersVO" value="%{#list}">
			<tr>
				<td align="center"><s:property value="#sysUsersVO.userName" /></td>
				<td align="center"><s:property value="#sysUsersVO.realName" />
				<td align="center"><s:property value="#sysUsersVO.sex" /></td>
				<td align="center"><s:property value="#sysUsersVO.tel" /></td>
				<td align="center"><s:date name="#sysUsersVO.createTime"
						format="yyyy-MM-dd HH:mm:ss" /></td>
				<td align="left"><s:iterator var="rolesPO"
						value="#sysUsersVO.rolesPOs" status="sta">
						<s:property value="roleName" />
						<s:if test="!#sta.last">、</s:if>
					</s:iterator></td>
				<td align="center"><s:if test="#sysUsersVO.status==0">有效</s:if>
					<s:else><font color="red">无效</font></s:else></td>
				<td align="left"
					<s:if
						test="null != #sysUsersVO.remark && #sysUsersVO.remark.length()>6">title="<s:property value="#sysUsersVO.remark" />" </s:if>>
					<s:if test="null != #sysUsersVO.remark && #sysUsersVO.remark.length()>6">
						<s:property value="#sysUsersVO.remark.substring(0,6)" />...</s:if> <s:else>
						<s:property value="#sysUsersVO.remark" />
					</s:else>
				</td>
				<td align="center"><s:form action="updateSysUser"
						style="margin-left:5px;" theme="simple">
						<s:hidden name="userId" value="%{#sysUsersVO.userId}" />
						<span class="editCliVersion icon edit-icon f-fl" title="编辑"
							onclick="datalist_ctrl.updateSysUser(this);">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					</s:form> 
					<s:if test="#sysUsersVO.userId !=11 ">
					<span class="delCliVersion icon del-icon f-fl" title="删除  "
					onclick="datalist_ctrl.deleteSysUser(this,'<s:property value="#sysUsersVO.userId" />');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
					</s:if>
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