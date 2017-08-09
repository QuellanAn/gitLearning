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
			<th width="20%"">角色</th>
			<th width="40%">描述</th>
			<th width="15%">时间</th>
			<th width="15%">操作</th>
		</tr>
	</thead>


	<tbody id="tb_cnt_body">
	<s:iterator value="list" var="list" status="status">
		<tr>
			<td align="center">${status.count}</td>
			<td align="center">${name}</td>
			<td align="center">${depict}</td>
			<td align="center"><s:date name="#list.createDate" format="yyyy-MM-dd HH:mm" /></td>
			<td align="center">
			<s:form action="role_findById">
			<s:hidden name="role.roleId" value="%{roleId}" />
				<span class="act_edit" onclick="role.modifyRole(this);" style="margin: auto 3px;cursor:pointer;"><a>编辑 </a></span>
				<s:if test="%{roleId != 1}">
					<span class="act_edit" onclick="javascript:role.deleteRole('${roleId}', '${page.pageNo}'); return false;" style="margin: auto 3px;cursor:pointer;"><a>删除</a></span>
				</s:if>
			</s:form>
				
			<%-- <span class="icon del-icon f-fl" title="删除" onclick="javascript:role.deleteRole('${roleId}', '${page.pageNo}'); return false;" style="margin: auto 5px;"></span></td> --%>
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
		<a href="javascript:role.findAll('${1}');" class="pagination" >首页 </a>
		<a href="javascript:role.findAll('${page.pageNo-1}');" class="pagination" >上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:role.findAll('${page.pageNo+1}');" class="pagination" >下页 </a>
		<a href="javascript:role.findAll('${page.pageCount}');" class="pagination" >末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>




