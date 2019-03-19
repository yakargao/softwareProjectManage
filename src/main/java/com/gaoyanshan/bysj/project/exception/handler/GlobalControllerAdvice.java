package com.gaoyanshan.bysj.project.exception.handler;

import com.gaoyanshan.bysj.project.constant.StatusCode;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.response.Response;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sun.awt.SunHints;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.CoderResult;

/**
 * <pre>类名: GlobalControllerAdvice</pre>
 * <pre>描述: Exception Handler</pre>
 * <pre>日期: 19-3-13 下午11:29</pre>
 * <pre>作者: gaoyanshan</pre>
 */


@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = SystemException.class)
    public Response handlerTypeMatchException(SystemException e){
        return Response.error(e.getCode(),e.getMessage());
    }


    /**
     * 处理登录账号不存在
     * @return
     */
    @ExceptionHandler(value = UnknownAccountException.class)
    public Response handlerUnknownAccountException(){
        return Response.error(StatusCode.ACCOUNT_ERROR,"账号不存在");
    }

    /**
     * 处理密码错误
     * @param ie
     * @return
     */
    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public Response handleIncorrectCredentialsException(IncorrectCredentialsException ie){
        return Response.error(StatusCode.PASSWORD_ERROR,"密码错误");
    }

    /**
     * 处理未登录或者token过期
     * @param ue
     * @return
     */
    @ExceptionHandler(value = UnauthenticatedException.class)
    public Response handleUnauthenticatedException(UnauthenticatedException ue){
        return Response.error(StatusCode.UNAUTH,ue.getMessage());
    }


    @ExceptionHandler(value = RuntimeException.class)
    public Response handlerSystemException(RuntimeException e){
        System.out.println(e.getClass());
        return Response.error(StatusCode.EXCEPTION,e.getMessage());
    }

}
