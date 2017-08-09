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
<title>对账单导入查询</title>
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
<script charset="utf-8" src="checkImportQuery/ImportQuery.js"></script>
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
				<li ><a href="../check/check_main.jsp">多方对账</a></li>
			    <li><a href="../checkRecord/checkRecord_main.jsp">对账处理记录</a></li>
			    <li class="tab-crt">对账单导入查询</li>
			    <li><a href="../checkImportQuery/exportCheck_main.jsp">第三方账单下载</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">对账单导入记录(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd" style="height: 40px;">
							<table height="100%" style="margin-top: -3px;">
								<tr>
									<td valign="middle" align="right">&nbsp;&nbsp;对账单来源:</td>
									<td valign="middle" align="left"><select id="source"
										    style="height: 28px; padding: 4px 8px;width:110px;">
										    <option value="">HIS</option>
									</select></td>
								    <td valign="middle" align="right">账单日期:</td>
									<td valign="middle">
								    <!--  <input type="text"  size="15"  id="batchNumber" style="height: 28px; line-height: 28px;"> -->
								     <input  name="billDate"
										type="text" size="10" style="height: 28px; line-height: 28px;" />
								    </td>
								      <td valign="middle" align="right">操作人:</td>
									<td valign="middle">
								     <input type="text"  size="15" id="operator" style="height: 28px; line-height: 28px;">
								    </td>
								  <!--   <td valign="middle" align="right">&nbsp;&nbsp;提交时间:</td>
									<td valign="middle" align="right"><input name="startdate"
										type="text" size="10" style="height: 28px; line-height: 28px;" /></td>
									<td valign="middle">-</td>
									<td valign="middle" align="right"><input name="enddate"
										type="text" size="10" style="height: 28px; line-height: 28px;" /></td> -->
									<td width="30px"></td>
									<td valign="middle" align="right"><button id="act_query"
											class="save-btn">查询</button></td>
									</tr>
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