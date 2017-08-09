<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="../resources/css/smoothness/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="../resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="../resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="../resources/css/index.css" />
<link rel="stylesheet" type="text/css" href="../resources/css/dialog.css" />
<div id="div_cn">
	<s:form id="myform" action="usermanage/specialist_save" method="post" enctype="multipart/form-data">
	<table id="tab-cnt" class="dialog-table">
		<tbody id="tb_cnt_body">
			<tr>
				<td align="center">医生编号<font style="color: red;">*</font></td>
				<td><input type="text" name="specialist.code" id="code" style="padding: 5px 5px;" /></td>
				<td align="center">医生姓名<font style="color: red;">*</font></td>
				<td align="center"><input type="text" name="specialist.name" id="dname" style="padding: 5px 5px;" /></td>
			</tr>
			<tr>
				<td align="center">所属科室<font style="color: red;">*</font></td>
				<td align="center">
					<select name="specialist.department" id="department" style="width:180px; padding: 5px 5px;">
						<option value=""></option>
						<option value="中西医结合科">中西医结合科</option>
						<option value="眼科">眼科</option>
						<option value="血液内科">血液内科</option>
						<option value="心血管外科">心血管外科</option>
						<option value="心血管内科">心血管内科</option>
						<option value="胸外科">胸外科</option>
						<option value="消化内科">消化内科</option>
						<option value="肾内科">肾内科</option>
						<option value="神经外科">神经外科</option>
						<option value="神经内科">神经内科</option>
						<option value="普外科">普外科</option>
						<option value="皮肤科">皮肤科</option>
						<option value="内分泌科">内分泌科</option>
						<option value="泌尿外科">泌尿外科</option>
						<option value="口腔科">口腔科</option>
						<option value="康复医学科">康复医学科</option>
						<option value="呼吸内科">呼吸内科</option>
						<option value="骨科手外">骨科手外</option>
						<option value="骨科脊柱">骨科脊柱</option>
						<option value="骨科关节">骨科关节</option>
						<option value="干部老年病科">干部老年病科</option>
						<option value="风湿科">风湿科</option>
						<option value="放疗科">放疗科</option>
						<option value="妇产科生殖病区">妇产科生殖病区</option>
						<option value="妇产科妇科病区">妇产科妇科病区</option>
						<option value="妇产科产科病区">妇产科产科病区</option>
						<option value="儿科">儿科</option>
						<option value="耳鼻喉头颈外科">耳鼻喉头颈外科</option>
						<option value="烧伤整形科">烧伤整形科</option>
						<option value="急诊内科">急诊内科</option>
						<option value="急诊外科">急诊外科</option>
						<option value="感染科">感染科</option>
						<option value="肿瘤科">肿瘤科</option>
						<option value="整形美容科">整形美容科</option>
					</select>
				</td>
				<td align="center">医生职称<font style="color: red;">*</font></td>
				<td align="center">
					<select name="specialist.type" id="type" style="width:180px; padding: 5px 5px;">
						<option value=""></option>
						<option value="主任医师">主任医师</option>
						<option value="副主任医师">副主任医师</option>
						<option value="主治医师">主治医师</option>
						<option value="医师">医师</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center">医生照片<font style="color: red;">*</font></td>
				<td colspan="3">
					<input type="file" name="file" id="photo" style="padding: 5px 5px;margin-left:50px;; float: left;"/>
					<span style="float: left; color:#C7C7C7;">请选择.gif,jpeg,jpg,png格式照片上传(2M)</span>
				</td>
			</tr>
			<tr>
				<td align="center">医生简介<font style="color: red;">*</font></td>
				<td align="center" colspan="3"><textarea rows="12" cols="90" name="specialist.depict" id="depict"></textarea> </td>
			</tr>
			<tr>
				<td align="center">擅长领域<font style="color: red;">*</font></td>
				<td align="center" colspan="3"><textarea rows="6" cols="90" name="specialist.adept" id="depict"></textarea> </td>
			</tr>
			<tr>
				<td align="center" colspan="4">
					<button id="btn_add" class="save-btn" onclick="save();return false;">提交</button>
				</td>
			</tr>
		</tbody>
	</table>
	</s:form>
</div>
