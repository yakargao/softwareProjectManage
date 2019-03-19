package com.gaoyanshan.bysj.project.exception;

/**
 * <pre>类名: SystemException</pre>
 * <pre>描述: 系统错误</pre>
 * <pre>日期: 19-3-16 下午11:23</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class SystemException extends RuntimeException{

    private int code;
    private String message;

    public SystemException(String message) {
        this.message = message;

    }

    public SystemException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
