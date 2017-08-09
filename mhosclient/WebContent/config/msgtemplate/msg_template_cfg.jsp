<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<MsgBusMainTypeVO> msgBusMainTypes = (List<MsgBusMainTypeVO>) request
			.getAttribute("msgBusMainTypes");
%>


<html>
<head>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>消息模板配置</title>
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script charset="utf-8" src="resources/js/common.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script charset="utf-8" src="config/msgtemplate/msg_template_cfg.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_MSG')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>

		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">消息模版配置</li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl right-list-wrap">
					<div id="div_msgcat_title" class="right-list-hd"
						style="cursor: default;">系统消息分类</div>
					<%
						for (MsgBusMainTypeVO m : msgBusMainTypes) {
					%>
					<div class="right-list-item">
						<div class="item-hd" style="cursor: default;"><%=m.getMainTypeDesc()%></div>
						<div class="item-cnt">
							<ul>
								<%
									for (BusTypeDicVO dic : m.getBusTypeDics()) {
								%>
								<li><a href="#"
									onclick="javascript:select('<%=dic.getBusTypeCode()%>');return false;"
									style="color: #0079ff; text-decoration: underline;"> <%=dic.getBusName()%>
								</a></li>
								<%
									}
								%>
							</ul>
						</div>
					</div>
					<%
						}
					%>

				</div>
				<div id="div_info_cat" class="f-fl left-list-wrap"
					style="border: 1px solid #dcdcdc; height: 445px;float: none;display: block;">
					<input type="hidden" id="msgBusCode" />
					<table style="padding-left: 90px;">
						<tr>
							<td width="20%" align="center">消息分类：</td>
							<td><span id="msgBusName"></span></td>
						</tr>
						<tr>
							<td align="center">发送方式：</td>
							<td><input id="supportPhoneMsg" name="supportPhoneMsg"
								type="checkbox" />&nbsp;&nbsp;短信方式&nbsp;&nbsp;&nbsp; <input
								id="supportNetMsg" name="supportNetMsg" type="checkbox" />&nbsp;&nbsp;手机消息方式
							</td>
						</tr>
						<tr>
							<td align="center">消息模版：</td>
							<td><textarea id="template" name="template" rows="12"
									cols="58"></textarea></td>
						</tr>
					</table>
					<br />
					<center>
						<button id="act_save" class="normal-btn">保存</button>
					</center>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>




</body>
</html>

