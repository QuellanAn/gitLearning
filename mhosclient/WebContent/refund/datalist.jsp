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
			    <th style="margin-left: 5px;margin-right: 5px;">退款单号</th>
			    <th style="margin-left: 5px;margin-right: 5px;">第三退款流水号</th>
				<th style="margin-left: 5px;margin-right: 5px;">原交易单号</th>
			    <th style="margin-left: 5px;margin-right: 5px;">院区</th>
			    <th style="margin-left: 5px;margin-right: 5px;">资金账户</th>
			<!-- 	<th style="margin-left: 5px;margin-right: 5px;">实缴金额(元)</th> -->
				<!-- <th style="margin-left: 5px;margin-right: 5px;">病人姓名</th> -->
				<th style="margin-left: 5px;margin-right: 5px;">退款方</th>
			<!-- 	<th style="margin-left: 5px;margin-right: 5px;">支付类型</th> -->
			<!-- 	<th style="margin-left: 5px;margin-right: 5px;">更新时间</th> -->
			    <th style="margin-left: 5px;margin-right: 5px;">退款方式</th>
				<th style="margin-left: 5px;margin-right: 5px;">退款金额(元)</th>
				<th style="margin-left: 5px;margin-right: 5px;">退款状态</th>
			    <th style="margin-left: 5px;margin-right: 5px;">操作人</th>
			    <th style="margin-left: 5px;margin-right: 5px;">退款时间</th>
			    <th style="margin-left: 5px;margin-right: 5px;">操作</th>
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
            <s:iterator value="rfList" var="rfList" status="status">
             <tr>
             <td align="left" style="word-wrap:break-word;">${refundNo}</td>   
              <td align="left" style="word-wrap:break-word;">${refundId}</td> 
           <%--   <td align="left" style="word-wrap:break-word;"><a href="javascript:toSelect('${orderId}')")>${tradeNo}</td>  --%>
             <td align="left" style="word-wrap:break-word;">${tradeNo}</td> 
             <td align="center">${yqName}</td>
             <td align="center">${accountName}</td>
              <td align="center">
             <s:if test='#rfList.pattern==0'>微信</s:if>
             <s:if test='#rfList.pattern==1'>支付宝</s:if>
             </td> 
              <td align="center">
              <s:if test='#rfList.refundType==0'>自动退款</s:if>
              <s:if test='#rfList.refundType==1'>人工退款</s:if>
             </td> 
           <%--   <td align="center">${actualPay/100.0}</td> --%>
             <td align="center">${refundFee}</td> 
           <%--   <td align="center">${patientName}</td>  --%>
            
           <%--   <td align="center">
              <s:if test='#rfList.payType==0'>扫码支付</s:if>
              <s:if test='#rfList.payType==1'>刷卡支付</s:if>
             </td>  --%>
              <td align="center">
              <s:if test='#rfList.refundStatus==0'>退款失败</s:if>
              <s:if test='#rfList.refundStatus==1'>退款成功</s:if>
             </td>  
         <%--     <td align="center">${updateTime.substring(0,16)}</td>  --%>
              <td align="center">${operatorName}</td> 
              <td align="center">${refundTime.substring(0,19)}</td> 
              <td align="center"><a href="unpaid/refundAction_findDetails?tradeNo=${tradeNo}">查看</a></td> 
             </tr>
           </s:iterator> 
          <s:if test="%{page.pageCount == 0}"> <tr><td align="center" colspan="12" >暂无数据</td></tr></s:if>
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
		<a href="javascript:datalist_ctrl.loaddatalist1('1','${tradeNo}','${refundNo}','${operatorName}','${pattern}','${refundType}','${sq_startdate}','${sq_enddate}','${tk_startdate}','${tk_enddate}','${refundStatus}');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageNo-1}','${tradeNo}','${refundNo}','${operatorName}','${pattern}','${refundType}','${sq_startdate}','${sq_enddate}','${tk_startdate}','${tk_enddate}','${refundStatus}');" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageNo+1}','${tradeNo}','${refundNo}','${operatorName}','${pattern}','${refundType}','${sq_startdate}','${sq_enddate}','${tk_startdate}','${tk_enddate}','${refundStatus}');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageCount}','${tradeNo}','${refundNo}','${operatorName}','${pattern}','${refundType}','${sq_startdate}','${sq_enddate}','${tk_startdate}','${tk_enddate}','${refundStatus}');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>
</div>