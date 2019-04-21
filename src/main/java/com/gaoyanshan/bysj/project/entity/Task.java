package com.gaoyanshan.bysj.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gaoyanshan.bysj.project.dto.TaskUserInfo;
import com.gaoyanshan.bysj.project.dto.UserInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>类名: Task</pre>
 * <pre>描述: 任务信息表实体</pre>
 * <pre>日期: 19-3-24 下午10:50</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Entity
@Table(name = "task")
public class Task implements Serializable {

    private static final long serialVersionUID = 7237621223092167499L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String content;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST} ,fetch = FetchType.EAGER)
    private Project project;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date expectedTime;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date doneTime;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @Column
    private int deleted;

    @Column
    private int isDone;

    @Column
    private int taskLevel;

    @JsonIgnore
    @ManyToOne (cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private TaskType taskType;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "task",fetch = FetchType.EAGER)
    private List<UserTask> userTasks = new ArrayList<>();

    @JsonInclude
    @Transient
    private int type;


    public int getType() {
        return taskType.getId();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Date expectedTime) {
        this.expectedTime = expectedTime;
    }

    public Date getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    public int getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(int taskLevel) {
        this.taskLevel = taskLevel;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public List<UserTask> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(List<UserTask> userTasks) {
        this.userTasks = userTasks;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "isDone=" + isDone +
                '}';
    }
}
