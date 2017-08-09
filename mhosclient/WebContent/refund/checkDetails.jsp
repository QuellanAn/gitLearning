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
</head>
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
						<table class="list-table" style="table-layout:fixed;">
							<tr>
								<td width="10%" align="center" style="color: blue;">
									<font size=4>支付平台</font>
								</td>
								<td width="20%" style="color: blue;border-right: none;"></td>
								<td width="10%" style="color: blue;border-right: none;"></td>
								<td width="20%" style="color: blue;"></td>
								
							</tr>
							<tr>
								<td align="center" style="background-color: #F2F2F2;">
									 交易单号
								</td>
								<td style="word-wrap:break-word;">
									${po.out_trade_no}
								</td>
								<td align="center" style="background-color: #F2F2F2;">
								   交易状态
								</td>
								<td>
								    <s:if test='po.payStatus == 0'>待支付</s:if>
								    <s:if test='po.payStatus == 1'>支付成功</s:if>
								    <s:if test='po.payStatus == 2'>退款中</s:if>
								    <s:if test='po.payStatus == 3'>已退款</s:if>
								</td>
							</tr>
							<tr>
								<td align="center" style="background-color: #F2F2F2;">
								       缴费项目
								</td>
								<td>
									${po.body}
								</td>
								<td align="center" style="background-color: #F2F2F2;">
								交易金额
								</td>
								<td>
									${po.actualPay}
								</td>
							</tr>
							<tr>
								<td align="center" style="background-color: #F2F2F2;">
								交易创建时间
								</td>
								<td>
									${po.creatDate.substring(0,19)}
								</td>
								<td align="center" style="background-color: #F2F2F2;">
								  支付时间
								</td>
								<td>
									${po.time_expire.substring(0,19)}
								</td>
							</tr>
							<tr>
								<td align="center" style="background-color: #F2F2F2;">
								   订单更新时间
								</td>
								<td>
									${po.updateTime.substring(0,19)}
								</td>
							</tr>
							<tr>
								<td align="center" style="color: blue;">
									<font size=4>第三方支付平台</font>
								</td>
							</tr>
							<tr>
								<td align="center" style="background-color: #F2F2F2;">
									支付方式
								</td>
								<td>
									<s:if test='po.pattern == 0'>微信支付</s:if>
									<s:if test='po.pattern == 1'>支付宝支付</s:if>
								</td>
								<td align="center" style="background-color: #F2F2F2;">
								   支付场景
								</td>
								<td>
									<s:if test='po.payType == 0'>扫码支付</s:if>
									<s:if test='po.payType == 1'>刷卡支付</s:if>
								</td>
							</tr>
							<tr>
								<td align="center" style="background-color: #F2F2F2;">
								  第三方交易流水号
								</td>
								<td style="word-wrap:break-word;">
									${po.orderCode}
								</td>
								<td align="center" style="background-color: #F2F2F2;">
								   交易状态
								</td>
								<td>
								    <s:if test='po.payStatus == 0'>待支付</s:if>
								    <s:if test='po.payStatus == 1'>支付成功</s:if>
								    <s:if test='po.payStatus == 2'>退款中</s:if>
								    <s:if test='po.payStatus == 3'>已退款</s:if>
								</td>
							</tr>
							<tr>
								<td align="center" style="background-color: #F2F2F2;">
								 商品名称
								</td>
								<td style="word-wrap:break-word;">
									${po.orderCode}
								</td>
								<td align="center" style="background-color: #F2F2F2;">
								交易金额（元）
								</td>
								<td>
								  
								</td>
							</tr>
							<tr>
								<td align="center" style="background-color: #F2F2F2;">
								 交易时间
								</td>
								<td style="word-wrap:break-word;">
									${}
								</td>
               					
							</tr>
						</table>
					</div>
					<div class="f-clear"></div>
				</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$('.menu-item-cnt').attr("style", "display:none;");
	$('.menu-item-cnt:eq(1)').attr("style", "display:block");
	});
function refund(tradeNo,amount){
	/*window.location.href="payorder/refund.jsp";*/
	window.location.href="unpaid/refundAction_toRefund?tradeNo="+tradeNo+"&amount="+amount;
}
</script>
</html>