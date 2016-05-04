package com.kaishengit.util;

import java.util.List;

public class Page<T> {

    private List<T> items;
    private int pageNo; //当前页码
    private int start; //起始行数
    private int size; //每页显示的数量
    private int totalPages; //总页数
    private int totalCount; //总条数

    /**
     *
     * @param pageNo 当前页号
     * @param count 总记录数
     * @param size 每页显示的数量
     */
    public Page(String pageNo,int count,int size) {
        this.totalCount = count;
        this.size = size;
        //给当前页码一个正确的值
        if(pageNo != null && pageNo.matches("\\d+")) {
            this.pageNo = Integer.parseInt(pageNo);
            if(this.pageNo <= 0) {
                this.pageNo = 1;
            }
        } else {
            this.pageNo = 1;
        }

        //计算总页数
        this.totalPages = count / size;
        if(count % size != 0) {
            this.totalPages++;
        }

        //限制当前页码不得大于总页数
        if(this.pageNo > this.totalPages && this.totalPages > 0) {
            this.pageNo = this.totalPages;
        }

        //计算起始行数
        this.start = (this.pageNo - 1) * size;
    }

    /**
     * 默认每页显示5条记录
     * @param pageNo 当前页号
     * @param count 总记录数
     */
    public Page(String pageNo,int count) {
        this(pageNo,count,5);
    }



    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getStart() {
        return start;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
