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
<title>自助终端管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
	<s:include value="../publicJs.jsp"></s:include>
<script charset="utf-8" src="selfServiceDevice/deviceVersion_main.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_DEPARTMENT')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<input id="hid_pageNo" type="hidden" value="${pageNo}"/>
<input id="facilityTypeCode" type="hidden" value="${facilityType}"/>
<input id="upgradeType" type="hidden" value="${upgradeType}"/>
<input id="versionCode" type="hidden" value="${versionCode}"/>
<input id="isLargerThan" type="hidden" value="${isLargerThan}"/>
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li><a href="selfServiceDevice/deviceUpgrade_main.jsp">终端升级管理</a></li>
				<li class="tab-crt">设备版本信息查询</li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">设备版本信息(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd" style="height: 80px;">
						<form id="form_query">
							<table height="100%" style="margin-top: -3px;">
								<tr>
									<td valign="middle" align="right">终端编号:</td>
									<td valign="middle"><input type="text" id="facilityId"
										name="payTer.facilityId" style="width: 130px; line-height: 28px;"/>
									</td>
									<td width="15px;"></td>
									<td valign="middle" align="right">院区:</td>
									<td valign="middle">
										<select id="district" name="payTer.district"
											style="width: 130px; height: 28px; line-height: 28px;">
										</select>
									</td>
									<td width="15px;"></td>
									<td valign="middle" align="right">终端类型:</td>
									<td valign="middle" align="left">
										<select id="facilityType"
									    	name="payTer.facilityType" style="width: 130px;height: 28px;">		
										</select>
									</td>
									<td valign="middle" width="200">
								</tr>
								<tr>
									<td valign="middle" align="right">终端状态:</td>
									<td valign="middle">
										<select id="facilityStatus" name="payTer.facilityStatus"
											style="height: 28px; line-height: 28px;width:130px;">
											<option value="">全部</option>
											<option value="0">启用</option>
											<option value="1">故障</option>
											<option value="2">关机</option>
											<option value="3">禁用</option>
										</select>
									</td>
									<td width="15px;"></td>
									<td valign="middle" width="80" align="left" colspan="3">
										<input type="submit" value="查询" id="act_query" class="save-btn"/>
									</td>
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