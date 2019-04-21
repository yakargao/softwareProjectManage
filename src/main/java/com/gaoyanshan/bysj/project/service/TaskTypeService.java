package com.gaoyanshan.bysj.project.service;

import antlr.collections.impl.LList;
import com.gaoyanshan.bysj.project.entity.Task;
import com.gaoyanshan.bysj.project.entity.TaskType;
import com.gaoyanshan.bysj.project.entity.UserTask;

import java.util.List;
import java.util.Map;

/**
 * <pre>类名: TaskTypeService</pre>
 * <pre>描述: 任务类型业务层</pre>
 * <pre>日期: 19-3-29 下午6:15</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public interface TaskTypeService {

    /**
     * 新增任务类型
     * @param name
     * @return
     */
    Integer addTaskType(String name);

    /**
     * 获取所有任务类型
     * @return
     */
    List<TaskType> getAllTaskType();

    /**
     * 通过projectId获取类型
     * @param projectId
     * @return
     */
    List<TaskType> getTasksByProjectId(int projectId);

    /**
     * 新增任务类型
     * @param taskType
     * @return
     */
    TaskType addTaskType(TaskType taskType);

    /**
     * 删除任务类型
     * @param typeId
     * @return
     */
    Boolean deleteTaskType(int typeId);




}
