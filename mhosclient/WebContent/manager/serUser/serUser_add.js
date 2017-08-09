$(document).ready(
		function() {
			//显示管理菜单
			$('.menu-item-cnt').attr("style", "display:none;");
			$('.menu-item-cnt:eq(4)').attr("style", "display:block");
			
			
			//检查账号名是否唯一 
			$('#username').blur(function(){
				$.post("manager/serUser/getOpenFireUser",{username:$('#username').val()},function(data){
					if(data.result=='1'){
						showTips('该用户名已经存在，请使用其它用户名！',350,2);
					}
				},'json');
			});

			//提交
			$('#serUserAddForm').submit(function() {
				if ($.trim($('#username').val()) == '') {
					showTips('用户名不能为空',500,2);
					return false;
				}else{
					var result = true;
					$.ajax({
						url:"manager/serUser/getOpenFireUser",
						async:false,
						type: "POST",
						data: {username:$('#username').val()},
						dataType:'json',
						success:function(data){
							if(data.result =='1'){
								result=true;
							}else if(data.result =='0'){
								result = false;
							}
						}
					});
					if(result){
						showTips('该用户名已经存在，请使用其它用户名！',500,2);
						return false;
					}
					if ($.trim($('#encryptedPassword').val()) == '') {
						showTips('密码不能为空！',500,2);
						return false;
					}else if(!/^\w{6,20}$/.test($('#encryptedPassword').val())){
						showTips('密码应由6－12位数字、字母或下划线组成！',500,2);
						return false;
					}

					if ($.trim($('#reencryptedPassword').val()) == '') {
						showTips('确认密码不能为空！',500,2);
						return false;
					} else if ($.trim($('#encryptedPassword').val()) != $.trim($(
							'#reencryptedPassword').val())) {
						showTips('两次输入的密码不相同！',500,2);
						return false;
					}
					return true;
				}
				
			});
		});