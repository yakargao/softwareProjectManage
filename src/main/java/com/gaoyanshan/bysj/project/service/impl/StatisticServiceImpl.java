package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.dto.*;
import com.gaoyanshan.bysj.project.dynamic.aspect.DynamicAspect;
import com.gaoyanshan.bysj.project.entity.Project;
import com.gaoyanshan.bysj.project.entity.Statistic;
import com.gaoyanshan.bysj.project.repository.*;
import com.gaoyanshan.bysj.project.service.StatisticService;
import com.gaoyanshan.bysj.project.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <pre>类名: StatisticServiceImpl</pre>
 * <pre>描述: 数据统计表业务逻辑实现类 </pre>
 * <pre>日期: 19-4-3 下午8:30</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    private static final Logger logger = LoggerFactory.getLogger(DynamicAspect.class);

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private FileResourceRepository fileResourceRepository;

    @Autowired
    private APIRepository apiRepository;

    @Override
    public StatisticDTO getPersonalDatas(int projectId,int userId) {
        List<Statistic> statistics = statisticRepository.findAllByProjectIdAndAndUserIdOrderByEndTimeDesc(projectId,userId);
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

    @Override
    public ClassifyStatisticDTO getTaskStatistic(int pId, int type) {

        TaskStatisticDTO statisticDTO = new TaskStatisticDTO();

        Project project = projectRepository.findOneById(pId);
        //type==0 按月查询
        //type==1 按周查询
        if (type == 0){
            for (int i = 0; i<12;i++){
                statisticDTO.getxDatas().add((i+1)+"月");
                Date startTime = DateUtil.getTimeAppointMouthBegin(i);
                Date endTime = DateUtil.getTimeAppointMouthEnd(i);
                int doneNum = taskRepository.countAllByIsDoneAndProjectAndCreateTimeBetween(Constant.TASK_IS_DONE,project,startTime,endTime);
                int unDoneNum = taskRepository.countAllByIsDoneAndProjectAndCreateTimeBetween(Constant.TASK_IS_NOT_DONE,project,startTime,endTime);
                int overdueNum = taskRepository.countAllByExpectedTimeBeforeAndIsDoneEqualsAndCreateTimeBetween(endTime,Constant.TASK_IS_NOT_DONE,startTime,endTime);
                statisticDTO.getDoneTasks().add(doneNum);
                statisticDTO.getUnDoneTasks().add(unDoneNum);
                statisticDTO.getOverdueTasks().add(overdueNum);
            }
        }else if (type == 1){
            int nowWeekNum = DateUtil.getWeekNumOfNow();
            int beginWeek = nowWeekNum<15?0:nowWeekNum-15;
            for (int i = beginWeek;i<=nowWeekNum;i++){
                statisticDTO.getxDatas().add("第"+i+"周");
                Date startTime = DateUtil.getOneWeekBegin(i);
                Date endTime = DateUtil.getOneWeekEnd(i);

                //统计完成未完成和逾期的任务数
                int unDoneNum = taskRepository.countAllByIsDoneAndProjectAndCreateTimeBetween(Constant.TASK_IS_NOT_DONE,project,startTime,endTime);
                int overdueNum = taskRepository.countAllByExpectedTimeBeforeAndIsDoneEqualsAndCreateTimeBetween(endTime,Constant.TASK_IS_NOT_DONE,startTime,endTime);
                int doneNum = taskRepository.countAllByIsDoneAndProjectAndCreateTimeBetween(Constant.TASK_IS_DONE,project,startTime,endTime);

                statisticDTO.getDoneTasks().add(doneNum);
                statisticDTO.getUnDoneTasks().add(unDoneNum);
                statisticDTO.getOverdueTasks().add(overdueNum);
            }
        }

        return statisticDTO;
    }

    @Override
    public ClassifyStatisticDTO getDocumentStatistic(int pId, int type) {

        Project project = projectRepository.findOneById(pId);

        DocumentStatisticDTO documentStatisticDTO = new DocumentStatisticDTO();

        if (type == 0){
            int oneFlag = 0;
            for (Map.Entry<Integer,String> entry : Constant.DOCUMENT_TYPES.entrySet()){
                DocumentStatisticUnit statisticUnit = new DocumentStatisticUnit();
                statisticUnit.setName(entry.getValue());
                for (int i=0;i<12;i++){
                    if (oneFlag == 0){
                        documentStatisticDTO.getxDatas().add((i+1)+"月");
                    }
                    Date startTime = DateUtil.getTimeAppointMouthBegin(i);
                    Date endTime = DateUtil.getTimeAppointMouthEnd(i);
                    int num = documentRepository.countAllByProjectAndDocumentTypeAndCreateTimeBetween(project,entry.getKey(),startTime,endTime);
                    statisticUnit.getDatas().add(num);
                }
                documentStatisticDTO.getUnits().add(statisticUnit);
                oneFlag = 1;
            }

        }else if (type ==1){
            int nowWeekNum = DateUtil.getWeekNumOfNow();
            int beginWeek = nowWeekNum<15?0:nowWeekNum-15;
            int oneFlag = 0;
            for (Map.Entry<Integer,String> entry : Constant.DOCUMENT_TYPES.entrySet()){
                DocumentStatisticUnit statisticUnit = new DocumentStatisticUnit();
                statisticUnit.setName(entry.getValue());
                for (int i=beginWeek;i<=nowWeekNum;i++){
                    if (oneFlag == 0){
                        documentStatisticDTO.getxDatas().add("第"+i+"周");
                    }
                    Date startTime = DateUtil.getOneWeekBegin(i);
                    Date endTime = DateUtil.getOneWeekEnd(i);
                    int num = documentRepository.countAllByProjectAndDocumentTypeAndCreateTimeBetween(project,entry.getKey(),startTime,endTime);
                    statisticUnit.getDatas().add(num);
                }
                documentStatisticDTO.getUnits().add(statisticUnit);
                oneFlag = 1;
            }
        }
        return documentStatisticDTO;
    }

    @Override
    public ClassifyStatisticDTO getFileStatistic(int pId, int type) {
        Project project = projectRepository.findOneById(pId);
        FileStatisticDTO statisticDTO = new FileStatisticDTO();
        if (type == 0){
            int oneFlag = 0;
            for (Types types:Constant.FILE_TYPES){
                FileStatisticUnit statisticUnit = new FileStatisticUnit();
                statisticUnit.setName(types.getName());
                for (int i=0;i<12;i++){
                    if (oneFlag == 0){
                        statisticDTO.getxDatas().add((i+1)+"月");
                    }
                    Date startTime = DateUtil.getTimeAppointMouthBegin(i);
                    Date endTime = DateUtil.getTimeAppointMouthEnd(i);
                    int num = fileResourceRepository.countAllByProjectAndFileTypeAndUploadTimeBetween(project,types.getId(),startTime,endTime);
                    statisticUnit.getDatas().add(num);
                }
                statisticDTO.getUnits().add(statisticUnit);
                oneFlag = 1;
            }
        }else if (type == 1){
            int nowWeekNum = DateUtil.getWeekNumOfNow();
            int beginWeek = nowWeekNum<15?0:nowWeekNum-15;
            int oneFlag = 0;
            for (Types types:Constant.FILE_TYPES){
                FileStatisticUnit statisticUnit = new FileStatisticUnit();
                statisticUnit.setName(types.getName());
                for (int i=beginWeek;i<=nowWeekNum;i++){
                    if (oneFlag == 0){
                        statisticDTO.getxDatas().add("第"+i+"周");
                    }
                    Date startTime = DateUtil.getOneWeekBegin(i);
                    Date endTime = DateUtil.getOneWeekEnd(i);
                    int num = fileResourceRepository.countAllByProjectAndFileTypeAndUploadTimeBetween(project,types.getId(),startTime,endTime);
                    statisticUnit.getDatas().add(num);
                }
                statisticDTO.getUnits().add(statisticUnit);
                oneFlag = 1;
            }
        }

        return statisticDTO;
    }

    @Override
    public ClassifyStatisticDTO getApiStatistic(int pId, int type) {
        Project project = projectRepository.findOneById(pId);
        ApiStatisticDTO statisticDTO = new ApiStatisticDTO();
        if (type == 0){
            ApiStatisticUnit apiStatisticUnit = new ApiStatisticUnit();
            apiStatisticUnit.setName("接口");
            for (int i = 0 ;i < 12; i++){
                statisticDTO.getxDatas().add((i+1)+"月");
                Date startTime = DateUtil.getTimeAppointMouthBegin(i);
                Date endTime = DateUtil.getTimeAppointMouthEnd(i);
                int num = apiRepository.countAllByProjectAndCreateTimeBetween(project,startTime,endTime);
                apiStatisticUnit.getDatas().add(num);
            }
            statisticDTO.getUnits().add(apiStatisticUnit);
        }else if (type == 1){
            int nowWeekNum = DateUtil.getWeekNumOfNow();
            int beginWeek = nowWeekNum<15?0:nowWeekNum-15;
            ApiStatisticUnit apiStatisticUnit = new ApiStatisticUnit();
            apiStatisticUnit.setName("接口");
            for (int i = beginWeek;i<=nowWeekNum;i++){
                statisticDTO.getxDatas().add("第"+i+"周");
                Date startTime = DateUtil.getOneWeekBegin(i);
                Date endTime = DateUtil.getOneWeekEnd(i);
                int num = apiRepository.countAllByProjectAndCreateTimeBetween(project,startTime,endTime);
                apiStatisticUnit.getDatas().add(num);
            }
            statisticDTO.getUnits().add(apiStatisticUnit);
        }
        return statisticDTO;
    }

    @Override
    public ClassifyStatisticDTO getTaskRankStatistic(int pId, int type) {
        return null;
    }
}
