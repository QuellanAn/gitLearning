<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	Page<DepartmentVO> pageBean = (Page<DepartmentVO>) request
	.getAttribute("pageBean");
	List<DepartmentVO> lst = pageBean.getCurPageData();
%>


<input id="hid_totalcount" type="hidden"
	value="<%=pageBean.getTotalRecord()%>" />
<script type="text/javascript">
	refresh_unpubcount();
</script>
<table id="tb_cnt" class="list-table" style="width:850px;">
	<thead>
		<tr>
			<th width="10%">科室排序</th>
			<th width="10%">科室名称</th>
			<th width="10%">科室编号</th>
			<th width="15%">联系电话</th>
			<th width="30%">科室位置</th>
			<th width="10%">科室状态</th>
			<th width="15%">操作</th>
		</tr>
	</thead>

	<tbody id="tb_cnt_body">
		<%
			for (DepartmentVO v : lst) {
		%>
		<tr>
			<td align="center"><%=v.getDisplay_no() %></td>
			<td align="center"><%=v.getDepartmentName()%></td>
			<td align="center"><%=v.getDepartmentId()%></td>
			<td align="center">
			<% 
			if(v.getPhone()!=null){
			
			%>
			<%=v.getPhone()%>
			
			<%}%>
			
			</td>
			<td align="center"> <%=v.getDepartmentAddr()%></td>
			<% 
			if(v.getDepartment_status()==1){
			%>
			<td align="center">正常</td>
			<%}%>
			<% 
			if(v.getDepartment_status()==2){
			%>
			<td align="center">冻结</td>
			<%}%>
			<td align="center">
			<a class="act_edit" id="act_edit_<%=v.getDepartmentId()%>"
				href="javascript:act_edit('<%=v.getId()%>');">
					编辑 </a>
					
			&nbsp;&nbsp;
			<a class="act_delete" id="act_delete_<%=v.getId()%>"
				href="javascript:deleteDepartment('<%=v.getId()%>','<%=v.getDepartmentName()%>');">
					删除</a>
					</td>		
					
					</td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>


<div class="pagination-wrap f-fr">
	<%
		if (pageBean.isFirstPage()) {
	%>
	<a class="pagination">首页</a>
	<%
		} else {
	%>
	<a href="#" class="pagination"
		onclick="javascript:datalist_ctrl.topage('<%=pageBean.getFirstPageUrl()%>');return false;">
		首页 </a>
	<%
		}
	%>
	<%
		if (pageBean.isFirstPage()) {
	%>
	<a class="pagination">上页</a>
	<%
		} else {
	%>
	<a href="#" class="pagination"
		onclick="javascript:datalist_ctrl.topage('<%=pageBean.getPrevPageUrl()%>');return false;">
		上页 </a>
	<%
		}
	%>
	<%
		if (pageBean.isLastPage()) {
	%>
	<a class="pagination">下页</a>
	<%
		} else {
	%>
	<a href="#" class="pagination"
		onclick="javascript:datalist_ctrl.topage('<%=pageBean.getNextPageUrl()%>'); return false;">
		下页 </a>
	<%
		}
	%>
	<%
		if (pageBean.isLastPage()) {
	%>
	<a class="pagination">末页</a>
	<%
		} else {
	%>
	<a href="#" class="pagination"
		onclick="javascript:datalist_ctrl.topage('<%=pageBean.getLastPageUrl()%>');return false;">
		末页 </a>
	<%
		}
	%>
	&nbsp; <span class="pagination">共<%if(pageBean.getTotalPage()>0){out.print(pageBean.getCurPage()+"/");}%><%=pageBean.getTotalPage()%>页
	</span>
</div>