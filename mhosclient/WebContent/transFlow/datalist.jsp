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
				<th width="5%">交易类型</th>
				<th width="9%">交易单号</th>
				<th width="9%">第三方交易流水号</th>
				<!-- <th width="9%">HIS交易流水号</th> -->
				<th width="7%">院区</th>
				<th width="7%">资金账户</th>
				<th width="7%">支付场景</th>
				<th width="6%">支付方式</th>
				<th width="7%">就诊卡号</th>
				<th width="6%">就诊人</th>
				<!-- <th width="6%">缴费项目</th> -->
				<th width="6%">交易金额(元)</th>
				<!-- <th width="7%">订单状态</th> -->
				<th width="9%">交易完成时间</th>
			    <th width="7%">操作</th>
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
			 <s:iterator value="tflist" var="tflist" status="status">
             <tr>
              <td align="center">${creatDate.substring(0,19)}</td> 
			 <td align="center">
			 	<s:if test='#tflist.transType==0'>支付</s:if>
			 	<s:if test='#tflist.transType==1'>退款</s:if>
			 </td>
             <td align="left"  style="word-wrap:break-word;">${outTradeNo}</a>
             <td align="left" style="word-wrap:break-word;">${transactionId}</td>   
             <%-- <td align="left" style="word-wrap:break-word;">${hisTransId}</td>    --%>
             <td align="center">${districtName}</td>
              <td align="center">${accountName }</td>
             <td align="center">${paySceneName }</td> 
             <td align="center" >${patternName }</td> 
             <td align="center">${cardNo}</td> 
             <td align="center">${patientName}</td> 
             <td align="center">${amount}</td> 
             <td align="center">${transTime.substring(0,19)}</td> 
               <td align="center">
               		<a href="javascript:toSelect('${orderId}')">查看</a>
               </td> 
             </tr>
           </s:iterator>
           <s:if test="%{page.pageCount == 0}">
			<tr><td colspan="15" align="center">暂无数据</td></tr>
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
		<a href="javascript:datalist_ctrl.loaddatalist1('1','${jyType }','${payType}','${startdate}','${enddate}','${pattern}','${districtId}','${payScene }','${account }');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageNo-1}','${jyType }','${payType}','${startdate}','${enddate}','${pattern}','${districtId}','${payScene }','${account }');" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageNo+1}','${jyType }','${payType}','${startdate}','${enddate}','${pattern}','${districtId}','${payScene }','${account }');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageCount}','${jyType }','${payType}','${startdate}','${enddate}','${pattern}','${districtId}','${payScene }','${account }');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>
</div>