<%--
  Created by IntelliJ IDEA.
  User: duan
  Date: 2019-06-03
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="page-header table-responsive">
        <p class="text-left">
        <h2>我的购物车</h2></p>

        <table class="table table-hover table-striped">

            <thead>
            <tr>
                <th>商品名称</th>
                <th>单价</th>
                <th>商品数量</th>
                <th>操作商品</th>
            </tr>
            </thead>

            <tbody id="shoppingcart_table">
            <%--<tr>
                <td>商品名称</td>
                <td>单价</td>
                <td width='20%' class='text-center'>
                    <div class='input-group' style='width: 60%'>
                        <span class="input-group-btn">
                            <button onclick="changeScCount(this,'sub')" class='btn btn-default btn-sm'
                                    type='button'>-</button>
                        </span>
                        <input id='scGoodsCount' type='text' class='form-control text-center input-sm' value=''>
                        <span class='input-group-btn'>
                            <button onclick="changeScCount(this,'add')" class='btn btn-default btn-sm'
                                    type='button'>+</button>
                        </span>
                    </div>
                </td>
                <td>操作商品</td>
            </tr>--%>
            </tbody>

        </table>
        <hr/>
        <p class="text-right">
        <span style="margin-right:150px;font-size:15px;color:#f40;">
        总金额:<span id="sum_price" style="font-size:15px;color:#f40;"></span>￥
        </span>
        </p>
        <p class="text-right">
            <a style="margin-right:20px;" class="btn btn-default" href="#" role="button">
                <span style="font-size:17px;" id="back">返回主页</span>
            </a>
            <a style="margin-right:20px;" class="btn btn-primary" href="#" role="button"
               onclick="pay_money();">
                <span style="font-size:17px;" id="pay_money">结算</span>
            </a>
        </p>
    </div>
</div>

