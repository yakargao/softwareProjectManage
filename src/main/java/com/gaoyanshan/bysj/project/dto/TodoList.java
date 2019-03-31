package com.gaoyanshan.bysj.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gaoyanshan.bysj.project.entity.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>类名: TodoList</pre>
 * <pre>描述: 任务看板DTO</pre>
 * <pre>日期: 19-3-29 下午9:36</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class TodoList implements Serializable{

    private static final long serialVersionUID = 1516778599928353266L;

    @JsonProperty("todos")
    private List<Task> todos = new ArrayList<>();

    @JsonProperty("doings")
    private List<Task> doings = new ArrayList<>();

    @JsonProperty("dones")
    private List<Task> dones = new ArrayList<>();


    public List<Task> getTodos() {
        return todos;
    }

    public void setTodos(List<Task> todos) {
        this.todos = todos;
    }

    public List<Task> getDoings() {
        return doings;
    }

    public void setDoings(List<Task> doings) {
        this.doings = doings;
    }

    public List<Task> getDones() {
        return dones;
    }

    public void setDones(List<Task> dones) {
        this.dones = dones;
    }
}
