$(document).ready(function() {
	$("#pub_time").html(new Date().format('yyyy-MM-dd hh:mm:ss'));
	
	var div_cnt = $("#div_content");
	var hid_cnt = $("#content");
	div_cnt.html(hid_cnt.val());
	
	
	$("#act_cancel").click(function(e){
		if (!confirm("确认要取消？")){
			return;
		}
		var id = $("#infoid").val();
		var url = "hosinfomng/submitCancel";
		$.post(url, {infoid:id}, function(data, status){
			if (data && data.msg){
				alert(data.msg);
			}else{
				alert("取消操作提交成功！");
				window.location=$("base")[0].href + "hosinfomng/hos_pub_info_main_pub.jsp";
			}
		});
	});
	


	
});




/** 
 * 时间对象的格式化; 
 */  
Date.prototype.format = function(format) {  
    /* 
     * eg:format="yyyy-MM-dd hh:mm:ss"; 
     */  
    var o = {  
        "M+" : this.getMonth() + 1, // month  
        "d+" : this.getDate(), // day  
        "h+" : this.getHours(), // hour  
        "m+" : this.getMinutes(), // minute  
        "s+" : this.getSeconds(), // second  
        "q+" : Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S" : this.getMilliseconds()  
        // millisecond  
    };  
  
    if (/(y+)/.test(format)) {  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4  
                        - RegExp.$1.length));  
    }  
  
    for (var k in o) {  
        if (new RegExp("(" + k + ")").test(format)) {  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1  
                            ? o[k]  
                            : ("00" + o[k]).substr(("" + o[k]).length));  
        }  
    }  
    return format;  
};

