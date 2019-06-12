//注册数据校验
function jy_register() {

    if (!jy_username_format("#inputUsername")) {

        return false;

    } else if (!jy_password("#inputPassword")) {

        return false;

    } else if (!jy_phone("#inputPhone")) {

        return false;

    } else {

        $.post('/javaEE_ssh/user/register.action',
            {
                'user.username': $("#inputUsername").val(),
                'user.password': $("#inputPassword").val(),
                'user.defaultPhone': $("#inputPhone").val()
            },
            function (result) {
                if (result == 1) {
                    $("#register").modal("hide");
                    setTimeout(function () {
                        $("#register_result").modal('show');
                        setTimeout(function () {
                            $("#register_result").modal('hide');
                            setTimeout(function () {
                                $("#login").modal("show");
                            }, 1000);
                        }, 1500);
                    }, 1000);
                }
            });

        return false;

    }

}

//登入数据校验
function jy_login() {

    //用户名校验
    if (!jy_username_format("#inputUsername2")) {
        jy("#inputUsername2", "error", "用户名格式不正确，必须是字母或数字6-16位或中文2-5位！");
        return false;
    } else {
        jy("#inputUsername2", "success", "");
        //密码
        if (!jy_password("#inputPassword2")) {
            $("#inputPassword2").val("");
            toastr.error("用户名或密码不正确", 'error');
            return false;

        } else {

            jy("#inputPassword2", "success", "");
            var password = $("#inputPassword2").val();

            $("#inputPassword2").val("")

            $.post('/javaEE_ssh/user/login.action',
                {
                    'user.username': $('#inputUsername2').val(),
                    'user.password': password,
                    'verifyCode': $("#inputVerifyCode").val()
                },
                function (data) {
                    if (data == 0) {
                        toastr.error("用户名或密码不正确", 'error');
                        return false;
                    } else if (data == 2) {
                        toastr.error("验证码错误", 'error');
                        return false;
                    } else {
                        setTimeout(function () {
                            $("#login").modal('hide');
                            setTimeout(function () {
                                $("#login_result").modal('show');
                                setTimeout(function () {
                                    $("#login_result").modal('hide');
                                    setTimeout(function () {
                                        $(location).attr("href", '/javaEE_ssh/')
                                    }, 500)
                                }, 1000);
                            }, 500);
                        }, 500);
                    }
                });
            return false;

        }

    }

}

