<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="container-fluid" style="background-color: #ebebeb">
    <div class="col-md-12">
        <div class="navbar-header">
            <a href="#" class="navbar-brand">商品</a>
        </div>
        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <label for="Search_goodsname" class="control-label"></label>
                <input type="text" id="Search_goodsname" class="form-control input-sm" placeholder="输入商品名称">
            </div>
            <button type="button" class="btn btn-default btn-sm" onclick="Search_goodsBygoodsname();"><span
                    class="glyphicon glyphicon-search"></span>查询
            </button>
        </form>
        <ul class="nav navbar-nav navbar-left">
            <li><a href="#" onclick="goodsByType_manager('xiaomi')"><strong>小米</strong></a></li>
            <li><a href="#">|</a></li>
            <li><a href="#" onclick="goodsByType_manager('huawei')"><strong>华为</strong></a></li>
            <li><a href="#">|</a></li>
            <li><a href="#" onclick="goodsByType_manager('pingguo')"><strong>苹果</strong></a></li>
            <li><a href="#">|</a></li>
            <li><a href="#" onclick="goodsByType_manager('sanxing')"><strong>三星</strong></a></li>
        </ul>
        <button type="button" class="btn btn-primary navbar-btn navbar-right btn-sm" data-toggle="modal"
                data-target="#add_goods"><span class="glyphicon glyphicon-plus"></span>增加商品
        </button>
    </div>
</div>


<table class="table table-hover table-bordered table-striped" width="100%" style="table-layout: fixed">
    <thead>
    <tr>
        <th>商品编号</th>
        <th>商品名称</th>
        <th>商品类别</th>
        <th>单价(￥)</th>
        <th>数量</th>
        <th width="22%">生产日期</th>
        <th width="12%">图片</th>
        <th width="18%">操作</th>
    </tr>
    </thead>

    <tbody id="goods_table">

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
            <select onchange="select_change('goods')" id="showSize" class="input-sm">
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

<div class="modal fade" id="goods_MessageModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel1">商品编号:<span id="gid"></span></h4>
            </div>
            <div class="modal-body">
                <form id="editForm" role="form">
                    <div class="row">
                        <div class="col-md-6">
                            <label for="gname">商品名称</label>
                            <input name="goods.id" type="text" class="form-control hidden" id="goods_id">
                            <input name="goods.name" type="text" class="form-control" id="gname">
                        </div>
                        <div class="col-md-6">
                            <label for="gimg">图片</label>
                            <img width="200px" height="130px" id="edit_img" src="">
                            <input name="goods.img" type="text" class="form-control hidden" id="goods_img">
                            <input name="upload" type="file" class="form-control" id="gimg">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="gtype">商品类别</label>
                            <select name="goods.type" class="form-control" id="gtype">
                                <option value="小米">小米</option>
                                <option value="华为">华为</option>
                                <option value="苹果">苹果</option>
                                <option value="三星">三星</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="gprice">单价</label>
                            <input name="goods.price" type="text" class="form-control" id="gprice">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="gcount">数量</label>
                            <input name="goods.count" type="text" class="form-control" id="gcount">
                        </div>
                        <div class="col-md-6">
                            <label for="gcreatetime">生产日期</label>
                            <input type="text" class="form-control" id="gcreatetime">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="save_goods()">Save changes
                </button>       <!-- data-dismiss="modal",作用是点击按钮，关闭模态框 -->
            </div>
        </div>
    </div>
</div>


<!-- 增加商品信息的模态框 -->

<div class="modal fade" id="add_goods" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">增加商品</h4>
            </div>
            <div class="modal-body">
                <form id="addForm" role="form">
                    <div class="row">
                        <div class="col-md-6">
                            <label for="add_gname">商品名称</label>
                            <input type="text" name="goods.name" class="form-control" id="add_gname">
                        </div>
                        <div class="col-md-6">
                            <label for="add_gimg">图片</label>
                            <input name="upload" type="file" class="form-control" id="add_gimg">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="add_gtype">商品类别</label>
                            <select name="goods.type" class="form-control" id="add_gtype">
                                <option value="小米">小米</option>
                                <option value="华为">华为</option>
                                <option value="苹果">苹果</option>
                                <option value="三星">三星</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="add_gprice">单价</label>
                            <input name="goods.price" type="text" class="form-control" id="add_gprice">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="add_gcount">数量</label>
                            <input name="goods.count" type="text" class="form-control" id="add_gcount">
                        </div>
                        <div class="col-md-6">
                            <label for="add_gcreatetime">生产日期</label>
                            <input type="text" class="form-control" id="add_gcreatetime">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="add_goods()">Save
                </button>       <!-- data-dismiss="modal",作用是点击按钮，关闭模态框 -->
            </div>
        </div>
    </div>
</div>

