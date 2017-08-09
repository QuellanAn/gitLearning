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
			<th width="13%">日志类型</th>
			<th width="10%">终端编号</th>
			<th width="10%">院区</th>
			<th width="15%">设备类型</th>
			<th width="15%">位置信息</th>
			<th width="15%">文件</th>
			<th width="12%">更新时间</th>
			<th width="10%">操作</th>
		</tr>
	</thead>

	<tbody id="tb_cnt_body">
		<s:iterator value="logList" var="log" status="status">
		<tr>
			<td align="center">
				<s:if test="%{logType==0}">系统日志</s:if>
				<s:if test="%{logType==1}">交易日志</s:if>
				<s:if test="%{logType==2}">异常日志</s:if>
			</td>
			<td align="center">${facilityId }</td>
			<td align="center">${district}</td>
			<td align="center">${facilityType}</td>
			<td align="center">${putAddress }</td>
			<td align="center">${logFileName }</td>
			<td align="center"><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			<td align="center">
				<a class="act_delete" href="<c:url value='${logPath }'/>" download="">下载</a>
			</td>
		</tr>
		</s:iterator>
		   <s:if test="%{page.pageCount == 0}"> <tr><td align="center" colspan="9" >暂无数据</td></tr></s:if>
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