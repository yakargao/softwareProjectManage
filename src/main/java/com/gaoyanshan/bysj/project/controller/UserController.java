package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.constant.StatusCode;
import com.gaoyanshan.bysj.project.entity.Role;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.service.RoleService;
import com.gaoyanshan.bysj.project.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.hibernate.CallbackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <pre>类名: UserController</pre>
 * <pre>描述: 用户所有接口的控制器</pre>
 * <pre>日期: 19-3-16 下午11:12</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public Response addUser(@RequestBody Map<String,String> map){
        User user = userService.addUser(map);
        if (user == null){
            return Response.error(StatusCode.PARAMS_ERROE,"注册失败");
        }
        return Response.success("注册成功");
    }

    @RequiresAuthentication
    @GetMapping("/info")
    public Response getUserInfo(){
        Subject subject = SecurityUtils.getSubject();
        return Response.success(subject.getPrincipal());
    }

}
