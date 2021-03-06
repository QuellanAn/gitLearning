<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<input id="hid_totalcount" type="hidden" value="${page.totalCount }"/>
<input id="pageNo" type="hidden" value="${page.pageNo }"/>
<input id="hid_pattern" type="hidden" value="${pattern}"/>
<input id="hid_createDate" type="hidden" value="${createDate}"/>
<input id="hid_billStatus" type="hidden" value="${billStatus}"/>
	<script type="text/javascript">
		refresh_totalcount();
	</script>
	<table id="tb_cnt" class="list-table" style="margin-top: 5px;table-layout:fixed;">
		<thead>
			<tr>
			   <!--  <th style="margin-left: 2px;margin-right: 2px;"rowspan="2">
			    <input type="checkbox" name="checkbox" id="check" onclick="dosubmit('check.ids','check');"/>
			    </th> -->
			    <th style="margin-left: 5px;margin-right: 5px;"rowspan="2">交易时间</th>
				<th style="margin-left: 5px;margin-right: 5px;"rowspan="2">资金账户</th>
				<th style="margin-left: 5px;margin-right: 5px;"rowspan="2">交易单号</th>
				<th style="margin-left: 5px;margin-right: 5px;"rowspan="2">交易类型</th>
				<th style="margin-left: 5px;margin-right: 5px;background-color:#75cecc;"colspan="2"><font color="#FFFFFF">综合支付平台</font></th>
			    <th style="margin-left: 5px;margin-right: 5px;background-color:#fd7054;"colspan="2"><font color="#FFFFFF">第三方支付平台</font></th>
			    <th style="margin-left: 5px;margin-right: 5px;background-color:#6fbfe8;"colspan="2"><font color="#FFFFFF">HIS系统</font></th>
			    <th style="margin-left: 5px;margin-right: 5px;"rowspan="2">对账状态</th>
			    <th style="margin-left: 5px;margin-right: 5px;"rowspan="2">处理状态</th>
			    <th style="margin-left: 5px;margin-right: 5px;"rowspan="2">操作</th>
			</tr>
			<tr>
				<!-- <th style="margin-left: 5px;margin-right: 5px;">支付流水号</th> -->
				<th style="margin-left: 5px;margin-right: 5px;">交易金额(元)</th>
				<th style="margin-left: 5px;margin-right: 5px;">交易状态</th>
				<th style="margin-left: 5px;margin-right: 5px;">交易金额(元)</th>
				<th style="margin-left: 5px;margin-right: 5px;">交易状态</th>
				<th style="margin-left: 5px;margin-right: 5px;">交易金额(元)</th>
				<th style="margin-left: 5px;margin-right: 5px;">交易状态</th>
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
			 <s:iterator value="cbdList" var="cbdList" status="status">
             <tr>
            <%--  <td align="center">
                <input type="checkbox" name="check.ids" value="${out_trade_no}" class="box">
             </td>  --%>
             <td align="center" style="word-wrap:break-word;">${transTime.substring(0,19)}</td>  
              <td align="center" style="word-wrap:break-word;">${accountName}</td>  
             <td align="center" style="word-wrap:break-word;"><a href="unpaid/payOrderAction_findDetails?orderId=${orderId}">${orderCode}</a></td> 
             <td align="center">
               <s:if test='#cbdList.transType==0'>支付</s:if>
               <s:if test='#cbdList.transType==1'>退款</s:if>
              </td> 
           <%--   <td align="center" style="word-wrap:break-word;">${orderCode}</td>   --%>
             <td align="center">${actualPay/100.0}</td>  
              <td align="center">
              <s:if test='#cbdList.ptTransType==0'>支付成功</s:if>
            <%--   <s:elseif test='#cbdList.payStatus==0'><font color="#FF0000">失败</font></s:elseif>
              <s:elseif test='#cbdList.payStatus==3&&#cbdList.outStatus==1'>成功</s:elseif> --%>
              <s:elseif test='#cbdList.ptTransType==1'>退款成功</s:elseif>
           <%--    <s:elseif test='#cbdList.ptTransType==null&&#cbdList.payStatus==0'>未支付</s:elseif>
              <s:elseif test='#cbdList.ptTransType==null&&#cbdList.payStatus==1'>支付成功</s:elseif>
              <s:elseif test='#cbdList.ptTransType==null&&#cbdList.payStatus==2'>待退款</s:elseif> --%>
             </td>  
             <td align="center">${outAmount/100.0}</td>  
             <td align="center">
               <s:if test='#cbdList.outStatus==1'>支付成功</s:if>
               <s:if test='#cbdList.outStatus==0'><font color="#FF0000">失败</font></s:if>
               <s:if test='#cbdList.outStatus==3'>退款成功</s:if>
             </td>  
             <td align="center">
              <s:if test='#cbdList.hisAmount.equals("")||#cbdList.hisAmount!=null'> ${hisAmount/100.0}</s:if>
            </td>  
             <td align="center">
              <s:if test='#cbdList.hisStatus==1'>成功</s:if>
              <s:if test='#cbdList.hisStatus==0'><font color="#FF0000">失败</font></s:if>
             </td> 
             <td align="center">
              <s:if test='#cbdList.billStatus==1'>正常</s:if>
              <s:if test='#cbdList.billStatus==0'><font color="#FF0000">异常</font></s:if>
             </td> 
              <td align="center">
              <s:if test='#cbdList.billStatus==1&&#cbdList.handle==0'>--</s:if>
              <s:elseif test='#cbdList.handle==0'>未处理</s:elseif>
              <s:else>已处理</s:else>
             </td> 
             <td align="center">
              <s:if test='#cbdList.payStatus==3'> 
                <a href="javascript:findRefundDetails('${id}')">查看</a>
              </s:if>
              <s:else> 
              <a href="javascript:findDetails('${id}')">查看</a>
              </s:else>      
            <%--  <s:if test='#cbdList.billStatus==0&&#cbdList.handle==0'> --%>
             <a href="javascript:verify('${orderCode}','${page.pageNo}','','${a_createDate}','${a_billStatus}','${id}','${a_status}','${a_accountCode}')">处理</a>
            <%--  </s:if> --%>
           <%--   <s:if test='#cbdList.billStatus==0&&#cbdList.handle==1'>
             <a href="javascript:verify('${out_trade_no}','${page.pageNo}','','${createDate}','')">查看</a>
             </s:if> --%>
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
		<a href="javascript:datalist_ctrl.topage('1','${createDate}','${status}','${billStatus}','${accountCode}');" class="pagination">首页 </a>
		<a href="javascript:datalist_ctrl.topage('${page.pageNo-1}','${createDate}','${status}','${billStatus}','${accountCode}');" class="pagination">上页 </a>
	</s:if>
	<s:if test="%{page.pageNo == page.pageCount}">
		<a class="pagination">下页</a>
		<a class="pagination">末页</a>
	</s:if>
	<s:if test="%{page.pageNo != page.pageCount}">
		<a href="javascript:datalist_ctrl.topage('${page.pageNo+1}','${createDate}','${status}','${billStatus}','${accountCode}');" class="pagination">下页 </a>
		<a href="javascript:datalist_ctrl.topage('${page.pageCount}','${createDate}','${status}','${billStatus}','${accountCode}');" class="pagination">末页 </a>
	</s:if>
	&nbsp; <span class="pagination">共 ${page.pageNo}/${page.pageCount} 页
	</span>
</div>
</s:if>
</div>