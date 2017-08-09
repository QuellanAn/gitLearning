<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:append var="list">
	<s:param value="%{pageBean.curPageData}" />
</s:append>

<s:hidden id="hid_totalcount" value="%{pageBean.totalRecord}" />
<script type="text/javascript">
	refresh_unpubcount();
	$(document).ready(function(){
		// 编辑
		$('.editCliVersion').each(function() {
			$(this).click(function() {
				location.href = "config/version/updateCliVersion?cliVersionPO.ID="+$(this).siblings('input').val();
			});
		});

		// 删除
		$('.delCliVersion').each(function() {
			$(this).click(function() {
				if(confirm("你是否确定要删除该条记录?"))
				$.post("config/version/delCliVersion",{"cliVersionPO.ID":$(this).siblings('input').val()},function(data){
					if(data.result=='1'){
						showTips(data.data,100,2);
						//alert(data.data);
						var cnt = $('#div_cnt_table');
						var paras = $("#cliVersionForm").serialize();
						$.post("config/version/getCliVersions?page="+<s:property value="%{pageBean.curPage}"/>+"&"+paras, function(data, status) {
							cnt.empty();
							cnt.append(data);
						}, 'html');
					}else if(data.result=='0'){
						//salert(data.data);
						showTips(data.data,100,2);
					}
				},'json');
			});
		});//end 删除
		
	});
</script>
<table class="list-table">
	<thead>
		<tr>
			<th align="center" width="11%">客户端平台</th>
			<th align="center" width="11%">客户端类别</th>
			<th align="center" width="8%">版本号</th>
			<th align="center" width="12%">发布日期</th>
			<th align="center" width="13%">更新内容</th>
			<th align="center" width="11%">升级版本</th>
			<th align="center" width="8%">件大小（MB）</th>
			<th align="center" width="19%">文件包</th>
			<th align="center" width="7%"></th>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="cliVersionVO" value="%{#list}">
			<tr>
				<td align="center"><s:property value="#cliVersionVO.ver_cat" /></td>
				<td align="center"><s:property value="#cliVersionVO.ver_type" /></td>
				<td align="center"><s:property value="#cliVersionVO.versionNo" /></td>
				<td align="center"><s:date name="#cliVersionVO.createDate" format="yyyy-MM-dd" /></td>
				<td <s:if
						test="null != #cliVersionVO.content && #cliVersionVO.content.length()>10">title="<s:property value="#cliVersionVO.content" />"</s:if>><s:if
						test="null != #cliVersionVO.content && #cliVersionVO.content.length()>10">
						<s:property value="#cliVersionVO.content.substring(0,10)" />...
					</s:if>
					<s:else><s:property value="#cliVersionVO.content" default="" /></s:else>
				<td align="center"><s:property value="#cliVersionVO.update_version" /></td>
				<td align="center"><s:property value="filesize/1000" />.<s:property value="filesize%1000" /></td>
				<td align="center"><s:property value="#cliVersionVO.apkName" /></td>
				<td align="center"><span
					class="editCliVersion icon edit-icon" style="margin-left:5px;" title="编辑">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span
					class="delCliVersion icon del-icon" style="margin-left:2px;" title="删除">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><input
					type="hidden" value="<s:property value="#cliVersionVO.ID"/>" /></td>
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