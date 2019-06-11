package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.dto.APIDTO;
import com.gaoyanshan.bysj.project.dto.ApiCondition;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.service.APISservice;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>类名: APIController</pre>
 * <pre>描述: api控制层</pre>
 * <pre>日期: 19-4-2 下午3:53</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@CrossOrigin(origins = "{http://0.0.0.0:8888}")
@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private APISservice apiSservice;

    @PostMapping("/add")
    public Response addOne(@RequestBody APIDTO dto){
        Subject subject = SecurityUtils.getSubject();
        User user = null;
        try{
            user = (User) subject.getPrincipal();
        }catch (ClassCastException e){
            throw new SystemException("类型转化出错："+e.getMessage());
        }
        return Response.success(apiSservice.addAPI(dto,user));
    }

    @PostMapping("condition")
    public Response getApisByCondition(@RequestBody ApiCondition condition){
        return Response.success(apiSservice.getApisByCoondition(condition));
    }

    @GetMapping("/get")
    public Response getApiById(@RequestParam("id")int id){
        return Response.success(apiSservice.getOneById(id));
    }
}
