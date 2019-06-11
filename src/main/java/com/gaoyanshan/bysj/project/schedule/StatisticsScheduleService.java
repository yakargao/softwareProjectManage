package com.gaoyanshan.bysj.project.schedule;

import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.entity.*;
import com.gaoyanshan.bysj.project.repository.*;
import com.gaoyanshan.bysj.project.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;
import java.util.concurrent.CountDownLatch;


/**
 * <pre>类名: StatisticsScheduleService</pre>
 * <pre>描述: 统计任务调度</pre>
 * <pre>日期: 19-4-3 下午4:28</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Configuration
@EnableScheduling
public class StatisticsScheduleService {

    private static  final Logger logger = LoggerFactory.getLogger(StatisticsScheduleService.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;


    @Autowired
    private UserTaskRepositiry userTaskRepositiry;

    @Autowired
    private FileResourceRepository fileResourceRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private APIRepository apiRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    /**
     * 统计任务调度，每周六0点进行统计
     */

    @Async
//    @Scheduled(cron = "* * * * * 6")
    public void staticsticTask(){
        List<Project> projects = projectRepository.findAll();
        final CountDownLatch countDownLatch = new CountDownLatch(projects.size());
        for (Project project : projects){
            StaticsticRunable runable = new StaticsticRunable(countDownLatch,project.getId());
            runable.run();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class StaticsticRunable implements Runnable{

        private CountDownLatch countDownLatch;

        private int projectId;

        public StaticsticRunable(CountDownLatch countDownLatch, int projectId) {
            this.countDownLatch = countDownLatch;
            this.projectId = projectId;
        }

        @Override
        public void run() {
           try{

               Date beginTime = DateUtil.getTimesWeekBegin();
               Date endTime = DateUtil.getTimesWeekEnd();
               HashSet<Integer> userHashSet = new HashSet<>();
               Map<Integer,Integer> doneTaskMap = new HashMap<>();
               Map<Integer,Integer> createTaskMap = new HashMap<>();
               Map<Integer,Integer> uploadFileMap = new HashMap<>();
               Map<Integer,Integer> createDocMap = new HashMap<>();
               Map<Integer,Integer> createApiMap = new HashMap<>();
               Project project = new Project();
               project.setId(projectId);
               //统计完成任务

               List<Task> doneTasks = taskRepository.findAllByDoneTimeBetweenAndIsDoneEqualsAndProjectEquals(beginTime,endTime, Constant.TASK_IS_DONE,project);
               //开始计算
               for (Task task:doneTasks){
                   List<UserTask> userTasks = userTaskRepositiry.findAllByTaskEquals(task);
                   for (UserTask userTask : userTasks){
                       int uId = userTask.getUser().getId();
                       userHashSet.add(uId);
                       if (doneTaskMap.get(uId) == null)
                           doneTaskMap.put(uId,0);
                       else
                           doneTaskMap.put(uId,doneTaskMap.get(uId)+1);
                   }
               }
               logger.info("工程："+projectId+"doneTaskMap:"+doneTaskMap);


               //统计创建任务
               List<Task> createTasks = taskRepository.findAllByCreateTimeBetweenAndProjectEquals(beginTime,endTime,project);
               //开始计算
               for(Task task : createTasks){
                   UserTask userTask = userTaskRepositiry.findByTaskEqualsAndConnectTypeEquals(task,0);
                   int uId = userTask.getUser().getId();
                   userHashSet.add(uId);
                   if (createTaskMap.get(uId) == null)
                       createTaskMap.put(uId,0);
                   else
                       createTaskMap.put(uId,createTaskMap.get(uId)+1);
               }
               logger.info("工程："+projectId+"createTaskMap:"+createTaskMap);


               //统计上传文件数
                List<FileResource> fileResources = fileResourceRepository.findAllByUploadTimeBetweenAndProjectEquals(beginTime,endTime,project);
                for (FileResource fileResource : fileResources){
                    int uId = fileResource.getUser().getId();
                    userHashSet.add(uId);
                    if (uploadFileMap.get(uId) == null)
                        uploadFileMap.put(uId,0);
                    else
                        uploadFileMap.put(uId,uploadFileMap.get(uId)+1);
                }
               logger.info("工程："+projectId+"uploadFileMap:"+uploadFileMap);


               //统计新建文档数
               List<Document> documents = documentRepository.findAllByCreateTimeBetweenAndProjectEquals(beginTime,endTime,project);
               for (Document document :documents){
                   int uid = document.getCreateUser().getId();
                   userHashSet.add(uid);
                   if (createDocMap.get(uid) == null)
                       createDocMap.put(uid,0);
                   else
                       createDocMap.put(uid,createDocMap.get(uid)+1);
               }
               logger.info("工程："+projectId+"createDocMap:"+createDocMap);

                //统计新建接口数
               List<API> apis = apiRepository.findAllByCreateTimeBetweenAndProjectEquals(beginTime,endTime,project);
               for (API api : apis){
                   int uid = api.getUser().getId();
                   userHashSet.add(uid);
                   if (createApiMap.get(uid) == null)
                       createApiMap.put(uid,0);
                   else
                       createApiMap.put(uid,createApiMap.get(uid)+1);
               }
               logger.info("工程："+projectId+"createApiMap:"+createApiMap);

               for (Integer uid : userHashSet){
                    int createTaskNum = 0;
                    int doneTaskNum = 0;
                    int createDocNum = 0;
                    int uploadFileNum = 0;
                    int createApiNum = 0;
                    try {
                        createTaskNum = createTaskMap.get(uid)!=null?createTaskMap.get(uid):0;
                        doneTaskNum = doneTaskMap.get(uid)!=null?doneTaskMap.get(uid):0;
                        createDocNum = createDocMap.get(uid)!=null?createDocMap.get(uid):0;
                        uploadFileNum = uploadFileMap.get(uid)!=null?uploadFileMap.get(uid):0;
                        createApiNum = createApiMap.get(uid)!=null?createApiMap.get(uid):0;
                        Statistic statistic = new Statistic();
                        statistic.setProjectId(projectId);
                        statistic.setUserId(uid);
                        statistic.setCreateTaskNum(createTaskNum);
                        statistic.setDoneTaskNum(doneTaskNum);
                        statistic.setCreateDocNum(createDocNum);
                        statistic.setUploadFileNum(uploadFileNum);
                        statistic.setCreateApiNum(createApiNum);
                        statistic.setBeginTime(beginTime);
                        statistic.setEndTime(endTime);
                        statisticRepository.save(statistic);
                    }catch (Exception e){
                        logger.error(beginTime+"-"+endTime+":用户id="+uid+"数据保存异常");
                    }finally {
                        logger.info(beginTime+"-"+endTime+":用户id="+uid+"创建任务"+createTaskNum+"个");
                        logger.info(beginTime+"-"+endTime+":用户id="+uid+"完成任务"+doneTaskNum+"个");
                        logger.info(beginTime+"-"+endTime+":用户id="+uid+"创建文档"+createDocNum+"个");
                        logger.info(beginTime+"-"+endTime+":用户id="+uid+"上传文件"+uploadFileNum+"个");
                        logger.info(beginTime+"-"+endTime+":用户id="+uid+"创建接口"+createApiNum+"个");
                    }
               }
           }finally {
              countDownLatch.countDown();
           }
        }
    }
}
