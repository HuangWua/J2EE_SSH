toastr.options = {
    positionClass: "toast-top-center",
    showDuration: "300",
    timeOut: "2000",
    showEasing: "swing",
    hideEasing: "linear"
};

//加载商品卡片
function loadSP(data) {
    $("#goods").append("<div class='col-md-3' name='goods_card'><div class='thumbnail'><img style='height: 187.5px' src='/image/" + data.img + "' alt='html书籍'>" +
        "<div class='caption'><p><span class='text-hide' id='sp_id'>" + data.id + "</span><a onclick='modelmessage(this)' data-toggle='modal' data-target='#message' href='#' id='sp_name' style='font-size:18px'>" + data.name
        + "</a></p><p><a href='#'style='font-size:15px;' onclick='modelmessage(this)' data-toggle='modal' data-target='#message'>详情信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);'style='font-size:14px;' onclick='add_shopping_cart(this);'>加入购物车</a></p><p style='color:#f40;font-size: 13px'> 价格:<span id='sp_marketprice'>"
        + data.price + "</span>，类型:<span id='sp_shopptype'>" + data.type + "</span></p></div></div></div>");
}

//页面加载完成就执行的函数,所有商品的展示。
$(function () {
    $.get("/javaEE_ssh/goods/selectAllGoods.action",
        function (data) {
            $("#ajaxload").load('/javaEE_ssh/jsp/goods/goods.jsp',
                function () {
                    <!--把数据库的信息填充到页面中-->
                    var size = data.goodsList.length;

                    for (var i = 0; i < data.goodsList.length; i++) {

                        loadSP(data.goodsList[i]);
                    }

                });
        });

});

//当点击商品卡片的详情信息，弹出，商品信息模态框
function modelmessage(obj) {

    //当点击详情信息时，获取点击的商品卡片的name
    var good_id = $(obj).parents().parents().children("p").eq(0).children('span').text();        //$(obj) 指向当前标签   ，  eq(0) 第一个子标签

    $.get('/javaEE_ssh/goods/selectGoodsById.action',
        {'goods.id': good_id},
        function (data) {

            $('#goods_name').text(data.goods.name);						//点击商品卡片的详情信息把传来的数据填写到详情信息表格中
            $('#goods_id').text(data.goods.id);
            $('#goods_type').text(data.goods.type);
            $('#goods_count').text(data.goods.count);
            $('#goods_price').text(data.goods.price);
            $('#createtime').text(data.goods.createtime);
            $('#g_img').attr('src', "/image/" + data.goods.img);

        });

}

//点击商品卡片，加入购物车按钮，触发的函数
function add_shopping_cart(obj) {

    var user_id = $("#id").html();        //获取登陆人的用户编号

    var goods_id = $(obj).parents().parents().children("p").eq(0).children('span').text();

    var sc_count = 1;

    $.post('/javaEE_ssh/shoppingCart/insertShoppingcart.action',
        {
            'sc.goodsCount': sc_count,
            'sc.user.id': user_id,
            'sc.goods.id': goods_id
        },
        function (data) {
            if (data == 1) {
                toastr.success("加入购物车成功！！！", "succsee");
            } else if (data == 101) {
                toastr.warning("请先登入!!!");
                setTimeout(function () {
                    $(location).attr('href', '/javaEE_ssh/jsp/user/login.jsp');
                }, 2000);
            } else {
                toastr.error("添加失败！", "error");
            }
        });

}

