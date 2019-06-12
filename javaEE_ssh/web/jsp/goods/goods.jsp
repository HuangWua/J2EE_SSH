<%--
  Created by IntelliJ IDEA.
  User: duan
  Date: 2019-06-03
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%--<div class="container">
    <div class="row">
        <h3>筛选</h3>
        <div class="col-md-12">
			 <table class="table table-bordered">
                <tr>
                    <td style="width:150px;">
                    	<ul class="nav navbar-nav">
				        <li><a href="#"><span>商品适用人群</span></a></li>
						</ul>

                    </td>
                    <td>
                        <ul class="nav navbar-nav">
				        <li><a href="#">儿童</a></li>
				        <li><a href="#" >|</a></li>
				        <li><a href="#">青年</a></li>
				         <li><a href="#">|</a></li>
				        <li><a href="#">壮年</a></li>
				         <li><a href="#">|</a></li>
				        <li><a href="#">老年</a></li>
				         <li><a href="#">|</a></li>
				        <li><a href="#">全体人员</a></li>
					  	</ul>
                    </td>

                </tr>


            </table>
        </div>

    </div>
</div>

<hr/>--%>

<!-- 商品展示 -->
<div class="container">
    <div class="col-md-12" id="goods">
        <!--
            <div class='col-md-3'>
                <div class='thumbnail'><img src='../dill/img/10.jpg' alt='html书籍'>
                         <div class='caption'><p><a href='#' id='sp_name' style='font-size:20px'>"+result[i].name+"</a></p>"+
                         "<p><a href='#'style='font-size:17px;' onclick='modelmessage(this)' data-toggle='modal' data-target='#message'>详情信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='#'style='font-size:15px;' onclick='add_shopping_cart(this);'>加入购物车</a></p><p style='color:red;'> 市场价：<del id='sp_marketprice'>"+result[i].market_price+"</del>"+
                         "，销售价:<span id='sp_shopprice'>"+result[i].shop_price+"</span></p></div></div></div>"
            -->
    </div>

</div>


<!--商品详情信息模态框-->

<div class="modal fade" id="message" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="goods_name"></h4>
            </div>
            <form class="form-horizontal">
                <div class="modal-body">
                    <table class="table table-bordered">

                        <tr>
                            <td>商品编号</td>
                            <td id="goods_id"></td>
                            <td>商品种类</td>
                            <td id="goods_type"></td>
                            <td colspan="2" ; rowspan="3" ;><img width="180px" height="140px" id="g_img" src=""
                                                                 alt="html书籍"></td>
                        </tr>
                        <tr>
                            <td>商品数量</td>
                            <td id="goods_count"></td>
                            <td>商品价格￥</td>
                            <td style="color: #f40" id="goods_price"></td>
                        </tr>
                        <tr>
                            <td>商品生产日期</td>
                            <td colspan="3" id="createtime"></td>
                        </tr>

                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                </div>
            </form>
        </div>
    </div>
</div>
