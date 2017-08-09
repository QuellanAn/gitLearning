$(document).ready(function() {
	$('#beginDate').datepicker({ dateFormat: 'yy-mm-dd' });
	$('#endDate').datepicker({ dateFormat: 'yy-mm-dd' });
	// 加载设备类型
	loadFacilityType();
	$("#act_query").click(function(e) {
		var pageNo=$("#hid_pageNo").val();
		loadPage(pageNo);
		return false;
	});
	
	$("#act_query").click();
});

/**
 * 加载分页数据
 * @param pageNo 页码
 */
function loadPage(pageNo){
	var paras = $("#form_query").serialize();
	var url = "selfService/selfServiceUpgradeAction_versionHistoryList?pageNo="+pageNo+"&" + paras;
	datalist_ctrl.loaddatalist(url);
}

function refresh_totalcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

var datalist_ctrl = {
	loaddatalist : function(url) {
		var cnt = $('#div_cnt_table');
		$.get(url, function(data, status) {
			if (data && data.msg) {
				jBox.alert(data.msg, 'error');
			} else if (data) {
//				window.setTimeout(function() {
					if (data != null) {
						cnt.empty();
						cnt.append(data);
					}
//				}, 500);
			}
		});
	},
	topage : function(url) {
		this.loaddatalist(url);
	}
};

/**
 * 加载设备类型
 */
function loadFacilityType(){
	var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"facility_type";
	var body = $('#facilityType');
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
