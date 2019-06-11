package com.gaoyanshan.bysj.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>类名: StatisticDTO</pre>
 * <pre>描述: </pre>
 * <pre>日期: 19-4-3 下午8:21</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class StatisticDTO {

    @JsonProperty("dates")
    @JsonFormat(pattern = "MM-dd")
    private List<Date> dates = new ArrayList<>();

    private StatisticDataUnit createTask = new StatisticDataUnit();

    private StatisticDataUnit doneTask = new StatisticDataUnit();

    private StatisticDataUnit ceateDoc = new StatisticDataUnit();

    private StatisticDataUnit uploadFile = new StatisticDataUnit();

    private StatisticDataUnit createApi = new StatisticDataUnit();

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public StatisticDataUnit getCreateTask() {
        return createTask;
    }

    public void setCreateTask(StatisticDataUnit createTask) {
        this.createTask = createTask;
    }

    public StatisticDataUnit getDoneTask() {
        return doneTask;
    }

    public void setDoneTask(StatisticDataUnit doneTask) {
        this.doneTask = doneTask;
    }

    public StatisticDataUnit getCeateDoc() {
        return ceateDoc;
    }

    public void setCeateDoc(StatisticDataUnit ceateDoc) {
        this.ceateDoc = ceateDoc;
    }

    public StatisticDataUnit getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(StatisticDataUnit uploadFile) {
        this.uploadFile = uploadFile;
    }

    public StatisticDataUnit getCreateApi() {
        return createApi;
    }

    public void setCreateApi(StatisticDataUnit createApi) {
        this.createApi = createApi;
    }
}
