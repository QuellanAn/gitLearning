$(document).ready(function() {
	$("#act_dele").click(function(){
		var paras = {
				id : $("#id").val(),
		};
		$.post("config/menu/deleMenuContent",paras,function(data,status){
			if (data && data.msg) {
				showTips(data.msg, 100, 2);
			} else {
				 alert("删除成功！");
				 window.location.href = window.location.href.replace('#', '');
				// showTips("删除成功！", 5, 2);
			}
		});
	});
	
	$("#level").change(function(e){
		loadsubmenu($(this).val());
	});
	
//function loadmenu(type){
//	var url = "config/menu/getmenuJson?type=" + type;
//	var sel = $('#submenu');
//	sel.empty();
//	if (!type=="" && type == 0){
//		sel.append("<option value=''></option>");
//	}
//	$.get(url , function(data, status){
//		var jarr = eval(data);
//		for (var i in jarr){
//			var o = $('<option></option>').val(jarr[i].id).html(jarr[i].name);
//			sel.append(o.get());
//		}
//	});
//}
	
	
	
	
	$("#act_pub").click(function(){
		$.post("config/menu/pubMenuContent",function(data,status){
			if (data && data.msg) {
				showTips(data.msg, 100, 2);
			} else {
				 alert("保存成功！");
				//showTips("发布成功！", 100, 2);
				 window.location.href = window.location.href.replace('#', '');
			}
		});
	});

	$("#act_save").click(function() {
		var paras = {
			id : $("#id").val(),
			name : $("#name").val(),
			view : $("#view").prop("checked"),
			click : $("#click").prop("checked"),
			key : $("#key").prop("checked"),
			url : $("#url").val()
		};
		$.post("config/menu/saveMenuContent", paras, function(data, status) {
			if (data && data.msg) {
				showTips(data.msg, 100, 2);
			} else {
				 alert("保存成功！");
				//showTips("保存成功！", 100, 2);
				window.location.href = window.location.href.replace('#', '');
			}
		});
	});
});

function check(){
	$("#view").prop("checked",false);
}
function check2(){
	$("#click").prop("checked",false);
	$("#key").prop("checked",false);
}
function check3(){
	$("#click").prop("checked",true);
	$("#view").prop("checked",false);
	$("#key").prop("checked",true);
}


function loadMain(){
	$.post("config/menu/showWechatMenu",function(data){
		if (data && data.msg) {
			showTips(data.msg, 100, 2);
		} 
	});
}


function select(id) {
	var url = "config/menu/loadWechatMenu?id=" + id;
	$.get(url, function(data, status) {
		if (data && data.msg) {
			showTips(data.msg, 100, 2);
		} else {
			if(data.parentId =="0"){
				$("#id").val(id);
				$("#name").val(data.name);
			}else{
				$("#id").val(id);
				$("#name").val(data.name);
				var a = $("#type").val(data.type);
				if (data.type != "click") {
					 $("#view").prop("checked", data.type);
					 $("#click").attr("checked",false);
					 $("#key").attr("checked",false);
				} else if (data.type != "view") {
					$("#click").prop("checked", data.type);
					$("#view").attr("checked",false);
					$("#key").attr("checked",false);
				}
				$("#url").val(data.url);
				if(data.key != ""){
					$("#key").prop("checked", data.key);
					$("#key").attr("checked",true);
					$("#click").prop("checked", data.type);
				}
			}
			
		}
	});
}

//设置状态,status为修改后的状态
function addStatus() {
	alert("***");
	$("#setMenu").dialog("open");
	
	alert("***");
}

function updateStatusAction() {
	if (confirm("确定保存吗?")) {
		var current_page = $("#hid_currentPage").val();
		$.post("manager/user/updateStatus", $("#setStatusForm").serialize(), function(data) {
			if (data == "1") {
				closeDialog("setStatus");
				showTips("保存成功！", 100, 2);
				datalist_ctrl.topage('manager/user/getUsers?&page=' + current_page);
			} else {
				showTips(data, 100, 2);
			}
		});
	}
}

function closeDialog(id) {
	$("#" + id).dialog("close");
}