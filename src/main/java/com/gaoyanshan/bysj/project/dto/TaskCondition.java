package com.gaoyanshan.bysj.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * <pre>类名: TaskCondition</pre>
 * <pre>描述: 任务分类查询DTO</pre>
 * <pre>日期: 19-3-29 下午9:12</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class TaskCondition {

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

    @Override
    public String toString() {
        return "TaskCondition{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", pId=" + pId +
                ", type=" + type +
                '}';
    }
}
