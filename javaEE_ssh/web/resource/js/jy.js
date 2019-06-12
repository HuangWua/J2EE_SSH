toastr.options = {
    positionClass: "toast-top-center",
    showDuration: "300",
    timeOut: "2000",
    showEasing: "swing",
    hideEasing: "linear"
};

//校验用户输入的数据是否符合格式
function jy(ele, status, msg) {
    //先清除之前的校验信息与校验提示信息

    $(ele).parent().removeClass("has-error has-success");
    $(ele).next("span").text("");
    if (status == "success") {
        $(ele).parent().addClass("has-success");
        $(ele).next("span").text(msg);
    } else if (status == "error") {
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    }
    return false;
}

//用户名各种是否正确、异步查看用户名是否可用
function jy_username(obj) {

    //用户名校验

    if (!jy_username_format(obj)) {
        jy(obj, "error", "用户名格式不正确，必须是字母或数字6-16位或中文2-5位！");
    } else {

        jy(obj, "success", "");

        $.post("/javaEE_ssh/user/selectUserByName.action",
            {
                registerName: $(obj).val()
            },
            function (result) {
                $(obj).parent().removeClass("has-error has-success");
                $(obj).next("span").text("");
                if (result != null && result != "null" && result != "") {
                    jy(obj, "error", "用户名不可用");
                } else {
                    jy(obj, "success", "");
                }
            });
    }

}

//校验用户名格式
function jy_username_format(obj) {
    //用户名校验
    var username = $(obj).val();
    var regName = /(^[a-zA-Z0-9_-]{5,16}$)|(^[\u2E80-\u9FFF]{2,5})/;    //用户名5-16位或中文2-5位

    if (!regName.test(username)) {

        jy(obj, "error", "用户名格式不正确，必须是字母或数字6-16位或中文2-5位！");
        return false;

    } else {
        jy(obj, "success", "");
        return true;
    }
}

//校验密码
function jy_password(obj) {

    //密码校验
    var password = $(obj).val();
    var regPassword = /^[a-z0-9_-]{6,18}$/;

    if (!regPassword.test(password)) {

        jy(obj, "error", "密码是6-18位");
        return false;

    } else {

        jy(obj, "success", "");
        return true;

    }

}

//电话检验
function jy_phone(obj) {

    //电话校验
    var phone = $(obj).val();
    var regPhone = /^[1][3,5,8][0-9]{9}$/;

    if (!regPhone.test(phone)) {

        jy(obj, "error", "电话是13/15/18+9位数字");
        return false;

    } else {

        jy(obj, "success", "");
        return true;

    }
}
