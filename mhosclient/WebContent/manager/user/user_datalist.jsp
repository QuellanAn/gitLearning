<%@page import="com.catic.mobilehos.po.UserPO"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:append var="list">
	<s:param value="%{pageBean.curPageData}" />
</s:append>
<s:hidden id="hid_totalcount" value="%{pageBean.totalRecord}" />
<s:hidden id="hid_currentPage" value="%{pageBean.curPage}" />
<script type="text/javascript">
	refresh_unpubcount();
</script>
<table class="list-table">
	<thead>
		<tr>
			<th style="margin-left: 5px;margin-right: 5px;">
				<input type="checkbox" name="checkbox" id="check" onclick="dosubmit('userName', 'check');"/>
			</th>
			<th align="center">用户账号</th>
			<th align="center">昵称</th>
			<th align="center">性别</th>
			<th align="center">地区</th>
			<th align="center">账号状态</th>
			<th align="center">注册时间</th>
			<th align="center" width="70px;">操作</th>
		</tr>
	</thead>
	<tbody>
		<s:set name="nowTime" value="new java.util.Date()"></s:set>
		<s:iterator var="user" value="%{#list}">
			<s:set name="blackTime" value="#user.blackTime"></s:set>
			<tr>
				<td align="center">
					<input id="cb" type="checkbox" name="userName" value="${userName }" class="box"/>
				</td>
				<td align="center"><s:property value="#user.userName" /></td>
				<%-- <td align="center"><span onclick="showPatients('<s:property value="#user.userId" />','<s:property value="#user.username" />');"
					style="color: #0079ff; text-decoration: underline;cursor:pointer;">查看就诊人</span></td> --%>
				<td align="center">${nickName }</td>
				<td align="center"><s:if test="#user.sexCode == 1">男</s:if><s:if test="#user.sexCode == 2">女</s:if> </td>
				<td align="center">${province }${city }</td>	
				<td align="center">
					<s:if test="#user.status == 0">
						<%-- <s:if test='#blackTime != null && #blackTime.getTime() >= #nowTime.getTime()'>
							<font style="color: red;">拉黑至: <s:date name="#user.blackTime" format="yyyy-MM-dd" /></font>
						</s:if> 
						<s:else>正常</s:else>--%>
						<font>正常</font>
					</s:if> 
					<s:else>
						<font color="red">冻结</font>
					</s:else>
				</td>
				<td align="center"><s:date name="#user.createDate" format="yyyy-MM-dd HH:mm:ss" />
				<td align="center">
					<span onclick="showPatients('${userId }','${userName}');"
						style="color: #0079ff; text-decoration: underline;cursor:pointer;">查看</span>
					<%-- <s:if test='#user.status == 0 && #blackTime != null && #blackTime.getTime() >= #nowTime.getTime()'>
						<span onclick="updateStatus('<s:property value="#user.userId" />','<s:property value="#user.status" />','<s:date name="#user.blackTime" format="yyyy-MM-dd" />');"
							style="color: #0079ff; text-decoration: underline;cursor:pointer;">操作</span>
					</s:if>
					<s:else>
						<span onclick="updateStatus('<s:property value="#user.userId" />','<s:property value="#user.status" />','');"
							style="color: #0079ff; text-decoration: underline;cursor:pointer;">操作</span>
					</s:else> --%>
				</td>
			</tr>
		</s:iterator>
		<s:if test="%{pageBean.totalRecord == 0}"> <tr><td align="center" colspan="8" >暂无数据</td></tr></s:if>
	</tbody>
</table>
<div class="pagination-wrap f-fr">
	<s:if test="%{pageBean.isFirstPage()}">
		<a class="pagination">首页</a>
	</s:if>
	<s:else>
		<a href="#" class="pagination" onclick="javascript:datalist_ctrl.topage('<s:property value="%{pageBean.getFirstPageUrl()}"/>');return false;"> 首页 </a>
	</s:else>
	<s:if test="%{pageBean.isFirstPage()}">
		<a class="pagination">上页</a>
	</s:if>
	<s:else>
		<a href="#" class="pagination" onclick="javascript:datalist_ctrl.topage('<s:property value="%{pageBean.getPrevPageUrl()}"/>');return false;"> 上页 </a>
	</s:else>
	<s:if test="%{pageBean.isLastPage()}">
		<a class="pagination">下页</a>
	</s:if>
	<s:else>
		<a href="#" class="pagination" onclick="javascript:datalist_ctrl.topage('<s:property value="%{pageBean.getNextPageUrl()}"/>'); return false;"> 下页 </a>
	</s:else>
	<s:if test="%{pageBean.isLastPage()}">
		<a class="pagination">末页</a>
	</s:if>
	<s:else>
		<a href="#" class="pagination" onclick="javascript:datalist_ctrl.topage('<s:property value="%{pageBean.getLastPageUrl()}"/>');return false;"> 末页 </a>
	</s:else>
	&nbsp; <span class="pagination">共<s:property value="%{pageBean.curPage}" />/<s:property value="%{pageBean.totalPage}" />页
	</span>
</div>