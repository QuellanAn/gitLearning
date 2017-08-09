<%@page import="com.catic.mobilehos.po.QuestionnairePO"%>
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
<script type="text/javascript">
 function deleteQuestionnaire(obj,id){
 confirm("您确定要删除该条记录吗");
	  $.ajax({
			url: "questionnaire/deleteQuestionnaire",
			type: "POST",
			data: {id: id},
			dataType: "json",
			success: function(data){
				if(data.result =='0'){
					$(span).parent().parent().remove();
				}else if(data.result =='1'){
					confirm("删除失败");
				}
			}
		}, 'json');
		confirm("删除成功"); 
		datalist_ctrl.loaddatalist("questionnaire/getQuestionnaiList?page=1");	
}

</script>
<table class="list-table">
	<thead>
		<tr>
			<th align="center" width="5%">序号</th>
			<th align="center" width="30%">标题</th>
			<!-- <th align="center" width="42%">副标题</th> -->
			<th align="center" width="15%">用户名</th>
			<!-- <th align="center" width="11%">创建时间</th> -->
			<th align="center" width="18%">参与时间</th>
			<th align="center" width="5%">状态</th>
			<th align="center" width="10%">操作</th>
			
		</tr>
	</thead>
	<tbody>
	<s:iterator var="questionnaireVO" value="%{#list}"  status="index">
			<tr>
				<td align="center"><s:property value="#index.getCount()" /></td>
				<td align="center"><s:property value="#questionnaireVO.title" /></td>
				<%-- <td align="center"><s:property value="#questionnaireVO.sub_title" /></td> --%>
				<td align="center"><s:property value="#questionnaireVO.userName" /></td>
			 	<td align="center"><s:date name="#questionnaireVO.createtime"
						format="yyyy-MM-dd HH:mm:ss" /></td> 
				<%-- <td align="center"><s:date name="#questionnaireVO.updatetime"
						format="yyyy-MM-dd HH:mm:ss" /></td> --%>
				<td align="center"><s:if test="#questionnaireVO.status==1">有效</s:if>
					<s:else><font color="red">无效</font></s:else></td>
				
				
				<td align="center">

						<%-- <a class="editCliVersion icon edit-icon f-fl" title="编辑" href="questionnaire/updateQuestionnaire?id=<s:property value="#questionnaireVO.id" />"
							></a> --%>
						<a href="questionnaire/updateQuestionnaire?id=<s:property value="#questionnaireVO.id" />&upType=1"
 							style="color: #0079ff; text-decoration: underline;cursor:pointer;">查看</a>
 						<a onclick="deleteQuestionnaire(this,'<s:property value="#questionnaireVO.id" />');"
 							style="color: #0079ff; text-decoration: underline;cursor:pointer;">删除</a>	
						<%-- <span class="delCliVersion icon del-icon f-fl" title="删除  "
							onclick="deleteQuestionnaire(this,'<s:property value="#questionnaireVO.id" />');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> --%>
					</td>
				</s:iterator>
				<s:if test="%{pageBean.totalRecord == 0}"> <tr><td align="center" colspan="11" >暂无数据</td></tr></s:if>
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