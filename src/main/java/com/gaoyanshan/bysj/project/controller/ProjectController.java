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

    /**
     * 通过id获得相关项目的接口
     * @param id
     * @return
     */
    @GetMapping("")
    public Response getProject(@RequestParam("id") int id){
        System.out.println(id);
        return Response.success(projectService.getProjec(id));
    }

    /**
     * 获得当前用户相关的项目
     * @return
     */
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

    /**
     * 新增项目
     * @param map
     * @return
     */
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

    /**
     * 删除项目
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Response deleteProject(@PathVariable("id") int id){
        System.out.println(id);
        projectService.deletedProject(id);
        return Response.success("true");
    }

    /**
     * 更新项目内容
     * @param id
     * @param map
     * @return
     */
    @PutMapping("/{id}")
    public Response updateProject(@PathVariable("id") int id,@RequestBody Map<String,Object> map){
        System.out.println(id);
        System.out.println(map);
        projectService.updateProject(id,map);
        return Response.success("true");
    }



}
