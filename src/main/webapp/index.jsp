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
                        <li><a href="query_clog"><i class="icon-font">&#xe005;</i>群组文件</a></li>
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
                <h1>文件基本信息</h1>
            </div>
            <div class="result-content">
                <table class="result-tab" width="100%">
                    <tr>
                        <th class="tc" width="5%"><input class="allChoose" name="" type="checkbox"></th>
                        <th>文件名</th>
                        <th>修改时间</th>
                        <th>大小</th>
                        <th>备注信息</th>
                        <th>操作</th>
                    </tr>
                    <%
                        if (filelist != null) {
                            for (FileStatus fs : filelist) {

                    %>
                    <tr>
                        <td class="tc"><input name="id[]" value="59" type="checkbox"></td>
                        <td>
                            <% if (fs.isDirectory() == true) { %>
                            <a target="_self" href="readdir?url=<%=fs.getPath() %>" title="进入目录"><img
                                    src="images/icon_dir.png"/><%=fs.getPath().getName() %>
                            </a>
                            <% } else {%>
                            <img src="images/icon_file.png"/><%=fs.getPath().getName() %>
                        </td>
                        <% }%>
                        </td>
                        <td><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fs.getModificationTime()) %>
                        </td>
                        <td title="大小"><%=(fs.isDirectory() == false) ? fs.getLen() / 1024 : " "   %>KB <a
                                target="_blank" href="#" title=""></a></td>
                        <td>暂无</td>
                        <td><% if (fs.isDirectory() == false) {%> <a class="link-update"
                                                                     href="download?fileName=<%=fs.getPath().toString() %>">下载</a>
                            &nbsp; &nbsp; <% } %>
                            <a class="link-del" href="delete?filePath=<%=fs.getPath().toString()%>"
                               onclick="javascript:return confirm('确认删除：<%=fs.getPath().toString()%>文件吗？');">删除</a>

                            <a href="#" onclick="javascript:{
                                    var dirname=prompt('请输入修改后的名称', '修改文件或目录');
                                    if(dirname != null){
                                    //获取原来的文件名字   新名字注意拼接的问题
                                    location.href='alter?oldPath=<%=fs.getPath().toString()%>'+'&newPath=<%=currenturl%>/'+dirname
                                    }else{
                                    location.href='#'
                                    }
                                    }">重命名</a>
                        </td>
                    </tr>
                    <% }
                    }
                    %>

                </table>
                <div class="list-page">1/1 页</div>
            </div>
        </div>
    </div>
    <!--/main-->

</div>
</body>
</html>