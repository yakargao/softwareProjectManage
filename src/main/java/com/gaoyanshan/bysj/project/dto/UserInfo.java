package com.gaoyanshan.bysj.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>类名: UserInfo</pre>
 * <pre>描述: 用户信息实体，用于网络传输</pre>
 * <pre>日期: 19-3-24 下午5:00</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -2362777264182649641L;
    private int id;
    private String email;
    private String name;
    private String avatar;
    private int valid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public UserInfo() {
    }

    public UserInfo(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getValid() {
        return valid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
