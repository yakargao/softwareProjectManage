package com.gaoyanshan.bysj.project.service;

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
}
