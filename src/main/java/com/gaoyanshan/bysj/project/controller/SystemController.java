package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.constant.StatusCode;
import com.gaoyanshan.bysj.project.response.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <pre>类名: SystemController</pre>
 * <pre>描述: 系统默认的一些链接跳转控制器</pre>
 * <pre>日期: 19-3-17 上午12:34</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@RestController
public class SystemController {



    @PostMapping("/login")
    public Response login(@RequestBody Map<String,String> map){
        String userEmail = map.get("email");
        String password = map.get("password");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userEmail,password);
        if (!subject.isAuthenticated()){
            subject.login(token);
        }
        return Response.success(subject.getSession().getId());
    }

    @GetMapping("/unauth")
    public Response unauth(){
        return Response.error(StatusCode.UNAUTH,"unauth");
    }




}
