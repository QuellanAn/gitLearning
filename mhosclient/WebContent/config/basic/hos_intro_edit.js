$(document).ready(function() {
	fileUploader({
		action:'config/basic/prevHosImage',
	    file:$('#hos_image'),
	    complete: function(response){ 
	    	//上传成功后处理回调
	        var d = $.parseJSON(response);
	        if (d.url){
	        	$('#hos_image_prev').attr('src', '');
	        	$('#hos_image_prev').attr('src', d.url);
	        }
	        
	    },
	    beforeUpLoad: function() {
	    	//由于百度服务器不允许创建在指定目录下生成文件,所以此处无法上传,需屏蔽.
	    	var filepath=$("#hos_image").val();
	        var extStart=filepath.lastIndexOf(".");
	        var ext=filepath.substring(extStart,filepath.length).toUpperCase();
	        if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
	        	alert("图片限于bmp,png,gif,jpeg,jpg格式！");
	        	$("#hos_image").val("");
	        	//return false;
	        }
	        return false;
	        //return true;     
	    },
	    afterUpLoad: function() {
	    	
	    }
	});
	
	$("#hos_image").change(function(){
		var objUrl = getObjectURL(this.files[0]) ;
		console.log("objUrl = "+objUrl) ;
		if (objUrl) {
			$("#img").attr("src", objUrl) ;
		}
	}) ;	

	function getObjectURL(file) {
	var url = null ; 
	if (window.createObjectURL!=undefined) {
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) {
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) {
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
	};
	
});