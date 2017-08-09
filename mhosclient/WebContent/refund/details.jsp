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
<title>退款记录详情</title>
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
<!-- <s:if
	test="#session.userName==null">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li ><a href="../unpaid/payOrderAction_findDetails?orderId=${rf.orderId}">订单详情</a></li>
				<li class="tab-crt">退款记录详情</li>
				<li ><a href="../unpaid/payLogAction_findLog?out_trade_no=${rf.tradeNo}&orderId='${rf.orderId}'&payStatus=${rf.payStatus}">交易日志</a></li>
			</ul>
				<div class="f-fl left-list-wrap" style="margin-bottom: 10px;">
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
						<table class="list-table" style="table-layout:fixed;">
							<tr>
								<td align="center" style="color: blue;">
									<font size=4>退款信息</font>
								</td>
								<td colspan="3" style="color: blue;">
								</td>
							</tr>
							<tr>
								<td width="10%" align="center" style="background-color: #F2F2F2;">
								  退款单号
								</td>
								<td width="5%" style="word-wrap:break-word;">
								   ${rf.refundNo}
								</td>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
									第三方退款流水号
								</td>
								<td width="20%" style="word-wrap:break-word;">
									<%-- ${po.out_trade_no} --%>
									     ${rf.refundId}
								</td>
							<%-- 	<td width="20%" align="center">
								         支付流水号
								</td>
								<td width="20%" style="word-wrap:break-word;">
									${rf.orderCode}
								</td> --%>
							</tr>
							<tr>
							    <td width="20%" align="center" style="background-color: #F2F2F2;">
									原交易单号
								</td>
								<td width="20%px" style="word-wrap:break-word;">
									<%-- ${po.out_trade_no} --%>
									     ${rf.tradeNo}
								</td>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
									资金账户
								</td>
								<td width="20%" style="word-wrap:break-word;">
									<%-- ${po.out_trade_no} --%>
									${rf.accountName }    
								</td>
							
							</tr>
							<tr>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
								  退款院区
								</td>
								<td width="20%" style="word-wrap:break-word;">
									${rf.yqName }
								</td>
								<td width="10%" align="center" style="background-color: #F2F2F2;">
								  退款状态
								</td>
								<td width="5%" style="word-wrap:break-word;">
								   <s:if test='rf.refundStatus == 0'>退款失败</s:if>
								   <s:if test='rf.refundStatus == 1'>退款成功</s:if>
								</td>
							<%-- 	<td width="20%" align="center">
								   支付类型
								</td>
								<td width="20%">
									<s:if test='rf.payType == 0'>扫码支付</s:if>
									<s:if test='rf.payType == 1'>刷卡支付</s:if>
								</td> --%>
							
							<%-- 	<td width="10%" align="center">
								   缴费用户
								</td>
								<td width="10%">
									${rf.patientName}
								</td> --%>
							</tr>
							<tr>
						     	<td width="20%" align="center" style="background-color: #F2F2F2;">
									退款类型
								</td>
								<td width="20%">
									<s:if test='rf.pattern == 0'>微信</s:if>
									<s:if test='rf.pattern == 1'>支付宝</s:if>—原路返回
								</td>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
								        退款方式
								</td>
								<td width="20%">
									<s:if test='rf.refundType==0'>自动退款</s:if>
									<s:if test='rf.refundType==1'>手动退款</s:if>
								</td>
								
							</tr>
							<tr>
							    <td width="10%" align="center" style="background-color: #F2F2F2;">
								       操作人
								</td>
								<td width="10%">
									${rf.operatorName}
								</td>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
									退款金额(元)
								</td>
								<td width="20%">
								   ${rf.refundFee}
								</td>
							</tr>
							<tr>
								<td align="center" style="background-color: #F2F2F2;">
								  退款原因描述:
								</td>
								<td align="left" colspan="3">
								${rf.refundReason}
								</td>
							</tr>
							<tr>
							<%-- 	<td width="20%" align="center" style="background-color: #F2F2F2;">
									申请退款时间
								</td>
								<td width="20%">
									${po.creatDate.substring(0,16)}
									${rf.createTime.substring(0,19)}
								</td> --%>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
								 退款时间
								</td>
								<td width="20%">
									${rf.refundTime.substring(0,19)}
								</td>
							<%-- 	<td width="10%" align="center">
								  实缴金额(元)
								</td>
								<td width="10%">
								 ${rf.actualPay/100.0}
								</td> --%>
							</tr>
							<tr>
								<td align="center" style="color: blue;">
									<font size=4>缴费信息</font>
								</td>
								<td colspan="3" style="color: blue;">
								</td>
							</tr>
							<tr>
								<%-- <td width="20%" align="center">
									缴费单号
								</td>
								<td width="20%">
									${unpaidPO.doc_bill_sn}
								</td> --%>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
								       就诊卡号
								</td>
								<td width="20%">
									${rf.cardNo}
								</td>
								<td width="10%" align="center" style="background-color: #F2F2F2;">
								    就诊人
								</td>
								<td width="10%">
									${rf.patientName}
								</td>
							</tr>
							<tr>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
								       缴费项目
								</td>
								<td width="20%">
									<%-- ${po.name} --%>
									${rf.body}
								</td>
								<%-- <td width="20%" align="center" >
									病人ID
								</td>
								<td width="20%" style="word-wrap:break-word;">
									${unpaidPO.cardNo}
									${rf.patientId}
								</td> --%>
								<td width="20%" align="center" style="background-color: #F2F2F2;">
								  应缴金额（元）
								</td>
								<td width="20%">
									<%-- ${unpaidPO.money} --%>
									${rf.total_fee/100.0}
								</td>
								<%-- <td width="10%" align="center">
								   科室
								</td>
								<td width="10%">
									${unpaidPO.departmentName}
								</td>
							</tr>
							<tr>
								<td width="20%" align="center">
									开单医生
								</td>
								<td width="20%">
									${unpaidPO.doctorName}
								</td>
								<td width="20%" align="center">
								   开单日期
								</td>
								<td width="20%">
									${unpaidPO.create_date.substring(0,16)}
								</td>
								<td width="10%" align="center">
							
								</td>
								<td width="10%">
								
								</td>
							</tr> --%>
							<%-- 	<tr>
								<td align="center" style="color: blue;">
									<font size=4>执行信息</font>
								</td>
								<td colspan="5" style="color: blue;">
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									第三方支付
								</td>
								<td colspan="2" align="center">
								        支付平台
								</td>
								<td colspan="2" align="center">
								    HIS平台
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
								     <s:if test='rf.outStatus == 1'>支付成功</s:if>
								     <s:if test='rf.outStatus == 2'>已退款</s:if>
								     <s:if test='rf.payStatus == 3'>已退款</s:if>
								     <s:if test='rf.outStatus == 0'><font color="#FF0000">支付失败</font></s:if>
								</td>
								<td colspan="2" align="center">
								     <s:if test='rf.payStatus == 1'>支付成功</s:if>
								     <s:if test='rf.payStatus == 2'>退款中</s:if>
								     <s:if test='rf.payStatus == 3'>已退款</s:if>
								     <s:if test='rf.payStatus == 0'><font color="#FF0000">支付失败</font></s:if>
								</td>
								<td colspan="2" align="center">
								     <s:if test='rf.hisStatus == 1'>支付成功</s:if>
								     <s:if test='rf.hisStatus == 0'><font color="#FF0000">支付失败</font></s:if>
								</td>
							</tr> --%>
						</table>
					</div>
					<div class="f-clear"></div>
				</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>