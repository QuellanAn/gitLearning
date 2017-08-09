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

var zNodes = [ {
	id : 1,
	pId : 0,
	name : "全部",
	open : true
}, {
	id : 11,
	pId : 1,
	name : "业务"
}, {
	id : 12,
	pId : 1,
	name : "统计"
}, {
	id : 13,
	pId : 1,
	name : "配置",
	open : true
}, {
	id : 301,
	pId : 13,
	name : "基础内容配置"
}, {
	id : 302,
	pId : 13,
	name : "科室介绍管理"
}, {
	id : 303,
	pId : 13,
	name : "消息模板配置"
}, {
	id : 305,
	pId : 13,
	name : "医生管理",
	checked : true
}, {
	id : 304,
	pId : 13,
	name : "客户端版本发布"
}, {
	id : 14,
	pId : 1,
	name : "管理",
	open : true
}, {
	id : 401,
	pId : 14,
	name : "会员管理"
}, {
	id : 402,
	pId : 14,
	name : "系统用户管理"
} ];

$(document).ready(function() {
	//显示管理菜单
	$('.menu-item-cnt').attr("style", "display:none;");
	$('.menu-item-cnt:eq(4)').attr("style", "display:block");
	
	$.fn.zTree.init($("#ztree"), setting, zNodes);

	//获取当前角色所有权限
	$.post('manager/sysUser/getAuthority', {
		"roleId" : $('#roleId').val()
	}, function(data) {
		if (data.result == '0') {
			var au_ids = data.au_ids.split(",");

			var treeObj = $.fn.zTree.getZTreeObj("ztree");
			for (var i = 0; i < au_ids.length; i++) {
				var node = treeObj.getNodeByParam("id", au_ids[i], null);
				treeObj.checkNode(node, true, true);
				
			}
		}// end if
	}, 'json');

	$('#updateButton').click(function() {
		if ($.trim($('#roleName').val()) == '') {
			showTips('角色不能为空！',200,2);
			return false;
		}

		var treeObj = $.fn.zTree.getZTreeObj("ztree");
		var nodes = treeObj.getCheckedNodes(true);
		var result = "";
		$.each(nodes, function(i) {
			if (!nodes[i].isParent) {
				result += nodes[i].id + ",";
			}
		});

		if ($.trim(result) == '') {
			showTips('权限不能为空，请选择相应的权限！',200,2);
			return false;
		} 
		
		result = result.replace(",,", "");
		
		$('#au_ids').val(result);


		$('#updateRoleForm').submit();
	});

});
