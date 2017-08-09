<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="right-list-hd"
	onclick="javascript:datalist_ctrl.loaddatalist(null,null,1);return false;">
	全部（
	<s:property value="catMenu.totalCount" />
	）
</div>
<div class="right-list-item">
	<div class="item-hd"
		onclick="javascript:datalist_ctrl.loaddatalist(1,null,1);return false;">
		健康资讯（
		<s:property value="catMenu.healthInfoCount" />
		）
	</div>
	<div class="item-cnt">
		<s:append var="statByCatVOs">
			<s:param value="%{catMenu.healthStatCat}" />
		</s:append>
		<ul>
			<s:iterator var="v" value="%{#statByCatVOs}">
				<li
					onmouseover="javascript:cat_menu_ctrl.cat_onmouseover('<s:property value="#v.infoCatCode"/>');"
					onmouseout="javascript:cat_menu_ctrl.cat_onmouseout('<s:property value="#v.infoCatCode"/>');">
					<a id="cat_<s:property value="#v.infoCatCode"/>" href="#"
					onclick="javascript:datalist_ctrl.loaddatalist(1, '<s:property value="#v.infoCatCode"/>', 1);return false;"><s:property
							value="#v.infoCatName" />(<s:property value="#v.statCount" />)</a>
					<input style="display: none;"
					id="inp_<s:property value="#v.infoCatCode"/>" type="text" size="5" />
					<span
						class="icon" title="编辑" style="display: none;width: 14px;height: 15px;margin-left: 3px;background-position:0 -169px; "
						id="edit_<s:property value="#v.infoCatCode"/>"
						onclick="javascript:cat_menu_ctrl.cat_edit_onclick('<s:property value="#v.infoCatCode"/>', '<s:property value="#v.infoCatName"/>');">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<span
						class="icon" title="删除" style="display: none;width: 13px;height: 15px;margin-left: 2px;background-position:0 -192px;"
						id="del_<s:property value="#v.infoCatCode"/>"
						onclick="javascript:cat_menu_ctrl.cat_del_onclick('<s:property value="#v.infoCatCode"/>');">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img width="14" height="15" src="../resources/images/save.png" title="保存"
						style="display: none;"
						id="save_<s:property value="#v.infoCatCode"/>"
						onclick="javascript:cat_menu_ctrl.cat_save_onclick('<s:property value="#v.infoCatCode"/>', '<s:property value="#v.statCount" />');"/>
						
					<img width="14" height="15" src="../resources/images/cancel.png" title="取消"
						style="display: none; margin-left: 3px;"
						id="cancel_<s:property value="#v.infoCatCode"/>"
						onclick="javascript:cat_menu_ctrl.cat_cancel_onclick('<s:property value="#v.infoCatCode"/>');">
						
				</li>
			</s:iterator>
		</ul>
	</div>
</div>
<div class="right-list-item">
	<div class="item-hd"
		onclick="javascript:datalist_ctrl.loaddatalist(2,null,1);return false;">
		医院动态（
		<s:property value="catMenu.newsInfoCount" />
		）
	</div>
	<div class="item-cnt">
		<s:append var="statByCatVOs">
			<s:param value="%{catMenu.newsStatCat}" />
		</s:append>
		<ul>
			<s:iterator var="v" value="%{#statByCatVOs}">
				<li
					onmouseover="javascript:cat_menu_ctrl.cat_onmouseover('<s:property value="#v.infoCatCode"/>');"
					onmouseout="javascript:cat_menu_ctrl.cat_onmouseout('<s:property value="#v.infoCatCode"/>');">
					<a id="cat_<s:property value="#v.infoCatCode"/>" href="#"
					onclick="javascript:datalist_ctrl.loaddatalist(2, '<s:property value="#v.infoCatCode"/>', 1);return false;"><s:property
							value="#v.infoCatName" />(<s:property value="#v.statCount" />)</a>
					<input style="display: none;"
					id="inp_<s:property value="#v.infoCatCode"/>" type="text" size="5" />
					<span
						class="icon" title="编辑" style="display: none;width: 14px;height: 15px;margin-left: 3px;background-position:0 -169px; "
						id="edit_<s:property value="#v.infoCatCode"/>"
						onclick="javascript:cat_menu_ctrl.cat_edit_onclick('<s:property value="#v.infoCatCode"/>', '<s:property value="#v.infoCatName"/>');">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<span class="icon" title="删除" style="display: none;width: 13px;height: 15px;margin-left: 2px;background-position:0 -192px;"
						id="del_<s:property value="#v.infoCatCode"/>"
						onclick="javascript:cat_menu_ctrl.cat_del_onclick('<s:property value="#v.infoCatCode"/>');">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img width="14" height="15" src="../resources/images/save.png" title="保存"
						style="display: none;"
						id="save_<s:property value="#v.infoCatCode"/>"
						onclick="javascript:cat_menu_ctrl.cat_save_onclick('<s:property value="#v.infoCatCode"/>', '<s:property value="#v.statCount" />');"/>
					<img width="14" height="15" src="../resources/images/cancel.png" title="取消"
						style="display: none; margin-left: 3px;"
						id="cancel_<s:property value="#v.infoCatCode"/>"
						onclick="javascript:cat_menu_ctrl.cat_cancel_onclick('<s:property value="#v.infoCatCode"/>');"/>

				</li>
			</s:iterator>
		</ul>
		<a href="#" class="edit-item"> &nbsp; </a>
	</div>
</div>
<div id="div_add_win">
	<select id="sel_type" style="width: 80px;">
		<option value="1">健康资讯</option>
		<option value="2">医院动态</option>
	</select> 
	<input id="inp_catname" style="width: 80px;" /> <br />
	<center><button id="btn_addcat" class="normal-btn"
		style="text-align: center; padding: 0; height: 30px;width: 50px;"
		onclick="javascript:cat_menu_ctrl.cat_add_onclick();">添加</button>
		<button  class="normal-btn"
		style="text-align: center; padding: 0; height: 30px;width: 50px;"
		onclick="javascript:$('#div_add_win').hide();">取消</button></center>
</div>
<div class="add-item">
	<a href="#" class="icon add-icon f-ib"
		onclick="javascript:cat_menu_ctrl.show_addwin();return false;">添加新分类</a>
</div>