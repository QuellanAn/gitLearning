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
				<th style="margin-left: 5px;margin-right: 5px;">账户编号</th>
				<th style="margin-left: 5px;margin-right: 5px;">账户名称</th>
				<th style="margin-left: 5px;margin-right: 5px;">第三方账户</th>
				<th style="margin-left: 5px;margin-right: 5px;">院区</th>
				<th style="margin-left: 5px;margin-right: 5px;">支付方式</th>
			    <th style="margin-left: 5px;margin-right: 5px;">状态</th>
				<th style="margin-left: 5px;margin-right: 5px;">操作者</th>
				<th style="margin-left: 5px;margin-right: 5px;">备注</th>
			    <th style="margin-left: 5px;margin-right: 5px;">操作时间</th>
			    <th style="margin-left: 5px;margin-right: 5px;">操作</th>
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
			 <s:iterator value="accountList" var="accountList" status="status">
             <tr>
             <td align="center">${accountCode}</td> 
             <td align="center" >${accountName}</td>   
             <td align="center" style="word-wrap:break-word;" >${outAccount}</td>
             <td align="center">${yqName}</td> 
             <td align="center">
                <s:if test='#accountList.pattern==0'>微信</s:if>
                <s:if test='#accountList.pattern==1'>支付宝</s:if>
                <s:if test='#accountList.pattern==2'>龙支付</s:if>
             </td>   
              <td align="center">
                <s:if test='#accountList.status==0'><font color="green">启用</font></s:if>
                <s:if test='#accountList.status==1'><font color="red">禁用</font></s:if>
             </td>            
             <td align="center">${operator}</td> 
             <td align="center">${remark}</td> 
             <td align="center">${createTime.substring(0,16)}</td> 
             <td align="center">
               <a  href="javascript:toModify('${accountCode}')">编辑</a>
           <%--     <a  href="javascript:toDelete('${yqCode}','${pageNo}')" class="icon del-icon f-fl"  title="删除"></a> --%>
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
		<a href="javascript:datalist_ctrl.loaddatalist1('unpaid/accountConfigAction_findAll?pageNo=1');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('unpaid/accountConfigAction_findAll?pageNo=${page.pageNo-1}')" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:datalist_ctrl.loaddatalist1('unpaid/accountConfigAction_findAll?pageNo=${page.pageNo+1}');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('unpaid/accountConfigAction_findAll?pageNo=${page.pageCount}');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>
</div>