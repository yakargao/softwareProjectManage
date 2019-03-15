package com.gaoyanshan.bysj.project.exception;

import com.gaoyanshan.bysj.project.constant.StatusCode;
import com.gaoyanshan.bysj.project.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>类名: GlobalControllerAdvice</pre>
 * <pre>描述: Exception Handler</pre>
 * <pre>日期: 19-3-13 下午11:29</pre>
 * <pre>作者: gaoyanshan</pre>
 */


@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public Response handlerSystemException(HttpServletRequest request, RuntimeException e){
        return Response.error(StatusCode.EXCEPTION,e.getMessage());
    }
}
