$(document).ready(
		function() {
			//显示配置菜单
			$('.menu-item-cnt').attr("style", "display:none;");
			$('.menu-item-cnt:eq(3)').attr("style", "display:block");
			
			// 显示编辑器
			var editor = KindEditor.create('textarea[id="content"]', {
				uploadJson : 'config/basic/cntCfgImagUpload?cfg_type='
						+ $('#cfg_type').val() + "&cat=" + $('#cat').val(),
				resizeType : 0,
				width : '621px',
				minWidth : '350px',
				height : '350px',
				allowPreviewEmoticons : false,
				allowImageUpload : true,
				afterBlur : function() {
					this.sync();
				},
				items : [ 'fontname', 'fontsize', '|', 'forecolor',
						'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter',
						'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image',
						'link' ]
			});

			//$(".error").hide();

			// 加载常见问题,默认为Android版本
			var getGuide = function() {
				$.getJSON("config/basic/getGuide", {
					"cfg_type" : $("#cfg_type").val(),
					"cat" : $("#cat").val()
				}, function(data) {
					if (null != data.content) {
						editor.html(data.content);
						$("#title_guide").val(data.cat_name);
						//$("#guide_submit").val("修改");
						$("#guide_submit").val("保存");
						$("#action").val("update");
					}
				});
			};
			
			getGuide();
			
			var setting = function(cat,title,htmlFileName){
				$("#title_guide").text(title);
				$("#cat").val(cat);
				$("#cat_name").val(title);
				$("#htmlFileName").val(htmlFileName);
				editor.html("");
				$("#action").val("save");
				$("#guide_submit").val("保存");
			};
			
			$("#android_faq").click(function() {
				setting("1",$(this).text(),"getandroidfaq.html");
				getGuide();
			});

			$("#ios_faq").click(function() {
				setting("2",$(this).text(),"getiosfaq.html");
				getGuide();
			});
			
			// 提交常见问题
			$("#addGuideForm").submit(
					function() {
						if ($.trim($("#content").val()) == '') {
							//$(".error").show();
							showTips("常见问题不能为空！",100,2);
							return false;
						}
						
						if($("#action").val()=="save"){
							$.post("config/basic/saveGuide", $("#addGuideForm")
									.serialize(), function(data) {
								showTips(data.data,100,2);
								$("#action").val("update");
								//$("#guide_submit").val("修改");
								$("#guide_submit").val("保存");
							}, "json");
						}else{
							$.post("config/basic/updateGuide", $("#addGuideForm")
									.serialize(), function(data) {
								showTips(data.data,100,2);
							}, "json");
						}
						
						return false;
					});
		});