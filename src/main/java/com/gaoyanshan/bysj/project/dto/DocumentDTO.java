package com.gaoyanshan.bysj.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>类名: DocumentDTO</pre>
 * <pre>描述: 文档类数据传输对象</pre>
 * <pre>日期: 19-3-31 下午7:34</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class DocumentDTO implements Serializable{

    private static final long serialVersionUID = 3741268923726734320L;

    private int id;

    @JsonProperty("pId")
    private int  projectId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String summary;

    @JsonProperty("content")
    private String content;

    @JsonProperty("type")
    private int type;

    @JsonProperty("createUser")
    private UserInfo userInfo;

    @JsonProperty("createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
