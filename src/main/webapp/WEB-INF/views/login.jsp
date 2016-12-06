<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>佰仟金融-权限管理系统-登录</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <%@ include file="common/common.jsp" %>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/login.css?rn=20161026">
</head>
<body>
<!-- 头部背景 -->
<div class="header-bg"></div>
<div class="header-panel">
    <h1 class="header-logo">
        <a href="${path}">权限管理系统</a>
    </h1>
</div>

<div class="form-login-panel">
    <h3 class="login-title">权限管理系统</h3>
    <form id="loginForm" action="${path}/login" class="form-login" method="post">
        <div class="form-line-user">
            <label for="userCode">用户名：</label>
            <input tabindex="1" autofocus="autofocus" type="text" class="easyui-textbox" name="userCode" id="userCode"
                   style="padding-left: 13px;width: 165px;height: 30px; line-height: 30px; border: 0 !important; background: transparent !important; "
                   data-options="prompt:'请输入用户名',iconCls:'icon-man', iconWidth:38, required:'true'">
        </div>
        <div class="form-line-pwd">
            <label for="password">密 码：</label>
            <input tabindex="2" class="easyui-textbox" name="password" id="password" type="password"
                   style="padding-left: 13px;width: 165px;height: 30px; line-height: 30px; border: 0 !important; background: transparent !important; "
                   data-options="prompt:'请输入密码',iconCls:'icon-lock',iconWidth:38,required:'true'">
        </div>
<!--         <div class="form-line-rememberMe"> -->
<!--         	<input type="checkbox" id="rememberMe" name="rememberMe" /> -->
<!--         	<label for="rememberMe" title="一天内不用重复登录">记住我</label> -->
<!--         </div> -->
        <div class="form-line-msg">
            <p style="color: white;" id="error"></p>
            <c:if test="${not empty msg}">
                <p style="color: white;">${msg}</p>
            </c:if>
            <!--[if lt IE 10]>
            <p style="color: white; width: 180px">当前系统仅支持IE10及以上的版本，其他浏览器版本暂不兼容！</p>
            <![endif]-->
        </div>
        <a tabindex="3" class="form-login-btn" href="javascript:submitForm();" id="submitBtn"></a>
        <div class="clear"></div>
    </form>
</div>
<script type="text/javascript">
    $(function () {
        // 登录
        $('#loginForm').form({
            url: "${path}/login",
            onSubmit: function () {
                return checkForm();
            },
            success: function (result) {
                result = $.parseJSON(result);
                debugger;
                if (result.success) {
                    window.location.href = "${path}/index";
                } else {
                    $("#error").text(result.msg);
                }
            }
        });
    });

    function submitForm() {
        $('#loginForm').submit();
    }

    /**
     * 检查表单
     */
    function checkForm() {
        var userCode = $.trim($("#userCode").val());
        var password = $.trim($("#password").val());
        $("#userCode").val(userCode);
        $("#password").val(password);

        if (userCode == "" || password == "") {
            $('.form-login').form('validate');
            return false;
        }
        if (!$('.form-login').form('validate')) {
            return false;
        }
        $('#submitBtn').attr("disabled", "disabled");
        return true;
    }

    $(document).keydown(function () {
        var event = arguments.callee.caller.arguments[0] || window.event;
        if (event.keyCode == 13) {
            $('#loginForm').submit();
        }
    });
</script>
</body>
</html>