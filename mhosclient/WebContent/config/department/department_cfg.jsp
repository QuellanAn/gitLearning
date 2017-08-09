<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>科室介绍管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
<link rel="stylesheet" href="resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="resources/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.ztree.core.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script charset="utf-8" src="config/department/department_cfg.js"></script>
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
				<li class="tab-crt">科室介绍管理</li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">科室介绍管理(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd">
						<form id="form_query" enctype="multipart/form-data" onsubmit="return false;">
							<table height="100%" style="margin-top: -3px;">
								<input  type="hidden" id="type" name="type"/>
								<tr>
									<td valign="middle">科室名称：</td>
									<td valign="middle"><input id="name" name="name"
										type="text" style="width:120px; height: 28px; line-height: 28px;" /></td>
									<td valign="middle">科室位置:</td>
									<td valign="middle"><input id="location" name="location"
										type="text" style="width:120px;height: 28px; line-height: 28px;" /></td>
									<td valign="middle">
									<td valign="middle">科室状态：</td>
									<td valign="middle"><select id="status" name="status"
										style="width: 100px; height: 28px;">
											<option value="">全部</option>
											<option value="1">正常</option>
											<option value="0">冻结</option>
									</select></td>
									<td>
									<button id="act_query" class="save-btn">
									查询
									</button>
									</td>
								<td valign="middle" align="right">
								<!-- <a href="javascript:act_save();">加</a> -->
								<button onclick="javascript:act_save();" class="save-btn">
									添加
									</button>
								</td>
								<td>
									<input type="file" id="excleFile" name="excleFile"onchange="fileChange(this);" style="display:none"/>
									<button onclick="$('input[id=excleFile]').click();" class="save-btn">导入</button>
								</td>
								<td>
									<button onclick="exportDepart()" class="save-btn">导出</button>
								</td>
								</tr>
							</table>
						</form>
					</div>
					<div style="float: left;width: 215px;height:550px;text-align:left;border:1px solid #CCCCCC">
						<ul id="treeDemo" class="ztree" style="overflow-y:auto;max-height:540px;"></ul>
					</div>
					<div id="div_cnt_table" style="float: right;"></div>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
		<div id="node"></div>
	</div>
	
</body>
</html>