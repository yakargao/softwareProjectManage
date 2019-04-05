package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.service.StatisticService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>类名: StatisticController</pre>
 * <pre>描述: 统计数据控制层</pre>
 * <pre>日期: 19-4-2 下午3:03</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@CrossOrigin(origins = "http://0.0.0.0:8888")
@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @RequiresAuthentication
    @GetMapping("/personal")
    public Response getPersonalStatistic(@RequestParam("pId")int pid){
        Subject subject = SecurityUtils.getSubject();
        User user = null;
        try{
            user = (User) subject.getPrincipal();
        }catch (ClassCastException e){
            throw new UnauthenticatedException("类型转化出错："+e.getMessage());
        }
        return Response.success(statisticService.getPersonalDatas(pid,user.getId()));
    }

}
