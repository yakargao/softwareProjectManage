package com.gaoyanshan.bysj.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>类名: Project</pre>
 * <pre>描述: 项目表</pre>
 * <pre>日期: 19-3-21 下午6:26</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@Entity
@Table(name = "project")
public class Project implements Serializable{

    private static final long serialVersionUID = 469157670714554315L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String detail;


    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date createTime;

    @Column
    private String createUserEmail;

    @Column
    private String createUserName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "project",fetch = FetchType.LAZY)
    private List<UserProject> userProjects = new ArrayList<>();

    @Column
    private int deleted;

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserEmail() {
        return createUserEmail;
    }

    public void setCreateUserEmail(String createUserEmail) {
        this.createUserEmail = createUserEmail;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public List<UserProject> getUserProjects() {
        return userProjects;
    }

    public void setUserProjects(List<UserProject> userProjects) {
        this.userProjects = userProjects;
    }

}
