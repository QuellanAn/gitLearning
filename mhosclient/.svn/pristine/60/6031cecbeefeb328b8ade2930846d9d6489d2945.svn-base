


var column = 4;/* 这里设置一行放多少个选项 */
var optionArray = new Array();

$(document).ready(function(){	
	
	var questions = eval('(' + $('#question_obj_input').val() + ')');
	var quesCount = questions.length;	
	column = column >=2 ? column : 2;//防止手贱设置一行少于两个选项
	
	/* ****拼接，拼接，拼接....**** */
	for (var i = 0; i < quesCount; i++) {
		optionArray.push(questions[i].options);
		var domString = '<div class="question_div" style="border: 1px solid rgb(192, 192, 192); margin-top: 20px; padding: 5px 20px;display: block;margin-right : 25px;">'+
							'<div class="block_table" style="width: 935px;height: 32px;">'+
								'<span class="f-fl table_vertical wtlx_title" style="line-heigh: 32px;">问题类型：</span> '+
								'<select id="sel_type" class="table_vertical sel_type" style="width: 120px;height:27px;margin-top: 2px;" onchange="javascript:addAnswers('+questions[i].type+', this.value, this, '+i+', false)">';						
		/* 拼接问题类型选择 */
		if (questions[i].type == 1) {
			domString += '<option value="0">单项选择题</option>'+
			   				'<option value="1" selected="selected">多项选择题</option>'+
			   				'<option value="2">表达题</option>';
		}else if (questions[i].type == 2) {
			domString +=  '<option value="0">单项选择题</option>'+
						'<option value="1">多项选择题</option>'+
						'<option value="2" selected="selected">表达题</option>';
		}else{
			domString +=  '<option value="0" selected="selected">单项选择题</option>'+
							'<option value="1">多项选择题</option>'+
							'<option value="2">表达题</option>';
		}			
								
		domString += '</select>'+
					'<input id="inp_subject" class="table_vertical inp_subject" style="width: 615px; height: 25px;margin-left: 5px;margin-top: 2px;" value="'+questions[i].subject+'"> '+
					'<input type="button" value="删除" class="normal-btn table_vertical question_delete_btn" style="text-align: center; padding: 0; height: 30px;width: 50px;margin-left: 5px;margin-top: 2px;" onclick="deleteQuestion(this)">'+
					'</div>'+
					'<div class="option_content_div" style="width: 935px;">';
					
		if (questions[i].type == 2) {	
			domString += '<textarea class="all_option_textarea all_option" rows="" cols="" readonly="readonly" style="box-sizing: border-box; height: 60px;margin-top: 5px;resize : none;">表达题答案由用户填写.</textarea>';
		}else{
			/* 拼接答案选项 */
			var opCount = questions[i].options.length;
			var row = Math.ceil(opCount/column);
			var topCount = 0;//计数位
			for (var j = 0; j < row; j++) {
				domString +='<div class="all_option_input_div all_option" style="display: inline-block;height: 32px;margin-top: 5px;">';
				for (var k = 0; k < column; k++) {
					topCount++;
					domString += getSoloOptionDomString(topCount,questions[i].options[topCount-1]);
					if (opCount == topCount) {
						break;
					}
				}			
				domString +='</div>';
			}
		}
					
		domString +='</div>'+
					'</div>';
					
		
		$('#questions').append(domString);
	}
	
	setDomSize();//修改完dom要设置一下每个dom的大小		
	
});

function addQuestions() {
	var domString = '<div class="question_div" style="border: 1px solid rgb(192, 192, 192); margin-top: 20px; padding: 5px 20px;display: block;margin-right : 25px;">'+
						'<div class="block_table" style="width: 935px;height: 32px;">'+
							'<span class="f-fl table_vertical wtlx_title" style="line-heigh: 32px;">问题类型：</span> '+
							'<select id="sel_type" class="table_vertical sel_type" style="width: 120px;height:27px;margin-top: 2px;" onchange="javascript:addAnswers(0, this.value, this, 0, true)">'+						
							'<option value="0" selected="selected">单项选择题</option>'+
							'<option value="1">多项选择题</option>'+
							'<option value="2">表达题</option>'+
							'</select>'+
							'<input id="inp_subject" class="table_vertical inp_subject" style="width: 615px; height: 25px;margin-left: 5px;margin-top: 2px;" value=""> '+
							'<input type="button" value="删除" class="normal-btn table_vertical question_delete_btn" style="text-align: center; padding: 0; height: 30px;width: 50px;margin-left: 5px;margin-top: 2px;" onclick="deleteQuestion(this)">'+
							'</div>'+
							'<div class="option_content_div" style="width: 935px;">';
				
	/* 拼接答案选项 */
	domString +='<div class="all_option_input_div all_option" style="display: inline-block;height: 32px;margin-top: 5px;">';
	for (var k = 0; k < 2; k++) {
		domString += getSoloOptionDomString(k+1,'');
	}			
	domString +='</div>';
	domString += getModifyBtnDomString(0);			
	domString +='</div>'+
				'</div>';
				
	$('#questions').append(domString);
	setDomSize();//修改完dom要设置一下每个dom的大小
};

