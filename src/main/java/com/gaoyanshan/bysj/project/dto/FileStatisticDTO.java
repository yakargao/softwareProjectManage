package com.gaoyanshan.bysj.project.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FileStatisticDTO extends ClassifyStatisticDTO implements Serializable {
    private static final long serialVersionUID = -745948964248705508L;

    private List<FileStatisticUnit> units = new ArrayList<>();

    public List<FileStatisticUnit> getUnits() {
        return units;
    }

    public void setUnits(List<FileStatisticUnit> units) {
        this.units = units;
    }
}
