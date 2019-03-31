package com.gaoyanshan.bysj.project.dynamic.enumeration;

/**
 * <pre>类名: DynamicEventEnum</pre>
 * <pre>描述: 事件枚举</pre>
 * <pre>日期: 2019/3/30 21:51</pre>
 * <pre>作者: ljianf</pre>
 */
public enum  DynamicEventEnum {
    NULL("00",""),
    UPDATE_USER("02","更新用户信息"),
    LOGOUT("03","执行用户登出");

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
