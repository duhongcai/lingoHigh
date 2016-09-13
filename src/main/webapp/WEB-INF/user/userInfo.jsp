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
    <script type="javascript" src="/assets/plugins/jquery-1.10.2.min.js"/>
    <style type="text/css">
        td{
            text-align: center;
            vertical-align: middle;
            height: 15px;;
        }
    </style>
</head>
<body>
   <div>
       <h3>会员列表</h3>
       <hr>
       <table width="100%" border="1px" align=center valign=middle>
           <tr>
               <td>会员编码</td>
               <td>名称</td>
               <td>密码</td>
               <td>邮箱</td>
               <td>生日</td>
               <td>操作</td>
           </tr>
           <c:forEach items="${users}" var="user" varStatus="temp">
               <tr >
                   <td>
                       <c:out value="${user.id}"/>
                   </td>
                   <td>
                       <c:out value="${user.name}"/>
                   </td>
                   <td>
                       <c:out value="${user.password}"/>
                   </td>
                   <td>
                       <c:out value="${user.email}"/>
                   </td>
                   <td>
                       <c:out value="${user.birthday}"/>
                   </td>
                   <td>
                       <a href="#">修改</a> |
                       <a href="#">删除</a>
                   </td>
               </tr>
           </c:forEach>
       </table>
   </div>
    <!--模态框-->
    <div></div>
    <!--遮罩页-->
</body>
</html>
