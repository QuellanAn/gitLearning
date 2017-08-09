<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.catic.mobilehos.service.vo.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:append var="list">
	<s:param value="%{pageBean.curPageData}" />
</s:append>
<s:hidden id="hid_totalcount" value="%{pageBean.totalRecord}" />
<s:hidden id="hid_currentPage" value="%{pageBean.curPage}" />
<%
	Page<ServiceEvaluationVO> pageBean = (Page<ServiceEvaluationVO>) request
			.getAttribute("pageBean");
	List<ServiceEvaluationVO> lst = pageBean.getCurPageData();
%>

<input id="hid_totalcount" type="hidden" value="<%=pageBean.getTotalRecord() %>"/>
<script type="text/javascript">
		refresh_totalcount();
</script>
	<table id="tb_cnt" class="list-table">
		<thead>
			<tr>
				<th style="margin-left: 5px;margin-right: 5px;">序号</th>
				<th style="margin-left: 5px;margin-right: 5px;">问卷标题</th>
				<!-- <th style="margin-left: 5px;margin-right: 5px;">手机号</th> -->
				<th style="margin-left: 5px;margin-right: 5px;">用户名</th>
				<th style="margin-left: 5px;margin-right: 5px;">参与时间</th>
				<th style="margin-left: 5px;margin-right: 5px;">操作</th>
				<!-- <th style="margin-left: 5px;margin-right: 5px;">就诊科室</th>
				<th style="margin-left: 5px;margin-right: 5px;">主治医生</th>
				<th style="margin-left: 5px;margin-right: 5px;">就诊时间</th> -->
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
			<s:iterator var="ServiceEvaluationVO" value="%{#list}"  status="index" >
			<tr>
				<td align="center"><s:property value="#index.getCount()" />  </td>
				<td align="center"><s:property value="#ServiceEvaluationVO.title" /></td>
				<%-- <td align="center"><s:property value="#ServiceEvaluationVO.sub_title" /></td> --%>
				<td align="center"><s:property value="#ServiceEvaluationVO.patientName" /></td>
			 	<td align="center"><s:property value="#ServiceEvaluationVO.createDateDesc" /></td> 
				
				<td align="center">
						<a href="busrecord/showQuestionnaire?questionnaireId=<s:property value="#ServiceEvaluationVO.questionnaireId" />
							&appRegOrderId=<s:property value="#ServiceEvaluationVO.appRegOrderId" />"
 							style="color: #0079ff; text-decoration: underline;cursor:pointer;">查看</a>
					</td>
				</s:iterator>
			<s:if test="%{pageBean.totalRecord == 0}"> <tr><td align="center" colspan="11" >暂无数据</td></tr></s:if>
			
		
			<%-- <% for (ServiceEvaluationVO v : lst) { %>
			<tr>
				<td align="center"><%=lst.index%></td>
				<td align="center"><%=v.getTitle()%></td>
				<td align="center"><%=v.getPatientName()%></td>
				<td align="center"><%=v.getCreateDateDesc()%></td>
				<td align="center">
 					<a href="busrecord/showQuestionnaire?questionnaireId=<%=v.getQuestionnaireId()%>&appRegOrderId=<%=v.getAppRegOrderId()%>"
 						style="color: #0079ff; text-decoration: underline;cursor:pointer;">查看</a> 
				</td>
				<td align="center"><%=v.getDepartmentName()%></td>
				<td align="center"><%=v.getDoctorName()%></td>
				<td align="center"><%=v.getDoctorDate()%></td>
			</tr>
			<%
				}
			%> --%>
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
	&nbsp; <span class="pagination">共<%=pageBean.getCurPage()%>/<%=pageBean.getTotalPage()%>页
	</span>
</div>