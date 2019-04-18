package com.gaoyanshan.bysj.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * <pre>类名: ProjectDTO</pre>
 * <pre>描述: Project项目的数据传输对象</pre>
 * <pre>日期: 19-3-21 下午9:02</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class ProjectDTO implements Serializable {

    private static final long serialVersionUID = 1908999818738731652L;
    private int id;
    private String title;
    private String detail;
    private int progress;

    @JsonProperty("githubUsername")
    private String githubUsername;

    @JsonProperty("githubPassword")
    private String githubPassword;

    @JsonProperty("githubUrl")
    private String githubUrl;

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

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getGithubUsername() {
        return githubUsername;
    }

    public void setGithubUsername(String githubUsername) {
        this.githubUsername = githubUsername;
    }

    public String getGithubPassword() {
        return githubPassword;
    }

    public void setGithubPassword(String githubPassword) {
        this.githubPassword = githubPassword;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", progress=" + progress +
                ", githubUsername='" + githubUsername + '\'' +
                ", githubPassword='" + githubPassword + '\'' +
                ", githubUrl='" + githubUrl + '\'' +
                '}';
    }
}
