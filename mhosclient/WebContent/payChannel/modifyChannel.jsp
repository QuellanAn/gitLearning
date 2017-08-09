<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>编辑支付通道</title>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/introduction-edit.css" />
<script type="text/javascript"
	src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null">
	<script type="text/javascript">
		location.href = $('base').attr('href') + "login.jsp";
	</script>
</s:if> -->
<input type="hidden" value="${param.key}" id="key1" />
<s:hidden value="%{#session.key}" id="key" />
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">编辑支付通道</li>
			</ul>

			<div class="tab-cnt" style="overflow: auto;margin-bottom: 10px;">

				<s:form method="post" action="unpaid/payChannelAction_modify"
					id="form" cssClass="introduction-fm">
					<ul>
						<li><span class="introduction-label f-fl"
							style="width: 130px;text-align:left"> <span class="color-red">*</span>通道名称：</span>
							<s:textfield id="cName" name="payChannel.cName"
								cssClass="fm-text f-fl" style="width:400px;" />
							<div class="f-clear"></div>
						</li>
						<li><span class="introduction-label f-fl"
							style="width: 130px;text-align:left"> <span class="color-red">*</span>院区：</span>
						      <s:select id="yqCode"  name="payChannel.yqCode" list="yqList" listKey="yqId" listValue="name" value="payChannel.yqCode" style="width: 240px;" Class="fm-text f-fl"/>
							<div class="f-clear"></div>
						</li>
						<li><span class="introduction-label f-fl"
							style="width: 130px;text-align:left"> <span class="color-red">*</span>支付场景：</span>
							  <s:select id="sCode"  name="payChannel.sCode" list="paySceneList" listKey="sCode" listValue="sName" value="payChannel.sCode" style="width: 240px;" Class="fm-text f-fl"/>
							<div class="f-clear"></div>
						</li>
						<li><span class="introduction-label f-fl"
							style="width: 130px;text-align:left"> <span class="color-red">*</span>支付方式：</span>
					     <s:select id="ptCode"  name="payChannel.ptCode" list="payTypeList" listKey="ptCode" listValue="ptName" value="payChannel.ptCode" style="width: 240px;" Class="fm-text f-fl"/>
						</li>
						<li><span class="introduction-label f-fl"
							style="width: 130px;text-align:left"> <span class="color-red">*</span>资金账户：</span>
						  <s:select id="accountCode"  name="payChannel.accountCode" list="accountList" listKey="accountCode" listValue="accountName" value="payChannel.accountCode" style="width: 240px;" Class="fm-text f-fl"/>
						</li>
						<li><span class="introduction-label f-fl"
							style="width: 130px;text-align:left"><span class="color-red">*</span>通道状态：</span>
							<s:select id="cStatus" name="payChannel.cStatus"
								list="#{0:'启用',1:'禁用'}" listKey="key" listValue="value"
								cssClass="fm-text f-fl" style="width:240px;" />	
							<div class="f-clear"></div>
						</li>
						<li><span class="introduction-label f-fl"
							style="width: 130px;text-align:left">备注：</span>
						<textarea name="payChannel.remark" cols=60 rows=4 style="text-align: left;width:400px;">${payChannel.remark}</textarea>
							<div class="f-clear"></div>
						</li>
					</ul>
					<br />
					<center>
					<s:hidden name="payChannel.cID"></s:hidden>
						<input id="save" type="submit" value="保存" class="normal-btn" style="margin-bottom: 10px;"
							onclick="" />
					</center>
				</s:form>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>

<script type="text/javascript">
	$("#form").submit(function(e){
		var cName=$("#cName").val();
		
		if($.trim(cName).length==0){
		alert("请填写通道名称");
		return false;
		}
		return true;
	});
</script>
</html>