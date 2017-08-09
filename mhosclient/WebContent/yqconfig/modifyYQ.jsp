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
<title>修改院区配置</title>
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
				<li class="tab-crt">修改院区配置</li>
			</ul>

			<div class="tab-cnt" style="overflow: auto;">

			<s:form method="post" action="yQConfigAction_modifyYQ"  id="form_modifyYQ" cssClass="introduction-fm">
					<ul>
						<li><span class="introduction-label f-fl"> <span
								class="color-red">*</span>院区名称： </span>
								<s:textfield
								id="yqName" name="yq.name"
								cssClass="fm-text f-fl" style="width:550px;" />
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl"> <span
								class="color-red">*</span>院区联系人: </span> 
								<s:textfield
								id="linkManName" name="yq.linkManName"
								Class="fm-text f-fl" style="width:550px;"/>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl"> <span
								class="color-red">*</span>联系人电话: </span> <s:textfield
								id="linkPhone" name="yq.linkPhone"
								Class="fm-text f-fl" style="width:550px;"/>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl">院区地址: </span> 
						 <s:textfield
								id="address" name="yq.address"
								 Class="fm-text f-fl" style="width:550px;"/>
							<div class="f-clear"></div></li>
					</ul>

					<br />
					<center>
					     <s:hidden name="yq.yqId"></s:hidden>
					      <s:hidden name="yq.yqCode"></s:hidden>
					      <s:hidden name="pageNo"></s:hidden>
						<input id="saveYQ" type="submit"  value="提交"
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
	
	$("#form_modifyYQ").submit(function(e){
				
		var yqName=$("#yqName").val();
		var linkManName=$("#linkManName").val();
		var linkPhone=$("#linkPhone").val();
		var address=$("#address").val();
		
		if($.trim(yqName).length==0 ||$.trim(linkManName).length==0||$.trim(linkPhone).length==0||$.trim(address).length==0){
		alert("请输出完整的信息");
		return false;
		}
		return true;
	});
	
/* 	$("#saveYQ").click(function(e){
	    var cnt=${"."};
		$.post($("#form_modifyYQ").submit(),{
		yqCode:yqCode,
		pageNo:pageNo
	},
		function(data, status){
			if (data && data.msg){
				alert(data.msg);
			}else if (data){
				alert("删除成功！");
				cnt.empty();
				cnt.append(data);
			}
		});		
	
	}); */
</script>
</html>