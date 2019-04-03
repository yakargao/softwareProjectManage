package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.dto.TaskCondition;
import com.gaoyanshan.bysj.project.dto.TaskDTO;
import com.gaoyanshan.bysj.project.dto.TodoList;
import com.gaoyanshan.bysj.project.entity.*;
import com.gaoyanshan.bysj.project.repository.ProjectRepository;
import com.gaoyanshan.bysj.project.repository.TaskRepository;
import com.gaoyanshan.bysj.project.repository.TaskTypeRepository;
import com.gaoyanshan.bysj.project.repository.UserTaskRepositiry;
import com.gaoyanshan.bysj.project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TaskServiceImpl implements TaskService{


    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserTaskRepositiry userTaskRepositiry;

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    @Transactional
    @Override
    public void addTask(TaskDTO taskDTO,User user) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setContent(taskDTO.getContent());
        Project project = projectRepository.findOneById(taskDTO.getProjectId());
        task.setProject(project);
        task.setCreateTime(new Date());
        task.setExpectedTime(taskDTO.getExpectedTime());
        task.setDoneTime(new Date());
        task.setIsDone(Constant.TASK_IS_NOT_DONE);
        task.setTaskLevel(taskDTO.getTaskLevel());
        if (taskDTO.getUsers().size() > 0)
            task.setStartTime(new Date());
        TaskType taskType = taskTypeRepository.findById(taskDTO.getTaskType());
        task.setTaskType(taskType);
        Task saveTask = taskRepository.save(task);
        UserTask userTask = new UserTask();
        userTask.setUser(user);
        userTask.setTask(saveTask);
        userTask.setCreateTime(new Date());
        userTaskRepositiry.save(userTask);
        for (Integer uId : taskDTO.getUsers()){
            userTaskRepositiry.saveOneRecord(uId,saveTask.getId());
        }
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
    public List<Task> getTaskByUserId(int userID) {
        List<UserTask> userTasks = userTaskRepositiry.getAllByUserId(userID);
        List<Task> tasks = new LinkedList<>();
        for (UserTask ut : userTasks){
            if (ut.getTask().getDeleted() != Constant.DB_DELETED && ut.getConnectType()==1){
                tasks.add(ut.getTask());
            }
        }
        return tasks;
    }

    @Override
    public List<Task> getTasksByType(int type) {
        return null;
    }

    @Override
    public Map<Integer, Boolean> deleteTasks(int[] ids) {
        return null;
    }

    @Override
    public List<User> getResponsUsers(int taskId) {
        return null;
    }

    @Override
    public User getCreateUser(int taskId) {
        return null;
    }

    @Transactional
    @Override
    public void updateStatus(int taskId, int status) {
        taskRepository.updateStatus(taskId,status);
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
}
