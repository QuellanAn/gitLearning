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
<title>问题与帮助</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="config/basic/help.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_DEPARTMENT')">
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
				<li><a href="../config/wechat/basecontent_cfg.jsp">就医指南</a></li>
				<li><a href="../config/basic/res_notice">挂号须知</a></li>
				<li class="tab-crt"><a href="config/basic/help.jsp">问题与帮助</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">问题与帮助列表(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd">
						<form id="form_query">
							<table height="100%" style="margin-top: -3px;">
								<td valign="middle">问题:</td>
									<td valign="middle"><input type="text" id="question"
										name="question" style="width: 80px; height: 18px; padding: 4px 8px;"/>
									<td>
									<td valign="middle" width="10" align="right">
									<td valign="middle">操作人:</td>
									<td valign="middle"><input type="text" id="update_people"
										name="createPeople" style="width: 80px; height: 18px; padding: 4px 8px;"/>
									<td valign="middle" width="10" align="right">
									<td valign="middle" width="100" align="center" >预期发布日期:</td>
									<td valign="middle"><input name="startdate" type="text"
										style="width: 80px; height: 28px; line-height: 28px;" /></td>
									<td valign="middle">-</td>
									<td valign="middle"><input name="enddate" type="text"
										style="width: 80px; height: 28px; line-height: 28px;" /></td>
									<td valign="middle" width="80" align="right"><button
											id="act_query" class="save-btn">查询</button></td>
									<td valign="middle" width="80" align="right">
									<INPUT class="save-btn" type="button" value="添加" 
										onClick="location.href='config/basic/add_edit_helpanswer.jsp'"></td>
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
		<div id="node"></div>
	</div>
	
</body>
</html>