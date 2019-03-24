package com.gaoyanshan.bysj.project.constant;

/**
 * <pre>类名: StatusCode</pre>
 * <pre>描述: http status cade </pre>
 * <pre>日期: 19-3-13 下午11:31</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class StatusCode {

    public static final int SUCCESS = 200;

    public static final int REQUEST_ERROR = 201;

    public static final int PARAMS_ERROE = 202;

    public static final int EXCEPTION_ERROR = 203;

    public static final int INVOKE_THIRDPARTY_ERROR = 204;



    public static final int UN_LOGIN = 1000;

    public static final int UNAUTH = 1001; //未登录或者token过期

    public static final int ACCOUNT_ERROR = 1002;//账号不存在

    public static final int PASSWORD_ERROR = 1003;//密码错误

    public static final int EMPTY_RESULT = 1004;//资源不存在

    public static final  int EXCEPTION = 1005;



}
