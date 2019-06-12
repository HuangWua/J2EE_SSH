<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: duan
  Date: 2019-05-29
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="resource/css/bootstrap.css">
    <%--<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>--%>
    <script src="resource/js/jquery-3.4.1.min.js"></script>
    <script src="resource/js/bootstrap.js"></script>
    <title>Title</title>
</head>
<body>
注册SUCCESS.....<br>
username:${user.username}<br>
password:${user.password}
<button id="jsonTest">测试</button>
<form id="form1" name="upload" action="/javaEE_ssh/user/uploadFile.action" method="post" enctype="multipart/form-data">
    <input type="file" name="upload">
    <input type="text" name="name">
    <input type="button" onclick="uploadfile()" value="上传">
</form>


</body>

<script type="application/javascript">
    function uploadfile() {
        var upload = new FormData($("#form1")[0]);
        $.ajax({
            url: '/javaEE_ssh/user/uploadFile.action',
            type: "post",
            data: upload,
            dataType: "JSON",
            cache: false,
            processData: false,
            contentType: false,
            success:function (result) {
                
            }
        });
    }
</script>

</html>
