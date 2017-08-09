$(document).ready(function() {
	$('input[name=sq_startdate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=sq_enddate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=sq_startmonth],input[name=sq_endmonth]').datepicker({
		monthNamesShort: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],  // 区域化月名为中文  
		prevText: '上月',         // 前选按钮提示  
		nextText: '下月',         // 后选按钮提示  
		changeYear: true,         // 年下拉菜单  
		changeMonth: true,        // 月下拉菜单  
		showButtonPanel: true,    // 显示按钮面板  
		showMonthAfterYear: true, // 月份显示在年后面  
		currentText: "本月",      // 当前日期按钮提示文字  
		closeText: "确定",        // 关闭按钮提示文字  
		dateFormat: "yy-mm",      // 日期格式
	    onClose: function(dateText, inst) {
	        var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val(); 
	        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val(); 
	        $(this).datepicker('setDate', new Date(year, month, 1)); 
	    }
	});
	
	loadUserSource();
	initDepartment();
//	$("a.selectTime:eq(0)").click();
	
	$("#act_query").click(function(){
		updateChart();
	});
	
	$('input[name=countType]').change(function(){
		var countType = $(this).val();
		if(countType == "day"){
			$("#daySelect").show();
			$("#monthSelect").hide();
			$('input[name=sq_startdate],input[name=sq_enddate]').show();
			$('input[name=sq_startmonth],input[name=sq_endmonth]').hide();
		}else{
			$("#monthSelect").show();
			$("#daySelect").hide();
			$('input[name=sq_startdate],input[name=sq_enddate]').hide();
			$('input[name=sq_startmonth],input[name=sq_endmonth]').show();
			if($('input[name=sq_startmonth]').val() == '' ||
					$('input[name=sq_endmonth]').val() == ''){
				// 默认选择最近3个月
				$("a.selectMonth:eq(0)").click();
			}
		}
	});
	
	// 选择月份时隐藏日期表格
	$('input[name=sq_startmonth],input[name=sq_endmonth]').click(function(){
		$("#ui-datepicker-div").addClass("monthDiv");
		var currValue = $(this).val();
		if(currValue != ''){
			var year = currValue.split("-")[0];
			var month = currValue.split("-")[1];
			$(".ui-datepicker-year").val(year);
			$(".ui-datepicker-month").val(parseInt(month) - 1);
		}
	});
	
	// 选择日期的时候恢复日期表格的显示
	$('input[name=sq_startdate],input[name=sq_enddate]').click(function(){
		$("#ui-datepicker-div").removeClass("monthDiv");
	});
	
	$('#countMonth').click();
});

/**
 * 更新显示的图表
 */
function updateChart(){
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	// 统计类型，day为按日，month为按月
	var countType = $("input[name=countType]:checked").val();
	if(countType == "month"){
		beginDate = $("#beginMonth").val();
		endDate = $("#endMonth").val();
	}
	var userSource = $("#userSource").val();
	var departmentCode = $("#departmentCode").val();// 科室id
	var myChart = echarts.init(document.getElementById('Section1'));
	var title = "好评率趋势分析图";
	var numText = "评价总数";
	myChart.showLoading({
        text: '读取数据中...' // loading，是在读取数据的时候显示
    });
	$.ajax({
		type : "post",
		url : "serviceEvaluationStatisticsAction_getCommentCountByDate",
		data:{"beginDate":beginDate, "endDate":endDate,
			"departmentId":departmentCode, "evaluateSource":userSource, "countDateType":countType,"ajaxType":"json"},
		dataType : "json",
		async : false,
		success : function(data) {
			if(eval(data).json!=null){
				window.setTimeout(function () { window.top.location.href = "session.jsp"; }, 100);
				return false;
			}
			var sum = eval(data.sum);
			$("div.divSum span.num").text(sum);
			$("div.divSum span.text").text(numText);
			var departmentName = data.department;
			title = departmentName + title;
			var rows = eval(data.list);
			var arr = [];
			var arrY = [];
			//调用函数获取值，转换成数组模式  
			for ( var i = 0; i < rows.length; i++) {
				arr.push(rows[i].countDate);
				arrY.push(rows[i].percentage);
			}
			option = {
				title : {
					text : title,
					x:'center',
					y:'bottom'
				},
				tooltip : {
					trigger : 'axis',
                    formatter: "{a} <br/>{b} : {c}%"
				},
				toolbox : {
					show : true,
					feature : {
						restore : {show: true},
			            saveAsImage : {show: true}
					}
				},
				xAxis : {
					type : 'category',
					boundaryGap: false,
					splitLine : {
						show : false
					},
					data : arr
				},
				grid : {
					left : '3%',
					right : '4%',
					bottom : '12%',
					containLabel : true
				},
				yAxis : {
					name : "好评率(%)",
					max : 100,
					axisLabel : {
						formatter: '{value}%'
		            }
				},
				series : [ {
					name : '好评率',
					type : 'line',
					itemStyle : {  
                        normal : {  
                        	color:'#44B549',  
                            lineStyle:{  
                                color:'#44B549'  
                            }
                        }
                    },  
					data : arrY
				} ]
			};
			myChart.setOption(option);
			myChart.hideLoading();
		},error : function(errorMsg) {
	         alert("图表请求数据失败!");
	         myChart.hideLoading();
		}
	});
}

/**
 * 加载用户来源列表
 */
function loadUserSource(){
	var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"business_source";
	var body = $('#userSource');
	body.empty();
	body.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var code = eval(data.code);
		if(code == 0){
			var arr1 = eval(data);
			var arr=eval("("+arr1.data+")");
			for (var i in arr){
				var o = $('<option></option>').val(arr[i].codeNo).html(arr[i].codeName);
				body.append(o.get());
			}
		}
	});
}

/**
 * 初始化科室列表
 */
function initDepartment(){
	var sel = $('#departmentCode');
	sel.empty();
	sel.append("<option value=''>全部</option>");
	$.ajax({  
         type : "post",  
         url : "config/department/findAllDeptment",
         data : {},  
         async : true,  
         success : function(data){  
        	var obj=eval('('+data+')');
     		var jarr = obj.depts;
     		for (var i in jarr){
     			var o = $('<option></option>').val(jarr[i].departmentId).html(jarr[i].departmentName);
     			sel.append(o.get());
     		}
         }
    }); 
	
	// 选中指定的科室
	var departmentId = $("#departmentId").val();
	if(departmentId != ''){
		$('#departmentCode').val(departmentId);
	}
};

/**
 * 点击“最近7天”等设置时间区间
 */
function setSelectTime(days){
	var beginDate = getDayAdd((-1) * (days - 1));
	var endDate = getTodayDate();
	$("#beginDate").val(beginDate);
	$("#endDate").val(endDate);
	
	updateChart();
}

/**
 * 点击“最近3个月”等设置时间区间
 */
function setSelectMonth(months){
	var beginDate = getMonthAdd((-1) * (months - 1));
	var endDate = getCurrMonth();
	$("#beginMonth").val(beginDate);
	$("#endMonth").val(endDate);
	
	updateChart();
}

/**
 * 跳转到科室服务评价统计页面
 */
function toDepartmentChange(){
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	window.location.href="serviceEvaluationStatisticsAction_toDepartmentCount?beginDate=" + beginDate + "&endDate=" + endDate; 
}