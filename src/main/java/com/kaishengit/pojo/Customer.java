package com.kaishengit.pojo;

import java.io.Serializable;

public class Customer implements Serializable {

    public static final String PROGRESS_NEW = "无";
    public static final String PROGRESS_PRICE = "报价";
    public static final String PROGRESS_INTERTION = "意向";
    public static final String PROGRESS_SUCCESS = "成交";
    public static final String PROGRESS_FAIL = "暂时搁置";
    public static final String PROGRESS_VISIT = "初访";



    private Integer id;
    private String custname;
    private String contact;
    private String tel;
    private String address;
    private String email;
    private String wechar;
    private String qq;
    private String mark;
    private Integer userid;
    private String progress;
    private String progresstime;
    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechar() {
        return wechar;
    }

    public void setWechar(String wechar) {
        this.wechar = wechar;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getProgresstime() {
        return progresstime;
    }

    public void setProgresstime(String progresstime) {
        this.progresstime = progresstime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
