<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/10/23 0023
  Time: 下午 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf8">
    <title>Google-style intelligent search</title>
    <link rel="stylesheet" href="/style/bootstrap.css">
    <link rel="stylesheet" href="/style/style.css">
    <link rel="stylesheet" type="text/css" href="/js/autocomplete/jquery.autocomplete.css">

    <script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="/js/autocomplete/jquery.autocomplete.min.js"></script>
</head>
<body>
<div class="main-content">
    <div class="search-logo"></div>
    <form class="form-inline">
        <div class="form-group">
            <input id="search-input" class="form-control" placeholder="Input something." style="width: 570px">
        </div>
        <%--<button class="btn btn-primary">--%>
            <%--<span class="search-button-text">Search</span>--%>
        <%--</button>--%>
</div>
</div>

<script type="text/javascript">
    $(function () {
        var data = "Core Selectors Attributes Traversing Manipulation CSS Events Effects Ajax Utilities".split(" ");

        $("#search-input").autocomplete("/getName", {
            max: 100,    //列表里的条目数
            minChars: 3,    //自动完成激活之前填入的最小字符
            width: "570px",     //提示的宽度，溢出隐藏
            scrollHeight: 300,   //提示的高度，溢出显示滚动条
            matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
            autoFill: false,    //自动填充
            cache: false,
            cacheLength: 0,
            flushCache: function () {
                return this.trigger("flushCache");
            },
            //extraParams:{v:function() { return $('#keyword').val();}},
            parse: function (data) {
                var rows = [];
                for (var i = 0; i < data.length; i++) {
                    rows[rows.length] = {
//                        data: data[i].name + "-" + data[i].to,
                        data: data[i].name,
                        value: data[i].name,
                        result: data[i].name
                    };
                }
                return rows;
            },
            formatItem: function (row, i, max) {
                return "<span style='line-height: 30px; '>" + i + '/' + max + ':' + row + "</span>";
            }//,
            /*formatMatch: function(row, i, max) {
             return row.name + row.to;
             },
             formatResult: function(row) {
             return row.to;
             }*/
        })
                .result(function (e, data, formatted) {
//                    alert(data);
                });
    });
</script>
</body>
</html>