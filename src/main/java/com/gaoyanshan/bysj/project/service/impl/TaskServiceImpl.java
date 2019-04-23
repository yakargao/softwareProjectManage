package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.dto.*;
import com.gaoyanshan.bysj.project.dynamic.aspect.Dynamic;
import com.gaoyanshan.bysj.project.dynamic.enumeration.DynamicEventEnum;
import com.gaoyanshan.bysj.project.entity.*;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.repository.*;
import com.gaoyanshan.bysj.project.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * <pre>类名: TaskServiceImpl</pre>
 * <pre>描述: 任务业务层实现类</pre>
 * <pre>日期: 19-3-25 下午8:55</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Service
public class TaskServiceImpl implements TaskService {

    private static  final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserTaskRepositiry userTaskRepositiry;

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    @Dynamic(event = DynamicEventEnum.CREATE_TASK)
    public Task addTask(TaskDTO taskDTO,User user) {

        System.out.println(taskDTO);

        Task task = new Task();
        if (taskDTO.getProjectId() != 0){
            Project project = projectRepository.findOneById(taskDTO.getProjectId());
            if (project == null)
                throw  new SystemException("该项目不存在");
            else
                task.setProject(project);
        }
        if (taskDTO.getId() != 0)
            task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        if (taskDTO.getContent() !=null)
            task.setContent(taskDTO.getContent());
        task.setCreateTime(new Date());
        if (taskDTO.getExpectedTime() !=null)
            task.setExpectedTime(taskDTO.getExpectedTime());
        System.out.println(taskDTO.getExpectedTime());
        task.setIsDone(taskDTO.getIsDone());
        task.setTaskLevel(taskDTO.getTaskLevel());
        TaskType taskType = taskTypeRepository.findById(taskDTO.getTaskType());
        task.setTaskType(taskType);
        Task saveTask = taskRepository.save(task);
        UserTask userTask = new UserTask();
        userTask.setUser(user);
        userTask.setTask(saveTask);
        userTask.setCreateTime(new Date());
        userTaskRepositiry.save(userTask);
        return saveTask;
    }

