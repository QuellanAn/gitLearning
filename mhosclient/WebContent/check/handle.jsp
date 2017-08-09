<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/main.css" />
<link rel="stylesheet" type="text/css" href="resources/css/dialog.css" />
<div id="div_cnt_table">
<s:hidden id="tradeNos" name="tradeNos"></s:hidden>
	<table id="tab-cnt" class="dialog-table" style="width: 100%; height: 100%;">
		<tbody id="tb_cnt_body">
			<s:if test="%{#session.manager.roleId == 1}">
			<!-- <tr>
				<td align="center" colspan="4">
					<p style="margin-top: 8px;"><input type="checkbox" name="customer.parentId" id="check" value="0" />
					&nbsp;&nbsp;是否一级机构</p>
				</td>
			</tr>-->
			</s:if> 
			<tr>
				<td align="center"  width="50%">
					<p style="margin-top: 8px;"></font>对账状态</p>
				</td>
				<td align="left"  width="50%" ><!-- colspan="3" -->
				
				  <s:if test='checkBillDetails.billStatus==1'>正常</s:if>
				  <s:if test='checkBillDetails.billStatus==0'><font color="#FF0000">异常</font></s:if>
				</td>		
			<tr>			
				<td align="center" id="td1"  width="50%">
					<p style="margin-top: 8px;">支付平台</p>
				</td>
				<td align="left"  width="50%"><!-- colspan="3" -->
		          <s:if test='checkBillDetails.payStatus==0'>未支付</s:if>
                  <s:if test='checkBillDetails.payStatus==1'>支付成功</s:if>
                  <s:if test='checkBillDetails.payStatus==3'>已退款</s:if>
				</td>
			</tr>
			<tr>
				<td align="center"  width="50%">
					<p style="margin-top: 8px;">第三方支付平台</p>
				</td>
				<td align="left"  width="50%">
				  <s:if test='checkBillDetails.outStatus==0'>未支付</s:if>
				  <s:if test='checkBillDetails.outStatus==1'>支付成功</s:if>
				  <s:if test='checkBillDetails.outStatus==3'>已退款</s:if>
				</td>
			</tr>
			<tr>
				<td align="center"  width="50%">
				<p style="margin-top: 8px;">HIS平台</p>
				</td>
				<td align="left"  width="50%">
				  <s:if test='checkBillDetails.hisStatus==0'>未支付</s:if>
                  <s:if test='checkBillDetails.hisStatus==1'><font color="#FF0000">支付成功</font></s:if>
				</td>
			</tr>
	     </tbody>
	</table>
	<p>
  <s:if test='checkBillDetails.billStatus==0&&checkBillDetails.outStatus==1'>
  <font color="#FF0000">注：</font><font size="2px">
           当前订单的对账状态已校验完成。请您核对该异常订单在各个平台内的情况，并输入核对后的异常原因。点击左侧【退款】按钮执行退款操作，如果您不需要进行退款操作，请点右上方【×】关闭窗口。</font>
  </s:if>
<s:if test='checkBillDetails.billStatus==0&&checkBillDetails.outStatus==3&&checkBillDetails.payStatus!=3'>
  <font color="#FF0000">注：</font><font size="2px">
           当前订单的对账状态已校验完成。请您核对该异常订单在各个平台内的情况，并输入核对后的异常原因。点击右侧【更新处理状态】按钮，系统将会为您自动更新对账单的处理状态。</font>
  </s:if>
  <s:if test='checkBillDetails.billStatus==1'>
  <font color="#FF0000">注：</font><font size="4px">
          当前订单的对账状态已校验完成，无需进行异常处理操作。</font>
  </s:if>
</p>
<s:if test='checkBillDetails.billStatus==0'>
				<p><font color="red">*</font>异常原因     <s:textfield id="refundReason"  style="width:80%;" /></p>	
 </s:if>
<div align="center">	
<!-- 			    <s:if test='checkBillDetails.billStatus==0&&checkBillDetails.outStatus==1'> -->
				
