package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.dto.FileCondition;
import com.gaoyanshan.bysj.project.dto.FileDTO;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.service.FileResourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>类名: FileResourceController</pre>
 * <pre>描述: 文件资源控制层</pre>
 * <pre>日期: 19-4-1 下午9:50</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@CrossOrigin(origins = "http://0.0.0.0:8888")
@RestController
@RequestMapping("/file")
public class FileResourceController {

    @Autowired
    private FileResourceService fileResourceService;


    @PostMapping("/add")
    public Response addFiles(@RequestBody FileDTO dto){
        Subject subject = SecurityUtils.getSubject();
        User user = null;
        try{
            user = (User) subject.getPrincipal();
        }catch (ClassCastException e){
            throw new SystemException("类型转化出错："+e.getMessage());
        }
        return Response.success(fileResourceService.addFiles(dto,user));
    }

    @GetMapping("/allType")
    public Response getAllTypes(){
        return Response.success(fileResourceService.getAllTypes());
    }

    @PostMapping("/condition")
    public Response getFilesByConditon(@RequestBody FileCondition condition){
        return Response.success(fileResourceService.getFilesByCondition(condition));
    }
}
