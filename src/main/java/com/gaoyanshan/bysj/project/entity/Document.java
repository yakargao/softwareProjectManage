package com.gaoyanshan.bysj.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gaoyanshan.bysj.project.dto.UserInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <pre>类名: Document</pre>
 * <pre>描述: 文档类实体</pre>
 * <pre>日期: 19-3-31 下午7:20</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Entity
@Table(name = "document")
public class Document implements Serializable{

    private static final long serialVersionUID = 4837142918298740497L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int  id ;

    @JsonIgnore
    @ManyToOne (cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Project project;

    @Column
    private String title;

    @Column
    private String summary;

    @Column
    private String content;

    @JsonIgnore
    @ManyToOne (cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private User createUser;

    @JsonInclude
    @Transient
    private UserInfo userInfo;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column
    private int documentType;

    public UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo(this.getCreateUser().getId(),
                                        this.getCreateUser().getEmail(),
                                        this.getCreateUser().getName());
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Column
    private int deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getDocumentType() {
        return documentType;
    }

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
