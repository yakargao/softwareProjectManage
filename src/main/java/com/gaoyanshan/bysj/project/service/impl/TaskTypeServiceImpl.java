package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.entity.TaskType;
import com.gaoyanshan.bysj.project.repository.TaskTypeRepository;
import com.gaoyanshan.bysj.project.service.TaskService;
import com.gaoyanshan.bysj.project.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>类名: TaskTypeServiceImpl</pre>
 * <pre>描述: 任务类型业务层实现</pre>
 * <pre>日期: 19-3-29 下午6:18</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Service
public class TaskTypeServiceImpl implements TaskTypeService {

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    @Override
    public Integer addTaskType(String name) {
        return null;
    }

    @Override
    public List<TaskType> getAllTaskType() {
        return  taskTypeRepository.findAll();
    }
}
