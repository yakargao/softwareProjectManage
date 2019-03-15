package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>类名: TestController</pre>
 * <pre>描述: Test Controller</pre>
 * <pre>日期: 19-3-13 下午11:45</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@RestController
public class TestController {

    @GetMapping("/test")
    public Response test(){
        List<Integer> data = new ArrayList<>();
        return Response.success(data);
    }

}
