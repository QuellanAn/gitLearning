<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input id="hid_totalcount" type="hidden" value="${page.totalCount }"/>
	<script type="text/javascript">
		refresh_totalcount();
	</script>
<table id="tb_cnt" class="list-table">
	<thead>
		<tr>
			<th width="12%">
				<c:if test="${empty terminalId }">上传时间</c:if>
				<c:if test="${!empty terminalId }">升级时间</c:if>
			</th>
			<th width="13%">升级类型</th>
			<c:if test="${empty terminalId }"><th width="13%">终端类型</th></c:if>
			<th width="10%">版本号</th>
			<th width="12%">版本名称</th>
			<th width="18%">更新内容</th>
			<c:if test="${empty terminalId }"><th width="10%">状态</th></c:if>
			<c:if test="${empty terminalId }"><th width="12%">激活时间</th></c:if>
		</tr>
	</thead>

	<tbody id="tb_cnt_body">
		<s:iterator value="versionList" var="version" status="status">
		<tr>
			<td align="center"><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			<td align="center">
				<s:if test="%{upgradeType==0}">主程序升级</s:if>
				<s:if test="%{upgradeType==1}">守护程序升级</s:if>
			</td>
			<s:if test="terminalId==null || terminalId==''"><td align="center">${facilityTypeName}</td></s:if>
			<td align="center">${versionCode}</td>
			<td align="center">${versionName}</td>
			<td align="center">${content }</td>
			<s:if test="terminalId == null || terminalId==''">
				<td align="center">
					<s:if test="%{activationStatus==0}">未激活</s:if>
					<s:if test="%{activationStatus==1}">已激活</s:if>
				</td>
				<td align="center"><s:date name="activationTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			</s:if>
		</tr>
		</s:iterator>
		   <s:if test="%{page.pageCount == 0}"> <tr><td align="center" colspan="7" >暂无数据</td></tr></s:if>
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