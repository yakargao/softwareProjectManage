package com.gaoyanshan.bysj.project.controller;


import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.service.GithubLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://0.0.0.0:8888")
@RestController
@RequestMapping("/github")
public class GithubLogController {

    @Autowired
    private  GithubLogService githubLogService;

    @GetMapping("/logs")
    public Response getGithubLogs(@RequestParam(value = "page",defaultValue = "1")int page,
                                  @RequestParam(value = "size",defaultValue ="10")int size,
                                  @RequestParam("pId")int pId){
        return  Response.success(githubLogService.getGithubLog(pId,page,size));
    }
}
