package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.dto.StatisticDTO;
import com.gaoyanshan.bysj.project.entity.Statistic;
import com.gaoyanshan.bysj.project.repository.StatisticRepository;
import com.gaoyanshan.bysj.project.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>类名: StatisticServiceImpl</pre>
 * <pre>描述: 数据统计表业务逻辑实现类 </pre>
 * <pre>日期: 19-4-3 下午8:30</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticRepository statisticRepository;

    @Override
    public StatisticDTO getPersonalDatas(int projectId,int userId) {
        List<Statistic> statistics = statisticRepository.findAllByProjectIdAndAndUserIdOrderByEndTimeDesc(projectId,userId);
//        StatisticDTO dto = new StatisticDTO();
//        if (statistics.size() > 15)
//            statistics = statistics.subList(0,15);
//        for (int i = statistics.size()-1;i>=0;i--){
//            Statistic statistic = statistics.get(i);
//            dto.getDates().add(statistic.getEndTime());
//            dto.getCreateTask().getDatas().add(statistic.getCreateTaskNum());
//            dto.getDoneTask().getDatas().add(statistic.getDoneTaskNum());
//            dto.getCeateDoc().getDatas().add(statistic.getCreateDocNum());
//            dto.getUploadFile().getDatas().add(statistic.getUploadFileNum());
//            dto.getCreateApi().getDatas().add(statistic.getCreateApiNum());
//        }
//        dto.getDoneTask().setName("完成任务");
//        dto.getCreateTask().setName("创建任务");
//        dto.getCeateDoc().setName("创建文档");
//        dto.getUploadFile().setName("上传文件");
//        dto.getCreateApi().setName("创建接口");
        return null;
    }

    @Override
    public StatisticDTO getStatistic(int userId) {
        List<Statistic> statistics = statisticRepository.findAllByUserId(userId);
        StatisticDTO dto = new StatisticDTO();
        if (statistics.size() > 15)
            statistics = statistics.subList(0,15);
        for (int i = statistics.size()-1;i>=0;i--){
            Statistic statistic = statistics.get(i);
            dto.getDates().add(statistic.getEndTime());
            dto.getCreateTask().getDatas().add(statistic.getCreateTaskNum());
            dto.getDoneTask().getDatas().add(statistic.getDoneTaskNum());
            dto.getCeateDoc().getDatas().add(statistic.getCreateDocNum());
            dto.getUploadFile().getDatas().add(statistic.getUploadFileNum());
            dto.getCreateApi().getDatas().add(statistic.getCreateApiNum());
        }
        dto.getDoneTask().setName("完成任务");
        dto.getCreateTask().setName("创建任务");
        dto.getCeateDoc().setName("创建文档");
        dto.getUploadFile().setName("上传文件");
        dto.getCreateApi().setName("创建接口");
        return dto;
    }
}
