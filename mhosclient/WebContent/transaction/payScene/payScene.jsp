<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>/" />
<title>支付场景配置</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script charset="utf-8" src="transaction/payScene/payScene.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_DEPARTMENT')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<input id="hid_pageNo" type="hidden" value="${pageNo}"/>
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li><a href="../yqconfig/yqConfig_main.jsp">院区配置</a></li>
				<li class="tab-crt">支付场景配置</li>
				<li><a href="../payType/payType_main.jsp">支付方式配置</a></li>
				<li><a href="../transaction/accountConfig/accountConfig_main.jsp">资金账户配置</a></li>
				<li><a href="../payChannel/payChannel_main.jsp">支付通道配置</a></li>
				<li><a href="../transaction/terminalConfig/payTerConfig_main.jsp">支付终端配置</a></li>
				<li><a href="../transaction/checkSource/checkSource.jsp">对账来源配置</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div id="div_cnt_table"></div>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
		<div id="node"></div>
	</div>
	
</body>
</html>