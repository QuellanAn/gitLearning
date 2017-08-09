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
<title>退款查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script charset="utf-8" src="refund/refund.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">退款查询</li>
				<li><a href="../refund/applyRefund.jsp">申请退款</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">退款记录列表(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd" style="height:120px;">
							<table height="100%" >
							<tr>
									<td valign="middle" align="right">&nbsp;&nbsp;退款单号:</td>
									<td valign="middle" colspan="3">
									<input type="text" id="refundNo"  size="10"  style="height: 28px;width:200px;line-height: 28px;" c>
									</td>
									<td valign="middle" align="right" colspan="2">&nbsp;&nbsp;第三方退款流水号:</td>
									<td valign="middle" colspan="3">
									<input type="text" id="refundId"  size="10"  style="height: 28px;width:200px; line-height: 28px;">
									</td>
									<td valign="middle" align="right" colspan="2">原交易单号:</td>
									<td valign="middle">
									<input type="text"  size="10"  id="tradeNo" style="height: 28px; line-height: 28px;width:200px; widows: ">
									</td>									
									</tr>
								<tr>								
									<td valign="middle" align="left">&nbsp;&nbsp;院区:</td>
										<td valign="middle" align="left"><select id="districtId"
										    style="height: 28px;width:80px">									
									</select></td>
									<td valign="middle" align="right">&nbsp;&nbsp;资金账户:</td>
										<td valign="middle" align="left"><select id="account"
										    style="height: 28px;width:100px">									
									</select></td>
									<td valign="middle" align="right">&nbsp;&nbsp;退款方:</td>
										<td valign="middle" align="left"><select id="pattern"
										    style="height: 28px;width:80px">
									</select></td>
									 <td valign="middle" align="right">退款时间:</td>
								     <td valign="middle" align="right" colspan="2"><input name="tk_startdate"
										type="text" size="10" style="height: 28px; line-height: 28px; width:100px;" /></td>
									<td valign="middle">-</td>
									<td valign="middle" align="right"><input name="tk_enddate"
										type="text" size="10" style="height: 28px; line-height: 28px;width:100px;" /></td>
									</tr>
									<tr>
								    <td valign="middle" align="right">&nbsp;&nbsp;退款方式:</td>
								 		<td valign="middle" align="left"><select id="refundType"
										    style="height: 28px;width:80px">
										    <option value="">全部</option>
											<option value="0">自动退款</option>
											<option value="1">人工退款</option>
									</select></td>
									 <td valign="middle" align="right">&nbsp;&nbsp;退款状态:</td>
								 		<td valign="middle" align="left"><select id="refundStatus"
										    style="height: 28px;width:100px">
										    <option value="">全部</option>
											<option value="0">退款失败</option>
											<option value="1">退款成功</option>
									</select></td>
									<td valign="middle" align="right">&nbsp;&nbsp;操作人:</td>
									<td valign="middle">
									<input type="text"  size="10"  id="operatorName" style="height: 28px; line-height: 28px;width:80px; widows: ">
									</td>
										<td valign="middle" align="right"><button id="act_query"
											class="save-btn">查询</button></td>
									<td valign="middle" align="right"  colspan="5"><button 
											class="save-btn" onclick="exportRefund()">导出</button></td>		
									</tr>			
									</tr>
							</table>
					</div>
					
<!-- 				<div class="left-list-hd" style="height:40px;margin:0px;border-top:none;">
							<table height="100%" >
									<tr>
								 <td valign="middle" align="right">退款申请时间:</td>
								     <td valign="middle" align="right"><input name="sq_startdate"
										type="text" size="10" style="height: 28px; line-height: 28px; width:120px;" /></td>
									<td valign="middle">-</td>
									<td valign="middle" align="right"><input name="sq_enddate"
										type="text" size="10" style="height: 28px; line-height: 28px;width:120px;" /></td>
											 <td valign="middle" align="right">&nbsp;&nbsp;退款完成时间:</td>
								     <td valign="middle" align="right"><input name="tk_startdate"
										type="text" size="10" style="height: 28px; line-height: 28px;width:120px;" /></td>
									<td valign="middle">-</td>
									<td valign="middle" align="right"><input name="tk_enddate"
										type="text" size="10" style="height: 28px; line-height: 28px;width:120px;" /></td>
									<td valign="middle" align="right"><button id="act_query"
											class="save-btn">查询</button></td>
									<td valign="middle" align="right"><button 
											class="save-btn" onclick="exportRefund()">导出</button></td>		
									</tr>						
								</tr>
							</table>
					</div> -->
					<div id="div_cnt_table"></div>

				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>