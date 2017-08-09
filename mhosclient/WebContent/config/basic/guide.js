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

			// 加载就医指南,默认为挂号须知
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
			
			$("#reg_guide").click(function() {
				setting("1",$(this).text(),"getregnotice.html");
				getGuide();
			});

			$("#doc_duide").click(function() {
				setting("2",$(this).text(),"getdocnotice.html");
				getGuide();
			});
			
			$("#med_guide").click(function() {
				setting("3",$(this).text(),"getmednotice.html");
				getGuide();
			});
			
			$("#hos_guide").click(function() {
				setting("4",$(this).text(),"gethosnotice.html");
				getGuide();
			});
			
			$("#exit_guide").click(function() {
				setting("5",$(this).text(),"gethosnotice.html");
				getGuide();
			});

			// 提交就医指南
			$("#addGuideForm").submit(
					function() {
						if ($.trim($("#content").val()) == '') {
							//$(".error").show();
							showTips("须知内容不能为空！",100,2);
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