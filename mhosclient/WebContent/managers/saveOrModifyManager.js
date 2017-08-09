var arr = new Array();
var flag1 = false;// 判断账号
var flag2 = false;// 密码
var flag4 = false;// 姓名
var flag5 = false;// 用户身份
var flag6 = false;// 手机号
var flag7 = false;// 用户身份（修改界面）
var flag11 = false;
$(document).ready(function() {
			findAllToJson();
			
			// 账号
			$("#userName").blur(function() {
						var account = $(this).val();
						if ($.trim(account).length < 5
								|| $.trim(account).length > 20
								|| isChineseChar(account)) {
							showTips('账号由5-20位字母数字或下划线组成！', 200, 2);
							return false;
						} else {
							manager.findByAccount(account);
						}
					});
			// 账号
			$("#muserName").blur(function() {
						var maccount = $(this).val();
						if ($.trim(maccount).length < 5
								|| $.trim(maccount).length > 20
								|| isChineseChar(maccount)) {
							showTips('账号由5-20位字母数字或下划线组成！', 200, 2);
							return false;
						} else {
							var mrid = $("#id").val();
							manager.findByMaccount(mrid, maccount);
						}
					});
			$("#tel").blur(function() {
				var phone = $(this).val();
				if ($.trim(phone).length == 0) {
					showTips('手机号码不能为空！', 200, 2);
					return false;
				} else if (!validPhone(phone)) {
					showTips('请输入正确的手机号码！', 200, 2);
					return false;
				} else {
					manager.findByPhone(phone);
				}
			});
			$("#mtel").blur(function() {
				var mphone = $(this).val();
				if ($.trim(mphone).length == 0) {
					showTips('手机号码不能为空！', 200, 2);
					return false;
				} else if (!validPhone(mphone)) {
					showTips('请输入正确的手机号码！', 200, 2);
					return false;
				} else {
					var mrid = $("#id").val();
					manager.findByMphone(mrid, mphone);
				}
			});
			$("#department").blur(function() {
				var department = $(this).val();
				if ($.trim(department).length == 0) {
					showTips('所属部门不能为空！', 200, 2);
					return false;
				} else {
				}
			});

			$("#pwd").blur(function() {
				var pwd = $(this).val();
				if ($.trim(pwd).length < 6 || $.trim(pwd).length > 16) {
					showTips('密码长度6-16位！', 200, 2);
					return false;
				} else {
					var password = $.md5(pwd);
					$("#password").val(password);
					flag2 = true;
				}
			});
			$("#realName").blur(function() {
				var name = $(this).val();
				if ($.trim(name).length == 0) {
					showTips('姓名不能为空！', 200, 2);
					return false;
				} else {
					flag4 = true;
				}
			});
			// 用户身份
			$("#roleId").blur(function() {
				var roleId = $(this).val();
				if ($.trim(roleId).length == 0) {
					showTips('分配角色不能为空！', 200, 2);
					return false;
				} else {
					flag5 = true;
				}
			});
			$("#mroleId").blur(function() {
				var mroleId = $(this).val();
				if ($.trim(mroleId).length == 0) {
					showTips('分配角色不能为空！', 200, 2);
					return false;
				} else {
					flag5 = true;
				}
			});
			$('#myform').submit(function() {
				$("#tel").blur();
				$("#pwd").blur();
				$("#realName").blur();
				$("#userName").blur();
				$("#roleId").blur();
				if(flag1 && flag2 && flag4 && flag5 && flag6 && flag11){
						return true;
				}
				return false;
			});
			 $('#modifyform').submit(function() {
			 $("#mtel").blur();
			 $("#realName").blur();
			 $("#muserName").blur();
			 $("#mroleId").blur();
			 jueshe();
			 if(flag1 && flag4 && flag5 && flag6 && flag11){
					return true;
			 }
			 return false;
			 });
		 });

