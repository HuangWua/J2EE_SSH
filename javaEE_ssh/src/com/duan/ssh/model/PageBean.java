package com.duan.ssh.model;

import java.util.List;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.model
 * @ClassName: PageBean
 * @Author: duan
 * @Description: 分页
 * @Date: 2019-06-09 14:44
 * @Version: 1.0
 */

public class PageBean<T> {

    //已知数据
    private int pageNum;//当前页
    private int pageSize;//每页显示的数据条数
    private int totalRecord;//总共的记录条数，查询数据库得到的shuju

    //需要计算的来的
    private int totalPage;//总页数
    private int startIndex;//开始索引
    private int endIndex;//结束索引

    //将每页要显示的数据放在list集合中
    private List<T> list;

    //分页显示的页数，比如页面上显示1,2,3,4,5页。start就为1，end就是5
    private int start;
    private int end;

    public PageBean() {

    }

    //
    public PageBean(int pageNum, int pageSize, int totalRecord) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;

        //totalPage总页数
        if (totalRecord % pageSize == 0) {
            this.totalPage = totalRecord / pageSize;
        } else {
            this.totalPage = totalRecord / pageSize + 1;
        }

        //开始索引
        this.startIndex = (pageNum - 1) * pageSize;
        //结束索引
        this.endIndex = (pageNum * pageSize <= totalRecord ? pageNum * pageSize : totalRecord) - 1;

        this.start = 1;
        this.end = 5;

        if (totalPage <= 5) {
            this.end = this.totalPage;
        } else {
            this.start = pageNum - 2;
            this.end = pageNum + 2;

            if (start < 0) {
                //比如当前页是第1页，或者第2页，那么就不如和这个规则，
                this.start = 1;
                this.end = 5;
            }
            if (end > this.totalPage) {
                //比如当前页是倒数第2页或者最后一页，也同样不符合上面这个规则
                this.end = totalPage;
                this.start = end - 5;
            }
        }

    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
