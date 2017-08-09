<%@page import="com.catic.mobilehos.po.AppRegOrdersPO"%>
<%@page import="com.catic.mobilehos.po.PatientPO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>充值详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/hos_pub_info_main_sp.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="trade/trade_main.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_HOSINFO')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>

		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt"><a href="trade/trade_main.jsp">充值详情</a></li>
			</ul>
			<div class="tab-cnt">
				<div style="margin-left : 50px;width: 1000px;border: 1px solid #ccc;">
					<div style="background-color: #ccc;height: 42px;padding-left: 20px;">就诊人信息</div>
					<div style="width: 100%;height: 126px;">
						<div class="patient-feft1">
							<div class="patient-feft">
								<div style="height: 100%;float: left;width: 78px;">姓名   </div>
								<div class="patient-feft">${patientPO.patientName } </div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 78px;">身份证号   </div>
								<div class="patient-feft">${patientPO.identityCard } </div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 78px;">出生日期</div>
								<div class="patient-feft">
									${patientPO.birthday}
									<s:iterator var="patientPO" value="patientPOs">${fn:substring(patientPO.birthday, 0, 16) }</s:iterator>
								</div>
							</div>
							<div style="clear: both;"></div>
						</div>
						<div class="patient-feft1">
							<div class="patient-feft">
								<div style="height: 100%;float: left;width: 78px;">就诊卡   </div>
								<div class="patient-feft">
									<s:iterator var="tradePO" value="tradePOs">
										<td align="center" valign="middle"><s:property value="#tradePO.cardNo" /></td>
									</s:iterator> 
								</div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 78px;">手机号   </div>
								<div class="patient-feft">${patientPO.phone} </div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 78px;">性别   </div>
								<div class="patient-feft"><s:if test="#patientPO.sexCode==1">女</s:if> <s:else>男</s:else> </div>
							</div>
						</div>
					</div>
				</div>
				<div style="margin-left : 50px;width: 1000px;border: 1px solid #ccc;margin-top: 20px;">
					<div style="background-color: #ccc;height: 42px;padding-left: 20px;">充值详情</div>
					<div style="width: 100%;height: 126px;">
						<div class="patient-feft1">
							<div class="patient-feft">
								<div style="height: 100%;float: left;width: 78px;">商户订单号   </div>
								<div class="patient-feft">${tradePO.tradeNo } </div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 78px;">商品名称   </div>
								<div class="patient-feft">${tradePO.goodsName } </div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 78px;">交易时间</div>
								<div class="patient-feft">
									${fn:substring(tradePO.endDate, 0, 16) }
								</div>
							</div>
							<div style="clear: both;"></div>
						</div>
						<div class="patient-feft1">
							<div class="patient-feft">
								<div style="height: 100%;float: left;width: 78px;">交易流水号   </div>
								<div class="patient-feft">
									${tradePO.transactionId } 
								</div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 78px;">交易类型  </div>
								<div class="patient-feft">微信充值 </div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 78px;">货币类型   </div>
								<div class="patient-feft">${tradePO.feeType }  </div>
							</div>
						</div>
					</div>
				</div>
			
			</div>
		</div>

		<div class="f-clear"></div>
	</div>
		<div id="showPayInfo"></div>
		<div id="showPatients"></div>
</body>
</html>






