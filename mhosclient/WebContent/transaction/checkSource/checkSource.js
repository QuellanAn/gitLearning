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
		$.post('unpaid/checkSourceAction_findAll', {		
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
function toModify(code){
	window.location.href="unpaid/checkSourceAction_toModify?code="+code;
}

function toStatus(code,status) {
	$.ajax({
		type : "post",
		url : "unpaid/checkSourceAction_toStatus",
		dataType : "json",
		data: {"code":code,"status":status},  
		async : false,
		success : function(data) {
			if(eval(data).shu>0){
				$.jBox.prompt('操作成功!', '提示', 'info', 
						{ closed: function () { location.reload(); } });
			}
		},error : function() {
			jBox.alert("请求数据失败!");
		}
	});
};