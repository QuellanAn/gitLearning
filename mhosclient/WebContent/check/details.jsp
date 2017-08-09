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
<title>账单详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/Toast.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="check/details.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<input type="hidden" value="${createDate}" id="createDate">
		<input type="hidden" value="${accountCode}" id="accountCode">
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">对账列表</li>
			</ul>
				<div class="f-fl left-list-wrap" style="padding-bottom: 10px;">
						<div class="left-list-hd" style="height:40px;margin:0px;padding:0px; border-bottom:none;">
					<table height="100%">
							<tr >	
							    <td valign="middle" align="center">对账状态:</td>
									<td valign="middle" align="middle" ><select id="billStatus"
										    style="height: 28px; padding: 4px 8px;width:100px;">
										    <option value="">全部</option>
											<option value="1">正常</option>
											<option value="0">异常</option>
									</select></td>
								<td valign="middle" align="center" >处理状态:</td>
								<td valign="middle" align="middle"><select id="handle"
										    style="height: 28px;width:100px;">
										    <option value="">全部</option>
											<option value="0">未处理</option>
											<option value="1">已处理</option>
								</select></td>
								<td align="left" width="60px">
									<button class="save-btn" id="act_query">查询</button>
								</td>
								<%-- <td align="right" width="60">
									<button class="save-btn" onclick="javascript:batchCheck('${out_trade_no}','${page.pageNo}','${pattern}','${createDate}','${billStatus}')">校验</button>
								</td> --%>
									<td align="right" width="500px">
									<button class="save-btn" onclick="javascript:exportExcel()" style="width: 100px">导出对账列表</button>
								</td>
								<td align="right" width="100px">
									<button class="save-btn" onclick="history.go(-1)">返回</button>
								</td>
							</tr>
						</table>
					</div>
						<div id="div_cnt_table">
						</div>
					<div class="f-clear"></div>
				</div>
		</div>
		<div class="f-clear"></div>
	</div>
		<div id="node"></div>
</body>
</html>