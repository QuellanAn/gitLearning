$(document).ready(function() {
	var dates = $("#start_create_date,#end_create_date");
	dates.datepicker({
		changeMonth : true,
		changeYear : true,
		dataFormat : "yyyy-MM-dd",
		onSelect : function(selectedDate) {
			var option = this.id == "startdate" ? "minDate" : "maxDate";
			dates.not(this).datepicker("option", option, selectedDate);
		}
	});

	$("#pat_blackTime").datepicker({
		changeMonth : true,
		changeYear : true,
		dataFormat : "yyyy-MM-dd",
		minDate : new Date()
	});

	$("#setStatus").dialog({
		width : 420,
		height : 230,
		autoOpen : false,
		modal : true
	});

	datalist_ctrl.loaddatalist("manager/user/getUsers?page=1");

	$('#searchUserForm').submit(function() {
		datalist_ctrl.loaddatalist_post('manager/user/getUsers', $(this).serialize());
		return false;
	});

});

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
	}
};

// 显示就诊人
function showPatients(userId, username) {
	document.location.href="manager/user/showUserInfo?userName="+username;
	/*$.post("manager/user/showPatients", {
		"userId" : userId,
		"user_name" : username
	}, function(data) {
		$("#showPatients").html(data);
		$("#showPatients").dialog({
			width : 850,
			height : 500
		});
	}, "html");*/

}

// 设置状态,status为修改后的状态
function updateStatus(userId, status, blackTime) {
	var status = $("#status").val();
	var userName = $("#userName").val();
	document.location.href="busrecord/appreg/updatePatientOrUser?type=1&status="+status+"&userName="+userName;//0表示对patient的操作，1表示对微信账户操作
	/*$("#setStatus").dialog("open");
	$("#pat_userId").val(userId);
	$("#pat_status").val(status);
	if (status == 1) {
		blackTime = "";
	}
	$("#pat_blackTime").val(blackTime);*/
}
function updateAllStatus(){
	var userName = getChickIds("userName");
	document.location.href="busrecord/appreg/updateAllPatientOrUser?type=1&userName="+userName;
	return false;
};
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
};
//显示就诊人,由微信跳转就诊人
function showPatientUser(patientId){
	document.location.href="busrecord/appreg/showPatientUser?patientId="+patientId;
};
function closeDialog(id) {
	$("#" + id).dialog("close");
}
