<!DOCTYPE html>
<html>
<head>
    <title>网盘首页 注册页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <!-- Custom Theme files -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
    <link href="css/snow.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>

    <script src="js/layer.js"></script>
    <script src="js/jquery-3.3.1.js"></script>


    <script type="text/javascript">
        $(function () {
            $("#uname").blur(function () {
                //获取输入框里边的值
                var uname = $(this).val()
                //获取span标签的对象
                var uspan = $("#uspan")
                //我们把这个值，传递给ajax做认证
                $.get("ajaxAuth", {uname: uname}, function (data) {
                    //判断用户名超过6位
                    if (uname.length >= 6) {
                        //用户符合规范  进行下一步判断  判断该用户是否存在
                        if (data.userExits) {
                            //用户存在
                            uspan.css("color", "red")
                            uspan.html("该用户已经存在")
                            /*  layer.tips('该用户名已经存在！', '#uname', {
                                  tips: [2, '#FF3030'],
                                  time: 3000
                              })*/
                        } else {
                            //证明用户不存在
                            uspan.css("color", "green")
                            uspan.html("该用户可以注册")
                        }
                    } else {
                        //用户的长度不足六位
                        uspan.css("color", "red")
                        uspan.html("账户名或密码不足6位")
                    }
                })

            })
        })
    </script>

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
        <h2 class="sub-agileits-w3layouts">注册</h2>
        <form action="register" method="post">
            <input type="text" name="username" placeholder="Username" required="" id="uname"/>
            <span id="uspan"></span>
            <input type="password" name="password" placeholder="Password" required=""/>
            <input type="email" name="email" placeholder="Email" required=""/>
            <div class="submit-w3l">
                <input type="submit" value="注册">
            </div>
        </form>
    </div>
</div>
</body>
</html>