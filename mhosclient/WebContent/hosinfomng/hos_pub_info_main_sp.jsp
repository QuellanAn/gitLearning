<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发布审批</title>
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="hosinfomng/hos_pub_info_main_sp.js"></script>
</head>

<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li><a href="hosinfomng/hos_pub_info_main.jsp">新发布</a></li>
				<li class="tab-crt"><a
					href="hosinfomng/hos_pub_info_main_sp.jsp">发布审批</a></li>
				<li><a href="hosinfomng/hos_pub_info_main_pub.jsp">已发布</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">待审批列表(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd">
						<form id="form_query">
							<table height="100%" style="margin-top: -3px;">
								<tr>
									<td valign="middle">咨询分类：</td>
									<!-- <td valign="middle"><select id="infotype" name="infotype"
										style="width: 80px; height: 28px; padding: 4px 8px;">
											<option value="0">全部</option>
											<option value="1">健康资讯</option>
											<option value="2">医院动态</option>
									</select></td> -->
									<td valign="middle"><select id="infocat"
										name="infocatcode"
										style="width: 120px; height: 28px; padding: 4px 8px;">
									</select></td>
									<td valign="middle" width="100">预期发布日期:</td>
									<td valign="middle"><input name="startexpdate" type="text"
										style="width: 90px; height: 28px; line-height: 28px;" /></td>
									<td valign="middle">-</td>
									<td valign="middle"><input name="endexpdate" type="text"
										style="width: 90px; height: 28px; line-height: 28px;" /></td>
									<td valign="middle">编辑人:</td>
									<td valign="middle"><input type="text" id="editor"
										name="editor" style="width: 80px; height: 18px; padding: 4px 8px;"/>
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