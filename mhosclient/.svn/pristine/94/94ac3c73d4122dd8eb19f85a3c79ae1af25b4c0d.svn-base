<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table id="tb_cnt" class="list-table">
	<thead>
		<tr>
			<th style="padding-left: 1px;padding-right: 1px;">编号</th>
			<th style="padding-left: 1px;padding-right: 1px;">姓名</th>
			<th style="padding-left: 1px;padding-right: 1px;">科室</th>
			<th style="padding-left: 1px;padding-right: 1px;">职称</th>
			<th style="padding-left: 1px;padding-right: 1px;">简介</th>
			<th style="padding-left: 1px;padding-right: 1px;">擅长</th>
			<th style="padding-left: 1px;padding-right: 1px;">操作</th>
		</tr>
	</thead>

	<tbody id="tb_cnt_body">
	<s:iterator value="list" status="s">
		<tr>
			<td align="center">${code}</td>
			<td align="center">${name}</td>
			<td align="center">${department}</td>
			<td align="center">${type}</td>
			<td align="center"><s:if test="%{depict.length() > 10}">${depict.substring(0,10)}...</s:if><s:else>${depict}</s:else></td>
			<td align="center"><s:if test="%{adept.length() > 10}">${adept.substring(0,10)}...</s:if><s:else>${adept}</s:else></td>
			<td align="center" width="50">
				<span class="icon edit-icon f-fl" title="编辑" onclick="javascript:specialist.toModify('${code}');return false;" style="margin: auto 5px;"></span>
				<span class="icon del-icon f-fl" title="删除" onclick="javascript:specialist.Delete('${id}'); return false;" style="margin: auto 5px;"></span>
			</td>
			
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
		<a href="javascript:specialist.findAll('${name}', '${1}');" class="pagination" >首页 </a>
		<a href="javascript:specialist.findAll('${name}', '${page.pageNo-1}');" class="pagination" >上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:specialist.findAll('${name}', '${page.pageNo+1}');" class="pagination" >下页 </a>
		<a href="javascript:specialist.findAll('${name}', '${page.pageCount}');" class="pagination" >末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>




