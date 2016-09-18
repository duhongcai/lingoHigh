<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/14
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试页面</title>
    <script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
    <style type="text/css">
        .add{
            float: left;
            height: 50px;
            width: 50px;
            margin-left: 5px;
        }
    </style>
    <script type="javascript">
        $(document).ready(function(){
            $("body").on("click",".add",function(){
                alert("body on click");
            });
        });

        var backcolor = "yellow";
        function bind(){
            $(".add").on("click",function(){
                alert("add clicked");
            });
            backcolor = "gray";
        }

        function addNewDiv(){
            $("<div class='add' style='background:"+ backcolor + ";'>added Div<div>").appendTo($("#newZone"));
        }
    </script>
</head>

<body>
    <form id="form1"></form>
    <div>
        <input type="button" value="addNewSpan" onclick="addNewDiv()" />
        <input type="button" value="bindAdded" onclick="bind()" />
        <div id="newZone">
        </div>
    </div>
</body>
</html>
