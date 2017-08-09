<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>自助终端详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
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
<input type="hidden" value="${param.key}" id="key1" />
<s:hidden value="%{#session.key}" id="key" />
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">自助终端详情</li>
			</ul>
			<div class="f-fl left-list-wrap">
				<div id="div_cnt_table">
					<div class="left-list-hd">
						<table width="100%" height="100%" style="margin-top: 0px;">
							<tr>
								<td align="right" width="60">
									<button class="save-btn" onclick="history.go(-1)">返回</button></td>
							</tr>
						</table>
					</div>
					<table class="list-table" style="table-layout:fixed;">
						<tr>
							<td width="10%" align="center" style="color: blue;"><font
								size=4>自助终端详情</font></td>
							<td width="20%" style="color: blue;border-right: none;"></td>
							<td width="10%" style="color: blue;border-right: none;"></td>
							<td width="20%" style="color: blue;"></td>

						</tr>
						<tr>
							<td align="center" style="background-color: #F2F2F2;">
								终端号</td>
							<td style="word-wrap:break-word;">
								${payTer.facilityId}</td>
							<td align="center" style="background-color: #F2F2F2;">HIS收费员编号</td>
							<td style="word-wrap:break-word;">${payTer.collectorId}</td>
						</tr>
						<tr>
							<td align="center" style="background-color: #F2F2F2;">
								终端名称</td>
							<td style="word-wrap:break-word;">${payTer.facilityName}</td>
							<td align="center" style="background-color: #F2F2F2;">终端类型
							</td>
							<td>${payTer.facilityType }</td>
						</tr>
						<tr>
							<td align="center" style="background-color: #F2F2F2;">院区</td>
							<td>${payTer.name}</td>
							<td align="center" style="background-color: #F2F2F2;">终端状态</td>
							<td>
								<c:if test="${payTer.facilityStatus eq '0'}"><span style="color: #44B549">启用</span></c:if>
								<c:if test="${payTer.facilityStatus eq '1'}"><span style="color: #FF0000">故障</span></c:if>
								<c:if test="${payTer.facilityStatus eq '2'}">关机</c:if>
								<c:if test="${payTer.facilityStatus eq '3'}"><span style="color: #FF6633">禁用</span></c:if>
							</td>
						</tr>
						<tr>
							<td align="center" style="background-color: #F2F2F2;">
								位置</td>
			                <td style="word-wrap:break-word;">${payTer.facilityName}</td>
							<td align="center" style="background-color: #F2F2F2;">备注
							</td>
							<td>${payTer.remark }</td>
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