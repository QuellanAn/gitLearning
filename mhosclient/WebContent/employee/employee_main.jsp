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
<%-- <script charset="utf-8" src="hosinfomng/hos_pub_info_main_sp.js"></script> --%>
<script charset="utf-8" src="employee/employee_main.js"></script>
</head>

<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt"><a href="employee/employee_manage.jsp">员工信息管理</a></li>
				<li ><a
					href="employee/employee_add.jsp">添加新员工</a></li>
				<li><a href="employee/employee_manage.jsp">设置</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">信息管理(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd">
						<form id="form_query" action="" method="post">
							<table height="100%" style="margin-top: -3px;">
								<tr>
									<td valign="middle">姓名:</td>
									<td valign="middle"><input type="text" id="name"
										name="name" style="width: 80px; height: 18px; padding: 4px 8px;"/>
									</td>
									<td valign="middle">性别：</td>
									<td valign="middle"><select id="sex" name="sex"
										style="width: 80px; height: 28px; padding: 4px 8px;">
											<option value="">全部</option>
											<option value="男">男</option>
											<option value="女">女</option>
									</select></td> 
									<td valign="middle">部门：</td>
									<td valign="middle"><select  name="department"
										style="width: 80px; height: 28px; padding: 4px 8px;">
											<option value="">全部</option>
											<option value="软件部">软件部</option>
											<option value="打印机">打印机</option>
											<option value="物联网">物联网</option>
											<option value="智能自助">智能自助</option>
											<option value="数据平台">数据平台</option>
									</select></td> 
															
									<td valign="middle" width="80" align="right"><button
											id="act_query" class="save-btn">查询</button></td>
									<td valign="middle" width="80" align="right"><button
											id="act_update" class="save-btn">修改</button></td>
									<td valign="middle" width="80" align="right"><button
											id="act_delete" class="save-btn">删除</button></td>
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