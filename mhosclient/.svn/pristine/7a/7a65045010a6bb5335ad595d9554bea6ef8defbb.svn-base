<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.catic.mobilehos.service.vo.*"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>消息发送记录</title>
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="busrecord/msg/msg_main.js"></script>
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
				<li><a href="busrecord/appreg/app_reg_main.jsp">预约挂号记录</a></li>
				<li><a>就诊缴费记录</a></li>
				<li><a>咨询服务记录</a></li>
				<li><a href="busrecord/evaluate/eva_main.jsp">服务评价记录</a></li>
				<li class="tab-crt"><a href="busrecord/msg/msg_main.jsp">消息发送记录</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">消息发送记录(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd">
						<form id="form_query">
							<table height="100%" style="margin-top: -3px;">
								<tr>
									<td valign="middle" align="right">消息类型：</td>
									<td valign="middle" align="right"><select id="bustype"
										name="bustypecode"
										style="width: 80px; height: 28px; padding: 4px 8px;">
											<option value=''>全部</option>
									</select></td>
									<td valign="middle" align="right">发送手机号：</td>
									<td valign="middle" align="right"><input name="phone"
										size="8" style="height: 28px; line-height: 28px;"></input></td>
									<td valign="middle" align="right">发送状态：</td>
									<td valign="middle" align="right"><select id="status"
										name="status"
										style="width: 70px; height: 28px; padding: 4px 8px;">
											<option value="">全部</option>
											<option value="<%=MessageVO.STATUS_UNSENT%>">未发送</option>
											<option value="<%=MessageVO.STATUS_SUCCESS%>">发送成功</option>
											<option value="<%=MessageVO.STATUS_FAILED%>">发送失败</option>
									</select></td>
									<td valign="middle" align="right">发送时间：</td>
									<td valign="middle" align="right"><input name="startdate"
										type="text" size="7" style="height: 28px; line-height: 28px;" /></td>
									<td valign="middle" align="right">-</td>
									<td valign="middle" align="right"><input name="enddate"
										type="text" size="7" style="height: 28px; line-height: 28px;" /></td>
									<td valign="middle" align="right"><button id="act_query"
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
</body>
</html>