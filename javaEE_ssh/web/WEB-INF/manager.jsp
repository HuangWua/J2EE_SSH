<%--
  Created by IntelliJ IDEA.
  User: duan
  Date: 2019-06-04
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="../resource/css/bootstrap.css">
    <link rel="stylesheet" href="../resource/css/toastr.css">
    <link rel="stylesheet" href="../resource/css/styles.css">
    <script src="../resource/js/jquery-3.4.1.min.js"></script>
    <script src="../resource/js/bootstrap.js"></script>
    <script src="../resource/js/toastr.js"></script>
    <script src="../resource/js/jy.js"></script>
    <!-- Router JavaScript -->
    <script type="text/javascript" src="../resource/public/director/director.min.js"></script>
    <!-- Custom JavaScript -->
    <script type="text/javascript" src="../resource/public/plugins.js"></script>
    <script type="text/javascript" src="../resource/page/home/home.js"></script>
    <script type="text/javascript" src="../resource/page/basicinfo/user/user.js"></script>
    <script type="text/javascript" src="../resource/public/app.js"></script>
    <title>后台管理</title>
</head>
<body>
<!-- 导航栏 -->
<div class="navbar navbar-default navbar-fixed-tops">
    <div class="navbar-header">
        <a class="navbar-brand" href="javascript:sidebarNoneToggle()">管理后台</a>

        <ol id="navbar-router" class="navbar-text">
            <li>HOME</li>
        </ol>

        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-tool"
                aria-expanded="true" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
    </div>

    <div id="navbar-tool" class="navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-right">
            <li><a href="../index.jsp"><span class="glyphicon glyphicon-shopping-cart"></span><strong>商城</strong></a>
            </li>
            <li><a href="#" onclick="logOut();">退出登陆</a></li>
        </ul>
    </div>
</div>


<!-- 左侧工具条 开始 -->
<div id="sidebar" class="sidebar sidebar-default sidebar-fixed-left">
    <div class="sidebar-toggle">
        <button onClick="sidebarSimpleToggle()" class="btn btn-xs btn-success"><span
                class="glyphicon glyphicon-chevron-left"></span></button>
    </div>

    <div id="sidebar-accordion" class="sidebar-accordion">
        <ul class="nav sidebar-nav">

            <li class="active">
                <a href="#/home">
                    <i class="glyphicon glyphicon-home"></i><span>HOME</span>
                </a>
            </li>

            <li>
                <a href="#nav-basicinfo" data-toggle="collapse" aria-expanded="false" aria-controls="nav-basicinfo">
                    <i class="glyphicon glyphicon-cog"></i><span>基础数据</span>
                </a>
                <ul id="nav-basicinfo" class="nav collapse">
                    <li><a href="#/basicinfo/user" onclick="usermanager()"><span>用户管理</span></a></li>
                    <li><a href="#/basicinfo/order" onclick="ordersmanager()"><span>订单管理</span></a></li>
                </ul>
            </li>

            <li>
                <a href="#nav-shopinfo" data-toggle="collapse" aria-expanded="false" aria-controls="nav-basicinfo">
                    <i class="glyphicon glyphicon-asterisk"></i><span>商家信息</span>
                </a>
                <ul id="nav-shopinfo" class="nav collapse">
                    <%--<li><a href="#"><span>商家管理</span></a></li>--%>
                    <li><a href="#" onclick="goodsmanager()"><span>商品管理</span></a></li>
                </ul>
            </li>

        </ul>
    </div>


</div>
<!-- 左侧工具条 结束 -->


<!-- 主要内容页 开始 -->
<div id="page" class="page"></div>
<!-- 主要内容页 结束 -->
<script src="../resource/js/manager.js" type="application/javascript"></script>

</body>
</html>
