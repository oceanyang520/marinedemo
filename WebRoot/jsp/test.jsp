<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>jQuery 瀑布流插件</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div id="top"><p>jQuery 瀑布流插件 by <a target="_blank" href="http://leolai.cnblogs.com/">无赖君子</a></p></div>
<div id="waterfall"></div>
<div id="footer">
    <p>jQuery 瀑布流插件 by <a target="_blank" href="http://leolai.cnblogs.com/">无赖君子</a></p>
</div>
<script type="text/javascript" src="../jquery.waterfall/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../jquery.waterfall/js/jquery.waterfall.js"></script>
<script type="text/javascript">
$(function(){
    // 一次性全部加载到本地，再部分显示
    // $('#waterfall').waterfall({
    //  url: 'http://127.0.0.1/demo/waterfall/js/json.js',
    //  perNum: 5,          // 每次显示五个
    //  ajaxTimes: 1        // 只发送一次请求
    // });
    
    
    // 按需加载方式
    var wf_page = 0;
    $('#waterfall').waterfall({
        // 自定义跨域请求
        ajaxFunc: function(success, error){
            $.ajax({
                type: 'GET',
                url: 'http://www.wookmark.com/api/json/popular?callback=?',
                cache: false,
                data: {'page': ++wf_page},
                dataType:'jsonp',
                timeout: 60000,
                success: success,
                error: error
            });
        },
        createHtml: function(data){
            return '<div class="wf_item_inner">' +
                      '<a href="'+ data.url +'" class="thumb" target="_blank">' +
                        '<img class="thumb_img"  src="'+ data.preview +'" />' +
                      '</a>' +
                      '<p class="desc" style="margin-top:1px;">'+ data.title +'</p>' +
                      '<a style="display:block;color:#060;" href="'+data.image+'" target="_blank">查看大图</a>' +
                  '</div>';
        }
    });
});
</script>
</body>
</html>
