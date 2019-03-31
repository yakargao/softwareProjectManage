package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.util.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>类名: TestController</pre>
 * <pre>描述: Test Controller</pre>
 * <pre>日期: 19-3-13 下午11:45</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@CrossOrigin(origins = "http://0.0.0.0:8888")
@RestController
public class TestController {

    @GetMapping("/test")
    public Response test(){
        List<Integer> data = new ArrayList<>();
        return Response.success(data);
    }


    @RequiresRoles("admin")
    @GetMapping("/index")
    public Response index(){
        return Response.success("index");
    }


}
