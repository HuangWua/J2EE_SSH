<%--
  Created by IntelliJ IDEA.
  User: duan
  Date: 2019-06-03
  Time: 8:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="resource/css/bootstrap.css">
    <link rel="stylesheet" href="resource/css/toastr.css">
    <script src="resource/js/jquery-3.4.1.min.js"></script>
    <script src="resource/js/bootstrap.js"></script>
    <script src="resource/js/toastr.js"></script>
    <script src="resource/js/jy.js"></script>
    <title>在线购物</title>
</head>
<body>

<%--导航栏--%>
<div class="container">
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#example-navbar-collapse-2" aria-expanded="false">
                    <span class="sr-only">Togger navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/javaEE_ssh/jsp/user/login.jsp">手机商城</a>
            </div>

            <div class="navbar-collapse collapse" id="example-navbar-collapse-2">

                <form class="navbar-form navbar-left" action="">
                    <div class="form-group">
                        <input type="text" class="form-control" id="Search_goodsname" placeholder="Search">
                    </div>
                    <button type="button" class="btn btn-default" onclick="Search_goodsByGoodsName();">GO!</button>
                </form>
                <ul class="nav navbar-nav navbar-left">
                    <li><a href="#" onclick="loadByType('xiaomi')"><strong>小米</strong></a></li>
                    <%--<li><a href="#">|</a></li>--%>
                    <li><a href="#" onclick="loadByType('huawei')"><strong>华为</strong></a></li>
                    <%--<li><a href="#">|</a></li>--%>
                    <li><a href="#" onclick="loadByType('pingguo')"><strong>苹果</strong></a></li>
                    <%--<li><a href="#">|</a></li>--%>
                    <li><a href="#" onclick="loadByType('sanxing')"><strong>三星</strong></a></li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false"><strong>
                            <span class="glyphicon glyphicon-stats"></span>价格区间</strong>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#" onclick="priceInBetween(0,499);">0--499<span
                                    class="help-block">实惠区</span></a></li>
                            <li class="divider"></li>
                            <li><a href="#" onclick="priceInBetween(500,4999);">500--4999<span
                                    class="help-block">名牌</span></a></li>
                            <li class="divider"></li>
                            <li><a href="#" onclick="priceInBetween(5000,null)">5000以上<span
                                    class="help-block">奢侈品</span></a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"></a></li>
                    <li><a href="#" onclick="shopping_cart();"><span
                            class="glyphicon glyphicon-shopping-cart"></span><strong class="text-info">购物车</strong></a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false"><strong>欢迎</strong>
                            <span id="id" class="text-hide">${sessionScope.user.id}</span>
                            <span id="user_name"
                                  style="color: #f40;font-size: larger">${sessionScope.user.username}</span>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#" data-toggle="modal" data-target="#myMessageModel"
                                   onclick="mymessage();">我的信息</a></li>
                            <li><a href="#" onclick="myorder();">我的订单</a></li>
                            <c:if test="${sessionScope.user.role==2}">
                                <li><a href="/javaEE_ssh/user/toManager.action">后台管理</a></li>
                            </c:if>
                            <c:if test="${sessionScope.user.username==null}">
                                <li><a href="/javaEE_ssh/jsp/user/login.jsp">登录账号</a></li>
                            </c:if>
                            <li><a href="#" onclick="logOut()">退出登录</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>


<%--我的信息模态框--%>
<div class="modal fade" id="myMessageModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">账户编号:<span id="user_id"></span></h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <label for="username">用户名</label>
                        <input type="text" class="form-control" id="username" onchange="jy_username_format(this);">
                        <span class="help-block"></span>
                    </div>
                    <div class="col-md-6">
                        <label for="phone">电话</label>
                        <input type="text" class="form-control" id="phone">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label for="password">密码</label>
                        <input type="text" class="form-control" id="password" onchange="jy_password(this);">
                        <span class="help-block"></span>
                    </div>
                    <div class="col-md-6">
                        <label for="userRole">用户级别</label>
                        <select class="form-control" id="userRole">
                            <option value="0">普通用户</option>
                            <option value="1">vip用户</option>
                            <option value="2">管理员用户</option>
                        </select>
                        <span class="help-block"></span>

                    </div>

                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label for="gender">性别</label>
                        <select class="form-control" id="gender">
                            <option>男</option>
                            <option>女</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="money">余额</label>
                        <input type="text" class="form-control" id="money">
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="row">
                    <!-- 地址联动
                       <div class="col-md-6" style="margin:10px;">
                               <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr><td>
                              <select id="province" name="province" onchange="doProvAndCityRelation();">
              　　　　　　　　			<option id="choosePro" value="-1">请选择您所在省份</option>
              　　　　　　		</select>
                            </td></tr>
                            <tr><td><select id="citys" name="city" onchange="doCityAndCountyRelation();">
             　　　　　　　　		<option id='chooseCity' value='-1'>请选择您所在城市</option>
             　　　　　　		</select>
                            </td>
                            </tr>
                            <tr><td>
                             <select id="county" name="county">
             　　　　　　　　			<option id='chooseCounty' value='-1'>请选择您所在区/县</option>
            　　　　　　		</select>
                            </td></tr>
                            </table>

                       </div>
                       -->
                    <div class="col-md-12">
                        <label for="address">收货地址</label>
                        <input type="text" class="form-control" id="address">
                    </div>
                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="save_userMseeage();">Save
                    changes
                </button>
            </div>
        </div>
    </div>
</div>

<!-- ajax 异步加载页面 -->
<div id="ajaxload">
</div>


</body>

<script src="resource/js/goods_index.js" type="application/javascript"></script>

</html>
