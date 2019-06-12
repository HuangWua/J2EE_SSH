//默认管理页面，加载商品信息页面
window.onload = function () {
    usermanager();
}

//向表格填充Goods数据
function loadGoods(data) {
    $("#goods_table").append("<tr name='goods_tr'><td class='text-center'>" + data.id
        + "</td><td style='white-space: nowrap;text-overflow: ellipsis;overflow: hidden'>" + data.name
        + "</td><td>" + data.type
        + "</td><td style='white-space: nowrap;text-overflow: ellipsis;overflow: hidden;color: #f40'>￥" + data.price
        + "</td><td>" + data.count
        + "</td><td>" + data.createtime
        + "</td><td style='white-space: nowrap;text-overflow: ellipsis;overflow: hidden'>" + data.img
        + "</td><td class='text-center'>"
        + "<button onclick='edit_goods(this)' type='button' class='btn btn-success btn-sm' data-toggle='modal' data-target='#goods_MessageModel'><span class='glyphicon glyphicon-pencil'></span>编辑</button>"
        + "&nbsp;&nbsp;<button onclick='delete_goods(this)' type='button' class='btn btn-danger btn-sm'><span class='glyphicon glyphicon-trash'></span>删除</button</td></tr>"
    );
}

//向表格填充User数据
function loadUser(data) {
    if (data.role === 0) {
        data.role = "普通用户";
    } else if (data.role === 1) {
        data.role = "VIP用户";
    } else {
        data.role = "管理员用户";
    }
    $("#user_table").append("<tr><td>" + data.id
        + "</td><td>" + data.username
        + "</td><td>******</td><td>" + data.money
        + "</td><td>" + data.gender
        + "</td><td>" + data.defaultPhone
        + "</td><td>" + data.defaultAddress
        + "</td><td>" + data.role
        + "</td><td>"
        + "<button onclick='edit_User(this)' type='button' class='btn btn-success btn-sm' data-toggle='modal' data-target='#updateUserModal'><span class='glyphicon glyphicon-pencil'></span>编辑</button>"
        + "&nbsp;&nbsp;<button onclick='deleteUser(this)' type='button' class='btn btn-danger btn-sm'><span class='glyphicon glyphicon-trash'></span>删除</button</td></tr>"
    );
}

//向表格填充orders数据
function loadorders(data) {

    $("#orders_table").append("<tr><td>" + data.id
        + "</td><td style='color: #f40'>￥" + data.sumprice
        + "</td><td>" + data.createtime
        + "</td><td>" + data.user.username
        + "</td><td>" + data.orderStatus
        + "</td><td><a class='small' href='#' onclick='order_goods(this);'  data-toggle='modal' data-target='#order_goodsModel'>详情信息</a></td><td>"
        + "<button onclick='editOrder(this)' type='button' class='btn btn-success btn-sm' data-toggle='modal' data-target='#order_update'><span class='glyphicon glyphicon-pencil'></span>编辑</button>"
        + "&nbsp;&nbsp;<button onclick='deleteOrder(this)' type='button' class='btn btn-danger btn-sm'><span class='glyphicon glyphicon-trash'></span>删除</button</td></tr>"
    );
}

//用户管理
//
//
//用户管理函数
function usermanager() {

    $.get('/javaEE_ssh/user/selectuserByPage.action',
        {
            'pageBean.pageNum': 1,
            'pageBean.pageSize': 10
        },
        function (data) {
            $("#page").load('/javaEE_ssh/jsp/user/user_message.jsp', function () {
                for (var i = 0; i < data.list.length; i++) {
                    loadUser(data.list[i]);
                }
                fy(data, 'user')
            });
        });
}

//编辑用户信息
function edit_User(obj) {

    var user_id = $(obj).parents().parents().children("td").eq(0).text();       //被选元素的父元素的父元素的  td 的子元素的第二个的文本值。

    $.get('/javaEE_ssh/user/selectUserById.action',
        {
            'user.id': user_id
        },
        function (data) {
            $("#user_id").text(data.id);
            $("#username").val(data.username);
            $("#password").val(data.password);
            $("#gender").val(data.gender);
            $("#role").val(data.role);
            $("#phone").val(data.defaultPhone);
            $("#money").val(data.money);
            $("#address").val(data.defaultAddress);
        });

}

