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
	<script type="text/javascript">
	function refund(){	    	
	    var str=($("#content").val());
	   	document.write("<form action=unpaid/refundAction_refund method=post name=form1 style='display:none'>");
		document.write("<input type=hidden name=tradeNo value='${po.out_trade_no}'/>");
	    document.write("<input type=hidden name=cardNo  value='${po.cardNo}'/>");
        document.write("<input type=hidden name=amount  value='${po.actualPay/100.0}'/>");
		document.write("<input type=hidden name=refundReason  value='"+str+"'/>");
		document.write("<input type=hidden name=pattern  value='${po.pattern}'/>");
	    document.write("</form>");
	    document.form1.submit();
	//    window.location.href("unpaid/refundAction_refund?trdeNo='"+out_trade_no+"'&cardNo='"+cardNo+"'&amount="+total_fee+"&refundReason='"+str+"'");
	}
	</script>
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">退款记录详情</li>
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
								<td align="center" style="color: blue;">
									<font size=4>订单信息</font>
								</td>
								<td colspan="5" style="color: blue;">
								</td>
							</tr>
							<tr>
								<td width="20%px" align="center">
									订单号
								</td>
								<td width="20%px" style="word-wrap:break-word;">
									${po.out_trade_no}
								</td>
								<td width="20%" align="center">
								   支付流水号
								</td>
								<td width="20%" style="word-wrap:break-word;">
									${po.orderCode}
								</td>
								<td width="10%" align="center">
								   支付状态
								</td>
								<td width="5%">
								    <s:if test='po.payStatus == 0'>待支付</s:if>
								    <s:if test='po.payStatus == 1'>支付成功</s:if>
								</td>
							</tr>
							<tr>
								<td width="20%" align="center">
									支付方式
								</td>
								<td width="20%">
									<s:if test='po.pattern == 0'>微信支付</s:if>
									<s:if test='po.pattern == 1'>支付宝支付</s:if>
								</td>
								<td width="20%" align="center">
								   支付类型
								</td>
								<td width="20%">
									<s:if test='po.payType == 0'>扫码支付</s:if>
								</td>
								<td width="10%" align="center">
								   缴费用户
								</td>
								<td width="10%">
									${po.patientName}
								</td>
							</tr>
							<tr>
								<td width="20%" align="center">
									订单产生时间
								</td>
								<td width="20%">
									${po.creatDate.substring(0,16)}
								</td>
								<td width="20%" align="center">
								   订单更新时间
								</td>
								<td width="20%">
									${po.updateTime.substring(0,16)}
								</td>
								<td width="10%" align="center">
								  实缴金额(元)
								</td>
								<td width="10%">
									 ${po.actualPay/100.0}
								</td>
							</tr>
							<tr>
								<td align="center" style="color: blue;">
									<font size=4>缴费单信息</font>
								</td>
								<td colspan="5" style="color: blue;">
								</td>
							</tr>
							<tr>
								<td width="20%" align="center">
									缴费单号
								</td>
								<td width="20%">
									${unpaid.doc_bill_sn}
								</td>
								<td width="20%" align="center">
								       缴费项目
								</td>
								<td width="20%">
									${unpaid.name}
								</td>
								<td width="10%" align="center">
								   就诊人
								</td>
								<td width="10%">
									${unpaid.patientName}
								</td>
							</tr>
							<tr>
								<td width="20%" align="center" >
									病人ID
								</td>
								<td width="20%" style="word-wrap:break-word;">
									${unpaid.cardNo}
								</td>
								<td width="20%" align="center">
								  应缴金额（元）
								</td>
								<td width="20%">
									${unpaid.money}
								</td>
								<td width="10%" align="center">
								   科室
								</td>
								<td width="10%">
									${unpaid.departmentName}
								</td>
							</tr>
							<tr>
								<td width="20%" align="center">
									开单医生
								</td>
								<td width="20%">
									${unpaid.doctorName}
								</td>
								<td width="20%" align="center">
								   开单日期
								</td>
								<td width="20%">
									${unpaid.create_date.substring(0,16)}
								</td>
								<td width="10%" align="center">
							
								</td>
								<td width="10%">
								
								</td>
							</tr>
								<tr>
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
									  <s:if test='po.payStatus ==1'>支付成功</s:if>
									  <s:if test='po.payStatus ==0'><font color="#FF0000">支付失败</font></s:if>
								</td>
								<td colspan="2" align="center">
								      <s:if test='po.payStatus == 1'>支付成功</s:if>
								</td>
								<td colspan="2" align="center">
								      <s:if test='po.hisStatus == 1'>支付成功</s:if>
								      <s:if test='po.hisStatus == 0'><font color="#FF0000">支付失败</font></s:if>
								</td>
							</tr>
							<tr>					
							<td colspan="6" align="left">
							    <font color="#FF0000">结论</font>:&nbsp;&nbsp;<s:if test='po.hisStatus == 0&& po.payStatus==1'>符合退款条件</s:if>
							 </td>
							</tr>
							<tr>
							<td  align="center">退款原因描述：</td>
							<td colspan="4" align="center">
							     <textarea rows="2" cols="70" id="content"></textarea>	
								</td>
							<td  align="center"><button class="save-btn" onclick="javascript:refund(${po.out_trade_no})">退款</button></td>
							</tr>
						</table>
					</div>
					<div class="f-clear"></div>
				</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>