<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	Page<HelpVO> pageBean = (Page<HelpVO>) request
	.getAttribute("pageBean");
	List<HelpVO> lst = pageBean.getCurPageData();
%>


<input id="hid_totalcount" type="hidden"
	value="<%=pageBean.getTotalRecord()%>" />
<script type="text/javascript">
	refresh_unpubcount();
</script>
<table id="tb_cnt" class="list-table">
	<thead>
		<tr>
			<th width="10%">序号</th>
			<th width="15%">问题</th>
			<th width="35%">答案</th>
			<th width="10%">操作人</th>
			<th width="20%">操作时间</th>
			<th width="10%">操作</th>
		</tr>
	</thead>

	<tbody id="tb_cnt_body">
		<%
			int i = 1;
			for (HelpVO v : lst) {
		%>
		<tr>
			<%-- <td align="center"><%=v.getHelpId()%></td> --%>
			<td align="center"><%=i++%></td>
			<td align="center"><%=v.getQuestion()%></td>
			<td align="center"><%=v.getAnswer()%></td>
			<td align="center"> <%=v.getCreatePeople()%></td>
			<td align="center"><fmt:formatDate value="<%=v.getCreateTime()%>" pattern="yyyy-MM-dd mm:ss"/></td>
			<td align="center">
			<a class="act_edit"	href="javascript:updateHelp(<%=v.getHelpId()%>);">编辑 </a>
			&nbsp;&nbsp;
			<a class="act_delete" href="javascript:deleteHelp('<%=v.getHelpId()%>');">删除</a>
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