<%--
  Created by IntelliJ IDEA.
  User: duan
  Date: 2019-06-02
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="../../resource/css/bootstrap.css">
    <link rel="stylesheet" href="../../resource/css/toastr.css">
    <script src="../../resource/js/jquery-3.4.1.min.js"></script>
    <script src="../../resource/js/bootstrap.js"></script>
    <script src="../../resource/js/toastr.js"></script>
    <script src="../../resource/js/jy.js"></script>
    <title>在线购物系统</title>
</head>
<body>

<%--导航栏--%>
<div class="container">
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Togger navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/javaEE_ssh">在线购物</a>
            </div>
            <div class="collapse navbar-collapse" id="example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#" data-toggle="modal" data-target="#register">
                            <span class="glyphicon glyphicon-plus"></span>注册
                        </a>
                    </li>
                    <li>
                        <a href="#" id="gologin" data-toggle="modal" data-target="#login">
                            <span class="glyphicon glyphicon-log-in"></span>登录
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>


<%--登录模态框--%>
<div class="modal fade" id="login" tabindex="-1" role="dialog"
     aria-labelledby="myModalLogin">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h3 class="modal-title" id="myModalLogin">登录</h3>
            </div>

            <form class="form-horizontal" action="../../jsp/goods/index.jsp" method="post" autocomplete="off"
                  id="loginform" onsubmit="return jy_login();">
                <div class="modal-body">

                    <div class="form-group">
                        <label for="inputUsername2" class="col-sm-2 control-label">Username</label>
                        <div class="col-sm-10">
                            <input type="text" name="username" class="form-control" id="inputUsername2"
                                   placeholder="Username">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword2" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" name="password" class="form-control" id="inputPassword2"
                                   placeholder="Password">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputVerifyCode" class="col-sm-2 control-label small">VerifyCode</label>
                        <div class="col-sm-4" id="doVerify">
                            <input type="text" name="verifyCode" class="form-control input-sm"
                                   id="inputVerifyCode" placeholder="请输入验证码">
                            <span class="help-block small">看不清？点图换一张...</span>
                        </div>
                        <img width="100px" height="45px" onclick="this.src=this.src+'?'+Math.random()"
                             src="${pageContext.request.contextPath}/user/loginVerifyCode.action">
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <input type="submit" class="btn btn-primary" value="确定" id="do_login">
                </div>
            </form>

        </div>
    </div>
</div>


<%--注册模态框--%>
<div class="modal fade" id="register" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h3 class="modal-title" id="myModalLabel">注册</h3>
            </div>

            <form class="form-horizontal" action="" method="post" autocomplete="off" id="register_from">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="inputUsername" class="col-sm-2 control-label">Username</label>
                        <div class="col-sm-10">
                            <input type="text" name="user.username" class="form-control" id="inputUsername"
                                   onchange="jy_username(this)" placeholder="Username">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" name="user.password" class="form-control" id="inputPassword"
                                   onchange="jy_password(this)" placeholder="Password">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPhone" class="col-sm-2 control-label">Phone</label>
                        <div class="col-sm-10">
                            <input type="text" name="user.defaultPhone" class="form-control" id="inputPhone"
                                   onchange="jy_phone(this)" placeholder="Phone">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <input type="button" class="btn btn-primary" onclick="jy_register()" value="确定" id="do_register">
                </div>

            </form>
        </div>
    </div>
</div>


<%--注册结果框--%>
<div class="modal fade" id="register_result" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div role="tabpanel" class="tab-pane" id="profile2">
                <div class="modal-body text-center">
                    <p></p>
                    <h2><span class="text-success">注册成功</span></h2>
                    <span class="help-block">即将前往登录.....</span>
                    <p></p>
                </div>
            </div>
        </div>
    </div>
</div>


<%--登录结果框--%>
<div class="modal fade" id="login_result" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div role="tabpanel" class="tab-pane" id="profile3">
                <div class="modal-body text-center">
                    <p></p>
                    <h2><span class="text-success">登录成功</span></h2>
                    <span class="help-block">即将前往商城.....</span>
                    <p></p>
                </div>
            </div>
        </div>
    </div>
</div>


<%--信息模态框--%>
<div class="modal fade" id="more_message" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div role="tabpanel" class="tab-pane" id="profile">
                <div class="modal-body">
                    <p>开发者：DUNJH</p>
                    <p>当前版本：v1.0.0</p>
                    <p>创建时间：2019年六月</p>
                </div>
            </div>
        </div>
    </div>
</div>


<%--巨幕--%>
<div class="container">
    <div class="jumbotron">
        <div class="container text-center">
            <h1>欢迎使用手机商城.</h1>
            <p>制作人：段君恒</p>
            <p><a href="#" class="btn btn-primary btn-lg" data-toggle="modal"
                  data-target="#more_message" role="button">了解更多！！！</a></p>
            <p><a href="#" class="btn btn-default" data-toggle="modal" data-target="#login" role="button">
                <span class="glyphicon glyphicon-log-in"></span>&nbsp;登录&nbsp;</a>
                <a href="#" class="btn btn-success" data-toggle="modal" data-target="#register" role="button"><span
                        class="glyphicon glyphicon-log-in"></span>&nbsp;注册&nbsp;</a>
            </p>
        </div>
    </div>
</div>


<!-- 当点击注册，登录按钮是，清除模态框的数据 -->
<script src="../../resource/js/login.js" type="application/javascript"></script>
</body>

<script type="application/javascript">
</script>

</html>
