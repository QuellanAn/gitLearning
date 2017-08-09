<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	List<MsgBusMainTypeVO> msgBusMainTypes = (List<MsgBusMainTypeVO>) request
	.getAttribute("msgBusMainTypes");
%>
<html>
<head>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微信消息配置</title>
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script charset="utf-8" src="resources/js/common.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script charset="utf-8" src="config/wechat/msg_template_cfg.js"></script>
</head>
<!-- <s:if test="#session.userName==null ||!#session.authority.contains('ROLE_MSG')">
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
				<li class="tab-crt">微信消息配置</li>
			</ul>
			<div class="tab-cnt">
				<div id="div_info_cat" class="f-fl left-list-wrap" style="border: 1px solid #dcdcdc; height: 445px; width: 1000px; margin-left: 80px;margin-bottom: 10px;">
						<table style="margin-left: 15px; width: 95%">
							<tr>
								<td></td>
								<td>模版内容(Tips: &lt;br&gt; 表示换行.)</td>
								<!-- <td>可用变量</td> -->
							</tr>
							<tr>
								<td align="center">关注成功：</td>
								<td><textarea rows="10" cols="120" style="resize: none;" id="wechat_msg_subscribe" placeholder="留空表示不启用此消息">${data.wechat_msg_subscribe }</textarea></td>
								<!-- <td>
									<p style="border: 1px solid gray; overflow-y: scroll; height: 80px; line-height: 20px;">无</p>
								</td> -->
							</tr>
							<%-- <tr>
								<td align="center">预约挂号：</td>
								<td><textarea rows="5" cols="60" style="resize: none;"  id="wechat_msg_appreg" placeholder="留空表示不启用此消息">${data.wechat_msg_appreg }</textarea></td>
								<td>
									<p style="border: 1px solid gray; overflow-y: scroll; height: 80px; line-height: 20px;">
										患者:{patient}</br>
										时间:{datetime}</br>
										科室:{department}</br>
										单号:{appnumber}</br>
										预约记录页面:{yyjlpage}
									</p>
								</td>
							</tr>
							<tr>
								<td align="center">取消挂号：</td>
								<td><textarea rows="5" cols="60" style="resize: none;" id="wechat_msg_unappreg" placeholder="留空表示不启用此消息">${data.wechat_msg_unappreg }</textarea></td>
								<td>
									<p style="border: 1px solid gray; overflow-y: scroll; height: 80px; line-height: 20px;">
										患者:{patient}</br>
										时间:{datetime}</br>
										科室:{department}</br>
										单号:{appnumber}</br>
									</p>
								</td>
							</tr> --%>
						</table>
						<br />
						<center>
							<button id="msg_save" class="normal-btn">保存</button>
						</center>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>

