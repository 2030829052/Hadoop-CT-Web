package org.fengyue.hadoopctweb.controller;

import org.fengyue.hadoopctweb.dao.HDFSDao;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class UpAndDown {

    /**
     * 上传文件都hdfs集群
     * upload?url=hdfs://8.142.31.231:9000/rt/个人资料
     */
   /* @RequestMapping("/upload")
    public String getUpload(@RequestParam String url, MultipartFile file, HttpSession session){
        //hdfs://8.142.31.231:9000/rt/个人资料
        //需要搞一个统一的目录   把需要上传的文件统一维护一下
        String realPath = session.getServletContext().getRealPath("upload");
        //没有这个的时候，需要创建出来
        File realPath_file = new File(realPath);
        if(!realPath_file.exists()){
           //不存在就创建出来
            realPath_file.mkdirs();
        }
        //想办法获取到windows本地的文件全路径 ？？？
        //获取到上传文件的名字   javaSE经典考试题.docx
        String filename = file.getOriginalFilename();
        //得想办法   解决文件全路径的文件
        //transferTo  可以把要上传的文件缓冲到本地统一的一个路径下
        try {
            file.transferTo(new File(realPath+"/"+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encode = null;
        try {
            encode = URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //调用
        HDFSDao.upload(realPath+"/"+filename,url);
        //删除目录
        try {
            FileUtils.deleteDirectory(realPath_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/readdir?url="+encode;
    }*/
    @RequestMapping("/upload")
    public String getUpload(@RequestParam String url, MultipartFile file) throws Exception {
        //InputStream in, OutputStream out, Configuration conf, boolean close
        //MultipartFile提供了一个方法，可以直接获取到上传文件的输出流
        InputStream is = file.getInputStream();
        //获取到hdfs集群的输出流  hdfs://8.142.31.231:9000/rt/个人资料/javaSE经典考试题.docx
        OutputStream os = HDFSDao.getOutputStream(url + "/" + file.getOriginalFilename());
        //上传文件到hdfs
        IOUtils.copyBytes(is, os, new Configuration(), true);
        String encode = null;
        try {
            encode = URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:/readdir?url=" + encode;
    }

    /**
     * 文件的下载
     * download?fileName=hdfs://8.142.31.231:9000/rt/个人资料/小说/坏蛋是怎样练成的.txt
     */
    @RequestMapping("/download")
    public void getDownload(@RequestParam String fileName, HttpServletResponse response) throws Exception {
        String substring = fileName.substring(fileName.lastIndexOf("/") + 1);
        //hdfs://8.142.31.231:9000/rt/个人资料/小说/坏蛋是怎样练成的.txt
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(substring, "UTF-8"));
        //1.打开输入流   （hdfs集群上的数据）
        InputStream is = HDFSDao.getInputStream(fileName);
        //2.获取输出流  （当前浏览器）
        ServletOutputStream os = response.getOutputStream();
        //3.输出数据
        int a = 0;
        byte[] bytes = new byte[2048];
        //4.io流的读写
        while ((a = is.read(bytes)) != -1) {
            os.write(bytes, 0, bytes.length);
            os.flush();
        }
        //关流
        is.close();
        os.close();
    }
}
