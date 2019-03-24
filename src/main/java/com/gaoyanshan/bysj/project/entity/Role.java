package com.gaoyanshan.bysj.project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>类名: Role</pre>
 * <pre>描述: 角色表</pre>
 * <pre>日期: 19-3-15 下午1:46</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Entity
@Table(name = "role")
public class Role implements Serializable{

    private static final long serialVersionUID = -5780572095080920331L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String roleNameEn;

    @Column
    private String roleNameZh;

    @Column
    private int deleted;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "role",fetch = FetchType.EAGER)
    private List<Permission> permissions = new ArrayList<>();

    public Role() {
    }

    public Role(String roleNameEn, String roleNameZh) {
        this.roleNameEn = roleNameEn;
        this.roleNameZh = roleNameZh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleNameEn() {
        return roleNameEn;
    }

    public void setRoleNameEn(String roleNameEn) {
        this.roleNameEn = roleNameEn;
    }

    public String getRoleNameZh() {
        return roleNameZh;
    }

    public void setRoleNameZh(String roleNameZh) {
        this.roleNameZh = roleNameZh;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
