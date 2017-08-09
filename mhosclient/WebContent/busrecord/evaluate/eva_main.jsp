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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>服务评价记录</title>
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="busrecord/evaluate/eva_main.js"></script>
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
				<!-- <li><a href="busrecord/appreg/app_reg_main.jsp">预约挂号记录</a></li>
				<li><a>就诊缴费记录</a></li>
				<li><a>咨询服务记录</a></li> -->
				<li class="tab-crt"><a href="busrecord/evaluate/eva_main.jsp">问卷调查记录</a></li>
				<!-- <li><a href="busrecord/msg/msg_main.jsp">消息发送记录</a></li> -->
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">问卷调查记录列表(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd">
						<form id="form_query">
							<table height="100%" style="margin-top: -3px;">
								<tr>
									<td valign="middle" align="right"></td>
									
									<%-- <td valign="middle" align="right"><select id="depart_name"
										name="departmentname"
										style="width: 80px; height: 28px; padding: 4px 8px;">
											<option value="0">全部</option>
									</select></td> --%>
									<input type="hidden" name="departmentname" value="0" />
									
									<!-- <td valign="middle" align="right">主治医生：</td>
									<td valign="middle">
									<input type="text" id="doctorname" name="doctorname" size="10"  style="height: 28px; line-height: 28px;">
									</td> -->
									
									<td valign="middle" align="right">问卷标题：</td>
									<td valign="middle">
									<input type="text" id="title" name="title" size="10"  style="height: 28px; line-height: 28px;">
									</td>
									
									
									
									<%-- <td valign="middle" align="right">选择医生：</td>
									<td valign="middle" align="right">
									<select id="doctor_name" name="doctorname"
										style="width: 80px; height: 28px; padding: 4px 8px;">
									</select></td> --%>
									
									
									
									<td valign="middle" align="right">参与时间:</td>
									<td valign="middle" align="right"><input name="startdate"
										type="text" size="10" style="height: 28px; line-height: 28px;" /></td>
									<td valign="middle">-</td>
									<td valign="middle" align="right"><input name="enddate"
										type="text" size="10" style="height: 28px; line-height: 28px;" /></td>
									
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