//点击index.jsp的导航栏的购物车  加载购物车页面，只加载属于该用户的购物车
function shopping_cart() {

    var user_id = $("#id").html();        //获取登陆人的用户编号

    $.get("/javaEE_ssh/user/selectSc.action",
        {'user.id': user_id},
        function (data) {
            if (data == 101) {
                toastr.warning("请先登入!!!");
                setTimeout(function () {
                    $(location).attr('href', '/javaEE_ssh/jsp/user/login.jsp');
                }, 1500);
            } else {
                $("#ajaxload").load("/javaEE_ssh/jsp/shoppingcart/shoppingCart.jsp", function () {
                    var sum_price = 0;
                    for (var i = 0; i < data.length; i++) {
                        $("#shoppingcart_table").append(
                            "<tr><td><span name='sc_id' class='text-hide'>" + data[i].id
                            + "</span><span name='goods_id' class='text-hide'>" + data[i].goods.id + "</span>" + data[i].goods.name
                            + "</td><td>" + data[i].goods.price
                            + "</td><td width='20%' class='text-center'>\n" +
                            "                    <div class='input-group' style='width: 60%'>\n" +
                            "                        <span class=\"input-group-btn\">\n" +
                            "                            <button onclick=\"changeScCount(this,'sub')\" class='btn btn-default btn-sm'\n" +
                            "                                    type='button'>-</button>\n" +
                            "                        </span>\n" +
                            "                        <input type='text' class='form-control text-center input-sm' value='" + data[i].goodsCount + "'>\n" +
                            "                        <span class='input-group-btn'>\n" +
                            "                            <button onclick=\"changeScCount(this,'add')\" class='btn btn-default btn-sm'\n" +
                            "                                    type='button'>+</button>\n" +
                            "                        </span>\n" +
                            "                    </div>\n" +
                            "                </td><td><a href='#' onclick='delete_shoppingcart_goods(this);'>删除</a></td></tr>");
                        sum_price = sum_price + (data[i].goods.price * data[i].goodsCount);
                    }
                    $("#sum_price").html(sum_price);

                });
            }

        });

}

//通过商品名称与用户名称删除购物车商品
function delete_shoppingcart_goods(obj) {

    var sc_id = $(obj).parents().parents().children("td").eq(0).children('span').eq(0).text();        //获取登陆人的用户名

    toastr.info(sc_id);

    $.post('/javaEE_ssh/shoppingCart/deleteShoppingcart.action',
        {'sc.id': sc_id},
        function (data) {
            if (data == 1) {
                toastr.success("删除成功！！！", "success");
                $(obj).parents().parents().children("td").remove();
                shopping_cart();
            } else {
                toastr.error("删除成功！！！", "error");
            }
        });

}

function changeScCount(obj, status) {
    var sc_id = $(obj).parents().parents().parents().parents().children("td").eq(0).children("span").eq(0).text();
    var goods_id = $(obj).parents().parents().parents().parents().children("td").eq(0).children("span").eq(1).text();
    var user_id = $("#id").text();
    var goods_count = $(obj).parents().parents().children("input").val();
    var sum_price = $("#sum_price").text();
    var goods_price = $(obj).parents().parents().parents().parents().children("td").eq(1).text();
    var sc_status = 0;
    if (status == "add") {
        $.post('/javaEE_ssh/shoppingCart/updateShoppingcart.action',
            {
                'sc.id': sc_id,
                'sc.status': sc_status,
                'sc.goods.id': goods_id,
                'sc.user.id': user_id,
                'sc.goodsCount': ++goods_count
            },
            function (data) {
                if (data == 1) {
                    $(obj).parents().parents().children("input").val(goods_count);
                    $("#sum_price").text(sum_price - (-goods_price));
                } else {
                    toastr.warning("系统异常");
                }
            });

    } else if (status == "sub") {
        if (goods_count == 1) {
            toastr.info("操作不成功！", '特别提示');
        } else {
            $.post('/javaEE_ssh/shoppingCart/updateShoppingcart.action',
                {
                    'sc.id': sc_id,
                    'sc.status': sc_status,
                    'sc.goods.id': goods_id,
                    'sc.user.id': user_id,
                    'sc.goodsCount': --goods_count
                },
                function (data) {
                    if (data == 1) {
                        $(obj).parents().parents().children("input").val(goods_count);
                        $("#sum_price").text(sum_price - goods_price);
                    } else {
                        toastr.warning("系统异常");
                    }
                });
        }
    }

}

