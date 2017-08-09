<%@page import="com.catic.mobilehos.po.UserPO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="patients">
	<table class="list-table">
		<thead>
			<tr>
				<th align="center" width="15%">姓名</th>
				<th align="center" width="15%">性别</th>
				<th align="center" width="20%">身份证号</th>
				<th align="center" width="20%">手机号</th>
				<th align="center" width="30%">就诊卡</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="patientPO" value="patientPOs">
				<tr>
					<td align="center" valign="middle"><s:property value="#patientPO.name" /></td>
					<td align="center" valign="middle"><s:if test="#patientPO.sex==1">男</s:if> <s:else>女</s:else>
					<td align="center" valign="middle"><s:property value="#patientPO.identityCard" /></td>
					<td align="center" valign="middle"><s:property value="#patientPO.phone" /></td>
					<td align="left"><s:iterator var="card"
							value="#patientPO.cards" status="index">
							<s:if test="#card.cardCode=='00001' && #card.cardNumber!=''">诊疗卡号:(<s:property
									value="#card.cardNumber" />)<s:if test="!#index.last">、<br>
								</s:if>
							</s:if>
							<s:elseif test="#card.cardCode=='00002' && #card.cardNumber!=''">社保卡号:(<s:property
									value="#card.cardNumber" />)<s:if test="!#index.last">、<br>
								</s:if>
							</s:elseif>
							<s:elseif test="#card.cardCode=='00003' && #card.cardNumber!=''">居民健康卡:(<s:property
									value="#card.cardNumber" />)<s:if test="!#index.last">、<br>
								</s:if>
							</s:elseif>
						</s:iterator></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>