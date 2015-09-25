
<html>
<head>
<meta charset="gbk">
<title>jQuery �ٲ������</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div id="top"><p>jQuery �ٲ������ by <a target="_blank" href="http://leolai.cnblogs.com/">��������</a></p></div>
<div id="waterfall"></div>
<div id="footer">
    <p>jQuery �ٲ������ by <a target="_blank" href="http://leolai.cnblogs.com/">��������</a></p>
</div>
<script type="text/javascript" src="../jquery.waterfall/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../jquery.waterfall/js/jquery.waterfall.js"></script>
<script type="text/javascript">
$(function(){
    // һ����ȫ�����ص����أ��ٲ�����ʾ
    // $('#waterfall').waterfall({
    //  url: 'http://127.0.0.1/demo/waterfall/js/json.js',
    //  perNum: 5,          // ÿ����ʾ���
    //  ajaxTimes: 1        // ֻ����һ������
    // });
    
    
    // ������ط�ʽ
    var wf_page = 0;
    $('#waterfall').waterfall({
        // �Զ����������
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
                      '<a style="display:block;color:#060;" href="'+data.image+'" target="_blank">�鿴��ͼ</a>' +
                  '</div>';
        }
    });
});
</script>
</body>
</html>
