package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.dto.TaskCondition;
import com.gaoyanshan.bysj.project.dto.TaskDTO;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.service.TaskService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <pre>类名: TaskController</pre>
 * <pre>描述: task相关接口</pre>
 * <pre>日期: 19-3-25 下午9:37</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@CrossOrigin(origins = "http://0.0.0.0:8888")
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 新增任务
     * @param taskDTO
     * @return
     */

    @RequiresAuthentication
    @PostMapping("add")
    public Response addTask(@RequestBody  TaskDTO taskDTO){
        Subject subject = SecurityUtils.getSubject();
        User user = null;
        try{
            user = (User) subject.getPrincipal();
        }catch (ClassCastException e){
            throw new SystemException("类型转化出错："+e.getMessage());
        }
        taskService.addTask(taskDTO,user);
        return Response.success("true");
    }

    /**
     * 删除任务
     * @param taskid
     * @return
     */

    @RequiresAuthentication
    @DeleteMapping("delete/{id}")
    public Response deleteTask(@PathVariable("id")int taskid){
        taskService.deleteTask(taskid);
        return Response.success("true");
    }

    /**
     * 更新任务
     * @param taskDTO
     * @param taskId
     * @return
     */
    @RequiresAuthentication
    @PutMapping("/update/{id}")
    public Response updateTask(@RequestBody TaskDTO taskDTO ,@PathVariable("id")int taskId){
        taskService.updatedTask(taskDTO,taskId);
        return Response.success("true");
    }


    /**
     * 获取某个任务详情
     * @param taskId
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/get")
    public Response getTask(@RequestParam("taskId")int taskId){
        return Response.success(taskService.getTask(taskId));
    }

    @RequiresAuthentication
    @GetMapping("/tasksByPid")
    public Response getTasksByProject(@RequestParam("pid")int projectId){
        return Response.success(taskService.getTasksByProjectId(projectId));
    }

    /**
     * 根据用户获取任务信息
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/tasksByUser")
    public Response getTaskByUser(@RequestParam("userId")int userId){
        return Response.success(taskService.getTaskByUserId(userId));
    }

    @PutMapping("/status/{id}")
    public Response setTaskStatus(@RequestBody Map<String,Boolean> map,@PathVariable("id")int taskId){
        boolean completed = map.get("completed");
        int status = completed == true ? Constant.TASK_IS_DONE : Constant.TASK_IS_NOT_DONE;
        taskService.updateStatus(taskId,status);
        return Response.success("true");
    }


    @PostMapping("/condition")
    public Response getTasksByCondition(@RequestBody TaskCondition taskCondition){
        System.out.println(taskCondition);
        return Response.success(taskService.getTasksByCondition(taskCondition));
    }

    @GetMapping("/byType")
    public Response getTasksByType(@RequestParam("type") int type){
        return Response.success(taskService.getTasksByType(type));
    }

    /**
     * 新增任务
     * @param taskDTO
     * @return
     */
    @PostMapping("/simple")
    public Response addSimpleTask(@RequestBody TaskDTO taskDTO){
        Subject subject = SecurityUtils.getSubject();
        User user = null;
        try{
            user = (User) subject.getPrincipal();
        }catch (ClassCastException e){
            throw new SystemException("类型转化出错："+e.getMessage());
        }
        return  Response.success(taskService.addTask(taskDTO,user));
    }


    /**
     * 获取创建者
     * @param taskId
     * @return
     */
    @GetMapping("/creator")
    public Response getCreatorOfTask(@RequestParam("taskId")int taskId){
        return Response.success(taskService.getCreateUser(taskId));
    }

    /**
     * 获取参与者
     * @param taskId
     * @return
     */
    @GetMapping("/actors")
    public Response getActors(@RequestParam("taskId")int taskId){
        return Response.success(taskService.getResponsUsers(taskId));
    }

    @PostMapping("/addActor")
    public Response addActor(@RequestBody Map<String,Integer> map){
        return Response.success(taskService.addActor(map));
    }
}