function deleteQuestion(obj) {
	$(obj).parent().parent().remove();
}

function addAnswers(oldtype,curtype,obj,index,isNewQuestion) {
	var div = $(obj).parent().parent();
	$(div).find(".all_option,.option_btn_div").remove();
	if (curtype == 0 || curtype == 1) {
		var options = optionArray.length > 0 ? optionArray[index] : [];
		if (oldtype == curtype) {
			// 拼接答案选项 
			var domString = '';
			var opCount = options.length;
			if (curtype == 0) {
				opCount = opCount < 2 ? 2 : opCount;
			}else if (curtype == 1) {
				opCount = opCount >= 4 ? opCount : 4;
			}
			var row = Math.ceil(opCount/column);
			var topCount = 0;//计数位
			for (var j = 0; j < row; j++) {
				domString +='<div class="all_option_input_div all_option" style="display: inline-block;height: 32px;margin-top: 5px;">';
				for (var k = 0; k < column; k++) {
					topCount++;
					var optionText = '';
					if (!isNewQuestion && options[topCount-1] != null && options[topCount-1] != "undefined") {
						optionText = options[topCount-1];
					}
					domString += getSoloOptionDomString(topCount,optionText);
					if (opCount == topCount) {
						break;
					}
				}			
				domString +='</div>';
			}
			domString += getModifyBtnDomString(curtype);
			$(div).find(".option_content_div").append(domString);
		}else{
			var domString = '<div class="all_option_input_div all_option" style="display: inline-block;height: 32px;margin-top: 5px;">';
			for (var k = 0; k < column; k++) {
				domString += getSoloOptionDomString(k+1,'');
			}
			domString +='</div>';
			domString += getModifyBtnDomString(curtype);
			$(div).find(".option_content_div").append(domString);
		}
	}else if (curtype == 2) {
		$(div).find(".option_content_div").append('<textarea class="all_option_textarea all_option" rows="" cols="" readonly="readonly" style="box-sizing: border-box;width: 100%; height: 60px;margin-top: 5px;resize : none;">表达题答案由用户填写.</textarea>');
	}
	setDomSize();//修改完dom要设置一下每个dom的大小
}
function addOption(obj,curtype) {
	var div = $(obj).parent().parent();
	var lastIndex = $(div).find(".all_option_input_div").length -1;
	var lastObj = $(div).find(".all_option_input_div").eq(lastIndex);
	var lastTitleStr = lastObj.find(".xx_title").eq(lastObj.find(".xx_title").length -1).text();
	var lastTitleLength = lastTitleStr.length;
	var lastNum = Number((lastTitleStr.substring(0,lastTitleLength - 1)).substring(2));
	if (lastNum == 10) {
		return;
	}
	if(lastNum % column == 0) {
		$(div).find(".option_btn_div").remove();
		$(div).append('<div class="all_option_input_div all_option" style="display: inline-block;height: 32px;margin-top: 5px;">'+
						getSoloOptionDomString(lastNum+1,'')+
						'</div>'+getModifyBtnDomString(curtype));
	}else{
		lastObj.append(getSoloOptionDomString(lastNum+1,''));
	}
	
	setDomSize();//修改完dom要设置一下每个dom的大小
}
function deleteOption(obj,curtype) {
	var div = $(obj).parent().parent();
	var lastIndex = $(div).find(".all_option_input_div").length -1;
	var lastObj = $(div).find(".all_option_input_div").eq(lastIndex);
	var lastTitleStr = lastObj.find(".xx_title").eq(lastObj.find(".xx_title").length -1).text();
	var lastTitleLength = lastTitleStr.length;
	var lastNum = Number((lastTitleStr.substring(0,lastTitleLength - 1)).substring(2));
	if (lastNum == 2 && curtype == 0) {//单选题最少两个答案
		return;
	}else if (lastNum == 4 && curtype == 1) {//多选题最少四个答案
		return;
	}
	var lastInputCount = lastObj.find(".solo_option_div").length;
	if (lastInputCount == 1) {
		lastObj.remove();
		$(div).find(".option_btn_div").remove();
		$(div).append(getModifyBtnDomString(curtype));
	}else{
		lastObj.find(".solo_option_div").eq(lastInputCount - 1).remove();
	}
	
	setDomSize();//修改完dom要设置一下每个dom的大小
}

