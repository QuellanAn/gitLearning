var zNodes;
var flag = false;
var flag1 = true;
$(document).ready(function() {
	$("input[name='role.category']").click(function(){
		value= $(":radio:checked").val();
		if(value ==1){
			adminIsExistent();
		}	
	});	
	
	$("#name").blur(function(){
		var name = $(this).val();
		if($.trim(name).length==0){
			showTips('角色不能为空！',200,2);
			return false;
		}else{
			roleMenu.findByName(roleId,name);
		}
	});
	
	$('#myform').submit(function() {
		$("#name").blur();
		if(value= $(":radio:checked").val()==1){
			adminIsExistent();
		}else{
			flag1=true;
		}
		if(flag&&flag1){
			var treeObj = $.fn.zTree.getZTreeObj("ztree");
			var nodes = treeObj.getCheckedNodes(true);
			var ids = "";
			$.each(nodes, function(i) {
				if (!nodes[i].isParent) {
					ids += nodes[i].id + ",";
				}
			});
			if ($.trim(ids).length == 0) {
				showTips('请选择需要分配的菜单！',200,2);
				return false;
			} 
			
			$('#ids').val(ids);
			
		}
		return flag&&flag1;
	});
	
	var roleId = $("#id").val();
	roleMenu.findAll();
	roleMenu.findRoleMenu(roleId);
});

var setting = {
	check : {
		enable : true
	},
	data : {
		simpleData : {
			enable : true
		}
	}
};

var roleMenu = {
	findAll:function(){
		$.ajax({
			type:"POST",
			url:"menu_findAll",
			dataType:"text",
			async : false,  
			success: function(data) {
				var obj = eval(data);
				zNodes = obj;
				$.fn.zTree.init($("#ztree"), setting, zNodes);
			},
			error:function(){
				alert("请稍后重试!");
			}
		});
	},
	findByName:function(roleId, name){
		$.ajax({
			type:"POST",
			url:"role_findByName",
			data:{pageNo:name},
			dataType:"json",
			async : false,  
			success: function(data) {
				var obj = eval(data);
				if(obj.code == 1){
					var role = eval("("+obj.data+")");
					if(role.roleId == roleId){
						flag = true;
					}else{
						showTips('角色名称已存在！',200,2);
						flag = false;
					}
				}else{
					flag = true;
				}
			},
			error:function(){
				alert("请稍后重试!");
			}
		});
	},
	findRoleMenu:function(roleId){
		$.ajax({
			type:"POST",
			url:"roleMenu_findAll",
			data:{pageNo:roleId},
			dataType:"json",
			async : false,  
			success: function(data) {
				var obj = eval(data);
				if(obj.code == 0){
					var menuIds = obj.data.split(",");
					var treeObj = $.fn.zTree.getZTreeObj("ztree");
					for (var i = 0; i < menuIds.length; i++) {
						var node = treeObj.getNodeByParam("id", menuIds[i], null);
						treeObj.checkNode(node, true, true);
					}
				}
			},
			error:function(){
				alert("请稍后重试!");
			}
		});
	},
};
//超级管理员只能有一位，验证是否已存在
function adminIsExistent(){
	var roleId= $("#id").val();
	$.ajax({
		type:"POST",
		url:"role_adminIsExistent",
		dataType:"json",
		data:{roleId:roleId},
		async : false,  
		success: function(data) {
			var obj = eval(data);
			if(obj.code == 1){
		//	$('#category').attr("style", "display:none;");
		//   $("input[name='role.category']")[0].css("display","none");
				alert("超级管理员已存在,不能再创建此角色");
				return flag1=false;
			}
		},
		error:function(){
			alert("请稍后重试!");
		}
	});
};

