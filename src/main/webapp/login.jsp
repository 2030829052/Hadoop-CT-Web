<!DOCTYPE html>
<html>
<head>
    <title>风月网盘 登录界面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <!-- Custom Theme files -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
    <link href="css/snow.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- //Custom Theme files -->


</head>
<body>
<div class="snow-container">
    <div class="snow foreground"></div>
    <div class="snow foreground layered"></div>
    <div class="snow middleground"></div>
    <div class="snow middleground layered"></div>
    <div class="snow background"></div>
    <div class="snow background layered"></div>
</div>

<div class="top-buttons-agileinfo">
    <a href="login.jsp" class="active">登录</a><a href="register.jsp">注册</a>
</div>
<h1>风月网盘</h1>
<div class="main-agileits">
    <!--form-stars-here-->
    <div class="form-w3-agile">
        <h2 class="sub-agileits-w3layouts">登录系统</h2>
        <form action="login" method="post">
            <input type="text" name="Username" placeholder="Username" required=""/>
            <input type="password" name="Password" placeholder="Password" required=""/>
            <a href="pass1" class="forgot-w3layouts">忘记密码 ?</a>
            <div class="submit-w3l">
                <input type="submit" value="登录">
            </div>
            <p class="p-bottom-w3ls"><a href="register.jsp">这里注册</a>如果你还没有一个账号.</p>
        </form>
    </div>
</div>
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
</body>
</html>
