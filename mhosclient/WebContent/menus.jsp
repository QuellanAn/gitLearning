<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('.menu-item-hd').click(function() {
			$('.menu-item-cnt').attr("style", "display:none;");
			$(this).siblings().attr("style", "display:block;");
		});
	});
</script>
<div class="menu f-fl" hidden="hidden">
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
			<li><a href="${submenu.url}?key=<s:property value="#status.index"/>" class="f-ib">${submenu.name} </a></li>
		</s:iterator>
		</ul>
	</div>
	</s:iterator>
	<div class="f-clear"></div>
</div>
