<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<input id="hid_totalcount" type="hidden" value="${page.totalCount }"/>
	<script type="text/javascript">
		refresh_totalcount();
	</script>
<table id="tb_cnt" class="list-table">
	<thead>
		<tr>
			<th width="13%">终端编号</th>
			<th width="15%">终端名称</th>
			<th width="10%">院区</th>
			<th width="15%">终端类型</th>
			<th width="15%">状态</th>
			<th width="10%">主程序版本</th>
			<th width="12%">守护程序版本</th>
			<th width="10%">操作</th>
		</tr>
	</thead>

	<tbody id="tb_cnt_body">
		<s:iterator value="ptlist" var="ter" status="status">
		<tr>
			<td align="center">${facilityId }</td>
			<td align="center"><a href="selfService/selfServiceManageAction_toDetail?id=${id }">${facilityName }</a></td>
			<td align="center">${name}</td>
			<td align="center">${facilityType }</td>
			<td align="center">
				<s:if test="%{facilityStatus==0}"><span style="color: #44B549">启用</span></s:if>
				<s:if test="%{facilityStatus==1}"><span style="color: #FF0000">故障</span></s:if>
				<s:if test="%{facilityStatus==2}">关机</s:if>
				<s:if test="%{facilityStatus==3}"><span style="color: #FF6633">禁用</span></s:if>
			</td>
			<td align="center">${mainProgramVersion }</td>
			<td align="center">${daemonVersion }</td>
			<td align="center">
				<a class="act_edit"	href="selfService/selfServiceUpgradeAction_toFacilityVersionDetail?terminalId=${id }">查看 </a>
			</td>		
		</tr>
		</s:iterator>
		   <s:if test="%{page.pageCount == 0}"> <tr><td align="center" colspan="8" >暂无数据</td></tr></s:if>
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
		<a href="javascript:loadPage('1');" class="pagination">首页 </a>
		<a href="javascript:loadPage('${page.pageNo-1}')" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:loadPage('${page.pageNo+1}');" class="pagination">下页 </a>
		<a href="javascript:loadPage('${page.pageCount}');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>