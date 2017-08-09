$(document).ready(function() {
	
	showContent();
	loadPayType();
	loadPayScene();
	loadAccount();
	loadBody();
	selectTime(7);
	var paras = $("#form_query").serialize();
	
	//initView(paras);
	$("#act_query").click(function(e) {
		selectByTime();
		return false;
	});

	$('input[name=startdate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=enddate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=startmonth],input[name=endmonth]').datepicker({
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
	});
	
	$("input[name=status]").change(function() {
		showContent();
	});
	
	$('input[name=timeType]').change(function(){
		var countType = $(this).val();
		if(countType == "1"){
			$("#daySelect").show();
			$("#monthSelect").hide();
			$('input[name=startdate],input[name=enddate]').show();
			$('input[name=startmonth],input[name=endmonth]').hide();
		}else{
			$("#monthSelect").show();
			$("#daySelect").hide();
			$('input[name=startdate],input[name=enddate]').hide();
			$('input[name=startmonth],input[name=endmonth]').show();
			selectMonth(3);
		}
	});
	
});

function selectByTime() {
	var paras = $("#form_query").serialize();
	initView(paras);
	datalist_ctrl.loaddatalist("unpaid/payOrderAction_selectListByStatistical?" + paras);
	return false;
}

var datalist_ctrl = {
		loaddatalist:function(url,paras){
				var cnt = $('#div_cnt_table');	
				$.post(url,paras,
			      	function(data, status){
					if (data && data.msg){
						jBox.alert(data.msg,'info');
					}else if (data){
						cnt.empty();
						cnt.append(data);
					}
				});
			},
			loaddatalist1:function(pageNo){
				var cnt = $('#div_cnt_table');
				var paras = $("#form_query").serialize();
				$.post('unpaid/payOrderAction_selectListByStatistical?pageNo='+pageNo+'&'+paras,
			      	function(data, status){
					if (data && data.msg){
						jBox.alert(data.msg,'error');
					}else if (data){
						cnt.empty();
						cnt.append(data);
					}
				});
			}
	};

function initView(params){
	var poType=$('input:radio[name="status"]:checked').val();
	var titleZe;
	var titleZb;
	var titleRj;
	if(poType=="1"){
		titleZe="实际总收入(元)";
		titleZb="实际总笔数(笔)";
		titleRj="人均金额(元)";
	}else if(poType=="2"){
		titleZe="交易总金额(元)";
		titleZb="交易总笔数(笔)";
		titleRj="人均交易金额(元)";
	}else{
		titleZe="退款总金额(元)";
		titleZb="退款总笔数(笔)";
		titleRj="人均退款金额(元)";
	}
	var myChartZe = echarts.init(document.getElementById('Section1'));
	myChartZe.showLoading({text : '正在努力请求数据,请稍等!' }); 
	var myChartBi = echarts.init(document.getElementById('Section2'));
	var myChartJun = echarts.init(document.getElementById('Section3'));
	
	$.ajax({
		type : "post",
		url : "unpaid/selectStatistics_selectOfStatisByAll",
		dataType : "json",
		data : params,
		async : false,
		success : function(data) {
			if(eval(data).json!=null){
				window.setTimeout(function () { window.top.location.href = "session.jsp"; }, 100);
				return false;
			}
			var rowZe = eval(data.listMapZe);
			var arrZeX = [];
			var arrZeY = [];
			for ( var i = 0; i < rowZe.length; i++) {
				arrZeX.push(rowZe[i].name);
				arrZeY.push(rowZe[i].val);
			}
			
			var rowZb = eval(data.listMapZb);
			var arrZbX = [];
			var arrZbY = [];
			for ( var i = 0; i < rowZb.length; i++) {
				arrZbX.push(rowZb[i].name);
				arrZbY.push(rowZb[i].val);
			}
			
			var rowRj = eval(data.listMapRj);
			var arrRjX = [];
			var arrRjY = [];
			for ( var i = 0; i < rowRj.length; i++) {
				arrRjX.push(rowRj[i].name);
				arrRjY.push(rowRj[i].val);
			}
			
			optionZe = {
				title : {
					text : titleZe,
					left : 'center'
				},
				tooltip : {
					trigger : 'item',
				},
				toolbox : {
					show : true,
					feature : {
						magicType : {show: true, type: ['line', 'bar']},
						restore : {}
					}
				},
				xAxis : {
					type : 'category',
					boundaryGap: false,
					splitLine : {
						show : false
					},
					axisLabel : {
//						interval:0,
                        textStyle: {
                            color: '#44B549'
                        }
                    },
					axisLine:{  
                        lineStyle:{  
                            color:'#CCCCCC',  
                            width:1,
                        }  
                    },
					data : arrZeX
				},
				grid : {
					left : '3%',
					right : '4%',
					bottom : '3%',
					containLabel : true
				},
				yAxis : {
					axisLabel : {
                        textStyle: {
                            color: '#000000'
                        }
                    },
					axisLine:{  
                        lineStyle:{  
                            color:'#ffffff',  
                            width:1,
                        }  
                    }
				},
				series : [ {
					name : titleZe,
					type : 'line',
					itemStyle : {  
                        normal : {  
                        	color:'#44B549',  
                            lineStyle:{  
                                color:'#44B549'  
                            }  
                        }  
                    },  
					data : arrZeY
				} ]
			};
			
			optionZb = {
					title : {
						text : titleZb,
						left : 'center'
					},
					tooltip : {
						trigger : 'item',
					},
					toolbox : {
						show : true,
						feature : {
							magicType : {show: true, type: ['line', 'bar']},
							restore : {}
						}
					},
					xAxis : {
						type : 'category',
						boundaryGap: false,
						splitLine : {
							show : false
						},
						axisLabel : {
	                        textStyle: {
	                            color: '#44B549'
	                        }
	                    },
						axisLine:{  
	                        lineStyle:{  
	                            color:'#CCCCCC',  
	                            width:1,
	                        }  
	                    },
						data : arrZbX
					},
					grid : {
						left : '3%',
						right : '4%',
						bottom : '3%',
						containLabel : true
					},
					yAxis : {
						axisLabel : {
	                        textStyle: {
	                            color: '#000000'
	                        }
	                    },
						axisLine:{  
	                        lineStyle:{  
	                            color:'#ffffff',  
	                            width:1,
	                        }  
	                    }
					},
					series : [ {
						name : titleZb,
						type : 'line',
						itemStyle : {  
	                        normal : {  
	                        	color:'#44B549',  
	                            lineStyle:{  
	                                color:'#44B549'  
	                            }  
	                        }  
	                    },  
						data : arrZbY
					} ]
				};
			
			optionRj = {
					title : {
						text : titleRj,
						left : 'center'
					},
					tooltip : {
						trigger : 'item',
					},
					toolbox : {
						show : true,
						feature : {
							magicType : {show: true, type: ['line', 'bar']},
							restore : {}
						}
					},
					xAxis : {
						type : 'category',
						boundaryGap: false,
						splitLine : {
							show : false
						},
						axisLabel : {
	                        textStyle: {
	                            color: '#44B549'
	                        }
	                    },
						axisLine:{  
	                        lineStyle:{  
	                            color:'#CCCCCC',  
	                            width:1,
	                        }  
	                    },
						data : arrRjX
					},
					grid : {
						left : '3%',
						right : '4%',
						bottom : '3%',
						containLabel : true
					},
					yAxis : {
						axisLabel : {
	                        textStyle: {
	                            color: '#000000'
	                        }
	                    },
						axisLine:{  
	                        lineStyle:{  
	                            color:'#ffffff',  
	                            width:1,
	                        }  
	                    }
					},
					series : [ {
						name : titleRj,
						type : 'line',
						itemStyle : {  
	                        normal : {  
	                        	color:'#44B549',  
	                            lineStyle:{  
	                                color:'#44B549'  
	                            }  
	                        }  
	                    },  
						data : arrRjY
					} ]
				};
			myChartZe.setOption(optionZe);
			$("#totalMoney").html(titleZe+"<br/>"+data.money);
			
			myChartBi.setOption(optionZb);
			$("#totalCount").html(titleZb+"<br/>"+data.count);
			
			myChartJun.setOption(optionRj);
			$("#avgMoney").html(titleRj+"<br/>"+data.avgMon);
			myChartZe.hideLoading();
		},error : function(errorMsg) {
	         jBox.alert("图表请求数据失败!",'error');
	         myChart.hideLoading();
		}
	});
};

