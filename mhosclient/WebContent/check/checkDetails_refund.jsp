<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>
<base href="<%=basePath%>/" />
<title>交易订单详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
</head>
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">对账单详情</li>
			</ul>
				<div class="f-fl left-list-wrap">
					<div id="div_cnt_table">
						<div class="left-list-hd">
					<table width="100%" height="100%" style="margin-top: 0px;">
							<tr >	
								<td align="right" width="60">
									<button class="save-btn" onclick="history.go(-1)">返回</button>
								</td>
							</tr>
						</table>
					</div>
					<span><b><font color="blue"  size=5>|</font></b>&nbsp;&nbsp;<font  size=5>对账信息</font></span>
					
					<table width="100%" class="list-table" style="table-layout:fixed;">
					     <tr><td width="20%" align="center" style="color: blue;">
									<font size=4>对账信息</font>
							 </td>
							 <td width="auto" align="center" style="color: blue;" ></td>
							 <td width="20%" align="center" style="color: blue;" ></td>
							 <td width="30%" align="center" style="color: blue;" ></td>
					     </tr>
					     <tr>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
									 对账状态
								</td>
								<td width="30%"style="word-wrap:break-word;">
									<s:if test="checkBillDetails.billStatus.equals(0)">异常</s:if>
									<s:if test="checkBillDetails.billStatus.equals(1)">正常</s:if>
								</td>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
								  处理操作
								</td>
								<td width="30%">${cr.taskState}
								<s:if test='cr.taskState.equals("")||cr.taskState==null'>--</s:if>
								</td>
							</tr>
							  <tr>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
									 处理状态
								</td>
								<td width="30%"style="word-wrap:break-word;">
									${cr.managerStatus}
								</td>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
								  处理备注
								</td>
								<td width="30%">	${cr.remark}</td>
							</tr>
							 <tr>
								<td width="20%" align="center" style="background-color: #F2F2F2; ">
									 处理时间
								</td>
								<td width="30%"style="word-wrap:break-word;border-right-width:0px;" >
									${cr.executionTime}
								</td>
								<td width="50%" align="center"  colspan="2">
								</td>
								
							</tr>
					</table>
						<span><b><font color="blue"  size=5>|</font></b>&nbsp;&nbsp;<font  size=5>各支付账单信息</font></span>
							<table class="list-table" style="table-layout:fixed;" width="100%">
							<tr>
								<td width="20%" align="center" style="color: blue;">
									<font size=4>综合支付平台</font>
								</td>
								<td width="30%" style="color: blue;border-right: none;"></td>
								<td width="20%" style="color: blue;border-right: none;"></td>
								<td width="30%" style="color: blue;"></td>
								
							</tr>
							<s:if test='flag.equals("4")'>
							<tr>
								<td align="center" style="background-color: #F2F2F2;" width="20%">
									 退款单号
								</td>
								<td style="word-wrap:break-word;" width="30%">
									${rf.tradeNo}
								</td>
								<td align="center" style="background-color: #F2F2F2;" width="20%">
								  退款状态
								</td>
								<td width="30%">
								    <s:if test='rf.payStatus == 0'>待支付</s:if>
								    <s:if test='rf.payStatus == 1'>支付成功</s:if>
								    <s:if test='rf.payStatus == 2'>退款中</s:if>
								    <s:if test='rf.payStatus == 3'>已退款</s:if>
								</td>
							</tr>
							<tr>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
								        退款方式
								</td>
								<td width="30%">
									<s:if test='rf.refundType==0'>自动退款</s:if>
									<s:if test='rf.refundType==1'>手动退款</s:if>
								</td>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
									退款类型
								</td>
								<td width="30%">
									<s:if test='rf.pattern == 0'>微信</s:if>
									<s:if test='rf.pattern == 1'>支付宝</s:if>—原路返回
								</td>
							</tr>
							<tr>
							   <td width="20%" align="center" style="background-color: #F2F2F2;">
									退款项目(元)
								</td>
								<td width="30%">
								   ${rf.refundFee}
								</td>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
									退款金额(元)
								</td>
								<td width="30%">
								   ${rf.refundFee}
								</td>
							</tr>
							<tr>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
								       操作人
								</td>
								<td width="30%" >
								   <s:if test='#rf.operatorName.equals("")&&#rf.operatorName==null'>--</s:if>
								   <s:else>${rf.operatorName}</s:else>
								</td>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
								  退款原因描述:
								</td>
								<td align="left" colspan="1" width="30%">
								${rf.refundReason}
								</td>
							</tr>
							<tr>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
									申请退款时间
								</td>
								<td width="30%">
									<%-- ${po.creatDate.substring(0,16)} --%>
									${rf.createTime.substring(0,19)}
								</td>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
								 退款完成时间
								</td>
								<td width="30%">
									${rf.refundTime.substring(0,19)}
								</td>
							</tr>
							</s:if>
							<s:if test='flag.equals("3")'>
								<tr>
								<td align="center" style="background-color: #F2F2F2;" width="20%">
									 交易单号
								</td>
								<td style="word-wrap:break-word;" width="30%">
									${po.out_trade_no}
								</td>
								<td align="center" style="background-color: #F2F2F2;"width="20%">
								   交易状态
								</td>
								<td width="30%">
								 <%--    <s:if test='po.payStatus == 0'>待支付</s:if>
								    <s:if test='po.payStatus == 1'>支付成功</s:if>
								    <s:if test='po.payStatus == 2'>退款中</s:if>
								    <s:if test='po.payStatus == 3'>已退款</s:if> --%>
								    <s:if test='checkBillDetails.transType == 0'>支付成功</s:if> 
								    <s:if test='checkBillDetails.transType == 1'>已退款</s:if> 
								</td>
							</tr>
							<tr>
								<td align="center" style="background-color: #F2F2F2;" width="20%">
								       缴费项目
								</td>
								<td width="30%">
									${po.body}
								</td>
								<td align="center" style="background-color: #F2F2F2;" width="20%">
								交易金额(元)
								</td>
								<td width="30%">
									${po.actualPay/100.0}
								</td>
							</tr>
							<tr>
								<td align="center" style="background-color: #F2F2F2;" width="20%">
								交易创建时间
								</td>
								<td width="30%">
									${po.creatDate.substring(0,19)}
								</td>
								<td align="center" style="background-color: #F2F2F2;" width="20%">
								  支付时间
								</td>
								<td width="30%">
									${po.time_expire.substring(0,19)}
								</td>
							</tr>
							<tr>
								<td align="center" style="background-color: #F2F2F2;" width="20%">
								   订单更新时间
								</td>
								<td style="border-right: none; " width="20%">
									${po.updateTime.substring(0,19)}
								</td>
								<td align="center" colspan="2"/>
								 
							</tr>
							</s:if>
							<tr>
								<td   width="20%" align="center" style="color: blue;">
									<font size=4>第三方支付平台</font>
								</td>
								<td width="30%" style="color: blue;border-right: none;"></td>
								<td width="20%" style="color: blue;border-right: none;"></td>
								<td width="30%" style="color: blue;"></td>
							</tr>
					       <s:if test='po.payStatus!=3'>
							<tr >
								<td   width="20%" align="center" style="background-color: #F2F2F2;">
									支付方式
								</td>
								<td  width="30%">
									<s:if test='po.pattern == 0'>微信支付</s:if>
									<s:if test='po.pattern == 1'>支付宝支付</s:if>
								</td>
								<td   width="20%" align="center" style="background-color: #F2F2F2;">
								   支付场景
								</td>
								<td  width="30%" >
									<s:if test='po.payType == 0'>扫码支付</s:if>
									<s:if test='po.payType == 1'>刷卡支付</s:if>
								</td>
							</tr>
							<tr>
								<td   width="20%" align="center" style="background-color: #F2F2F2;">
								  第三方交易流水号
								</td>
								<td  width="30%" style="word-wrap:break-word;">
									${po.orderCode}
								</td>
								<td  width="20%" align="center" style="background-color: #F2F2F2;">
								   交易状态
								</td>
								<td  width="30%">
								    <s:if test='tradeState.equals("NOTPAY")'>待支付</s:if>
								    <s:if test='tradeState.equals("SUCCESS")'>支付成功</s:if>
								    <s:if test='tradeState.equals("REFUND")'>转入退款</s:if>
								    <s:if test='tradeState.equals("FAILED")'>订单支付失败或被关闭</s:if>
								</td>
							</tr>
							<tr>
								<td  width="20%" align="center" style="background-color: #F2F2F2;">
								 商品名称
								</td>
								<td  width="30%" style="word-wrap:break-word;">
										${po.body}
								</td>
								<td  width="20%" align="center" style="background-color: #F2F2F2;">
								交易金额（元）
								</td>
								<td  width="30%">
								 ${totalFee/100.0}								 
								</td>
							</tr>
							<tr>
								<td  width="20%" align="center" style="background-color: #F2F2F2;">
								 交易时间
								</td>
								<td  width="30%" style="word-wrap:break-word;border-right: none;">
									${po.time_expire.substring(0,19)}	
               					<td align="center" colspan="2"/>
							</tr>
							</s:if>	
							<s:if test='(!refund_id.equals("")&&refund_id!=null)||pattern == 1'>
							<tr>
								<td  width="20%" align="center" style="background-color: #F2F2F2;">
									第三方退款流水号
								</td>
								<td  width="30%">
								${refund_id }
								</td>
								<td  width="20%" align="center" style="background-color: #F2F2F2;">
								   退款状态
								</td>
								<td  width="30%">
								  <s:if test='refundStatus.equals("SUCCESS")'>退款成功</s:if>
								  <s:else>退款失败</s:else>
								</td>
							</tr>
							<tr>
								<td  width="20%" align="center" style="background-color: #F2F2F2;">
								  退款类型
								</td>
								<td width="30%">
									<s:if test='pattern == 0'>微信</s:if>
									<s:if test='pattern == 1'>支付宝</s:if>—原路返回
								</td>
								<td  width="20%" align="center" style="background-color: #F2F2F2;">
								   退款金额(元)
								</td>
								<td  width="30%">
<!-- 							     <s:if test='pattern == 0'> -->
<!-- 								 ${refundFee/100.00 } -->
<!-- 								 </s:if> -->
<!-- 								 <s:else>  -->
								 ${refundFee}
<!-- 								 </s:else> -->
								</td>
							</tr>
							<tr>
								<td   width="20%" align="center" style="background-color: #F2F2F2;">
								退款完成时间
								</td>
								<td   width="30%" style="border-right: none;">
								${refund_success_time }
								</td>
								<td width="50%" colspan="2"></td>
								</tr>
								</s:if>
						</table>
					</div>
					<div class="f-clear"></div>
				</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>