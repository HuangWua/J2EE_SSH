<%--
  Created by IntelliJ IDEA.
  User: duan
  Date: 2019-06-04
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="page-header table-responsive">
        <p class="text-left">
        <h2>我的订单</h2></p>
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th>订单编号</th>
                <th>合计(元)</th>
                <th>创建日期</th>
                <th>客户</th>
                <th>订单状态
                </td>
                <th>查看</th>
            </tr>
            </thead>

            <tbody id="order_table">

            </tbody>
        </table>
        <hr/>
    </div>


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
