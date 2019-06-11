package com.gaoyanshan.bysj.project.dynamic.enumeration;

/**
 * <pre>类名: DynamicEventEnum</pre>
 * <pre>描述: 事件枚举</pre>
 * <pre>日期: 2019/3/30 21:51</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public enum  DynamicEventEnum {
    NULL("00",""),
    UPDATE_USER("02","更新用户信息"),
    LOGOUT("03","执行用户登出"),
    LOGIN("04","登录了系统"),
    UPDATE_PROJECT("011","更新了项目"),
    CREATE_TASK("021","更新了任务"),
    DONE_TASK("022","完成了任务"),
    CREATE_DOCUMENT("031","更新了文档"),
    UPDATE_FILE("041","上传了文件"),
    CREATE_API("051","创建了API");


    private String code;

    private String eventName;

    DynamicEventEnum() {
    }

    DynamicEventEnum(String code, String eventName) {
        this.code = code;
        this.eventName = eventName;
    }

    /**
     * 获取code
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置code
     *
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取eventName
     *
     * @return eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * 设置eventName
     *
     * @param eventName eventName
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
