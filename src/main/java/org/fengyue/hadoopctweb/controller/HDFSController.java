package org.fengyue.hadoopctweb.controller;

import org.fengyue.hadoopctweb.dao.HDFSDao;
import org.fengyue.hadoopctweb.entity.SearchFile;
import org.apache.hadoop.fs.FileStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class HDFSController {

    /**
     * 跳转请求  readdir
     */
    @RequestMapping("/readdir")
    public String getReaddir(@RequestParam String url, Model model) {
        FileStatus[] fileStatuses = null;
        //判断
        if (url.equals("/")) {
            //hdfs://8.142.31.231:9000/rt
            fileStatuses = HDFSDao.listStatus();
        } else {
            //currenturl  全称 hdfs://8.142.31.231:9000/rt/个人资料/视频
            //currenturl1  不是全称   个人资料/视频
            //hdfs://8.142.31.231:9000/rt/个人资料/视频
            fileStatuses = HDFSDao.listStatus(url);
        }
        model.addAttribute("filelist", fileStatuses);
        model.addAttribute("currenturl", url);
        model.addAttribute("currenturl1", url.replace("hdfs://hadoop001:8020/", ""));
        return "index.jsp";
    }


    /**
     * 创建目录的方法
     * createdir?newdirname="+dirname
     */
    @RequestMapping("/createdir")
    public String getCreateDir(@RequestParam String newdirname) {
        if (newdirname.contains("hdfs://hadoop001:8020/")) {
            newdirname = newdirname.replace("hdfs://hadoop001:8020/", "");
        }
        String hdfsPath = HDFSDao.getHdfsPath();
        //创建目录   a/b/c
        HDFSDao.mkdir(newdirname);
        //
        String newPath = hdfsPath + newdirname;
        //返回的问题    返回上一级目录
        String substring = newPath.substring(0, newPath.lastIndexOf("/"));
        //url中文路径乱码
        String encode = null;
        try {
            encode = URLEncoder.encode(substring, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(encode);
        return "redirect:/readdir?url=" + encode;
    }

    /**
     * 删除目录或文件的方法
     * delete?filePath=hdfs://hadoop001:8020/rt/a
     */
    @RequestMapping("/delete")
    public String getDelete(@RequestParam String filePath) {
        HDFSDao.delete(filePath);
        //返回的问题    返回上一级目录
        String substring = filePath.substring(0, filePath.lastIndexOf("/"));
        //url中文路径乱码
        String encode = null;
        try {
            encode = URLEncoder.encode(substring, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(filePath);
        System.out.println(encode);
        return "redirect:/readdir?url=" + encode;
    }

    /**
     * 修改文件或目录
     * alter?oldPath=hdfs://hadoop001:8020/rt/个人资料/c
     * newPath=hdfs://hadoop001:8020/rt/个人资料/c1
     */
    @RequestMapping("/alter")
    public String getAlter(@RequestParam String oldPath, @RequestParam String newPath) {
        //调用修改的方法
        HDFSDao.rename(oldPath, newPath);
        String substring = newPath.substring(0, newPath.lastIndexOf("/"));
        //url中文路径乱码
        String encode = null;
        try {
            encode = URLEncoder.encode(substring, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:/readdir?url=" + encode;
    }

    /**
     * 检索的功能
     * query?search=txt
     */
    @RequestMapping("/query")
    public String getQuery(HttpServletRequest request, Model model) {
        String searchName = request.getParameter("searchName");
        //获取到所有的文件  包括递归
        List<SearchFile> searchFiles = HDFSDao.listAll(searchName);
        //
        model.addAttribute("searchFiles", searchFiles);
        return "search.jsp";
    }

}
