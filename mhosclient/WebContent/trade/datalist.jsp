<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<input id="hid_totalcount" type="hidden" value="${page.totalCount }"/>
	<script type="text/javascript">
		refresh_totalcount();
	</script>
	<table id="tb_cnt" class="list-table" style="margin-top: 5px;table-layout:fixed;">
		<thead>
			<tr>
				<th width="25%">交易单号</th>
				<th width="15%">就诊卡号</th>
				<th width="10%">就诊人</th>
				<th width="10%">充值途径</th>
				<th width="10%">充值金额(元)</th>
				<th width="10%">充值状态</th>
				<th width="20%">充值时间</th>
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
			 <s:iterator value="polist" var="polist" status="status">
             <tr>
             <td align="left"  style="word-wrap:break-word;"><a href="javascript:toSelect('${orderId}')">${out_trade_no}</a></td> 
             <td align="center">${cardNo}</td> 
             <td align="center">${patientName}</td> 
             <td align="center" >
	             ${paySceneName }
             </td> 
             <td align="center">${fee}</td> 
             <td align="center">
               ${payStatusName }
            </td>
             <td align="center">${fn:substring(creatDate, 0, 19) }</td> 
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
		<a href="javascript:datalist_ctrl.loaddatalist1('1','${payType}','${startdate}','${enddate}','${status}','${pattern}','${districtId}','${out_trade_no}','${hisTransId}','${orderCode}','${body}','${cardNo}','${patientName}','${payScene}');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageNo-1}','${payType}','${startdate}','${enddate}','${status}','${pattern}','${districtId}','${out_trade_no}','${hisTransId}','${orderCode}','${body}','${cardNo}','${patientName}','${payScene}');" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
	<%-- 	<a href="javascript:datalist_ctrl.loaddatalist1('unpaid/payOrderAction_findAll?pageNo=${page.pageNo+1}&startdate=${startdate}&out_trade_no=${out_trade_no}&patientname=${patientname}&enddate=${enddate}&status=${status}&pattern=${pattern}&patientname=${patientname}&enddate=${enddate}&status=${status}&pattern=${pattern}&hisTransId=${hisTransId }&orderCode=${orderCode }');" class="pagination">下页 </a> --%>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageNo+1}','${payType}','${startdate}','${enddate}','${status}','${pattern}','${districtId}','${out_trade_no}','${hisTransId}','${orderCode}','${body}','${cardNo}','${patientName}','${payScene}');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.loaddatalist1('${page.pageCount}','${payType}','${startdate}','${enddate}','${status}','${pattern}','${districtId}','${out_trade_no}','${hisTransId}','${orderCode}','${body}','${cardNo}','${patientName}','${payScene}');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>
</div>