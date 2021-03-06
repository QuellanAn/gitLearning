<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:append var="lst">
	<s:param value="%{pageBean.curPageData}"></s:param>
</s:append>


<s:hidden name="infocatcode"></s:hidden>
<input id="hid_totalcount" type="hidden"
	value="<s:property value="%{pageBean.totalRecord}"/>" />
<script type="text/javascript">
		refresh_unpubcount();
	</script>
<table class="list-table">
	<tbody>
		<tr>
			
			<th>序号</th>
			<th>咨询分类</th>
			<!-- <th>资讯细类</th> -->
			<th>标题</th>
			<th>首页展示</th>
			<th>编辑人</th>
			<th>编辑日期</th>
			<th>预计发布日期</th>
			<th>发布状态</th>
			<th width="10%">操作</th>
		</tr>
		<s:iterator var="hosPubInfoVO" value="%{#lst}" status="status">
			<tr>
				<td><s:property value="%{#status.index+1}"/></td>
				<td><s:property value="#hosPubInfoVO.getInfoTypeDesc()" /></td>
				<!-- <td><s:property value="#hosPubInfoVO.infoCatName" /></td> -->
				<td style="text-align: center;" class="f-ta-left"
					<s:if
						test="null != #hosPubInfoVO.subject && #hosPubInfoVO.subject.length()>6"> title="<s:property value="#hosPubInfoVO.subject" />" </s:if>><a
					href="hosinfomng/prevunpubhosinfo?infoid=<s:property value="#hosPubInfoVO.hosInfoId"/>">
						<s:if
							test="null != #hosPubInfoVO.subject && #hosPubInfoVO.subject.length()>6">
							<s:property value="#hosPubInfoVO.subject.substring(0,6)" />...
					</s:if> <s:else>
							<s:property value="#hosPubInfoVO.subject" default="" />
						</s:else>
				</a></td>
				<td>
					<s:if test="#hosPubInfoVO.isMain=1">是</s:if> 
					<s:else>否</s:else></td>
				<td><s:property value="#hosPubInfoVO.editor" /></td>
				<td><s:date name="#hosPubInfoVO.updateDate"
						format="yyyy-MM-dd" /></td>
				<td><s:date name="#hosPubInfoVO.expPubDate"
						format="yyyy-MM-dd" /></td>
				<td><s:property
						value="#hosPubInfoVO.getStatusDesc()" /></td>
				<td align="center">
					<a class="act_edit"	href="hosinfomng/showedithosinfopage?infoid=<s:property value="#hosPubInfoVO.hosInfoId"/>">编辑 </a>
					&nbsp;&nbsp;
					<a class="act_delete" href="javascript:deletehosinfo('<s:property value="#hosPubInfoVO.hosInfoId"/>', '<s:property value="%{#infocatcode}"/>', <s:property value="%{pageBean.curPage}"/>);">删除</a>
				</td>	
		</s:iterator>
		<s:if test="%{pageBean.totalRecord == 0}"> <tr><td align="center" colspan="9" >暂无数据</td></tr></s:if>
	</tbody>
</table>
<div class=" pagination-wrap f-fr">
	<s:if test="%{pageBean.isFirstPage()}">
		<a class="pagination">首页</a>
	</s:if>
	<s:else>
		<a class="pagination" href="#"
			onclick="javascript:datalist_ctrl.topage(<s:property value="#action.infotype"/>, '<s:property value="#action.infocatcode"/>', 1);return false;">
			首页 </a>
	</s:else>

	<s:if test="%{pageBean.isFirstPage()}">
		<span class="pagination">上页</span>
	</s:if>
	<s:else>
		<a href="#" class="pagination"
			onclick="javascript:datalist_ctrl.topage(<s:property value="#action.infotype"/>, '<s:property value="#action.infocatcode"/>',<s:property value="%{pageBean.getPrevPage()}"/>);return false;">
			上页 </a>
	</s:else>
	<s:if test="%{pageBean.isLastPage()}">
		<a class="pagination">下页</a>
	</s:if>
	<s:else>
		<a href="#" class="pagination" href="#"
			onclick="javascript:datalist_ctrl.topage(<s:property value="#action.infotype"/>, '<s:property value="#action.infocatcode"/>', <s:property value="%{pageBean.getNextPage()}"/>); return false;">
			下页 </a>
	</s:else>
	<s:if test="%{pageBean.isLastPage()}">
		<a class="pagination">末页</a>
	</s:if>
	<s:else>
		<a href="#" class="pagination"
			onclick="javascript:datalist_ctrl.topage(<s:property value="#action.infotype"/>, '<s:property value="#action.infocatcode"/>', <s:property value="%{pageBean.getTotalPage()}"/>);return false;">
			末页 </a>
	</s:else>
	<span class="pagination">共<s:if test="#pageBean.totalPage>0"></s:if><s:property
			value="%{pageBean.curPage}" />/<s:property
			value="%{pageBean.totalPage}" />页
	</span>
</div>