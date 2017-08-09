<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>院长开讲</title>
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="enterprize/deanlecturer/lecturer_main.js"></script>
</head>
<body>
	<input id="hid_pageNo" type="hidden" value="${pageNo}"/>
	<div class="cnt-wrap">
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">开讲内容</li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">数据列表（<span id="totalcount"></span>）
						</span> <a href="javascript:void(0);" id="btn_add_pub" class="new-link f-fr">添加新内容</a>
					</div>
					
					<div class="left-list-hd">
						<form id="form_query">
							<table height="100%" style="margin-top: -3px;">
								<tr>
									<td valign="middle" align="center" width="80">创建日期:</td>
									<td valign="middle"><input name="starttime" type="text"
										style="width: 90px; height: 28px; line-height: 28px;" /></td>
									<td valign="middle">-</td>
									<td valign="middle"><input name="endtime" type="text"
										style="width: 90px; height: 28px; line-height: 28px;" /></td>
									<td valign="middle">创建人:</td>
									<td valign="middle"><input type="text" id="author"
										name=""author"" style="width: 80px; height: 18px; padding: 4px 8px;"/>
									<td>
									<td valign="middle" width="80" align="right"><button
											id="act_query" class="save-btn">查询</button></td>
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