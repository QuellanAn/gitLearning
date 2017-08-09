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
	List<WechatMainMenuVO> wechatVO = (List<WechatMainMenuVO>) request
	.getAttribute("wechatVO");
%>


<html>
<head>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微信菜单配置</title>
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script charset="utf-8" src="resources/js/common.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script charset="utf-8" src="config/menu/menu.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_MSG')">
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
				<li class="tab-crt">微信菜单配置</li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl right-list-wrap">
					<div id="div_msgcat_title" class="right-list-hd"
						style="cursor: default;font-size: 16px;">
						菜单管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
				onclick="addStatus()"
				style="color: #0079ff; text-decoration: underline;cursor:pointer;"> +</span>
						</a>
					</div>
					<%
						for (WechatMainMenuVO w : wechatVO) {
							if (w.getParentId().equals("0")) {
					%>
					<div class="right-list-item">
						<div class="item-hd" style="cursor: default;">
							<li style="list-style-type:none;">
							<a onclick="javascript:select('<%=w.getId()%>');"><%=w.getName()%></a></li>
						</div>
						<div class="item-cnt">
							<ul>
								<%
									for (WechatMainMenuVO wm : wechatVO) {
											if (wm.getParentId().equals(w.getId())) {
								%>
								<li><a href="#"
									onclick="javascript:select('<%=wm.getId()%>');return false;"
									style="color: #0079ff; text-decoration: underline;"> <%=wm.getName()%>&nbsp;</a>
									</li>
								<%
									}
								%>
								<%
									}
								%>
							</ul>
						</div>
					</div>
					<%
						}
					%>
					<%
						}
					%>
					
				</div>
				<div id="div_info_cat" class="f-fl left-list-wrap"
					style="border: 1px solid #dcdcdc; height: 590px;float: none;display: block;">
					<input type="hidden" id="id" />
					<table style="padding-left: 100px;">
						<tr>
							<td width="20%" align="center">菜单名称：</td>
							<td><input id="name" name="name" style="width:130px; height: 25px;"></input>
							</td>	
							
						</tr>
						<%-- <tr>
							<td width="20%" align="center">菜单级别：</td>	
							<td><select id="level" name="level" style="width:80px; height: 30px;">
								<option value="0">一级菜单</option>
								<option value="1">二级菜单</option>
							    </select>
							   &nbsp;&nbsp; 所属菜单:&nbsp;&nbsp;&nbsp;&nbsp;
							    <select id="submenu" name="submenu" style="width:80px; height: 30px;">
							    </select>
							
							</td>
						</tr> --%>
						
						<tr>
							<td align="center">发送方式：</td>
							<td><input id="click" name="click" type="checkbox" onclick="check()" />&nbsp;&nbsp;点击事件&nbsp;&nbsp;&nbsp;
								<input id="view" name="view" type="checkbox" onclick="check2()"/>&nbsp;&nbsp;跳转到网页&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td align="center">消息模版：</td>
							<td><textarea id="url" name="url" rows="12" cols="58"></textarea></td>
						</tr>
						<tr>
							<td align="center">发送方式：</td>
							<td><input id="key" name="key" type="checkbox" onclick="check3()"/>&nbsp;&nbsp;在线客服&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</table>
					<br />
					<center>
						<button id="act_add" class="normal-btn">新增</button>
						<button style="margin-left: 30px;" id="act_save" class="normal-btn">保存</button>
						<button style="margin-left: 30px;" id="act_pub" class="normal-btn">发布</button>
						<button style="margin-left: 30px;" id="act_dele" class="normal-btn">删除</button>
					</center>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
	<div id="setMenu" title="状态更改">
		<form id="setMenu" onsubmit="return false;">
			<table width="100%" cellpadding="7">
			</table>
		</form>
	</div>


</body>
</html>

