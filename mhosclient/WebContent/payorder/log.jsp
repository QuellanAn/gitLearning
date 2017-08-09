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
<title>执行日志</title>
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
</head>
<!-- <s:if
	test="#session.userName==null">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<script type="text/javascript">
//查看详情
    function findDetails(orderId){
        
        window.location.href="unpaid/payOrderAction_findDetails?orderId="+orderId;
    }
</script>
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li ><a href="javascript:findDetails(${orderId})">订单详情</a></li>
				<s:if test="payStatus==3">
					<li ><a href="../unpaid/refundAction_findDetails?tradeNo=${out_trade_no}">退款记录详情</a></li>
				</s:if>
				<li class="tab-crt">交易日志</li>
			</ul>
				<div class="f-fl left-list-wrap" style="margin-bottom: 10px;">
					<div id="div_cnt_table">
					<div class="left-list-hd">
					<table width="100%" height="100%" style="margin-top: 0px;">
							<tr >	
								<td align="right" width="60">
									<button class="save-btn" onclick="history.go(-1)">返回</button>
								</td>
							</tr>
						</table>
					</div>
			           <table id="tb_cnt" class="list-table" style="margin-top: 5px;">
		     <thead>
			   <tr>
				<th style="margin-left: 5px;margin-right: 5px;">序号</th>
				<th style="margin-left: 5px;margin-right: 5px;">执行时间</th>
				<th style="margin-left: 5px;margin-right: 5px;">任务状态</th>
				<th style="margin-left: 5px;margin-right: 5px;">执行状态</th>
				<th style="margin-left: 5px;margin-right: 5px;">失败类型</th>
			  </tr>
		    </thead>
		    <tbody id="tb_cnt_body">
			 <s:iterator value="plList" var="plList" status="status1">
             <tr>
               <td align="center">${status1.count}</td> 
               <td align="center">${createTime.substring(0,16)}</td> 
               <td align="center">${itemName}</td> 
               <td align="center"><s:if test='#plList.status==0'>成功</s:if>
                 <s:if test='#plList.status==1'>失败</s:if>
               </td> 
               
               <td align="center"></td> 
             </tr>
           </s:iterator>
		</tbody>
						</table>
					</div>
					<div class="f-clear"></div>
				</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>