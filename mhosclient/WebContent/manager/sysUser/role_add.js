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
	name : "业务",
	checked : true
}, {
	id : 12,
	pId : 1,
	name : "统计",
	checked : true
}, {
	id : 13,
	pId : 1,
	name : "配置",
	open : true
}, {
	id : 301,
	pId : 13,
	name : "基础内容配置",
	checked : true
}, {
	id : 302,
	pId : 13,
	name : "科室介绍管理",
	checked : true
}, {
	id : 305,
	pId : 13,
	name : "医生管理",
	checked : true
}, {
	id : 303,
	pId : 13,
	name : "消息模板配置",
	checked : true
}, {
	id : 304,
	pId : 13,
	name : "客户端版本发布",
	checked : true
}, {
	id : 14,
	pId : 1,
	name : "管理",
	open : true
}, {
	id : 401,
	pId : 14,
	name : "会员管理",
	checked : true
}, {
	id : 402,
	pId : 14,
	name : "系统用户管理",
	checked : true
} ];

$(document).ready(function() {
	//显示管理菜单
	$('.menu-item-cnt').attr("style", "display:none;");
	$('.menu-item-cnt:eq(4)').attr("style", "display:block");
	
	$.fn.zTree.init($("#ztree"), setting, zNodes);

	$('#addButton').click(function() {
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
		
		$('#au_ids').val(result);


		$('#roleAddForm').submit();
	});

});
