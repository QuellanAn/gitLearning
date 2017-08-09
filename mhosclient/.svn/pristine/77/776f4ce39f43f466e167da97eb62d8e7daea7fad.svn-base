<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<input id="hid_totalcount" type="hidden" value="${page.totalCount }"/>
	<script type="text/javascript">
		refresh_totalcount();
	</script>
	<table id="tb_cnt" class="list-table" style="margin-top: 5px;table-layout:fixed;">
		<thead>
			<tr>
			    <th>资金账户编号</th>
			    <th>对账来源</th>
			    <th>资金账户名称</th>
			    <th>状态</th>
			    <th>创建时间</th>
			    <th>操作</th>
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
			 <s:iterator value="checkList" var="checkList" status="status">
             <tr>
             <td align="center">${checkCode}</td> 
             <td align="center"><a href="javascript:toModify('${checkCode}')">${checkSource}</a></td>
             <td align="center">${acoountName}</td>
             <td align="center">
             <s:if test='#checkList.status==0'><font color="green">启用</font></s:if>
             <s:if test='#checkList.status==1'><font color="red">禁用</font></s:if>   
             </td>
             <td align="center">${createTime.substring(0,16)}</td>
             <td align="center">
	             <s:if test='#checkList.status==0'>
	             	<a href="javascript:toStatus('${checkCode}',1)">禁用</a>
	             </s:if>
             	 <s:if test='#checkList.status==1'>
	             	<a href="javascript:toStatus('${checkCode}',0)">启用</a>
	             </s:if>
             </td>
             </tr>
           </s:iterator>
           <s:if test="%{page.pageCount == 0}">
			<tr><td colspan="12" align="center">暂无数据</td></tr>
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
		<a href="javascript:datalist_ctrl.loaddatalist('1');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist('${page.pageNo-1}');" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:datalist_ctrl.loaddatalist('${page.pageNo+1}');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist('${page.pageCount}');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>
</div>