<!-- 				</s:if> -->
	              <input id="button" type="button" value="退款" class="normal-btn" onclick="javascript:refund('${checkBillDetails.orderCode}','${checkBillDetails.outAmount/100.0}','${checkBillDetails.id}','${a_createDate}','${a_accountCode }','${a_status }','${a_billStatus}')" />
				  <s:if test='checkBillDetails.billStatus==0&&checkBillDetails.outStatus==3&&checkBillDetails.payStatus!=3'>			
					<input type="button" value="更新处理状态" class="normal-btn" onclick="javascript:button1(javascript:button2('${checkBillDetails.id}','${a_createDate}','${checkBillDetails.payStatus}','${a_accountCode }','${a_status }','${a_billStatus}')"  style="margin-right:20px;"/>
				<%-- <input type="button" value="取消" class="normal-btn" onclick="javascript:upStatus('${checkBillDetails.id}','${createDate}')" />		 --%>
				</s:if>
				 <s:if test='checkBillDetails.billStatus==1'>			
					<input type="button" value="确认" class="normal-btn" onclick="javascript:button2('${checkBillDetails.id}','${a_createDate}','${checkBillDetails.payStatus}','${a_accountCode }','${a_status }','${a_billStatus}')" />
				</s:if>
</div>
<%-- 			<tr>
			<td style="border: none">123</td>
			</tr>
		    <s:if test='checkBillDetails.billStatus==0'>
			<tr>
				<td align="center">
				<p style="margin-top: 8px;">异常原因</p>
				</td>
				<td align="left">
					<s:textfield id="refundReason" name="" cssClass="fm-text f-fl" style="width:90%;" />
				</td>
			</tr>
			 </s:if>
			<tr>
			    <s:if test='heckBillDetails.outStatus==1'>
				<td align="center" colspan="2">
					<input id="button" type="button" value="退款" class="normal-btn" onclick="javascript:refund('${checkBillDetails.orderCode}','${checkBillDetails.actualPay/100.0}')" />
				</td>
				</s:if>
				  <s:if test='checkBillDetails.outStatus==3&&checkBillDetails.payStatus!=3'>
				<td align="right" colspan="2" >		
					<input type="button" value="更新处理状态" class="normal-btn" onclick="javascript:upStatus('${checkBillDetails.id}','${createDate}')"  style="margin-right:20px;"/>
					<input type="button" value="取消" class="normal-btn" onclick="javascript:upStatus('${checkBillDetails.id}','${createDate}')" />
				</td>
				</s:if>
			</tr> --%>
</div>
<script type="text/javascript">
function refund(tradeNo,amount,checkDetailsId,createDate,accountCode,status,billStatus){
    var remark=$("#refundReason").val();
  /*   if(remark.length==0){
		alert("请输入异常原因");
		return;
	} */
	window.location.href="unpaid/checkBillAction_toRefund?flag=5&outTradeNo="+tradeNo+"&amount="+amount+"&remark="+remark+"&id="+checkDetailsId+"&createDate="+createDate+"&accountCode="+accountCode+"&status="+status+"billStatus"+billStatus;
};
function upStatus(id,createDate,payStatus,accountCode,status,billStatus){
var remark=$("#refundReason").val();
var tradeNos=$("#tradeNos").val();
		$.post('unpaid/checkBillAction_upStatus',{
		id:id,
		remark:remark,
		tradeNos:tradeNos,
		payStatus:payStatus,
 		accountCode:accountCode
	},
		function(data, status){
		var obj=eval(data);
			if (obj.code==0){
				alert("处理成功");
			window.location.href="unpaid/checkBillAction_toFindDetails?createDate="+createDate+"&accountCode="+accountCode+"&status="+status+"&billStatus="+billStatus;
			}else if (data){
			  	alert("处理失败"); 
			}
		});
	};
function button1(id,createDate,payStatus,accountCode){
var remark=$("#refundReason").val();
if(remark.length==0){
alert("请输入异常原因");
return;
}
upStatus(id,createDate,payStatus,accountCode);
};
function button2(id,createDate,payStatus,accountCode){
upStatus(id,createDate,payStatus,accountCode);
};
</script>
