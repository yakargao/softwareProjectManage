package com.gaoyanshan.bysj.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <pre>类名: Types</pre>
 * <pre>描述: 类型通用dto</pre>
 * <pre>日期: 19-3-31 下午9:58</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class Types {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    public Types(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Types() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


