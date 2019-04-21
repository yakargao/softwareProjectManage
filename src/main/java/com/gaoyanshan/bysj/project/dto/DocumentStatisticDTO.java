package com.gaoyanshan.bysj.project.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DocumentStatisticDTO extends ClassifyStatisticDTO implements Serializable {


    private static final long serialVersionUID = -3542488425068752340L;

    private List<DocumentStatisticUnit> units = new ArrayList<>();

    public List<DocumentStatisticUnit> getUnits() {
        return units;
    }

    public void setUnits(List<DocumentStatisticUnit> units) {
        this.units = units;
    }


}
