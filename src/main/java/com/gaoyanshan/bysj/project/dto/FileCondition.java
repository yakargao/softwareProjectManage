package com.gaoyanshan.bysj.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * <pre>类名: FileCondition</pre>
 * <pre>描述: </pre>
 * <pre>日期: 19-4-2 上午12:11</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class FileCondition {
    @JsonProperty(value = "page",defaultValue = "1")
    public int page;

    @JsonProperty(value = "size",defaultValue = "10")
    public int size;

    @JsonProperty(value = "startTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;


    @JsonProperty("endTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @JsonProperty("pid")
    private int pId;

    @JsonProperty("type")
    private int type;

    @JsonProperty("userId")
    private int UserId;

    @JsonProperty("key")
    private String key;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
