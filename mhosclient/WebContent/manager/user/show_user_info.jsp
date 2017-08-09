<%@page import="com.catic.mobilehos.po.UserPO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>
<base href="<%=basePath%>/" />
<title>用户信息详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/hos_pub_info_main_sp.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="manager/user/user.js"></script>
<script charset="utf-8" src="js/util.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script type="text/javascript">
$(document).ready(function(){	
	var num ;
	var height ;
	if($("#num").val() == 0){
		height = 100+"px";
	}else{
		num = parseInt($("#num").val());
		height = (num+1)*42+10+"px";
	}
	$("#wx").css({"height":height});
});
</script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_HOSINFO')">
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
				<li class="tab-crt"><a href="manager/patientsUser/patients_user_main.jsp">用户信息查询</a></li>
			</ul>
			<div class="tab-cnt">
				<div style="margin-left : 50px;width: 1000px;border: 1px solid #ccc;">
					<div style="background-color: #f3f3f3;height: 42px;padding-left: 20px;">用户信息</div>
					<input type="hidden" id="userName" value="${userPO.userName }" />
					<div style="width: 100%;height: 126px;">
						<div class="patient-feft1">
							<div class="patient-feft">
								<div style="height: 100%;float: left;width: 78px;">账号 </div>
								<div class="patient-feft">${userPO.userName }</div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 78px;">性别   </div>
								<div class="patient-feft"><s:if test="#userPO.sexCode==1">女</s:if> <s:else>男</s:else> </div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 78px;">账号状态 </div>
								<div class="patient-feft" style="padding-top: 0px;">
									<s:if test="%{userPO.status == 0}">正常</s:if>
									<s:if test="%{userPO.status == 1}">冻结</s:if>
									<!-- 操作是否冻结的功能 -->
									<%-- <select name="status" id="status" style="width: 60px;">
										<s:if test="%{userPO.status == 1}">
											<option value="1">冻结</option>
											<option value="0">正常</option>
										</s:if>
										<s:if test="%{userPO.status == 0}">
											<option value="0">正常</option>
											<option value="1">冻结</option>
										</s:if>
									</select> --%>
								</div>
							</div>
							<div style="clear: both;"></div>
						</div>
						<div class="patient-feft1">
							<div class="patient-feft">
								<div style="height: 100%;float: left;width: 78px;"> 昵称   </div>
								<div class="patient-feft">${userPO.nickName } </div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 78px;">地区</div>
								<div class="patient-feft">${userPO.province } ${userPO.city } </div>
							</div>
							<div style="padding-left: 50px;height: 42px;float: left;width: 100%;">
								<div style="height: 100%;float: left;width: 78px;">注册时间  </div>
								<div class="patient-feft">${fn:substring(userPO.createDate, 0, 16) } </div>
							</div>
						</div>
					</div>
				</div>
				<div style="margin-left : 50px;width: 1000px;border: 1px solid #ccc;margin-top: 20px;">
					<div style="background-color: #f3f3f3;height: 42px;padding-left: 20px;">绑定就诊人信息</div>
					<div style="width: 100%;height: 168px;padding-left: 2.5%;" id="wx">
						<table id="tb_cnt" class="list-table" style="margin-top: 10px;width: 95%;">
							<thead>
								<tr>
									<th style="margin-left: 5px;margin-right: 5px;">序号</th>
									<th style="margin-left: 5px;margin-right: 5px;">就诊卡号</th>
									<th style="margin-left: 5px;margin-right: 5px;">就诊人</th>
									<th style="margin-left: 5px;margin-right: 5px;">性别</th>
									<th style="margin-left: 5px;margin-right: 5px;">年龄</th>
									<th style="margin-left: 5px;margin-right: 5px;">手机号</th>
									<th style="margin-left: 5px;margin-right: 5px;">身份证号</th>
									<th style="margin-left: 5px;margin-right: 5px;">状态</th>
									<th style="margin-left: 5px;margin-right: 5px;">操作</th>
								</tr>
							</thead>
							<tbody id="tb_cnt_body">
							<input id="num" type="hidden" value="<s:property value='#request.patientPOs.size()'/>" />
								<s:iterator value="patientPOs" var="patientPO" status="p">
									<tr>
										<td align="center"><s:property value="#p.index+1"/></td>
										<td align="center">${cardNo}</td>
										<td align="center">${patientName} </td>
										<td align="center"><%-- <s:if test="#patientPO.sexCode==1">女</s:if> <s:else>男</s:else> --%></td>
										<td align="center">${birthday} </td>
										<td align="center">${phone} </td>
										<td align="center">${identityCard} </td>
										<td align="center"><c:if test="${status == 0}">正常</c:if><c:if test="${status == 1}">冻结</c:if>  </td>
										<td align="center">
											<span onclick="showPatientUser(value= '${patientId} ');"
												style="color: #0079ff; text-decoration: underline;cursor:pointer;">编辑</span> 
										</td>
									</tr>
								</s:iterator>
								<s:if test="#request.patientPOs.size() <= 0"><tr><td align="center" colspan="11" >暂无数据</td></tr></s:if>
							</tbody>
						</table>
					</div>
				</div>
				<div style="height: 100px;margin-left : 50px;width: 1000px;text-align: center;">
					<div style="height: 100%;width: 100%; line-height:100px ;">
						<button id="act_query2" class="save-btn" onclick="updateStatus();">保存</button>
						<button id="act_query1" class="save-btn" style="margin-left: 50px;" onclick="javascript:history.go(-1);">返回</button>
					</div>
				</div>
			</div>
		</div>

		<div class="f-clear"></div>
	</div>
		<div id="showPayInfo"></div>
		<div id="showPatients"></div>
</body>
</html>