//点击我的信息弹出的模态框中的信息，用ajax 加载用户信息
function mymessage() {
    $("#username").parent().removeClass("has-error has-success");
    $("#username").next("span").text("");

    var user_id = $("#id").html();              //html() 函数获取 span标签的值

    $.get('/javaEE_ssh/user/selectUserById.action',
        {'user.id': user_id},
        function (data) {

            if (data == 101) {
                toastr.warning("请先登入!!!");
                setTimeout(function () {
                    $(location).attr('href', '/javaEE_ssh/jsp/user/login.jsp');
                }, 2000);
            } else {

                $('#user_id').html(data.id);
                $('#username').val(data.username);
                $('#password').val(data.password);
                $('#userRole').val(data.role == null ? "" : data.role);
                $('#gender').val(data.gender == null ? "" : data.gender);
                $('#money').val(data.money == null ? "" : data.money);
                $('#address').val(data.defaultAddress == null ? "" : data.money);
                $('#phone').val(data.defaultPhone);
                // $('#money').attr('readOnly', 'true');
                $('#userRole').attr('disabled', 'disabled');
            }

        })
}

//输入商品名称搜索商品
function Search_goodsByGoodsName() {

    var goods_name = $('#Search_goodsname').val();

    $.get("/javaEE_ssh/goods/findGoodsByName.action",
        {'goods_name': goods_name},
        function (data) {

            if (data.goodsList.length == 0) {

                toastr.info("很遗憾！未查到相关商品......");
            } else {
                $("div[name='goods_card']").remove();
            }

            var goodscar = $("#goods");

            if (goodscar.length == 0) {

                $("#ajaxload").load('/javaEE_ssh/jsp/goods/goods.jsp',
                    function () {
                        <!--把数据库的信息填充到页面中-->
                        var size = data.goodsList.length;

                        for (var i = 0; i < data.goodsList.length; i++) {

                            loadSP(data.goodsList[i]);

                        }
                    });

            } else {

                for (var i = 0; i < data.goodsList.length; i++) {

                    loadSP(data.goodsList[i]);

                }

            }

        });

}

function loadByType(inputType) {

    var type = inputType;

    if (inputType == 'xiaomi') {
        type = "小米";
    } else if (inputType == 'huawei') {
        type = "华为";
    } else if (inputType == 'pingguo') {
        type = "苹果";
    } else {
        type = "三星";
    }

    $.get('/javaEE_ssh/goods/selectGoodsByType.action',
        {'goods.type': type},
        function (data) {
            if (data.goodsList.length == 0) {
                toastr.info("很遗憾！未查到相关商品......");
            } else {
                $("div[name='goods_card']").remove();
            }

            var goodscar = $("#goods");

            if (goodscar.length == 0) {

                $("#ajaxload").load('/javaEE_ssh/jsp/goods/goods.jsp',
                    function () {
                        <!--把数据库的信息填充到页面中-->
                        var size = data.goodsList.length;

                        for (var i = 0; i < data.goodsList.length; i++) {

                            loadSP(data.goodsList[i]);

                        }
                    });

            } else {

                for (var i = 0; i < data.goodsList.length; i++) {

                    loadSP(data.goodsList[i]);

                }

            }

        });

}

//退出登录
function logOut() {

    var a = confirm("是否退出！！！");

    if (a) {
        $.post('/javaEE_ssh/user/logOut.action',
            function (data) {
                if (data == 101) {
                    toastr.warning("请先登入!!!");
                } else {
                    window.location.href = "http://localhost:8080/javaEE_ssh/jsp/user/login.jsp";
                }
            });
    } else {
        toastr.info("退出取消");
    }
}

// 点击我的信息模态框的保存信息按钮，出发ajax ，将这时模态框的信息 更新 到数据库中
function save_userMseeage() {
    var ass1 = $("#province").find("option:selected").text();
    var ass2 = $("#citys").find("option:selected").text();
    var ass3 = $("#county").find("option:selected").text();

    var ass = ass1 + ass2 + ass3;

    // alert(ass);

    $.post('/javaEE_ssh/user/updateUser.action',
        {
            'user.id': $('#user_id').html(),
            'user.username': $('#username').val(),
            'user.password': $('#password').val(),
            'user.role': $('#userRole').val(),
            'user.gender': $('#gender').val(),
            'user.money': $('#money').val(),
            'user.defaultPhone': $('#phone').val(),
            'user.defaultAddress': $('#address').val(),
        },
        function (data) {
            if (data == 1) {
                toastr.success("修改个人信息成功！！！", "success");

            } else {
                toastr.error("save_userMseeage() , error", "error");
            }
        })

}

