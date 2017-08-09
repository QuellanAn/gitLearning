$(document).ready(function() {
	$("#msg_save").click(function() {
		var paras = {
			wechat_msg_subscribe : $("#wechat_msg_subscribe").val(),
			wechat_msg_appreg : $("#wechat_msg_appreg").val(),
			wechat_msg_unappreg : $("#wechat_msg_unappreg").val()
		};
		$.post("config/wechat/saveWechatCfg", paras, function(data) {
			if (data && data.msg) {
				showTips(data.msg, 100, 2);
			} else {
				// alert("保存成功！");
				showTips("保存成功！", 100, 2);
			}
		});
	});
});
