<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.catic.mobilehos.service.vo.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


	<table id="tb_cnt" class="list-table">
		<thead>
			<tr>
				<th style="margin-left: 5px;margin-right: 5px;">满意数</th>
				<th style="margin-left: 5px;margin-right: 5px;">一般数</th>
				<th style="margin-left: 5px;margin-right: 5px;">差评数</th>
				
			</tr>
		</thead>
		<tbody id="tb_cnt_body">
		<tr>
		<td align="center">${serviceEvaluationHosVO.goodNum}</td>
		<td align="center">${serviceEvaluationHosVO.commonNum}</td>
		<td align="center">${serviceEvaluationHosVO.badNum}</td>
		
		</tr>
		
			
		</tbody>
	</table>