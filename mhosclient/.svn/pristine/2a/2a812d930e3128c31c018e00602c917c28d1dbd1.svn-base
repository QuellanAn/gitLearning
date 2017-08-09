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
				<th style="margin-left: 5px;margin-right: 5px;">院区编号</th>
				<th style="margin-left: 5px;margin-right: 5px;">院区名称</th>
				<th style="margin-left: 5px;margin-right: 5px;">院区联系人</th>
				<th style="margin-left: 5px;margin-right: 5px;">联系电话</th>
				<th style="margin-left: 5px;margin-right: 5px;">院区地址</th>
				<th style="margin-left: 5px;margin-right: 5px;">创建者</th>
				<th style="margin-left: 5px;margin-right: 5px;">创建时间</th>
			<!-- 	<th style="margin-left: 5px;margin-right: 5px;">操作</th> -->
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
			 <s:iterator value="yqList" var="yqList" status="status">
             <tr>
             <td align="center" >${yqCode}</td> 
             <td align="center" >${name}</td>   
             <td align="center">${linkManName}</td>
             <td align="center">${linkPhone}</td> 
              <td align="center">${address}</td> 
             <td align="center">${creator}</td> 
             <td align="center">${createTime.substring(0,16)}</td> 
            <%--  <td align="center">
               <a  href="javascript:toModifyYQ('${yqCode}','${pageNo}')" class="icon edit-icon f-fl" title="编辑"></a>
               <a  href="javascript:toDelete('${yqCode}','${pageNo}')" class="icon del-icon f-fl"  title="删除"></a>
             </td>  --%>
           
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
		<a href="javascript:datalist_ctrl.loaddatalist1('unpaid/yQConfigAction_findAll?pageNo=1');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('unpaid/yQConfigAction_findAll?pageNo=${page.pageNo-1}')" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:datalist_ctrl.loaddatalist1('unpaid/yQConfigAction_findAll?pageNo=${page.pageNo+1}');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('unpaid/yQConfigAction_findAll?pageNo=${page.pageCount}');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>
</div>