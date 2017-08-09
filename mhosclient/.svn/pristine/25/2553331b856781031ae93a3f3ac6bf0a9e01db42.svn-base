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
<div class="menu f-fl">
	<div class="menu-item">
		<div class="menu-item-hd open-icon icon">
			<div class="hd-icon-business icon f-fl">业务</div>
		</div>
		<ul class="menu-item-cnt" style="display: block;">
			<li><a
				href="${pageContext.request.contextPath}/hosinfomng/hos_pub_info_main.jsp"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_HOSINFO')"> style="color:#cdcccc" </s:if>>
					发布医院资讯 </a></li>
					
			<li><a
				href="${pageContext.request.contextPath}/busrecord/appreg/app_reg_main.jsp"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_HOSINFO')"> style="color:#cdcccc" </s:if>>
					预约记录 </a></li>
			<li><a
				href="${pageContext.request.contextPath}/busrecord/appreg/regist_main.jsp"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_HOSINFO')"> style="color:#cdcccc" </s:if>>
					挂号记录 </a></li>
			<%-- <li><a
				href="${pageContext.request.contextPath}/busrecord/appreg/payMent_main.jsp"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_HOSINFO')"> style="color:#cdcccc" </s:if>>
					缴费记录</a></li> --%>
			<li><a
				href="${pageContext.request.contextPath}/trade/trade_main.jsp"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_HOSINFO')"> style="color:#cdcccc" </s:if>>
					充值记录</a></li>
			<%-- <li><a
				href="${pageContext.request.contextPath}/busrecord/orderpay/order_pay_main.jsp"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_HOSINFO')"> style="color:#cdcccc" </s:if>>
					预约缴费记录 </a></li> --%>
					
			<%-- <li><a
				href="${pageContext.request.contextPath}/busrecord/pay/pay_main.jsp"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_HOSINFO')"> style="color:#cdcccc" </s:if>>
					缴费记录 </a></li> --%>
			<li><a
				href="${pageContext.request.contextPath}/busrecord/appreg/payMent_main.jsp"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_HOSINFO')"> style="color:#cdcccc" </s:if>>
					缴费记录 </a></li>
					
			
			<li><a
				href="${pageContext.request.contextPath}/busrecord/evaluate/eva_hos_main.jsp"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_HOSINFO')"> style="color:#cdcccc" </s:if>>
					服务评价记录 </a></li>
					
					
		<%-- <li><a
			 href="${pageContext.request.contextPath}/busrecord/paycheck/pay_check_main.jsp"
				class="f-ib""
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_HOSINFO')"> style="color:#cdcccc" </s:if>>
					手机支付对账 </a></li> --%>
					
			<%-- <li><a
				href="${pageContext.request.contextPath}/busrecord/appreg/app_reg_main.jsp"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_HOSINFO')"> style="color:#cdcccc" </s:if>>
					业务记录 </a></li> --%>
		</ul>
	</div>
	
		<div class="menu-item">
		<div class="menu-item-hd open-icon icon">
			<div class="hd-icon-business icon f-fl">交易中心</div>
		</div>
		<ul class="menu-item-cnt">
			<li><a class="f-ib" href="${pageContext.request.contextPath}/payorder/payorder_main.jsp">支付管理</a></li>
			<li><a class="f-ib" href="${pageContext.request.contextPath}/transFlow/payorder_main.jsp">交易流水</a></li>
			<li><a class="f-ib" href="${pageContext.request.contextPath}/refund/refund_main.jsp">退账记录</a></li>
			<li><a class="f-ib" href="${pageContext.request.contextPath}/check/check_main.jsp">对账</a></li>	
			<li><a class="f-ib" href="${pageContext.request.contextPath}/transaction/jygl/accountConfig_main.jsp">交易概览</a></li>	
			<li><a class="f-ib" href="${pageContext.request.contextPath}/yqconfig/yqConfig_main.jsp">支付配置</a></li>								
		</ul>
	</div>
	
	
	<div class="menu-item">
		<div class="menu-item-hd open-icon icon">
			<div class="hd-icon-count icon f-fl">统计</div>
		</div>
		<ul class="menu-item-cnt">
			<li><a class="f-ib"  href="${pageContext.request.contextPath}/statistics/members.jsp"> 会员增量统计 </a></li>
			<li><a class="f-ib"  href="${pageContext.request.contextPath}/statistics/appointment.jsp"> 预约统计 </a></li>
			<li><a class="f-ib"  href="${pageContext.request.contextPath}/statistics/registration.jsp"> 挂号统计 </a></li>
			<%-- <li><a class="f-ib" href="${pageContext.request.contextPath}/statistics/evaluate.jsp"> 服务评价统计 </a></li>
			 --%>
			 <li><a class="f-ib" href="${pageContext.request.contextPath}/statistics/evaluateHos.jsp"> 服务评价统计 </a></li>
			<!-- <li><a class="f-ib" style="color: #cdcccc"> 在线缴费统计 </a></li>
			<li><a class="f-ib" style="color: #cdcccc"> 咨询服务统计 </a></li>
			<li><a class="f-ib" style="color: #cdcccc"> 健康咨询统计 </a></li>
			<li><a class="f-ib" style="color: #cdcccc"> 医院动态统计 </a></li> -->
		</ul>
	</div>
	<div class="menu-item">
		<div class="menu-item-hd open-icon icon">
			<div class="hd-icon-set icon f-fl">配置</div>
		</div>
		<ul class="menu-item-cnt">
			<li><a
				href="${pageContext.request.contextPath}/config/basic/showHosIntroCfgPage"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_BASIC_CON')"> style="color:#cdcccc" </s:if>>
					基础内容配置 </a></li>
			 <li><a
				href="${pageContext.request.contextPath}/config/department/department_cfg.jsp"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_DEPARTMENT')"> style="color:#cdcccc" </s:if>>
					科室介绍管理 </a></li>
					
					 <li><a
				href="${pageContext.request.contextPath}/config/doctor/doctor_cfg.jsp"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_DOCTOR')"> style="color:#cdcccc" </s:if>>
					医生管理 </a></li>
					
					
			 <li><a
				href="${pageContext.request.contextPath}/config/msgtemplate/showMsgTemplateCfgPage"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_MSG')"> style="color:#cdcccc" </s:if>>
					消息模板配置 </a></li> 
			 <li>
				<a href="${pageContext.request.contextPath}/config/wechat/showWechatCfg" class="f-ib">微信消息配置 </a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/config/menu/showWechatMenu" class="f-ib">微信菜单配置 </a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/config/wechat/basecontent_cfg.jsp" class="f-ib">微信基础配置 </a>
			</li>  
			<%-- <li><a
				href="${pageContext.request.contextPath}/config/version/showVersion"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_CLIENT_VER')"> style="color:#cdcccc" </s:if>>
					客户端版本发布 </a></li> --%>

		</ul>
	</div>
	<div class="menu-item">
		<div class="menu-item-hd open-icon icon">
			<div class="hd-icon-manage icon f-fl">管理</div>
		</div>
		<ul class="menu-item-cnt no-border">
			<li><a
				href="${pageContext.request.contextPath}/manager/user/showUsers"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_USER')"> style="color:#cdcccc" </s:if>>
					会员管理 </a></li>
<!-- 			<li><a -->
<!-- 				href="${pageContext.request.contextPath}/manager/serUser/serUser.jsp" -->
<!-- 				class="f-ib"> -->
<!-- 					医护人员管理 </a></li> -->
			<li><a
				href="${pageContext.request.contextPath}/manager/sysUser/showSysUsers"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_SYS_USER')"> style="color:#cdcccc" </s:if>>
					系统用户管理 </a></li>
					
					
			<li><a
				href="${pageContext.request.contextPath}/questionnaire/questionnaire.jsp"
				class="f-ib"
				<s:if
						test="#session.userName==null || !#session.authority.contains('ROLE_HOSINFO')"> style="color:#cdcccc" </s:if>>
					服务评价问卷管理 </a></li>
		</ul>
	</div>
</div>