<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	Page<HosPubInfoVO> pageBean = (Page<HosPubInfoVO>) request
	.getAttribute("pageBean");
	List<HosPubInfoVO> lst = pageBean.getCurPageData();
%>



<input id="hid_totalcount" type="hidden"
	value="<%=pageBean.getTotalRecord()%>" />
<script type="text/javascript">
	refresh_unpubcount();
</script>
<table id="tb_cnt" class="list-table">
	<thead>
		<tr>
			<th width="40" style="padding-left: 1px;padding-right: 1px;">序号</th>
			<th width="100" style="padding-left: 1px;padding-right: 1px;">资讯大类</th>
			<!-- <th width="100" style="padding-left: 1px;padding-right: 1px;">资讯细类</th> -->
			<th width="150" style="padding-left: 1px;padding-right: 1px;">标题</th>
			<th width="76" style="padding-left: 1px;padding-right: 1px;">首页展示</th>
			<th width="76" style="padding-left: 1px;padding-right: 1px;">编辑人</th>
			<th width="88" style="padding-left: 1px;padding-right: 1px;">编辑日期</th>
			<!-- <th width="87" style="padding-left: 1px;padding-right: 1px;">摘要</th> -->
			<th width="80" style="padding-left: 1px;padding-right: 1px;">审批人</th>
			<th width="110" style="padding-left: 1px;padding-right: 1px;">审批日期</th>
			<th width="110" style="padding-left: 1px;padding-right: 1px;">预计发布日期</th>
			<th width="100" style="padding-left: 1px;padding-right: 1px;">发布状态</th>
			<th width="40" style="padding-left: 1px;padding-right: 1px;">操作</th>
		</tr>
	</thead>


	<tbody id="tb_cnt_body">
		<% if(lst.size() <= 0){%>
			<tr><td align="center" colspan="11" >暂无数据</td></tr>
		<%}else{ %>
			<%
				int i=1;
				for (HosPubInfoVO v : lst) {
			%>
			<tr>
				<td align="center"><%=i++ %></td>
				<td align="center"><%=v.getInfoTypeDesc()%></td>
				<%-- <td align="center"><%=v.getInfoCatName()%></td> --%>
				<td align="center" <%if(v.getSubject().length() > 10) {%> title="<%=v.getSubject()%>"<%} %>><a
					href="hosinfomng/prevpubhosinfo?infoid=<%=v.getHosInfoId()%>"><%=v.getSubject().length()>10?(v.getSubject().substring(0, 10)+"..."):v.getSubject()%></a></td>
				<%-- <td style="text-align:left;" <%if(v.getConcise().length() > 5) {%> title="<%=v.getConcise()%>" <%} %>><%=v.getConcise().length()>5?(v.getConcise().substring(0, 5)+"..."):v.getConcise()%></td> --%>
				<td align="center"><%if(v.getIsMain()==1){%>是<% } else{%>否<%}%></td>
				<td align="center"><%=v.getEditor()%></td>
				<td align="center"><%=v.getUpdateDateDesc()%></td>
				<td align="center"><%=v.getApprover() %></td>
				<td align="center"><%=v.getApproveDateDesc()%></td>
				<td align="center"><%=v.getExpPubDateDesc()%></td>
				<td align="center"><%=v.getStatusDesc()%></td>
				<td align="center">
					<a class="act_delete" href="javascript:deletehospub('<%=v.getHosInfoId() %>', '<%=v.getInfoCatCode() %>', '${pageBean.curPage}');");">删除</a
				</td>
			</tr>
			<%
				}
			%>
		<%
			}
		%>
	</tbody>
</table>


<div class=" pagination-wrap f-fr">
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





