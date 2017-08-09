$(document).ready(
		function() {
			//显示管理菜单
			$('.menu-item-cnt').attr("style", "display:none;");
			$('.menu-item-cnt:eq(4)').attr("style", "display:block");
			
			$.ajax({
				url:"manager/serUser/getOpenFireUser",
				async:false,
				type: "POST",
				data: {username:$('#username').val()},
				dataType:'json',
				success:function(data){
					if(data.result =='1'){
						$('#name').val(data.openFireUserPO.name);
						$('#email').val(data.openFireUserPO.email);
					}else if(data.result =='0'){
						
					}
				}
			});

			//提交
			$('#updateButton').click(
					function() {
						
						if($.trim($('#encryptedPassword').val()) != '' && 
								!/^\w{6,20}$/.test($('#encryptedPassword').val())){
							showTips('密码应由6－12位数字、字母或下划线组成！',500,2);
							return false;
						}

						if ($.trim($('#encryptedPassword').val()) != $.trim($(
								'#reencryptedPassword').val())) {
							showTips('两次输入的密码不相同！',500,2);
							return false;
						}
						
						$.ajax({
							url:"manager/serUser/updateOpenFireUser",
							async:false,
							type: "POST",
							data: {groupName:$('#groupName').val(),username:$('#username').val(),
								name:$('#name').val(),encryptedPassword:$('#encryptedPassword').val(),
								email:$('#email').val()},
							dataType:'json',
							success:function(data){
								if(data.result =='1'){
									alert("编辑医护人员成功！");
									location.href="serUser.jsp";
								}else if(data.result =='0'){
									alert("编辑医护人员失败！");
								}
							}
						});

					});

		});