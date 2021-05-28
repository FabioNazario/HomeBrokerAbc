<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script>
        <script src="https://code.highcharts.com/stock/highstock.js"></script>
        <script src="https://code.highcharts.com/stock/modules/data.js"></script>
        <script src="https://code.highcharts.com/stock/modules/drag-panes.js"></script>
        <script src="https://code.highcharts.com/stock/modules/exporting.js"></script>
        <script src="https://code.highcharts.com/stock/indicators/indicators.js"></script>
        <script src="https://code.highcharts.com/stock/indicators/pivot-points.js"></script>
        <script src="https://code.highcharts.com/stock/indicators/ema.js"></script>
        <script src="https://code.highcharts.com/stock/indicators/macd.js"></script>
        <link rel="stylesheet" href="css/style.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div id="container" style="height: 800px; min-width: 310px;"></div>
        
        <script>
            Highcharts.stockChart('container', {

                rangeSelector: {
                    selected: 2
                },

                yAxis: [{
                    height: '75%',
                    resize: {
                        enabled: true
                    },
                    labels: {
                        align: 'left',
                        format: '{value:.2f}',
                        reserveSpace: true,
                        y:3,
                        x:5
                    }
                }, {
                    //MACD
                    top: '75%',
                    height: '25%',
                    width:'100%',
                    labels: {
                        align: 'left',
                        format: '{value:.2f}',
                        reserveSpace: true,
                        y:3,
                        x:5
                    },
                    offset: 0,
                }],
                xAxis: {
                    tickInterval: 7 * 24 * 3600 * 1000,
                    tickWidth: 1,
                    gridLineWidth: 1,
                    labels: {
                        reserveSpace: true,
                        align: 'center'
                    }
                },
                title: {
                    text: 'MAGAZINE LUIZA S.A Stock Price', 
                    style: { "color": "#333333", "fontSize": "28px" }
                },
                subtitle: {
                    text: "${titleData}",
                    y:70
                },
                plotOptions: {
                    series: {
                      groupPadding: 0,
                      pointPadding: 0.15,
                      borderWidth: 0
                    },
                    macd:{
                        lineWidth:1,
                        macdLine:{
                            styles:{
                              lineColor:'#0295ff',
                              lineWidth:1
                            } 
                        },
                        marker:{
                            fillColor:'#26a69a'
                        }
                    }
                },

                series : [{
                        name : 'Close Price',
                        id:'aapl',
                        type : 'line',
                        color: '#000',
                        lineWidth:2,
                        data : ${candlesticks},
                    },{
                        name : 'EMA 9 Close',
                        type: 'spline',
                        color: '#673ab7',
                        lineWidth:1,
                        data : ${ema9},
                        step: true,
                    },{
                        name : 'EMA 12 Close',
                        type: 'spline',
                        color: '#ff6d00',
                        lineWidth:1,
                        data : ${ema12},
                        step: true,
                    },{
                        name : 'EMA 26 Close',
                        type: 'spline',
                        color: '#26c6da',
                        lineWidth:1,
                        data : ${ema26},
                        step: true,
                    }, {
                        type: 'macd',
                        color: '#ff6b01',
                        yAxis: 1,
                        linkedTo: 'aapl'
                }]
            });
    </script>
        
        
        <%--<script>
        https://jsfiddle.net/gh/get/library/pure/highcharts/highcharts/tree/master/samples/stock/demo/macd-pivot-points
            $(function () {
                $('#container').highcharts('StockChart', {
                    rangeSelector : {
                        inputEnabled: $('#container').width() > 480,
                        selected : 1
                    },
                    title : {
                        text : 'MAGAZINE LUIZA S.A. 1D BMFBOVESPA 44.59 -0.98 (-2.15%)'
                    },
                    plotOptions: {
                        series: {
                            compare: 'Close Price'
                        }
                    },
                    series : [{
                        name : 'Close Price',
                        type : 'line',
                        color: '#000',
                        lineWidth:2,
                        data : ${candlesticks},
                    },{
                        name : 'EMA 9 Close',
                        type: 'spline',
                        color: '#673ab7',
                        lineWidth:1,
                        data : ${ema9},
                        step: true,
                    },{
                        name : 'EMA 17 Close',
                        type: 'spline',
                        color: '#ff6d00',
                        lineWidth:1,
                        data : ${ema12},
                        step: true,
                    },{
                        name : 'EMA 26 Close',
                        type: 'spline',
                        color: '#26c6da',
                        lineWidth:1,
                        data : ${ema26},
                        step: true,
                    }]
                });
            });
        </script>--%>
        
    </body>
</html>
