$(document).ready(function() {
	//显示管理菜单
	$('.menu-item-cnt').attr("style", "display:none;");
	$('.menu-item-cnt:eq(4)').attr("style", "display:block");
	
	//显示所有角色
	$.post("manager/sysUser/findAllRoles", function(data) {
		if (data.result == '1') {
			var roles="";
			$.each(data.roles, function(i) {
				var roleIds= $.trim($('#tempRoleDiv').text()).split(",");
				if(roleIds.length==0){
					roles+="<input type=\"checkbox\" name=\"role_ids\" value='"
						+data.roles[i].roleId+"'/>"+data.roles[i].roleName+"<br/>";
				}else{
					roles+="<input type=\"checkbox\" name=\"role_ids\" value=\""
						+data.roles[i].roleId+"\"";
					for (var int = 0; int < roleIds.length; int++) {
						if(roleIds[int]==data.roles[i].roleId){
							roles+=" checked=\"checked\"";
							break;
						}
					}//end for
					roles+=" />"+data.roles[i].roleName+"<br/>";
				}
			});
			$('#roleDiv').html(roles);
		}
	}, 'json');


	//表单提交
	$('#updateSysUserForm').submit(function() {
		if ($.trim($('#realName').val()) == '') {
			showTips('姓名不能为空',400,2);
			return false;
		}
		
		if($.trim($('#password').val()) != ''){
			if(!/^\w{6,20}$/.test($('#password').val())){
				showTips('密码应由6－12位数字、字母或下划线组成！',400,2);
				return false;
			}
			
			if ($.trim($('#password').val()) != $.trim($('#repassword').val())) {
				showTips('两次输入的密码不相同！',400,2);
				return false;
			}
		}
		
		if(!$("input[name='role_ids']").is(':checked')){
			showTips('角色不能为空，请为该用户选择角色！',400,2);
			return false;
		}

		return true;

	});

});