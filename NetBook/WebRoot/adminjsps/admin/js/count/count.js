/*
*线型 单一
*/
function line(title,myArray,legend,divId){
	var lineChart = new JSChart(divId, 'Line');
	lineChart.setDataArray(myArray,legend);	//赋值
	
	lineChart.setLineColor('#FF0000', legend); //注：第一条线的颜色
	lineChart.setLineOpacity(0.9, legend);	//设置曲线图中曲线的透明度，取值0～1之间，默认0.9。参数id的意义同上。
	
	lineChart.setSize(1100, 250);		//注：宽度500,、高度：300
	lineChart.setTitle(title);//图表标题
	lineChart.setTitleFontSize( 15 );//图表标题大小
	
	lineChart.setLegendShow(true);	//显示图例
	lineChart.setLegendPosition('right top');		//图例显示位置(可以是相对位置或坐标)
	
	lineChart.setAxisNameX("");
	lineChart.setAxisNameY("");
	
	lineChart.setGridOpacityX(0.8);
	lineChart.setGridOpacityY(0.1);
	
	for(var i = 0;i < myArray.length;i++){
		lineChart.setTooltip([myArray[i][0],myArray[i][1],legend]);
	}
	lineChart.draw();
}
/*
*线型 对比
*/
function lineContrast(title,myArray,myArray1,legend,legend1,divId){
	var lineChart = new JSChart(divId, 'Line');
	lineChart.setDataArray(myArray,legend);	//赋值
	lineChart.setDataArray(myArray1,legend1);	//赋值
	
	lineChart.setLineColor('#FF0000', legend); //注：第一条线的颜色
	lineChart.setLineColor('#00FF00', legend1); //注：第二条线的颜色
	
	lineChart.setSize(1100, 250);	//注：宽度500,、高度：300
	lineChart.setTitle(title);	//图表标题
	lineChart.setTitleFontSize(15);//图表标题大小
	
	lineChart.setLegendShow(true);	//显示图例
	lineChart.setLegendPosition('right top');		//图例显示位置(可以是相对位置或坐标)
	
	lineChart.setAxisNameX("");
	lineChart.setAxisNameY("");
	
	lineChart.setGridOpacityX(0.8);
	lineChart.setGridOpacityY(0.1);
	
	for(var i = 0;i < myArray.length;i++){
		lineChart.setTooltip([myArray[i][0],myArray[i][1],legend],callback);
		lineChart.setTooltip([myArray1[i][0],myArray1[i][1],legend1],callback);
	}
	
	lineChart.draw();
}

/*
*柱形 单一
*/
function bar(title,myArray,legend,divId){
	var barChart = new JSChart(divId, 'bar');
	barChart.setDataArray(myArray);	//赋值
	
	barChart.resize(1100, 250);         //注：图表宽度、高度
	barChart.setTitle(title);//图表标题
	barChart.setTitleFontSize( 15 );//图表标题大小
	
	barChart.setLegendShow(true);	//显示图例
	barChart.setLegendPosition('right top');	//图例显示位置
	barChart.setLegendForBar(1, legend);	//绑定柱形并设置名称
	
	barChart.setAxisNameX("");
	barChart.setAxisNameY("");
	
	barChart.setBarSpacingRatio(30);	//注：两个柱子之间的距离（1-100）
	barChart.setBarValuesDecimals(0);	//注：柱子上值的小数数量。
	barChart.setBarValuesFontSize(10);	// 注：柱子上值的字体大小。
	
	barChart.draw();
}

/*
*柱形 对比
*/
function barContrast(title,myArray,legend,legend1,divId){
	var barChart = new JSChart(divId, 'bar');
	barChart.setDataArray(myArray);	//赋值
	
	barChart.resize(1100, 250);         //注：图表宽度、高度
	barChart.setTitle(title);//图表标题
	barChart.setTitleFontSize( 15 );//图表标题大小
	
	barChart.setLegendShow(true);	//显示图例
	barChart.setLegendPosition('right top');	//图例显示位置
	barChart.setLegendForBar(1, legend);	//绑定柱形并设置名称
	barChart.setLegendForBar(2, legend1);	
	
	barChart.setAxisNameX("");
	barChart.setAxisNameY("");
	
	barChart.setBarSpacingRatio(30);	//注：两个柱子之间的距离（1-100）
	barChart.setBarValuesDecimals(0);	//注：柱子上值的小数数量。
	barChart.setBarValuesFontSize(10);	// 注：柱子上值的字体大小。

	barChart.draw();
}

/*
*饼形 单一
*/
function pie(title,myArray,divId){
	var colArray = new Array('#d71345','#ffd400','#45b97c','#145b7d');
	var pieChart = new JSChart(divId, 'pie');
	//pieChart.setDataArray(myArray);	//赋值
	pieChart.setDataJSON(myArray);
	pieChart.resize(400, 250);         //注：图表宽度、高度
	pieChart.setTitle(title);//图表标题
	pieChart.setTitleFontSize(15);//图表标题大小
	
	pieChart.colorize(colArray);
	for(var i = 0 ; i<myArray.length;i++){
		pieChart.setLegend(colArray[i], myArray[i][0]);
	}
	
	pieChart.setLegendShow(true);	//显示图例
	pieChart.setLegendPosition('right middle');
	
	pieChart.set3D(true);
	pieChart.draw();
}
/*
*饼形 对比
*/
function pieContrast(title,myArray,myArray1,divId,divId1){
	var colArray = new Array('#d71345','#ffd400','#45b97c','#145b7d');
	var pieChart = new JSChart(divId, 'pie');
	pieChart.setDataArray(myArray);	//赋值
	pieChart.resize(500, 250);         //注：图表宽度、高度
	pieChart.setTitle(title);//图表标题
	pieChart.setTitleFontSize(15);//图表标题大小
	pieChart.colorize(colArray);
	for(var i = 0 ; i<myArray.length;i++){
		pieChart.setLegend(colArray[i], myArray[i][0]);
	}
	pieChart.set3D(true);
	pieChart.draw();

	var pieChart1 = new JSChart(divId1, 'pie');
	pieChart1.setDataArray(myArray1);	//赋值
	pieChart1.resize(500, 250);         //注：图表宽度、高度
	pieChart1.setTitle(title);//图表标题
	pieChart1.setTitleFontSize(15);//图表标题大小
	pieChart1.colorize(colArray);
	for(var i = 0 ; i<myArray.length;i++){
		pieChart1.setLegend(colArray[i], myArray1[i][0]);
	}
	pieChart1.setLegendShow(true);	//显示图例
	pieChart1.setLegendPosition('right middle');
	pieChart1.set3D(true);
	pieChart1.draw();
}

/**
 * 用来请求数据
 */
function ask(year,graph){
	if(year==null&&year.leagth==0&&year==""){
		year=new Date().getFullYear();
	}
	var array;
	$.ajax({
		type:"post",
		url:"count/CountServlet?action=count&year="+year,
		dateType:"text/html",
		success:function(data,state,xhr){
			if(xhr.readyState==4&&xhr.status==200){
				var jsonstr = eval("("+data+")");
				array=new Array();  
			      for(var i=0;i<jsonstr.length;i++){  
			       var arr=new Array();  
			       arr[0]=jsonstr[i].bname.substr(0,20);  
			       arr[1]=jsonstr[i].countNum;  
			       array[i]=arr;  
			      }   
			      var myChart = new JSChart('canvasDiv', graph); 
			      myChart.setTitle(year+"年销售量前10的书籍");
			      myChart.setDataArray(array);
			      myChart.setSize(1300, 400); //设置背景大小
			      myChart.draw();
			}	
		}
	});
}


