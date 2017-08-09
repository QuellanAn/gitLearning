$(document).ready(function() {
	
	// 显示配置菜单
	$('.menu-item-cnt').attr("style", "display:none;");
	$('.menu-item-cnt:eq(4)').attr("style", "display:block");
	loadinfocats(0);
	
	var questType =  $("#questType").val();
	var ty = questType.substr(0,1);
	if(ty == 0){
		document.getElementById("addQuest").style.display="none";
		datalist_ctrl.loaddatalist("questionnaire/getQuestionnaiList?page=1&upType=0");	
		$('#searchQuestionnaireForm').submit(
			function() {
				datalist_ctrl.loaddatalist_post(
						'questionnaire/getQuestionnaiList?upType=0', $(this).serialize());
				return false;
			});
	}else{
		datalist_ctrl.loaddatalist("questionnaire/getQuestionnaiList?page=1&upType=1");	
		$('#searchQuestionnaireForm').submit(
			function() {
				datalist_ctrl.loaddatalist_post(
						'questionnaire/getQuestionnaiList?upType=1', $(this).serialize());
				return false;
			});
	}
	
	
	$('input[name=startdate]').datepicker({
		dateFormat : 'yy-mm-dd'
	});
	$('input[name=enddate]').datepicker({
		dateFormat : 'yy-mm-dd'
	});
	
});

function refresh_unpubcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

function loadinfocats(type){
	var url = "hosinfomng/getinfocats?infotype=" + type;
	var sel = $('#infocat');
	sel.empty();
	if (!type=="" || type == 0){
		sel.append("<option value=''>全部</option>");
	}
	$.get(url , function(data, status){
		var jarr = eval(data);
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].infoCatCode).html(jarr[i].infoCatName);
			sel.append(o.get());
		}
	});
}


function refresh_unpubcount() {
	$("#totalcount").html($("#hid_totalcount").val());
}

var datalist_ctrl = {
	loaddatalist : function(url) {
		var cnt = $('#div_cnt_table');
		$.post(url, function(data, status) {
			cnt.empty();
			cnt.append(data);
		}, 'html');
	},

	loaddatalist_post : function(url, paras) {
		var cnt = $('#div_cnt_table');
		$.post(url, paras, function(data, status) {
			cnt.empty();
			cnt.append(data);

		}, 'html');
	},
	topage : function(url) {
		this.loaddatalist(url);
		
		var questType =  $("#questType").val();
		var ty = questType.substr(0,1);
		if(ty == 0){
			document.getElementById("addQuest").style.display="none";
			setTimeout(function() {
				var size = $("#hid_totalcount").val();
				for(var i=0; i<size; i++){
					var id = "#delQuest_"+i;
					$(id).hide();
				}
			}, 100); 
		}
		
	},
	// 编辑
	updateSerUser : function(url,username) {
		location.href=url+"?username="+username;
	},
	// 删除
	deleteSysUser : function(span, username) {
		if (confirm("您确定要删除该条记录吗")) {
			$.post("manager/serUser/deleteOpenFireUser", {
				"username" : username
			}, function(data) {
				if (data.result == '1') {
					$('#totalcount').text($('#totalcount').text() - 1);
					$(span).parent().parent().remove();
				}
			}, 'json');
		}// end if
	}
};
