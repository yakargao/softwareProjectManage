package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.dto.TaskDTO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        TaskType taskType = taskTypeRepository.findById(taskDTO.getTaskType());
        task.setTaskType(taskType);
        Task saveTask = taskRepository.save(task);
        UserTask userTask = new UserTask();
        userTask.setUser(user);
        userTask.setTask(saveTask);
        userTask.setCreateTime(new Date());
        userTaskRepositiry.save(userTask);
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
        List<Task> tasks = new ArrayList<>();
        for (UserTask ut : userTasks){
            if (ut.getTask().getDeleted() != Constant.DB_DELETED){
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
    public List<Task> getTasksByCondition(Date startTime, Date endTime, int pid,int type) {
     return null;
    }
}
