<%--
  Created by IntelliJ IDEA.
  User: lonecloud
  Date: 17/2/5
  Time: 下午10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录界面</title>
    <link href="//cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
</head>

<style>
    .col-center-block {
        float: none;
        display: block;
        margin-left: auto;
        margin-right: auto;
    }
    .logo{
        position: absolute;
        /*background-position: 0 -96px;*/
        width: 25px;
        height: 25px;
        font-size: 0;
        background-image: url(${pageContext.request.contextPath}/assets/logo.jpg);
    }
</style>
</head>

<body>
<div class="container">
    <div class="row myCenter">
        <div class="col-xs-6 col-md-4 col-center-block">
            <form class="form-signin" method="post" action="${pageContext.request.contextPath}/doLogin">
                <h2 class="form-signin-heading ">请登录${msg}</h2>
                <input type="hidden" name="location" id="location">
                <label for="username" class="sr-only">用户名</label>
                <input type="text" id="username" class="form-control" placeholder="用户名" required autofocus>
                <label for="inputPassword" class="sr-only">密码</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="密码" required>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me">
                        记住我 </label>
                </div>
                <%--tip 图片的分组--%>
                <div><label>选中图片中的"<span id="tip">${tip}</span>"</label></div>
                <div style="width: 400px;height: 200px;position:relative" id="png">
                    <img src="${pageContext.request.contextPath}/assets/tmp/${name}" alt="" id="images">
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="button" id="refresh">刷新图片</button>
                <button class="btn btn-lg btn-primary btn-block" type="submit" id="submitForm">登录</button>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    var index=0;
    $(function(){

        $("#png").click(function(event){
            event = event || window.event;
            var x = event.offsetX || event.originalEvent.layerX;
            var y = event.offsetY || event.originalEvent.layerY;
            var div=$("<div></div>");
            div.addClass("logo");
            div.css("left",(x-12)+"px");
            div.css("top",(y-12)+"px");
            div.attr("index",index++);
            div.click(function(){
                var nowindex=div.attr("index");
                $('.logo[index='+nowindex+']').remove();
            });
            /**
             * 阻止边框的影响
             * 处理不完美
             */
            if(x>13&&y>13&&x<400&&y<200){
//                var value=$("#location").val()+x+","+y+";"
//                $("#location").val(value);
                $("#png").append(div);
            }

        });
        $("#submitForm").click(function(){
            var childerns=$(".logo");
            var value="";
            for(var i=0;i<childerns.length;i++){
                value+=$(childerns[i]).css("left")+",";
                value+=$(childerns[i]).css("top")+";";
            }
            value=value.replace(/px/g,"");
            $("#location").val(value);
            $(".form-signin").submit();
        });
        //刷新图片方法
        $("#refresh").click(function(){
            $.ajax({
                url:"${pageContext.request.contextPath}/getPng",
                success:function(data){
                    var datas=data.split(",");
                    $("#images").attr("src","${pageContext.request.contextPath}/assets/tmp/"+datas[0]);
                    $("#tip").html(datas[1]);
                    $(".logo").each(function (i,e) {
                        //alert(e);
                        e.remove();
                    });
                },
                error:function(){
                    alert("请求失败");
                }
            });
        });

    });
</script>
</html>