//删除用户
function deleteUser(obj) {

    var a = confirm("确认删除用户：" + $(obj).parents().parents().children("td").eq(1).text() + "?");

    if (a) {
        var user_id = $(obj).parents().parents().children("td").eq(0).text();       //商品id。
        $.post('/javaEE_ssh/user/deleteUser.action',
            {
                'user.id': user_id
            },
            function (data) {
                if (data == 1) {
                    $(obj).parents().parents().children("td").remove();
                    toastr.success('删除成功', 'success');
                } else {
                    toastr.error("很遗憾，删除失败！", 'error');
                }
            });
    } else {
        toastr.info("取消删除");
    }
}

//更新用户信息
function save_user() {

    var a = confirm("确认更改用户：" + $('#username').val() + "的信息?");

    if (a) {
        $.post('/javaEE_ssh/user/updateUser.action',
            {
                'user.id': $('#user_id').text(),
                'user.username': $('#username').val(),
                'user.password': $('#password').val(),
                'user.gender': $('#gender').val(),
                'user.role': $('#role').val(),
                'user.defaultPhone': $('#phone').val(),
                'user.money': $('#money').val(),
                'user.defaultAddress': $('#address').val()
            },
            function (data) {
                if (data == 1) {
                    usermanager();
                    toastr.success('更改成功', 'success');
                } else {
                    toastr.error("很遗憾，更改失败！", 'error');
                }
                $(".modal-backdrop").remove();
            });
    } else {
        toastr.info("放弃更改");
    }
}

//添加用户信息
function add_user() {

    var role = $('#role').val();
    $.post('/javaEE_ssh/user/insertUser.action',
        {
            'user.username': $('#add_username').val(),
            'user.password': $('#add_password').val(),
            'user.gender': $('#add_gender').val(),
            'user.role': $('#add_role').val(),
            'user.defaultPhone': $('#add_phone').val(),
            'user.money': $('#add_money').val(),
            'user.defaultAddress': $('#add_address').val()
        },
        function (data) {
            if (data == 1) {
                usermanager();
                toastr.success('添加成功', 'success');
            } else {
                toastr.error("很遗憾，添加失败！", 'error');
            }
            $(".modal-backdrop").remove();
        });

}

//根据名称搜索用户信息
function Search_userByUsername() {

    var user_name = $('#Search_username').val();

    $.get("/javaEE_ssh/user/selectUserLikeName.action",
        {
            'registerName': user_name
        },
        function (data) {
            if (data.length == 0) {
                toastr.info("很遗憾！未查到相关用户......");
            } else {
                $("#user_table").children('tr').remove();
                for (var i = 0; i < data.length; i++) {

                    loadUser(data[i]);

                }
            }

        });

}

//用户分类
function userByRole(role) {

    $.get('/javaEE_ssh/user/selectUserByRole.action',
        {
            'user.role': role
        },
        function (data) {
            if (data.length == 0) {
                toastr.info("很遗憾！未查到相关用户......");
            } else {
                $("#user_table").children('tr').remove();
                for (var i = 0; i < data.length; i++) {

                    loadUser(data[i]);

                }
            }
        });
}

