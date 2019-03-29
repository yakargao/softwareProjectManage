package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>类名: TaskTypeController</pre>
 * <pre>描述: 任务类型控制层</pre>
 * <pre>日期: 19-3-29 下午6:20</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@RestController
@RequestMapping("/taskType")
public class TaskTypeController {

    @Autowired
    private TaskTypeService taskTypeService;

    @GetMapping("/all")
    public Response getAllType(){
        return Response.success(taskTypeService.getAllTaskType());
    }
}


