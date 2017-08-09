$(document).ready(
		function() {
			// 显示编辑器
			var editor = KindEditor.create('textarea[id="notice_content"]', {
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
						'insertunorderedlist' ]
			});

			// 加载预约挂号须知
			var getResNotice = function() {
				$.getJSON("config/basic/getNoticePO",{
					"noticeId" : $("#noticeId").val(),
				},  function(data) {
					if (null != data.notice_content) {
						editor.html(data.notice_content);
						//$("#resNotice_submit").val("修改");
						$("#resNotice_submit").val("保存");
						$("#action").val("update");
					}
				});
			};
			
			getResNotice();
			
			var setting = function(noticeId,title){
				$("#title_guide").text(title);
				$("#noticeId").val(noticeId);
				editor.html("");
				$("#action").val("save");
				$("#resNotice_submit").val("保存");
			};
			
			$("#res_ddgh").click(function() {
				setting("2",$(this).text());
				$("#res_ddgh").css("background-color","#0079ff");
				$("#res_yygh").css("background-color","#fff");
				getResNotice();
			});

			$("#res_yygh").click(function() {
				setting("1",$(this).text());
				$("#res_ddgh").css("background-color","#fff");
				$("#res_yygh").css("background-color","#0079ff");
				getResNotice();
			});
	
			// 提交预约挂号须知
			$("#addResNoticeForm").submit(
					function() {
						if ($.trim($("#notice_content").val()) == '') {
							showTips("预约挂号须知不能为空",100,2);
							return false;
						}
						
						if($("#action").val()=="save"){
							$.post("config/basic/saveNoticePO", $("#addResNoticeForm")
									.serialize(), function(data) {
								//alert(data.data);
								showTips(data.data,100,2);
								$("#action").val("update");
								//$("#resNotice_submit").val("修改");
								$("#resNotice_submit").val("保存");
							}, "json");
						}else{
							$.post("config/basic/updateNoticePO", $("#addResNoticeForm")
									.serialize(), function(data) {
								//alert(data.data);
								showTips(data.data,100,2);
							}, "json");
						}
						
						return false;
					});
		});