var manager = {
	findByAccount : function(account) {
		$.ajax({
			type : "POST",
			url : "manager_findByValid",
			data : {
				pageNo : account
			},
			dataType : "json",
			async : false,
			success : function(data) {
				var obj = eval(data);
				if (obj.code == 1) {
					showTips('账号已存在！', 200, 2);
					return false;
				} else {
					flag1 = true;
				}
			},
			error : function() {
				alert("请稍后重试!");
			}
		});
	},
	findByPhone : function(phone) {
		$.ajax({
			type : "POST",
			url : "manager_findByValid",
			data : {
				keywords : phone
			},
			dataType : "json",
			async : false,
			success : function(data) {
				var obj = eval(data);
				if (obj.code == 1) {
					showTips('手机号码已存在！', 200, 2);
					return false;
				} else {
					flag6 = true;
				}
			},
			error : function() {
				alert("请稍后重试!");
			}
		});
	},
	findByMaccount : function(mrid, account) {
		$.ajax({
			type : "POST",
			url : "manager_findByValid",
			data : {
				pageNo : account
			},
			dataType : "json",
			async : false,
			success : function(data) {
				var obj = eval(data);
				if (obj.code == 1) {
					var m = eval("(" + obj.data + ")");
					if (m.userId == mrid) {
						flag1 = true;
					} else {
						showTips('账号已存在！', 200, 2);
						return false;
					}
				} else {
					flag1 = true;
				}
			},
			error : function() {
				alert("请稍后重试!");
			}
		});
	},
	findByMphone : function(mrid, phone) {
		$.ajax({
			type : "POST",
			url : "manager_findByValid",
			data : {
				keywords : phone
			},
			dataType : "json",
			async : false,
			success : function(data) {
				var obj = eval(data);
				if (obj.code == 1) {
					var m = eval("(" + obj.data + ")");
					if (m.userId == mrid) {
						flag6 = true;
					} else {
						showTips('手机号码已存在！', 200, 2);
						return false;
					}
				} else {
					flag6 = true;
				}
			},
			error : function() {
				alert("请稍后重试!");
			}
		});
	},
	adminIsExistingToJson : function(mid) {
		$.ajax({
			type : "POST",
			url : "manager_adminIsExistingToJson",
			dataType : "json",
			data : {
				mid : mid
			},
			async : false,
			success : function(data) {
				var obj = eval(data);
				if (obj.code == 1) {
					showTips('超级管理员用户已存在且只能有一位！', 200, 2);
					return false;
				} else {
					flag11 = true;
				}
			},
			error : function() {
				alert("请稍后重试!");
			}
		});
	},
};

function isChineseChar(str) {
	var reg = /^[\u4E00-\u9FA5\uF900-\uFA2D]$/;
	return reg.test(str);
}

function validPhone(str) {
	var reg = /^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\d{8}$/;
	return reg.test(str);
}

function findAllToJson(){
	var sel = $('#roleId');
	sel.empty();
	sel.append("<option value=''>请选择</option>");
	$.post("role_findAllToJson" , function(data, status){
		var jarr = eval(data);
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].roleId).html(jarr[i].name);
			sel.append(o.get());
		}
	});
};

function jueshe(){
	var roleId=$("#roleId").val();
	var mroleId=$("#mroleId").val();
	if(roleId==1 || mroleId==1){
		var mid=$("#id").val();
		if(mid!=null){
			manager.adminIsExistingToJson(mid);
		}else{
			manager.adminIsExistingToJson();
		}
	}else if(roleId==null && mroleId==null){
	}else{
		flag11=true;
	}
	
};

/*function getvalue(value) {
	if (value == 1) {
		document.getElementById("department").disabled = true;
		$("#department").attr("placeholder", "无需输入");
	} else {
		document.getElementById("department").disabled = false;
		$("#department").attr("placeholder", "");
	}
}*/

/*function findAllToJsonByCategory(value, num) {
	if (num == 1) {
		var sel = $('#roleId');
	}
	if (num == 2) {
		var sel = $('#mroleId');
	}
	sel.empty();
	sel.append("<option value=''>请选择角色</option>");
	$.post("role_findAllToJsonByCategory", {
		category : value,
	}, function(data, status) {
		var jarr = eval(data);
		for ( var i in jarr) {
			var o = $('<option></option>').val(jarr[i].roleId).html(
					jarr[i].name);
			sel.append(o.get());
		}
		;
	});
};*/
