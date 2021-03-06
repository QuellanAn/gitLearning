$(document).ready(function() {
	loadBody();
	payStatus();
	orderStatus();
	initDepartment();
	datalist_ctrl.loaddatalist("busrecord/getAppRegOrdersRegistList?page=1&type=1");//0为预约，1为挂号

	$("#act_query").click(function(e) {
		var paras = $("#form_query").serialize();
		var url = "busrecord/getAppRegOrdersRegistList?page=1&type=1&" + encodeURI(paras);
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
function initDepartment(){
	var sel = $('#departmentCode');
	sel.empty();
	sel.append("<option value=''>全部</option>");
	$.post("config/department/findAllDeptment" , function(data, status){
		var obj=eval('('+data+')');
		var jarr = obj.depts;
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].departmentId).html(jarr[i].departmentName);
			sel.append(o.get());
		}
	});
	
};
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
function showPatients(patientId,appRegOrderId) {
	document.location.href="busrecord/appreg/showRegist?patientId="+patientId+"&appRegOrderId="+appRegOrderId;
	
	/*$.post("busrecord/appreg/showRegist", {
		"patientId" : patientId,
		"appRegOrderId":appRegOrderId
	}, function(data) {
		$("#showPatients").html(data);
		$("#showPatients").dialog({
			title:"挂号详情",
			width : 850,
			height : 400
		});
	}, "html");*/
}

function closeDialog(id) {
	$("#" + id).dialog("close");
}

/**
 * 加载预约途径项目
 */
function loadBody(){
	var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"business_source";
	var body = $('#regSource');
	body.empty();
	body.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var arr1 = eval(data);
        var arr=eval("("+arr1.data+")");
		for (var i in arr){
			var o = $('<option></option>').val(arr[i].codeNo).html(arr[i].codeName);
			body.append(o.get());
		}
	});
};
/**
 * 加载缴费状态
 */
function payStatus(){
	var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"payment_status";
	var body = $('#paid');
	body.empty();
	body.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var arr1 = eval(data);
        var arr=eval("("+arr1.data+")");
		for (var i in arr){
			var o = $('<option></option>').val(arr[i].codeNo).html(arr[i].codeName);
			body.append(o.get());
		}
	});
};
/**
 * 加载预约状态
 */
function orderStatus(){
	var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"reservation_status";
	var body = $('#status');
	body.empty();
	body.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var arr1 = eval(data);
        var arr=eval("("+arr1.data+")");
		for (var i in arr){
			var o = $('<option></option>').val(arr[i].codeNo).html(arr[i].codeName);
			body.append(o.get());
		}
	});
};