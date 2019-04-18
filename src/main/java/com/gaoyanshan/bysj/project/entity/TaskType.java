package com.gaoyanshan.bysj.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>类名: TaskType</pre>
 * <pre>描述: 任务类型实体</pre>
 * <pre>日期: 19-3-29 下午5:58</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Entity
@Table(name = "task_type")
public class TaskType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "taskType",fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

    @Column
    private int projectId;

    @Column
    private int deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
