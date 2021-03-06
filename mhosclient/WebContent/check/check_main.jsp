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
<title>多方对账</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script charset="utf-8" src="check/check_main.js"></script>
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
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">多方对账</li>
			    <li><a href="../checkRecord/checkRecord_main.jsp">对账处理记录</a></li>
			    <li><a href="../checkImportQuery/ImportQuery_main.jsp">对账单导入查询</a></li>
			    <li><a href="../checkImportQuery/exportCheck_main.jsp">第三方账单下载</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">对账记录(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd" style="height: 80px;">
							<table height="100%" style="margin-top: -3px;">
							<%-- <tr>
							<td valign="middle" align="right">资金账户</td>
							<td colspan="10" >							
							<s:radio id="checkType" name="checkType" list="#{'0':'按照支付方式','1':'按照资金账户'}" cssStyle="width:30px;" value="0"/>
							</td>
							</tr> --%>
								<tr>
								   <%--  <td valign="middle" align="right">支付方式:</td>
									<td valign="middle" align="left"><select id="pattern"
										    style="height: 28px; padding: 4px 8px;">								
									</select></td>
								 <td valign="middle" align="right">&nbsp;&nbsp;账单日期:</td>
									<td valign="middle" align="right"><input name="startdate"
										type="text" size="10" style="height: 28px; line-height: 28px;" /></td>
									<td valign="middle">-</td>
									<td valign="middle" align="right"><input name="enddate"
										type="text" size="10" style="height: 28px; line-height: 28px;" /></td>
									<td></td> --%>
									<td valign="middle" align="right">&nbsp;&nbsp;资金账户:</td>
									<td valign="middle" align="left"><select id="account"
										    style="height: 28px; padding: 4px 8px;width:120px;">
									</select></td>
									<td></td>
									<td valign="middle" align="right">&nbsp;&nbsp;对账状态:</td>
									<td valign="middle" align="left"><select id="checkStatus"
										    style="height: 28px; padding: 4px 8px;width:120px;">
										    <option value="">全部</option>
											<option value="0">正常</option>
											<option value="1">异常</option>
									</select></td>
									<td valign="middle" align="right">&nbsp;&nbsp;处理状态:</td>
									<td valign="middle" align="left"><select id="status"
										    style="height: 28px; padding: 4px 8px;width:120px;">
										    <option value="">全部</option>
											<option value="0">待确认</option>
											<option value="1">已确认</option>
									</select></td>
								
								   <td  valign="middle" width="33%"></td>
								   <td valign="middle" align="right"><button onclick="exports()"
											class="save-btn" style="width: 100px">导入对账单</button></td>
									</tr>
									<tr>
									<td valign="middle" align="right">交易时间:</td>
								     <td valign="middle" align="left"><input name="startDate"
										type="text" style="height: 28px; line-height: 28px; width: 120px;" /></td>
									<td valign="middle">-</td>
									<td valign="middle" align="left" colspan="2"><input name="endDate"
										type="text" style="height: 28px; line-height: 28px; width: 120px;" /></td>
									<td valign="middle" align="right"><button id="act_query"
											class="save-btn">查询</button></td>
									<td></td>
									<td  valign="middle" align="right" >
											<button class="save-btn"  onclick="javascript:recheck()" style="width: 100px">重新对账</button>
								   </td>
    								<td  valign="middle" align="right">
											<button class="save-btn"  id="export" style="width: 100px">导出对账报表</button>
								   </td>
									</tr>
							</table>
					</div>
					<div id="div_cnt_table"></div>

				</div>
				<div class="f-clear"></div>
			</div>
		</div>

		<div class="f-clear"></div>
	</div>
		<div id="showPayInfo"></div>
		<div id="showPatients"></div>
</body>
</html>