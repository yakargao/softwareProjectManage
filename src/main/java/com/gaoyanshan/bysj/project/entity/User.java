package com.gaoyanshan.bysj.project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Role> roles = new ArrayList<>();

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mail='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", valid=" + valid +
                ", roles=" + roles +
                '}';
    }
}
