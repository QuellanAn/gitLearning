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
			    <s:if test='pattern==0'>
				<th style="margin-left: 5px;margin-right: 5px;">微信退款流水号</th>
				</s:if>
				 <s:if test='pattern==1'>
				<th style="margin-left: 5px;margin-right: 5px;">支付宝退款流水号</th>
				</s:if>
				<th style="margin-left: 5px;margin-right: 5px;">本系统退款单号</th>
				<s:if test='pattern==0'>
				<th style="margin-left: 5px;margin-right: 5px;">微信交易流水号</th>
				</s:if>
				 <s:if test='pattern==1'>
				<th style="margin-left: 5px;margin-right: 5px;">支付宝交易流水号</th>
				</s:if>
				<th style="margin-left: 5px;margin-right: 5px;">本系统交易单号</th>
			<!--<th style="margin-left: 5px;margin-right: 5px;">退款渠道</th> -->
				<th style="margin-left: 5px;margin-right: 5px;">退款金额</th>
				<th style="margin-left: 5px;margin-right: 5px;">退款状态</th>
			    <th style="margin-left: 5px;margin-right: 5px;">退款完成时间</th>
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
		<s:if test="oqr==null">
			<tr><td colspan="7" align="center">暂无数据</td></tr>
		</s:if>	
		<s:else>
             <tr>
             <td align="center" style="word-wrap:break-word;">${oqr.refundId}</td> 
             <td align="center" style="word-wrap:break-word;">${oqr.refundNo}</td>   
             <td align="center" style="word-wrap:break-word;">${oqr.transactionId}</td> 
             <td align="center" style="word-wrap:break-word;">${oqr.outTradeNo}</td>   
           <%--   <td align="center">${oqr.refundType}</td>  --%>
             <td align="center">${oqr.refundAmount}</td> 
             <td align="center">${oqr.refundStatus}</td> 
             <td align="center">${oqr.refundTime}</td>           
             </tr>
           </s:else>
		</tbody>
	</table>
