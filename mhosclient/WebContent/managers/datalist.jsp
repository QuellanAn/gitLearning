<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<input id="hid_totalcount" type="hidden" value="${page.totalCount}" />
<script type="text/javascript">
	refresh_unpubcount();
</script>
<table id="tb_cnt" class="list-table">
	<thead>
		<tr>
			<th width="10%">序号</th>
			<th width="15%">账号</th>
			<th width="10%">姓名</th>
			<th width="10%">性别</th>
			<th width="15">手机号码</th>
			<th width="10%">状态</th>
			<th width="15%">操作时间</th>
			<th width="15%">操作</th>
		</tr>
	</thead>


	<tbody id="tb_cnt_body">
	<s:iterator value="list" var="list" status="statu">
		<tr>
			<td align="center">${statu.count}</td>
			<td align="center">${userName}</td>
			<td align="center">${realName}</td>
			<td align="center"><s:if test="%{sex == 0}">男</s:if><s:else>女</s:else></td>
			<td align="center">${tel}</td>
			<td align="center"><s:if test="%{status == 0}">有效</s:if><s:else>无效</s:else></td>
			<td align="center"><s:date name="#list.updateTime" format="yyyy-MM-dd" /><br></td>
			<td align="center">
			<s:form action="manager_enterModify">
			<s:hidden name="manager.userId" value="%{userId}" />
				<s:if test="%{#session.roleId == 1}">
				<span class="act_edit" onclick="javascript:manager.modifyManager(this);" style="margin: auto 3px;cursor:pointer;"><a>编辑 </a></span>
				<s:if test="%{roleId != 1}">
				<span class="act_edit" onclick="javascript:manager.deleteManager('${userId}', '${page.pageNo}'); return false;" style="margin: auto 3px;cursor:pointer;"><a>删除</a></span></s:if></s:if>
				<s:if test="%{#session.roleId != 1 && roleId!=1}">
				<span class="act_edit" onclick="javascript:manager.modifyManager(this);" style="margin: auto 3px;cursor:pointer;"><a>编辑 </a></span>
				<span class="act_edit" onclick="javascript:manager.deleteManager('${userId}', '${page.pageNo}'); return false;" style="margin: auto 3px;cursor:pointer;"><a>删除</a></span></s:if>
			</s:form>
		</tr>
	</s:iterator>
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
		<a href="javascript:manager.pageing('${1}');" class="pagination" >首页 </a>
		<a href="javascript:manager.pageing('${page.pageNo-1}');" class="pagination" >上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:manager.pageing('${page.pageNo+1}');" class="pagination" >下页 </a>
		<a href="javascript:manager.pageing('${page.pageCount}');" class="pagination" >末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>




