package com.gaoyanshan.bysj.project.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <pre>类名: User_Project</pre>
 * <pre>描述: 用户项目表</pre>
 * <pre>日期: 19-3-23 下午9:32</pre>
 * <pre>作者: gaoyanshan</pre>
 */


@Entity
@Table(name = "user_project")
public class UserProject implements Serializable{

    private static final long serialVersionUID = 8937235854938895908L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private User user;

    @ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Project project;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
