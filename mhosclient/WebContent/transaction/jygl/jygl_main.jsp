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
<base href="<%=basePath%>" />
<title>交易概览</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<link rel="stylesheet" type="text/css" href="transaction/jygl/jygl.css" />
<link id="skin" rel="stylesheet" href="resources/jBox/Skins2/Blue/jbox.css" />
<script type="text/javascript" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script src="resources/js/jquery/bootstrap.min.js"
	type="text/javascript"></script>
	<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/i18n/jquery.jBox-zh-CN.js"></script>
<script charset="utf-8" src="transaction/jygl/jygl.js"></script>

</head>

<input type="hidden" value="" id="zongeBefore" />
<input type="hidden" value="" id="zongbiBefore" />
<input type="hidden" value="" id="juneBefore" />
<body>
	<div class="cnt-wrap">
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg" id="ul1">
				<li class="tab-crt">交易概览</li>
			</ul>
			<div class="tab-cnt" style="padding-bottom: 10px;">
				<div class="tab" role="tabpanel" style="width: 830px;margin-left: 100px;">
					<div align="center;"
						style="text-align: center;font-weight: bold;font-size: 18px;margin-top: -10px;">近一周交易数据及变化趋势</div>
					<ul class="nav nav-tabs" role="tablist">
						<li style="width: 33%" role="presentation" class="active"><a
							href="#Section1" aria-controls="home" role="tab" id="totalMoney" data-toggle="tab" 
							style="height: 60px;text-align: center;"><div>订单总金额(元)<br/></div></a>
						</li>
						<li style="width: 33%" role="presentation"><a
							href="#Section2" aria-controls="profile" role="tab"
							id="totalCount" data-toggle="tab"
							style="height: 60px;text-align: center;"><div>订单总笔数(笔)<br/></div></a>
						</li>
						<li style="width: 34%" role="presentation"><a
							href="#Section3" aria-controls="messages" role="tab"
							id="avgMoney" data-toggle="tab"
							style="height: 60px;text-align: center;"><div>人均金额(元)<br/></div></a>
						</li>
					</ul>
					<div class="tab-content tabs">
						<div role="tabpanel" class="tab-pane fade in active"
							style="height: 410px;width: 790px;" id="Section1"></div>
						<div role="tabpanel" class="tab-pane fade"
							style="height: 410px;width: 790px;" id="Section2"></div>
						<div role="tabpanel" class="tab-pane fade"
							style="height: 410px;width: 790px;" id="Section3"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="f-clear"></div>
		<div class="f-clear"></div>
</body>
<script src="resources/js/echarts.min.js" type="text/javascript"></script>
</html>