<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<input id="hid_totalcount" type="hidden" value="${page.totalCount }"/>
<input id="hid_startdate" type="hidden" value="${startdate}"/>
<input id="hid_enddate" type="hidden" value="${enddate}"/>
<input id="hid_billStatus" type="hidden" value="${billStatus}"/>
	<script type="text/javascript">
		refresh_totalcount();
	</script>
	<table id="tb_cnt" class="list-table" style="margin-top: 5px;margin-bottom: 10px;">
		<thead>	
			<tr>
			    <th style="margin-left: 2px;margin-right: 2px;"rowspan="2">
			    <input type="checkbox" name="checkbox" id="check" onclick="dosubmit('ids','check');"/>
			    </th> 
				<th style="margin-left: 5px;margin-right: 5px;"rowspan="2">账单日期</th>
				<th style="margin-left: 5px;margin-right: 5px;"rowspan="2">资金账户</th>
			    <th style="margin-left: 5px;margin-right: 5px;background-color:#75cecc;"colspan="3"><font color="#FFFFFF">综合支付平台</font></th>
			    <th style="margin-left: 5px;margin-right: 5px;background-color:#fd7054;"colspan="3"><font color="#FFFFFF">第三方支付平台</font></th>
			    <th style="margin-left: 5px;margin-right: 5px;background-color:#6fbfe8;"colspan="3"><font color="#FFFFFF">HIS系统</font></th>
			    <th style="margin-left: 5px;margin-right: 5px;"rowspan="2">对账状态</th>
			    <th style="margin-left: 5px;margin-right: 5px;"rowspan="2">异常笔数</th>
			    <th style="margin-left: 5px;margin-right: 5px;"rowspan="2">对账时间</th>
			    <th style="margin-left: 5px;margin-right: 5px;"rowspan="2">状态</th>
				<th style="margin-left: 5px;margin-right: 5px;width:60px"rowspan="2">操作</th>
			</tr>
			<tr>
				<th style="margin-left: 5px;margin-right: 5px;">订单总数(笔)</th>
				<th style="margin-left: 5px;margin-right: 5px;">交易总金额(元)</th>
				<th style="margin-left: 5px;margin-right: 5px;">退款总金额(元)</th>
				<th style="margin-left: 5px;margin-right: 5px;">订单总数(笔)</th>
				<th style="margin-left: 5px;margin-right: 5px;">交易总金额(元)</th>
				<th style="margin-left: 5px;margin-right: 5px;">退款总金额(元)</th>
				<th style="margin-left: 5px;margin-right: 5px;">订单总数(笔)</th>
				<th style="margin-left: 5px;margin-right: 5px;">交易总金额(元)</th>
				<th style="margin-left: 5px;margin-right: 5px;">退款总金额(元)</th>	
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
			 <s:iterator value="blist" var="blist" status="status">
             <tr>
               <td align="center">
                <input type="checkbox" name="ids" value="${bid}" class="box">
               </td> 
               <td align="center"><a href="javascript:findDetails('${billDate}','${accountCode }')">${billDate}</a></td>
               <td align="center">${accountName}</td>  
		       <td align="center">${total}</td>
		       <td align="center">${amount}</td>
		       <td align="center">${refund_amount}</td>
		       <td align="center">${total_out}</td>
		       <td align="center">${amount_out}</td>                 
		       <td align="center">${refund_amount_out}</td>  
		       <td align="center">${total_his}</td>
		       <td align="center">${amount_his}</td>                 
		       <td align="center">${refund_amount_his}</td>  
		       <s:if test="#blist.checkStatus==1"> <td align="center"><font color="red">异常</font></td></s:if>
		       <s:if test="#blist.checkStatus==0"> <td align="center">正常</td></s:if>
		       <td align="center">${abnormalNum}</td>
		       <td align="center">${createTime.substring(0,19)}</td>
		       <s:if test="#blist.status==0"> <td align="center">待确认</td></s:if>
		       <s:if test="#blist.status==1"> <td align="center">已确认</td></s:if>
		      <td align="center">
             	<s:if test='#blist.status==0'><a href="javascript:toConfirm('${bid}','${page.pageNo}','${startDate}','${endDate}','${cStatus}','${accountCode}','${handle}','${billDate}')">待确认</a></s:if>           
              	<s:if test='#blist.status==1'>--</s:if>       
              </td>       
             </tr>
           </s:iterator>
           		   <s:if test="%{page.pageCount == 0}"> <tr><td align="center" colspan="17" >暂无数据</td></tr></s:if>
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
		<a href="javascript:datalist_ctrl.loaddatalist('unpaid/checkBillAction_findAll?pageNo=1&startDate=${startDate}&endDate=${endDate}&checkStatus=${checkStatus}&accountCode=${accountCode}&status=${status}');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist('unpaid/checkBillAction_findAll?pageNo=${page.pageNo-1}&startDate=${startDate}&endDate=${endDate}&checkStatus=${checkStatus}&accountCode=${accountCode}&status=${status}');" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:datalist_ctrl.loaddatalist('unpaid/checkBillAction_findAll?pageNo=${page.pageNo+1}&startDate=${startDate}&endDate=${endDate}&checkStatus=${checkStatus}&accountCode=${accountCode}&status=${status}');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist('unpaid/checkBillAction_findAll?pageNo=${page.pageCount}&startDate=${startDate}&endDate=${endDate}&checkStatus=${checkStatus}&accountCode=${accountCode}&status=${status}');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>
</div>