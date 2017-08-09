//图片格式
var imageFormat = /^\S+[\.bmp|\.gif|\.jpg|\.jpeg]$/i;

// 应用格式
var appFormat = /^\S+[\.apk|\.ipa]$/i;

// 日期格式
var dataFormat = /^(19|20)\d{2}-(0\d|1[1-2])-([0-2]\d|3[1-2])$/;

// contetn为要显示的内容
// height为离窗口顶部的距离
// time为多少秒后关闭的时间，单位为秒
function showTips(content, height, time) {
	// 窗口的宽度
	var windowWidth = $(window).width();

	//顶部窗口
	//var topFrame = window.parent.frames["content"].document;
	var topFrame = window.parent.frames["content"];

	$('.tipsClass', topFrame).html('');
	var tipsDiv = '<div class="tipsClass">' + content + '</div>';

	//height = 3;

	$("body", topFrame).append(tipsDiv);

	//显示
	$('div.tipsClass', topFrame).css({
		'top' : height + 'px',
		'left' : (windowWidth / 2) - 350 / 2 + 'px',
		'position' : 'absolute',
		'padding' : '3px 5px',
		'background' : '#0079ff',
		'font-size' : 14 + 'px',
		'margin' : '0 auto',
		'text-align' : 'center',
		'width' : '350px',
		'height' : 'auto',
		'color' : '#fff',
		'opacity' : '0.9'
	}).show();
	
	//消失
	setTimeout(function() {
		$('div.tipsClass', topFrame).fadeOut();
	}, (time * 1000));
}

function login_tips(content, height, time) {
	// 窗口的宽度
	var windowWidth = $(window).width();

	$('.tipsClass').html('');
	var tipsDiv = '<div class="tipsClass">' + content + '</div>';

	$("body").append(tipsDiv);

	//显示
	$('div.tipsClass').css({
		'top' : height + 'px',
		'left' : (windowWidth / 2) - 350 / 2 + 'px',
		'position' : 'absolute',
		'padding' : '3px 5px',
		'background' : '#0079ff',
		'font-size' : 14 + 'px',
		'margin' : '0 auto',
		'text-align' : 'center',
		'width' : '350px',
		'height' : 'auto',
		'color' : '#fff',
		'opacity' : '0.9'
	}).show();
	
	//消失
	setTimeout(function() {
		$('div.tipsClass').fadeOut();
	}, (time * 1000));
}

/**
 * 获取指定天数后的日期
 * @param addDays 指定天数，如果要获取过去时间则传负数值
 * @returns 指定天数后的日期，格式为“2017-05-04”
 */
function getDayAdd(addDays){
    var date1 = new Date();
    var date2 = new Date(date1);
    date2.setDate(date1.getDate() + addDays);
    var month = date2.getMonth() + 1;
    if(month < 10){
    	month = "0" + month;
    }
    var date = date2.getDate();
    if(date < 10){
    	date = "0" + date;
    }
    var time = date2.getFullYear() + "-" + month + "-" + date;
    return time;
}

/**
 * 获取当前日期
 * @returns 当前日期，格式为“2017-05-04”
 */
function getTodayDate(){
	var date1 = new Date();
	var month = date1.getMonth() + 1;
    if(month < 10){
    	month = "0" + month;
    }
    var date = date1.getDate();
    if(date < 10){
    	date = "0" + date;
    }
    var time = date1.getFullYear() + "-" + month + "-" + date;
	return time;
}

/**
 * 获取指定月后的年月
 * @param addMonths 指定月数，如果要获取过去时间则传负数值
 * @returns 指定月数后的年月，格式为“2017-05”
 */
function getMonthAdd(addMonths){
    var date1 = new Date();
    var date2 = new Date(date1);
    date2.setMonth(date2.getMonth() + addMonths);
    var month = date2.getMonth() + 1;
    if(month < 10){
    	month = "0" + month;
    }
    var time = date2.getFullYear() + "-" + month;
    return time;
}

/**
 * 获取当前年月
 * @returns 当前年月，格式为“2017-05”
 */
function getCurrMonth(){
	var date1 = new Date();
	var month = date1.getMonth() + 1;
    if(month < 10){
    	month = "0" + month;
    }
    var time = date1.getFullYear() + "-" + month;
	return time;
}

/**
 * 计算两个日期之间相差的天数
 * @param startDate 开始日期，格式为2017-05-19
 * @param endDate 结束日期，格式为2017-05-19
 * @returns 相差天数
 */
function getDateDiff(startDate,endDate){
    var startTime = new Date(Date.parse(startDate.replace(/-/g,   "/"))).getTime();     
    var endTime = new Date(Date.parse(endDate.replace(/-/g,   "/"))).getTime();     
    var dates = Math.abs((startTime - endTime))/(1000*60*60*24);     
    return dates;
}

/**
 * 计算两个日期之间相差的月数
 * @param startDate 开始日期，格式为2017-05-19
 * @param endDate 结束日期，格式为2017-05-19
 * @returns 相差月数
 */
function getMonthDiff(startDate,endDate){
	var year1 =  startDate.split('-')[0];
	var year2 =  endDate.split('-')[0]; 
	var month1 = startDate.split('-')[1];
	var month2 = endDate.split('-')[1];
	  
	var len = (year2 - year1) * 12 + (month2 - month1);
	return len;
}

/**
 * 根据数据得出y轴最小值
 * @param rows 数据list
 * @param fieldName 数据的字段名称
 */
function getyAxisMinValue(rows, fieldName){
	if(rows == null || rows.length == 0){
		return 0;
	}
	var minValue = null;
	for ( var i = 0; i < rows.length; i++) {
		if(minValue == null){
			minValue = eval("rows[i]." + fieldName);
		}else{
			if(rows[i].count < minValue){
				minValue = eval("rows[i]." + fieldName);
			}
		}
	}
	if(minValue != 0){// 如果循环得出的最小值不是0，则返回最小值-1
		minValue -= 1;
	}
	return minValue;
}