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
<title>编辑支付方式</title>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/introduction-edit.css" />
<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null">
	<script type="text/javascript">
		location.href = $('base').attr('href') + "login.jsp";
	</script>
</s:if> -->
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">编辑支付方式</li>
			</ul>

			<div class="tab-cnt" style="overflow: auto;margin-bottom: 10px;">

			<s:form method="post" action="payTypeAction_modify"  id="form_modify" cssClass="introduction-fm">
					<ul>
						<li><span class="introduction-label f-fl"  style="width:120px;text-align:left;"> <span
								class="color-red">*</span>支付方式编号：</span>
								<s:textfield
								id="ptCode" name="payType.ptCode"
								cssClass="fm-text f-fl" style="width:500px;" readonly="true"/>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl"   style="width:120px;text-align:left;"> <span
								class="color-red">*</span>支付方式名称：</span> 
								<s:textfield
								id="ptName" name="payType.ptName"
								Class="fm-text f-fl" style="width:500px;"/>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl"  style="width:120px;text-align:left;" > <span
								class="color-red">*</span>状态：</span>
								<s:select id="ptStatus"
								name="payType.ptStatus" list="#{0:'启用',1:'禁用'}"
								listKey="key" listValue="value" cssClass="fm-text f-fl" style="width:240px;" />
							<div class="f-clear"></div></li>					
					</ul>
					<br />
					<center>				
						<input id="saveYQ" type="submit"  value="保存" style="margin-bottom: 10px;"
							class="normal-btn" onclick="" />
					</center>
			</s:form>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>

<script type="text/javascript">
//显示配置菜单
	$('.menu-item-cnt').attr("style", "display:none;");
	$('.menu-item-cnt:eq(1)').attr("style", "display:block");
	
	$("#form_modify").submit(function(e){
				
		var ptCode=$("#ptCode").val();
		var ptName=$("#ptName").val();
		var ptStatus=$("#ptStatus").val();
		if($.trim(ptCode).length==0 ||$.trim(ptName).length==0||$.trim(ptStatus).length==0){
		alert("请输出完整的信息");
		return false;
		}
		return true;
	});
</script>
</html>