package com.kaishengit.pojo;

import java.io.Serializable;

public class ProgressFile implements Serializable {

    private Integer id;
    private String filename;
    private String path;
    private String createtime;
    private Integer progressid;
    private Integer custid;
    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getProgressid() {
        return progressid;
    }

    public void setProgressid(Integer progressid) {
        this.progressid = progressid;
    }

    public Integer getCustid() {
        return custid;
    }

    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
