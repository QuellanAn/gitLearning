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
	<table id="tb_cnt" class="list-table" style="margin-top: 5px;">
		<thead>
		    <tr>
		     <th style="margin-left: 5px;margin-right: 5px;border-right:none">
		      <s:if test='%{pattern.equals("0")}'>微信</s:if>
		      <s:elseif test='%{pattern.equals("1")}'>支付宝</s:elseif>
		      <s:else>全部 </s:else>
		   </th>
		     <th style="margin-left: 5px;margin-right: 5px;border-left: none" colspan="12"></th>
		    </tr>
			<tr>
				<th style="margin-left: 5px;margin-right: 5px;"rowspan="2">账单日期</th>
			    <th style="margin-left: 5px;margin-right: 5px;background-color:#75cecc;"colspan="3"><font color="#FFFFFF">支付平台</font></th>
			    <th style="margin-left: 5px;margin-right: 5px;background-color:#fd7054;"colspan="3"><font color="#FFFFFF">第三方支付平台</font></th>
			    <th style="margin-left: 5px;margin-right: 5px;background-color:#6fbfe8;"colspan="3"><font color="#FFFFFF">HIS支付平台</font></th>
			    <th style="margin-left: 5px;margin-right: 5px;"rowspan="2">对账时间</th>
			    <th style="margin-left: 5px;margin-right: 5px;"rowspan="2">对账状态</th>
			    <s:if test='%{pattern==null||pattern.equals("")}'>
				<th style="margin-left: 5px;margin-right: 5px;width:60px"rowspan="2">操作${pattern}</th>
				</s:if>
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
			 <s:iterator value="cbList" var="cbList" status="status">
             <tr>
             <td align="center"><a href="javascript:findDetails('${billDate}')">${billDate}</a></td>
             <s:if test='%{total_w!=null}'>
               <td align="center">${total_w}</td>  
		       <td align="center">${amount_w}</td>
		       <td align="center">${refund_amount_w}</td>
		       <td align="center">${w_total}</td>
		       <td align="center">${w_amount}</td>
		       <td align="center">${w_refund_amount}</td>           
               </s:if>
		      <s:elseif test='%{total_a!=null}'>
		       <td align="center">${total_a}</td>  
		       <td align="center">${amount_a}</td>
		       <td align="center">${refund_amount_a}</td>
		       <td align="center">${a_total}</td>
		       <td align="center">${a_amount}</td>
		       <td align="center">${a_refund_amount}</td> 
		      
		      </s:elseif>
		      <s:else>
		       <td align="center">${total}</td>  
		       <td align="center">${amount}</td>
		       <td align="center">${refund_amount}</td>
		       <td align="center">${total_out}</td>
		       <td align="center">${amount_out}</td>
		       <td align="center">${refund_amount_out}</td> 
		       </s:else>    
             <td align="center">${total}</td>  
             <td align="center">${amount}</td> 
             <td align="center">${outAmout}</td> 
           <%--   <td align="center">${hisAmount}</td>  --%>
             <td align="center">${createTime.substring(0,19)}</td> 
             <td align="center">
              <s:if test='#cbList.billStatus==0'>正常</s:if>
              <s:if test='#cbList.billStatus==1'><font color="#FF0000">异常</font></s:if>
             </td> 
             <s:if test="%{total_a==null&&total_w==null}">
             <td align="center">
             <s:if test='#cbList.status==0'><a href="javascript:toConfirm('${id}','${page.pageNo}','${startdate}','${enddate}','${billStatus}','${billDate}')">待确认</a></s:if>
             <s:if test='#cbList.status==1'>已确认</s:if>
            </s:if>
             </td> 
             </tr>
           </s:iterator>
           		   <s:if test="%{page.pageCount == 0}"> <tr><td align="center" colspan="13" >暂无数据</td></tr></s:if>
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
		<a href="javascript:datalist_ctrl.loaddatalist('unpaid/checkBillAction_findAll?pageNo=1&startdate=${startdate}&enddate=${enddate}&billStatus=${billStatus}');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist('unpaid/checkBillAction_findAll?pageNo=${page.pageNo-1}&startdate=${startdate}&enddate=${enddate}&billStatus=${billStatus}');" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:datalist_ctrl.loaddatalist('unpaid/checkBillAction_findAll?pageNo=${page.pageNo+1}&startdate=${startdate}&enddate=${enddate}&billStatus=${billStatus}');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist('unpaid/checkBillAction_findAll?pageNo=${page.pageCount}&startdate=${startdate}&enddate=${enddate}&billStatus=${billStatus}');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>
</div>