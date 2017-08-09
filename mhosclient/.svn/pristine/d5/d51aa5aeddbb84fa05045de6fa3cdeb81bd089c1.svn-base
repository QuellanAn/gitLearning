$(document).ready(function() {
	$('input[name=createTime]').datepicker({ dateFormat: 'yy-mm-dd' });
});

function exportFile() {
	var name=$("#name").val();
	var createTime=$("#createTime").val();
	var submit = function (v) {
	    if (v == 'ok'){
    		var paras = $("#checkForm").serialize();
	    	document.location.href='unpaid/checkImportAction_exportOtherCheck?'+paras;
	    }
	    else if (v == 'cancel'){
	    	jBox.tip("操作取消", 'info');
		    return true;
	    }
	};
	if($.trim(name).length==0 ||$.trim(createTime).length==0){
		jBox.alert("请填写完整信息", '提示');
	}else{
		$.jBox.confirm("是否导出数据？", "提示", submit);
	}
	
};