//点击我的订单，异步加载用户订单
function myorder() {

    var user_id = $("#id").html();        //获取登陆人的用户编号
    $.get('/javaEE_ssh/user/selectOrders.action',
        {'user.id': user_id},
        function (data) {
            if (data == 101) {
                toastr.warning("请先登入!!!");
                setTimeout(function () {
                    $(location).attr('href', '/javaEE_ssh/jsp/user/login.jsp');
                }, 1500);
            } else {
                $("#ajaxload").load('/javaEE_ssh/jsp/orders/orders.jsp', function () {
                    for (var i = 0; i < data.length; i++) {
                        $("#order_table").append("<tr><td style='color: black'>" + data[i].id
                            + "</td><td style='color: #f40'>￥" + data[i].sumprice
                            + "</td><td>" + data[i].createtime
                            + "</td><td>" + data[i].user.username
                            + "</td><td>" + data[i].orderStatus
                            + "</td><td><a class='small' href='#' onclick='showOrder_goods(this);'  data-toggle='modal' data-target='#order_goodsModel'>查询购买商品的详情信息</a></td></tr>")
                    }
                });
            }

        });
}

//订单详情
function showOrder_goods(obj) {

    var order_id = $(obj).parents().parents().children("td").eq(0).text();

    $.get('/javaEE_ssh/order_goods/selectOGByOrderId.action',
        {'orderGoods.order.id': order_id},
        function (data) {

            if (data == 101) {
                toastr.warning("请先登入!!!");
                setTimeout(function () {
                    $(location).attr('href', '/javaEE_ssh/jsp/user/login.jsp');
                }, 1500);
            } else {
                $("tr[name='as']").remove();
                if (data.length == 0) {
                    $("#order_goods_table").append("<tr name='as'><td colspan='3' class='text-warning text-center lead'>抱歉,该订单暂无详情！</td></tr>");
                } else {
                    for (var i = 0; i < data.length; i++) {
                        $("#order_goods_table").append("<tr name='as'><td>" + data[i].goods.name
                            + "</td><td style='color: #f40'>￥" + data[i].goods.price
                            + "</td><td>" + data[i].goodsCount
                            + "</td></tr>");
                    }
                }
            }

        });

}

