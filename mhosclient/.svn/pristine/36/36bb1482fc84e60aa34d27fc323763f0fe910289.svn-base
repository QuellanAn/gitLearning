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
<title>终端升级管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<s:include value="../publicJs.jsp"></s:include>
<script type="text/javascript" src="resources/js/jquery/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="resources/js/jquery/jquery.ztree.core.js"></script>
<script charset="utf-8" src="selfServiceDevice/deviceUpgrade_main.js"></script>
<style type="text/css">
td.upgraded a{
	color: #00CC00;
	font-weight: bold;
}
td.notupgrade a{
	color: #FF0000;
	font-weight: bold;
}
td.facilitySum a{
	color: #000;
}

.inputClass{
	width:150px;
	height:28px;
	box-sizing: border-box;
}

.labelClass{
	width: 80px;
	text-align: left;
	display:inline-block;
}

.gapSpan{
	width: 100px;
	display:inline-block;
}
</style>
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
				<li class="tab-crt">终端升级管理</li>
				<li><a href="selfServiceDevice/deviceVersion_main.jsp">设备版本信息查询</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<%-- <div class="left-list-hd">
						<span class="f-fl">医生管理(<span id="totalcount"></span>) </span>
					</div> --%>
					<div class="left-list-hd" style="height: 80px;">
						<form id="form_query" onsubmit="return false;">
							<table height="100%" style="margin-top: -3px;">
								<input id="upgradeType" type="hidden" name="po.upgradeType" value=""/>
								<tr>
									<td valign="middle" align="right">终端类型：</td>
									<td valign="middle">
										<select id="facilityType"
									    	name="po.facilityType" style="width: 130px;height: 28px;">		
										</select>
									</td>
									<td width="15px;"></td>
									<td valign="middle" align="right">版本名称：</td>
									<td valign="middle">
										<input type="text" id="versionName" name="po.versionName" style="width: 130px; line-height: 28px;"/>
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
								</tr>
								<tr>
									<td valign="middle">激活时间：</td>
									<td valign="middle" colspan="7">
										<input id="beginDate" name="po.beginDate" type="text" size="10" 
											style="height: 28px; line-height: 28px; width:120px;" readonly="readonly"/>
										<span>-</span>
										<input id="endDate" name="po.endDate" type="text" size="10" 
											style="height: 28px; line-height: 28px;width:120px;" readonly="readonly"/>
										<input style="margin-left: 15px;" type="submit" value="查询" id="act_query" class="save-btn"/>
										<button style="margin-left: 15px;width: 90px;" onclick="uploadPackage()" class="save-btn">上传升级包</button>
										<button style="margin-left: 15px;" onclick="activate()" class="save-btn">激活</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="treeDemoDiv"
						style="float: left;width: 180px;height:800px;text-align:left;border:1px solid #CCCCCC;min-height: 150px">
						<ul id="treeDemo" class="ztree"></ul>
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