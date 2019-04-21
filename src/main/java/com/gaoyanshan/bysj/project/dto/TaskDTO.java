package com.gaoyanshan.bysj.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.internal.dynalink.linker.LinkerServices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>类名: TaskDTO</pre>
 * <pre>描述: task相关的数据传输类</pre>
 * <pre>日期: 19-3-25 下午8:37</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class TaskDTO implements Serializable{

    private static final long serialVersionUID = -8065495955326228491L;

    private int id;

    private String title;

    @JsonProperty(value = "pId")
    private int projectId;

    @JsonProperty(value = "content",defaultValue = " ")
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date expectedTime;

    private int taskLevel;

    @JsonProperty(value = "type")
    private int taskType;

    private int isDone;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Date expectedTime) {
        this.expectedTime = expectedTime;
    }

    public int getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(int taskLevel) {
        this.taskLevel = taskLevel;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }


    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", projectId=" + projectId +
                ", content='" + content + '\'' +
                ", expectedTime=" + expectedTime +
                ", taskLevel=" + taskLevel +
                ", taskType=" + taskType +
                ", isDone=" + isDone +
                '}';
    }
}
