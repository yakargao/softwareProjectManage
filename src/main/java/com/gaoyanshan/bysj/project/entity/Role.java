package com.gaoyanshan.bysj.project.entity;

import javax.persistence.*;
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
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String roleNameEn;

    @Column
    private String roleNameZh;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "role")
    private List<Permission> permissions = new ArrayList<>();

    public Role() {
    }

    public Role(String roleNameEn, String roleNameZh, User user, List<Permission> permissions) {
        this.roleNameEn = roleNameEn;
        this.roleNameZh = roleNameZh;
        this.user = user;
        this.permissions = permissions;
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
