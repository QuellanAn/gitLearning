/*$(document).ready(function() {
	$('#loginForm').submit(function() {
		if ($.trim($('#userName').val()) == '') {
			login_tips('账号不能为空！', 10, 2);
			return false;
		}

		if ($.trim($('#password').val()) == '') {
			login_tips('密码不能为空！', 10, 2);
			return false;
		}

		return true;
	});
});*/
$(document).ready(function() {
	var flag1 = false;
	var flag2 = false;
	var flag3 = false;
	var name, password;
	$("#userName").blur(function(){
		name = $(this).val();
		if($.trim(name).length==0){
			login_tips('账号不能为空！', 140, 5);
			return false;
		}else{
			flag1 = true;
		}
	});
			
	$("#password").blur(function(){
		password = $(this).val();
		if($.trim(password).length==0){
			login_tips('密码不能为空！', 140, 5);
			return false;
		}else{
			flag2 = true;
		}
	});
	
	$('#loginForm').submit(function() {
		$("#userName").blur();
		$("#password").blur();
		if(flag1 && flag2){
			password = $.md5(password);
			$.ajax({
				type:"POST",
				url:"manager_login",
				data:{
					account:name,
					password:password
				},
				dataType:"json",
				async : false,//true 表示异步方式，false表示同步方式
				success: function(data) {
					 var obj = eval(data);
					 if(obj.code == 0){
					 	setTimeout(jumurl(),1000);
					 	return true;
					 }else if(obj.code == 9){//账号的权限被删除
						 login_tips(obj.data, 140, 5);
					 	return true;
					 }else{
						 login_tips(obj.message, 140, 5);
						 return false;
					 }
				},
				error:function(){
					alert("请稍后重试!");
				}
			});
		}
		return false;
	});
});

function jumurl(){
	window.location.href='menu_protal';
}