package org.fengyue.hadoopctweb.entity;


public class SearchFile {
    private String fileName;
    private String times;
    private long size;
    private String pathName;

    public SearchFile() {
    }

    public SearchFile(String fileName, String times, long size, String pathName) {
        this.fileName = fileName;
        this.times = times;
        this.size = size;
        this.pathName = pathName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public long getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }
}