function setDomSize() {
	var textareaW = $('.sel_type').outerWidth(true) + $('.inp_subject').outerWidth(true);
	var optionW = $('.wtlx_title').outerWidth(true) + textareaW;
	var xxTitleW = $('.wtlx_title').width();
	var xxInputW = (optionW - xxTitleW * column) / column;
	$('.solo_option_div').css({'width':xxTitleW+xxInputW+'px'});
	$('.xx_title').css({'width':xxTitleW+'px'});
	$('.inp_option').css({'width':xxInputW+'px'});
	$('.all_option_textarea').css({'margin-left':xxTitleW+'px','width':textareaW+'px'});
	$('.all_option_input_div').css({'width':optionW+'px'});
	$('.option_btn_div').css({'margin-right':($('.option_content_div').width() - $('.question_delete_btn').position().left + 5)+'px'});
}
//获取添加和删除选项按钮的节点字符串
function getModifyBtnDomString(curtype) {
	return '<div class="option_btn_div" style="float: right;display: inline-block;padding: 0; height: 25px;width: 60px;margin-top: 8px;">'+
			'<img src="resources/images/icon_add_option.png" style="float: left;display: inline-block; height: 25px;width: 25px;" onclick="addOption(this,'+curtype+')">'+
			'<img src="resources/images/icon_delete_option.png" style="float: right;display: inline-block; height: 25px;width: 25px;" onclick="deleteOption(this,'+curtype+')">'+
			'</div>';
}
//获取单个选项节点字符串
function getSoloOptionDomString(topCount,optionText) {
	return '<div class="block_table solo_option_div" style="display: inline-block;height: 32px;">'+
			'<span class="f-fl table_vertical xx_title" style="line-heigh: 32px;text-align: right;">选项'+topCount+'：</span>'+
			'<input class="table_vertical inp_option" style="box-sizing: border-box;width: 615px; height: 25px;margin-top: 4px;" value="'+optionText+'">'+
			'</div>';
}

function checkQuestionnaire() {
	var title = $('#title').val();
	if (title.length == 0) {
		checkRedBorder($('#title'),0);
		$('.tab-cnt').scrollTop(0);
		$(window).scrollTop(0);
		showToast("请填写标题！");
		return false;
	}
	var subtitle = $('#sub_title').val();
	if (subtitle.length == 0) {
		checkRedBorder($('#sub_title'),0);
		$('.tab-cnt').scrollTop(0);
		$(window).scrollTop(0);
		showToast("请填写副标题！");
		return false;
	}
	var questionTitles = document.body.querySelectorAll('.inp_subject');
	var flag = 0;
	for (var i = 0; i < questionTitles.length; i++) {
		if ($(questionTitles[i]).val().length == 0) {
		    checkRedBorder($(questionTitles[i]),1);
			if (flag == 0) {
			    scrollToCurDom($(questionTitles[i]).parent().parent());
			}
		    flag = 1;
			showToast("请填写红框内容！");
		}
		if (flag == 1 && i == questionTitles.length - 1) { return false; }
	}
	var optionTexts = document.body.querySelectorAll('.inp_option');
	var flag1 = 0;
	for (var i = 0; i < optionTexts.length; i++) {
		if ($(optionTexts[i]).val().length == 0) {
		    checkRedBorder($(optionTexts[i]),1);
			if (flag1 == 0) {
			    scrollToCurDom($(optionTexts[i]).parent().parent().parent().parent());
			}
		    flag1 = 1;
			showToast("请填写红框内容！");
		}
		if (flag1 == 1 && i == optionTexts.length - 1) { return false; }
	}
	
	return true;
}
function checkRedBorder(curDom,type) {
	if (type == 0) {
		curDom.css({'border': '2px solid red'});
		curDom.bind('input propertychange', function() {
	    	if (curDom.val().length > 0) {
	    		curDom.css({'border': '2px solid #c8c8c8'});
	    	}
	    });
	}else{
		curDom.css({'border-width': '2px','border-style': 'solid','border-color': 'red','border-image': 'red'});
		curDom.bind('input propertychange', function() {
	    	if (curDom.val().length > 0) {
	    		curDom.css({'border-width': '2px','border-style': 'inset','border-color': 'initial','border-image': 'initial'});
	    	}
	    });
	}
}
function scrollToCurDom(curDom) {
	var container = $('.tab-cnt');
    container.scrollTop(curDom.offset().top - container.offset().top + container.scrollTop() - curDom.height());
}
function showToast(text) {
	$.jBox.messager(text, "提示", 2000, {
	    width: 350,
	    icon: 'info',
	    showType: 'show'		
	});
}
