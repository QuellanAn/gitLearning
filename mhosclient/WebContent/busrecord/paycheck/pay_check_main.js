$(document).ready(function() {
	datalist_ctrl.loaddatalist("busrecord/getPayCheckList?page=1");

	$("#act_query").click(function(e) {
		var paras = $("#form_query").serialize();
		var url = "busrecord/getPayCheckList?page=1&" + encodeURI(paras);
		datalist_ctrl.loaddatalist(url);
		return false;
	});

	$('input[name=startdate]').datepicker({
		dateFormat : 'yy-mm-dd'
	});
	$('input[name=enddate]').datepicker({
		dateFormat : 'yy-mm-dd'
	});
	$('input[name=startCreateTime]').datepicker({
		dateFormat : 'yy-mm-dd'
	});
	$('input[name=endCreateTime]').datepicker({
		dateFormat : 'yy-mm-dd'
	});

});

function refresh_totalcount() {
	$("#totalcount").html($("#hid_totalcount").val());
}

var datalist_ctrl = {

	loaddatalist : function(url) {
		var cnt = $('#div_cnt_table');
		$.get(url, function(data, status) {
			if (data && data.msg) {
				alert(data.msg);
			} else if (data) {
				cnt.empty();
				cnt.append(data);
			}
		});
	},
	topage : function(url) {
		this.loaddatalist(url);
	}
};
// 显示预约记录
function showRegInfo(orderid) {
	$.post("busrecord/appreg/showRegInfo", {
		"orderid" : orderid
	}, function(data) {
		$("#showRegInfo").html(data);
		$("#showRegInfo").dialog({
			width : 850,
			height : 400
		});
	}, "html");
}


// 显示就诊人
function showPatients(patientId) {
	$.post("busrecord/appreg/showPatient", {
		"patientId" : patientId
	}, function(data) {
		$("#showPatients").html(data);
		$("#showPatients").dialog({
			width : 850,
			height : 400
		});
	}, "html");
}

function closeDialog(id) {
	$("#" + id).dialog("close");
}
