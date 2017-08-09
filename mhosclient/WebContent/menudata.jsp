<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script charset="utf-8" src="resources/js/common.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('.menu-item-hd').click(function() {
			$('.menu-item-cnt').attr("style", "display:none;");
			$(this).siblings().attr("style", "display:block;");			
		});
		/*单击显示选中菜单  */
		$("li").click(function(){
			/* if (cells.length > 0) {//如果页面上存在cell
				$('.f-ib').removeClass("active");//移除所有的cell
			} */
			$('.menu-item-cnt li').removeClass("active");
			$(this).addClass("active").siblings().removeClass("active");
			
		//$(this).css({'background':'blue','color':'black'});
		});
	});
</script>
<div class="menu f-fl">
	
	<s:iterator value="#session.menu" var="menu" status="status">
	<div class="menu-item">
		<div class="menu-item-hd open-icon icon">
			<div class="${menu.url}">${menu.name}</div>
		</div>
		<s:if test="%{#status.index == 0}">
			<ul class="menu-item-cnt" style="display: block;">
		</s:if>
		<s:else>
			<ul class="menu-item-cnt">
		</s:else>
		<s:iterator value="#menu.submenus" var="submenu">
			<li><a href="mhosclient/${submenu.url}?key=<s:property value="#status.index"/>" target="mainframe" class="f-ib">${submenu.name} </a></li>
		</s:iterator>
		</ul>
	</div>
	</s:iterator>
	<div class="menu-item">
		<div class="menu-item-hd open-icon icon">
		
			<ul class="menu-item-cnt icon" style="display: block;">员工信息管理
				
				<li ><a href="employee/employee_add.jsp" target="mainframe" class="f-ib"></a>增加新员工</li>
				<li ><a href="employee/employee_manage.jsp" target="mainframe" class="f-ib"></a>员工信息管理</li>
				<li >aa</li>
			</ul>
		</div>
	</div>
	<div class="f-clear"></div>
</div>
