<!DOCTYPE html>
<html>
<head>
    <title>密码找回页面 </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <!-- Custom Theme files -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
    <link href="css/snow.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>

    <script src="js/layer.js"></script>
    <script src="js/jquery-3.3.1.js"></script>

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
    <a href="login.jsp">Sign In</a><a href="register.jsp" class="active">Sign Up</a>
</div>
<h1>风月网盘</h1>
<div class="main-agileits">
    <!--form-stars-here-->
    <div class="form-w3-agile">
        <h2 class="sub-agileits-w3layouts">密码找回</h2>
        <form action="emailBack" method="post">
            <input type="email" name="email" placeholder="Email" required=""/>
            <div class="submit-w3l">
                <input type="submit" value="提交">
            </div>
        </form>
    </div>
</div>
<!--//form-ends-here-->
</body>
</html>