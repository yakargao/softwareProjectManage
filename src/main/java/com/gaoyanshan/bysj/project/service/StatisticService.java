package com.gaoyanshan.bysj.project.service;

import com.gaoyanshan.bysj.project.dto.ClassifyStatisticDTO;
import com.gaoyanshan.bysj.project.dto.StatisticDTO;

/**
 * <pre>类名: StatisticService</pre>
 * <pre>描述: 统计表业务层</pre>
 * <pre>日期: 19-4-3 下午8:15</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public interface StatisticService {

    /**
     * 获取用户效率数据
     * @return
     */
    StatisticDTO getPersonalDatas(int projectId,int useId);


    StatisticDTO getStatistic(int userId);

    ClassifyStatisticDTO getTaskStatistic(int pId,int type);


    ClassifyStatisticDTO getDocumentStatistic(int pId,int type);


    ClassifyStatisticDTO getFileStatistic(int pId,int type);

    ClassifyStatisticDTO getApiStatistic(int pId,int type);
}
