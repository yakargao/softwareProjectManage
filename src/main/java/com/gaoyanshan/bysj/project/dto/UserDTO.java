package com.gaoyanshan.bysj.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>类名: UserDTO</pre>
 * <pre>描述: 用户的DTo</pre>
 * <pre>日期: 19-3-20 下午1:08</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 2576445550452219993L;

    @JsonProperty("id")
    private int id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;

    @JsonProperty("roles")
    private List<String> roles;

    public UserDTO(int id, String email, String name) {
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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
