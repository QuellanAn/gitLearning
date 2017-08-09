<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script charset="utf-8" src="selfServiceDevice/deviceVersion_history.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_DEPARTMENT')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<input id="hid_pageNo" type="hidden" value="${pageNo}"/>

<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt"><c:if test="${!empty terminalId }">终端</c:if>历史升级记录</li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl"><c:if test="${!empty terminalId }">终端</c:if>历史升级记录(<span id="totalcount"></span>)
						</span>
						<button class="save-btn" onclick="history.go(-1)" style="float: right;margin-right: 3px;margin-top: 3px;">返回</button>
					</div>
					<div class="left-list-hd" style="height: 40px;<c:if test="${!empty terminalId }">display:none;</c:if>" >
						<form id="form_query" onsubmit="return false;">
							<table height="100%" style="margin-top: -3px;">
								<input id="id" type="hidden" name="terminalId" value="${terminalId}"/>
								<input id="upgradeType" type="hidden" name="po.upgradeType" value="${upgradeType}"/>
								<tr>
									<td valign="middle" align="right">终端类型：</td>
									<td valign="middle">
										<select id="facilityType"
									    	name="po.facilityType" style="width: 130px;height: 28px;">		
										</select>
									</td>
									<td width="15px;"></td>
									<td valign="middle">激活状态：</td>
									<td valign="middle"><select name="po.activationStatus"
										type="text"
										style="height: 28px; line-height: 28px;width:120px;">
											<option value="">全部</option>
											<option value="1">已激活</option>
											<option value="0">未激活</option>
									</select></td>
									<td width="15px;"></td>
									<td valign="middle">激活时间：</td>
									<td valign="middle" colspan="7">
										<input id="beginDate" name="po.beginDate" type="text" size="10" 
											style="height: 28px; line-height: 28px; width:120px;" readonly="readonly"/>
										<span>-</span>
										<input id="endDate" name="po.endDate" type="text" size="10" 
											style="height: 28px; line-height: 28px;width:120px;" readonly="readonly"/>
									</td>
									<td>
										<input style="margin-left: 15px;" type="submit" value="查询" id="act_query" class="save-btn"/>
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