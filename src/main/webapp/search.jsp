<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="org.fengyue.hadoopctweb.entity.SearchFile" %>
<%@ page import="java.util.ArrayList" %>

<%
    ArrayList<SearchFile> filelist = (ArrayList<SearchFile>) request.getAttribute("searchFiles");
%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>搜索结果</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>HDFD首页</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
                    <ul class="sub-menu">
                        <li><a href="readdir?url=/"><i class="icon-font">&#xe008;</i>个人文件</a></li>
                        <li><a href="#"><i class="icon-font">&#xe005;</i>群组文件</a></li>
                        <li><a href="#"><i class="icon-font">&#xe006;</i>我的收藏</a></li>
                        <li><a href="#"><i class="icon-font">&#xe004;</i>本地同步</a></li>
                        <li><a href="#"><i class="icon-font">&#xe012;</i>我的分享</a></li>
                        <li><a href="#"><i class="icon-font">&#xe052;</i>我的订阅</a></li>
                        <li><a href="#"><i class="icon-font">&#xe033;</i>标签分类</a></li>
                        <li><a href="design.html"><i class="icon-font">&#xe033;</i>回收站</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe018;</i>帮助中心</a>
                    <ul class="sub-menu">
                        <li><a href="system.html"><i class="icon-font">&#xe017;</i>系统设置</a></li>
                        <li><a href="system.html"><i class="icon-font">&#xe037;</i>清理缓存</a></li>

                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font">&#xe06b;</i><span>当前路径>><a href="readdir?url=/">个人文件</a></span>
                <%--                添加一个搜索框--%>
                <div class="container" style="float: right ; padding-right: 10px">
                    <form action="query">
                        <input type="text" name="searchName">
                        <input type="submit" value="搜索一下">
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
                        <th>文件路径</th>
                        <th>操作</th>
                    </tr>
                    <%
                        if (filelist != null) {
                            for (SearchFile file : filelist) {
                    %>
                    <tr>
                        <td class="tc"><input name="id[]" value="59" type="checkbox"></td>
                        <td>
                            <img src="images/icon_file.png"/><%=file.getFileName()%>
                        </td>
                        </td>
                        <td><%=file.getTimes()%>
                        </td>
                        <td title="大小"><%=file.getSize()%>
                        </td>
                        <td><%=file.getPathName()%>
                        </td>
                        <td><a class="link-update"
                               href="download?fileName=<%=file.getPathName().toString() %>">下载</a>
                            &nbsp;
                            <a class="link-update"
                               href="download?fileName=<%=file.getPathName().toString() %>">下载</a>
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