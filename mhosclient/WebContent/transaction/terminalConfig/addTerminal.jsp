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
<title>添加支付终端</title>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/introduction-edit.css" />
<!-- <script type="text/javascript"
	src="resources/js/jquery/jquery-1.10.2.js"></script> -->
<s:include value="../../publicJs.jsp"></s:include>
<script type="text/javascript" src="resources/js/common.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null">
	<script type="text/javascript">
		location.href = $('base').attr('href') + "login.jsp";
	</script>
</s:if> -->
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">添加支付终端</li>
			</ul>

			<div class="tab-cnt" style="overflow: auto;margin-bottom: 10px;">

			<s:form method="post" action="unpaid/payTerAction_saveOrUpdate"  id="formTerminal" cssClass="introduction-fm">
					<ul>
						<li><span class="introduction-label f-fl" style="width: 130px;"> <span
								class="color-red">*</span>终端编号：</span>
								<s:textfield
								id="facilityId" name="payTer.facilityId"
								cssClass="fm-text f-fl" style="width:400px;" />
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl" style="width: 130px;"> <span
								class="color-red">*</span>HIS收费员编号：</span> 
								<s:textfield
								id="collectorId" name="payTer.collectorId"
								Class="fm-text f-fl" style="width:400px;"/>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl" style="width: 130px;"> <span
								class="color-red">*</span>终端名称：</span> <s:textfield
								id="facilityName" name="payTer.facilityName"
								Class="fm-text f-fl" style="width:400px;"/>
							<div class="f-clear"></div></li>
						<li><span class="introduction-label f-fl" style="width: 130px;"><span
								class="color-red">*</span>支付场景：</span> 
						<select id="payScene" name="payTer.payScene"
										    style="width: 240px;"Class="fm-text f-fl">										
						</select></li>

						<li><span class="introduction-label f-fl" style="width: 130px;">
						<span class="color-red">*</span>院区：</span> 
						<s:select id="district"
								name="payTer.district" list="#request.yqlist"
								listKey="yqId" listValue="name" cssClass="fm-text f-fl" style="width:240px;" />
							<div class="f-clear"></div></li>
						<li><span class="introduction-label f-fl" style="width: 130px;"><span
								class="color-red">*</span>设备状态：</span> 
						<s:select id="facilityStatus"
								name="payTer.facilityStatus" list="#{0:'启用',1:'故障',2:'关机',3:'禁用'}"
								listKey="key" listValue="value"
								cssClass="fm-text f-fl" style="width:240px;" />
							<div class="f-clear"></div></li>
						<li><span class="introduction-label f-fl" style="width: 130px;"> <span
								class="color-red">*</span>存放位置： </span>
								<s:textfield
								id="putAddress" name="payTer.putAddress"
								cssClass="fm-text f-fl" style="width:400px;" />
							<div class="f-clear"></div></li>
						<li><span class="introduction-label f-fl" style="width: 130px;"> <span
								class="color-red">*</span>备注： </span>
								<s:textfield
								id="remark" name="payTer.remark"
								cssClass="fm-text f-fl" style="width:400px;height:100px;" />
							<div class="f-clear"></div></li>
					</ul>
					<br />
					<center>
						<input id="saveTer" type="submit"  value="提交" style="margin-bottom: 10px;"
							class="normal-btn" onclick="" />
					</center>
			</s:form>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>

<script type="text/javascript">
var flag=false;
	$(document).ready(function() {
		loadPayScene();
		
		$("#facilityId").blur(function(){
		var code=$("#facilityId").val();
		if(code.length!=0 ){
		  $.post("unpaid/payTerAction_checkCode" ,{
		    code:code
		  }, function(data, status){
			if(data.code=="1"){
			   flag=true;
			}else if(data.code=="0"){
			    flag=false;
			   $.jBox.alert('终端编号已存在', '提示');
			}else{
			   flag=false;
			   $.jBox.alert('系统繁忙', '提示');
			}
			});
		}
		});
	});

	$("#formTerminal").submit(function(e){
		var facilityId=$("#facilityId").val();
		var collectorId=$("#collectorId").val();
		var facilityName=$("#facilityName").val();
		var district=$("#district").val();
		var facilityStatus=$("#facilityStatus").val();
		var putAddress=$("#putAddress").val();
		var payScene=$("#payScene").val();
		var remark=$("#remark").val();
		if(!flag){
		  $.jBox.alert('终端编号已存在', '提示');
		   return false;
		}
		if($.trim(facilityId).length==0 ||$.trim(collectorId).length==0||$.trim(facilityName).length==0||$.trim(remark).length==0||
		$.trim(district).length==0||$.trim(payScene).length==0||$.trim(facilityStatus).length==0||$.trim(putAddress).length==0){
			$.jBox.alert('请填写完整信息', '提示');
		return false;
		}
		return true;
	});
	
	function loadPayScene(){
		var url = 'unpaid/paySceneAction_findAllJson';
		var payScene = $('#payScene');
		payScene.empty();
		$.get(url , function(data, status){
			var jarr1 = eval(data);
	        var jarr=eval("("+jarr1.data+")");
			for (var i in jarr){
				var o = $('<option></option>').val(jarr[i].SCode).html(jarr[i].SName);
				payScene.append(o.get());
			}
		});
	};
</script>
</html>