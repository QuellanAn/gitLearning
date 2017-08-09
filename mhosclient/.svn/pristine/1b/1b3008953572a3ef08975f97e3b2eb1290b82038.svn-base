<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<input id="hid_totalcount" type="hidden" value="${page.totalCount }"/>
	<script type="text/javascript">
		refresh_totalcount();
	</script>
<table id="tb_cnt" class="list-table" style="margin-top: 5px;">
	<tbody>
		<tr>
			<th>序号</th>
			<th>类别名称</th>
			<th>标题名</th>
			<th>开讲人</th>
			<th>开讲日期</th>
			<th width="10%">操作</th>
		</tr>
		<s:iterator var="list" value="list" status="status">
			<tr>
				<td align="center">${status.count}</td>
				<td align="center">
					<s:if test="#list.type==2">
						院长开讲
					</s:if>
				</td>
				<td align="center">${title }</td>
				<td align="center">${author }</td>
				<td align="center">${createtime.substring(0,10)}</td> 
				<td align="center">
				<a class="act_edit"	href="javascript:updatehosinfo(${id });">编辑 </a>
				&nbsp;&nbsp;
				<a class="act_delete" href="javascript:deletehosinfo(${id });">删除</a>
				</td>	
		</s:iterator>
		<s:if test="%{page.pageCount == 0}">
			<tr><td colspan="6" align="center">暂无数据</td></tr>
			</s:if>
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
		<a href="javascript:datalist_ctrl.loaddatalist1('1');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageNo-1}');" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageNo+1}');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageCount}');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>