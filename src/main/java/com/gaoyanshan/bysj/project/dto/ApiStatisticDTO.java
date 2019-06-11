package com.gaoyanshan.bysj.project.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApiStatisticDTO extends ClassifyStatisticDTO implements Serializable {
    private static final long serialVersionUID = -1644127215000213645L;

    private List<ApiStatisticUnit> units = new ArrayList<>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<ApiStatisticUnit> getUnits() {
        return units;
    }

    public void setUnits(List<ApiStatisticUnit> units) {
        this.units = units;
    }
}
