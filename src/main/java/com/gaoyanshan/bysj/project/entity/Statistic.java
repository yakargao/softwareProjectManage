package com.gaoyanshan.bysj.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <pre>类名: Statistic</pre>
 * <pre>描述: 用户信息统计表</pre>
 * <pre>日期: 19-4-3 下午4:34</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Entity
@Table(name ="statistic")
public class Statistic implements Serializable{

    private static final long serialVersionUID = -4551269649213633090L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @Column
    private int userId;

    @Column
    private int projectId;

    @Column
    private int createTaskNum;

    @Column
    private int doneTaskNum;

    @Column
    private int uploadFileNum;

    @Column
    private int createDocNum;

    @Column
    private int createApiNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getCreateTaskNum() {
        return createTaskNum;
    }

    public void setCreateTaskNum(int createTaskNum) {
        this.createTaskNum = createTaskNum;
    }

    public int getDoneTaskNum() {
        return doneTaskNum;
    }

    public void setDoneTaskNum(int doneTaskNum) {
        this.doneTaskNum = doneTaskNum;
    }

    public int getUploadFileNum() {
        return uploadFileNum;
    }

    public void setUploadFileNum(int uploadFileNum) {
        this.uploadFileNum = uploadFileNum;
    }

    public int getCreateDocNum() {
        return createDocNum;
    }

    public void setCreateDocNum(int createDocNum) {
        this.createDocNum = createDocNum;
    }

    public int getCreateApiNum() {
        return createApiNum;
    }

    public void setCreateApiNum(int createApiNum) {
        this.createApiNum = createApiNum;
    }
}
