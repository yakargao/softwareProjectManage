package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.service.ProjectService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <pre>类名: ProjectController</pre>
 * <pre>描述: 项目控制层</pre>
 * <pre>日期: 19-3-21 下午8:21</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public Response getProject(@RequestParam("id") int id){
        System.out.println(id);
        return Response.success(projectService.getProjec(id));
    }

    @GetMapping("/projects")
    public Response getProjects(){
        Subject subject = SecurityUtils.getSubject();
        User user = null;
        try{
            user = (User) subject.getPrincipal();
        }catch (ClassCastException e){
            throw new SystemException("类型转化出错："+e.getMessage());
        }
        return Response.success(projectService.getProjectsByUserId(user.getId()));
    }

    @PostMapping()
    public Response addProject(@RequestBody Map<String,Object> map){
        Subject subject = SecurityUtils.getSubject();
        User user = null;
        try{
            user = (User) subject.getPrincipal();
        }catch (ClassCastException e){
            throw new SystemException("类型转化出错："+e.getMessage());
        }
        projectService.addProject(map,user);
        return Response.success("true");
    }

    @DeleteMapping("/{id}")
    public Response deleteProject(@PathVariable("id") int id){
        System.out.println(id);
        projectService.deletedProject(id);
        return Response.success("true");
    }

    @PutMapping("/{id}")
    public Response updateProject(@PathVariable("id") int id,@RequestBody Map<String,Object> map){
        System.out.println(id);
        System.out.println(map);
        projectService.updateProject(id,map);
        return Response.success("true");
    }


}
