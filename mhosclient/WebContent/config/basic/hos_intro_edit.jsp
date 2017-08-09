<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	HosIntroVO hosIntroVO = (HosIntroVO) request
			.getAttribute("hosIntroVO");
%>
<html>
<head>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>医院介绍－编辑</title>
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/introduction-edit.css" />
<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script charset="utf-8" src="resources/js/fileuploader2.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script charset="utf-8" src="config/basic/hos_intro_edit.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_BASIC_CON')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt"><a href="config/basic/showHosIntroCfgPage">医院介绍</a></li>
				<!-- <li><a href="config/basic/res_notice">预约挂号须知</a></li>
				<li><a href="config/basic/guide">就医指南</a></li> -->
				<!-- <li><a href="config/basic/fun">操作指南</a></li>
				<li><a href="config/basic/faq">常见问题</a></li> -->
			</ul>
			<div class="tab-cnt" style="overflow: auto;height: 1350px;">
				<s:form action="config/basic/saveHosIntro" method="post" enctype="multipart/form-data"
					theme="simple" id="form_edit" cssClass="introduction-fm">
					<ul>
						<li><span class="introduction-label f-fl"> <span class="color-red">*</span>医院名称： </span> <s:textfield
								name="hosIntroVO.hospitalName" cssClass="fm-text f-fl"></s:textfield>
							<div class="f-clear"></div></li>
						<li><span class="introduction-label f-fl"><span class="color-red">*</span>医院级别： </span> <s:textfield
								name="hosIntroVO.level" size="35" cssClass="fm-text f-fl"></s:textfield>
							<div class="f-clear"></div>
							<p class="introduction-pmt">例如：三甲、三级、二甲</p></li>
						<li><span class="introduction-label f-fl"><span class="color-red">*</span> 医院图片： </span>
						<s:file id="hos_image" name="hosIntroVO.pic" cssClass="fm-file"></s:file>
						<img class="introduction-pmt" src="config/basic/getHosImage" id="img" height="120" width="180"/>
							<div class="f-clear"></div>
							<p class="introduction-pmt">图片建议尺寸：360像素*200像素</p></li>
						<li><span class="introduction-label f-fl"> 医院地址： </span> <s:textfield
								name="hosIntroVO.address" size="35" cssClass="fm-text f-fl"></s:textfield>
							<div class="f-clear"></div></li>
						<li><span class="introduction-label f-fl"> 联系电话： </span>
							<s:textarea
								name="hosIntroVO.phone" cssClass="fm-text introduction-textarea1 f-fl"></s:textarea>
							<div class="f-clear"></div>
							<p class="introduction-pmt">格式：每个电话号码后面以 ; 隔开</p>
							<%-- <table>
								<tr>
									<td>
										<%
											for (int i = 0; i < 3; i++) {
													HosIntroVO.ContactsVO v = null;
													if (i < hosIntroVO.getContacts().size()) {
														v = hosIntroVO.getContacts().get(i);
													}
										%> <input type="hidden" name="hosIntroVO.contacts[<%=i%>].lineId"
										value="<%=i%>" /> <input type="text"
										name="hosIntroVO.contacts[<%=i%>].name"
										value="<%=v != null ? v.getName() : ""%>"
										class="fm-text f-fl phone-text1"></input> <input type="text"
										name="hosIntroVO.contacts[<%=i%>].phone"
										value="<%=v != null ? v.getPhone() : ""%>"
										class="fm-text f-fl phone-text2"></input> <br /> <%
 	}
 %>
									</td>
								</tr>
							</table> --%>
							<div class="f-clear"></div></li>
						<li><span class="introduction-label f-fl"> 官方网址： </span> <s:textfield
								name="hosIntroVO.website" cssClass="fm-text f-fl" size="35"></s:textfield>
							<div class="f-clear"></div></li>
						<li><span class="introduction-label f-fl"> 特色科室： </span>
						<s:textarea
								id="form_featureDepartment" name="hosIntroVO.featureDepartment"
								cssClass="fm-text introduction-textarea2 f-fl"></s:textarea>
						 <!-- <s:textarea
								name="hosIntroVO.featureDepartment"
								cssClass="fm-text introduction-textarea1 f-fl"></s:textarea> -->
							<div class="f-clear"></div></li>
						<li><span class="introduction-label f-fl"> 医院介绍： </span> <s:textarea
								name="hosIntroVO.introduction" id="form_introduction"
								cssClass="fm-text introduction-textarea2 f-fl"></s:textarea>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl"> 医院路线： </span> <s:textarea
								name="hosIntroVO.busLine" id="form_busLine"
								cssClass="fm-text introduction-textarea3 f-fl"></s:textarea>
							<div class="f-clear"></div></li>

						<%-- <li><span class="introduction-label f-fl"> GPS经度： </span> <s:textfield
								name="hosIntroVO.longitude" readonly="true"
								cssClass="fm-text f-fl"></s:textfield>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl"> GPS纬度： </span> <s:textfield
								name="hosIntroVO.latitude" readonly="true"
								cssClass="fm-text f-fl"></s:textfield>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl"> GPS地点： </span> <s:textfield
								name="hosIntroVO.gpsPlace" readonly="true" disabled="disabled"
								cssClass="fm-text f-fl"></s:textfield>
							<div class="f-clear"></div></li> --%>
					</ul>
					<br/>
					<center>
						<s:submit value="保存" cssClass="normal-btn"></s:submit><br/><br/>
					</center>
				</s:form>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
<script type="text/javascript">
	var editor;
	var introduction;
	var busLine;
	editor = KindEditor.create('textarea[id="form_featureDepartment"]', {
		resizeType : 0,
		width : '615px',
		minWidth : '300px',
		height : '120px',
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		//同步值
		afterBlur : function() {
			this.sync();
		},
		items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
				'bold', 'italic', 'underline', 'removeformat', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'insertorderedlist', 'insertunorderedlist' ]
	});
	introduction = KindEditor.create('textarea[id="form_introduction"]', {
		resizeType : 0,
		width : '615px',
		minWidth : '300px',
		height : '360px',
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		//同步值
		afterBlur : function() {
			this.sync();
		},
		items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
				'bold', 'italic', 'underline', 'removeformat', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'insertorderedlist', 'insertunorderedlist' ]
	});
	busLine = KindEditor.create('textarea[id="form_busLine"]', {
		resizeType : 0,
		width : '615px',
		minWidth : '300px',
		height : '200px',
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		//同步值
		afterBlur : function() {
			this.sync();
		},
		items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
				'bold', 'italic', 'underline', 'removeformat', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'insertorderedlist', 'insertunorderedlist' ]
	});
</script>
</html>