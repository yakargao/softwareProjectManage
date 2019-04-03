package com.gaoyanshan.bysj.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gaoyanshan.bysj.project.dto.UserInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <pre>类名: FileResource</pre>
 * <pre>描述: 文件资源实体类</pre>
 * <pre>日期: 19-4-1 下午3:53</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Entity
@Table(name ="file_resource")
public class FileResource implements Serializable{

    private static final long serialVersionUID = 7952121703454491252L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;

    @Column
    private int fileType;

    @Column
    private String path;

    @Column
    private String backupPath;

    @JsonIgnore
    @ManyToOne (cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private User user;

    @JsonIgnore
    @ManyToOne (cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Project project;

    @Column
    private int deleted;

    @JsonInclude
    @Transient
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo(this.getUser().getId(),
                this.getUser().getEmail(),
                this.getUser().getName());
        return userInfo;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }


    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBackupPath() {
        return backupPath;
    }

    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
