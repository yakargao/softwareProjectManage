package com.gaoyanshan.bysj.project.service;

import com.gaoyanshan.bysj.project.dto.*;
import com.gaoyanshan.bysj.project.entity.Task;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.entity.UserTask;

import java.util.List;
import java.util.Map;

/**
 * <pre>类名: TaskService</pre>
 * <pre>描述: task相关的业务层</pre>
 * <pre>日期: 19-3-25 下午8:25</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public interface TaskService {
    /**
     * 新增任务
     * @param taskDTO
     */
    Task addTask(TaskDTO taskDTO,User user);

    /**
     * 删除任务
     * @param taskId
     */
    void deleteTask(int taskId);

    /**
     * 更新任务
     * @param taskDTO
     */
    void updatedTask(TaskDTO taskDTO,int taskID);

    /**
     * 获取任务
     * @param taskId
     */
    Task getTask(int taskId);


    /**
     * 获得项目下的所有任务
     * @param projectId
     * @return
     */
    List<Task> getTasksByProjectId(int projectId);

    /**
     * 根据用户id获取当前任务
     * @param userID
     * @return
     */
    MyPage<Task> getTaskByUserId(int userID,int page,int size);

    /**
     * 获取对应类型的所有任务
     * @param type
     * @return
     */
    List<Task> getTasksByType(int type);

    /**
     * 批量删除任务
     * @param ids
     * @return
     */
    Map<Integer,Boolean> deleteTasks(int[] ids);

    /**
     * 获得任务的负责人
     * @param taskId
     * @return
     */
    List<UserInfo> getResponsUsers(int taskId);

    /**
     * 获取任务的创建人
     * @param taskId
     * @return
     */
    UserInfo getCreateUser(int taskId);

    /**
     * 更新任务状态
     * @param taskId
     * @param status
     */
    void updateStatus(int taskId,int status);

    /**
     * 多条件查询
     * @param taskCondition
     * @return
     */
    TodoList getTasksByCondition(TaskCondition taskCondition);

    /**
     * 简单的新增任务
     * @param map
     * @param user
     * @return
     */
    Task addSimpleTask(Map<String,Object>map,User user);

    /**
     * 添加参与者
     * @param map
     * @return
     */
    Boolean addActor(Map<String,Integer> map);


}
