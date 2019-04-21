package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.entity.TaskType;
import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <pre>类名: TaskTypeController</pre>
 * <pre>描述: 任务类型控制层</pre>
 * <pre>日期: 19-3-29 下午6:20</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@CrossOrigin(origins = "http://0.0.0.0:8888")
@RestController
@RequestMapping("/taskType")
public class TaskTypeController {

    @Autowired
    private TaskTypeService taskTypeService;

    @GetMapping("/all")
    public Response getAllType(){
        return Response.success(taskTypeService.getAllTaskType());
    }

    @GetMapping("types")
    public Response getTaskTypesByPid(@RequestParam("pId")int projectId){
        return Response.success(taskTypeService.getTasksByProjectId(projectId));
    }

    @PostMapping("/add")
    public Response addTaskType(@RequestBody TaskType taskType){
        return Response.success(taskTypeService.addTaskType(taskType));
    }


    @DeleteMapping("/delete/{id}")
    public Response deleteById(@PathVariable("id")int id){
        return Response.success(taskTypeService.deleteTaskType(id));
    }
}


