package com.gaoyanshan.bysj.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>类名: User</pre>
 * <pre>描述: 用户信息表</pre>
 * <pre>日期: 19-3-15 下午1:41</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Entity
@Table(name = "user")
public class User implements Serializable{

    private static final long serialVersionUID = -1946184199704372786L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private int valid;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.LAZY)
    private List<UserProject> userProjects = new ArrayList<>();

    @Column
    private String avatar;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String   getAuthCacheKey(){

        return this.email;
    }

    public List<UserProject> getUserProjects() {
        return userProjects;
    }

    public void setUserProjects(List<UserProject> userProjects) {
        this.userProjects = userProjects;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User){
            return this.email == ((User) o).getEmail();
        }
        return false;
    }
}
