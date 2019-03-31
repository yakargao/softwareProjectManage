package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.constant.StatusCode;
import com.gaoyanshan.bysj.project.dynamic.aspect.Dynamic;
import com.gaoyanshan.bysj.project.dynamic.enumeration.DynamicEventEnum;
import com.gaoyanshan.bysj.project.response.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>类名: SystemController</pre>
 * <pre>描述: 系统默认的一些链接跳转控制器</pre>
 * <pre>日期: 19-3-17 上午12:34</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@CrossOrigin(origins = "http://0.0.0.0:8888")
@RestController
public class SystemController {


    /**
     * 登录接口，无需权限
     * @param map
     * @return
     */
    @PostMapping("/login")
    public Response login(@RequestBody Map<String,String> map){
        String userEmail = map.get("email");
        String password = map.get("password");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userEmail,password);
        if (!subject.isAuthenticated()){
            subject.login(token);
        }
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("token",subject.getSession().getId());
        return Response.success(resMap);
    }

    /**
     * 登出接口，需要验证是否登录
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/logout")
    @Dynamic(event = DynamicEventEnum.LOGOUT)
    public Response logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Response.success("ok");
    }

    @GetMapping("/unauth")
    public Response unauth(){
        return Response.error(StatusCode.UNAUTH,"unauth");
    }




}
