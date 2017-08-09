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
<s:include value="../publicJs.jsp"></s:include>
<script type="text/javascript" src="resources/js/common.js"></script>
<style type="text/css">
#formTerminal .intro_label{
	width: 88px;
	text-align: left;
	padding-left:45px;
}
</style>
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
				<li class="tab-crt">添加自助终端</li>
			</ul>

			<div class="tab-cnt" style="overflow: auto;">

			<s:form method="post" action="selfServiceManageAction_saveOrUpdate"  id="formTerminal" cssClass="introduction-fm">
					<ul>
						<li><span class="introduction-label f-fl intro_label"> <span
								class="color-red">*</span>终端编号：</span>
								<s:textfield
								id="facilityId" name="payTer.facilityId"
								cssClass="fm-text f-fl" style="width:400px;" />
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl intro_label"> <span
								class="color-red">*</span>终端名称：</span> <s:textfield
								id="facilityName" name="payTer.facilityName"
								Class="fm-text f-fl" style="width:400px;"/>
							<div class="f-clear"></div></li>
						<li><span class="introduction-label f-fl intro_label"><span
								class="color-red">*</span>院区：</span> 
							<select id="district" name="payTer.district" style="width: 240px;"Class="fm-text f-fl">										
							</select>
						</li>
						
						<li><span class="introduction-label f-fl intro_label"><span
								class="color-red">*</span>终端类型：</span> 
							<select id="facilityType" name="payTer.facilityType" style="width: 240px;"Class="fm-text f-fl">	
							</select>
						</li>

						<li><span class="introduction-label f-fl intro_label"><span
								class="color-red">*</span>终端状态：</span> 
						<s:select id="facilityStatus"
								name="payTer.facilityStatus" list="#{0:'启用',1:'故障',2:'关机',3:'禁用'}"
								listKey="key" listValue="value"
								cssClass="fm-text f-fl" style="width:240px;" />
							<div class="f-clear"></div></li>
						<li><span class="introduction-label f-fl intro_label"> <span
								class="color-red">*</span>位置： </span>
								<s:textfield
								id="putAddress" name="payTer.putAddress"
								cssClass="fm-text f-fl" style="width:400px;" />
							<div class="f-clear"></div></li>
						<li><span class="introduction-label f-fl intro_label"> <span
								class="color-red" style="visibility:hidden;">*</span>备注： </span>
								<s:textarea
								id="remark" name="payTer.remark"
								cssClass="fm-text f-fl" style="width:400px;height:100px;" />
							<div class="f-clear"></div></li>
					</ul>
					<br />
					<center>
						<input id="saveTer" type="submit"  value="提交"
							class="normal-btn" onclick="" />
					</center>
			</s:form>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		loadYq();
		loadFacilityType();
	});

	$("#formTerminal").submit(function(e){
		var facilityId=$("#facilityId").val();
		var facilityName=$("#facilityName").val();
		var district=$("#district").val();
		var facilityStatus=$("#facilityStatus").val();
		var putAddress=$("#putAddress").val();
		// var remark=$("#remark").val();
		var facilityType=$("#facilityType").val();
		
		if($.trim(facilityId).length==0 || $.trim(facilityName).length==0 
			|| $.trim(district).length==0 || $.trim(facilityStatus).length==0 || $.trim(putAddress).length==0
			|| $.trim(facilityType).length==0){
			$.jBox.alert('请填写完整信息', '提示');
			return false;
		}
		
		if($.trim(facilityId).length > 20){
			$.jBox.alert('终端编号不能超过20位', '提示');
			return false;
		}
		
　　		if(/.*[\u4e00-\u9fa5]+.*$/.test($.trim(facilityId))){
			$.jBox.alert('终端编号不能包含中文', '提示');
			return false;
		}
		
		var checkResult = false;
		// 检查终端编号是否已存在
		$.ajax({
			type : "post",
			url : "selfService/selfServiceManageAction_checkFacilityId",
			data:{"facilityId":facilityId},
			dataType : "json",
			async : false,
			success : function(data) {
				if(data.result){
					checkResult = true;// 终端编号不存在，可以添加
				}else{
					jBox.tip("终端编号“" + facilityId + "”已存在！", 'info');
				}
			},error : function(errorMsg) {
				jBox.tip("终端编号检查失败，请重新尝试", 'info');
			}
		});
		//return true;
		return checkResult;
	});
	
	/**
	 * 加载院区
	 */
	function loadYq(){
		var url = "unpaid/allYqJson";
		var dis = $('#district');
		dis.empty();
		$.get(url , function(data, status){
			var jarr = eval(data);
			for (var i in jarr){
				var o = $('<option></option>').val(jarr[i].yqId).html(jarr[i].name);
				dis.append(o.get());
			}
		});
	};
	
	/**
	 * 加载设备类型
	 */
	function loadFacilityType(){
		var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"facility_type";
		var body = $('#facilityType');
		body.empty();
		$.get(url , function(data, status){
			var arr1 = eval(data);
	        var arr=eval("("+arr1.data+")");
			for (var i in arr){
				var o = $('<option></option>').val(arr[i].codeNo).html(arr[i].codeName);
				body.append(o.get());
			}
		});
	};
</script>
</html>