package com.gaoyanshan.bysj.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gaoyanshan.bysj.project.dto.UserInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <pre>类名: API</pre>
 * <pre>描述: api实体类</pre>
 * <pre>日期: 19-4-2 下午3:26</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@Entity
@Table(name = "api")
public class API implements Serializable{

    private static final long serialVersionUID = -4835966004790138491L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonIgnore
    @ManyToOne (cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Project project;

    @JsonIgnore
    @ManyToOne (cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private User user;

    @Column
    private String content;

    @Column
    private String summary;

    @JsonProperty("pId")
    @JsonInclude
    @Transient
    private int projectId;

    @JsonInclude
    @Transient
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo(this.getUser().getId(),
                this.getUser().getEmail(),
                this.getUser().getName());
        return userInfo;
    }

    public int getProjectId() {
        return this.getProject().getId();
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
