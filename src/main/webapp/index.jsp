<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path=request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>"/>
        <title>LingoHigh电子交易平台</title>
        <style type="text/css">
            *{
                font-size: 14px;
                font-family: "Microsoft YaHei";
            }
        </style>

        <link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->

        <!-- BEGIN THEME STYLES -->
        <link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/style.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/pages/tasks.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
        <!-- END THEME STYLES -->

        <link rel="shortcut icon" href="app/img/favicon.ico" />
        <script type="text/javascript" src="assets/plugins/jquery-1.10.2.min.js"></script>
        <link rel="stylesheet" href="assets/plugins/bootstrap-select/css/bootstrap-select.min.css">
        <script type="text/javascript" src="assets/plugins/bootstrap-select/js/bootstrap-select.min.js"></script>
    </head>

    <body class="page-header-fixed page-sidebar-fixed">
        <div class="header navbar navbar-inverse navbar-fixed-top">
            <div class="header-inner">
                <a class="navbar-brand" href="javascript:;">
                    <img src="assets/img/logo.png" alt="logo" class="img-responsive">
                </a>
                <a href="javascript:;" class="navbar-toggle" data-toggle="collage" data-target=".navbar-collapse">
                    <img src="assets/img/menu-toggler.png" alt=" "/>
                </a>
                <ul class="nav navbar-nav pull-right">
                    <li class="dropdown user">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                            <img alt="" src="assets/img/avatar1_small.jpg"/>
                            <span class="username"> ${userInfo.username } </span>
                            <i class="fa fa-angle-down"></i>全屏
                        </a>
                    </li>
                    <li>
                        <a href="extra_lock.html">
                            <i class="fa fa-lock"></i>锁屏
                        </a>
                    </li>
                    <li>
                        <a href="#logoutConfirm" date-toggle="modal">
                            <i class="fa fa-key"></i>退出
                        </a>
                    </li>
                </ul>
                <div class="modal fade" id="logoutConfirm" tagindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 class="modal-title">提示</h4>
                        </div>
                        <div class="modal-body">确定要退出吗？</div>
                        <div class="modal-footer">
                            <a href="logout" role="button" class="btn blue">确认</a>
                            <button type="button" class="btn default" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <div class="clearfix"></div>
    <div class="page-container">
        <div class="page-sidebar-wrapper">
          <div class="page-sidebar navbar-collapse collapse">
              <ul class="page-sidebar-menu" id="page-sidebar-menu">
                  <li class="sidebar-toggler-wrapper">
                      <div class="sidebar-toggler hidden-phone"></div>
                  </li>

                  <li class="start active">
                      <a href="/" id="btn-dashboard">
                          <i class="fa fa-home"></i><span class="title">会员管理</span>
                      </a>
                      <ul class="sub-menu">
                          <li>
                              <a href="user/control" target="main-content">增</a>
                          </li>
                          <li>
                              <a href="/" target="main-content">删</a>
                          </li>
                          <li>
                              <a href="/" target="main-content">改</a>
                          </li>
                          <li>
                              <a href="/" target="main-content">查</a>
                          </li>
                      </ul>
                  </li>

                  <li class="">
                      <a href="/" >
                          <i class="fa fa-home"></i><span class="title">商品管理</span>
                      </a>
                  </li>

                  <li class="">
                      <a href="/" >
                          <i class="fa fa-home"></i><span class="title">订单管理</span>
                      </a>
                  </li>
              </ul>
          </div>
        </div>

        <div class="page-content-wrapper">
            <div class="page-content">
                <div class="row">
                    <div class="col-md-12">
                        <h3 class="page-title" id="index-page-title">LingoHigh电子商务交易平台</h3>
                        <hr/>
                    </div>
                </div>
                <div id="main-content"></div>
            </div>
        </div>
    </div>
        <script src="assets/plugins/respond.min.js"></script>
        <script src="assets/plugins/excanvas.min.js"></script>
        <script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
        <script src="assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
        <script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
        <script src="assets/plugins/bootstrap-table/js/bootstrap-table.js"></script>
        <script src="assets/plugins/bootstrap-table/js/bootstrap-table-zh-CN.min.js"></script>
        <script src="assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
        <script src="assets/scripts/app.js" type="text/javascript"></script>
        <script type="text/javascript" src="app/js/index.js"></script>
    </body>
</html>



























