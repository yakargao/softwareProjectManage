package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.service.DynamicContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://0.0.0.0:8888")
@RestController
@RequestMapping("/dynamic")
public class DynamicContentController {


    @Autowired
    private DynamicContentService dynamicContentService;

    @GetMapping("/byUserId")
    public Response getDynamicsByUserId(@RequestParam(name = "page",defaultValue = "1")int page,
                                        @RequestParam(name = "size",defaultValue = "10")int size,
                                        @RequestParam(name = "userId")int userId){
        return Response.success( dynamicContentService.getDynamicContentsByUserId(page,size,userId));
    }

}