//点击购物车的结算按钮，将购物车的信息，插入到用户订单表
function pay_money() {

    var sumprice = $("#sum_price").text();
    if (sumprice == 0) {
        toastr.info("请选择需购买的商品！！！", "特别提示！");
        return false;
    }

    var order_status = 1;

    var user_id = $("#id").html();        //获取登陆人的用户编号
    var goodsNum = $("#shoppingcart_table").children("tr").length;
    var goodsList = [];               //购物车商品id
    var goodsCount = [];            //数量
    var scDel = [];                 //购物车记录id数组
    for (var i = 0; i < goodsNum; i++) {
        goodsList.push($("#shoppingcart_table").children("tr").eq(i).children("td").eq(0).children("span").eq(1).text());
        goodsCount.push($("#shoppingcart_table").children("tr").eq(i).children("td").eq(2).children("div").children("input").val());
        scDel.push($("#shoppingcart_table").children("tr").eq(i).children("td").eq(0).children("span").eq(0).text());
    }
    // json.scDel = scDel;
    // json.sc = {'count': 10};
    //

    var gSellData = "";
    for (var k = 0; k < goodsNum; k++) {
        gSellData = gSellData + "&goodsList[" + k + "].id=" + goodsList[k] + "&goodsList[" + k + "].count=" + goodsCount[k];
    }

    $.post('/javaEE_ssh/goods/checkGoodsCount.action',
        gSellData,
        function (result) {
            if (result == "success") {

                $.post('/javaEE_ssh/user/userPayment.action',
                    {
                        'user.money': sumprice,
                        'user.id': user_id
                    },
                    function (data) {

                        if (data == 0) {
                            toastr.warning('系统异常，请重试！', 'Warning');
                        } else if (data == 2) {
                            toastr.info('您的余额不足！', '温馨提示');
                        } else if (data == 1) {

                            toastr.success('支付成功', 'success');

                            $.post('/javaEE_ssh/orders/insertOrders.action',
                                {
                                    'order.sumprice': sumprice,
                                    'order.user.id': user_id,
                                    'order.orderStatus': order_status
                                },
                                function (result_o) {
                                    if (result_o != 0) {

                                        toastr.info("请前往订单查看...");

                                        var ogInsData = "";
                                        for (var a = 0; a < goodsList.length; a++) {
                                            ogInsData = ogInsData + "&ogList[" + a + "].goodsCount=" + goodsCount[a]
                                                + "&ogList[" + a + "].order.id=" + result_o
                                                + "&ogList[" + a + "].goods.id=" + goodsList[a];
                                        }

                                        $.post('/javaEE_ssh/order_goods/insertManyOG.action',
                                            ogInsData,
                                            function (result_og) {

                                                if (result_og == 1) {
                                                    toastr.success("OGsuccess");

                                                    $.post('/javaEE_ssh/goods/goodsSellOut.action',
                                                        gSellData,
                                                        function (result_g) {
                                                            if (result_g == 1) {

                                                                myorder();

                                                            }
                                                        });

                                                }

                                            });

                                        var scDelData = "";
                                        for (var j = 0; j < scDel.length; j++) {
                                            scDelData = scDelData + "&scDel[" + j + "]=" + scDel[j];
                                        }
                                        $.post('/javaEE_ssh/shoppingCart/deleteSomeSc.action',
                                            scDelData,
                                            function (result_sc) {
                                                if (result_sc == 1) {
                                                    toastr.success('购物车已清空');
                                                    shopping_cart()
                                                }
                                            });

                                    } else {
                                        toastr.warning('系统异常');
                                    }
                                });

                        }

                    });

            } else {
                toastr.info(result, "特别提醒");
            }
        });

    // $.post('/javaEE_ssh/goods/goodsSellOut.action',
    //     {},
    //     function (data) {
    //
    //     });
    //

    // $.post('/javaEE_ssh/user/userPayment.action',
    //     {
    //         'user.money': sumprice,
    //         'user.id': user_id
    //     },
    //     function (data) {
    //
    //         if (data == 0) {
    //             toastr.warning('系统异常，请重试！', 'Warning');
    //         } else if (data == 2) {
    //             toastr.warning('余额不足！', 'Warning');
    //         } else if (data == 1) {
    //             toastr.success('支付成功', 'success');
    //         }
    //
    //     });

    // $.post('/javaEE_ssh/orders/insertOrders.action',
    //     {
    //         'order.sumprice': sumprice,
    //         'order.creattime': createtime,
    //         'order.user.id': user_id,
    //         'order.orderStatus': order_status
    //     },
    //     function (data) {
    //         if (data == 1) {
    //             toastr.success('结算成功', 'success');
    //
    //             for (var i = 0; i < scDel.length; i++) {
    //                 $.post('/javaEE_ssh/shoppingCart/deleteShoppingcart.action',
    //                     json, function (result) {
    //                         if (result == 1) {
    //
    //                         }
    //                     });
    //             }
    //
    //             myorder();
    //         } else {
    //             toastr.error('很遗憾！购买失败！', 'error');
    //         }
    //     });

}

//按价格区间进行搜索
function priceInBetween(low, high) {

    $.get('/javaEE_ssh/goods/priceBetween.action',
        {
            'priceLow': low,
            'priceHigh': high
        },
        function (data) {
            if (data.goodsList.length == 0) {
                toastr.info("很遗憾！未查到相关商品......");
            } else {
                $("div[name='goods_card']").remove();
            }

            var goodscar = $("#goods");

            if (goodscar.length == 0) {

                $("#ajaxload").load('/javaEE_ssh/jsp/goods/goods.jsp',
                    function () {
                        <!--把数据库的信息填充到页面中-->
                        var size = data.goodsList.length;

                        for (var i = 0; i < data.goodsList.length; i++) {

                            loadSP(data.goodsList[i]);

                        }
                    });

            } else {

                for (var i = 0; i < data.goodsList.length; i++) {

                    loadSP(data.goodsList[i]);

                }

            }

        });

}

