
$(document).ready(function() {
	
	$("#expdate").datepicker({dateFormat:'yy-mm-dd'});
	
	fileUploader({
		action:'hosinfomng/uploadimage',
	    file:$('#image'),
	    complete: function(response){ 
	    	//上传成功后处理回调
	        var d = $.parseJSON(response);
	        if (d.url){
	        	$('#img_prev').attr('src', '');
	        	$('#img_prev').attr('src', d.url);
	        }
	        
	    },
	    beforeUpLoad: function() {
	    	var filepath=$("#image").val();
	        var extStart=filepath.lastIndexOf(".");
	        var ext=filepath.substring(extStart,filepath.length).toUpperCase();
	        if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
	        	//alert("图片限于bmp,png,gif,jpeg,jpg格式！");
	        	showTips("图片限于bmp,png,gif,jpeg,jpg格式！",350,2);
	        	//return false;
	        }
	        //return true;     
	        return false;
	    },
	    afterUpLoad: function() {
	    	
	    }
	});
	
	$("#infotype").change(function(e){
		var url = "hosinfomng/getinfocats?infotype=" + $(this).val();
		var sel = $('#infocat');
		$.get(url , function(data, status){
			sel.empty();
			var jarr = eval(data);
			for (var i in jarr){
				var o = $('<option></option>').val(jarr[i].infoCatCode).html(jarr[i].infoCatName);
				sel.append(o.get());
			}
		});
	});
	
	$("#subject").change(function(e){
		$("#subject_prev").html($(this).val());
	});
	
	$("#concise").change(function(e){
		var concise = $(this).val();
		if(concise.length>50){
			$(this).val(concise.substring(0,50));
		}
		$("#concise_prev").html($(this).val());
	});
	
	
	$("#btn_prev").click(function(e) {
		e.preventDefault();
		$.cookie("img_prev_src", $("#img_prev").attr('src'));
		var f = this.form;
		f.action = 'hosinfomng/prevtemphosinfo?infoid=' + $("#infoid").val();
		f.target = '_self';
		f.submit();
		
	});
	
	$("#btn_submit").click(function(e) {
		e.preventDefault();
		
		if($.trim($('#subject').val())==''){
			showTips("标题不能为空！",650,2);
			return false;
		}
		
		if ($('#updateaction').val() == 'add'){
			if($.trim($('#image').val())==''){
				showTips("图片不能为空！",650,2);
				return false;
			}else if(!imageFormat.test($('#image').val())){
				showTips("图片限于bmp,png,gif,jpeg,jpg格式！",650,2);
				return false;
			}
		}
		
		if($.trim($('#concise').val())==''){
			showTips("摘要不能为空！",650,2);
			return false;
		}else if($('#concise').val().length>50){
			showTips("摘要长度不能大于50！",650,2);
			return false;
		}
		
		if($.trim($('#expdate').val())==''){
			showTips("预期发布日期不能为空！",650,2);
			return false;
		}else if(!dataFormat.test($('#expdate').val())){
			showTips("预期发布日期格式不正确！",650,2);
			return false;
		}
		
		if($.trim($('#content').val())==''){
			showTips("正文不能为空！",650,2);
			return false;
		}
		
		var f = this.form;
		if ($('#updateaction').val() == 'add'){
			f.action = 'hosinfomng/addhosinfo';
		}else{
			f.action = 'hosinfomng/edithosinfo';
		}
		f.target = '_self';
		f.submit();
		
	});
	var ck = $.cookie("img_prev_src");
	if ($("#imageurl").val()){
		$('#img_prev').attr('src', '');
		$('#img_prev').attr('src', $("#imageurl").val());
	}else if (ck){
		$('#img_prev').attr('src', '');
		$('#img_prev').attr('src', $.cookie("img_prev_src"));
		$.removeCookie("img_prev_src");
	}
	$("#subject_prev").html($("#subject").val());
	$("#concise_prev").html($("#concise").val());

	
});
