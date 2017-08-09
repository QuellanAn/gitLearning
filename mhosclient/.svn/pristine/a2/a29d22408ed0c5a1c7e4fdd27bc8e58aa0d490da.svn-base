$(document).ready(function() {

	datalist_ctrl.loaddatalist("");

});

function refresh_totalcount() {

	$("#totalcount").html($("#hid_totalcount").val());
}

var datalist_ctrl = {
	topage : function(url) {
		this.loaddatalist(url);
	},
	loaddatalist : function(pageNo) {
		var cnt = $('#div_cnt_table');
		$.post('unpaid/payTypeAction_find', {		
			pageNo : pageNo
		}, function(data, status) {
			if (data && data.msg) {
				alert(data.msg);
			} else if (data) {
				cnt.empty();
				cnt.append(data);
			}
		});
	},

};

/**
 * 跳转到编辑界面
 * @param ptCode
 */
function toModify(ptCode){
	window.location.href="unpaid/payTypeAction_toModify?ptCode="+ptCode;
}