//商品管理
//
//
//
//商品管理函数
function goodsmanager() {

    // $.get("/javaEE_ssh/goods/selectAllGoods.action",
    //     function (data) {
    //         $("#page").load('/javaEE_ssh/jsp/goods/goods_message.jsp',
    //             function () {
    //                 <!--把数据库的信息填充到页面中-->
    //                 for (var i = 0; i < data.goodsList.length; i++) {
    //                     // $("#goods_table").append("<tr name='goods_tr'><td class='text-center'>" + data.goodsList[i].id
    //                     //     + "</td><td>" + data.goodsList[i].name
    //                     //     + "</td><td>" + data.goodsList[i].type
    //                     //     + "</td><td>" + data.goodsList[i].price
    //                     //     + "</td><td>" + data.goodsList[i].count
    //                     //     + "</td><td>" + data.goodsList[i].createtime
    //                     //     + "</td><td>" + data.goodsList[i].img
    //                     //     + "</td><td class='text-center'>"
    //                     //     + "<button onclick='edit_goods(this)' type='button' class='btn btn-success btn-sm' data-toggle='modal' data-target='#goods_MessageModel'><span class='glyphicon glyphicon-pencil'></span>编辑</button>"
    //                     //     + "&nbsp;&nbsp;<button onclick='delete_goods(this)' type='button' class='btn btn-danger btn-sm'><span class='glyphicon glyphicon-trash'></span>删除</button</td></tr>"
    //                     // );
    //                     loadGoods(data.goodsList[i]);
    //
    //                 }
    //             });
    //     });

    $.get("/javaEE_ssh/goods/selectgoodsByPage.action",
        {
            'pageBean.pageNum': 1,
            'pageBean.pageSize': 10
        },
        function (data) {
            $("#page").load('/javaEE_ssh/jsp/goods/goods_message.jsp',
                function () {
                    <!--把数据库的信息填充到页面中-->
                    for (var i = 0; i < data.list.length; i++) {

                        loadGoods(data.list[i]);

                    }

                    fy(data, 'goods');

                });
        });

}

//点击修改商品信息，弹出的模态框,用jax加载该商品的信息呈现在模态框中
function edit_goods(obj) {

    var good_id = $(obj).parents().parents().children("td").eq(0).text();       //被选元素的父元素的父元素的  td 的子元素的第二个的文本值。

    $.get('/javaEE_ssh/goods/selectGoodsById.action',
        {
            'goods.id': good_id
        },
        function (data) {
            $("#gid").text(data.goods.id);
            $("#goods_id").val(data.goods.id);
            $("#gname").val(data.goods.name);
            $("#gtype").val(data.goods.type);
            $("#gprice").val(data.goods.price);
            $("#gcount").val(data.goods.count);
            $("#gcreatetime").val(data.goods.createtime);
            $("#edit_img").attr('src', "/image/" + data.goods.img);
            $("#goods_img").val(data.goods.img);
        });

}

//修改商品模态框的保存按钮所点击的函数，把表单信息，用ajax 进行更新商品信息
function save_goods() {

    // $.post('/javaEE_ssh/goods/updateGoods.action',
    //     {
    //         'goods.id': $('#gid').text(),
    //         'goods.name': $('#gname').val(),
    //         'goods.type': $('#gtype').val(),
    //         'goods.price': $('#gprice').val(),
    //         'goods.count': $('#gcount').val()
    //     },
    //     function (data) {
    //         if (data == 1) {
    //             goodsmanager();
    //             toastr.success('保存成功', 'success');
    //         } else {
    //             toastr.error("很遗憾，保存失败！", 'error');
    //         }
    //     });

    var editForm = new FormData($("#editForm")[0]);
    $.ajax({
        url: '/javaEE_ssh/goods/updateGoods.action',
        type: "post",
        data: editForm,
        dataType: "JSON",
        cache: false,
        processData: false,
        contentType: false,
        success: function (result) {
            if (result == 1) {
                toastr.success('保存成功', 'success');
                $(".modal-backdrop").remove();
                $("body").removeClass("modal-open");
                $("body").css('padding-right', '0px');
                goodsmanager();
            } else {
                toastr.error("很遗憾，保存失败！", 'error');
            }
        }
    });

}

function add_goods() {

    // $.post('/javaEE_ssh/goods/insertGoods.action',
    //     {
    //         'goods.name': $('#add_gname').val(),
    //         'goods.type': $('#add_gtype').val(),
    //         'goods.price': $('#add_gprice').val(),
    //         'goods.count': $('#add_gcount').val()
    //     },
    //     function (data) {
    //         if (data == 1) {
    //             goodsmanager();
    //             toastr.success('添加成功', 'success');
    //         } else {
    //             toastr.error("很遗憾，添加失败！", 'error');
    //         }
    //     });

    var addForm = new FormData($("#addForm")[0]);
    $.ajax({
        url: '/javaEE_ssh/goods/insertGoods.action',
        type: "post",
        data: addForm,
        dataType: "JSON",
        cache: false,
        sync: true,
        processData: false,
        contentType: false,
        success: function (result) {
            if (result == 1) {
                toastr.success('保存成功', 'success');
                $(".modal-backdrop").remove();
                $("body").removeClass("modal-open");
                $("body").css('padding-right', '0px');
                goodsmanager();

            } else {
                toastr.error("很遗憾，添加失败！", 'error');
            }

        }
    });
}

