$(document).ready(
		function() {
			//显示管理菜单
			$('.menu-item-cnt').attr("style", "display:none;");
			$('.menu-item-cnt:eq(4)').attr("style", "display:block");
			
			$.post("manager/sysUser/findAllRoles", function(data) {
				if (data.result == '1') {
					var roles="";
					$.each(data.roles, function(i) {
						roles+="<input type=\"checkbox\" name=\"role_ids\" value='"
							+data.roles[i].roleId+"'/>"+data.roles[i].roleName+"<br/>";
					});
					$('#roleDiv').html(roles);
				}
			}, 'json');
			
			
			//检查账号名是否唯一 
			$('#userName').blur(function(){
				$.post("manager/sysUser/existsUsername",{userName:$('#userName').val()},function(data){
					if(data.result=='0'){
						showTips('该账号名已经存在，请使用其它账号名！',350,2);
					}
				},'json');
			});

			//提交
			$('#sysUserAddForm').submit(
					function() {
						if ($.trim($('#realName').val()) == '') {
							showTips('姓名不能为空',500,2);
							return false;
						}else{
							var result = true;
							$.ajax({
								url:"manager/sysUser/existsUsername",
								async:false,
								type: "POST",
								data: {userName:$('#userName').val()},
								dataType:'json',
								success:function(data){
									if(data.result =='0'){
										result=true;
									}else if(data.result =='1'){
										result = false;
									}
								}
							});
							if(result){
								showTips('该账号名已经存在，请使用其它账号名！',500,2);
								return false;
							}
						}

						if ($.trim($('#userName').val()) == '') {
							showTips('账号名不能为空',500,2);
							return false;
						}else if(!/^\w{6,20}$/.test($('#userName').val())){
							showTips('账号名应由6－12位数字、字母或下划线组成！',500,2);
							return false;
						}

						if ($.trim($('#password').val()) == '') {
							showTips('密码不能为空！',500,2);
							return false;
						}else if(!/^\w{6,20}$/.test($('#password').val())){
							showTips('密码应由6－12位数字、字母或下划线组成！',500,2);
							return false;
						}

						if ($.trim($('#repassword').val()) == '') {
							showTips('确认密码不能为空！',500,2);
							return false;
						} else if ($.trim($('#password').val()) != $.trim($(
								'#repassword').val())) {
							showTips('两次输入的密码不相同！',500,2);
							return false;
						}
						
						if(!$("input[name='role_ids']").is(':checked')){
							showTips('角色不能为空，请为该用户选择角色！',500,2);
							return false;
						}

						return true;

					});

		});