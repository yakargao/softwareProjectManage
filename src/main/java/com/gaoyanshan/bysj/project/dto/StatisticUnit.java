package com.gaoyanshan.bysj.project.dto;

import java.util.ArrayList;
import java.util.List;

public class StatisticUnit {
    private String name;
    private List<Integer> datas = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getDatas() {
        return datas;
    }

    public void setDatas(List<Integer> datas) {
        this.datas = datas;
    }
}
