<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="container-fluid" style="background-color: #ebebeb">
    <div class="col-md-12">
        <div class="navbar-header">
            <a onclick="ordersmanager()" href="#" class="navbar-brand">订单</a>
        </div>
        <%--<form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <label for="Search_username" class="control-label"></label>
                <input type="text" id="Search_username" class="form-control input-sm" placeholder="输入用户名称">
            </div>
            <button type="button" class="btn btn-default btn-sm" onclick="Search_OrdersByUsername();"><span
                    class="glyphicon glyphicon-search"></span>查询
            </button>
        </form>--%>
        <ul class="nav navbar-nav navbar-left">
            <li><a href="#" onclick="orderByMoney('0','499')"><strong>0~499</strong></a></li>
            <li><a href="#">|</a></li>
            <li><a href="#" onclick="orderByMoney('500','4999')"><strong>500~4999</strong></a></li>
            <li><a href="#">|</a></li>
            <li><a href="#" onclick="orderByMoney('5000','99999')"><strong>5000~99999</strong></a></li>
            <li><a href="#">|</a></li>
            <li><a href="#" onclick="orderByMoney('100000',null)"><strong>10万以上</strong></a></li>
        </ul>
        <button type="button" class="btn btn-primary navbar-btn navbar-right btn-sm" data-toggle="modal"
                data-target="#add_goods" disabled><span class="glyphicon glyphicon-plus"></span>
        </button>
    </div>
</div>


<table class="table table-hover table-bordered table-striped">
    <thead>
    <tr>
        <th>订单ID</th>
        <th>订单金额</th>
        <th>下单日期</th>
        <th>客户</th>
        <th>订单状态</th>
        <th>详情</th>
        <th>操作</th>
    </tr>
    </thead>

    <tbody id="orders_table">

    </tbody>

    <!-- ajax 加载 用户信息 -->
    <!--
    <tr>
        <td>food</td>
        <td>面皮</td>
        <td>青年</td>
        <td>10</td>
        <td>8</td>
        <td>2017.7.7</td>
    </tr>
    -->
</table>

<div class="row">
    <div class="col-md-3 text-center">
        <div>
            每页
            <select onchange="select_change('orders')" id="showSize" class="input-sm">
                <option value="10">10</option>
                <option value="25">25</option>
                <option value="50">50</option>
                <option value="100">100</option>
            </select>
            条记录
        </div>
    </div>
    <div class="col-md-4 text-center">
        <div>
            显示第<span id="startIndex"></span>至第<span id="endIndex"></span> 项记录，
            共<span id="totalRecord"></span>项
        </div>
    </div>
    <div class="col-md-5 text-right">
        <div>
            <ul id="page_bean" class="pagination pagination-sm">
                <%--<li class="previous"><a href="#" onclick="previousPage('Goods')">上一页</a></li>
                <li><a href="#" onclick="toPageNum(1,'Goods')">1</a></li>
                <li><a href="#" onclick="toPageNum(1,'Goods')">2</a></li>
                <li><a href="#" onclick="toPageNum(1,'Goods')">3</a></li>
                <li><a href="#" onclick="toPageNum(1,'Goods')">4</a></li>
                <li><a href="#" onclick="toPageNum(1,'Goods')">5</a></li>
                <li class="next"><a href="#" onclick="nextPage('Goods')">下一页</a></li>--%>
            </ul>
        </div>
    </div>
</div>

<!-- 修改商品信息的模态框 -->

<div class="modal fade" id="order_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel1">订单编号:<span id="o_id"></span></h4>
            </div>
            <div class="modal-body">
                <form id="o_editForm" role="form">
                    <div class="row">
                        <div class="col-md-6">
                            <label for="order_sumprice">订单金额</label>
                            <input name="order.id" type="text" class="form-control hidden" id="order_id">
                            <input name="order.sumprice" type="text" class="form-control" id="order_sumprice">
                        </div>
                        <div class="col-md-6">
                            <label for="order_createtime">下单日期</label>
                            <input type="text" class="form-control" id="order_createtime">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="order_user">客户</label>
                            <input name="order.user.id" type="text" class="form-control hidden" id="order_user_id">
                            <input type="text" class="form-control" id="order_user">
                        </div>
                        <div class="col-md-6">
                            <label for="order_status">订单状态</label>
                            <select class="form-control" name="order.orderStatus" id="order_status">
                                <option value="0">未支付</option>
                                <option value="1">已付款</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="save_orders()">Save changes
                </button>       <!-- data-dismiss="modal",作用是点击按钮，关闭模态框 -->
            </div>
        </div>
    </div>
</div>


<!-- 增加商品信息的模态框 -->

<%--<div class="modal fade" id="order_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">订单编号:<span id="add_o_id"></span></h4>
            </div>
            <div class="modal-body">
                <form id="o_addForm" role="form">
                    <div class="row">
                        <div class="col-md-6">
                            <label for="order_sumprice">订单金额</label>
                            <input name="order.sumprice" type="text" class="form-control" id="add_order_sumprice">
                        </div>
                        <div class="col-md-6">
                            <label for="order_createtime">下单日期</label>
                            <input type="text" class="form-control" id="add_order_createtime">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="order_user">客户</label>
                            <input name="order.user.id" type="text" class="form-control hidden" id="add_order_user_id">
                            <input type="text" class="form-control" id="add_order_user">
                        </div>
                        <div class="col-md-6">
                            <label for="order_status">订单状态</label>
                            <input name="order.status" type="text" class="form-control" id="add_order_status">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="add_orders()">Save changes
                </button>       <!-- data-dismiss="modal",作用是点击按钮，关闭模态框 -->
            </div>
        </div>
    </div>
</div>--%>

<!-- 不同订单中的商品信息-模态框 -->
<div class="modal fade" id="order_goodsModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">商品信息:</h4>
            </div>
            <div class="modal-body">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>商品名称</th>
                        <th>单价</th>
                        <th>选购数量</th>
                    </tr>
                    </thead>

                    <tbody id="order_goods_table">

                    </tbody>

                </table>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</div>