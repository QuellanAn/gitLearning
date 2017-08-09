$(document).ready(
		function() {
			//显示配置菜单
			$('.menu-item-cnt').attr("style", "display:none;");
			$('.menu-item-cnt:eq(3)').attr("style", "display:block");
			
			var update_version = function() {
				$.post('config/version/getVersionNo', {
					'cliVersionPO.ver_cat' : $('#ver_cat').val(),
					'cliVersionPO.ver_type' : $('#ver_type').val()
				}, function(data) {
					if (data.data.length == 0) {
						$('#update_version')
								.html("<option value=''>无</option>");
					} else {
						var option = "<option value=''>无</option>";
						$.each(data.data, function(i, update_version) {
							option += "<option value='" + update_version + "'>"
									+ update_version + "</option>";
						});
						$('#update_version').html(option);
					}
				}, 'json');
			};
			
			update_version();
			$('#ver_type').change(update_version);//改变客户端类别
			$('#ver_cat').change(update_version);//改变客户端平台
			
			var androidFormat =/^\S+\.apk$/i;//android格式
			var iOSFormat =/^\S+\.ipa$/i;//iOS格式

			// 提交
			$('#cliVerAddForm').submit(function() {
				var versionFormat=/^\d+\.\d+\.\d+$/;
				if ($.trim($('#versionNo').val()) == '') {
					showTips('版本号不能为空！',250,2);
					return false;
				}else if(!versionFormat.test($('#versionNo').val())){
					showTips('版本号格式不正确！',250,2);
					return false;
				}

				if ($.trim($('#content').val()) == '') {
					showTips('更新内容不能为空！',250,2);
					return false;
				}

				if ($.trim($('#apkPath').val()) == '') {
					showTips('安装包不能为空！',250,2);
					return false;
				}else if($('#ver_cat').val()=='Android' && !androidFormat.test($('#apkPath').val())){
					showTips('安装包不符合Android格式！',250,2);
					return false;
				}else if($('#ver_cat').val()=='iOS' && !iOSFormat.test($('#apkPath').val())){
					showTips('安装包不符合iOS格式！',250,2);
					return false;
				}
				return true;
			});

		});