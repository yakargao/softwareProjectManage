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
@RequiresAuthentication
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


    @GetMapping("/byUserId")
    public Response getStatistic(@RequestParam("userId")int userId){
        return Response.success(statisticService.getStatistic(userId));
    }


    @GetMapping("/task")
    public Response getTaskStatistic(@RequestParam("pId")int pId,@RequestParam(name = "type",defaultValue = "0")int type){
        return  Response.success(statisticService.getTaskStatistic(pId,type));
    }

    @GetMapping("/document")
    public Response getDocumentStatistic(@RequestParam("pId")int pId,@RequestParam(name = "type",defaultValue = "0")int type){
        return Response.success(statisticService.getDocumentStatistic(pId,type));
    }

    @GetMapping("/file")
    public Response getFileStatistic(@RequestParam("pId")int pId,@RequestParam(name = "type",defaultValue = "0")int type){
        return Response.success(statisticService.getFileStatistic(pId,type));
    }

    @GetMapping("/api")
    public Response getApiStatistic(@RequestParam("pId")int pId,@RequestParam(name = "type",defaultValue = "0")int type){
        return Response.success(statisticService.getApiStatistic(pId,type));
    }
}
