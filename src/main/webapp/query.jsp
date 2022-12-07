<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="org.apache.hadoop.fs.FileStatus" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    FileStatus[] filelist = (FileStatus[]) request.getAttribute("filelist");
    String currenturl = "";
    String currenturl1 = "";
    if (request.getAttribute("currenturl") != null) {
        currenturl = request.getAttribute("currenturl").toString();
        currenturl1 = request.getAttribute("currenturl1").toString();
    }

%>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>网盘首页</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="./js/jquery-2.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="./bootstrap/js/bootstrap.js"></script>

<script>

    // 声明方法
    function queryData() {
        window.location.href = "/view?tel=" + $("#tel").val() + "&calltime=" + $("#calltime").val();
    }
</script>

<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>网盘首页</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
                    <ul class="sub-menu">
                        <li><a href="readdir?url=/"><i class="icon-font">&#xe008;</i>个人文件</a></li>
                        <li><a href="query_clog"><i class="icon-font">&#xe005;</i>号码查询</a></li>
                        <li><a href="#"><i class="icon-font">&#xe006;</i>我的收藏</a></li>
                        <li><a href="#"><i class="icon-font">&#xe004;</i>本地同步</a></li>
                        <li><a href="#"><i class="icon-font">&#xe012;</i>我的分享</a></li>
                        <li><a href="#"><i class="icon-font">&#xe052;</i>我的订阅</a></li>
                        <li><a href="#"><i class="icon-font">&#xe033;</i>标签分类</a></li>
                        <li><a href="#"><i class="icon-font">&#xe033;</i>回收站</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe018;</i>帮助中心</a>
                    <ul class="sub-menu">
                        <li><a href="#"><i class="icon-font">&#xe017;</i>系统设置</a></li>
                        <li><a href="#"><i class="icon-font">&#xe037;</i>清理缓存</a></li>

                    </ul>
                </li>
            </ul>
        </div>
    </div>


    <!--/sidebar-->
    <div class="main-wrap">
        <div class="container" style="float: right ; padding-right: 10px;margin-top: 7px">
            <form action="query">
                <input type="text" name="searchName">
                <input type="submit" value="搜索一下">
            </form>
        </div>
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font">&#xe06b;</i><span>当前路径>><a
                    href="readdir?url=/">个人文件</a> </span>

                <%
                    // hdfs:///rt/11
                    String[] splits = currenturl.split("/");
                    //判断
                    if (splits.length > 3) {
                        String path = splits[0] + "/" + splits[1] + "/" + splits[2] + "/" + splits[3];
                        int len = 0;
                        //for循环
                        for (int i = 4; i < splits.length; i++) {
                            len = path.length();
                            path = path + "/" + splits[i];
                            String path1 = path.substring(len, path.length());
                %>
                <a href="readdir?url=<%=path%>">&nbsp;&nbsp;&nbsp;<%=path1 %>
                </a>
                <%}%>
                <% } else {%>
                <a href="readdir?url=<%=currenturl%>"><%=currenturl1 %>
                </a>
                <% }%>
            </div>
        </div>
        <div class="result-wrap">
            <div class="result-title">
                <h1>快捷操作</h1>
            </div>
            <div class="result-content">
                <div class="short-wrap">
                    <form method="post" action="/upload?url=<%=currenturl %>" enctype="multipart/form-data" id="myfrm">
                        <a href="javascript:;" class="a-upload"><input type="file" name="file" id="file"
                                                                       onchange="uploadfile()"><i class="icon-font">&#xe005;</i>上传文件</a>
                        <script language="JavaScript">
                            function uploadfile() {
                                document.getElementById("myfrm").submit();
                            }
                        </script>
                        <a href="javascript:createdir()" class="a-upload"><input type="button" name="" id="createdir"><i
                                class="icon-font">&#xe005;</i>新建文件夹</a>
                        <script language="JavaScript">
                            function createdir() {
                                var dirname = prompt('请输入文件夹名称', '新建文件夹1');
                                if (dirname.length > 0) {
                                    location.href = "createdir?newdirname=<%=currenturl1 %>/" + dirname;
                                }
                            }
                        </script>
                    </form>
                </div>
            </div>
        </div>
        <div class="result-wrap">
            <div class="result-title">
                <h1>通话时间查询</h1>
            </div>
            <div class="result-content">
                <div>
                    <form>
                        <div class="form-group">
                            <label for="tel">电话号码：</label>
                            <input type="text" class="form-control" id="tel" placeholder="请输入电话号码">
                        </div>
                        <div class="form-group">
                            <label for="calltime">查询时间：</label>
                            <input type="date" class="form-control" id="calltime"/>
                        </div>
                        <button type="button" class="btn btn-default" onclick="queryData()">查询</button>
                    </form>
                </div>

            </div>
        </div>
    </div>
    <!--/main-->

</div>
</body>
</html>