function delete_goods(obj) {

    var a = confirm("确认删除商品：" + $(obj).parents().parents().children("td").eq(1).text() + "?");

    if (a) {
        var good_id = $(obj).parents().parents().children("td").eq(0).text();       //商品id。
        $.post('/javaEE_ssh/goods/deleteGoods.action',
            {
                'goods.id': good_id
            },
            function (data) {
                if (data == 1) {
                    goodsmanager();
                    toastr.success('删除成功', 'success');
                } else {
                    toastr.error("很遗憾，删除失败！", 'error');
                }
            });
    } else {
        toastr.info("取消删除");
    }

}

//搜索框的查询商品
function Search_goodsBygoodsname() {

    var goods_name = $('#Search_goodsname').val();

    $.get("/javaEE_ssh/goods/findGoodsByName.action",
        {
            'goods_name': goods_name
        },
        function (data) {

            if (data.goodsList.length == 0) {

                toastr.info("很遗憾！未查到相关商品......");
            } else {
                $("#goods_table").children('tr').remove();
                for (var i = 0; i < data.goodsList.length; i++) {

                    loadGoods(data.goodsList[i]);

                }
            }

        });
}

//分类搜索
function goodsByType_manager(inputType) {

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
        {
            'goods.type': type
        },
        function (data) {
            if (data.goodsList.length == 0) {

                toastr.info("很遗憾！未查到相关商品......");
            } else {
                $("#goods_table").children('tr').remove();
                for (var i = 0; i < data.goodsList.length; i++) {

                    loadGoods(data.goodsList[i]);

                }
            }

        });

}

//订单管理
//
//
//
//商品管理函数
function ordersmanager() {

    $.get("/javaEE_ssh/orders/selectordersByPage.action",
        {
            'pageBean.pageNum': 1,
            'pageBean.pageSize': 10
        },
        function (data) {
            $("#page").load('/javaEE_ssh/jsp/orders/order_manager.jsp',
                function () {
                    <!--把数据库的信息填充到页面中-->
                    for (var i = 0; i < data.list.length; i++) {

                        loadorders(data.list[i]);

                    }
                    fy(data, 'orders');
                });
        });

}

