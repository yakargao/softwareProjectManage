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


    /**
     * 统计数据
     * @param userId
     * @return
     */
    StatisticDTO getStatistic(int userId);

    /**
     * 获取任务统计数据
     * @param pId
     * @param type
     * @return
     */
    ClassifyStatisticDTO getTaskStatistic(int pId,int type);


    /**
     * 获取文档统计数据
     * @param pId
     * @param type
     * @return
     */

    ClassifyStatisticDTO getDocumentStatistic(int pId,int type);


    /**
     * 获取文件统计数据
     * @param pId
     * @param type
     * @return
     */
    ClassifyStatisticDTO getFileStatistic(int pId,int type);

    /**
     * 获取Api统计数据
     * @param pId
     * @param type
     * @return
     */
    ClassifyStatisticDTO getApiStatistic(int pId,int type);


    /**
     * 获取任务排行数据
     * @param pId
     * @param type
     * @return
     */
    ClassifyStatisticDTO getTaskRankStatistic(int pId,int type);

}
