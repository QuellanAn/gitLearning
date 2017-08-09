<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>已发布</title>
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="resources/js/common.js"></script>
<script type="text/javascript" src="../specialist/allSpecialist.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_HOSINFO')">
	<script type="text/javascript">
		location.href = "../login.jsp";
	</script>
</s:if> -->
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<input type="hidden" value="${param.key}" id="key1"/>
				<s:hidden value="%{#session.key}" id="key"/>
				<li class="tab-crt"><a href="${pageContext.request.contextPath}/specialist/allSpecialist.jsp">专家信息管理</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<div style="float: left;">
							<table width="100%" style="margin-top: -3px;">
								<tr>
									<td>医生姓名:</td>
									<td>
										<input type="text" id="name" style="padding: 5px 5px"/>
									</td>
									
									<td>
										<button id="find" class="save-btn">查询</button>
									</td>
								</tr>
							</table>
						</div>
						<span id="btn_add_pub" class="new-link f-fr" style="cursor:pointer;" onclick="javascript:specialist.toSave();">添加专家信息</span>
					</div>
					<div id="div_cnt_table"></div>

				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
	<div id="node"></div>
</body>
</html>