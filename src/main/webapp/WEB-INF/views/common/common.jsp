<%-- 公共文件引入jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="taglibs.jsp" %>

<link rel="stylesheet" type="text/css" href="${path}/static/js/lib/jquery-easyui-1.4.5/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="${path}/static/js/lib/jquery-easyui-1.4.5/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${path}/static/js/lib/jquery-easyui-1.4.5/themes/color.css">
<link rel="stylesheet" type="text/css" href="${path}/static/css/base.css">

<script type="text/javascript" src="${path}/static/js/lib/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${path}/static/js/lib/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/static/js/lib/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    //工程跟路径
    var CONTEXT_PATH = '${path}';
    $(function () {
        $.ajaxSetup({
            cache: false, //关闭AJAX缓存
            complete: function (xhr, status) {
                var sessionstatus = xhr.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus，
                if (sessionstatus == "timeout") { //如果超时就处理 ，指定要跳转的页面
                    alert("您的登录已过期，请重新登录！");
                    window.top.location.replace("${path}");
                }
            }
        });
    });
    /*
     *处理图片加载失败，采用默认图片
     */
    function checkLoadImg(tagetElement) {
        if (tagetElement) {
            $("#" + tagetElement).find("img").attr("onerror", "this.src='" + CONTEXT_PATH + "/static/images/error.png'");
        }
    }
</script>