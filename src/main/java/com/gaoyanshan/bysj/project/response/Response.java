package com.gaoyanshan.bysj.project.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gaoyanshan.bysj.project.constant.StatusCode;
import jdk.net.SocketFlow;

/**
 * <pre>类名: Response</pre>
 * <pre>描述: Interface returns a standard class</pre>
 * <pre>日期: 19-3-13 下午10:54</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class Response<T> {

    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(int code,T data) {
        this.code = code;
        this.data = data;
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * @desc 成功返回
     * @param data
     * @param <T>
     * @return
     */
    public  static <T>Response success(T data){
        return new Response(StatusCode.SUCCESS,data);
    }

    /**
     * @desc 错误返回
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T>Response error(int code,String message){
        return new Response(code,message);
    }

}