    @Override
    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }

    @Transactional
    @Override
    public void updatedTask(TaskDTO taskDTO,int taskId) {
        Task task = taskRepository.findOneById(taskId);
        task.setTitle(taskDTO.getTitle());
        task.setContent(taskDTO.getContent());
        Project project = projectRepository.findOneById(taskDTO.getProjectId());
        task.setProject(project);
        task.setExpectedTime(taskDTO.getExpectedTime());
        task.setDoneTime(new Date());
        task.setTaskLevel(taskDTO.getTaskLevel());
        task.setTaskType(taskTypeRepository.findById(taskDTO.getTaskType()));
        taskRepository.save(task);
    }

    @Override
    public Task getTask(int taskId) {
        return taskRepository.findOneById(taskId);
    }

    @Override
    public List<Task> getTasksByProjectId(int projectId) {
        return taskRepository.findAllByProjectId(projectId);
    }

    @Override
    public MyPage<Task> getTaskByUserId(int userID,int page,int size) {

        Pageable pageable = PageRequest.of(page-1,size,Sort.Direction.DESC,"createTime");
        Specification specification = new Specification<UserTask>() {
            @Override
            public Predicate toPredicate(Root<UserTask> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("connectType"),1));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page pages = userTaskRepositiry.findAll(specification, pageable);
        List<UserTask> userTasks = pages.getContent();

        List<Task> tasks = new LinkedList<>();
        for (UserTask ut : userTasks){
            if (ut.getTask().getDeleted() != Constant.DB_DELETED){
               tasks.add(ut.getTask());
           }
        }
        return new MyPage<>(tasks,pages.getTotalElements(),pages.getTotalPages());
    }

    @Override
    public List<Task> getTasksByType(int type) {
        TaskType taskType = taskTypeRepository.findById(type);
        return taskRepository.findAllByTaskTypeAndDeletedOrderByIsDoneAsc(taskType,Constant.DB_UNDELETED);
    }

    @Override
    public Map<Integer, Boolean> deleteTasks(int[] ids) {
        return null;
    }

    @Override
    public List<UserInfo> getResponsUsers(int taskId) {

        List<UserTask> userTasks = userTaskRepositiry.findAllByTaskIdAndConnectType(taskId,1);
        List<UserInfo>  userInfos = new ArrayList<>();
        for (UserTask userTask : userTasks){
            UserInfo userInfo = new UserInfo();
            userInfo.setId(userTask.getUser().getId());
            userInfo.setName(userTask.getUser().getName());
            userInfo.setAvatar(userTask.getUser().getAvatar());
            userInfo.setEmail(userTask.getUser().getEmail());
            userInfos.add(userInfo);
        }
        return userInfos;
    }

    @Override
    public UserInfo getCreateUser(int taskId) {
        UserTask userTask = userTaskRepositiry.findOneByTaskIdAndConnectType(taskId,0);
        User user = userTask.getUser();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setEmail(user.getEmail());
        userInfo.setName(user.getName());
        userInfo.setAvatar(user.getAvatar());
        return userInfo;
    }

    @Transactional
    @Override
    @Dynamic(event = DynamicEventEnum.DONE_TASK)
    public void updateStatus(int taskId, int status) {
        taskRepository.updateStatus(taskId,status,new Date());
    }

    @Override
    public TodoList getTasksByCondition(TaskCondition taskCondition) {
        List<Task> resultList = null;
        Specification querySpecifi = new Specification<Task>() {
            @Override
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if(taskCondition.getStartTime() != null){
                    predicates.add(criteriaBuilder.greaterThan(root.get("startTime"), taskCondition.getStartTime()));
                }
                if(taskCondition.getEndTime() != null){
                    predicates.add(criteriaBuilder.lessThan(root.get("startTime"), taskCondition.getEndTime()));
                }
                if(taskCondition.getType() != 0){
                    predicates.add(criteriaBuilder.equal(root.get("taskType").get("id"), taskCondition.getType()));
                }
                if (taskCondition.getTaskLevel() != 0){
                    predicates.add(criteriaBuilder.equal(root.get("taskLevel"),taskCondition.getTaskLevel()));
                }
                predicates.add(criteriaBuilder.equal(root.get("project").get("id"),taskCondition.getpId()));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        resultList =  taskRepository.findAll(querySpecifi);

        TodoList todoList = new TodoList();

        for (Task task : resultList){
            if (task.getDeleted() == Constant.DB_DELETED)
                continue;
            if (task.getStartTime() == null)
                todoList.getTodos().add(task);
            else if (task.getIsDone() == Constant.TASK_IS_DONE)
                todoList.getDones().add(task);
            else
                todoList.getDoings().add(task);
        }
        return todoList;
    }

    @Override
    public Task addSimpleTask(Map<String, Object> map, User user) {

        String title = null;
        int projectId = 0;
        int type = 0;
        try {
            title = (String) map.get("title");
            projectId = Integer.parseInt((String) map.get("pId"));
            type = (int) map.get("type");
        }catch (Exception e){
            throw new  SystemException("类型转化错误");
        }

        Project project = projectRepository.findOneById(projectId);
        if (project == null)
            throw  new SystemException("该项目不存在");

        TaskType taskType = taskTypeRepository.findById(type);

        Task task = new Task();
        task.setTitle(title);
        task.setProject(project);
        task.setContent("");
        task.setTaskLevel(0);
        task.setTaskType(taskType);
        task.setCreateTime(new Date());
        return taskRepository.save(task);
    }

    @Override
    public Boolean addActor(Map<String, Integer> map) {
        int userId = map.get("userId");
        int taskId = map.get("taskId");

        User user = userRepository.findOneById(userId);
        if (user == null){
            throw new SystemException("该用户不存在,用户ID："+userId);
        }
        Task task = taskRepository.findOneById(taskId);
        if (task == null){
            throw  new SystemException("该任务不存在,任务ID："+taskId);
        }else if (task.getStartTime()==null){
            task.setStartTime(new Date());
            taskRepository.save(task);
        }

        UserTask userTask  = new UserTask();
        userTask.setUser(user);
        userTask.setTask(task);
        userTask.setConnectType(1);
        userTask.setCreateTime(new Date());
        userTaskRepositiry.save(userTask);
        return true;
    }


}
