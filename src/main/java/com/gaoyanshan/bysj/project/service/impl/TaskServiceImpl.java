package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.dto.TaskDTO;
import com.gaoyanshan.bysj.project.entity.Project;
import com.gaoyanshan.bysj.project.entity.Task;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.entity.UserTask;
import com.gaoyanshan.bysj.project.repository.ProjectRepository;
import com.gaoyanshan.bysj.project.repository.TaskRepository;
import com.gaoyanshan.bysj.project.repository.UserTaskRepositiry;
import com.gaoyanshan.bysj.project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        task.setTaskType(taskDTO.getTaskType());
        Task saveTask = taskRepository.save(task);
        UserTask userTask = new UserTask();
        userTask.setUser(user);
        userTask.setTask(saveTask);
        userTaskRepositiry.save(userTask);
    }

    @Override
    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public void updatedTask(TaskDTO taskDTO) {

    }

    @Override
    public void getTask(int taskId) {

    }

    @Override
    public List<Task> getTasksByProjectId(int projectId) {
        return null;
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
}
