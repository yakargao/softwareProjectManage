package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.constant.StatusCode;
import com.gaoyanshan.bysj.project.dto.UserDTO;
import com.gaoyanshan.bysj.project.entity.Role;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.service.RoleService;
import com.gaoyanshan.bysj.project.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.hibernate.CallbackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>类名: UserController</pre>
 * <pre>描述: 用户所有接口的控制器</pre>
 * <pre>日期: 19-3-16 下午11:12</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@CrossOrigin(origins = "http://0.0.0.0:8888")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 添加用户
     * @param map
     * @return
     */
    @PostMapping("/add")
    public Response addUser(@RequestBody Map<String,String> map){
        User user = userService.addUser(map);
        if (user == null){
            return Response.error(StatusCode.PARAMS_ERROE,"注册失败");
        }
        return Response.success("注册成功");
    }

    /**
     * 当前用户信息
     * @return
     * @throws Exception
     */
    @RequiresAuthentication
    @GetMapping("/information")
    public Response getUserInfo() throws Exception {
        Subject subject = SecurityUtils.getSubject();
        User user = null;
        try {
            user = (User) subject.getPrincipal();
        }catch (Exception e){
            throw new Exception("user类型转化异常");
        }
        //返回体
      return Response.success(userService.getUserInfo(user));
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequiresRoles(value = {"admin"})
    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable("id") int id){
        return Response.success(userService.deleteUser(id));
    }


    /**
     * 更新用户信息
     * @param userDTO
     * @return
     */

    @RequiresAuthentication
    @PutMapping("/{id}")
    public Response update(@RequestBody UserDTO userDTO){
        if (userService.updateUser(userDTO) == true ){
            Subject subject = SecurityUtils.getSubject();
            User user = (User)subject.getPrincipal();
            //当前用户更新了资料，执行登出
            if (user.getEmail().equals(userDTO.getEmail())){
                subject.logout();
            }
            return Response.success("true");
        }else {
            throw new EmptyResultDataAccessException(userDTO.getId());
        }
    }

    /**
     * 获得项目关联用户
     * @param projectId
     * @return
     */
    @GetMapping("/linkUsers")
    public Response getUsersByProject(@RequestParam("projectId")int projectId){
        return Response.success(userService.getUsersByProject(projectId));
    }

    @RequiresAuthentication
    @GetMapping("/users")
    public Response getAllUsers(){
        return Response.success(userService.getAllUsers());
    }
}
