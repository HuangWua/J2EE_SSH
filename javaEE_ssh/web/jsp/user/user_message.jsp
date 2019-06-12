<%--
  Created by IntelliJ IDEA.
  User: duan
  Date: 2019-06-04
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid" style="background-color: #ebebeb">
    <div class="col-md-12">
        <div class="navbar-header">
            <a href="#" class="navbar-brand">用户</a>
        </div>
        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <label for="Search_username" class="control-label"></label>
                <input type="text" id="Search_username" class="form-control input-sm" placeholder="输入用户名">
            </div>
            <button type="button" class="btn btn-default btn-sm" onclick="Search_userByUsername();"><span
                    class="glyphicon glyphicon-search"></span>查询
            </button>
        </form>
        <ul class="nav navbar-nav navbar-left">
            <li><a href="#" onclick="userByRole(0)"><strong>普通用户</strong></a></li>
            <li><a href="#">|</a></li>
            <li><a href="#" onclick="userByRole(1)"><strong>vip用户</strong></a></li>
            <li><a href="#">|</a></li>
            <li><a href="#" onclick="userByRole(2)"><strong>管理员</strong></a></li>
            <%--<li><a href="#">|</a></li>
            <li><a href="#" onclick="loadByType('it')"><strong>数码产品</strong></a></li>--%>
        </ul>
        <button type="button" class="btn btn-primary navbar-btn navbar-right btn-sm" data-toggle="modal"
                data-target="#addUserModal"><span class="glyphicon glyphicon-plus"></span>增加用户
        </button>
    </div>
</div>

<table class="table table-hover table-striped table-bordered">
    <thead>
    <tr>
        <th>用户ID</th>
        <th>用户名</th>
        <th>密码</th>
        <th>余额</th>
        <th>性别</th>
        <th>电话</th>
        <th>地址</th>
        <th>用户级别</th>
        <th>操作</th>
    </tr>
    </thead>

    <tbody id="user_table"></tbody>

    <!-- ajax 加载 用户信息 -->
    <!--
    <tr>
        <td>admin</td>
        <td>1231</td>
        <td>智爷</td>
        <td>男</td>
        <td>42</td>
        <td>12312312</td>
        <td>湖北省武汉市洪山区珞喻路吴家湾武汉工程大学邮电与信息工程学院</td>
        <td>管理员</td>
    </tr>
    -->
</table>

<div class="row">
    <div class="col-md-3 text-center">
        <div>
            每页
            <select onchange="select_change('user')" id="showSize" class="input-sm">
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


<!-- 修改用户信息的模态框 -->
<div class="modal fade" id="updateUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">用户编号:<span id="user_id"></span></h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <label for="username">用户名</label>
                        <input type="text" class="form-control" id="username" onchange="jy_username_format(this);">
                        <span class="help-block"></span>
                    </div>
                    <div class="col-md-6">
                        <label for="password">密码</label>
                        <input type="text" class="form-control" id="password" onchange="jy_password(this);">
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label for="gender">性别</label>
                        <select class="form-control" id="gender">
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="role">用户级别</label>
                        <select class="form-control" id="role">
                            <option value="0">普通用户</option>
                            <option value="1">vip用户</option>
                            <option value="2">管理员用户</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label for="phone">电话</label>
                        <input type="text" class="form-control" id="phone" onchange="jy_phone(this)">
                        <span class="help-block"></span>
                    </div>
                    <div class="col-md-6">
                        <label for="money">余额</label>
                        <input type="text" class="form-control" id="money">
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="row">
                    <!-- 地址联动
                       <div class="col-md-6" style="margin:10px;">
                               <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr><td>
                              <select id="province" name="province" onchange="doProvAndCityRelation();">
              　　　　　　　　			<option id="choosePro" value="-1">请选择您所在省份</option>
              　　　　　　		</select>
                            </td></tr>
                            <tr><td><select id="citys" name="city" onchange="doCityAndCountyRelation();">
             　　　　　　　　		<option id='chooseCity' value='-1'>请选择您所在城市</option>
             　　　　　　		</select>
                            </td>
                            </tr>
                            <tr><td>
                             <select id="county" name="county">
             　　　　　　　　			<option id='chooseCounty' value='-1'>请选择您所在区/县</option>
            　　　　　　		</select>
                            </td></tr>
                            </table>

                       </div>
                       -->
                    <div class="col-md-12">
                        <label for="address">收货地址</label>
                        <input type="text" class="form-control" id="address">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="save_user();">Save
                    changes
                </button>
            </div>
        </div>
    </div>
</div>


<!-- 增加用户信息的模态框 -->

<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增用户</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <label for="username">用户名</label>
                        <input type="text" class="form-control" id="add_username" onchange="jy_username(this);">
                        <span class="help-block"></span>
                    </div>
                    <div class="col-md-6">
                        <label for="password">密码</label>
                        <input type="text" class="form-control" id="add_password" onchange="jy_password(this);">
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label for="gender">性别</label>
                        <select class="form-control" id="add_gender">
                            <option>男</option>
                            <option>女</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="role">用户级别</label>
                        <select class="form-control" id="add_role">
                            <option value="0">普通用户</option>
                            <option value="1">vip用户</option>
                            <option value="2">管理员用户</option>
                        </select>
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label for="phone">电话</label>
                        <input type="text" class="form-control" id="add_phone" onchange="jy_phone(this)">
                        <span class="help-block"></span>
                    </div>
                    <div class="col-md-6">
                        <label for="money">余额</label>
                        <input type="text" class="form-control" id="add_money" onchange="jy_money();">
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="row">
                    <!-- 地址联动
                       <div class="col-md-6" style="margin:10px;">
                               <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr><td>
                              <select id="province" name="province" onchange="doProvAndCityRelation();">
              　　　　　　　　			<option id="choosePro" value="-1">请选择您所在省份</option>
              　　　　　　		</select>
                            </td></tr>
                            <tr><td><select id="citys" name="city" onchange="doCityAndCountyRelation();">
             　　　　　　　　		<option id='chooseCity' value='-1'>请选择您所在城市</option>
             　　　　　　		</select>
                            </td>
                            </tr>
                            <tr><td>
                             <select id="county" name="county">
             　　　　　　　　			<option id='chooseCounty' value='-1'>请选择您所在区/县</option>
            　　　　　　		</select>
                            </td></tr>
                            </table>

                       </div>
                       -->
                    <div class="col-md-12">
                        <label for="address">收货地址</label>
                        <input type="text" class="form-control" id="add_address">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="add_user();">Save
                    changes
                </button>
            </div>
        </div>
    </div>
</div>
