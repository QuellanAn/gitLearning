$(document).ready(function() {
	
	specialist.findAll(null, 1);

	$("#find").click(function(e) {
		var name = $("#name").val();
		specialist.findAll(name, 1);
	});

});

var specialist = {
	findAll:function(name, pageNo){
		var cnt = $('#div_cnt_table');
		$.post(
			'usermanage/specialist_findAll',
			{name:name, pageNo:pageNo},
			function(data, status){
				if (data){
					cnt.empty();
					cnt.append(data);
				}
		});
	},
	Delete:function(id){
		if (confirm("你确定删除吗?")) {
			$.post(
				'usermanage/specialist_delete?id=' + id,
				function(data, status){
					var obj = eval("("+data+")");
					if(obj.code == 0){
						alert("删除成功！");
						specialist.findAll(null,1);
					}else{
						alert("删除失败！");
					}
			});
		}
	},
	toSave:function(){
		$("#node").empty();
		$.post(
			'usermanage/specialist_toSave',
			function(data, status){
				$("#node").html(data);
				$("#node").dialog({
            		modal: true,
            		resizable:false,
            		title: "添加专家信息", 
					width: 800,
					height:600
				});
			}, "html");
	},
	toModify:function(code){
		$("#node").empty();
		$.post(
			'usermanage/specialist_toModify',
			{code:code},
			function(data, status){
				$("#node").html(data);
				$("#node").dialog({
            		modal: true,
            		resizable:false,
            		title: "更新专家信息", 
					width: 800,
					height:600
				});
			}, "html");
	}
};

function save(){
		var flag = false;
		var reg = new RegExp("^[0-9]*$");
		var reg1 = new RegExp("^.*[^a][^b][^c]\.(?:gif|jpg|jpeg|png|GIF|JPG|PNG)$");
		var code = $("#code").val();
		var name = $("#dname").val();
		var department = $("#department").val();
		var type = $("#type").val();
		var photo = $("#photo").val();
		var depict = $("#depict").val();
		
		if($.trim(code).length == 0){
			alert('医生编号不能为空！');
			return false;
		}else if(!reg.test(code)){
			alert('医生编号只包含数字！');
			return false;
		}else{
			$.ajax({
				type:"POST",
				url:"usermanage/specialist_findByCode",
				data:{code:code},
				dataType:"json",
				async : false,  
				success: function(data) {
					var obj = eval(data);
					if(obj.code == 0){
						flag = true;
					}else{
						alert('此医生编号已存在！');
					}
				},
				error:function(){
					alert("请稍后重试！");
				}
			});
			if(!flag){
				return flag;
			}
		}

		if($.trim(name).length == 0){
			alert('医生姓名不能为空！');
			return false;
		}
				
		if($.trim(department).length == 0){
			alert('所属科室不能为空！');
			return false;
		}
		
		if($.trim(type).length == 0){
			alert('医生职称不能为空！');
			return false;
		}
		
		if($.trim(photo).length == 0){
			alert('医生图片不能为空！');
			return false;
		}else if(!reg1.test(photo)){
			alert('请选择.gif,jpeg,jpg,png格式照片上传！');
			return false;
		} 
		
		if($.trim(depict).length == 0){
			alert('医生简介不能为空！');
			return false;
		}
		if(flag){
			$("#myform").submit();
		}
		alert("添加医生信息成功！");
		$("#node").dialog("close");
		specialist.findAll(null, 1);
}

function modify(){
		var flag = false;
		var reg = new RegExp("^[0-9]*$");
		var reg1 = new RegExp("^.*[^a][^b][^c]\.(?:gif|jpg|jpeg|png|GIF|JPG|PNG)$");
		var code = $("#code").val();
		var name = $("#dname").val();
		var department = $("#department").val();
		var type = $("#type").val();
		var photo = $("#photo").val();
		var depict = $("#depict").val();
		
		if($.trim(code).length != 0){
			if(!reg.test(code)){
				alert('医生编号只包含数字！');
				return false;
			}else{
				$.ajax({
					type:"POST",
					url:"usermanage/specialist_findByCode",
					data:{code:code},
					dataType:"json",
					async : false,  
					success: function(data) {
						var obj = eval(data);
						if(obj.code == 0){
							flag = true;
						}else{
							alert('此医生编号已存在！');
						}
					},
					error:function(){
						alert("请稍后重试！");
					}
				});
				if(!flag){
					return flag;
				}
			}
		}
		
		if($.trim(photo).length != 0){
			if(!reg1.test(photo)){
				alert('请选择.gif,jpeg,jpg,png格式照片上传！');
				return false;
			}
		}

		
		$("#myform").submit();
		
		alert("更新医生信息成功！");
		$("#node").dialog("close");
		specialist.findAll(null, 1);
}

