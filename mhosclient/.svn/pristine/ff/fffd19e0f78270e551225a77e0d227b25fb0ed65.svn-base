<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.catic.mobilehos.po.UserPO"%>
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
<title>预约详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="resources/js/common.js"></script>
<script type="text/javascript" src="busrecord/appreg/app_reg_main.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_HOSINFO')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>

		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt"><a href="busrecord/appreg/app_reg_main.jsp">预约详情</a></li>
			</ul>
			<div class="tab-cnt" id="tab-cnt" style="padding-bottom: 10px;">
				<div style="margin-left : 50px;width: 1040px;border: 1px solid #ccc;">
					<div style="background-color: #f3f3f3;height: 42px;padding-left: 20px;">就诊人信息</div>
					<div style="width: 100%;height: 126px;">
						<div class="patient-feft1">
							<div class="patient-feft">
								<div style="height: 100%;float: left;width: 56px;">就诊卡号   </div>
								<div class="patient-feft">${patientPO.cardNo }</div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 56px;">性别   </div>
								<div class="patient-feft"><s:if test="#patientPO.sexCode==1">女</s:if> <s:else>男</s:else> </div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 56px;">手机号   </div>
								<div class="patient-feft">${patientPO.phone } </div>
							</div>
							<div style="clear: both;"></div>
						</div>
						<div class="patient-feft1">
							<div class="patient-feft">
								<div style="height: 100%;float: left;width: 87px;">姓名   </div>
								<div class="patient-feft">${patientPO.patientName } </div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 87px;">年龄 </div>
								<div class="patient-feft">
									<s:iterator var="patientPO" value="patientPOs">${fn:substring(patientPO.birthday, 0, 16) }</s:iterator>
								</div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 87px;">身份证号   </div>
								<div class="patient-feft">${patientPO.identityCard } </div>
							</div>
						</div>
					</div>
				</div>
				<div style="margin-left : 50px;width: 1040px;border: 1px solid #ccc;margin-top: 20px;">
					<div style="background-color: #f3f3f3;height: 42px;padding-left: 20px;">预约基本信息</div>
					<div style="width: 100%;height: 168px;">
						<div class="patient-feft1">
							<div class="patient-feft">
								<div class="patient-feft2">预约号   </div>
								<div class="patient-feft"><s:iterator var="appRegOrdersPO" value="aropo">${appRegOrdersPO.appROrderNumber } </s:iterator> </div>
							</div>
							<div class="patient-feft" style="width: 100%;">
								<div style="height: 100%;float: left;width: 56px;">预约科室   </div>
								<div class="patient-feft"><s:iterator var="appRegOrdersPO" value="aropo">${appRegOrdersPO.departmentName } </s:iterator></div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 56px;">预约途径   </div>
								<div class="patient-feft">
									<s:iterator var="appRegOrdersPO" value="aropo">${appRegOrdersPO.regSourceName } </s:iterator>
									<%-- <s:if test="#appRegOrdersPO.regSource==1">微信</s:if>
									<s:if test="#appRegOrdersPO.regSource==2">支付宝</s:if>
									<s:if test="#appRegOrdersPO.regSource==3">自助机</s:if> --%>
								</div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 56px;">创建时间  </div>
								<div class="patient-feft"><s:iterator var="appRegOrdersPO" value="aropo">${fn:substring(appRegOrdersPO.createDate, 0, 16) } </s:iterator></div>
							</div>
							<div style="clear: both;"></div>
						</div>
						<div class="patient-feft1">
							<div class="patient-feft">
								<div class="patient-feft2" style="width: 87px;">就诊人 </div>
								<div class="patient-feft">
									${patientPO.patientName } 
								</div>
							</div>
							<div class="patient-feft" style="width: 100%;">
								<div style="height: 100%;float: left;width: 87px;">预约医生  </div>
								<div class="patient-feft">
									${appRegOrdersPO.doctorName }
								</div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 87px;"> 预约状态  </div>
								<div class="patient-feft">
									<s:iterator var="appRegOrdersPO" value="aropo">${appRegOrdersPO.statusName } </s:iterator>
									<%-- <s:if test="#appRegOrdersPO.orderStatus==1">失败</s:if><s:elseif test="#appRegOrdersPO.orderStatus==0">成功</s:elseif> <s:else></s:else>  --%>
								</div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 87px;">预约就诊时间 </div>
								<div class="patient-feft">
									<s:iterator var="appRegOrdersPO" value="aropo">${fn:substring(appRegOrdersPO.doctorDate, 0, 16) } </s:iterator>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div style="margin-left : 50px;width: 1040px;border: 1px solid #ccc;margin-top: 20px;">
					<div style="background-color: #f3f3f3;height: 42px;padding-left: 20px;">预约缴费信息</div>
					<div style="width: 100%;height: 126px;">
						<div class="patient-feft1">
							<div class="patient-feft">
								<div class="patient-feft2">应缴金额   </div>
								<div class="patient-feft" style="color: #54d2d6">${cardPayPO.payFee}(元) </div>
							</div>
							<div class="patient-feft" style="width: 100%;">
								<div class="patient-feft2">缴费状态 </div>
								<div class="patient-feft">
									<s:iterator var="appRegOrdersPO" value="aropo">${appRegOrdersPO.paidName } </s:iterator>
									<%-- <s:if test="%{cardPayPO.payStatus==1}">失败</s:if><s:elseif test="%{cardPayPO.payStatus==0}">成功</s:elseif> <s:else></s:else> --%>
								</div>
							</div>
							<div class="patient-feft" style="width: 100%;">
								<div class="patient-feft2">缴费时间  </div> 
								<div class="patient-feft">${fn:substring(cardPayPO.createDate, 0, 16) }</div>
							</div>
							<div style="clear: both;"></div>
						</div>
						<div class="patient-feft1">
							<div class="patient-feft">
								<div class="patient-feft2">实付金额   </div>
								<div class="patient-feft" style="color: #54d2d6">${cardPayPO.payFee}(元) </div>
							</div>
							
							<div style="clear: both;"></div>
						</div>
					</div>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>

		<div class="f-clear"></div>
	</div>
	<div id="showRegInfo"></div>
	<div id="showPatients"></div>
</body>
</html>