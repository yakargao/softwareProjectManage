package com.gaoyanshan.bysj.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>类名: FileDTo</pre>
 * <pre>描述: 文件资源类数据传输对象</pre>
 * <pre>日期: 19-4-1 下午9:27</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class FileDTO implements Serializable{

    private static final long serialVersionUID = -4466001656220952551L;
    private int id;

    @JsonProperty("pId")
    private int projectId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;


    @JsonProperty("type")
    private int type;

    @JsonProperty("paths")
    private List<String> paths = new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", paths=" + paths +
                '}';
    }
}
