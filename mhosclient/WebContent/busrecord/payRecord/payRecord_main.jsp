<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>缴费记录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="resources/js/common.js"></script>
<script type="text/javascript" src="busrecord/payRecord/payRecord_main.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>

		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt"><a href="busrecord/payRecord/payRecord_main.jsp">缴费记录列表</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">缴费记录列表(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd" style="height: 40px;">
						<form id="form_query">
							<table height="100%" style="margin-top: 0px;">
									<tr>
										<td valign="middle" align="right">就诊卡号：</td>
										<td valign="middle">
											<input type="text" id="cardNo" name="cardNo" size="10"  style="height: 28px; line-height: 28px;">
										</td>
										<td valign="middle" align="right">就诊人：</td>
										<td valign="middle">
											<input type="text" id="patientname" name="patientname" size="10"  style="height: 28px; line-height: 28px;">
										</td>
										<td valign="middle" align="right">缴费项目：</td>
										<td valign="middle" align="right"><select id="tarOCCateDesc"
											name="tarOCCateDesc" style="height: 28px;width:90px;">
												<option value="">全部</option>
												<option value="西药">西药</option>
												<option value="临床诊断">临床诊断</option>
												<option value="特殊耗材">特殊耗材</option>
											</select>
										</td>
										<td valign="middle" align="right">缴费时间：</td>
										<td valign="middle" align="right"><input
											name="startCreateTime" type="text" size="10"
											style="height: 28px; line-height: 28px;" /></td>
										<td valign="middle" align="center">-</td>
										<td valign="middle" align="right"><input
											name="endCreateTime" type="text" size="10"
											style="height: 28px; line-height: 28px;" /></td>
										<td valign="middle" align="right" style="padding-left: 20px;"><button id="act_query"
											class="save-btn">查询</button></td>
									</tr>
									
							</table>
						</form>
					</div>
					<div id="div_cnt_table"></div>

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