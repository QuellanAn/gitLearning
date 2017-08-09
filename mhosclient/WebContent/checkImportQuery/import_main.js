$(document).ready(function() {
	$('input[name=createTime]').datepicker({ dateFormat: 'yy-mm-dd' });
	
//	var statu = confirm('是否需要下载模板文件');
//	  if(!statu){
//		  return false;
//	  }else{
//		  exportMoban();
//	  }
});

function importExcle() {
	var name = $("#name").val();
	var createTime = $("#createTime").val();
	var upload = $("#upload").val();
	if ($.trim(name).length == 0 || $.trim(upload).length == 0
			|| $.trim(createTime).length == 0) {
		alert("请输入完整的信息!");
		return false;
	}
	if(upload.length>0) {
        var ldot = upload.lastIndexOf(".");
        var type = upload.substring(ldot + 1);
        if(type=="xls" || type=="xlsx") {
        	$("#type").val(type);
			/*$("#act_query").submit();  */
        	var formData = new FormData($( "#checkForm" )[0]);  
            $.ajax({  
                 url: 'unpaid/checkImportAction_addCheck' ,  
                 type: 'POST',  
                 data: formData,  
                 async: false,  
                 cache: false,  
                 contentType: false,  
                 processData: false,  
                 success: function () {  
                     alert("操作成功！");
                     window.location.href="checkImportQuery/ImportQuery_main.jsp";
                 },  
                 error: function () {  
                     alert("操作失败！");  
                     return false;
                 }  
            });  
        }else{
        	alert("文件格式不准确！");
        	input.outerHTML=input.outerHTML.replace(/(value=\").+\"/i,"$1\"")
        	return false;
        }    
	}
}

function exportMoban() {
	document.location.href="unpaid/checkImportAction_modeDown";
};