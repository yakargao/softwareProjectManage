package com.gaoyanshan.bysj.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>类名: StatisticDataUnit</pre>
 * <pre>描述: 折线图每个单元的数据格式</pre>
 * <pre>日期: 19-4-3 下午8:18</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class StatisticDataUnit {

    @JsonProperty("name")
    private String name;

    @JsonProperty("datas")
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
