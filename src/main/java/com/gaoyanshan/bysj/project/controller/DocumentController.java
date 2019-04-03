package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.dto.DocumentCondition;
import com.gaoyanshan.bysj.project.dto.DocumentDTO;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.service.DocumentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>类名: DocumentController</pre>
 * <pre>描述: 文档控制层</pre>
 * <pre>日期: 19-3-31 下午7:28</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@CrossOrigin(origins = "http://0.0.0.0:8888")
@RestController
@RequestMapping("/doc")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/types")
    public Response getAllType(){
        return Response.success(documentService.getAllDocType());
    }

    @PostMapping("/add")
    public Response addDocument(@RequestBody DocumentDTO dto){
        Subject subject = SecurityUtils.getSubject();
        User user = null;
        try{
            user = (User) subject.getPrincipal();
        }catch (ClassCastException e){
            throw new SystemException("类型转化出错："+e.getMessage());
        }
        return Response.success(documentService.addDocument(dto,user));
    }

    @DeleteMapping("delete/{id}")
    public Response deleteDocument(@PathVariable("id")int id){
        documentService.deleteDocument(id);
        return Response.success("true");
    }

    @GetMapping("/one")
    public Response getDocumentInfo(@RequestParam("id")int id){
        return Response.success(documentService.getDocumentById(id));
    }

    @PostMapping("/many")
    public Response getDocsByCondition(@RequestBody DocumentCondition condition){
        return  Response.success(documentService.getDocumentsByCondition(condition));
    }
}

