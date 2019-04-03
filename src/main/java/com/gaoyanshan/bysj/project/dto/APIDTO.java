package com.gaoyanshan.bysj.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * <pre>类名: APIDTO</pre>
 * <pre>描述: </pre>
 * <pre>日期: 19-4-2 下午3:39</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class APIDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonProperty("pId")
    private int projectId;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("content")
    private String content;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "APIDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", projectId=" + projectId +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
