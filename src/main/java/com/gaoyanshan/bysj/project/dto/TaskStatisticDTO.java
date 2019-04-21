package com.gaoyanshan.bysj.project.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务统计数据
 */
public class TaskStatisticDTO extends ClassifyStatisticDTO implements Serializable {

    private static final long serialVersionUID = -3303911929000632430L;

    private List<Integer> doneTasks = new ArrayList<>();

    private List<Integer> unDoneTasks = new ArrayList<>();

    private List<Integer> overdueTasks = new ArrayList<>();

    public List<Integer> getDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(List<Integer> doneTasks) {
        this.doneTasks = doneTasks;
    }

    public List<Integer> getUnDoneTasks() {
        return unDoneTasks;
    }

    public void setUnDoneTasks(List<Integer> unDoneTasks) {
        this.unDoneTasks = unDoneTasks;
    }

    public List<Integer> getOverdueTasks() {
        return overdueTasks;
    }

    public void setOverdueTasks(List<Integer> overdueTasks) {
        this.overdueTasks = overdueTasks;
    }
}
