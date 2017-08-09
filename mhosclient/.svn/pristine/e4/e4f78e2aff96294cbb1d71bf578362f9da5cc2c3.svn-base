$(document)
		.ready(
				function() {
					// 显示管理菜单
					$('.menu-item-cnt').attr("style", "display:none;");
					$('.menu-item-cnt:eq(4)').attr("style", "display:block");

					datalist_ctrl
							.loaddatalist("manager/serUser/getOpenfireUsers?page=1&groupName=doctor&baseUrl=manager/serUser/getOpenfireUsers");

					$('#searchSerUserForm').submit(
							function() {
								datalist_ctrl.loaddatalist_post(
										'manager/serUser/getOpenfireUsers', $(this)
												.serialize());
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
