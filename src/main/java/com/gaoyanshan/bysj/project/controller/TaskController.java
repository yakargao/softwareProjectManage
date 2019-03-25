package com.gaoyanshan.bysj.project.controller;

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

/**
 * <pre>类名: TaskController</pre>
 * <pre>描述: task相关接口</pre>
 * <pre>日期: 19-3-25 下午9:37</pre>
 * <pre>作者: gaoyanshan</pre>
 */

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


}
