$(document).ready(function() {
	role.findAll("1");
	
});

function refresh_unpubcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

var role = {
	findAll:function(pageNo){
		var cnt = $('#div_cnt_table');
		$.post(
			'role_findAll',
			{pageNo:pageNo},
			function(data, status){
			if (data){
				cnt.empty();
				cnt.append(data);
			}
		});
	},
	deleteRole:function(id, pageNo){
		if (confirm("你确定删除吗?")) {
			var cnt = $('#div_cnt_table');
			$.get(
				'role_delete?role.roleId=' + id,
				{pageNo:pageNo},
				function(data, status){
				if (data){
					cnt.empty();
					cnt.append(data);
				}
			});
		}
	},
	modifyRole:function(span) {
		$(span).parents().submit();
	},
};