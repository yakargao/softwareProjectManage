package com.gaoyanshan.bysj.project.dto;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>类名: MyPage</pre>
 * <pre>描述: 分页查询适用</pre>
 * <pre>日期: 19-3-28 下午6:59</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class MyPage<T> implements Serializable {


    private static final long serialVersionUID = -8583682877447612451L;
    private List<T> records;

    private long totalNum;

    private long pageNum;

    public MyPage(List<T> records, long totalNum,long pageNum) {
        this.records = records;
        this.totalNum = totalNum;
        this.pageNum = pageNum;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public static MyPage transformPage(Page page){
        return new MyPage(page.getContent(),page.getTotalElements(),page.getTotalPages());
    }
}