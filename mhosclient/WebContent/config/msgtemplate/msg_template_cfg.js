$(document).ready(function() {
	
	$("#act_save").click(function(){
		var paras = {msgBusCode:$("#msgBusCode").val() ,
				supportPhoneMsg:$("#supportPhoneMsg").prop("checked"),
				supportNetMsg:$("#supportNetMsg").prop("checked"),
				template:$("#template").val()};
		$.post("config/msgtemplate/saveMsgCfg",
			paras,
			function(data, status){
				if (data && data.msg){
					showTips(data.msg,100,2);
				}else{
					//alert("保存成功！");
					showTips("保存成功！",100,2);
				}
			});
	});
});


function act_save(id){
}


function select(code){
	var url = "config/msgtemplate/loadMsgCfg?msgBusCode=" + code;
	$.get(url, function(data, status){
		if (data && data.msg){
			showTips(data.msg,100,2);
		}else{
			$("#msgBusCode").val(code);
			$("#msgBusName").html(data.msgBusName);
			$("#supportPhoneMsg").prop("checked", data.supportPhoneMsg);
			$("#supportNetMsg").prop("checked", data.supportNetMsg);
			$("#template").val(data.template);
		}
	});
}

function submit_save(){
	var paras = {msgBusCode:$("#msgBusCode").val() ,
				supportPhoneMsg:$("#supportPhoneMsg").prop("checked"),
				supportNetMsg:$("#supportNetMsg").prop("checked"),
				template:$("#template").val()};
	$.post("config/msgtemplate/saveDepartment",
			paras,
			function(data, status){
				if (data && data.msg){
					showTips(data.msg,100,2);
				}
			});
}






