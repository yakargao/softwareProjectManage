package com.gaoyanshan.bysj.project.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <pre>类名: Permission</pre>
 * <pre>描述: 权限表</pre>
 * <pre>日期: 19-3-15 下午1:49</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Entity
@Table(name = "permission")
public class Permission implements Serializable{

    private static final long serialVersionUID = -3291062098128547460L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String permission;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    public Permission() {
    }

    public Permission(String permission, Role role) {
        this.permission = permission;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
