package com.gaoyanshan.bysj.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * <pre>类名: GithubLog</pre>
 * <pre>描述: github提交记录跟踪表</pre>
 * <pre>日期: 19-4-5 上午11:19</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Entity
@Table(name = "github_log")
public class GithubLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column
    private String sha;

    @Column
    private int projectId;

    @Column
    private int userId;

    @Column
    private String commitUserName;

    @Column
    private String commitMessage;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date commmitDate;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCommitUserName() {
        return commitUserName;
    }

    public void setCommitUserName(String commitUserName) {
        this.commitUserName = commitUserName;
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }

    public Date getCommmitDate() {
        return commmitDate;
    }

    public void setCommmitDate(Date commmitDate) {
        this.commmitDate = commmitDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GithubLog{" +
                "id=" + id +
                ", sha='" + sha + '\'' +
                ", projectId=" + projectId +
                ", userId=" + userId +
                ", commitUserName='" + commitUserName + '\'' +
                ", commitMessage='" + commitMessage + '\'' +
                ", commmitDate=" + commmitDate +
                '}';
    }
}