//订单详情
function order_goods(obj) {

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

//修改订单信息
function editOrder(obj) {
    var order_id = $(obj).parents().parents().children("td").eq(0).text();       //被选元素的父元素的父元素的  td 的子元素的第一个的文本值。

    $.get('/javaEE_ssh/orders/selectOrdersById.action',
        {
            'id': order_id
        },
        function (data) {
            $("#order_id").val(data.id);
            $("#o_id").text(data.id);
            $("#order_sumprice").val(data.sumprice);
            $("#order_createtime").val(data.createtime);
            $("#order_user").val(data.user.username);
            $("#order_user_id").val(data.user.id);
            $("#order_status").val(data.orderStatus);
        });
}

//提交修改订单update
function save_orders() {

    $.post('/javaEE_ssh/orders/updateOrders.action',
        $("#o_editForm").serialize(),
        function (result) {
            if (result == 1) {
                ordersmanager();
                toastr.success("更改成功");
            } else {
                toastr.error("很遗憾，保存失败！", 'error');
            }
        });

}

//删除订单
function deleteOrder(obj) {

    var a = confirm("确认删除该订单?");

    if (a) {
        var order_id = $(obj).parents().parents().children("td").eq(0).text();      //订单id。

        $.post('/javaEE_ssh/order_goods/deleteOGByOrderId.action',
            {
                'orderGoods.order.id': order_id
            },
            function (result_og) {
                if (result_og == 1) {
                    $.post('/javaEE_ssh/orders/deleteOrders.action',
                        {
                            'order.id': order_id
                        },
                        function (data) {
                            if (data == 1) {
                                ordersmanager();
                                toastr.success('删除成功', 'success');
                            } else {
                                toastr.error("很遗憾，删除失败！", 'error');
                            }
                        });
                } else {
                    toastr.error("很遗憾，删除失败！", 'error');
                }
            });

    } else {
        toastr.info("取消删除");
    }
}

// //查询订单根据用户名
// function Search_OrdersByUsername() {
//
//     $.get("/javaEE_ssh/orders/selectOrdersByUname.action",
//         {
//             'order.user.username': $('#Search_username').val()
//         },
//         function (data) {
//             if (data.length == 0) {
//
//                 toastr.info("很遗憾！未查到相关商品......");
//             } else {
//                 $("#orders_table").children('tr').remove();
//                 for (var i = 0; i < data.length; i++) {
//
//                     loadorders(data[i]);
//
//                 }
//             }
//         });
// }

//查询订单根据用户名
function orderByMoney(low, high) {

    $.get("/javaEE_ssh/orders/selectOrdersByPrice.action",
        {
            'low': low,
            'high': high
        },
        function (data) {
            if (data.length == 0) {

                toastr.info("很遗憾！未查到相关商品......");
            } else {
                $("#orders_table").children('tr').remove();
                for (var i = 0; i < data.length; i++) {

                    loadorders(data[i]);

                }
            }
        });
}

//

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

//分页
function fy(data, type) {

    $("#showSize").val(data.pageSize);
    $("#startIndex").text(data.startIndex + 1);
    $("#endIndex").text(data.endIndex + 1);
    $("#totalRecord").text(data.totalRecord);
    if (data.pageNum == 1) {
        $("#page_bean").append("<li class='previous disabled'><a>上一页</a></li>");
    } else {
        $("#page_bean").append("<li class='previous'><a href='javascript:void(0);' onclick=\"previousPage('" + type + "')\">上一页</a></li>");
    }
    for (var i = data.start; i <= data.end; i++) {
        if (i == data.pageNum) {
            $("#page_bean").append("<li class='active'><a href='javascript:void(0);' onclick=\"toPageNum(" + i + ",'" + type + "')\">" + i + "</a></li>");
        } else {
            $("#page_bean").append("<li><a href='javascript:void(0);' onclick=\"toPageNum(" + i + ",'" + type + "')\">" + i + "</a></li>");
        }
    }
    if (data.pageNum == data.totalPage) {
        $("#page_bean").append("<li class='next disabled'><a>下一页</a></li>");
    } else {
        $("#page_bean").append("<li class='next'><a href='javascript:void(0);' onclick=\"nextPage('" + type + "')\">下一页</a></li>");
    }
}

function previousPage(type) {

    var nowpage = $("#page_bean").children(".active").text();

    $.get('/javaEE_ssh/' + type + '/select' + type + 'ByPage.action',
        {
            'pageBean.pageNum': --nowpage,
            'pageBean.pageSize': $("#showSize").val()
        },
        function (data) {

            fy_type(data, type);

        });

}

function toPageNum(pageNum, type) {
    $.get('/javaEE_ssh/' + type + '/select' + type + 'ByPage.action',
        {
            'pageBean.pageNum': pageNum,
            'pageBean.pageSize': $("#showSize").val()
        },
        function (data) {

            fy_type(data, type);

        });
}

function nextPage(type) {

    var nowpage = $("#page_bean").children(".active").text();

    $.get('/javaEE_ssh/' + type + '/select' + type + 'ByPage.action',
        {
            'pageBean.pageNum': ++nowpage,
            'pageBean.pageSize': $("#showSize").val()
        },
        function (data) {
            fy_type(data, type);
        });
}

function fy_type(data, type) {
    $("#page_bean").children("li").remove();
    if (type == 'goods') {
        $("#goods_table").children('tr').remove();
        for (var i = 0; i < data.list.length; i++) {
            loadGoods(data.list[i]);
        }
    } else if (type == 'orders') {
        $("#orders_table").children('tr').remove();
        for (var i = 0; i < data.list.length; i++) {
            loadorders(data.list[i]);
        }
    } else {
        $("#user_table").children('tr').remove();
        for (var i = 0; i < data.list.length; i++) {
            loadUser(data.list[i]);
        }
    }
    fy(data, type);
}

function select_change(type) {

    toPageNum(1, type);
    console.log("Success");

}