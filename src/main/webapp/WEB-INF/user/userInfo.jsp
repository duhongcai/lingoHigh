<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/9
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会员信息</title>
    <style type="text/css">
        body,button,input,select,textarea,code{
            font-size: 12px;
            font-family: "Microsoft YaHei";
        }
        h3 {
            display:block;
            font-size: 1.17em;
            font-weight: bold;
        }
        td{
            text-align: center;
            vertical-align: middle;
            height: 15px;;
        }
        ol,ul,menu{
            list-style: none;
        }
        div,form{
            display: block;
        }
        a{
            text-decoration: none;
            cursor: pointer;
        }
        .theme-page{
            z-index:9999;
            position: fixed;
            top:50%;
            left: 50%;
            width:660px;
            height: 360px;
            margin:-180px 0 0 -330px;
            border-radius:5px;
            border:solid 2px #666;
            background-color:#fff;
            box-shadow: 0 0 10px #666;
        }
        .dform{
            padding:80px 60px 40px;
        }
        .theme-head{
            border-bottom: 1px solid #ddd;
            padding:12px;
            position: relative;
        }
        .theme-head .close{
            float: right;
            color: #999;
            padding: 5px;
            margin: 4px 4px -2px -10px;
            text-shadow: 0 1px 0 #ddd;
        }
        .theme-body{
            color: #444;
            height: 148px;
        }
        .theme-content{
            margin: -50px -20px -50px 90px;
            text-align: left;
            font-size: 14px;
        }
        .theme-content .sendUpdate{
            margin-bottom: 10px;
            position: relative;
            display: inline-block;
            vertical-align: middle;
            font-size: 12px;
            font-weight: bold;
            line-height:27px;
            min-width: 52px;
            padding: 0 12px;
            text-align: center;
            text-decoration: none;
            border-radius: 2px;
            border:1px solid #ddd;
        }
        .theme-mask {
            z-index:9998;
            position: fixed;
            top:0;
            left:0;
            width:100%;
            height: 100%;
            background: #000;
            opacity: 0.4;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            console.log("lingoHigh");
            $(".theme-page").slideUp(0);
            $(".theme-mask").fadeOut();
            $("table").on("click",".updateUserInfo",function(){

                $(".uCode1").val($(this).parent().parent().find("td:eq(0)").text().trim());
                $(".uName1").val($(this).parent().parent().find("td:eq(1)").text().trim());
                $(".uPassword1").val($(this).parent().parent().find("td:eq(2)").text().trim());
                $(".uEmail1").val($(this).parent().parent().find("td:eq(3)").text().trim());
                $(".uBirthday1").val($(this).parent().parent().find("td:eq(4)").text().trim());

                $(".theme-mask").fadeIn(100);
                $(".theme-page").slideDown(200);
            });
        });
        $(".close").click(function () {

                $(".theme-page").fadeOut(100);
                $(".theme-mask").slideUp(200);
        });

    </script>
</head>
<body>
   <div>
       <h3>会员列表</h3>
       <hr>
       <table width="100%" border="1px">
           <tr>
               <td>编码</td>
               <td>名称</td>
               <td>密码</td>
               <td>邮箱</td>
               <td>生日</td>
               <td>操作</td>00
           </tr>
           <c:forEach items="${users}" var="user" varStatus="temp">
               <tr>
                   <td class="uCode">
                       <c:out value="${user.id}"/>
                   </td>
                   <td class="uName">
                       <c:out value="${user.name}"/>
                   </td>
                   <td class="uPWord">
                       <c:out value="${user.password}"/>
                   </td>
                   <td class="uEmial">
                       <c:out value="${user.email}"/>
                   </td>
                   <td class="uBirthday">
                       <c:out value="${user.birthday}"/>
                   </td>
                   <td>
                       <a href="#" class="updateUserInfo">修改</a> |
                       <a href="#">删除</a>
                   </td>
               </tr>
           </c:forEach>
       </table>
   </div>
    <!--模态框:3个div组合-->
    <div class="theme-page">
        <div class="theme-head">
            更新会员信息
            <a href="javascript:;" title="关闭" class="close">x</a>
        </div>
       <div class="theme-body dform">
           <form class="theme-content" action="user/updateUserInfo" method="post">
               <dl>
                   <dd>编码：<input type="text" value="" name="id" class="uCode1" readonly="readonly"/></dd><br/>
                   <dd>名称：<input type="text" value="" name="name" class="uName1" /></dd><br/>
                   <dd>密码：<input type="text" value="" name="password" class="uPassword1" /></dd><br/>
                   <dd>邮箱：<input type="text" value="" name="email" class="uEmail1" /></dd><br/>
                   <dd>生日：<input type="text" value="" name="birthday" class="uBirthday1" /></dd><br/>
                   <dd>
                       <input type="submit" class="sendUpdate" value="提交">
                   </dd>
               </dl>
           </form>
       </div>
    </div>
    <!--遮罩页：模态框激活后遮罩主页面 防止操作主页面-->
   <div class="theme-mask"></div>
    <!--测试-->
</body>
</html>
