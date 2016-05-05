package com.kaishengit.pojo;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;

public class Task implements Serializable {

    private Integer id;
    private String worktime;
    private String task;
    private Boolean done;
    private String donetime;
    private String createtime;
    private Integer userid;
    private Integer custid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getDonetime() {
        return donetime;
    }

    public void setDonetime(String donetime) {
        this.donetime = donetime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCustid() {
        return custid;
    }

    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    public String getMonthAndDay() {

        String[] chineseWeek = {"一","二","三","四","五","六","日"};

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dt = formatter.parseDateTime(getWorktime());
        int week = dt.getDayOfWeek();
        return dt.toString("MM月dd日 周" + chineseWeek[week-1]);
    }

    public boolean isOverTime() {
        if(getDone()) {
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dt = formatter.parseDateTime(getWorktime());
        return dt.isBeforeNow();
    }
}