function showContent(){
	var val=$('input:radio[name="status"]:checked').val();
	if(val==3){
		$("#jiaofei").hide();
		$("#body").hide();
		$("#zeFont").hide();
		$("#zbFont").hide();
		$("#rjFont").show();
	}else if(val==2){
		$("#jiaofei").show();
		$("#body").show();
		$("#zeFont").hide();
		$("#zbFont").show();
		$("#rjFont").hide();
	}else{
		$("#jiaofei").show();
		$("#body").show();
		$("#zeFont").show();
		$("#zbFont").hide();
		$("#rjFont").hide();
	}
};

/**
 * 加载支付方式
 */
function loadPayType(){
	var url = "unpaid/payTypeAction_findAll";
	var dis = $('#pattern');
	dis.empty();
	dis.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var jarr1 = eval(data);
        var jarr=eval("("+jarr1.data+")");
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].ptCode).html(jarr[i].ptName);
			dis.append(o.get());
		}
	});
};

function loadBody(){
	var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"payment_project";
	var body = $('#body');
	body.empty();
	body.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var arr1 = eval(data);
        var arr=eval("("+arr1.data+")");
		for (var i in arr){
			var o = $('<option></option>').val(arr[i].codeName).html(arr[i].codeName);
			body.append(o.get());
		}
	});
};

function loadPayScene(){
	var url = 'unpaid/paySceneAction_findAllJson';
	var payScene = $('#paySence');
	payScene.empty();
	payScene.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var jarr1 = eval(data);
        var jarr=eval("("+jarr1.data+")");
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].SCode).html(jarr[i].SName);
			payScene.append(o.get());
		}
	});
};

function loadAccount(){
	var url = 'unpaid/accountConfigAction_findAllJson';
	var account = $('#account');
	account.empty();
	account.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var jarr1 = eval(data);
        var jarr=eval("("+jarr1.data+")");
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].accountCode).html(jarr[i].accountName);
			account.append(o.get());
		}
	});
};

function exportCheckValue() {
	var count=$("#count").val();
	if(count>0){
		var paras = $("#form_query").serialize();
		document.location.href="unpaid/payOrderAction_exportCheck?"+paras;
		$.jBox.tip("正在读取数据...", 'loading');
	}else{
		jBox.alert("暂无数据!",'info');
	}
};

function selectTime(days){
	var beginDate = getDayAdd((-1) * (days - 1));
	var endDate = getTodayDate();
	$('input[name=startdate]').val(beginDate);
	$('input[name=enddate]').val(endDate);
	selectByTime();
};

function selectMonth(months){
	var beginDate = getMonthAdd((-1) * (months - 1));
	var endDate = getCurrMonth();
	$('input[name=startmonth]').val(beginDate);
	$('input[name=endmonth]').val(endDate);
	selectByTime();
};