package com.gaoyanshan.bysj.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * <pre>类名: ApiCondition</pre>
 * <pre>描述: </pre>
 * <pre>日期: 19-4-2 下午3:43</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class ApiCondition {


    @JsonProperty("key")
    private String key;

    @JsonProperty("page")
    private int page;

    @JsonProperty("limit")
    private int size;

    @JsonProperty("pId")
    private int pId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }
}
