package org.fengyue.hadoopctweb.dao;

import org.fengyue.hadoopctweb.entity.SearchFile;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HDFSDao {

    static FileSystem fileSystem;

    static String hdfsPath = "hdfs://hadoop101:8020/";

    //先获取hadoop的连接对象
    static {
        //final URI uri, final Configuration conf,
        //        final String user  hdfs://hadoop01:9000
        Configuration conf = new Configuration();
        conf.set("dfs.replication", "1");
        conf.set("dfs.client.use.datanode.hostname", "true");
        try {
            fileSystem = FileSystem.get(URI.create("hdfs://hadoop101:8020/"), conf, "fy");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取跟路径的方法
     */
    public static String getHdfsPath() {
        return hdfsPath;
    }

    /**
     * 查询当前路径下的所有文件或者目录
     * listStatus
     *
     * @param
     */
    public static FileStatus[] listStatus() {
        FileStatus[] fileStatuses = null;
        try {
            fileStatuses = fileSystem.listStatus(new Path(hdfsPath));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileStatuses;
    }

    /**
     * 查询当前路径下的所有文件或者目录
     * listStatus
     *
     * @param
     */
    public static FileStatus[] listStatus(String url) {
        FileStatus[] fileStatuses = null;
        try {
            fileStatuses = fileSystem.listStatus(new Path(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileStatuses;
    }


    /**
     * 增加目录的方法
     */
    public static void mkdir(String newdirname) {
        //
        try {
            fileSystem.mkdirs(new Path(hdfsPath + newdirname));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除目录或文件的方法
     */
    public static void delete(String filePath) {
        //
        try {
            fileSystem.delete(new Path(filePath), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改文件或目录
     */
    public static void rename(String oldPath, String newPath) {
        //
        try {
            fileSystem.rename(new Path(oldPath), new Path(newPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传
     */
   /* public static void upload(String srcPath,String dstPath){
        //srcPath:本地windows的文件全路径
        //dstPath：hdfs集群的目标路径（这个已经有了）
        try {
           fileSystem.copyFromLocalFile(new Path(srcPath),new Path(dstPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 打开hdfs的输出流
     */
    public static OutputStream getOutputStream(String url) {
        FSDataOutputStream fsDataOutputStream = null;
        try {
            fsDataOutputStream = fileSystem.create(new Path(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fsDataOutputStream;
    }

    /**
     * 打开hdfs的输入流
     */
    public static InputStream getInputStream(String fileName) {
        FSDataInputStream open = null;
        try {
            open = fileSystem.open(new Path(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return open;
    }

    /**
     * 查询所有路径下的文件  （包括递归）
     * listStatus
     *
     * @param
     */
    public static List<SearchFile> listAll(String searchName) {

        ArrayList<SearchFile> list = new ArrayList<>();
        try {
            RemoteIterator<LocatedFileStatus> iterator = fileSystem.listFiles(new Path(hdfsPath), true);
            //从迭代器中拿数据
            while (iterator.hasNext()) {
                LocatedFileStatus next = iterator.next();
                //判断
                if (next.getPath().getName().contains(searchName)) {
                    //证明找到了
                    //创建bean对象
                    SearchFile searchFile = new SearchFile(next.getPath().getName(),
                            new Date(next.getModificationTime()).toString(),
                            next.getLen(), next.getPath().toString());
                    //添加到list集合中
                    list.add(searchFile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
