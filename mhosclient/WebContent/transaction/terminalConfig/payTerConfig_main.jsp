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
<title>支付终端配置</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
	<s:include value="../../publicJs.jsp"></s:include>
<script charset="utf-8" src="transaction/terminalConfig/terminalConfig.js"></script>
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
				<li><a href="../yqconfig/yqConfig_main.jsp">院区配置</a></li>
				<li><a href="../transaction/payScene/payScene.jsp">支付场景配置</a></li>
			    <li><a href="../payType/payType_main.jsp">支付方式配置</a></li>
				<li><a href="../transaction/accountConfig/accountConfig_main.jsp">资金账户配置</a></li>
				<li><a href="../payChannel/payChannel_main.jsp">支付通道配置</a></li>
				<li class="tab-crt">支付终端配置</li>
				<li><a href="../transaction/checkSource/checkSource.jsp">对账来源配置</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">支付终端配置(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd" style="height: 80px;">
						<form id="form_query">
							<table height="100%" style="margin-top: -3px;">
								<tr>
									<td valign="middle" align="right">院区:</td>
									<td valign="middle"><select id="district" name="payTer.district"
										style="width: 130px; height: 28px; line-height: 28px;">
									</select></td>
									<td width="15px;"></td>
									<!-- <td valign="middle" style="padding-left: 25px;">HIS收费员编号:</td>
									<td valign="middle"><input type="text" id="collectorId"
										name="payTer.collectorId" style="width: 110px; height: 18px; padding: 4px 8px;"/></td> -->
									<td valign="middle" align="right">支付场景:</td>
										<td valign="middle" align="left"><select id="payScene"
										    name="payTer.payScene" style="width: 130px;height: 28px;">										
									</select></td>
								</tr>
								<tr>
									<td valign="middle" align="right">终端状态:</td>
									<td valign="middle">  <select id="facilityStatus" name="payTer.facilityStatus"
										style="height: 28px; line-height: 28px;width:130px;">
											<option value="">全部</option>
											<option value="0">启用</option>
											<option value="1">故障</option>
											<option value="2">关机</option>
											<option value="3">禁用</option>
									</select></td>
									<td width="15px;"></td>
									<td valign="middle" align="right">位置:</td>
									<td valign="middle"><input type="text" id="putAddress"
										name="payTer.putAddress" style="width: 130px; line-height: 28px;"/></td>
									<td valign="middle" width="80" align="right">
									<input type="submit" value="查询" id="act_query" class="save-btn"/></td>
									<td valign="middle" width="80" align="right">
									<INPUT class="save-btn" type="button" value="添加" 
										onClick="toAdd()"></td>
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