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
				<th width="9%">交易时间</th>
				<th width="10%">交易单号</th>
				<th width="9%">第三方交易流水号</th>
				<!-- <th width="9%">HIS交易流水号</th> -->
				<th width="7%">院区</th>
				<th width="7%">资金账户</th>
				<th width="7%">支付场景</th>
				<th width="7%">支付方式</th>
				<th width="7%">就诊卡号</th>
				<th width="7%">就诊人</th>
				<th width="7%">缴费项目</th>
				<th width="9%">交易金额(元)</th>
				<th width="7%">交易状态</th>
				<th width="9%">交易完成时间</th>
			    <th width="7%">操作</th>
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
			 <s:iterator value="polist" var="polist" status="status">
             <tr>
             <td align="center">${creatDate.substring(0,19)}</td> 
             <td align="left"  style="word-wrap:break-word;">${out_trade_no}</a>
             <td align="left" style="word-wrap:break-word;">${orderCode}</td>   
             <%-- <td align="left" style="word-wrap:break-word;">${hisTransId}</td>    --%>
             <td align="center">${districtName}</td>
             <td align="center">${accountName }</td>
             <td align="center">${paySceneName }</td> 
             <td align="center" >${patternName }</td> 
             <td align="center">${cardNo}</td> 
             <td align="center">${patientName}</td> 
             <td align="center">${body}</td>
             <td align="center">${fee}</td> 
             <td align="center">${payStatusName }</td>
            <td align="center">${time_expire.substring(0,19)}</td> 
         <!--     <td align="center">待支付</td>  -->
           <%--   <td align="center">${attach}</td>  --%>
               <td align="center">
                    <a href="javascript:toSelect('${orderId}')">查看</a>&nbsp;&nbsp;    
                    <s:if test='flag==1&&#polist.payStatus!=3'><a href="javascript:refund('${out_trade_no}','${fee}','${pattern}')">退款</a></s:if>
                    <%-- <s:elseif test='#polist.payStatus==3'><a href="unpaid/refundAction_findDetails?tradeNo=${out_trade_no}">&nbsp;查看退款单</a></s:elseif> --%>
               </td> 
             </tr>
           </s:iterator>
           <s:if test="%{page.pageCount == 0}">
			<tr><td colspan="14" align="center">暂无数据</td></tr>
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
		<a href="javascript:datalist_ctrl.loaddatalist1('1','${payType}','${startdate}','${enddate}','${status}','${pattern}','${districtId}','${out_trade_no}','${hisTransId}','${orderCode}','${body}','${payScene }','${account }');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageNo-1}','${payType}','${startdate}','${enddate}','${status}','${pattern}','${districtId}','${out_trade_no}','${hisTransId}','${orderCode}','${body}','${payScene }','${account }');" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
	<%-- 	<a href="javascript:datalist_ctrl.loaddatalist1('unpaid/payOrderAction_findAll?pageNo=${page.pageNo+1}&startdate=${startdate}&out_trade_no=${out_trade_no}&patientname=${patientname}&enddate=${enddate}&status=${status}&pattern=${pattern}&patientname=${patientname}&enddate=${enddate}&status=${status}&pattern=${pattern}&hisTransId=${hisTransId }&orderCode=${orderCode }');" class="pagination">下页 </a> --%>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageNo+1}','${payType}','${startdate}','${enddate}','${status}','${pattern}','${districtId}','${out_trade_no}','${hisTransId}','${orderCode}','${body}','${payScene }','${account }');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageCount}','${payType}','${startdate}','${enddate}','${status}','${pattern}','${districtId}','${out_trade_no}','${hisTransId}','${orderCode}','${body}','${payScene }','${account }');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>
</div>