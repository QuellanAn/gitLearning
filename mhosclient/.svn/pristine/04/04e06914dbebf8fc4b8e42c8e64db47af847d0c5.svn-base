$(document).ready(function() {
	//显示配置菜单
	$('.menu-item-cnt').attr("style", "display:none;");
	$('.menu-item-cnt:eq(3)').attr("style", "display:block");
	
	$('#addbutton').click(function() {
		location.href = 'config/version/addVersion';
	});

	datalist_ctrl.loaddatalist("config/version/getCliVersions?page=1");

	$("#ver_cat").change(function(e) {
		var paras = $("#cliVersionForm").serialize();
		var url = "config/version/getCliVersions?page=1&" + paras;
		datalist_ctrl.loaddatalist(url);
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
				if (data && data.msg) {
					showTips(data.msg,100,2);
					//alert(data.msg);
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
